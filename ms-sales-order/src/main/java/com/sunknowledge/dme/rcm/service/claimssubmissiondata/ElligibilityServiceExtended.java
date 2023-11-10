package com.sunknowledge.dme.rcm.service.claimssubmissiondata;

import java.util.concurrent.ExecutionException;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.elligibility.EligibilityInput;
import com.sunknowledge.dme.rcm.domain.elligibility.ResultElligibilityOutcome;
import com.sunknowledge.dme.rcm.domain.elligibility.ServiceOutcome;

import reactor.core.publisher.Mono;

public interface ElligibilityServiceExtended {

	public ServiceOutcome<ResultElligibilityOutcome> medicalEligibiltycheck(String soId);

	public ServiceOutcome<ResultElligibilityOutcome> eligibiltyHealthcheck(String token);

	public ServiceOutcome<ResultElligibilityOutcome> elligiblityCheck(String accessToken,
			EligibilityInput eligibilityInput);

	public ServiceOutcome<MemberElligibilityMaster> insertMemberPrimaryElligibilityData(Long soId)
			throws InterruptedException, ExecutionException;

	public ServiceOutcome<MemberElligibilityMaster> insertMemberSecondaryElligibilityData(Long soId)
			throws InterruptedException, ExecutionException;

	public Mono<Boolean> validateClaimSubmissionData(Long soId) throws InterruptedException, ExecutionException;

	public Mono<SalesOrderMaster> createDuplicateSalesOrder(Long soId) throws InterruptedException, ExecutionException;

	public Mono<Boolean> rentalHelperPeriodGreaterThanOne(Long salesOrderId, String periodNo,
			Long previousSoId);

	public Mono<SalesOrderMaster> updateOrderStatus(Long salesOrderId);

	public Mono<SalesOrderMaster> saveSalesOrder(SalesOrderMaster salesOrderMaster) throws InterruptedException, ExecutionException;
}
