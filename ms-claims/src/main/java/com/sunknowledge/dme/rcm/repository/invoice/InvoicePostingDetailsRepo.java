package com.sunknowledge.dme.rcm.repository.invoice;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import com.sunknowledge.dme.rcm.pojo.invoice.PostingAutoAdjustmentSettingsProjection;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoicePostingDetailsRepo extends InvoicePostingDetailsRepository {
    @Query("FROM InvoicePostingDetails WHERE hcpcsCode = :hcpcsCode AND invoiceNo = :invoiceNo AND postStatus = :postStatus AND status = :status")
    List<InvoicePostingDetails> getInvoiceClaimPostingOnItemInvoiceNoAndPostStatus(@Param("hcpcsCode") String hcpcsCode,
                                                                                   @Param("invoiceNo") String invoiceNo,
                                                                                   @Param("postStatus") String postStatus,
                                                                                   @Param("status") String status);

    @Query(value = "SELECT * FROM f_claims_get_posting_no()", nativeQuery = true)
    String getPostingNumber();

    @Query(value = "select adjustment_settings_id as adjustmentSettingsId, branch_id as branchId, branch_no as branchNo, adjustment_amount as adjustmentAmount, status as status " +
        "from masterdata.mv_t_posting_auto_adjustment_settings where branch_id = :branchId", nativeQuery = true)
    List<PostingAutoAdjustmentSettingsProjection> getPostingAutoAdjustmentSettingsByBranch(@Param("branchId") Long branchId);

    @Query(value = "select distinct hcpcs_code from t_invoice_posting_details where invoice_no = :invoiceNo", nativeQuery = true)
    List<String> getItemListOnInvoiceNo(@Param("invoiceNo") String invoiceNo);

    @Query("FROM InvoicePostingDetails WHERE hcpcsCode = :hcpcsCode AND invoiceNo = :invoiceNo AND status = :status")
    List<InvoicePostingDetails> getInvoiceClaimPostingOnItemInvoiceNo(@Param("hcpcsCode") String hcpcsCode,
                                                                                   @Param("invoiceNo") String invoiceNo,
                                                                                   @Param("status") String status);

    @Query(value = "select distinct deposit_id from t_invoice_posting_details where hcpcs_code = :hcpcsCode and invoice_no = :invoiceNo " +
        "and post_status = :postStatus and status = :status", nativeQuery = true)
    Long getDepositeOnInvoiceNoAndHcpcsCode(@Param("hcpcsCode") String hcpcsCode, @Param("invoiceNo") String invoiceNo,
                                           @Param("postStatus") String postStatus, @Param("status") String status);

    @Query(value = "select sum(post_amount) from t_invoice_posting_details where deposit_id = :depositId and post_type = 'Payment'", nativeQuery = true)
    Double getTotalPostedPaymentAmountOnDeposit(@Param("depositId") Long depositId);
}
