package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientInsVerifStatAuditLog}, with proper type conversions.
 */
@Service
public class PatientInsVerifStatAuditLogRowMapper implements BiFunction<Row, String, PatientInsVerifStatAuditLog> {

    private final ColumnConverter converter;

    public PatientInsVerifStatAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientInsVerifStatAuditLog} stored in the database.
     */
    @Override
    public PatientInsVerifStatAuditLog apply(Row row, String prefix) {
        PatientInsVerifStatAuditLog entity = new PatientInsVerifStatAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setInsrnceVrifId(converter.fromRow(row, prefix + "_insrnce_vrif_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
