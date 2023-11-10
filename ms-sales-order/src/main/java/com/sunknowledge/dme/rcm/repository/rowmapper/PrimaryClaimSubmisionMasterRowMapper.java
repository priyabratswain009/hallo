package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PrimaryClaimSubmisionMaster}, with proper type conversions.
 */
@Service
public class PrimaryClaimSubmisionMasterRowMapper implements BiFunction<Row, String, PrimaryClaimSubmisionMaster> {

    private final ColumnConverter converter;

    public PrimaryClaimSubmisionMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PrimaryClaimSubmisionMaster} stored in the database.
     */
    @Override
    public PrimaryClaimSubmisionMaster apply(Row row, String prefix) {
        PrimaryClaimSubmisionMaster entity = new PrimaryClaimSubmisionMaster();
        entity.setChangeHealthPrimarySubmisionMasterId(
            converter.fromRow(row, prefix + "_change_health_primary_submision_master_id", Long.class)
        );
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setClaimControlNo(converter.fromRow(row, prefix + "_claim_control_no", String.class));
        entity.setTradingPartnerServiceId(converter.fromRow(row, prefix + "_trading_partner_service_id", String.class));
        entity.setTradingPartnerName(converter.fromRow(row, prefix + "_trading_partner_name", String.class));
        entity.setSubmitterOrganizationName(converter.fromRow(row, prefix + "_submitter_organization_name", String.class));
        entity.setSubmitterContactPersonName(converter.fromRow(row, prefix + "_submitter_contact_person_name", String.class));
        entity.setSubmitterContactNo(converter.fromRow(row, prefix + "_submitter_contact_no", String.class));
        entity.setReceiverOrganizationName(converter.fromRow(row, prefix + "_receiver_organization_name", String.class));
        entity.setSubscriberMemberIdNo(converter.fromRow(row, prefix + "_subscriber_member_id_no", String.class));
        entity.setSubscriberPaymentResponsibilityLevelCode(
            converter.fromRow(row, prefix + "_subscriber_payment_responsibility_level_code", String.class)
        );
        entity.setSubscriberFirstName(converter.fromRow(row, prefix + "_subscriber_first_name", String.class));
        entity.setSubscriberLastName(converter.fromRow(row, prefix + "_subscriber_last_name", String.class));
        entity.setSubscriberGender(converter.fromRow(row, prefix + "_subscriber_gender", String.class));
        entity.setSubscriberDob(converter.fromRow(row, prefix + "_subscriber_dob", LocalDate.class));
        entity.setPrimaryInsurerPolicyNo(converter.fromRow(row, prefix + "_primary_insurer_policy_no", String.class));
        entity.setSubscriberAddressLine1(converter.fromRow(row, prefix + "_subscriber_address_line_1", String.class));
        entity.setSubscriberCity(converter.fromRow(row, prefix + "_subscriber_city", String.class));
        entity.setSubscriberState(converter.fromRow(row, prefix + "_subscriber_state", String.class));
        entity.setSubscriberZipCode(converter.fromRow(row, prefix + "_subscriber_zip_code", String.class));
        entity.setBillingProviderNpi(converter.fromRow(row, prefix + "_billing_provider_npi", String.class));
        entity.setBillingProviderEin(converter.fromRow(row, prefix + "_billing_provider_ein", String.class));
        entity.setBillingProviderOrganizationName(converter.fromRow(row, prefix + "_billing_provider_organization_name", String.class));
        entity.setBillingProviderAddressLine1(converter.fromRow(row, prefix + "_billing_provider_address_line_1", String.class));
        entity.setBillingProviderCity(converter.fromRow(row, prefix + "_billing_provider_city", String.class));
        entity.setBillingProviderState(converter.fromRow(row, prefix + "_billing_provider_state", String.class));
        entity.setBillingProviderZipCode(converter.fromRow(row, prefix + "_billing_provider_zip_code", String.class));
        entity.setBillingProviderContactPersonName(converter.fromRow(row, prefix + "_billing_provider_contact_person_name", String.class));
        entity.setBillingProviderContactNo(converter.fromRow(row, prefix + "_billing_provider_contact_no", String.class));
        entity.setClaimFilingCode(converter.fromRow(row, prefix + "_claim_filing_code", String.class));
        entity.setClaimChargeAmount(converter.fromRow(row, prefix + "_claim_charge_amount", Double.class));
        entity.setPosCode(converter.fromRow(row, prefix + "_pos_code", String.class));
        entity.setClaimFrequencyCode(converter.fromRow(row, prefix + "_claim_frequency_code", String.class));
        entity.setSignatureIndicator(converter.fromRow(row, prefix + "_signature_indicator", String.class));
        entity.setPlanParticipationCode(converter.fromRow(row, prefix + "_plan_participation_code", String.class));
        entity.setBenefitsAssignmentCertificationIndicator(
            converter.fromRow(row, prefix + "_benefits_assignment_certification_indicator", String.class)
        );
        entity.setReleaseInformationCode(converter.fromRow(row, prefix + "_release_information_code", String.class));
        entity.setPrimaryDiagnosis(converter.fromRow(row, prefix + "_primary_diagnosis", String.class));
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
        entity.setInsertedById(converter.fromRow(row, prefix + "_inserted_by_id", Long.class));
        entity.setInsertedDate(converter.fromRow(row, prefix + "_inserted_date", LocalDate.class));
        entity.setIpdatedById(converter.fromRow(row, prefix + "_ipdated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setBillingProviderType(converter.fromRow(row, prefix + "_billing_provider_type", String.class));
        entity.setInsertedByName(converter.fromRow(row, prefix + "_inserted_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setBillingProviderAddressLine2(converter.fromRow(row, prefix + "_billing_provider_address_line_2", String.class));
        entity.setInsuredFirstName(converter.fromRow(row, prefix + "_insured_first_name", String.class));
        entity.setInsuredLastName(converter.fromRow(row, prefix + "_insured_last_name", String.class));
        entity.setInsuredAddressLine1(converter.fromRow(row, prefix + "_insured_address_line_1", String.class));
        entity.setInsuredAddressLine2(converter.fromRow(row, prefix + "_insured_address_line_2", String.class));
        entity.setInsuredCity(converter.fromRow(row, prefix + "_insured_city", String.class));
        entity.setInsuredState(converter.fromRow(row, prefix + "_insured_state", String.class));
        entity.setInsuredZip(converter.fromRow(row, prefix + "_insured_zip", String.class));
        entity.setInsuredContactNo(converter.fromRow(row, prefix + "_insured_contact_no", String.class));
        entity.setInsuredDob(converter.fromRow(row, prefix + "_insured_dob", LocalDate.class));
        entity.setInsuredGender(converter.fromRow(row, prefix + "_insured_gender", String.class));
        entity.setOrderingProviderFirstName(converter.fromRow(row, prefix + "_ordering_provider_first_name", String.class));
        entity.setOrderingProviderLastName(converter.fromRow(row, prefix + "_ordering_provider_last_name", String.class));
        entity.setOrderingProviderNpi(converter.fromRow(row, prefix + "_ordering_provider_npi", String.class));
        entity.setPatientRelationshipInsured(converter.fromRow(row, prefix + "_patient_relationship_insured", String.class));
        entity.setPatientConditionEmployment(converter.fromRow(row, prefix + "_patient_condition_employment", String.class));
        entity.setPatientConditionAutoAccident(converter.fromRow(row, prefix + "_patient_condition_auto_accident", String.class));
        entity.setPatientConditionOtherAccident(converter.fromRow(row, prefix + "_patient_condition_other_accident", String.class));
        entity.setIsNextLevelInsurerPresentStatus(converter.fromRow(row, prefix + "_is_next_level_insurer_present_status", String.class));
        entity.setOriginalDos(converter.fromRow(row, prefix + "_original_dos", LocalDate.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setBillingProviderTaxonomy(converter.fromRow(row, prefix + "_billing_provider_taxonomy", String.class));
        entity.setServiceProviderNpi(converter.fromRow(row, prefix + "_service_provider_npi", String.class));
        entity.setServiceProviderOrganisationName(converter.fromRow(row, prefix + "_service_provider_organisation_name", String.class));
        entity.setServiceProviderAddressLine1(converter.fromRow(row, prefix + "_service_provider_address_line_1", String.class));
        entity.setServiceProviderAddressLine2(converter.fromRow(row, prefix + "_service_provider_address_line_2", String.class));
        entity.setServiceProviderCity(converter.fromRow(row, prefix + "_service_provider_city", String.class));
        entity.setServiceProviderState(converter.fromRow(row, prefix + "_service_provider_state", String.class));
        entity.setServiceProviderCountry(converter.fromRow(row, prefix + "_service_provider_country", String.class));
        entity.setServiceProviderZipCode(converter.fromRow(row, prefix + "_service_provider_zip_code", String.class));
        entity.setServiceProviderTaxonomy(converter.fromRow(row, prefix + "_service_provider_taxonomy", String.class));
        entity.setCms1500FormName(converter.fromRow(row, prefix + "_cms_1500_form_name", String.class));
        entity.setTradingPartnerAddressLine1(converter.fromRow(row, prefix + "_trading_partner_address_line_1", String.class));
        entity.setTradingPartnerAddressLine2(converter.fromRow(row, prefix + "_trading_partner_address_line_2", String.class));
        entity.setTradingPatnerCity(converter.fromRow(row, prefix + "_trading_patner_city", String.class));
        entity.setTradingPartnerState(converter.fromRow(row, prefix + "_trading_partner_state", String.class));
        entity.setTradingPartnerZip(converter.fromRow(row, prefix + "_trading_partner_zip", String.class));
        entity.setDiagnosisCodeType(converter.fromRow(row, prefix + "_diagnosis_code_type", String.class));
        entity.setPrimaryClaimSubmisionMasterUuid(converter.fromRow(row, prefix + "_primary_claim_submision_master_uuid", UUID.class));
        return entity;
    }
}
