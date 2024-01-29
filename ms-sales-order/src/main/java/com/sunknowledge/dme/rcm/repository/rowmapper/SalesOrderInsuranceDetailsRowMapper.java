package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderInsuranceDetails}, with proper type conversions.
 */
@Service
public class SalesOrderInsuranceDetailsRowMapper implements BiFunction<Row, String, SalesOrderInsuranceDetails> {

    private final ColumnConverter converter;

    public SalesOrderInsuranceDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderInsuranceDetails} stored in the database.
     */
    @Override
    public SalesOrderInsuranceDetails apply(Row row, String prefix) {
        SalesOrderInsuranceDetails entity = new SalesOrderInsuranceDetails();
        entity.setSalesOrderInsuranceDetailsId(converter.fromRow(row, prefix + "_sales_order_insurance_details_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPrimaryInsurerId(converter.fromRow(row, prefix + "_primary_insurer_id", Long.class));
        entity.setPrimaryInsurerName(converter.fromRow(row, prefix + "_primary_insurer_name", String.class));
        entity.setPrimaryInsurerPolicyNo(converter.fromRow(row, prefix + "_primary_insurer_policy_no", String.class));
        entity.setPrimaryInsurerPatientIdNumber(converter.fromRow(row, prefix + "_primary_insurer_patient_id_number", String.class));
        entity.setPrimaryInsurerEffectiveDate(converter.fromRow(row, prefix + "_primary_insurer_effective_date", LocalDate.class));
        entity.setPrimaryInsurerVerificationStatus(converter.fromRow(row, prefix + "_primary_insurer_verification_status", String.class));
        entity.setPrimaryInsurerVerificationDate(converter.fromRow(row, prefix + "_primary_insurer_verification_date", LocalDate.class));
        entity.setPrimaryInsurerPayPercentage(converter.fromRow(row, prefix + "_primary_insurer_pay_percentage", Long.class));
        entity.setPrimaryBox10D(converter.fromRow(row, prefix + "_primary_box_10_d", String.class));
        entity.setPrimaryBox19(converter.fromRow(row, prefix + "_primary_box_19", String.class));
        entity.setPrimaryBox24Ia(converter.fromRow(row, prefix + "_primary_box_24_ia", String.class));
        entity.setPrimaryBox24Ja(converter.fromRow(row, prefix + "_primary_box_24_ja", String.class));
        entity.setPrimaryBox24Jb(converter.fromRow(row, prefix + "_primary_box_24_jb", String.class));
        entity.setPrimaryIncludeBox24Jbstatus(converter.fromRow(row, prefix + "_primary_include_box_24_jbstatus", String.class));
        entity.setPrimaryIncludePayerSalesOrderStatus(
            converter.fromRow(row, prefix + "_primary_include_payer_sales_order_status", String.class)
        );
        entity.setPrimaryWaitForPreviousPayerBeforeBillingStatus(
            converter.fromRow(row, prefix + "_primary_wait_for_previous_payer_before_billing_status", String.class)
        );
        entity.setPrimaryPayPercentageStatus(converter.fromRow(row, prefix + "_primary_pay_percentage_status", String.class));
        entity.setSecondaryInsurerId(converter.fromRow(row, prefix + "_secondary_insurer_id", Long.class));
        entity.setSecondaryInsurerName(converter.fromRow(row, prefix + "_secondary_insurer_name", String.class));
        entity.setSecondaryInsurerPolicyNo(converter.fromRow(row, prefix + "_secondary_insurer_policy_no", String.class));
        entity.setSecondaryInsurerPatientIdNumber(converter.fromRow(row, prefix + "_secondary_insurer_patient_id_number", String.class));
        entity.setSecondaryInsurerEffectiveDate(converter.fromRow(row, prefix + "_secondary_insurer_effective_date", LocalDate.class));
        entity.setSecondaryInsurerVerificationStatus(
            converter.fromRow(row, prefix + "_secondary_insurer_verification_status", String.class)
        );
        entity.setSecondaryInsurerVerificationDate(
            converter.fromRow(row, prefix + "_secondary_insurer_verification_date", LocalDate.class)
        );
        entity.setSecondaryInsurerPayPercentage(converter.fromRow(row, prefix + "_secondary_insurer_pay_percentage", Long.class));
        entity.setSecondaryBox10D(converter.fromRow(row, prefix + "_secondary_box_10_d", String.class));
        entity.setSecondaryBox19(converter.fromRow(row, prefix + "_secondary_box_19", String.class));
        entity.setSecondaryBox24Ia(converter.fromRow(row, prefix + "_secondary_box_24_ia", String.class));
        entity.setSecondaryBox24Ja(converter.fromRow(row, prefix + "_secondary_box_24_ja", String.class));
        entity.setSecondaryBox24Jb(converter.fromRow(row, prefix + "_secondary_box_24_jb", String.class));
        entity.setSecondaryIncludeBox24JbStatus(converter.fromRow(row, prefix + "_secondary_include_box_24_jb_status", String.class));
        entity.setSecondaryIncludePayerSalesOrderStatus(
            converter.fromRow(row, prefix + "_secondary_include_payer_sales_order_status", String.class)
        );
        entity.setSecondaryWaitPreviousPayerBefrBillingStatus(
            converter.fromRow(row, prefix + "_secondary_wait_previous_payer_befr_billing_status", String.class)
        );
        entity.setSecondaryPayPercentageStatus(converter.fromRow(row, prefix + "_secondary_pay_percentage_status", String.class));
        entity.setTertiaryInsurerId(converter.fromRow(row, prefix + "_tertiary_insurer_id", Long.class));
        entity.setTertiaryInsurerName(converter.fromRow(row, prefix + "_tertiary_insurer_name", String.class));
        entity.setTertiaryInsurerPolicyno(converter.fromRow(row, prefix + "_tertiary_insurer_policyno", String.class));
        entity.setTertiaryInsurerPatientIdNumber(converter.fromRow(row, prefix + "_tertiary_insurer_patient_id_number", String.class));
        entity.setTertiaryInsurerEffectiveDate(converter.fromRow(row, prefix + "_tertiary_insurer_effective_date", LocalDate.class));
        entity.setTertiaryInsurerVerificationStatus(converter.fromRow(row, prefix + "_tertiary_insurer_verification_status", String.class));
        entity.setTertiaryInsurerVerificationDate(converter.fromRow(row, prefix + "_tertiary_insurer_verification_date", LocalDate.class));
        entity.setTertiaryInsurerPayPercentage(converter.fromRow(row, prefix + "_tertiary_insurer_pay_percentage", Long.class));
        entity.setTertiaryBox10D(converter.fromRow(row, prefix + "_tertiary_box_10_d", String.class));
        entity.setTertiaryBox19(converter.fromRow(row, prefix + "_tertiary_box_19", String.class));
        entity.setTertiaryBox24Ia(converter.fromRow(row, prefix + "_tertiary_box_24_ia", String.class));
        entity.setTertiaryBox24Ja(converter.fromRow(row, prefix + "_tertiary_box_24_ja", String.class));
        entity.setTertiaryBox24Jb(converter.fromRow(row, prefix + "_tertiary_box_24_jb", String.class));
        entity.setTertiaryIncludeBox24JbStatus(converter.fromRow(row, prefix + "_tertiary_include_box_24_jb_status", String.class));
        entity.setTertiaryIncludePayerInSalesOrderStatus(
            converter.fromRow(row, prefix + "_tertiary_include_payer_in_sales_order_status", String.class)
        );
        entity.setTertiaryWaitPreviousPayerBeforeBillingStatus(
            converter.fromRow(row, prefix + "_tertiary_wait_previous_payer_before_billing_status", String.class)
        );
        entity.setTertiaryPayPercentageStatus(converter.fromRow(row, prefix + "_tertiary_pay_percentage_status", String.class));
        entity.setInsuranceVerificationStatus(converter.fromRow(row, prefix + "_insurance_verification_status", String.class));
        entity.setCoverageVerificationStatus(converter.fromRow(row, prefix + "_coverage_verification_status", String.class));
        entity.setExcludeFromEligibilityCheckStatus(
            converter.fromRow(row, prefix + "_exclude_from_eligibility_check_status", String.class)
        );
        entity.setPatientPayPercentage(converter.fromRow(row, prefix + "_patient_pay_percentage", Long.class));
        entity.setPatientIncludeThisPayorInSalesOrderStatus(
            converter.fromRow(row, prefix + "_patient_include_this_payor_in_sales_order_status", String.class)
        );
        entity.setPatientWaitForPreviousPayerBeforeBillingStatus(
            converter.fromRow(row, prefix + "_patient_wait_for_previous_payer_before_billing_status", String.class)
        );
        entity.setWorkersCompDateOfOnset(converter.fromRow(row, prefix + "_workers_comp_date_of_onset", LocalDate.class));
        entity.setWorkersCompInjuryRelatedEmploymentStatus(
            converter.fromRow(row, prefix + "_workers_comp_injury_related_employment_status", String.class)
        );
        entity.setWorkersCompInjuryRelatedAutoAccidentStatus(
            converter.fromRow(row, prefix + "_workers_comp_injury_related_auto_accident_status", String.class)
        );
        entity.setWorkersCompAutoAccidentStateCode(converter.fromRow(row, prefix + "_workers_comp_auto_accident_state_code", String.class));
        entity.setWorkersCompInjuryRelatedToOtherAccidentStatus(
            converter.fromRow(row, prefix + "_workers_comp_injury_related_to_other_accident_status", String.class)
        );
        entity.setEclaimsAttachmentStatus(converter.fromRow(row, prefix + "_eclaims_attachment_status", String.class));
        entity.setAttachmentNumber(converter.fromRow(row, prefix + "_attachment_number", Long.class));
        entity.setTypeCode(converter.fromRow(row, prefix + "_type_code", String.class));
        entity.setTransactionCode(converter.fromRow(row, prefix + "_transaction_code", String.class));
        entity.setClaimsNoteType(converter.fromRow(row, prefix + "_claims_note_type", String.class));
        entity.setClaimsNote(converter.fromRow(row, prefix + "_claims_note", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setSalesOrderNo(converter.fromRow(row, prefix + "_sales_order_no", String.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setPrimaryInsuranceGroupId(converter.fromRow(row, prefix + "_primary_insurance_group_id", Long.class));
        entity.setPrimaryInsuranceGroupName(converter.fromRow(row, prefix + "_primary_insurance_group_name", String.class));
        entity.setSecondaryInsuranceGroupId(converter.fromRow(row, prefix + "_secondary_insurance_group_id", Long.class));
        entity.setSecondaryInsuranceGroupName(converter.fromRow(row, prefix + "_secondary_insurance_group_name", String.class));
        entity.setTertiaryInsuranceGroupId(converter.fromRow(row, prefix + "_tertiary_insurance_group_id", Long.class));
        entity.setTertiaryInsuranceGroupName(converter.fromRow(row, prefix + "_tertiary_insurance_group_name", String.class));
        entity.setPrimaryInsurancePlanId(converter.fromRow(row, prefix + "_primary_insurance_plan_id", Long.class));
        entity.setPrimaryInsurancePlanType(converter.fromRow(row, prefix + "_primary_insurance_plan_type", String.class));
        entity.setSecondaryInsurancePlanId(converter.fromRow(row, prefix + "_secondary_insurance_plan_id", Long.class));
        entity.setSecondaryInsurancePlanType(converter.fromRow(row, prefix + "_secondary_insurance_plan_type", String.class));
        entity.setTertiaryInsurancePlanId(converter.fromRow(row, prefix + "_tertiary_insurance_plan_id", Long.class));
        entity.setTertiaryInsurancePlanType(converter.fromRow(row, prefix + "_tertiary_insurance_plan_type", String.class));
        entity.setSalesOrderInsuranceDetailsUuid(converter.fromRow(row, prefix + "_sales_order_insurance_details_uuid", UUID.class));
        entity.setPrimaryInsurancePayerId(converter.fromRow(row, prefix + "_primary_insurance_payer_id", String.class));
        entity.setSecondaryInsurancePayerId(converter.fromRow(row, prefix + "_secondary_insurance_payer_id", String.class));
        entity.setTertiaryInsurancePayerId(converter.fromRow(row, prefix + "_tertiary_insurance_payer_id", String.class));
        entity.setPrimaryInsuranceIndicatorCode(converter.fromRow(row, prefix + "_primary_insurance_indicator_code", String.class));
        entity.setSecondaryInsuranceIndicatorCode(converter.fromRow(row, prefix + "_secondary_insurance_indicator_code", String.class));
        entity.setTertiaryInsuranceIndicatorCode(converter.fromRow(row, prefix + "_tertiary_insurance_indicator_code", String.class));
        entity.setPriceTableId(converter.fromRow(row, prefix + "_price_table_id", Long.class));
        entity.setPriceTableName(converter.fromRow(row, prefix + "_price_table_name", String.class));
        entity.setPrimaryInsurerAddressLine1(converter.fromRow(row, prefix + "_primary_insurer_address_line_1", String.class));
        entity.setPrimaryInsurerAddressLine2(converter.fromRow(row, prefix + "_primary_insurer_address_line_2", String.class));
        entity.setPrimaryInsurerCity(converter.fromRow(row, prefix + "_primary_insurer_city", String.class));
        entity.setPrimaryInsurerState(converter.fromRow(row, prefix + "_primary_insurer_state", String.class));
        entity.setPrimaryInsurerZip(converter.fromRow(row, prefix + "_primary_insurer_zip", String.class));
        entity.setPrimaryInsurerContact1(converter.fromRow(row, prefix + "_primary_insurer_contact_1", String.class));
        entity.setPrimaryInsurerFax(converter.fromRow(row, prefix + "_primary_insurer_fax", String.class));
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
        entity.setPrimaryInsurerPolicyEndDate(converter.fromRow(row, prefix + "_primary_insurer_policy_end_date", LocalDate.class));
        entity.setPrimaryInsurerContactName(converter.fromRow(row, prefix + "_primary_insurer_contact_name", String.class));
        entity.setPrimaryClaimProgram(converter.fromRow(row, prefix + "_primary_claim_program", String.class));
        entity.setSecondaryClaimProgram(converter.fromRow(row, prefix + "_secondary_claim_program", String.class));
        entity.setTertiaryClaimProgram(converter.fromRow(row, prefix + "_tertiary_claim_program", String.class));
        entity.setWorkersCompInsuredEmployer(converter.fromRow(row, prefix + "_workers_comp_insured_employer", String.class));
        entity.setWorkersCompPayerIdNumber(converter.fromRow(row, prefix + "_workers_comp_payer_id_number", String.class));
        entity.setWorkersCompPlanName(converter.fromRow(row, prefix + "_workers_comp_plan_name", String.class));
        entity.setWorkersCompAdditionalDtls(converter.fromRow(row, prefix + "_workers_comp_additional_dtls", String.class));
        entity.setWorkersCompClaimFillingCode(converter.fromRow(row, prefix + "_workers_comp_claim_filling_code", String.class));
        entity.setWorkersCompTplCode(converter.fromRow(row, prefix + "_workers_comp_tpl_code", String.class));
        entity.setWorkersCompTplName(converter.fromRow(row, prefix + "_workers_comp_tpl_name", String.class));
        entity.setWorkersCompPropertyCasualtyAgencyClaimNo(
            converter.fromRow(row, prefix + "_workers_comp_property_casualty_agency_claim_no", String.class)
        );
        entity.setWorkersCompCarrierId(converter.fromRow(row, prefix + "_workers_comp_carrier_id", String.class));
        entity.setWorkersCompEmployerAddressLine1(converter.fromRow(row, prefix + "_workers_comp_employer_address_line_1", String.class));
        entity.setWorkersCompEmployerAddressLine2(converter.fromRow(row, prefix + "_workers_comp_employer_address_line_2", String.class));
        entity.setWorkersCompEmployerCity(converter.fromRow(row, prefix + "_workers_comp_employer_city", String.class));
        entity.setWorkersCompEmployerState(converter.fromRow(row, prefix + "_workers_comp_employer_state", String.class));
        entity.setWorkersCompEmployerCountry(converter.fromRow(row, prefix + "_workers_comp_employer_country", String.class));
        entity.setWorkersCompEmployerZip(converter.fromRow(row, prefix + "_workers_comp_employer_zip", String.class));
        entity.setWorkersCompEmployerContactNo1(converter.fromRow(row, prefix + "_workers_comp_employer_contact_no_1", String.class));
        entity.setWorkersCompEmployerContactNo2(converter.fromRow(row, prefix + "_workers_comp_employer_contact_no_2", String.class));
        entity.setWorkersCompEmployerFax(converter.fromRow(row, prefix + "_workers_comp_employer_fax", String.class));
        entity.setWorkersCompEmployerEfax(converter.fromRow(row, prefix + "_workers_comp_employer_efax", String.class));
        entity.setWorkersCompEmployerEmail(converter.fromRow(row, prefix + "_workers_comp_employer_email", String.class));
        entity.setWorkersCompRelationship(converter.fromRow(row, prefix + "_workers_comp_relationship", String.class));
        entity.setWorkersCompModeOfContact(converter.fromRow(row, prefix + "_workers_comp_mode_of_contact", String.class));
        return entity;
    }
}
