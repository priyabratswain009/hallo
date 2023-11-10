package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link DeliveryTicket}, with proper type conversions.
 */
@Service
public class DeliveryTicketRowMapper implements BiFunction<Row, String, DeliveryTicket> {

    private final ColumnConverter converter;

    public DeliveryTicketRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DeliveryTicket} stored in the database.
     */
    @Override
    public DeliveryTicket apply(Row row, String prefix) {
        DeliveryTicket entity = new DeliveryTicket();
        entity.setDeliveryTicketId(converter.fromRow(row, prefix + "_delivery_ticket_id", Long.class));
        entity.setDeliveryTicketNo(converter.fromRow(row, prefix + "_delivery_ticket_no", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setGender(converter.fromRow(row, prefix + "_gender", String.class));
        entity.setAgeAsOnDate(converter.fromRow(row, prefix + "_age_as_on_date", Integer.class));
        entity.setPhone1(converter.fromRow(row, prefix + "_phone_1", String.class));
        entity.setPhone2(converter.fromRow(row, prefix + "_phone_2", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setDeliveryAddress1(converter.fromRow(row, prefix + "_delivery_address_1", String.class));
        entity.setDeliveryAddress2(converter.fromRow(row, prefix + "_delivery_address_2", String.class));
        entity.setDeliveryCity(converter.fromRow(row, prefix + "_delivery_city", String.class));
        entity.setDeliveryState(converter.fromRow(row, prefix + "_delivery_state", String.class));
        entity.setDeliveryZip(converter.fromRow(row, prefix + "_delivery_zip", String.class));
        entity.setDeliveryStatus(converter.fromRow(row, prefix + "_delivery_status", String.class));
        entity.setDeliveryDate(converter.fromRow(row, prefix + "_delivery_date", LocalDate.class));
        entity.setDeliveryPriority(converter.fromRow(row, prefix + "_delivery_priority", String.class));
        entity.setDeliveryNote(converter.fromRow(row, prefix + "_delivery_note", String.class));
        entity.setDeliveryComment(converter.fromRow(row, prefix + "_delivery_comment", String.class));
        entity.setDeliveryAcceptedBy(converter.fromRow(row, prefix + "_delivery_accepted_by", String.class));
        entity.setRelationshipWithPatient(converter.fromRow(row, prefix + "_relationship_with_patient", String.class));
        entity.setDeliveryAcceptedByContactNo(converter.fromRow(row, prefix + "_delivery_accepted_by_contact_no", String.class));
        entity.setPrimaryInsurerName(converter.fromRow(row, prefix + "_primary_insurer_name", String.class));
        entity.setPrimaryInsurerPolicyNo(converter.fromRow(row, prefix + "_primary_insurer_policy_no", String.class));
        entity.setPrimaryInsurerPatientIdNumber(converter.fromRow(row, prefix + "_primary_insurer_patient_id_number", String.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setBranchAddressLine1(converter.fromRow(row, prefix + "_branch_address_line_1", String.class));
        entity.setBranchAddressLine2(converter.fromRow(row, prefix + "_branch_address_line_2", String.class));
        entity.setBranchCity(converter.fromRow(row, prefix + "_branch_city", String.class));
        entity.setBranchState(converter.fromRow(row, prefix + "_branch_state", String.class));
        entity.setBranchZipCode(converter.fromRow(row, prefix + "_branch_zip_code", String.class));
        entity.setBranchContactNo1(converter.fromRow(row, prefix + "_branch_contact_no_1", String.class));
        entity.setBranchContactNo2(converter.fromRow(row, prefix + "_branch_contact_no_2", String.class));
        entity.setBranchNpi(converter.fromRow(row, prefix + "_branch_npi", String.class));
        entity.setBranchEin(converter.fromRow(row, prefix + "_branch_ein", String.class));
        entity.setBranchFax(converter.fromRow(row, prefix + "_branch_fax", String.class));
        entity.setOrderingProviderFirstName(converter.fromRow(row, prefix + "_ordering_provider_first_name", String.class));
        entity.setOrderingProviderMiddleName(converter.fromRow(row, prefix + "_ordering_provider_middle_name", String.class));
        entity.setOrderingProviderLastName(converter.fromRow(row, prefix + "_ordering_provider_last_name", String.class));
        entity.setOrderingProviderNpi(converter.fromRow(row, prefix + "_ordering_provider_npi", String.class));
        entity.setOrderingProviderAddressLine1(converter.fromRow(row, prefix + "_ordering_provider_address_line_1", String.class));
        entity.setOrderingProviderAddressLine2(converter.fromRow(row, prefix + "_ordering_provider_address_line_2", String.class));
        entity.setOrderingProviderCity(converter.fromRow(row, prefix + "_ordering_provider_city", String.class));
        entity.setOrderingProviderState(converter.fromRow(row, prefix + "_ordering_provider_state", String.class));
        entity.setOrderingProviderZip(converter.fromRow(row, prefix + "_ordering_provider_zip", String.class));
        entity.setOrderingProviderPhone1(converter.fromRow(row, prefix + "_ordering_provider_phone_1", String.class));
        entity.setOrderingProviderPhone2(converter.fromRow(row, prefix + "_ordering_provider_phone_2", String.class));
        entity.setOrderingProviderFax(converter.fromRow(row, prefix + "_ordering_provider_fax", String.class));
        entity.setOrderingProviderEmail(converter.fromRow(row, prefix + "_ordering_provider_email", String.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setPatientBranchId(converter.fromRow(row, prefix + "_patient_branch_id", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setDeliveryTicketUuid(converter.fromRow(row, prefix + "_delivery_ticket_uuid", UUID.class));
        entity.setBillingAddressLine1(converter.fromRow(row, prefix + "_billing_address_line_1", String.class));
        entity.setBillingAddressLine2(converter.fromRow(row, prefix + "_billing_address_line_2", String.class));
        entity.setBillingCity(converter.fromRow(row, prefix + "_billing_city", String.class));
        entity.setBillingState(converter.fromRow(row, prefix + "_billing_state", String.class));
        entity.setBillingZip(converter.fromRow(row, prefix + "_billing_zip", String.class));
        entity.setInventoryLocationId(converter.fromRow(row, prefix + "_inventory_location_id", Long.class));
        entity.setInventoryLocationName(converter.fromRow(row, prefix + "_inventory_location_name", String.class));
        entity.setDeliveryTicketDocumentId(converter.fromRow(row, prefix + "_delivery_ticket_document_id", Long.class));
        entity.setDeliveryTicketDocumentNo(converter.fromRow(row, prefix + "_delivery_ticket_document_no", String.class));
        entity.setDeliveryTicketDocumentName(converter.fromRow(row, prefix + "_delivery_ticket_document_name", String.class));
        entity.setDeliveryType(converter.fromRow(row, prefix + "_delivery_type", String.class));
        entity.setCarrierName(converter.fromRow(row, prefix + "_carrier_name", String.class));
        entity.setShippingDate(converter.fromRow(row, prefix + "_shipping_date", LocalDate.class));
        entity.setTrackingNo(converter.fromRow(row, prefix + "_tracking_no", String.class));
        entity.setReferenceNo(converter.fromRow(row, prefix + "_reference_no", String.class));
        entity.setPackageWeight(converter.fromRow(row, prefix + "_package_weight", String.class));
        entity.setSetupMethod(converter.fromRow(row, prefix + "_setup_method", String.class));
        entity.setSetupTechnicianNo(converter.fromRow(row, prefix + "_setup_technician_no", String.class));
        entity.setSetupTechnicianContactNo(converter.fromRow(row, prefix + "_setup_technician_contact_no", String.class));
        entity.setSetupTechnicianFirstName(converter.fromRow(row, prefix + "_setup_technician_first_name", String.class));
        entity.setSetupTechnicianMiddleName(converter.fromRow(row, prefix + "_setup_technician_middle_name", String.class));
        entity.setSetupTechnicianLastName(converter.fromRow(row, prefix + "_setup_technician_last_name", String.class));
        entity.setSetupDateTime(converter.fromRow(row, prefix + "_setup_date_time", LocalDate.class));
        entity.setScheduleSetupDateTime(converter.fromRow(row, prefix + "_schedule_setup_date_time", LocalDate.class));
        entity.setSetupComments(converter.fromRow(row, prefix + "_setup_comments", String.class));
        entity.setSetupStatus(converter.fromRow(row, prefix + "_setup_status", String.class));
        entity.setCourierPackageAcceptedBy(converter.fromRow(row, prefix + "_courier_package_accepted_by", String.class));
        entity.setTherapistFirstName(converter.fromRow(row, prefix + "_therapist_first_name", String.class));
        entity.setTherapistMiddleName(converter.fromRow(row, prefix + "_therapist_middle_name", String.class));
        entity.setTherapistLastName(converter.fromRow(row, prefix + "_therapist_last_name", String.class));
        entity.setTherapistLicenseNo(converter.fromRow(row, prefix + "_therapist_license_no", String.class));
        entity.setTherapistNpi(converter.fromRow(row, prefix + "_therapist_npi", String.class));
        entity.setTherapistTaxonomyCode(converter.fromRow(row, prefix + "_therapist_taxonomy_code", String.class));
        entity.setScheduleTherapyDate(converter.fromRow(row, prefix + "_schedule_therapy_date", LocalDate.class));
        entity.setActualTherapyDate(converter.fromRow(row, prefix + "_actual_therapy_date", LocalDate.class));
        entity.setTherapyMode(converter.fromRow(row, prefix + "_therapy_mode", String.class));
        entity.setTherapyStatus(converter.fromRow(row, prefix + "_therapy_status", String.class));
        entity.setTherapyNotes(converter.fromRow(row, prefix + "_therapy_notes", String.class));
        return entity;
    }
}
