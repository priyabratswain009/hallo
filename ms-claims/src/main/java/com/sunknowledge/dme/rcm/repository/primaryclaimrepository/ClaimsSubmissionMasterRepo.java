package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.ClaimsSubmissionMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimsSubmissionMasterRepo extends ClaimsSubmissionMasterRepository{
    @Query(value="From ClaimsSubmissionMaster")
    List<ClaimsSubmissionMaster> getPrimarySubmissionDetails();

	@Query(value="From ClaimsSubmissionMaster where salesOrderId=:salesOrderNumber and claimControlNo=:claimControlNo")
	ClaimsSubmissionMaster getPrimarySubmissionDetails(@Param("salesOrderNumber") Long salesOrderNumber,@Param("claimControlNo") String claimControlNo);

    @Query(value = "FROM ClaimsSubmissionMaster WHERE salesOrderId = :salesOrderId AND status in('Active', 'active')")
    ClaimsSubmissionMaster getHealthInsuranceClaimDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query(value = "FROM ClaimsSubmissionMaster WHERE claimControlNo = :claimControlNumber AND status in('Active', 'active')")
    ClaimsSubmissionMaster getHealthInsuranceClaimDetailsOnClaimControlNumber(@Param("claimControlNumber") String claimControlNumber);
}
