package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderMaster}, with proper type conversions.
 */
@Service
public class SalesOrderMasterRowMapper implements BiFunction<Row, String, SalesOrderMaster> {

    private final ColumnConverter converter;

    public SalesOrderMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderMaster} stored in the database.
     */
    @Override
    public SalesOrderMaster apply(Row row, String prefix) {
        SalesOrderMaster entity = new SalesOrderMaster();
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setSalesOrderNo(converter.fromRow(row, prefix + "_sales_order_no", String.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setPatientAddressLine1(converter.fromRow(row, prefix + "_patient_address_line_1", String.class));
        entity.setPatientAddressLine2(converter.fromRow(row, prefix + "_patient_address_line_2", String.class));
        entity.setPatientContactNo1(converter.fromRow(row, prefix + "_patient_contact_no_1", String.class));
        entity.setPatientContactNo2(converter.fromRow(row, prefix + "_patient_contact_no_2", String.class));
        entity.setPatientDob(converter.fromRow(row, prefix + "_patient_dob", LocalDate.class));
        entity.setPatientHeight(converter.fromRow(row, prefix + "_patient_height", Double.class));
        entity.setPatientWeight(converter.fromRow(row, prefix + "_patient_weight", Double.class));
        entity.setPatientSsn(converter.fromRow(row, prefix + "_patient_ssn", String.class));
        entity.setPatientGender(converter.fromRow(row, prefix + "_patient_gender", String.class));
        entity.setHipaaOnFileStatus(converter.fromRow(row, prefix + "_hipaa_on_file_status", String.class));
        entity.setPatientBranchId(converter.fromRow(row, prefix + "_patient_branch_id", Long.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setDeliveryScheduleDatetime(converter.fromRow(row, prefix + "_delivery_schedule_datetime", LocalDate.class));
        entity.setDeliveryActualDatetime(converter.fromRow(row, prefix + "_delivery_actual_datetime", LocalDate.class));
        entity.setDeliveryAddressLine1(converter.fromRow(row, prefix + "_delivery_address_line_1", String.class));
        entity.setDeliveryAddressLine2(converter.fromRow(row, prefix + "_delivery_address_line_2", String.class));
        entity.setDeliveryPhoneNo1(converter.fromRow(row, prefix + "_delivery_phone_no_1", String.class));
        entity.setDeliveryPhoneNo2(converter.fromRow(row, prefix + "_delivery_phone_no_2", String.class));
        entity.setFacilityId(converter.fromRow(row, prefix + "_facility_id", Long.class));
        entity.setFacilityName(converter.fromRow(row, prefix + "_facility_name", String.class));
        entity.setFacilityNpi(converter.fromRow(row, prefix + "_facility_npi", String.class));
        entity.setTaxZoneId(converter.fromRow(row, prefix + "_tax_zone_id", Long.class));
        entity.setTaxRate(converter.fromRow(row, prefix + "_tax_rate", Double.class));
        entity.setSalesOrderNote(converter.fromRow(row, prefix + "_sales_order_note", String.class));
        entity.setDeliveryNote(converter.fromRow(row, prefix + "_delivery_note", String.class));
        entity.setDeliveryTechnician(converter.fromRow(row, prefix + "_delivery_technician", String.class));
        entity.setSignatureRequiredStatus(converter.fromRow(row, prefix + "_signature_required_status", String.class));
        entity.setPodStatus(converter.fromRow(row, prefix + "_pod_status", String.class));
        entity.setPodStatusDatetime(converter.fromRow(row, prefix + "_pod_status_datetime", LocalDate.class));
        entity.setPodLastMessage(converter.fromRow(row, prefix + "_pod_last_message", String.class));
        entity.setPodMessageDatetime(converter.fromRow(row, prefix + "_pod_message_datetime", LocalDate.class));
        entity.setMutualHoldStatus(converter.fromRow(row, prefix + "_mutual_hold_status", String.class));
        entity.setHoldStatus(converter.fromRow(row, prefix + "_hold_status", String.class));
        entity.setHoldReasonDescription(converter.fromRow(row, prefix + "_hold_reason_description", String.class));
        entity.setStopDate(converter.fromRow(row, prefix + "_stop_date", LocalDate.class));
        entity.setStopReasonDescription(converter.fromRow(row, prefix + "_stop_reason_description", String.class));
        entity.setBranchId(converter.fromRow(row, prefix + "_branch_id", Long.class));
        entity.setBillingBranchName(converter.fromRow(row, prefix + "_billing_branch_name", String.class));
        entity.setInventoryLocationId(converter.fromRow(row, prefix + "_inventory_location_id", Long.class));
        entity.setOrderStatus(converter.fromRow(row, prefix + "_order_status", String.class));
        entity.setOrderClassificationId(converter.fromRow(row, prefix + "_order_classification_id", Long.class));
        entity.setPosId(converter.fromRow(row, prefix + "_pos_id", Long.class));
        entity.setAdmissionDate(converter.fromRow(row, prefix + "_admission_date", LocalDate.class));
        entity.setDischargeDate(converter.fromRow(row, prefix + "_discharge_date", LocalDate.class));
        entity.setDiscountPercentage(converter.fromRow(row, prefix + "_discount_percentage", Long.class));
        entity.setPoNumber(converter.fromRow(row, prefix + "_po_number", String.class));
        entity.setUserField1(converter.fromRow(row, prefix + "_user_field_1", String.class));
        entity.setUserField2(converter.fromRow(row, prefix + "_user_field_2", String.class));
        entity.setUserField3(converter.fromRow(row, prefix + "_user_field_3", String.class));
        entity.setUserField4(converter.fromRow(row, prefix + "_user_field_4", String.class));
        entity.setReference(converter.fromRow(row, prefix + "_reference", String.class));
        entity.setWipStatus(converter.fromRow(row, prefix + "_wip_status", String.class));
        entity.setWipDaysInState(converter.fromRow(row, prefix + "_wip_days_in_state", Long.class));
        entity.setWipAssignedToId(converter.fromRow(row, prefix + "_wip_assigned_to_id", Long.class));
        entity.setWipDateNeeded(converter.fromRow(row, prefix + "_wip_date_needed", LocalDate.class));
        entity.setCompletedStatus(converter.fromRow(row, prefix + "_completed_status", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCityName(converter.fromRow(row, prefix + "_city_name", String.class));
        entity.setStateName(converter.fromRow(row, prefix + "_state_name", String.class));
        entity.setZipCode(converter.fromRow(row, prefix + "_zip_code", String.class));
        entity.setDeliveryCityName(converter.fromRow(row, prefix + "_delivery_city_name", String.class));
        entity.setDeliveryStateName(converter.fromRow(row, prefix + "_delivery_state_name", String.class));
        entity.setDeliveryZipCode(converter.fromRow(row, prefix + "_delivery_zip_code", String.class));
        entity.setPatientDod(converter.fromRow(row, prefix + "_patient_dod", LocalDate.class));
        entity.setPosName(converter.fromRow(row, prefix + "_pos_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setConfirmationById(converter.fromRow(row, prefix + "_confirmation_by_id", Long.class));
        entity.setConfirmationByName(converter.fromRow(row, prefix + "_confirmation_by_name", String.class));
        entity.setConfirmationDate(converter.fromRow(row, prefix + "_confirmation_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setSalesOrderMasterUuid(converter.fromRow(row, prefix + "_sales_order_master_uuid", UUID.class));
        entity.setBranchContactPersonName(converter.fromRow(row, prefix + "_branch_contact_person_name", String.class));
        entity.setBranchNpi(converter.fromRow(row, prefix + "_branch_npi", String.class));
        entity.setBranchEin(converter.fromRow(row, prefix + "_branch_ein", String.class));
        entity.setBranchContactNo1(converter.fromRow(row, prefix + "_branch_contact_no_1", String.class));
        entity.setBranchContactNo2(converter.fromRow(row, prefix + "_branch_contact_no_2", String.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setPosCode(converter.fromRow(row, prefix + "_pos_code", String.class));
        entity.setEclaimCarrierName(converter.fromRow(row, prefix + "_eclaim_carrier_name", String.class));
        entity.setPlanParticipationCode(converter.fromRow(row, prefix + "_plan_participation_code", String.class));
        entity.setPatientMemberId(converter.fromRow(row, prefix + "_patient_member_id", String.class));
        entity.setContactPersonName(converter.fromRow(row, prefix + "_contact_person_name", String.class));
        entity.setProviderType(converter.fromRow(row, prefix + "_provider_type", String.class));
        entity.setBranchAddressLine1(converter.fromRow(row, prefix + "_branch_address_line_1", String.class));
        entity.setBranchAddressLine2(converter.fromRow(row, prefix + "_branch_address_line_2", String.class));
        entity.setBranchCity(converter.fromRow(row, prefix + "_branch_city", String.class));
        entity.setBranchState(converter.fromRow(row, prefix + "_branch_state", String.class));
        entity.setBranchZipCode(converter.fromRow(row, prefix + "_branch_zip_code", String.class));
        entity.setPatientDeliveryAddressLine1(converter.fromRow(row, prefix + "_patient_delivery_address_line_1", String.class));
        entity.setPatientDeliveryAddressLine2(converter.fromRow(row, prefix + "_patient_delivery_address_line_2", String.class));
        entity.setPatientDeliveryCity(converter.fromRow(row, prefix + "_patient_delivery_city", String.class));
        entity.setPatientDeliveryState(converter.fromRow(row, prefix + "_patient_delivery_state", String.class));
        entity.setPatientDeliveryCountry(converter.fromRow(row, prefix + "_patient_delivery_country", String.class));
        entity.setPatientDeliveryZip(converter.fromRow(row, prefix + "_patient_delivery_zip", String.class));
        entity.setPatientBillingAddressLine1(converter.fromRow(row, prefix + "_patient_billing_address_line_1", String.class));
        entity.setPatientBillingAddressLine2(converter.fromRow(row, prefix + "_patient_billing_address_line_2", String.class));
        entity.setPatientBillingCity(converter.fromRow(row, prefix + "_patient_billing_city", String.class));
        entity.setPatientBillingState(converter.fromRow(row, prefix + "_patient_billing_state", String.class));
        entity.setPatientBillingCountry(converter.fromRow(row, prefix + "_patient_billing_country", String.class));
        entity.setPatientBillingZip(converter.fromRow(row, prefix + "_patient_billing_zip", String.class));
        entity.setPatientFax(converter.fromRow(row, prefix + "_patient_fax", String.class));
        entity.setBranchFax(converter.fromRow(row, prefix + "_branch_fax", String.class));
        entity.setPatientEmail(converter.fromRow(row, prefix + "_patient_email", String.class));
        entity.setRelationship(converter.fromRow(row, prefix + "_relationship", String.class));
        entity.setModeOfContact(converter.fromRow(row, prefix + "_mode_of_contact", String.class));
        entity.setInsuredFirstName(converter.fromRow(row, prefix + "_insured_first_name", String.class));
        entity.setInsuredLastName(converter.fromRow(row, prefix + "_insured_last_name", String.class));
        entity.setInsuredAddressLine1(converter.fromRow(row, prefix + "_insured_address_line_1", String.class));
        entity.setInsuredAddressLine2(converter.fromRow(row, prefix + "_insured_address_line_2", String.class));
        entity.setInsuredCity(converter.fromRow(row, prefix + "_insured_city", String.class));
        entity.setInsuredState(converter.fromRow(row, prefix + "_insured_state", String.class));
        entity.setInsuredZip(converter.fromRow(row, prefix + "_insured_zip", String.class));
        entity.setInsuredContactNo(converter.fromRow(row, prefix + "_insured_contact_no", String.class));
        entity.setInsuredGender(converter.fromRow(row, prefix + "_insured_gender", String.class));
        entity.setPatientRelationshipInsured(converter.fromRow(row, prefix + "_patient_relationship_insured", String.class));
        entity.setPatientConditionEmployment(converter.fromRow(row, prefix + "_patient_condition_employment", String.class));
        entity.setPatientConditionAutoAccident(converter.fromRow(row, prefix + "_patient_condition_auto_accident", String.class));
        entity.setPatientConditionOtherAccident(converter.fromRow(row, prefix + "_patient_condition_other_accident", String.class));
        entity.setBillingProviderTaxonomy(converter.fromRow(row, prefix + "_billing_provider_taxonomy", String.class));
        entity.setBillingProviderNpi(converter.fromRow(row, prefix + "_billing_provider_npi", String.class));
        entity.setBillingProviderOrganisationName(converter.fromRow(row, prefix + "_billing_provider_organisation_name", String.class));
        entity.setBillingProviderAddressLine1(converter.fromRow(row, prefix + "_billing_provider_address_line_1", String.class));
        entity.setBillingProviderAddressLine2(converter.fromRow(row, prefix + "_billing_provider_address_line_2", String.class));
        entity.setBillingProviderCity(converter.fromRow(row, prefix + "_billing_provider_city", String.class));
        entity.setBillingProviderState(converter.fromRow(row, prefix + "_billing_provider_state", String.class));
        entity.setBillingProviderCountry(converter.fromRow(row, prefix + "_billing_provider_country", String.class));
        entity.setBillingProviderZipCode(converter.fromRow(row, prefix + "_billing_provider_zip_code", String.class));
        entity.setInsuredDob(converter.fromRow(row, prefix + "_insured_dob", LocalDate.class));
        entity.setBranchCountry(converter.fromRow(row, prefix + "_branch_country", String.class));
        entity.setBranchTaxonomy(converter.fromRow(row, prefix + "_branch_taxonomy", String.class));
        entity.setPrimaryInsurerPriceTableId(converter.fromRow(row, prefix + "_primary_insurer_price_table_id", Long.class));
        entity.setPrimaryInsurerPriceTableName(converter.fromRow(row, prefix + "_primary_insurer_price_table_name", String.class));
        entity.setInventoryLocationName(converter.fromRow(row, prefix + "_inventory_location_name", String.class));
        entity.setSoControlNumber(converter.fromRow(row, prefix + "_so_control_number", String.class));
        return entity;
    }
}
