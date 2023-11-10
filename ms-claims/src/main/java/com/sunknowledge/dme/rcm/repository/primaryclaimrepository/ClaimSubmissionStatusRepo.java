package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.pojo.invoice.InvoiceDetailsProjection;
import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the ClaimSubmissionStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimSubmissionStatusRepo extends ClaimSubmissionStatusRepository {

	@Query(value = "From ClaimSubmissionStatus")
	List<ClaimSubmissionStatus> getAllClaimSubmissionStatus();

	@Query(value = "From ClaimSubmissionStatus where readyForSubmissionStatus='Y' and (submissionStatus is null or submissionStatus='') and lower(status)='active' order by claimStatusId")
	List<ClaimSubmissionStatus> getAllSalesOrderForClaimSubmission();

	@Query(value = "From ClaimSubmissionStatus where salesOrderId=:salesOrderId and payorLevel=:payorLevel and (submissionStatus is null or submissionStatus='')")
	ClaimSubmissionStatus getClaimSubmissionValidation(@Param("salesOrderId") Long salesOrderId,@Param("payorLevel") String payorLevel);

	@Query(value = "From ClaimSubmissionStatus where intClaimNo = :intClaimNo")
	ClaimSubmissionStatus getClaimSubmissionStatus(@Param("intClaimNo") String intClaimNo);

//    @Query("FROM ClaimSubmissionStatus WHERE payorClaimControlNo = :payerClaimControlNumber OR patientAccountNo = :patientAccountNo")
    @Query(value = "select * from t_claim_submission_status tcss where tcss.payor_claim_control_no = :payerClaimControlNumber or tcss.patient_account_no = :patientAccountNo", nativeQuery = true)
    ClaimSubmissionStatus getClaimSubmissionStatusByPayorClaimControlNumber(@Param("payerClaimControlNumber") String payerClaimControlNumber, @Param("patientAccountNo") String patientAccountNo);

    @Query("FROM ClaimSubmissionStatus WHERE intClaimNo = :patientAccountNumber")
    ClaimSubmissionStatus getClaimSubmissionStatusByPatientControlNumberNPatientMemberId(@Param("patientAccountNumber") String patientAccountNumber);

	@Query(value = "From ClaimSubmissionStatus where submissionStatus IS null")
	List<ClaimSubmissionStatus> getAllClaimsForSubmission();

    @Query("FROM ClaimSubmissionStatus WHERE salesOrderId = :salesOrderId and intClaimNo =:intClaimNo and payorLevel='S' and status='Active'")
    ClaimSubmissionStatus validateSubmissionStatus(@Param("salesOrderId") Long salesOrderId, @Param("intClaimNo") String intClaimNo);

    @Query("FROM ClaimSubmissionStatus where salesOrderNo = :salesOrderNo and payorLevel='P' and periodNo=:periodNo and createdDate=(select min(createdDate) from ClaimSubmissionStatus where salesOrderNo=:salesOrderNo and payorLevel='P' and periodNo=:periodNo)")
    ClaimSubmissionStatus getOriginalClaimControlNumber(@Param("salesOrderNo") String salesOrderNo, @Param("periodNo") String periodNo);

    @Query("FROM ClaimSubmissionStatus WHERE intClaimNo = :claimControlNo")
    ClaimSubmissionStatus getClaimSubmissionStatusByInternalClaimControlNumber(@Param("claimControlNo") String claimControlNo);

    @Query(value = "select sales_order_no as salesorderNo, int_claim_no as intClaimNo, period_no as periodNo, " +
        "invoice_no as invoiceNo, invoice_id as invoiceId from claims.t_claim_submission_status where sales_order_no = :salesOrderNo order by period_no", nativeQuery = true)
    List<InvoiceDetailsProjection> getInvoiceListOnSalesOrder(@Param("salesOrderNo") String salesOrderNo);
}
