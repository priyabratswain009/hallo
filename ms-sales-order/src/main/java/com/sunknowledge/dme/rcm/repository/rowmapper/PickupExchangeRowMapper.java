package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PickupExchange}, with proper type conversions.
 */
@Service
public class PickupExchangeRowMapper implements BiFunction<Row, String, PickupExchange> {

    private final ColumnConverter converter;

    public PickupExchangeRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PickupExchange} stored in the database.
     */
    @Override
    public PickupExchange apply(Row row, String prefix) {
        PickupExchange entity = new PickupExchange();
        entity.setPickupExchangeId(converter.fromRow(row, prefix + "_pickup_exchange_id", Long.class));
        entity.setPickupExchangeNo(converter.fromRow(row, prefix + "_pickup_exchange_no", String.class));
        entity.setPickupExchangeType(converter.fromRow(row, prefix + "_pickup_exchange_type", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setBranchId(converter.fromRow(row, prefix + "_branch_id", Long.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setInventoryLocationId(converter.fromRow(row, prefix + "_inventory_location_id", Long.class));
        entity.setInventoryLocationName(converter.fromRow(row, prefix + "_inventory_location_name", String.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setPatientContact1(converter.fromRow(row, prefix + "_patient_contact_1", String.class));
        entity.setPatientContact2(converter.fromRow(row, prefix + "_patient_contact_2", String.class));
        entity.setPatientBillingAddressLine1(converter.fromRow(row, prefix + "_patient_billing_address_line_1", String.class));
        entity.setPatientBillingAddressLine2(converter.fromRow(row, prefix + "_patient_billing_address_line_2", String.class));
        entity.setPatientBillingAddressState(converter.fromRow(row, prefix + "_patient_billing_address_state", String.class));
        entity.setPatientBillingAddressCity(converter.fromRow(row, prefix + "_patient_billing_address_city", String.class));
        entity.setPatientBillingAddressZip(converter.fromRow(row, prefix + "_patient_billing_address_zip", String.class));
        entity.setPatientDeliveyAddressLine1(converter.fromRow(row, prefix + "_patient_delivey_address_line_1", String.class));
        entity.setPatientDeliveyAddressLine2(converter.fromRow(row, prefix + "_patient_delivey_address_line_2", String.class));
        entity.setPatientDeliveyAddressState(converter.fromRow(row, prefix + "_patient_delivey_address_state", String.class));
        entity.setPatientDeliveyAddressCity(converter.fromRow(row, prefix + "_patient_delivey_address_city", String.class));
        entity.setPatientDeliveyAddressZip(converter.fromRow(row, prefix + "_patient_delivey_address_zip", String.class));
        entity.setPickupExchangeScheduleDateTime(converter.fromRow(row, prefix + "_pickup_exchange_schedule_date_time", LocalDate.class));
        entity.setPickupExchangeActualDateTime(converter.fromRow(row, prefix + "_pickup_exchange_actual_date_time", LocalDate.class));
        entity.setPickupExchangeReason(converter.fromRow(row, prefix + "_pickup_exchange_reason", String.class));
        entity.setPickupExchangeRequest(converter.fromRow(row, prefix + "_pickup_exchange_request", String.class));
        entity.setPickupExchangeNote(converter.fromRow(row, prefix + "_pickup_exchange_note", String.class));
        entity.setPickupExchangeAgentIdNo(converter.fromRow(row, prefix + "_pickup_exchange_agent_id_no", String.class));
        entity.setPickupExchangeAgentName(converter.fromRow(row, prefix + "_pickup_exchange_agent_name", String.class));
        entity.setPickupExchangeDocumentId(converter.fromRow(row, prefix + "_pickup_exchange_document_id", String.class));
        entity.setPickupExchangeDocumentNo(converter.fromRow(row, prefix + "_pickup_exchange_document_no", String.class));
        entity.setPickupExchangeDocumentName(converter.fromRow(row, prefix + "_pickup_exchange_document_name", String.class));
        entity.setPickupExchangeStatus(converter.fromRow(row, prefix + "_pickup_exchange_status", String.class));
        entity.setPickupExchangeComments(converter.fromRow(row, prefix + "_pickup_exchange_comments", String.class));
        entity.setIsPatientSigned(converter.fromRow(row, prefix + "_is_patient_signed", String.class));
        entity.setRelationshipWithPatient(converter.fromRow(row, prefix + "_relationship_with_patient", String.class));
        entity.setPatientSignedDateTime(converter.fromRow(row, prefix + "_patient_signed_date_time", LocalDate.class));
        entity.setIsAgentSigned(converter.fromRow(row, prefix + "_is_agent_signed", String.class));
        entity.setLastBillingDate(converter.fromRow(row, prefix + "_last_billing_date", LocalDate.class));
        entity.setDateOfDeath(converter.fromRow(row, prefix + "_date_of_death", LocalDate.class));
        entity.setPickupExchangeSupportingDocument1(
            converter.fromRow(row, prefix + "_pickup_exchange_supporting_document_1", String.class)
        );
        entity.setPickupExchangeSupportingDocument2(
            converter.fromRow(row, prefix + "_pickup_exchange_supporting_document_2", String.class)
        );
        entity.setPatientNotsignedReason(converter.fromRow(row, prefix + "_patient_notsigned_reason", String.class));
        entity.setPickupExchangeJsonData(converter.fromRow(row, prefix + "_pickup_exchange_json_data", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPickupExchangeUuid(converter.fromRow(row, prefix + "_pickup_exchange_uuid", UUID.class));
        return entity;
    }
}
