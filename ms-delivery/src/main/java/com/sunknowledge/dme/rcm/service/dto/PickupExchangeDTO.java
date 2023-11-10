package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PickupExchange} entity.
 */
public class PickupExchangeDTO implements Serializable {

    @NotNull
    private Long pickupExchangeId;

    private String pickupExchangeNo;

    private String pickupExchangeType;

    private Long soId;

    private String soNo;

    private Long branchId;

    private String branchName;

    private Long inventoryLocationId;

    private String inventoryLocationName;

    private Long patientId;

    private String patientIdNo;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private String patientContact1;

    private String patientContact2;

    private String patientBillingAddressLine1;

    private String patientBillingAddressLine2;

    private String patientBillingAddressState;

    private String patientBillingAddressCity;

    private String patientBillingAddressZip;

    private String patientDeliveyAddressLine1;

    private String patientDeliveyAddressLine2;

    private String patientDeliveyAddressState;

    private String patientDeliveyAddressCity;

    private String patientDeliveyAddressZip;

    private LocalDate pickupExchangeScheduleDateTime;

    private LocalDate pickupExchangeActualDateTime;

    private String pickupExchangeReason;

    private String pickupExchangeRequest;

    private String pickupExchangeNote;

    private String pickupExchangeAgentIdNo;

    private String pickupExchangeAgentName;

    private String pickupExchangeDocumentId;

    private String pickupExchangeDocumentNo;

    private String pickupExchangeDocumentName;

    private String pickupExchangeStatus;

    private String pickupExchangeComments;

    private String isPatientSigned;

    private String relationshipWithPatient;

    private LocalDate patientSignedDateTime;

    private String isAgentSigned;

    private LocalDate lastBillingDate;

    private LocalDate dateOfDeath;

    private String pickupExchangeSupportingDocument1;

    private String pickupExchangeSupportingDocument2;

    private String patientNotsignedReason;

    private String pickupExchangeJsonData;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID pickupExchangeUuid;

    public Long getPickupExchangeId() {
        return pickupExchangeId;
    }

    public void setPickupExchangeId(Long pickupExchangeId) {
        this.pickupExchangeId = pickupExchangeId;
    }

    public String getPickupExchangeNo() {
        return pickupExchangeNo;
    }

    public void setPickupExchangeNo(String pickupExchangeNo) {
        this.pickupExchangeNo = pickupExchangeNo;
    }

    public String getPickupExchangeType() {
        return pickupExchangeType;
    }

