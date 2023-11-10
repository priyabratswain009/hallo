package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientInsurance;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientInsurance}, with proper type conversions.
 */
@Service
public class PatientInsuranceRowMapper implements BiFunction<Row, String, PatientInsurance> {

    private final ColumnConverter converter;

    public PatientInsuranceRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientInsurance} stored in the database.
     */
    @Override
    public PatientInsurance apply(Row row, String prefix) {
        PatientInsurance entity = new PatientInsurance();
        entity.setPatientInsuranceId(converter.fromRow(row, prefix + "_patient_insurance_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPayerLevel(converter.fromRow(row, prefix + "_payer_level", String.class));
        entity.setInsuranceName(converter.fromRow(row, prefix + "_insurance_name", String.class));
        entity.setInsuranceId(converter.fromRow(row, prefix + "_insurance_id", Long.class));
        entity.setCoverageType(converter.fromRow(row, prefix + "_coverage_type", String.class));
        entity.setPayerContact(converter.fromRow(row, prefix + "_payer_contact", String.class));
        entity.setPolicyNum(converter.fromRow(row, prefix + "_policy_num", String.class));
        entity.setPolicyGroupNum(converter.fromRow(row, prefix + "_policy_group_num", String.class));
        entity.setPolicyStartDate(converter.fromRow(row, prefix + "_policy_start_date", LocalDate.class));
        entity.setPolicyEndDate(converter.fromRow(row, prefix + "_policy_end_date", LocalDate.class));
        entity.setPayPercentage(converter.fromRow(row, prefix + "_pay_percentage", Double.class));
        entity.setDeductableAmt(converter.fromRow(row, prefix + "_deductable_amt", Double.class));
        entity.setRelationship(converter.fromRow(row, prefix + "_relationship", String.class));
        entity.setInsuredFirstName(converter.fromRow(row, prefix + "_insured_first_name", String.class));
        entity.setInsuredMiddleName(converter.fromRow(row, prefix + "_insured_middle_name", String.class));
        entity.setInsuredSuffix(converter.fromRow(row, prefix + "_insured_suffix", String.class));
        entity.setInsuredDob(converter.fromRow(row, prefix + "_insured_dob", LocalDate.class));
        entity.setInsuredSsn(converter.fromRow(row, prefix + "_insured_ssn", String.class));
        entity.setInsuredGender(converter.fromRow(row, prefix + "_insured_gender", String.class));
        entity.setAlwaysCrossoverStatus(converter.fromRow(row, prefix + "_always_crossover_status", String.class));
        entity.setClaimCodes(converter.fromRow(row, prefix + "_claim_codes", String.class));
        entity.setAddtnlClaimInfo(converter.fromRow(row, prefix + "_addtnl_claim_info", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setInsuredLastName(converter.fromRow(row, prefix + "_insured_last_name", String.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPatientInsuranceUuid(converter.fromRow(row, prefix + "_patient_insurance_uuid", UUID.class));
        entity.setMemberId(converter.fromRow(row, prefix + "_member_id", String.class));
        entity.setPatientRelationshipInsured(converter.fromRow(row, prefix + "_patient_relationship_insured", String.class));
        entity.setPatientConditionEmployment(converter.fromRow(row, prefix + "_patient_condition_employment", String.class));
        entity.setPatientConditionAutoAccident(converter.fromRow(row, prefix + "_patient_condition_auto_accident", String.class));
        entity.setPatientConditionOtherAccident(converter.fromRow(row, prefix + "_patient_condition_other_accident", String.class));
        entity.setInsurancePayerIdNo(converter.fromRow(row, prefix + "_insurance_payer_id_no", String.class));
        entity.setExpirationStatus(converter.fromRow(row, prefix + "_expiration_status", String.class));
        entity.setInsurerAddressLine1(converter.fromRow(row, prefix + "_insurer_address_line_1", String.class));
        entity.setInsurerAddressLine2(converter.fromRow(row, prefix + "_insurer_address_line_2", String.class));
        entity.setInsurerCity(converter.fromRow(row, prefix + "_insurer_city", String.class));
        entity.setInsurerState(converter.fromRow(row, prefix + "_insurer_state", String.class));
        entity.setInsurerZip(converter.fromRow(row, prefix + "_insurer_zip", String.class));
        entity.setInsurerContact1(converter.fromRow(row, prefix + "_insurer_contact_1", String.class));
        entity.setInsurerFax(converter.fromRow(row, prefix + "_insurer_fax", String.class));
        return entity;
    }
}
