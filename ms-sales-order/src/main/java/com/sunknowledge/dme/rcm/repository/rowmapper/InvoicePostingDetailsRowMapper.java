package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link InvoicePostingDetails}, with proper type conversions.
 */
@Service
public class InvoicePostingDetailsRowMapper implements BiFunction<Row, String, InvoicePostingDetails> {

    private final ColumnConverter converter;

    public InvoicePostingDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link InvoicePostingDetails} stored in the database.
     */
    @Override
    public InvoicePostingDetails apply(Row row, String prefix) {
        InvoicePostingDetails entity = new InvoicePostingDetails();
        entity.setInvoiceLineItemPostingId(converter.fromRow(row, prefix + "_invoice_line_item_posting_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setPostingDate(converter.fromRow(row, prefix + "_posting_date", LocalDate.class));
        entity.setPostedById(converter.fromRow(row, prefix + "_posted_by_id", Long.class));
        entity.setPostedByName(converter.fromRow(row, prefix + "_posted_by_name", String.class));
        entity.setPostingComment(converter.fromRow(row, prefix + "_posting_comment", String.class));
        entity.setPostType(converter.fromRow(row, prefix + "_post_type", String.class));
        entity.setDepositId(converter.fromRow(row, prefix + "_deposit_id", String.class));
        entity.setReceiptId(converter.fromRow(row, prefix + "_receipt_id", String.class));
        entity.setPostAmount(converter.fromRow(row, prefix + "_post_amount", Double.class));
        entity.setPostStatus(converter.fromRow(row, prefix + "_post_status", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setInvoicePostingDetailsUuid(converter.fromRow(row, prefix + "_invoice_posting_details_uuid", UUID.class));
        entity.setInvoiceNo(converter.fromRow(row, prefix + "_invoice_no", String.class));
        entity.setInvoiceDate(converter.fromRow(row, prefix + "_invoice_date", LocalDate.class));
        entity.setInvoiceLineItemDetailsId(converter.fromRow(row, prefix + "_invoice_line_item_details_id", Long.class));
        return entity;
    }
}