    public void setPickupExchangeType(String pickupExchangeType) {
        this.pickupExchangeType = pickupExchangeType;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientContact1() {
        return patientContact1;
    }

    public void setPatientContact1(String patientContact1) {
        this.patientContact1 = patientContact1;
    }

    public String getPatientContact2() {
        return patientContact2;
    }

    public void setPatientContact2(String patientContact2) {
        this.patientContact2 = patientContact2;
    }

    public String getPatientBillingAddressLine1() {
        return patientBillingAddressLine1;
    }

    public void setPatientBillingAddressLine1(String patientBillingAddressLine1) {
        this.patientBillingAddressLine1 = patientBillingAddressLine1;
    }

    public String getPatientBillingAddressLine2() {
        return patientBillingAddressLine2;
    }

    public void setPatientBillingAddressLine2(String patientBillingAddressLine2) {
        this.patientBillingAddressLine2 = patientBillingAddressLine2;
    }

    public String getPatientBillingAddressState() {
        return patientBillingAddressState;
    }

    public void setPatientBillingAddressState(String patientBillingAddressState) {
        this.patientBillingAddressState = patientBillingAddressState;
    }

    public String getPatientBillingAddressCity() {
        return patientBillingAddressCity;
    }

    public void setPatientBillingAddressCity(String patientBillingAddressCity) {
        this.patientBillingAddressCity = patientBillingAddressCity;
    }

    public String getPatientBillingAddressZip() {
        return patientBillingAddressZip;
    }

    public void setPatientBillingAddressZip(String patientBillingAddressZip) {
        this.patientBillingAddressZip = patientBillingAddressZip;
    }

    public String getPatientDeliveyAddressLine1() {
        return patientDeliveyAddressLine1;
    }

    public void setPatientDeliveyAddressLine1(String patientDeliveyAddressLine1) {
        this.patientDeliveyAddressLine1 = patientDeliveyAddressLine1;
    }

    public String getPatientDeliveyAddressLine2() {
        return patientDeliveyAddressLine2;
    }

    public void setPatientDeliveyAddressLine2(String patientDeliveyAddressLine2) {
        this.patientDeliveyAddressLine2 = patientDeliveyAddressLine2;
    }

    public String getPatientDeliveyAddressState() {
        return patientDeliveyAddressState;
    }

    public void setPatientDeliveyAddressState(String patientDeliveyAddressState) {
        this.patientDeliveyAddressState = patientDeliveyAddressState;
    }

    public String getPatientDeliveyAddressCity() {
        return patientDeliveyAddressCity;
    }

    public void setPatientDeliveyAddressCity(String patientDeliveyAddressCity) {
        this.patientDeliveyAddressCity = patientDeliveyAddressCity;
    }

    public String getPatientDeliveyAddressZip() {
        return patientDeliveyAddressZip;
    }

    public void setPatientDeliveyAddressZip(String patientDeliveyAddressZip) {
        this.patientDeliveyAddressZip = patientDeliveyAddressZip;
    }

    public LocalDate getPickupExchangeScheduleDateTime() {
        return pickupExchangeScheduleDateTime;
    }

    public void setPickupExchangeScheduleDateTime(LocalDate pickupExchangeScheduleDateTime) {
        this.pickupExchangeScheduleDateTime = pickupExchangeScheduleDateTime;
    }

    public LocalDate getPickupExchangeActualDateTime() {
        return pickupExchangeActualDateTime;
    }

    public void setPickupExchangeActualDateTime(LocalDate pickupExchangeActualDateTime) {
        this.pickupExchangeActualDateTime = pickupExchangeActualDateTime;
    }

    public String getPickupExchangeReason() {
        return pickupExchangeReason;
    }

    public void setPickupExchangeReason(String pickupExchangeReason) {
        this.pickupExchangeReason = pickupExchangeReason;
    }

    public String getPickupExchangeRequest() {
        return pickupExchangeRequest;
    }

    public void setPickupExchangeRequest(String pickupExchangeRequest) {
        this.pickupExchangeRequest = pickupExchangeRequest;
    }

    public String getPickupExchangeNote() {
        return pickupExchangeNote;
    }

    public void setPickupExchangeNote(String pickupExchangeNote) {
        this.pickupExchangeNote = pickupExchangeNote;
    }

    public String getPickupExchangeAgentIdNo() {
        return pickupExchangeAgentIdNo;
    }

    public void setPickupExchangeAgentIdNo(String pickupExchangeAgentIdNo) {
        this.pickupExchangeAgentIdNo = pickupExchangeAgentIdNo;
    }

    public String getPickupExchangeAgentName() {
        return pickupExchangeAgentName;
    }

    public void setPickupExchangeAgentName(String pickupExchangeAgentName) {
        this.pickupExchangeAgentName = pickupExchangeAgentName;
    }

    public String getPickupExchangeDocumentId() {
        return pickupExchangeDocumentId;
    }

    public void setPickupExchangeDocumentId(String pickupExchangeDocumentId) {
        this.pickupExchangeDocumentId = pickupExchangeDocumentId;
    }

    public String getPickupExchangeDocumentNo() {
        return pickupExchangeDocumentNo;
    }

    public void setPickupExchangeDocumentNo(String pickupExchangeDocumentNo) {
        this.pickupExchangeDocumentNo = pickupExchangeDocumentNo;
    }

    public String getPickupExchangeDocumentName() {
        return pickupExchangeDocumentName;
    }

    public void setPickupExchangeDocumentName(String pickupExchangeDocumentName) {
        this.pickupExchangeDocumentName = pickupExchangeDocumentName;
    }

    public String getPickupExchangeStatus() {
        return pickupExchangeStatus;
    }

    public void setPickupExchangeStatus(String pickupExchangeStatus) {
        this.pickupExchangeStatus = pickupExchangeStatus;
    }

    public String getPickupExchangeComments() {
        return pickupExchangeComments;
    }

    public void setPickupExchangeComments(String pickupExchangeComments) {
        this.pickupExchangeComments = pickupExchangeComments;
    }

    public String getIsPatientSigned() {
        return isPatientSigned;
    }

    public void setIsPatientSigned(String isPatientSigned) {
        this.isPatientSigned = isPatientSigned;
    }

    public String getRelationshipWithPatient() {
        return relationshipWithPatient;
    }

    public void setRelationshipWithPatient(String relationshipWithPatient) {
        this.relationshipWithPatient = relationshipWithPatient;
    }

    public LocalDate getPatientSignedDateTime() {
        return patientSignedDateTime;
    }

    public void setPatientSignedDateTime(LocalDate patientSignedDateTime) {
        this.patientSignedDateTime = patientSignedDateTime;
    }

    public String getIsAgentSigned() {
        return isAgentSigned;
    }

    public void setIsAgentSigned(String isAgentSigned) {
        this.isAgentSigned = isAgentSigned;
    }

    public LocalDate getLastBillingDate() {
        return lastBillingDate;
    }

    public void setLastBillingDate(LocalDate lastBillingDate) {
        this.lastBillingDate = lastBillingDate;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getPickupExchangeSupportingDocument1() {
        return pickupExchangeSupportingDocument1;
    }

    public void setPickupExchangeSupportingDocument1(String pickupExchangeSupportingDocument1) {
        this.pickupExchangeSupportingDocument1 = pickupExchangeSupportingDocument1;
    }

    public String getPickupExchangeSupportingDocument2() {
        return pickupExchangeSupportingDocument2;
    }

    public void setPickupExchangeSupportingDocument2(String pickupExchangeSupportingDocument2) {
        this.pickupExchangeSupportingDocument2 = pickupExchangeSupportingDocument2;
    }

    public String getPatientNotsignedReason() {
        return patientNotsignedReason;
    }

    public void setPatientNotsignedReason(String patientNotsignedReason) {
        this.patientNotsignedReason = patientNotsignedReason;
    }

    public String getPickupExchangeJsonData() {
        return pickupExchangeJsonData;
    }

    public void setPickupExchangeJsonData(String pickupExchangeJsonData) {
        this.pickupExchangeJsonData = pickupExchangeJsonData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPickupExchangeUuid() {
        return pickupExchangeUuid;
    }

    public void setPickupExchangeUuid(UUID pickupExchangeUuid) {
        this.pickupExchangeUuid = pickupExchangeUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PickupExchangeDTO)) {
            return false;
        }

        PickupExchangeDTO pickupExchangeDTO = (PickupExchangeDTO) o;
        if (this.pickupExchangeId == null) {
            return false;
        }
        return Objects.equals(this.pickupExchangeId, pickupExchangeDTO.pickupExchangeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pickupExchangeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PickupExchangeDTO{" +
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
