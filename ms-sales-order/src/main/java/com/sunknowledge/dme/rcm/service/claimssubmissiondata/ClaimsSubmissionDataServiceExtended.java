package com.sunknowledge.dme.rcm.service.claimssubmissiondata;

import java.util.concurrent.ExecutionException;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.dto.claims.ClaimSubmissionStatusCustomDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;

import reactor.core.publisher.Mono;

public interface ClaimsSubmissionDataServiceExtended {

	void getClaimsSubmissionData(String soId);

	Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSecondaryPayorinSOInsurance(Long salesOrderId, Long secondaryPayorId, String secondaryPayorName)
			throws InterruptedException, ExecutionException;

	Mono<ServiceOutcome<String>> updateSecondaryPayorInPatient(Long patientId, Long secondaryPayorId, String secondaryPayorName,
			String payorLevel, Double payPercentage) throws ParseException, JsonMappingException, JsonProcessingException;

	ServiceOutcome<Boolean> validateSecondaryPayor(Long salesOrderId, String SecondaryPayorId)
			throws InterruptedException, ExecutionException;

	ServiceOutcome<ClaimSubmissionStatusCustomDTO> addDifferentSecondary(Long soId, String claimControlNumber) throws InterruptedException, ExecutionException;

	Mono<AddSecondaryForPrimaryDTO> addSecondaryForPrimary(Long salesOrderId, String internalClaimControlNumber);
}
