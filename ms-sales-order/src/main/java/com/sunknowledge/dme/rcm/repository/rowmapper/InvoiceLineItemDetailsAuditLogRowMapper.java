package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link InvoiceLineItemDetailsAuditLog}, with proper type conversions.
 */
@Service
public class InvoiceLineItemDetailsAuditLogRowMapper implements BiFunction<Row, String, InvoiceLineItemDetailsAuditLog> {

    private final ColumnConverter converter;

    public InvoiceLineItemDetailsAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link InvoiceLineItemDetailsAuditLog} stored in the database.
     */
    @Override
    public InvoiceLineItemDetailsAuditLog apply(Row row, String prefix) {
        InvoiceLineItemDetailsAuditLog entity = new InvoiceLineItemDetailsAuditLog();
        entity.setInvceLinItmDetilId(converter.fromRow(row, prefix + "_invce_lin_itm_detil_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        return entity;
    }
}
