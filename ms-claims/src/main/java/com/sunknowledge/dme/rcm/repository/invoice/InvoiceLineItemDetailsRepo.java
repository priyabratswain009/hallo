package com.sunknowledge.dme.rcm.repository.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsRepository;

public interface InvoiceLineItemDetailsRepo extends InvoiceLineItemDetailsRepository {
	
	@Query(value = "select * from t_invoice_line_item_details where primary_invoice_no = :primaryInvoiceNo", nativeQuery = true)
	List<InvoiceLineItemDetails> getPrimaryInvoiceLineItemDetailsOnPrimaryInvoiceNo(@Param("primaryInvoiceNo") String primaryInvoiceNo);
	
    @Query(value = "select * from t_invoice_line_item_details where primary_invoice_no = :invoiceNo and hcpcs_code = :procCode", nativeQuery = true)
    InvoiceLineItemDetails getPrimaryInvoiceLineItemDetailsOnInvoiceNoAndProcCode(@Param("invoiceNo") String invoiceNo, @Param("procCode") String procCode);

    @Query(value = "select * from t_invoice_line_item_details where secondary_invoice_no = :invoiceNo and hcpcs_code = :procCode", nativeQuery = true)
    InvoiceLineItemDetails getSecondaryInvoiceLineItemDetailsOnInvoiceNoAndProcCode(@Param("invoiceNo") String invoiceNo, @Param("procCode") String procCode);

    @Query(value = "select * from t_invoice_line_item_details where tertiary_invoice_no = :invoiceNo and hcpcs_code = :procCode", nativeQuery = true)
    InvoiceLineItemDetails getTertiaryInvoiceLineItemDetailsOnInvoiceNoAndProcCode(@Param("invoiceNo") String invoiceNo, @Param("procCode") String procCode);

    @Query(value = "select * from t_invoice_line_item_details where patient_invoice_no = :invoiceNo and hcpcs_code = :procCode", nativeQuery = true)
    InvoiceLineItemDetails getPatientInvoiceLineItemDetailsOnInvoiceNoAndProcCode(@Param("invoiceNo") String invoiceNo, @Param("procCode") String procCode);

    @Query(value = "select * from t_invoice_line_item_details where primary_invoice_no = :invoiceNo and hcpcs_code = :procCode", nativeQuery = true)
    InvoiceLineItemDetails getInvoiceLineItemDetailsOnPrimaryInvoiceNoAndProcCode(@Param("invoiceNo") String invoiceNo, @Param("procCode") String procCode);

    @Query(value = "select * from t_invoice_line_item_details where secondary_invoice_no = :invoiceNo and hcpcs_code = :procCode", nativeQuery = true)
    InvoiceLineItemDetails getInvoiceLineItemDetailsOnSecondaryInvoiceNoAndProcCode(@Param("invoiceNo") String invoiceNo, @Param("procCode") String procCode);
}
