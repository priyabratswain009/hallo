package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosisAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDiagnosisAuditLog}, with proper type conversions.
 */
@Service
public class PatientDiagnosisAuditLogRowMapper implements BiFunction<Row, String, PatientDiagnosisAuditLog> {

    private final ColumnConverter converter;

    public PatientDiagnosisAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDiagnosisAuditLog} stored in the database.
     */
    @Override
    public PatientDiagnosisAuditLog apply(Row row, String prefix) {
        PatientDiagnosisAuditLog entity = new PatientDiagnosisAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPatintDiagoisId(converter.fromRow(row, prefix + "_patint_diagois_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
