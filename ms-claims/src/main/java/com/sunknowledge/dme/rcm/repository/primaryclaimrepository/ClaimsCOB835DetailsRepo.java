package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details;
import com.sunknowledge.dme.rcm.repository.ClaimsCOB835DetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the ClaimsCOB835Details entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsCOB835DetailsRepo extends ClaimsCOB835DetailsRepository {
    @Query(value = "SELECT * FROM t_claims_cob_835_details WHERE control_number = :controlNumber", nativeQuery = true)
    List<ClaimsCOB835Details> getAllClaimTransactionDetailsByControlNumber(@Param("controlNumber") String controlNumber);

    @Query(value = "SELECT * FROM t_claims_cob_835_details WHERE claim_cob_835_master_id = :claimCob835MasterId AND adjudicated_procedure_code = :procCode", nativeQuery = true)
    ClaimsCOB835Details getClaimCOB835DetailsOnClaimCOB835MasterAndProcCode(@Param("claimCob835MasterId") Long claimCob835MasterId, @Param("procCode") String procCode);

    @Query(value = "select tccd.* from t_claims_cob_835_master tccm, t_claims_cob_835_details tccd \n" +
        "where tccm.claim_cob_835_master_id = tccd.claim_cob_835_master_id and tccm.patient_control_number = :patientControlNo and tccd.adjudicated_procedure_code = :procCode", nativeQuery = true)
    ClaimsCOB835Details getClaimCOB835DetailsOnPatientControlNumberAndProcCode(@Param("patientControlNo") String patientControlNo, @Param("procCode") String procCode);

    @Query("FROM ClaimsCOB835Details WHERE status = :status AND (postStatus is null OR postStatus = '')")
    List<ClaimsCOB835Details> getAllActiveClaimCOB835DetailsWithNotPosted(@Param("status") String status);
}
