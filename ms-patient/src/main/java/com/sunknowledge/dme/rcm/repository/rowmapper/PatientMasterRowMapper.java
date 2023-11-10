package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientMaster}, with proper type conversions.
 */
@Service
public class PatientMasterRowMapper implements BiFunction<Row, String, PatientMaster> {

    private final ColumnConverter converter;

    public PatientMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientMaster} stored in the database.
     */
    @Override
    public PatientMaster apply(Row row, String prefix) {
        PatientMaster entity = new PatientMaster();
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setDob(converter.fromRow(row, prefix + "_dob", LocalDate.class));
        entity.setGender(converter.fromRow(row, prefix + "_gender", String.class));
        entity.setSsn(converter.fromRow(row, prefix + "_ssn", String.class));
        entity.setTaxZoneId(converter.fromRow(row, prefix + "_tax_zone_id", Long.class));
        entity.setDiscountPercent(converter.fromRow(row, prefix + "_discount_percent", Double.class));
        entity.setPosId(converter.fromRow(row, prefix + "_pos_id", Long.class));
        entity.setPriorSystemKey(converter.fromRow(row, prefix + "_prior_system_key", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setBranchId(converter.fromRow(row, prefix + "_branch_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setPatientMasterUUID(converter.fromRow(row, prefix + "_patient_master_uuid", UUID.class));
        entity.setPatientIdNumber(converter.fromRow(row, prefix + "_patient_id_number", String.class));
        entity.setAddressLine1(converter.fromRow(row, prefix + "_address_line_1", String.class));
        entity.setAddressLine2(converter.fromRow(row, prefix + "_address_line_2", String.class));
        entity.setCity(converter.fromRow(row, prefix + "_city", String.class));
        entity.setState(converter.fromRow(row, prefix + "_state", String.class));
        entity.setCountry(converter.fromRow(row, prefix + "_country", String.class));
        entity.setZip(converter.fromRow(row, prefix + "_zip", String.class));
        entity.setContactNo1(converter.fromRow(row, prefix + "_contact_no_1", String.class));
        entity.setContactNo2(converter.fromRow(row, prefix + "_contact_no_2", String.class));
        entity.setFax(converter.fromRow(row, prefix + "_fax", String.class));
        entity.setEfax(converter.fromRow(row, prefix + "_efax", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setModeOfContact(converter.fromRow(row, prefix + "_mode_of_contact", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setBillingAddressLine1(converter.fromRow(row, prefix + "_billing_address_line_1", String.class));
        entity.setBillingAddressLine2(converter.fromRow(row, prefix + "_billing_address_line_2", String.class));
        entity.setBillingAddressCity(converter.fromRow(row, prefix + "_billing_address_city", String.class));
        entity.setBillingAddressState(converter.fromRow(row, prefix + "_billing_address_state", String.class));
        entity.setBillingAddressZip(converter.fromRow(row, prefix + "_billing_address_zip", String.class));
        entity.setCaregiverName(converter.fromRow(row, prefix + "_caregiver_name", String.class));
        entity.setCaregiverContact(converter.fromRow(row, prefix + "_caregiver_contact", String.class));
        entity.setCaregiverRelatinshipPatient(converter.fromRow(row, prefix + "_caregiver_relatinship_patient", String.class));
        entity.setTaxZoneName(converter.fromRow(row, prefix + "_tax_zone_name", String.class));
        entity.setTaxRate(converter.fromRow(row, prefix + "_tax_rate", Double.class));
        entity.setPatientDod(converter.fromRow(row, prefix + "_patient_dod", LocalDate.class));
        return entity;
    }
}
