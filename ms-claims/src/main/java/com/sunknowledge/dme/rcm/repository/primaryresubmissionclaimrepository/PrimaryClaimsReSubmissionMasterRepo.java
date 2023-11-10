package com.sunknowledge.dme.rcm.repository.primaryresubmissionclaimrepository;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimsReSubmissionMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrimaryClaimsReSubmissionMasterRepo extends PrimaryClaimsReSubmissionMasterRepository{
    @Query(value="From PrimaryClaimsReSubmissionMaster")
    List<PrimaryClaimsReSubmissionMaster> getPrimarySubmissionDetails();

	@Query(value="From PrimaryClaimsReSubmissionMaster where salesOrderId=:salesOrderNumber")
	PrimaryClaimsReSubmissionMaster getPrimarySubmissionDetails(@Param("salesOrderNumber") Long salesOrderNumber);

    @Query("FROM PrimaryClaimsReSubmissionMaster WHERE salesOrderId = :salesOrderId AND status in('active', 'Active')")
    PrimaryClaimsReSubmissionMaster getHealthInsuranceClaimDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query(value = "FROM PrimaryClaimsReSubmissionMaster WHERE claimControlNo = :claimControlNumber AND status in('Active', 'active')")
    PrimaryClaimsReSubmissionMaster getHealthInsuranceClaimDetailsOnClaimControlNumber(@Param("claimControlNumber") String claimControlNumber);
}
