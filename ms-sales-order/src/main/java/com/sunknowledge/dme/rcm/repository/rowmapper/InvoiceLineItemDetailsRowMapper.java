package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link InvoiceLineItemDetails}, with proper type conversions.
 */
@Service
public class InvoiceLineItemDetailsRowMapper implements BiFunction<Row, String, InvoiceLineItemDetails> {

    private final ColumnConverter converter;

    public InvoiceLineItemDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link InvoiceLineItemDetails} stored in the database.
     */
    @Override
    public InvoiceLineItemDetails apply(Row row, String prefix) {
        InvoiceLineItemDetails entity = new InvoiceLineItemDetails();
        entity.setInvoiceLineItemDetailsId(converter.fromRow(row, prefix + "_invoice_line_item_details_id", Long.class));
        entity.setPrimaryInvoiceNo(converter.fromRow(row, prefix + "_primary_invoice_no", String.class));
        entity.setInvoiceDate(converter.fromRow(row, prefix + "_invoice_date", LocalDate.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemQty(converter.fromRow(row, prefix + "_item_qty", Long.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setHcpcsCode(converter.fromRow(row, prefix + "_hcpcs_code", String.class));
        entity.setChargedAmount(converter.fromRow(row, prefix + "_charged_amount", Long.class));
        entity.setAllowAmount(converter.fromRow(row, prefix + "_allow_amount", Double.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setInvoiceLineItemDetailsUuid(converter.fromRow(row, prefix + "_invoice_line_item_details_uuid", UUID.class));
        entity.setSecondaryInvoiceNo(converter.fromRow(row, prefix + "_secondary_invoice_no", String.class));
        entity.setTertiaryInvoiceNo(converter.fromRow(row, prefix + "_tertiary_invoice_no", String.class));
        entity.setPatientInvoiceNo(converter.fromRow(row, prefix + "_patient_invoice_no", String.class));
        entity.setPrimaryInvoiceId(converter.fromRow(row, prefix + "_primary_invoice_id", Long.class));
        entity.setSecondaryInvoiceId(converter.fromRow(row, prefix + "_secondary_invoice_id", Long.class));
        entity.setTertiaryInvoiceId(converter.fromRow(row, prefix + "_tertiary_invoice_id", Long.class));
        entity.setPatientInvoiceId(converter.fromRow(row, prefix + "_patient_invoice_id", Long.class));
        return entity;
    }
}
