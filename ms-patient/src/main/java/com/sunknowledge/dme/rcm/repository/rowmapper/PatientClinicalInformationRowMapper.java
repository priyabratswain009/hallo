package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformation;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientClinicalInformation}, with proper type conversions.
 */
@Service
public class PatientClinicalInformationRowMapper implements BiFunction<Row, String, PatientClinicalInformation> {

    private final ColumnConverter converter;

    public PatientClinicalInformationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientClinicalInformation} stored in the database.
     */
    @Override
    public PatientClinicalInformation apply(Row row, String prefix) {
        PatientClinicalInformation entity = new PatientClinicalInformation();
        entity.setPatientClinicalInformationId(converter.fromRow(row, prefix + "_patient_clinical_information_id", Long.class));
        entity.setHeight(converter.fromRow(row, prefix + "_height", Double.class));
        entity.setWeight(converter.fromRow(row, prefix + "_weight", Double.class));
        entity.setFunctionalAbilities(converter.fromRow(row, prefix + "_functional_abilities", String.class));
        entity.setCaptureDate(converter.fromRow(row, prefix + "_capture_date", LocalDate.class));
        entity.setInfectionConditionStatus(converter.fromRow(row, prefix + "_infection_condition_status", String.class));
        entity.setDiabetesStatus(converter.fromRow(row, prefix + "_diabetes_status", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPatientClinicalInformationUuid(converter.fromRow(row, prefix + "_patient_clinical_information_uuid", UUID.class));
        return entity;
    }
}
