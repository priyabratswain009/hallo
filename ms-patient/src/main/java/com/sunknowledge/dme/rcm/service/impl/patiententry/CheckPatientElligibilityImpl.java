package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.patientelligibility.PatientElligibilityInput;
import com.sunknowledge.dme.rcm.domain.patientelligibility.PatientElligibilityOutput;
import com.sunknowledge.dme.rcm.domain.patientelligibility.Subscriber;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientelligibility.ElligibilityHealthCheck;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceVerificationParameterDTO;
import com.sunknowledge.dme.rcm.service.patiententry.CheckPatientElligibility;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.utils.application.ApplicationConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CheckPatientElligibilityImpl implements CheckPatientElligibility {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationService;
	@Autowired
	private PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
	@Autowired
	private PatientInsuranceSearchServiceExtended patientInsuranceSearchServiceExtended;

	@Override
	public PatientInsuranceVerificationParameterDTO checkElligibility(String patientId) {
		// TODO Auto-generated method stub
        System.out.println("===========Inside checkElligibility============");
        String inputEntity = "";
        PatientSearchByBasicInfoOrAddressOrBranch obj = new PatientSearchByBasicInfoOrAddressOrBranch();
        PatientElligibilityOutput patientElligibilityOutput = null;
        String url = ApplicationConstant.ELLIGIBILITY_URL;
        PatientInsuranceVerificationParameterDTO patientInsuranceVerificationParameterDTO = new PatientInsuranceVerificationParameterDTO();
		try {

            System.out.println("===========Inside try============");

			String accessToken = tokenGenerationService.getToken();
			PatientElligibilityInput objPatientElligibilityInput = new PatientElligibilityInput();
            obj.setPatientID(Long.valueOf(patientId));
			PatientMasterDTO objpatient = patientMasterSearchServiceExtended
					.getPatientBySearchParameters(obj).collectList().toFuture().get().get(0);
			PatientInsuranceDTO patientInsuranceDTO = patientInsuranceSearchServiceExtended
					.getPatientInsuranceByPatientId(Long.valueOf(patientId)).collectList().toFuture().get().get(0);
			if (objpatient == null || patientInsuranceDTO == null) {
                System.out.println("No Data available in Patient");
			} else {
				if (checkElligibilityHealth(accessToken)) {
					objPatientElligibilityInput = getInputData(objpatient, patientInsuranceDTO);
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Type", "application/json");
					headers.add("Accept", "application/json");
					headers.set("Authorization", "Bearer " + accessToken);
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					inputEntity = ow.writeValueAsString(objPatientElligibilityInput);

					HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
					ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
							String.class);

                    System.out.println("========response========"+response);

					if (response.getStatusCode() == HttpStatus.OK) {
						ObjectMapper mapper = new ObjectMapper();
						patientElligibilityOutput = mapper.readValue(response.getBody(),
								PatientElligibilityOutput.class);
						if(patientElligibilityOutput.getErrors()==null) {
							patientInsuranceVerificationParameterDTO = setDTOData(patientElligibilityOutput,
									String.valueOf(patientInsuranceDTO.getPatientInsuranceId()),
									ow.writeValueAsString(patientElligibilityOutput));
							log.info("Successful TraceId :"+patientElligibilityOutput.getMeta().getTraceId());
						}else {
							patientInsuranceVerificationParameterDTO=new PatientInsuranceVerificationParameterDTO();
							log.info("===================Errors======================"+response.getBody());
						}
					} else {
						log.info("===================Failed======================"+response.getBody());
					}
				} else {
					log.info("URL you're Trying to access is currently  Unavailable");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patientInsuranceVerificationParameterDTO;
	}

	@Override
	public boolean checkElligibilityHealth(String token) {
		// TODO Auto-generated method stub
		ElligibilityHealthCheck healthCheckOutcome = new ElligibilityHealthCheck();
		boolean status = false;

		try {
			String URL = ApplicationConstant.ELLIGIBILITY_HEALTHCHECK_URL;
			HttpHeaders header = new HttpHeaders();
			token = "Bearer " + token;

			header.add("Content-Type", "application/json");
			header.add("Accept", "application/json");
			header.add("Authorization", token);

			HttpEntity<String> request = new HttpEntity<String>(null, header);
			ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, request, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				healthCheckOutcome = mapper.readValue(responseEntity.getBody(), ElligibilityHealthCheck.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (healthCheckOutcome.getVersion().equalsIgnoreCase("v3")
				&& healthCheckOutcome.getStatus().equalsIgnoreCase("ok")) {
			status = true;
		} else {
			status = false;
		}

		return status;
	}

	public static PatientElligibilityInput getInputData(PatientMasterDTO objpatient,
			PatientInsuranceDTO objpatientInsurance) {

		PatientElligibilityInput objPatientElligibilityInput = new PatientElligibilityInput();
		Subscriber objSubscriber = new Subscriber();
		String fname = "", lname = "";

		if (CommonUtilities.isStringNullOrBlank(objpatientInsurance.getPolicyNum())) {
			objSubscriber.setMemberId(objpatientInsurance.getPolicyNum());
		}
		if (CommonUtilities.isStringNullOrBlank(
            CommonUtilities.mergeName(objpatient.getPatientFirstName(), objpatient.getPatientMiddleName(), objpatient.getPatientLastName()))) {
			String[] arr = CommonUtilities.mergeName(objpatient.getPatientFirstName(), objpatient.getPatientMiddleName(), objpatient.getPatientLastName()).split(" ");
			fname = arr[0];
			lname = arr[arr.length - 1];
		}
		if (CommonUtilities.isStringNullOrBlank(fname)) {
			objSubscriber.setFirstName(fname);
		}
		if (CommonUtilities.isStringNullOrBlank(lname)) {
			objSubscriber.setLastName(lname);
		}
		if (CommonUtilities.isStringNullOrBlank(objpatient.getGender())) {
			objSubscriber.setGender(objpatient.getGender());
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objpatient.getDob()))) {
			objSubscriber.setDateOfBirth(CommonUtilities.datetoStringConverter(String.valueOf(objpatient.getDob())));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objpatientInsurance.getPolicyNum()))) {
			// No control number is available in patient
			objPatientElligibilityInput.setControlNumber("123456789");
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objpatientInsurance.getInsurancePayerIdNo()))) {
			// for sandbox using hard coded value
			objPatientElligibilityInput.setTradingPartnerServiceId(objpatientInsurance.getInsurancePayerIdNo());
		}
		objPatientElligibilityInput.setSubscriber(objSubscriber);

		return objPatientElligibilityInput;
	}

	public static PatientInsuranceVerificationParameterDTO setDTOData(
			PatientElligibilityOutput patientElligibilityOutput, String insuranceId, String response) {

		PatientInsuranceVerificationParameterDTO patientInsuranceVerificationParameterDTO = new PatientInsuranceVerificationParameterDTO();

		if (CommonUtilities.isStringNullOrBlank(insuranceId)) {
			patientInsuranceVerificationParameterDTO.setPatientInsuranceUuid(Long.valueOf(insuranceId));
		}
		if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getPlanStatus().get(0).getStatus())) {
			patientInsuranceVerificationParameterDTO
					.setElligibilityStatus(patientElligibilityOutput.getPlanStatus().get(0).getStatus());
		}
		patientInsuranceVerificationParameterDTO.setElligibilityCheckType("Change Health Care");
		if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getPlanDateInformation().getPlan())) {
			patientInsuranceVerificationParameterDTO
					.setPeriodYear(patientElligibilityOutput.getPlanDateInformation().getPlan());
		}
		if (CommonUtilities.isStringNullOrBlank(response)) {
			patientInsuranceVerificationParameterDTO.setCoverageInfo(response);
		}
		if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getMeta().getTraceId())) {
			patientInsuranceVerificationParameterDTO.setNetworkInfo(patientElligibilityOutput.getMeta().getTraceId());
		}
		if (CommonUtilities.isStringNullOrBlank(patientElligibilityOutput.getPlanStatus().get(0).getStatus())) {
			patientInsuranceVerificationParameterDTO
					.setNetworkInfo(patientElligibilityOutput.getPlanStatus().get(0).getStatus());
		}
		return patientInsuranceVerificationParameterDTO;
	}

}
