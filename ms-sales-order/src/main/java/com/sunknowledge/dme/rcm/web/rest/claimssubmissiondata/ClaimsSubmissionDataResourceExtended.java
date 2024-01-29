package com.sunknowledge.dme.rcm.web.rest.claimssubmissiondata;

import java.util.concurrent.ExecutionException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.dto.claims.ClaimSubmissionStatusCustomDTO;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.ClaimsSubmissionDataServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;

import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/api")
public class ClaimsSubmissionDataResourceExtended {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	@Autowired
	private ClaimsSubmissionDataServiceExtended claimsSubmissionDataService;

	@PostMapping("/getClaimsSubmissionData")
	public void getClaimsSubmissionData(@RequestParam("salesOrderId") String salesOrderId) {
		claimsSubmissionDataService.getClaimsSubmissionData(salesOrderId);
	}

	@PostMapping("/addSecondaryForPrimary")
	public Mono<ServiceOutcome<AddSecondaryForPrimaryDTO>> addSecondaryForPrimary(
			@RequestParam("salesOrderId") Long salesOrderId,
			@RequestParam("internalClaimControlNumber") String internalClaimControlNumber) {

		return claimsSubmissionDataService.addSecondaryForPrimary(salesOrderId, internalClaimControlNumber).map(v -> {
			System.out.println(v);
			return new ServiceOutcome<AddSecondaryForPrimaryDTO>(v, true, "Data Inserted Successfully");
		}).switchIfEmpty(
				Mono.just(new ServiceOutcome<AddSecondaryForPrimaryDTO>(null, false, "Data Insertion Failed")));

	}

	@PostMapping("/updateSecondaryPayorInSO")
	public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSecondaryPayorInSO(
			@RequestParam("salesOrderId") Long salesOrderId, @RequestParam("SecondaryPayorId") Long secondaryPayorId,
			@RequestParam("SecondaryPayorName") String secondaryPayorName)
			throws InterruptedException, ExecutionException, ParseException {

		// Updating So Insurance
		ServiceOutcome<SalesOrderInsuranceDetailsDTO> SalesOrderInsuranceDetailsDTO = claimsSubmissionDataService
				.updateSecondaryPayorinSOInsurance(salesOrderId, secondaryPayorId, secondaryPayorName).toFuture().get();

		return Mono.just(SalesOrderInsuranceDetailsDTO);

	}

	@PostMapping("/updateSecondaryPayorInPatient")
	public Mono<ServiceOutcome<String>> updateSecondaryPayorInPatient(@RequestParam("patientId") Long patientId,
			@RequestParam("payPercentage") Double payPercentage,
			@RequestParam("SecondaryPayorId") Long secondaryPayorId,
			@RequestParam("SecondaryPayorName") String secondaryPayorName) throws InterruptedException,
			ExecutionException, ParseException, JsonMappingException, JsonProcessingException {

		// Updating patient Insurance
		ServiceOutcome<String> PatientInsuranceParameterMicroserviceDTO = claimsSubmissionDataService
				.updateSecondaryPayorInPatient(patientId, secondaryPayorId, secondaryPayorName, "secondary",
						payPercentage)
				.toFuture().get();

		return Mono.just(PatientInsuranceParameterMicroserviceDTO);

	}

	@GetMapping("/validateSecondaryPayerForClaimSubmission")
	public Mono<ServiceOutcome<Boolean>> validateSecondaryPayerForClaimSubmission(
			@RequestParam("salesOrderId") Long salesOrderId, @RequestParam("SecondaryPayorId") String secondaryPayorId)
			throws InterruptedException, ExecutionException {

		ServiceOutcome<Boolean> outCome = claimsSubmissionDataService.validateSecondaryPayor(salesOrderId,
				secondaryPayorId);

		return Mono.just(outCome);
	}

	@PostMapping("/addDifferentSecondary")
	public Mono<ServiceOutcome<ClaimSubmissionStatusCustomDTO>> addDifferentSecondary(
			@RequestParam("salesOrderId") Long salesOrderId,
			@RequestParam("claimControlNumber") String claimControlNumber)
			throws InterruptedException, ExecutionException {
		return Mono.just(claimsSubmissionDataService.addDifferentSecondary(salesOrderId, claimControlNumber));
	}

}
