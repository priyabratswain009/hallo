package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderClinicalDetails}, with proper type conversions.
 */
@Service
public class SalesOrderClinicalDetailsRowMapper implements BiFunction<Row, String, SalesOrderClinicalDetails> {

    private final ColumnConverter converter;

    public SalesOrderClinicalDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderClinicalDetails} stored in the database.
     */
    @Override
    public SalesOrderClinicalDetails apply(Row row, String prefix) {
        SalesOrderClinicalDetails entity = new SalesOrderClinicalDetails();
        entity.setSalesOrderClinicalDetailsId(converter.fromRow(row, prefix + "_sales_order_clinical_details_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientWeightInKg(converter.fromRow(row, prefix + "_patient_weight_in_kg", Double.class));
        entity.setPatientWeightInLbs(converter.fromRow(row, prefix + "_patient_weight_in_lbs", Double.class));
        entity.setHeightInInches(converter.fromRow(row, prefix + "_height_in_inches", Double.class));
        entity.setHeightInCm(converter.fromRow(row, prefix + "_height_in_cm", Double.class));
        entity.setSalesRepId(converter.fromRow(row, prefix + "_sales_rep_id", Long.class));
        entity.setSalesRepName(converter.fromRow(row, prefix + "_sales_rep_name", String.class));
        entity.setRenderingProviderFacilityId(converter.fromRow(row, prefix + "_rendering_provider_facility_id", Long.class));
        entity.setRenderingProviderFacilityName(converter.fromRow(row, prefix + "_rendering_provider_facility_name", String.class));
        entity.setRenderingProviderId(converter.fromRow(row, prefix + "_rendering_provider_id", Long.class));
        entity.setRenderingProviderType(converter.fromRow(row, prefix + "_rendering_provider_type", String.class));
        entity.setRenderingProviderFirstName(converter.fromRow(row, prefix + "_rendering_provider_first_name", String.class));
        entity.setRenderingProviderMiddleName(converter.fromRow(row, prefix + "_rendering_provider_middle_name", String.class));
        entity.setRenderingProviderLastName(converter.fromRow(row, prefix + "_rendering_provider_last_name", String.class));
        entity.setRenderingProviderNpi(converter.fromRow(row, prefix + "_rendering_provider_npi", String.class));
        entity.setRenderingProviderDea(converter.fromRow(row, prefix + "_rendering_provider_dea", String.class));
        entity.setRenderingProviderAddressLine1(converter.fromRow(row, prefix + "_rendering_provider_address_line_1", String.class));
        entity.setRenderingProviderAddressLine2(converter.fromRow(row, prefix + "_rendering_provider_address_line_2", String.class));
        entity.setRenderingProviderEmail(converter.fromRow(row, prefix + "_rendering_provider_email", String.class));
        entity.setRenderingProviderFax(converter.fromRow(row, prefix + "_rendering_provider_fax", String.class));
        entity.setReferringProviderFacilityId(converter.fromRow(row, prefix + "_referring_provider_facility_id", Long.class));
        entity.setReferringProviderFacilityName(converter.fromRow(row, prefix + "_referring_provider_facility_name", String.class));
        entity.setReferringProviderId(converter.fromRow(row, prefix + "_referring_provider_id", Long.class));
        entity.setReferringProviderType(converter.fromRow(row, prefix + "_referring_provider_type", String.class));
        entity.setReferringProviderFirstName(converter.fromRow(row, prefix + "_referring_provider_first_name", String.class));
        entity.setReferringProviderMiddleName(converter.fromRow(row, prefix + "_referring_provider_middle_name", String.class));
        entity.setReferringProviderLastName(converter.fromRow(row, prefix + "_referring_provider_last_name", String.class));
        entity.setReferringProviderNpi(converter.fromRow(row, prefix + "_referring_provider_npi", String.class));
        entity.setReferringProviderDea(converter.fromRow(row, prefix + "_referring_provider_dea", String.class));
        entity.setReferringProviderAddressLine1(converter.fromRow(row, prefix + "_referring_provider_address_line_1", String.class));
        entity.setReferringProviderAddressLine2(converter.fromRow(row, prefix + "_referring_provider_address_line_2", String.class));
        entity.setReferringProviderEmail(converter.fromRow(row, prefix + "_referring_provider_email", String.class));
        entity.setReferringProviderFax(converter.fromRow(row, prefix + "_referring_provider_fax", String.class));
        entity.setOrderingProviderFacilityId(converter.fromRow(row, prefix + "_ordering_provider_facility_id", Long.class));
        entity.setOrderingProviderFacilityName(converter.fromRow(row, prefix + "_ordering_provider_facility_name", String.class));
        entity.setOrderingProviderId(converter.fromRow(row, prefix + "_ordering_provider_id", Long.class));
        entity.setOrderingProviderType(converter.fromRow(row, prefix + "_ordering_provider_type", String.class));
        entity.setOrderingProviderFirstName(converter.fromRow(row, prefix + "_ordering_provider_first_name", String.class));
        entity.setOrderingProviderMiddleName(converter.fromRow(row, prefix + "_ordering_provider_middle_name", String.class));
        entity.setOrderingProviderLastName(converter.fromRow(row, prefix + "_ordering_provider_last_name", String.class));
        entity.setOrderingProviderNpi(converter.fromRow(row, prefix + "_ordering_provider_npi", String.class));
        entity.setOrderingProviderDea(converter.fromRow(row, prefix + "_ordering_provider_dea", String.class));
        entity.setOrderingProviderAddressLine1(converter.fromRow(row, prefix + "_ordering_provider_address_line_1", String.class));
        entity.setOrderingProviderAddressLine2(converter.fromRow(row, prefix + "_ordering_provider_address_line_2", String.class));
        entity.setOrderingProviderEmail(converter.fromRow(row, prefix + "_ordering_provider_email", String.class));
        entity.setOrderingProviderFax(converter.fromRow(row, prefix + "_ordering_provider_fax", String.class));
        entity.setMarketingReferralTypeId(converter.fromRow(row, prefix + "_marketing_referral_type_id", Long.class));
        entity.setMarketingReferralTypeDescription(converter.fromRow(row, prefix + "_marketing_referral_type_description", String.class));
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
        entity.setEpsdtCertificationConditionIndicator(
            converter.fromRow(row, prefix + "_epsdt_certification_condition_indicator", String.class)
        );
        entity.setEpsdtCertificationCode(converter.fromRow(row, prefix + "_epsdt_certification_code", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setRenderingProviderZip(converter.fromRow(row, prefix + "_rendering_provider_zip", String.class));
        entity.setReferringProviderZip(converter.fromRow(row, prefix + "_referring_provider_zip", String.class));
        entity.setOrderingProviderZip(converter.fromRow(row, prefix + "_ordering_provider_zip", String.class));
        entity.setMarketingReferralId(converter.fromRow(row, prefix + "_marketing_referral_id", Long.class));
        entity.setMarketingReferralName(converter.fromRow(row, prefix + "_marketing_referral_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setSalesOrderClinicalDetailsUuid(converter.fromRow(row, prefix + "_sales_order_clinical_details_uuid", UUID.class));
        entity.setPrimaryDiagnosis(converter.fromRow(row, prefix + "_primary_diagnosis", String.class));
        entity.setOrderingProviderCity(converter.fromRow(row, prefix + "_ordering_provider_city", String.class));
        entity.setOrderingProviderState(converter.fromRow(row, prefix + "_ordering_provider_state", String.class));
        entity.setOrderingProviderCountry(converter.fromRow(row, prefix + "_ordering_provider_country", String.class));
        entity.setOrderingProviderContactNo1(converter.fromRow(row, prefix + "_ordering_provider_contact_no_1", String.class));
        entity.setOrderingProviderContactNo2(converter.fromRow(row, prefix + "_ordering_provider_contact_no_2", String.class));
        entity.setOrderingProviderEfax(converter.fromRow(row, prefix + "_ordering_provider_efax", String.class));
        entity.setRelationship(converter.fromRow(row, prefix + "_relationship", String.class));
        entity.setModeOfContact(converter.fromRow(row, prefix + "_mode_of_contact", String.class));
        entity.setReferringProviderCity(converter.fromRow(row, prefix + "_referring_provider_city", String.class));
        entity.setReferringProviderState(converter.fromRow(row, prefix + "_referring_provider_state", String.class));
        entity.setReferringProviderCountry(converter.fromRow(row, prefix + "_referring_provider_country", String.class));
        entity.setReferringProviderContactNo1(converter.fromRow(row, prefix + "_referring_provider_contact_no_1", String.class));
        entity.setReferringProviderContactNo2(converter.fromRow(row, prefix + "_referring_provider_contact_no_2", String.class));
        entity.setReferringProviderEfax(converter.fromRow(row, prefix + "_referring_provider_efax", String.class));
        entity.setRenderingProviderCity(converter.fromRow(row, prefix + "_rendering_provider_city", String.class));
        entity.setRenderingProviderState(converter.fromRow(row, prefix + "_rendering_provider_state", String.class));
        entity.setRenderingProviderCountry(converter.fromRow(row, prefix + "_rendering_provider_country", String.class));
        entity.setRenderingProviderContactNo1(converter.fromRow(row, prefix + "_rendering_provider_contact_no_1", String.class));
        entity.setRenderingProviderContactNo2(converter.fromRow(row, prefix + "_rendering_provider_contact_no_2", String.class));
        entity.setRenderingProviderEfax(converter.fromRow(row, prefix + "_rendering_provider_efax", String.class));
        entity.setDiagnosisCodeType(converter.fromRow(row, prefix + "_diagnosis_code_type", String.class));
        return entity;
    }
}
