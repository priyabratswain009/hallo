package com.sunknowledge.dme.rcm.service.impl.claimsubmissiondata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ElligibilityResponse;
import com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation;
import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.elligibility.AccessTokenError;
import com.sunknowledge.dme.rcm.domain.elligibility.BenefitsInformation;
import com.sunknowledge.dme.rcm.domain.elligibility.Dependent;
import com.sunknowledge.dme.rcm.domain.elligibility.EligibilityInput;
import com.sunknowledge.dme.rcm.domain.elligibility.EligibilityOutput;
import com.sunknowledge.dme.rcm.domain.elligibility.Encounter;
import com.sunknowledge.dme.rcm.domain.elligibility.HealthCheckOutcome;
import com.sunknowledge.dme.rcm.domain.elligibility.ProviderIP;
import com.sunknowledge.dme.rcm.domain.elligibility.ResultElligibilityOutcome;
import com.sunknowledge.dme.rcm.domain.elligibility.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.elligibility.SubscriberIP;
import com.sunknowledge.dme.rcm.domain.elligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseBenefitinformationRepository;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseRepository;
import com.sunknowledge.dme.rcm.repository.MemberElligibilityMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.ElligibilityServiceExtended;

import reactor.core.publisher.Mono;

@Service
public class ElligibilityServiceExtendedImpl implements ElligibilityServiceExtended {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;
	@Autowired
	private MemberElligibilityMasterRepositoryExtended memberElligibilityRepositoryExtended;
	@Autowired
	private ElligibilityResponseRepository elligibilityResponseRepository;
	@Autowired
	ElligibilityResponseBenefitinformationRepository elligibilityResponseBenefitinformationRepository;
	@Autowired
	private SalesOrderMasterRepositoryExtended salesOrderMasterRepositoryExtended;

