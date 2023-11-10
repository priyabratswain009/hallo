package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDoctorMapAuditLog}, with proper type conversions.
 */
@Service
public class PatientDoctorMapAuditLogRowMapper implements BiFunction<Row, String, PatientDoctorMapAuditLog> {

    private final ColumnConverter converter;

    public PatientDoctorMapAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDoctorMapAuditLog} stored in the database.
     */
    @Override
    public PatientDoctorMapAuditLog apply(Row row, String prefix) {
        PatientDoctorMapAuditLog entity = new PatientDoctorMapAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPaentDctorMapId(converter.fromRow(row, prefix + "_paent_dctor_map_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
