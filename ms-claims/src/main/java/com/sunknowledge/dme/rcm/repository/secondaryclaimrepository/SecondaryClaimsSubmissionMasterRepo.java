package com.sunknowledge.dme.rcm.repository.secondaryclaimrepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimsSubmissionMasterRepository;

public interface SecondaryClaimsSubmissionMasterRepo extends SecondaryClaimsSubmissionMasterRepository {

	@Query(value = "From SecondaryClaimsSubmissionMaster where salesOrderId=:salesOrderNumber")
	SecondaryClaimsSubmissionMaster getSecondarySubmissionDetails(@Param("salesOrderNumber") Long salesOrderNumber);

	@Query(value = "From SecondaryClaimsSubmissionMaster where salesOrderId=:salesOrderNumber and claimControlNo=:claimControlNo")
	SecondaryClaimsSubmissionMaster getSecondarySubmissionMasterDetails(
			@Param("salesOrderNumber") Long salesOrderNumber, @Param("claimControlNo") String claimControlNo);

    @Query("FROM SecondaryClaimsSubmissionMaster WHERE salesOrderId = :salesOrderId AND status in('Active', 'active')")
    SecondaryClaimsSubmissionMaster getHealthInsuranceClaimDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("FROM SecondaryClaimsSubmissionMaster WHERE salesOrderId = :salesOrderId AND claimControlNo = :intClaimNo AND status in('Active', 'active')")
    SecondaryClaimsSubmissionMaster getSecondaryClaimSubmissionDetailsOnInternalClaimSlNo(@Param("salesOrderId") Long salesOrderId, @Param("intClaimNo") String intClaimNo);

    @Query(value = "FROM SecondaryClaimsSubmissionMaster WHERE claimControlNo = :claimControlNumber AND status in('Active', 'active')")
    SecondaryClaimsSubmissionMaster getHealthInsuranceClaimDetailsOnClaimControlNumber(@Param("claimControlNumber") String claimControlNumber);
}
