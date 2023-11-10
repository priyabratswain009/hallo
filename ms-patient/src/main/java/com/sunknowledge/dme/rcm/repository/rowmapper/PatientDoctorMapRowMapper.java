package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDoctorMap}, with proper type conversions.
 */
@Service
public class PatientDoctorMapRowMapper implements BiFunction<Row, String, PatientDoctorMap> {

    private final ColumnConverter converter;

    public PatientDoctorMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDoctorMap} stored in the database.
     */
    @Override
    public PatientDoctorMap apply(Row row, String prefix) {
        PatientDoctorMap entity = new PatientDoctorMap();
        entity.setPatientDoctorMapId(converter.fromRow(row, prefix + "_patient_doctor_map_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setDoctorFirstName(converter.fromRow(row, prefix + "_doctor_first_name", String.class));
        entity.setDoctorMiddleName(converter.fromRow(row, prefix + "_doctor_middle_name", String.class));
        entity.setDoctorLastName(converter.fromRow(row, prefix + "_doctor_last_name", String.class));
        entity.setDoctorNameSuffix(converter.fromRow(row, prefix + "_doctor_name_suffix", String.class));
        entity.setDoctorAddressLine1(converter.fromRow(row, prefix + "_doctor_address_line_1", String.class));
        entity.setDoctorAddressLine2(converter.fromRow(row, prefix + "_doctor_address_line_2", String.class));
        entity.setDoctorAddressCity(converter.fromRow(row, prefix + "_doctor_address_city", String.class));
        entity.setDoctorAddressState(converter.fromRow(row, prefix + "_doctor_address_state", String.class));
        entity.setDoctorAddressZip(converter.fromRow(row, prefix + "_doctor_address_zip", String.class));
        entity.setDoctorContact1(converter.fromRow(row, prefix + "_doctor_contact_1", String.class));
        entity.setDoctorContact2(converter.fromRow(row, prefix + "_doctor_contact_2", String.class));
        entity.setDoctorFax(converter.fromRow(row, prefix + "_doctor_fax", String.class));
        entity.setDoctorNpiNumber(converter.fromRow(row, prefix + "_doctor_npi_number", String.class));
        entity.setDoctorGender(converter.fromRow(row, prefix + "_doctor_gender", String.class));
        entity.setDoctorTaxonomyCode(converter.fromRow(row, prefix + "_doctor_taxonomy_code", String.class));
        entity.setDoctorTaxonomyDescription(converter.fromRow(row, prefix + "_doctor_taxonomy_description", String.class));
        entity.setDoctorPracticeState(converter.fromRow(row, prefix + "_doctor_practice_state", String.class));
        entity.setDoctorLicenseNumber(converter.fromRow(row, prefix + "_doctor_license_number", String.class));
        entity.setPatientDoctorMapUuid(converter.fromRow(row, prefix + "_patient_doctor_map_uuid", UUID.class));
        return entity;
    }
}
