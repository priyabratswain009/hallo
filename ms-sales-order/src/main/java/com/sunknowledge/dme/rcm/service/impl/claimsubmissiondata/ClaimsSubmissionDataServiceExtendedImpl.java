package com.sunknowledge.dme.rcm.service.impl.claimsubmissiondata;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.dto.claims.ClaimSubmissionStatusCustomDTO;
import com.sunknowledge.dme.rcm.dto.claims.PatientInsuranceParameterMicroserviceDTO;
import com.sunknowledge.dme.rcm.repository.ClaimsSubmissionMasterDataRepoExtended;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimsSubmissionMasterDataRepoExtended;
import com.sunknowledge.dme.rcm.repository.claims.ClaimSubmissionStatusRepo;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.ClaimsSubmissionDataServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsServiceExtended;
import com.sunknowledge.dme.rcm.web.rest.soentryandsearch.SalesOrderInsuranceDetailsSearchResourceExtended;

import reactor.core.publisher.Mono;

@Service
public class ClaimsSubmissionDataServiceExtendedImpl implements ClaimsSubmissionDataServiceExtended {

	@Autowired
	private ClaimsSubmissionMasterDataRepoExtended claimsSubmissionDataRepository;
	@Autowired
	private SalesOrderMasterRepositoryExtended salesOrderMasterRepositoryExtended;
	@Autowired
	private SalesOrderInsuranceDetailsSearchResourceExtended salesOrderInsuranceDetailsSearchService;
	@Autowired
	private SalesOrderInsuranceDetailsServiceExtended salesOrderInsuranceDetailsServiceExtended;
	@Autowired
	private SecondaryClaimsSubmissionMasterDataRepoExtended secondaryClaimsSubmissionMasterDataRepoExtended;
	@Autowired
	private ClaimSubmissionStatusRepo claimSubmissionStatusRepository;

	@Override
	public void getClaimsSubmissionData(String soId) {
		// TODO Auto-generated method stub
		try {
			claimsSubmissionDataRepository.getClaimsSubmissionData(Long.valueOf(soId)).collectList().toFuture().get()
					.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Mono<AddSecondaryForPrimaryDTO> addSecondaryForPrimary(Long salesOrderId,
			String internalClaimControlNumber) {
		// TODO Auto-generated method stub

		return salesOrderMasterRepositoryExtended.addSecondaryForPrimary(salesOrderId, internalClaimControlNumber)
				.map(v -> {
					System.out.println(v);
					return v;
				});

	}

	@Override
	public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSecondaryPayorinSOInsurance(Long salesOrderId,
			Long secondaryPayorId, String secondaryPayorName) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		ServiceOutcome<SalesOrderInsuranceDetailsDTO> outCome = new ServiceOutcome<SalesOrderInsuranceDetailsDTO>();

		SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsSearchService
				.getSalesOrderInsuranceDetailsBySOId(salesOrderId).toFuture().get().getData();

		System.out.println("SalesOrderInsuranceDetailsDTO : " + salesOrderInsuranceDetailsDTO);

		salesOrderInsuranceDetailsDTO.setSecondaryInsurerId(secondaryPayorId);
		salesOrderInsuranceDetailsDTO.setSecondaryInsurerName(secondaryPayorName);

		SalesOrderInsuranceDetailsDTO recievedsalesOrderInsuranceDetailsDTO = salesOrderInsuranceDetailsServiceExtended
				.updateSOSecondaryInsuranceInfo(salesOrderInsuranceDetailsDTO).toFuture().get().getData();

		if (recievedsalesOrderInsuranceDetailsDTO == null) {
			outCome.setData(null);
			outCome.setMessage("Data was not Updated");
			outCome.setOutcome(false);
		} else {
			outCome.setData(recievedsalesOrderInsuranceDetailsDTO);
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);
		}

		return Mono.just(outCome);
	}

	@Override
	public Mono<ServiceOutcome<String>> updateSecondaryPayorInPatient(Long patientId, Long secondaryPayorId,
			String secondaryPayorName, String PayorLevel, Double payPercentage)
			throws ParseException, JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub

		ServiceOutcome<String> outCome = new ServiceOutcome<String>();

		PatientInsuranceParameterMicroserviceDTO patientInsuranceParameterDTO = new PatientInsuranceParameterMicroserviceDTO();
		patientInsuranceParameterDTO.setPatientID(patientId);
		patientInsuranceParameterDTO.setPayerLevel(PayorLevel);
		patientInsuranceParameterDTO.setInsuranceId(secondaryPayorId);
		patientInsuranceParameterDTO.setInsuranceName(secondaryPayorName);
		patientInsuranceParameterDTO.setPayPercentage(payPercentage);
		patientInsuranceParameterDTO.setRelationship("");

		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
        headersData.add("Content-Type", "application/json");
		HttpEntity entityData = new HttpEntity<>(patientInsuranceParameterDTO, headersData);

		ResponseEntity responseData = restTemplateData.exchange(
				"http://localhost:8080/services/patient/api/savePatientInsuranceForSecondaryClaim", HttpMethod.POST,
				entityData, String.class);
		System.out.println(responseData.getBody());
		JSONObject response = (JSONObject) parser.parse(responseData.getBody().toString());

		if (responseData.getStatusCode().equals(HttpStatus.OK)) {
			outCome.setData(response.get("data").toString());
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);
		} else {
			outCome.setData(null);
			outCome.setMessage("Data Updation Failed");
			outCome.setOutcome(false);
		}

		return Mono.just(outCome);

	}

	@Override
	public ServiceOutcome<Boolean> validateSecondaryPayor(Long salesOrderId, String SecondaryPayorId)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		ServiceOutcome<Boolean> outCome = new ServiceOutcome<Boolean>();
		SecondaryClaimSubmisionMaster SecondaryClaimSubmisionMasterList = secondaryClaimsSubmissionMasterDataRepoExtended
				.getSecondarySubmissionDataForValidation(salesOrderId).collectList().toFuture().get().get(0);

		if (SecondaryClaimSubmisionMasterList.getTradingPartnerServiceId().equalsIgnoreCase(SecondaryPayorId)) {
			outCome.setData(false);
			outCome.setOutcome(true);
			outCome.setMessage("Validation Successful");
		} else {
			outCome.setData(true);
			outCome.setOutcome(true);
			outCome.setMessage(
					"Payer in SalesOrder Insurance and Claims Submission is different, Do you wish to generate a new Claim.");
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<ClaimSubmissionStatusCustomDTO> addDifferentSecondary(Long soId, String claimControlNumber) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		ServiceOutcome<ClaimSubmissionStatusCustomDTO> outCome = new ServiceOutcome<ClaimSubmissionStatusCustomDTO>();

		ClaimSubmissionStatusCustomDTO claimSubmissionStatusCustomDTO = claimSubmissionStatusRepository.getSecondarySubmissionData(soId, claimControlNumber).toFuture().get();
		
		if(claimSubmissionStatusCustomDTO.getSalesorderid() == null) {
			outCome.setData(null);
			outCome.setMessage("Claims Data Creation Failed");
			outCome.setOutcome(false);
		}else {
			outCome.setData(claimSubmissionStatusCustomDTO);
			outCome.setMessage("Successfully Created new Secondary Claims");
			outCome.setOutcome(true);
		}

		return outCome;

	}

}
