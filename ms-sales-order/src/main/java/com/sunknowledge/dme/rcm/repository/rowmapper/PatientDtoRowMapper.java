package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDto}, with proper type conversions.
 */
@Service
public class PatientDtoRowMapper implements BiFunction<Row, String, PatientDto> {

    private final ColumnConverter converter;

    public PatientDtoRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDto} stored in the database.
     */
    @Override
    public PatientDto apply(Row row, String prefix) {
        PatientDto entity = new PatientDto();
        entity.setPatientDtoId(converter.fromRow(row, prefix + "_patient_dto_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setDob(converter.fromRow(row, prefix + "_dob", LocalDate.class));
        entity.setGender(converter.fromRow(row, prefix + "_gender", String.class));
        entity.setSsn(converter.fromRow(row, prefix + "_ssn", String.class));
        entity.setTaxZoneId(converter.fromRow(row, prefix + "_tax_zone_id", Long.class));
        entity.setTaxZoneName(converter.fromRow(row, prefix + "_tax_zone_name", String.class));
        entity.setTaxRate(converter.fromRow(row, prefix + "_tax_rate", Double.class));
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
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setBillingAddressLine1(converter.fromRow(row, prefix + "_billing_address_line_1", String.class));
        entity.setBillingAddressLine2(converter.fromRow(row, prefix + "_billing_address_line_2", String.class));
        entity.setBillingAddressCity(converter.fromRow(row, prefix + "_billing_address_city", String.class));
        entity.setBillingAddressState(converter.fromRow(row, prefix + "_billing_address_state", String.class));
        entity.setBillingAddressZip(converter.fromRow(row, prefix + "_billing_address_zip", String.class));
        entity.setCaregiverName(converter.fromRow(row, prefix + "_caregiver_name", String.class));
        entity.setCaregiverContact(converter.fromRow(row, prefix + "_caregiver_contact", String.class));
        entity.setCaregiverRelatinshipPatient(converter.fromRow(row, prefix + "_caregiver_relatinship_patient", String.class));
        entity.setPrimaryInsuranceId(converter.fromRow(row, prefix + "_primary_insurance_id", Long.class));
        entity.setPrimaryInsuranceName(converter.fromRow(row, prefix + "_primary_insurance_name", String.class));
        entity.setPrimaryInsurancePayerIdNo(converter.fromRow(row, prefix + "_primary_insurance_payer_id_no", String.class));
        entity.setPrimaryInsurancePayerContactNo(converter.fromRow(row, prefix + "_primary_insurance_payer_contact_no", String.class));
        entity.setPrimaryInsurancePolicyNum(converter.fromRow(row, prefix + "_primary_insurance_policy_num", String.class));
        entity.setPrimaryInsurancePolicyGroupNum(converter.fromRow(row, prefix + "_primary_insurance_policy_group_num", String.class));
        entity.setPrimaryInsurancePolicyGroupId(converter.fromRow(row, prefix + "_primary_insurance_policy_group_id", Long.class));
        entity.setPrimaryInsurancePolicyStartDate(converter.fromRow(row, prefix + "_primary_insurance_policy_start_date", LocalDate.class));
        entity.setPrimaryInsurancePolicyEndDate(converter.fromRow(row, prefix + "_primary_insurance_policy_end_date", LocalDate.class));
        entity.setPrimaryInsurancePayPercentage(converter.fromRow(row, prefix + "_primary_insurance_pay_percentage", Double.class));
        entity.setPrimaryInsuranceDeductableAmt(converter.fromRow(row, prefix + "_primary_insurance_deductable_amt", Double.class));
        entity.setSecondaryInsuranceId(converter.fromRow(row, prefix + "_secondary_insurance_id", Long.class));
        entity.setSecondaryInsuranceName(converter.fromRow(row, prefix + "_secondary_insurance_name", String.class));
        entity.setSecondaryInsurancePayerIdNo(converter.fromRow(row, prefix + "_secondary_insurance_payer_id_no", String.class));
        entity.setSecondaryInsurancePayerContactNo(converter.fromRow(row, prefix + "_secondary_insurance_payer_contact_no", String.class));
        entity.setSecondaryInsurancePolicyNum(converter.fromRow(row, prefix + "_secondary_insurance_policy_num", String.class));
        entity.setSecondaryInsurancePolicyGroupNum(converter.fromRow(row, prefix + "_secondary_insurance_policy_group_num", String.class));
        entity.setSecondaryInsurancePolicyGroupId(converter.fromRow(row, prefix + "_secondary_insurance_policy_group_id", Long.class));
        entity.setSecondaryInsurancePolicyStartDate(
            converter.fromRow(row, prefix + "_secondary_insurance_policy_start_date", LocalDate.class)
        );
        entity.setSecondaryInsurancePolicyEndDate(converter.fromRow(row, prefix + "_secondary_insurance_policy_end_date", LocalDate.class));
        entity.setSecondaryInsurancePayPercentage(converter.fromRow(row, prefix + "_secondary_insurance_pay_percentage", Double.class));
        entity.setSecondaryInsuranceDeductableAmt(converter.fromRow(row, prefix + "_secondary_insurance_deductable_amt", Double.class));
        entity.setTertiaryInsuranceId(converter.fromRow(row, prefix + "_tertiary_insurance_id", Long.class));
        entity.setTertiaryInsuranceName(converter.fromRow(row, prefix + "_tertiary_insurance_name", String.class));
        entity.setTertiaryInsurancePayerIdNo(converter.fromRow(row, prefix + "_tertiary_insurance_payer_id_no", String.class));
        entity.setTertiaryInsurancePayerContactNo(converter.fromRow(row, prefix + "_tertiary_insurance_payer_contact_no", String.class));
        entity.setTertiaryInsurancePolicyNum(converter.fromRow(row, prefix + "_tertiary_insurance_policy_num", String.class));
        entity.setTertiaryInsurancePolicyGroupNum(converter.fromRow(row, prefix + "_tertiary_insurance_policy_group_num", String.class));
        entity.setTertiaryInsurancePolicyGroupId(converter.fromRow(row, prefix + "_tertiary_insurance_policy_group_id", Long.class));
        entity.setTertiaryInsurancePolicyStartDate(
            converter.fromRow(row, prefix + "_tertiary_insurance_policy_start_date", LocalDate.class)
        );
        entity.setTertiaryInsurancePolicyEndDate(converter.fromRow(row, prefix + "_tertiary_insurance_policy_end_date", LocalDate.class));
        entity.setTertiaryInsurancePayPercentage(converter.fromRow(row, prefix + "_tertiary_insurance_pay_percentage", Double.class));
        entity.setTertiaryInsuranceDeductableAmt(converter.fromRow(row, prefix + "_tertiary_insurance_deductable_amt", Double.class));
        entity.setRelationship(converter.fromRow(row, prefix + "_relationship", String.class));
        entity.setInsuredFirstName(converter.fromRow(row, prefix + "_insured_first_name", String.class));
        entity.setInsuredMiddleName(converter.fromRow(row, prefix + "_insured_middle_name", String.class));
        entity.setInsuredLastName(converter.fromRow(row, prefix + "_insured_last_name", String.class));
        entity.setInsuredSuffix(converter.fromRow(row, prefix + "_insured_suffix", String.class));
        entity.setInsuredDob(converter.fromRow(row, prefix + "_insured_dob", LocalDate.class));
        entity.setInsuredSsn(converter.fromRow(row, prefix + "_insured_ssn", String.class));
        entity.setInsuredGender(converter.fromRow(row, prefix + "_insured_gender", String.class));
        entity.setPrimaryInsurerAddressLine1(converter.fromRow(row, prefix + "_primary_insurer_address_line_1", String.class));
        entity.setPrimaryInsurerAddressLine2(converter.fromRow(row, prefix + "_primary_insurer_address_line_2", String.class));
        entity.setPrimaryInsurerCity(converter.fromRow(row, prefix + "_primary_insurer_city", String.class));
        entity.setPrimaryInsurerState(converter.fromRow(row, prefix + "_primary_insurer_state", String.class));
        entity.setPrimaryInsurerZip(converter.fromRow(row, prefix + "_primary_insurer_zip", String.class));
        entity.setPrimaryInsurerContact1(converter.fromRow(row, prefix + "_primary_insurer_contact_1", String.class));
        entity.setPrimaryInsurerFax(converter.fromRow(row, prefix + "_primary_insurer_fax", String.class));
        entity.setAlwaysCrossoverStatus(converter.fromRow(row, prefix + "_always_crossover_status", String.class));
        entity.setPrimaryInsuranceMemberId(converter.fromRow(row, prefix + "_primary_insurance_member_id", String.class));
        entity.setSecondaryInsuranceMemberId(converter.fromRow(row, prefix + "_secondary_insurance_member_id", String.class));
        entity.setTertiaryInsuranceMemberId(converter.fromRow(row, prefix + "_tertiary_insurance_member_id", String.class));
        entity.setPatientRelationshipInsured(converter.fromRow(row, prefix + "_patient_relationship_insured", String.class));
        entity.setPatientConditionEmployment(converter.fromRow(row, prefix + "_patient_condition_employment", String.class));
        entity.setPatientConditionAutoAccident(converter.fromRow(row, prefix + "_patient_condition_auto_accident", String.class));
        entity.setPatientConditionOtherAccident(converter.fromRow(row, prefix + "_patient_condition_other_accident", String.class));
        entity.setInsuredEmployer(converter.fromRow(row, prefix + "_insured_employer", String.class));
        entity.setWorkersCompensationPayerIdNumber(converter.fromRow(row, prefix + "_workers_compensation_payer_id_number", String.class));
        entity.setWorkersCompensationPlanName(converter.fromRow(row, prefix + "_workers_compensation_plan_name", String.class));
        entity.setWorkersCompensationAdditionalDtls(converter.fromRow(row, prefix + "_workers_compensation_additional_dtls", String.class));
        entity.setWorkersCompensationClaimFillingCode(
            converter.fromRow(row, prefix + "_workers_compensation_claim_filling_code", String.class)
        );
        entity.setWorkersCompensationTplCode(converter.fromRow(row, prefix + "_workers_compensation_tpl_code", String.class));
        entity.setWorkersCompensationTplName(converter.fromRow(row, prefix + "_workers_compensation_tpl_name", String.class));
        entity.setWcPropertyCasualtyAgencyClaimNo(converter.fromRow(row, prefix + "_wc_property_casualty_agency_claim_no", String.class));
        entity.setWcCarrierId(converter.fromRow(row, prefix + "_wc_carrier_id", String.class));
        entity.setEmployerAddressLine1(converter.fromRow(row, prefix + "_employer_address_line_1", String.class));
        entity.setEmployerAddressLine2(converter.fromRow(row, prefix + "_employer_address_line_2", String.class));
        entity.setEmployerCity(converter.fromRow(row, prefix + "_employer_city", String.class));
        entity.setEmployerState(converter.fromRow(row, prefix + "_employer_state", String.class));
        entity.setEmployerCountry(converter.fromRow(row, prefix + "_employer_country", String.class));
        entity.setEmployerZip(converter.fromRow(row, prefix + "_employer_zip", String.class));
        entity.setEmployerContactNo1(converter.fromRow(row, prefix + "_employer_contact_no_1", String.class));
        entity.setEmployerContactNo2(converter.fromRow(row, prefix + "_employer_contact_no_2", String.class));
        entity.setEmployerFax(converter.fromRow(row, prefix + "_employer_fax", String.class));
        entity.setEmployerEfax(converter.fromRow(row, prefix + "_employer_efax", String.class));
        entity.setEmployerEmail(converter.fromRow(row, prefix + "_employer_email", String.class));
        entity.setEmployeeRelationship(converter.fromRow(row, prefix + "_employee_relationship", String.class));
        entity.setHeight(converter.fromRow(row, prefix + "_height", Double.class));
        entity.setWeight(converter.fromRow(row, prefix + "_weight", Double.class));
        entity.setFunctionalAbilities(converter.fromRow(row, prefix + "_functional_abilities", String.class));
        entity.setInfectionConditionStatus(converter.fromRow(row, prefix + "_infection_condition_status", String.class));
        entity.setDiabetesStatus(converter.fromRow(row, prefix + "_diabetes_status", String.class));
        entity.setDiagnosisCodeType(converter.fromRow(row, prefix + "_diagnosis_code_type", String.class));
        entity.setIcd10DiagnosisCode1(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_1", String.class));
        entity.setIcd10DiagnosisCode2(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_2", String.class));
        entity.setIcd10DiagnosisCode3(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_3", String.class));
        entity.setIcd10DiagnosisCode4(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_4", String.class));
        entity.setIcd10DiagnosisCode5(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_5", String.class));
        entity.setIcd10DiagnosisCode6(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_6", String.class));
        entity.setIcd10DiagnosisCode7(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_7", String.class));
        entity.setIcd10DiagnosisCode8(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_8", String.class));
        entity.setIcd10DiagnosisCode9(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_9", String.class));
        entity.setIcd10DiagnosisCode10(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_10", String.class));
        entity.setIcd10DiagnosisCode11(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_11", String.class));
        entity.setIcd10DiagnosisCode12(converter.fromRow(row, prefix + "_icd_10_diagnosis_code_12", String.class));
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
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setPatientDtoUuid(converter.fromRow(row, prefix + "_patient_dto_uuid", UUID.class));
        entity.setSecondaryInsurerAddressLine1(converter.fromRow(row, prefix + "_secondary_insurer_address_line_1", String.class));
        entity.setSecondaryInsurerAddressLine2(converter.fromRow(row, prefix + "_secondary_insurer_address_line_2", String.class));
        entity.setSecondaryInsurerCity(converter.fromRow(row, prefix + "_secondary_insurer_city", String.class));
        entity.setSecondaryInsurerState(converter.fromRow(row, prefix + "_secondary_insurer_state", String.class));
        entity.setSecondaryInsurerZip(converter.fromRow(row, prefix + "_secondary_insurer_zip", String.class));
        entity.setSecondaryInsurerContact1(converter.fromRow(row, prefix + "_secondary_insurer_contact_1", String.class));
        entity.setSecondaryInsurerFax(converter.fromRow(row, prefix + "_secondary_insurer_fax", String.class));
        entity.setTertiaryInsurerAddressLine1(converter.fromRow(row, prefix + "_tertiary_insurer_address_line_1", String.class));
        entity.setTertiaryInsurerAddressLine2(converter.fromRow(row, prefix + "_tertiary_insurer_address_line_2", String.class));
        entity.setTertiaryInsurerCity(converter.fromRow(row, prefix + "_tertiary_insurer_city", String.class));
        entity.setTertiaryInsurerState(converter.fromRow(row, prefix + "_tertiary_insurer_state", String.class));
        entity.setTertiaryInsurerZip(converter.fromRow(row, prefix + "_tertiary_insurer_zip", String.class));
        entity.setTertiaryInsurerContact1(converter.fromRow(row, prefix + "_tertiary_insurer_contact_1", String.class));
        entity.setTertiaryInsurerFax(converter.fromRow(row, prefix + "_tertiary_insurer_fax", String.class));
        return entity;
    }
}
