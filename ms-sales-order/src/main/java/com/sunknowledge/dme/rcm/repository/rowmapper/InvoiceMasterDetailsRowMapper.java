package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link InvoiceMasterDetails}, with proper type conversions.
 */
@Service
public class InvoiceMasterDetailsRowMapper implements BiFunction<Row, String, InvoiceMasterDetails> {

    private final ColumnConverter converter;

    public InvoiceMasterDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link InvoiceMasterDetails} stored in the database.
     */
    @Override
    public InvoiceMasterDetails apply(Row row, String prefix) {
        InvoiceMasterDetails entity = new InvoiceMasterDetails();
        entity.setInvoiceId(converter.fromRow(row, prefix + "_invoice_id", Long.class));
        entity.setInvoiceNo(converter.fromRow(row, prefix + "_invoice_no", String.class));
        entity.setInvoiceDate(converter.fromRow(row, prefix + "_invoice_date", LocalDate.class));
        entity.setInvoiceToEntity(converter.fromRow(row, prefix + "_invoice_to_entity", String.class));
        entity.setInvoiceToPayorId(converter.fromRow(row, prefix + "_invoice_to_payor_id", Long.class));
        entity.setInvoiceToPayorName(converter.fromRow(row, prefix + "_invoice_to_payor_name", String.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setSalesOrderNo(converter.fromRow(row, prefix + "_sales_order_no", String.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setItemQtyTotal(converter.fromRow(row, prefix + "_item_qty_total", Long.class));
        entity.setChargedAmountTotal(converter.fromRow(row, prefix + "_charged_amount_total", Double.class));
        entity.setAllowAmountTotal(converter.fromRow(row, prefix + "_allow_amount_total", Double.class));
        entity.setPaymentAmountTotal(converter.fromRow(row, prefix + "_payment_amount_total", Double.class));
        entity.setTaxAmountTotal(converter.fromRow(row, prefix + "_tax_amount_total", Double.class));
        entity.setAdjAmountTotal(converter.fromRow(row, prefix + "_adj_amount_total", Double.class));
        entity.setBalanceAmountTotal(converter.fromRow(row, prefix + "_balance_amount_total", Double.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setInvoiceMasterDetailsUuid(converter.fromRow(row, prefix + "_invoice_master_details_uuid", UUID.class));
        entity.setInvoiceStatus(converter.fromRow(row, prefix + "_invoice_status", String.class));
        entity.setPrimarySubmissionMasterId(converter.fromRow(row, prefix + "_primary_submission_master_id", Long.class));
        entity.setClaimControlNo(converter.fromRow(row, prefix + "_claim_control_no", String.class));
        return entity;
    }
}
