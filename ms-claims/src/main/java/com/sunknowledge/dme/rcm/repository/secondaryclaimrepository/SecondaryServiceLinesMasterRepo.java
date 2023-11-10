package com.sunknowledge.dme.rcm.repository.secondaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster;
import com.sunknowledge.dme.rcm.pojo.claimreports.submissionstatus.LineAdjustmentProjection;
import com.sunknowledge.dme.rcm.repository.SecondaryServiceLinesMasterRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SecondaryServiceLinesMasterRepo extends SecondaryServiceLinesMasterRepository {

	@Query(value="From SecondaryServiceLinesMaster WHERE changeHealthSecondarySubmisionMasterId = :changeHealthSecondarySubmisionMasterId")
	List<SecondaryServiceLinesMaster> getSecondarySubmissionServiceDetails(@Param("changeHealthSecondarySubmisionMasterId") Long changeHealthSecondarySubmisionMasterId);

    @Query("FROM SecondaryServiceLinesMaster WHERE changeHealthSecondarySubmisionMasterId = :changeHealthSecondarySubmisionMasterId")
    List<SecondaryServiceLinesMaster> getServiceLineMasterDataOnClaimSubmission(@Param("changeHealthSecondarySubmisionMasterId") Long changeHealthSecondarySubmisionMasterId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL claims.claims_secondary_servicelines_updation(:salesOrderId)", nativeQuery = true)
    void updateServiceLineMasterDataOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query(value = "SELECT adjudicatedprocedurecode, providerpaymentamount, payerclaimcontrolnumber, co, pr, cr, oa, pi FROM claims.get_claim_details_v1(:salesOrderId, :claimControlNumber)", nativeQuery = true)
    List<LineAdjustmentProjection> getServiceLineMasterDataOnSalesOrder(@Param("salesOrderId") Long salesOrderId, @Param("claimControlNumber") String claimControlNumber);

    @Query("FROM SecondaryServiceLinesMaster WHERE changeHealthSecondarySubmisionMasterId = :changeHealthSecondarySubmissionMasterId AND procCode = :procCode")
    SecondaryServiceLinesMaster getServiceLineMasterDataOnSubmissionMasterNProcCode(@Param("changeHealthSecondarySubmissionMasterId") Long changeHealthSecondarySubmissionMasterId,
                                                                                 @Param("procCode") String procCode);
}
