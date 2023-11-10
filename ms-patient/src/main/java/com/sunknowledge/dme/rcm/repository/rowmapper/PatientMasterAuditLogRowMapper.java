package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientMasterAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientMasterAuditLog}, with proper type conversions.
 */
@Service
public class PatientMasterAuditLogRowMapper implements BiFunction<Row, String, PatientMasterAuditLog> {

    private final ColumnConverter converter;

    public PatientMasterAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientMasterAuditLog} stored in the database.
     */
    @Override
    public PatientMasterAuditLog apply(Row row, String prefix) {
        PatientMasterAuditLog entity = new PatientMasterAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPatintId(converter.fromRow(row, prefix + "_patint_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
