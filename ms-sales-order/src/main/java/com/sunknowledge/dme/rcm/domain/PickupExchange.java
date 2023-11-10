package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PickupExchange.
 */
@Table("t_pickup_exchange")
public class PickupExchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("pickup_exchange_id")
    private Long pickupExchangeId;

    @Column("pickup_exchange_no")
    private String pickupExchangeNo;

    @Column("pickup_exchange_type")
    private String pickupExchangeType;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("branch_id")
    private Long branchId;

    @Column("branch_name")
    private String branchName;

    @Column("inventory_location_id")
    private Long inventoryLocationId;

    @Column("inventory_location_name")
    private String inventoryLocationName;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("patient_contact_1")
    private String patientContact1;

    @Column("patient_contact_2")
    private String patientContact2;

    @Column("patient_billing_address_line_1")
    private String patientBillingAddressLine1;

    @Column("patient_billing_address_line_2")
    private String patientBillingAddressLine2;

    @Column("patient_billing_address_state")
    private String patientBillingAddressState;

    @Column("patient_billing_address_city")
    private String patientBillingAddressCity;

    @Column("patient_billing_address_zip")
    private String patientBillingAddressZip;

    @Column("patient_delivey_address_line_1")
    private String patientDeliveyAddressLine1;

    @Column("patient_delivey_address_line_2")
    private String patientDeliveyAddressLine2;

    @Column("patient_delivey_address_state")
    private String patientDeliveyAddressState;

    @Column("patient_delivey_address_city")
    private String patientDeliveyAddressCity;

    @Column("patient_delivey_address_zip")
    private String patientDeliveyAddressZip;

    @Column("pickup_exchange_schedule_date_time")
    private LocalDate pickupExchangeScheduleDateTime;

    @Column("pickup_exchange_actual_date_time")
    private LocalDate pickupExchangeActualDateTime;

    @Column("pickup_exchange_reason")
    private String pickupExchangeReason;

    @Column("pickup_exchange_request")
    private String pickupExchangeRequest;

    @Column("pickup_exchange_note")
    private String pickupExchangeNote;

    @Column("pickup_exchange_agent_id_no")
    private String pickupExchangeAgentIdNo;

    @Column("pickup_exchange_agent_name")
    private String pickupExchangeAgentName;

    @Column("pickup_exchange_document_id")
    private String pickupExchangeDocumentId;

    @Column("pickup_exchange_document_no")
    private String pickupExchangeDocumentNo;

    @Column("pickup_exchange_document_name")
    private String pickupExchangeDocumentName;

    @Column("pickup_exchange_status")
    private String pickupExchangeStatus;

    @Column("pickup_exchange_comments")
    private String pickupExchangeComments;

    @Column("is_patient_signed")
    private String isPatientSigned;

    @Column("relationship_with_patient")
    private String relationshipWithPatient;

    @Column("patient_signed_date_time")
    private LocalDate patientSignedDateTime;

    @Column("is_agent_signed")
    private String isAgentSigned;

    @Column("last_billing_date")
    private LocalDate lastBillingDate;

    @Column("date_of_death")
    private LocalDate dateOfDeath;

    @Column("pickup_exchange_supporting_document_1")
    private String pickupExchangeSupportingDocument1;

    @Column("pickup_exchange_supporting_document_2")
    private String pickupExchangeSupportingDocument2;

    @Column("patient_notsigned_reason")
    private String patientNotsignedReason;

    @Column("pickup_exchange_json_data")
    private String pickupExchangeJsonData;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("pickup_exchange_uuid")
    private UUID pickupExchangeUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPickupExchangeId() {
        return this.pickupExchangeId;
    }

    public PickupExchange pickupExchangeId(Long pickupExchangeId) {
        this.setPickupExchangeId(pickupExchangeId);
        return this;
    }

    public void setPickupExchangeId(Long pickupExchangeId) {
        this.pickupExchangeId = pickupExchangeId;
    }

    public String getPickupExchangeNo() {
        return this.pickupExchangeNo;
    }

    public PickupExchange pickupExchangeNo(String pickupExchangeNo) {
        this.setPickupExchangeNo(pickupExchangeNo);
        return this;
    }

    public void setPickupExchangeNo(String pickupExchangeNo) {
        this.pickupExchangeNo = pickupExchangeNo;
    }

    public String getPickupExchangeType() {
        return this.pickupExchangeType;
    }

    public PickupExchange pickupExchangeType(String pickupExchangeType) {
        this.setPickupExchangeType(pickupExchangeType);
        return this;
    }

    public void setPickupExchangeType(String pickupExchangeType) {
        this.pickupExchangeType = pickupExchangeType;
    }

    public Long getSoId() {
        return this.soId;
    }

    public PickupExchange soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public PickupExchange soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public PickupExchange branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public PickupExchange branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getInventoryLocationId() {
        return this.inventoryLocationId;
    }

    public PickupExchange inventoryLocationId(Long inventoryLocationId) {
        this.setInventoryLocationId(inventoryLocationId);
        return this;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getInventoryLocationName() {
        return this.inventoryLocationName;
    }

    public PickupExchange inventoryLocationName(String inventoryLocationName) {
        this.setInventoryLocationName(inventoryLocationName);
        return this;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PickupExchange patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public PickupExchange patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public PickupExchange patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public PickupExchange patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public PickupExchange patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientContact1() {
        return this.patientContact1;
    }

    public PickupExchange patientContact1(String patientContact1) {
        this.setPatientContact1(patientContact1);
        return this;
    }

    public void setPatientContact1(String patientContact1) {
        this.patientContact1 = patientContact1;
    }

    public String getPatientContact2() {
        return this.patientContact2;
    }

    public PickupExchange patientContact2(String patientContact2) {
        this.setPatientContact2(patientContact2);
        return this;
    }

    public void setPatientContact2(String patientContact2) {
        this.patientContact2 = patientContact2;
    }

    public String getPatientBillingAddressLine1() {
        return this.patientBillingAddressLine1;
    }

    public PickupExchange patientBillingAddressLine1(String patientBillingAddressLine1) {
        this.setPatientBillingAddressLine1(patientBillingAddressLine1);
        return this;
    }

    public void setPatientBillingAddressLine1(String patientBillingAddressLine1) {
        this.patientBillingAddressLine1 = patientBillingAddressLine1;
    }

    public String getPatientBillingAddressLine2() {
        return this.patientBillingAddressLine2;
    }

    public PickupExchange patientBillingAddressLine2(String patientBillingAddressLine2) {
        this.setPatientBillingAddressLine2(patientBillingAddressLine2);
        return this;
    }

    public void setPatientBillingAddressLine2(String patientBillingAddressLine2) {
        this.patientBillingAddressLine2 = patientBillingAddressLine2;
    }

    public String getPatientBillingAddressState() {
        return this.patientBillingAddressState;
    }

    public PickupExchange patientBillingAddressState(String patientBillingAddressState) {
        this.setPatientBillingAddressState(patientBillingAddressState);
        return this;
    }

    public void setPatientBillingAddressState(String patientBillingAddressState) {
        this.patientBillingAddressState = patientBillingAddressState;
    }

    public String getPatientBillingAddressCity() {
        return this.patientBillingAddressCity;
    }

    public PickupExchange patientBillingAddressCity(String patientBillingAddressCity) {
        this.setPatientBillingAddressCity(patientBillingAddressCity);
        return this;
    }

    public void setPatientBillingAddressCity(String patientBillingAddressCity) {
        this.patientBillingAddressCity = patientBillingAddressCity;
    }

    public String getPatientBillingAddressZip() {
        return this.patientBillingAddressZip;
    }

    public PickupExchange patientBillingAddressZip(String patientBillingAddressZip) {
        this.setPatientBillingAddressZip(patientBillingAddressZip);
        return this;
    }

    public void setPatientBillingAddressZip(String patientBillingAddressZip) {
        this.patientBillingAddressZip = patientBillingAddressZip;
    }

    public String getPatientDeliveyAddressLine1() {
        return this.patientDeliveyAddressLine1;
    }

    public PickupExchange patientDeliveyAddressLine1(String patientDeliveyAddressLine1) {
        this.setPatientDeliveyAddressLine1(patientDeliveyAddressLine1);
        return this;
    }

    public void setPatientDeliveyAddressLine1(String patientDeliveyAddressLine1) {
        this.patientDeliveyAddressLine1 = patientDeliveyAddressLine1;
    }

    public String getPatientDeliveyAddressLine2() {
        return this.patientDeliveyAddressLine2;
    }

    public PickupExchange patientDeliveyAddressLine2(String patientDeliveyAddressLine2) {
        this.setPatientDeliveyAddressLine2(patientDeliveyAddressLine2);
        return this;
    }

    public void setPatientDeliveyAddressLine2(String patientDeliveyAddressLine2) {
        this.patientDeliveyAddressLine2 = patientDeliveyAddressLine2;
    }

    public String getPatientDeliveyAddressState() {
        return this.patientDeliveyAddressState;
    }

    public PickupExchange patientDeliveyAddressState(String patientDeliveyAddressState) {
        this.setPatientDeliveyAddressState(patientDeliveyAddressState);
        return this;
    }

    public void setPatientDeliveyAddressState(String patientDeliveyAddressState) {
        this.patientDeliveyAddressState = patientDeliveyAddressState;
    }

    public String getPatientDeliveyAddressCity() {
        return this.patientDeliveyAddressCity;
    }

    public PickupExchange patientDeliveyAddressCity(String patientDeliveyAddressCity) {
        this.setPatientDeliveyAddressCity(patientDeliveyAddressCity);
        return this;
    }

    public void setPatientDeliveyAddressCity(String patientDeliveyAddressCity) {
        this.patientDeliveyAddressCity = patientDeliveyAddressCity;
    }

    public String getPatientDeliveyAddressZip() {
        return this.patientDeliveyAddressZip;
    }

    public PickupExchange patientDeliveyAddressZip(String patientDeliveyAddressZip) {
        this.setPatientDeliveyAddressZip(patientDeliveyAddressZip);
        return this;
    }

    public void setPatientDeliveyAddressZip(String patientDeliveyAddressZip) {
        this.patientDeliveyAddressZip = patientDeliveyAddressZip;
    }

    public LocalDate getPickupExchangeScheduleDateTime() {
        return this.pickupExchangeScheduleDateTime;
    }

    public PickupExchange pickupExchangeScheduleDateTime(LocalDate pickupExchangeScheduleDateTime) {
        this.setPickupExchangeScheduleDateTime(pickupExchangeScheduleDateTime);
        return this;
    }

    public void setPickupExchangeScheduleDateTime(LocalDate pickupExchangeScheduleDateTime) {
        this.pickupExchangeScheduleDateTime = pickupExchangeScheduleDateTime;
    }

    public LocalDate getPickupExchangeActualDateTime() {
        return this.pickupExchangeActualDateTime;
    }

    public PickupExchange pickupExchangeActualDateTime(LocalDate pickupExchangeActualDateTime) {
        this.setPickupExchangeActualDateTime(pickupExchangeActualDateTime);
        return this;
    }

    public void setPickupExchangeActualDateTime(LocalDate pickupExchangeActualDateTime) {
        this.pickupExchangeActualDateTime = pickupExchangeActualDateTime;
    }

    public String getPickupExchangeReason() {
        return this.pickupExchangeReason;
    }

    public PickupExchange pickupExchangeReason(String pickupExchangeReason) {
        this.setPickupExchangeReason(pickupExchangeReason);
        return this;
    }

    public void setPickupExchangeReason(String pickupExchangeReason) {
        this.pickupExchangeReason = pickupExchangeReason;
    }

    public String getPickupExchangeRequest() {
        return this.pickupExchangeRequest;
    }

    public PickupExchange pickupExchangeRequest(String pickupExchangeRequest) {
        this.setPickupExchangeRequest(pickupExchangeRequest);
        return this;
    }

    public void setPickupExchangeRequest(String pickupExchangeRequest) {
        this.pickupExchangeRequest = pickupExchangeRequest;
    }

    public String getPickupExchangeNote() {
        return this.pickupExchangeNote;
    }

    public PickupExchange pickupExchangeNote(String pickupExchangeNote) {
        this.setPickupExchangeNote(pickupExchangeNote);
        return this;
    }

    public void setPickupExchangeNote(String pickupExchangeNote) {
        this.pickupExchangeNote = pickupExchangeNote;
    }

    public String getPickupExchangeAgentIdNo() {
        return this.pickupExchangeAgentIdNo;
    }

    public PickupExchange pickupExchangeAgentIdNo(String pickupExchangeAgentIdNo) {
        this.setPickupExchangeAgentIdNo(pickupExchangeAgentIdNo);
        return this;
    }

    public void setPickupExchangeAgentIdNo(String pickupExchangeAgentIdNo) {
        this.pickupExchangeAgentIdNo = pickupExchangeAgentIdNo;
    }

    public String getPickupExchangeAgentName() {
        return this.pickupExchangeAgentName;
    }

    public PickupExchange pickupExchangeAgentName(String pickupExchangeAgentName) {
        this.setPickupExchangeAgentName(pickupExchangeAgentName);
        return this;
    }

    public void setPickupExchangeAgentName(String pickupExchangeAgentName) {
        this.pickupExchangeAgentName = pickupExchangeAgentName;
    }

    public String getPickupExchangeDocumentId() {
        return this.pickupExchangeDocumentId;
    }

    public PickupExchange pickupExchangeDocumentId(String pickupExchangeDocumentId) {
        this.setPickupExchangeDocumentId(pickupExchangeDocumentId);
        return this;
    }

    public void setPickupExchangeDocumentId(String pickupExchangeDocumentId) {
        this.pickupExchangeDocumentId = pickupExchangeDocumentId;
    }

    public String getPickupExchangeDocumentNo() {
        return this.pickupExchangeDocumentNo;
    }

    public PickupExchange pickupExchangeDocumentNo(String pickupExchangeDocumentNo) {
        this.setPickupExchangeDocumentNo(pickupExchangeDocumentNo);
        return this;
    }

    public void setPickupExchangeDocumentNo(String pickupExchangeDocumentNo) {
        this.pickupExchangeDocumentNo = pickupExchangeDocumentNo;
    }

    public String getPickupExchangeDocumentName() {
        return this.pickupExchangeDocumentName;
    }

    public PickupExchange pickupExchangeDocumentName(String pickupExchangeDocumentName) {
        this.setPickupExchangeDocumentName(pickupExchangeDocumentName);
        return this;
    }

    public void setPickupExchangeDocumentName(String pickupExchangeDocumentName) {
        this.pickupExchangeDocumentName = pickupExchangeDocumentName;
    }

    public String getPickupExchangeStatus() {
        return this.pickupExchangeStatus;
    }

    public PickupExchange pickupExchangeStatus(String pickupExchangeStatus) {
        this.setPickupExchangeStatus(pickupExchangeStatus);
        return this;
    }

    public void setPickupExchangeStatus(String pickupExchangeStatus) {
        this.pickupExchangeStatus = pickupExchangeStatus;
    }

    public String getPickupExchangeComments() {
        return this.pickupExchangeComments;
    }

    public PickupExchange pickupExchangeComments(String pickupExchangeComments) {
        this.setPickupExchangeComments(pickupExchangeComments);
        return this;
    }

    public void setPickupExchangeComments(String pickupExchangeComments) {
        this.pickupExchangeComments = pickupExchangeComments;
    }

    public String getIsPatientSigned() {
        return this.isPatientSigned;
    }

    public PickupExchange isPatientSigned(String isPatientSigned) {
        this.setIsPatientSigned(isPatientSigned);
        return this;
    }

    public void setIsPatientSigned(String isPatientSigned) {
        this.isPatientSigned = isPatientSigned;
    }

    public String getRelationshipWithPatient() {
        return this.relationshipWithPatient;
    }

    public PickupExchange relationshipWithPatient(String relationshipWithPatient) {
        this.setRelationshipWithPatient(relationshipWithPatient);
        return this;
    }

    public void setRelationshipWithPatient(String relationshipWithPatient) {
        this.relationshipWithPatient = relationshipWithPatient;
    }

    public LocalDate getPatientSignedDateTime() {
        return this.patientSignedDateTime;
    }

    public PickupExchange patientSignedDateTime(LocalDate patientSignedDateTime) {
        this.setPatientSignedDateTime(patientSignedDateTime);
        return this;
    }

    public void setPatientSignedDateTime(LocalDate patientSignedDateTime) {
        this.patientSignedDateTime = patientSignedDateTime;
    }

    public String getIsAgentSigned() {
        return this.isAgentSigned;
    }

    public PickupExchange isAgentSigned(String isAgentSigned) {
        this.setIsAgentSigned(isAgentSigned);
        return this;
    }

    public void setIsAgentSigned(String isAgentSigned) {
        this.isAgentSigned = isAgentSigned;
    }

    public LocalDate getLastBillingDate() {
        return this.lastBillingDate;
    }

    public PickupExchange lastBillingDate(LocalDate lastBillingDate) {
        this.setLastBillingDate(lastBillingDate);
        return this;
    }

    public void setLastBillingDate(LocalDate lastBillingDate) {
        this.lastBillingDate = lastBillingDate;
    }

    public LocalDate getDateOfDeath() {
        return this.dateOfDeath;
    }

    public PickupExchange dateOfDeath(LocalDate dateOfDeath) {
        this.setDateOfDeath(dateOfDeath);
        return this;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getPickupExchangeSupportingDocument1() {
        return this.pickupExchangeSupportingDocument1;
    }

    public PickupExchange pickupExchangeSupportingDocument1(String pickupExchangeSupportingDocument1) {
        this.setPickupExchangeSupportingDocument1(pickupExchangeSupportingDocument1);
        return this;
    }

    public void setPickupExchangeSupportingDocument1(String pickupExchangeSupportingDocument1) {
        this.pickupExchangeSupportingDocument1 = pickupExchangeSupportingDocument1;
    }

    public String getPickupExchangeSupportingDocument2() {
        return this.pickupExchangeSupportingDocument2;
    }

    public PickupExchange pickupExchangeSupportingDocument2(String pickupExchangeSupportingDocument2) {
        this.setPickupExchangeSupportingDocument2(pickupExchangeSupportingDocument2);
        return this;
    }

    public void setPickupExchangeSupportingDocument2(String pickupExchangeSupportingDocument2) {
        this.pickupExchangeSupportingDocument2 = pickupExchangeSupportingDocument2;
    }

    public String getPatientNotsignedReason() {
        return this.patientNotsignedReason;
    }

    public PickupExchange patientNotsignedReason(String patientNotsignedReason) {
        this.setPatientNotsignedReason(patientNotsignedReason);
        return this;
    }

    public void setPatientNotsignedReason(String patientNotsignedReason) {
        this.patientNotsignedReason = patientNotsignedReason;
    }

    public String getPickupExchangeJsonData() {
        return this.pickupExchangeJsonData;
    }

    public PickupExchange pickupExchangeJsonData(String pickupExchangeJsonData) {
        this.setPickupExchangeJsonData(pickupExchangeJsonData);
        return this;
    }

    public void setPickupExchangeJsonData(String pickupExchangeJsonData) {
        this.pickupExchangeJsonData = pickupExchangeJsonData;
    }

    public String getStatus() {
        return this.status;
    }

    public PickupExchange status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PickupExchange createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PickupExchange createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PickupExchange createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PickupExchange updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PickupExchange updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PickupExchange updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPickupExchangeUuid() {
        return this.pickupExchangeUuid;
    }

    public PickupExchange pickupExchangeUuid(UUID pickupExchangeUuid) {
        this.setPickupExchangeUuid(pickupExchangeUuid);
        return this;
    }

    public void setPickupExchangeUuid(UUID pickupExchangeUuid) {
        this.pickupExchangeUuid = pickupExchangeUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PickupExchange)) {
            return false;
        }
        return pickupExchangeId != null && pickupExchangeId.equals(((PickupExchange) o).pickupExchangeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PickupExchange{" +
            "pickupExchangeId=" + getPickupExchangeId() +
            ", pickupExchangeNo='" + getPickupExchangeNo() + "'" +
            ", pickupExchangeType='" + getPickupExchangeType() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", inventoryLocationId=" + getInventoryLocationId() +
            ", inventoryLocationName='" + getInventoryLocationName() + "'" +
            ", patientId=" + getPatientId() +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientContact1='" + getPatientContact1() + "'" +
            ", patientContact2='" + getPatientContact2() + "'" +
            ", patientBillingAddressLine1='" + getPatientBillingAddressLine1() + "'" +
            ", patientBillingAddressLine2='" + getPatientBillingAddressLine2() + "'" +
            ", patientBillingAddressState='" + getPatientBillingAddressState() + "'" +
            ", patientBillingAddressCity='" + getPatientBillingAddressCity() + "'" +
            ", patientBillingAddressZip='" + getPatientBillingAddressZip() + "'" +
            ", patientDeliveyAddressLine1='" + getPatientDeliveyAddressLine1() + "'" +
            ", patientDeliveyAddressLine2='" + getPatientDeliveyAddressLine2() + "'" +
            ", patientDeliveyAddressState='" + getPatientDeliveyAddressState() + "'" +
            ", patientDeliveyAddressCity='" + getPatientDeliveyAddressCity() + "'" +
            ", patientDeliveyAddressZip='" + getPatientDeliveyAddressZip() + "'" +
            ", pickupExchangeScheduleDateTime='" + getPickupExchangeScheduleDateTime() + "'" +
            ", pickupExchangeActualDateTime='" + getPickupExchangeActualDateTime() + "'" +
            ", pickupExchangeReason='" + getPickupExchangeReason() + "'" +
            ", pickupExchangeRequest='" + getPickupExchangeRequest() + "'" +
            ", pickupExchangeNote='" + getPickupExchangeNote() + "'" +
            ", pickupExchangeAgentIdNo='" + getPickupExchangeAgentIdNo() + "'" +
            ", pickupExchangeAgentName='" + getPickupExchangeAgentName() + "'" +
            ", pickupExchangeDocumentId='" + getPickupExchangeDocumentId() + "'" +
            ", pickupExchangeDocumentNo='" + getPickupExchangeDocumentNo() + "'" +
            ", pickupExchangeDocumentName='" + getPickupExchangeDocumentName() + "'" +
            ", pickupExchangeStatus='" + getPickupExchangeStatus() + "'" +
            ", pickupExchangeComments='" + getPickupExchangeComments() + "'" +
            ", isPatientSigned='" + getIsPatientSigned() + "'" +
            ", relationshipWithPatient='" + getRelationshipWithPatient() + "'" +
            ", patientSignedDateTime='" + getPatientSignedDateTime() + "'" +
            ", isAgentSigned='" + getIsAgentSigned() + "'" +
            ", lastBillingDate='" + getLastBillingDate() + "'" +
            ", dateOfDeath='" + getDateOfDeath() + "'" +
            ", pickupExchangeSupportingDocument1='" + getPickupExchangeSupportingDocument1() + "'" +
            ", pickupExchangeSupportingDocument2='" + getPickupExchangeSupportingDocument2() + "'" +
            ", patientNotsignedReason='" + getPatientNotsignedReason() + "'" +
            ", pickupExchangeJsonData='" + getPickupExchangeJsonData() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", pickupExchangeUuid='" + getPickupExchangeUuid() + "'" +
            "}";
    }
}
