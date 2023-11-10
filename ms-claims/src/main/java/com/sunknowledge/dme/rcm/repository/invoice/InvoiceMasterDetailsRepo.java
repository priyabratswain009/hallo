package com.sunknowledge.dme.rcm.repository.invoice;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import com.sunknowledge.dme.rcm.pojo.invoice.ClaimedInvoiceDetailsProjection;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceMasterDetailsRepo extends InvoiceMasterDetailsRepository {
    @Query(value = "select * from t_invoice_master_details where sales_order_id = :salesOrderId and invoice_no = :invoice_no", nativeQuery = true)
    InvoiceMasterDetails getInvoiceMasterDetailsOnSalesOrderAndInvoiceNo(@Param("salesOrderId") Long salesOrderId, @Param("invoice_no") String invoice_no);

    @Query("FROM InvoiceMasterDetails WHERE salesOrderId = :salesOrderId")
    List<InvoiceMasterDetails> getInvoiceMasterDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("FROM InvoiceMasterDetails WHERE salesOrderNo = :salesOrderNo AND status = 'Active'")
    List<InvoiceMasterDetails> getInvoiceListOnSalesOrderNo(@Param("salesOrderNo") String salesOrderNo);

    @Query(value = "select timd.invoice_id as invoiceId, timd.invoice_no as invoiceNo, to_char(timd.invoice_date, 'MM/DD/YYYY') as invoiceDate, timd.invoice_to_entity as invoiceToEntity,\n" +
        "timd.invoice_to_payor_name as invoiceToPayerName, timd.sales_order_id as salesOrderId, timd.sales_order_no as salesOrderNo,\n" +
        "concat(timd.patient_first_name,' ',timd.patient_middle_name,' ',timd.patient_last_name) as patientName, \n" +
        "timd.item_qty_total as itemQtyTotal, timd.charged_amount_total as chargedAmountTotal, timd.allow_amount_total as allowAmountTotal, cast(timd.invoice_master_details_uuid as varchar) as invoiceMasterDetailsUuid,\n" +
        "tcss.payor_level as payorLevel, tcss.payor_id_no as payorIdNo, tcss.int_claim_no as intClaimNo, tcss.payor_claim_control_no as payorClaimControlNo,\n" +
        "tcss.patient_id_no as patientIdNo, tcss.payor as payor, tcss.claim_submission_date as claimSubmissionDate, tcss.submission_status as submissionStatus,\n" +
        "tcss.submission_note as submissionNote, tcss.response_status as responseStatus, tcss.response_status_notes as responseStatusNotes, \n" +
        "to_char(tcss.response_status_date, 'MM/DD/YYYY') as responseStatusDate, tcss.period_no as periodNo\n" +
        "from t_invoice_master_details timd \n" +
        "left join t_claim_submission_status tcss on timd.sales_order_id = tcss.sales_order_id  \n" +
        "where timd.sales_order_no = :salesOrderNo and timd.status = 'Active' and tcss.status = 'Active'", nativeQuery = true)
    List<ClaimedInvoiceDetailsProjection> getClaimedInvoiceDetailsOnSalesOrder(@Param("salesOrderNo") String salesOrderNo);

    @Query(value = "select * from t_invoice_master_details where sales_order_id = :salesOrderId and claim_control_no = :claimControlNumber", nativeQuery = true)
    InvoiceMasterDetails getInvoiceMasterDetailsOnSalesOrderAndClaimControlNumber(@Param("salesOrderId") Long salesOrderId, @Param("claimControlNumber") String claimControlNumber);

    @Query(value = "select timd.invoice_id as invoiceId, timd.invoice_no as invoiceNo, to_char(timd.invoice_date, 'MM/DD/YYYY') as invoiceDate, timd.invoice_to_entity as invoiceToEntity,\n" +
        "timd.invoice_to_payor_name as invoiceToPayerName, timd.sales_order_id as salesOrderId, timd.sales_order_no as salesOrderNo,\n" +
        "concat(timd.patient_first_name,' ',timd.patient_middle_name,' ',timd.patient_last_name) as patientName, \n" +
        "timd.item_qty_total as itemQtyTotal, timd.charged_amount_total as chargedAmountTotal, timd.allow_amount_total as allowAmountTotal, cast(timd.invoice_master_details_uuid as varchar) as invoiceMasterDetailsUuid\n" +
        "from t_invoice_master_details timd\n" +
        "where timd.status = 'Active' and timd.invoice_id = :invoiceId", nativeQuery = true)
    List<ClaimedInvoiceDetailsProjection> getInvoiceDetailsOnInvoice(@Param("invoiceId") Long invoiceId);
}