	@Override
	public ServiceOutcome<ResultElligibilityOutcome> medicalEligibiltycheck(String salesOrderId) {

		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		ResultElligibilityOutcome claimOutcome = new ResultElligibilityOutcome();
		ElligibilityResponse objElligibilityResponse = new ElligibilityResponse();
		try {
			MemberElligibilityMaster data = memberElligibilityRepositoryExtended
					.getmemberElligibilityData(Long.valueOf(salesOrderId)).toFuture().get();
			EligibilityInput eligibilityInput = setData(data);
			objTokenOutCome = tokenGenerationServiceImpl.getToken();
			String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
			if (eligibiltyHealthcheck(token).getOutcome()) {
				routcome = elligiblityCheck(token, eligibilityInput);
				objElligibilityResponse = setresponse(routcome.getData().getElligiblityResponseOutput());
				ElligibilityResponse elligibilityResponse = elligibilityResponseRepository.save(objElligibilityResponse)
						.toFuture().get();
				setbenefitinfo(routcome.getData().getElligiblityResponseOutput().getBenefitsInformation(),
						elligibilityResponse.getElligibilityResponseStatusId());
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setHealthCheckOutcome(null);
				claimOutcome.setErrorhandler(null);
				claimOutcome.setElligiblityResponseOutput(null);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultElligibilityOutcome> eligibiltyHealthcheck(String token) {
		boolean response = false;
		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		ResultElligibilityOutcome claimOutcome = new ResultElligibilityOutcome();
		try {
			String URL = ApplicationConstants.ELLIGIBILITY_HEALTHCHECK_URL;
			HttpHeaders header = new HttpHeaders();
			token = "Bearer " + token;

			header.add("Content-Type", "application/json");
			header.add("Accept", "application/json");
			header.add("Authorization", token);

			HttpEntity<String> request = new HttpEntity<String>(null, header);
			ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, request, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckOutcome healthCheckOutcome = mapper.readValue(responseEntity.getBody(),
						HealthCheckOutcome.class);
				if (healthCheckOutcome.getVersion().equalsIgnoreCase("v3")
						&& healthCheckOutcome.getStatus().equalsIgnoreCase("ok")) {
					response = true;
					claimOutcome.setHealthCheckOutcome(healthCheckOutcome);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! OK.");
					routcome.setOutcome(response);
				} else {
					System.out.println("Improper Response");
				}
			} else {
				System.out.println("Failed");
			}
		} catch (HttpStatusCodeException hsce) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(hsce.getResponseBodyAsString());
			try {
				ObjectMapper mapper = new ObjectMapper();
				String errorResponse = responseException.getBody();
				AccessTokenError accessTokenError = mapper.readValue(errorResponse, AccessTokenError.class);
				claimOutcome.setAccessTokenError(accessTokenError);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return routcome;
	}

	@Override
	public ServiceOutcome<ResultElligibilityOutcome> elligiblityCheck(String accessToken,
			EligibilityInput eligibilityInput) {

		String inputEntity = "";
		EligibilityOutput elligiblityResponseOutput = null;
		String url = ApplicationConstants.ELLIGIBILITY_URL;
		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		ResultElligibilityOutcome claimOutcome = new ResultElligibilityOutcome();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(eligibilityInput);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				elligiblityResponseOutput = mapper.readValue(response.getBody(), EligibilityOutput.class);

				ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				claimOutcome.setElligiblityResponseOutput(elligiblityResponseOutput);
				routcome.setData(claimOutcome);
				routcome.setMessage("Successfully get the data");
				routcome.setOutcome(true);

			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return routcome;
	}

	EligibilityInput setData(MemberElligibilityMaster elligibility) {

		EligibilityInput eligibilityInput = new EligibilityInput();
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getElligibilityControlNumber()))) {
			// Sand Box Value
			eligibilityInput.setControlNumber("123456789");
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getTradingPartnerServiceId()))) {
			eligibilityInput.setTradingPartnerServiceId(String.valueOf(elligibility.getTradingPartnerServiceId()));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getTradingPartnerName()))) {
			eligibilityInput.setTradingPartnerName(String.valueOf(elligibility.getTradingPartnerName()));
		}
		// Provider
		ProviderIP objProviderIP = new ProviderIP();
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getProviderOrganizationName()))) {
			objProviderIP.setOrganizationName(String.valueOf(elligibility.getProviderOrganizationName()));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getProviderNpi()))) {
			objProviderIP.setNpi(String.valueOf(elligibility.getProviderNpi()));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getProviderType()))) {
			objProviderIP.setProviderCode("BI");
		}
		eligibilityInput.setProvider(objProviderIP);
		// Subscriber
		SubscriberIP objSubscriberIP = new SubscriberIP();
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getSubscriberFirstName()))) {
			objSubscriberIP.setFirstName(String.valueOf(elligibility.getSubscriberFirstName()));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getSubscriberLastName()))) {
			objSubscriberIP.setLastName(String.valueOf(elligibility.getSubscriberLastName()));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getSubscriberMemberId()))) {
			// Hard coded value for CHC
			objSubscriberIP.setMemberId("0000000005");
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getSubscriberDob()))) {
			objSubscriberIP.setDateOfBirth(
					CommonUtilities.datetoStringConverter(String.valueOf(elligibility.getSubscriberDob())));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getSubscriberGender()))) {
			objSubscriberIP.setGender(String.valueOf(elligibility.getSubscriberGender()));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getSubscriberPlanIssueDate()))) {
			objSubscriberIP.setPlanIssueDate(
					CommonUtilities.datetoStringConverter(String.valueOf(elligibility.getSubscriberPlanIssueDate())));
		}
		eligibilityInput.setSubscriber(objSubscriberIP);
		// Dependent
		if (!elligibility.getInsuredRelationshipwithSubscriber().equalsIgnoreCase("18")) {
			List<Dependent> listDependent = new ArrayList<>();
			Dependent objDependent = new Dependent();
			if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getInsuredFirstName()))) {
				objDependent.setFirstName(String.valueOf(elligibility.getInsuredFirstName()));
			}
			if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getInsuredLastName()))) {
				objDependent.setLastName(String.valueOf(elligibility.getInsuredLastName()));
			}
			if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getInsuredGender()))) {
				objDependent.setGender(String.valueOf(elligibility.getInsuredGender()));
			}
			if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getInsuredDob()))) {
				objDependent.setDateOfBirth(
						CommonUtilities.datetoStringConverter(String.valueOf(elligibility.getInsuredDob())));
			}
			if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getInsuredGender()))) {
				objDependent.setGender(String.valueOf(elligibility.getInsuredGender()));
			}
			listDependent.add(objDependent);
			eligibilityInput.setDependents(listDependent);
		}
		// Encounter
		Encounter objEncounter = new Encounter();
		List<String> serviceTypeCodes = new ArrayList<>();
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getDateOfService()))) {
			objEncounter.setDateOfService(
					CommonUtilities.datetoStringConverter(String.valueOf(elligibility.getDateOfService())));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(elligibility.getServiceTypeCodes()))) {
			serviceTypeCodes.add(elligibility.getServiceTypeCodes());
		}
		objEncounter.setServiceTypeCodes(serviceTypeCodes);
		eligibilityInput.setEncounter(objEncounter);

		return eligibilityInput;
	}

	ElligibilityResponse setresponse(EligibilityOutput eligibilityOutput) {

		ElligibilityResponse objElligibilityResponse = new ElligibilityResponse();
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getControlNumber())) {
			objElligibilityResponse.setElligibilityControlNumber(eligibilityOutput.getControlNumber());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getMeta().getTraceId())) {
			objElligibilityResponse.setTraceid(eligibilityOutput.getMeta().getTraceId());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getFirstName())) {
			objElligibilityResponse.setSubscriberFirstName(eligibilityOutput.getSubscriber().getFirstName());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getLastName())) {
			objElligibilityResponse.setSubscriberLastName(eligibilityOutput.getSubscriber().getLastName());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getGender())) {
			objElligibilityResponse.setSubscriberGender(eligibilityOutput.getSubscriber().getGender());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getDateOfBirth())) {
			objElligibilityResponse.setSubscriberDob(
					CommonUtilities.stringtodateConverter(eligibilityOutput.getSubscriber().getDateOfBirth()));
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getEntityIdentifier())) {
			objElligibilityResponse.setSubscriberIdentifier(eligibilityOutput.getSubscriber().getEntityIdentifier());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getEntityType())) {
			objElligibilityResponse.setSubscriberEntitytype(eligibilityOutput.getSubscriber().getEntityType());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getSubscriber().getSsn())) {
			objElligibilityResponse.setSubscriberSsn(eligibilityOutput.getSubscriber().getSsn());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPayer().getEntityIdentifier())) {
			objElligibilityResponse.setPayerIdentifier(eligibilityOutput.getPayer().getEntityIdentifier());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPayer().getEntityType())) {
			objElligibilityResponse.setPayerEntitytype(eligibilityOutput.getPayer().getEntityType());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPayer().getName())) {
			objElligibilityResponse.setPayerName(eligibilityOutput.getPayer().getName());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPayer().getPayorIdentification())) {
			objElligibilityResponse.setPayerIdentification(eligibilityOutput.getPayer().getPayorIdentification());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getControlNumber())) {
			objElligibilityResponse.setElligibilityControlNumber(eligibilityOutput.getControlNumber());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPlanInformation().getSocialSecurityNumber())) {
			objElligibilityResponse.setPlanSsn(eligibilityOutput.getPlanInformation().getSocialSecurityNumber());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPlanDateInformation().getPlan())) {
			objElligibilityResponse.setPlanDate(eligibilityOutput.getPlanDateInformation().getPlan());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPlanStatus().get(0).getStatusCode())) {
			objElligibilityResponse.setPlanStatusCode(eligibilityOutput.getPlanStatus().get(0).getStatusCode());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPlanStatus().get(0).getStatus())) {
			objElligibilityResponse.setPlanStatus(eligibilityOutput.getPlanStatus().get(0).getStatus());
		}
		if (CommonUtilities.isStringNullOrBlank(eligibilityOutput.getPlanStatus().get(0).getPlanDetails())) {
			objElligibilityResponse.setPlanDetails(eligibilityOutput.getPlanStatus().get(0).getPlanDetails());
		}
		if (!Objects.isNull(eligibilityOutput.getPlanStatus().get(0).getServiceTypeCodes())) {
			List<String> serviceTypeCodes = new ArrayList<>();
			for (int i = 0; i < eligibilityOutput.getPlanStatus().get(0).getServiceTypeCodes().size(); i++) {
				serviceTypeCodes.add(eligibilityOutput.getPlanStatus().get(0).getServiceTypeCodes().get(i));
			}
			objElligibilityResponse.setServiceTypeCodes(serviceTypeCodes.toString());
		}

		return objElligibilityResponse;
	}

	String setbenefitinfo(List<BenefitsInformation> benefitsInformation, Long id) {

		for (int i = 0; i < benefitsInformation.size(); i++) {
			ElligibilityResponseBenefitinformation objElligibilityResponseBenefitinformation = new ElligibilityResponseBenefitinformation();

			objElligibilityResponseBenefitinformation.setElligibilityResponseStatusId(id);

			if (CommonUtilities.isStringNullOrBlank(benefitsInformation.get(i).getCode())) {
				objElligibilityResponseBenefitinformation
						.setBenefitsinformationCode(benefitsInformation.get(i).getCode());
			}
			if (CommonUtilities.isStringNullOrBlank(benefitsInformation.get(i).getName())) {
				objElligibilityResponseBenefitinformation
						.setBenefitsinformationName(benefitsInformation.get(i).getName());
			}
			if (CommonUtilities.isStringNullOrBlank(benefitsInformation.get(i).getCoverageLevelCode())) {
				objElligibilityResponseBenefitinformation
						.setCoverageLevelCode(benefitsInformation.get(i).getCoverageLevelCode());
			}
			if (!Objects.isNull(benefitsInformation.get(i).getServiceTypeCodes())) {
				List<String> serviceTypeCodes = new ArrayList<>();
				for (int j = 0; j < benefitsInformation.get(j).getServiceTypeCodes().size(); j++) {
					serviceTypeCodes.add(benefitsInformation.get(j).getServiceTypeCodes().get(j));
				}
				objElligibilityResponseBenefitinformation.setServiceTypeCodes(serviceTypeCodes.toString());
			}
			if (CommonUtilities.isStringNullOrBlank(benefitsInformation.get(i).getInsuranceTypeCode())) {
				objElligibilityResponseBenefitinformation
						.setInsuranceTypeCode(benefitsInformation.get(i).getInsuranceTypeCode());
			}
			if (CommonUtilities.isStringNullOrBlank(benefitsInformation.get(i).getPlanCoverage())) {
				objElligibilityResponseBenefitinformation.setPlanCoverage(benefitsInformation.get(i).getPlanCoverage());
			}
			if (CommonUtilities
					.isStringNullOrBlank(benefitsInformation.get(i).getBenefitsDateInformation().getBenefit())) {
				objElligibilityResponseBenefitinformation.setBenefitsDateInformation(
						"Benefit:" + String.valueOf(benefitsInformation.get(i).getBenefitsDateInformation().getBenefit()));
			} else if (CommonUtilities
					.isStringNullOrBlank(benefitsInformation.get(i).getBenefitsDateInformation().getPlan())) {
				objElligibilityResponseBenefitinformation.setBenefitsDateInformation(
						"Plan:" + String.valueOf(benefitsInformation.get(i).getBenefitsDateInformation().getPlan()));
			}

			elligibilityResponseBenefitinformationRepository.save(objElligibilityResponseBenefitinformation).toFuture();

		}
		return "OK";
	}

	@Override
	public ServiceOutcome<MemberElligibilityMaster> insertMemberPrimaryElligibilityData(Long soId)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		ServiceOutcome<MemberElligibilityMaster> outCome = new ServiceOutcome<MemberElligibilityMaster>();
		MemberElligibilityMaster memberElligibilityMaster = memberElligibilityRepositoryExtended.insertMemberPrimaryElligibilityData(soId).toFuture().get();
		
		outCome.setData(memberElligibilityMaster);
		outCome.setMessage("Data Recieved Successfully");
		outCome.setOutcome(true);
		
		return outCome;
	}

	@Override
	public ServiceOutcome<MemberElligibilityMaster> insertMemberSecondaryElligibilityData(Long soId)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		ServiceOutcome<MemberElligibilityMaster> outCome = new ServiceOutcome<MemberElligibilityMaster>();
		MemberElligibilityMaster memberElligibilityMaster = memberElligibilityRepositoryExtended.insertMemberSecondaryElligibilityData(soId).toFuture().get();
		
		outCome.setData(memberElligibilityMaster);
		outCome.setMessage("Data Recieved Successfully");
		outCome.setOutcome(true);
		
		return outCome;
	}

	@Override
	public Mono<Boolean> validateClaimSubmissionData(Long soId) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return salesOrderMasterRepositoryExtended.validateClaimSubmissionData(soId);
	}

	@Override
	public Mono<SalesOrderMaster> createDuplicateSalesOrder(Long soId) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return salesOrderMasterRepositoryExtended.createDuplicateSalesOrder(soId);
	}

	@Override
	public Mono<Boolean> rentalHelperPeriodGreaterThanOne(Long salesOrderId, String periodNo,
			Long previousSoId) {
		// TODO Auto-generated method stub
		return salesOrderMasterRepositoryExtended.rentalHelperPeriodGreaterThanOne(salesOrderId, periodNo, previousSoId);
	}

	@Override
	public Mono<SalesOrderMaster> updateOrderStatus(Long salesOrderId) {
		// TODO Auto-generated method stub
		return salesOrderMasterRepositoryExtended.getSOBysalesOrderId(salesOrderId);
	}

	@Override
	public Mono<SalesOrderMaster> saveSalesOrder(SalesOrderMaster salesOrderMaster) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return salesOrderMasterRepositoryExtended.save(salesOrderMaster);
		
		 
	}

}
