package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosis;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDiagnosis}, with proper type conversions.
 */
@Service
public class PatientDiagnosisRowMapper implements BiFunction<Row, String, PatientDiagnosis> {

    private final ColumnConverter converter;

    public PatientDiagnosisRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDiagnosis} stored in the database.
     */
    @Override
    public PatientDiagnosis apply(Row row, String prefix) {
        PatientDiagnosis entity = new PatientDiagnosis();
        entity.setPatientDiagnosisId(converter.fromRow(row, prefix + "_patient_diagnosis_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setDiagnosisCodeType(converter.fromRow(row, prefix + "_diagnosis_code_type", String.class));
        entity.setDiagnosisCode(converter.fromRow(row, prefix + "_diagnosis_code", String.class));
        entity.setDiagnosisDescription(converter.fromRow(row, prefix + "_diagnosis_description", String.class));
        entity.setEffectiveDate(converter.fromRow(row, prefix + "_effective_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPatientDiagnosisUuid(converter.fromRow(row, prefix + "_patient_diagnosis_uuid", UUID.class));
        return entity;
    }
}
