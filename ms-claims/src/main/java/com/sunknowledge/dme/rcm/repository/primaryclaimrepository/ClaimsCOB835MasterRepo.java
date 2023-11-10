package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.ClaimsCOB835CrossoverProjection;
import com.sunknowledge.dme.rcm.pojo.invoice.UnprocessedCOBClaimsDetailProjection;
import com.sunknowledge.dme.rcm.repository.ClaimsCOB835MasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the ClaimsCOB835Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsCOB835MasterRepo extends ClaimsCOB835MasterRepository {
    @Query("FROM ClaimsCOB835Master WHERE patientControlNumber = :controlNumber")
    ClaimsCOB835Master getClaimTransactionMasterOnClaimControlNumber(@Param("controlNumber") String controlNumber);

    @Query(value = "select tccm.* from t_claims_cob_835_master tccm, t_claim_submission_status tcss \n" +
        "where tccm.patient_control_number = tcss.patient_account_no and tccm.claim_cob_835_master_id = tcss.claim_cob_835_master_id \n" +
        "and tccm.patient_control_number = :controlNumber", nativeQuery = true)
    ClaimsCOB835Master getClaimCOB335AndClaimStatusOnClaimControlNumber(@Param("controlNumber") String controlNumber);

    @Query(value = "select tccm.file_name from t_claims_cob_835_master tccm, t_claim_835_277_exception tce \n" +
        "where tccm.claim_cob_835_master_id = tce.claim_cob_835_master_id \n" +
        "and tccm.cob_type = 'E' and tccm.status = 'Active' and tce.status = 'Active' and tce.is_835 = true\n" +
        "group by tccm.file_name", nativeQuery = true)
    List<String> getActiveUnprocessedFileNameListOnException();

    @Query(value = "select tccm.file_name as fileName, tccm.check_or_eft_trace_number as checkOrEftTraceNumber, to_char(tccm.check_issue_or_eft_effective_date, 'MM/DD/YYYY') as checkIssueOrEftEffectiveDate, \n" +
        "tccm.payer_name as payerName, tccm.total_claim_charge_amount as totalClaimChargeAmount, tccm.total_claim_payment_amount as totalClaimPaymentAmount, \n" +
        "tccm.total_patient_responsibility_amount as totalPatientResponsibilityAmount, tccm.claim_cob_835_master_id as claimCob835MasterId, \n" +
        "tccm.patient_control_number as patientControlNumber, tccm.payer_claim_control_number as payerClaimControlNumber, concat(tccm.patient_first_name, ' ',tccm.patient_last_name) as patientName\n" +
        "from t_claims_cob_835_master tccm, t_claim_835_277_exception tce \n" +
        "where tccm.claim_cob_835_master_id = tce.claim_cob_835_master_id \n" +
        "and tccm.cob_type = 'E' and tccm.status = 'Active' and tce.status = 'Active' and tce.is_835 = true\n" +
        "and tccm.file_name = :fileName", nativeQuery = true)
    List<UnprocessedCOBClaimsDetailProjection> getActiveUnprocessedFileNameListOnExceptionFileName(@Param("fileName") String fileName);

    @Query("FROM ClaimsCOB835Master WHERE checkOrEFTTraceNumber = :checkOrEFTTraceNumber AND checkIssueOrEFTEffectiveDate = :checkIssueOrEFTEffectiveDate")
    List<ClaimsCOB835Master> getClaimTransactionMasterOnEFTTraceNumberAndDate(@Param("checkOrEFTTraceNumber") String checkOrEFTTraceNumber,
                                                                              @Param("checkIssueOrEFTEffectiveDate") LocalDate checkIssueOrEFTEffectiveDate);

    @Query(value = "select tccm.* from t_claims_cob_835_master tccm, t_claim_835_277_exception tce where tccm.claim_cob_835_master_id = tce.claim_cob_835_master_id \n" +
        "and tccm.file_name = :fileName and tce.claim_cob_835_master_id = :claimCob835MasterId", nativeQuery = true)
    ClaimsCOB835Master getClaimTransactionMasterOnClaimCOBAndFileName(@Param("claimCob835MasterId") Long claimCob835MasterId,
                                                                              @Param("fileName") String fileName);

    @Query(value = "select claim_cob_835_master_id as claimCob835MasterId, patient_control_number as patientControlNumber, \n" +
        "payer_claim_control_number as payerClaimControlNumber, crossover_carrier_name as crossoverCarrierName,\n" +
        "crossover_carrier_payer_id as crossoverCarrierPayerId, crossover_carrier_payer_name as crossoverCarrierPayerName \n" +
        "from mv_t_claims_cob_835_master mtccm where mtccm.crossover_carrier_name = true and mtccm.claim_cob_835_master_id not in(select tccp.claim_cob_835_master_id from t_claims_835_crossover_processed tccp)\n" +
        "and mtccm.claim_cob_835_master_id not in(select tcce.claim_cob_835_master_id from t_claims_835_crossover_exception tcce)", nativeQuery = true)
    List<ClaimsCOB835CrossoverProjection> getClaimsCOB835CrossoverProjectionDetails();
}
