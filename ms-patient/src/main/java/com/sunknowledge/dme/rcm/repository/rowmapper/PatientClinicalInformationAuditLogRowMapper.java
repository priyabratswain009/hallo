package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientClinicalInformationAuditLog}, with proper type conversions.
 */
@Service
public class PatientClinicalInformationAuditLogRowMapper implements BiFunction<Row, String, PatientClinicalInformationAuditLog> {

    private final ColumnConverter converter;

    public PatientClinicalInformationAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientClinicalInformationAuditLog} stored in the database.
     */
    @Override
    public PatientClinicalInformationAuditLog apply(Row row, String prefix) {
        PatientClinicalInformationAuditLog entity = new PatientClinicalInformationAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPatntCliicalInfoationId(converter.fromRow(row, prefix + "_patnt_cliical_infoation_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
