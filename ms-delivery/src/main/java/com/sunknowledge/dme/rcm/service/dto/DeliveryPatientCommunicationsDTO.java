package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications} entity.
 */
public class DeliveryPatientCommunicationsDTO implements Serializable {

    @NotNull
    private Long deliveryPatientCommunicationsId;

    private Long deliveryTicketId;

    private String deliveryTicketNo;

    private String reasonForCommunication;

    private String patientPhoneNo;

    private String personSpokenToName;

    private String personSpokenToRelationWithPatient;

    private String communicationSummery;

    private String csrName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID deliveryPatientCommunicationsUuid;

    public Long getDeliveryPatientCommunicationsId() {
        return deliveryPatientCommunicationsId;
    }

    public void setDeliveryPatientCommunicationsId(Long deliveryPatientCommunicationsId) {
        this.deliveryPatientCommunicationsId = deliveryPatientCommunicationsId;
    }

    public Long getDeliveryTicketId() {
        return deliveryTicketId;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return deliveryTicketNo;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public String getReasonForCommunication() {
        return reasonForCommunication;
    }

    public void setReasonForCommunication(String reasonForCommunication) {
        this.reasonForCommunication = reasonForCommunication;
    }

    public String getPatientPhoneNo() {
        return patientPhoneNo;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public String getPersonSpokenToName() {
        return personSpokenToName;
    }

    public void setPersonSpokenToName(String personSpokenToName) {
        this.personSpokenToName = personSpokenToName;
    }

    public String getPersonSpokenToRelationWithPatient() {
        return personSpokenToRelationWithPatient;
    }

    public void setPersonSpokenToRelationWithPatient(String personSpokenToRelationWithPatient) {
        this.personSpokenToRelationWithPatient = personSpokenToRelationWithPatient;
    }

    public String getCommunicationSummery() {
        return communicationSummery;
    }

    public void setCommunicationSummery(String communicationSummery) {
        this.communicationSummery = communicationSummery;
    }

    public String getCsrName() {
        return csrName;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
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

    public UUID getDeliveryPatientCommunicationsUuid() {
        return deliveryPatientCommunicationsUuid;
    }

    public void setDeliveryPatientCommunicationsUuid(UUID deliveryPatientCommunicationsUuid) {
        this.deliveryPatientCommunicationsUuid = deliveryPatientCommunicationsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryPatientCommunicationsDTO)) {
            return false;
        }

        DeliveryPatientCommunicationsDTO deliveryPatientCommunicationsDTO = (DeliveryPatientCommunicationsDTO) o;
        if (this.deliveryPatientCommunicationsId == null) {
            return false;
        }
        return Objects.equals(this.deliveryPatientCommunicationsId, deliveryPatientCommunicationsDTO.deliveryPatientCommunicationsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryPatientCommunicationsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryPatientCommunicationsDTO{" +
            "deliveryPatientCommunicationsId=" + getDeliveryPatientCommunicationsId() +
            ", deliveryTicketId=" + getDeliveryTicketId() +
            ", deliveryTicketNo='" + getDeliveryTicketNo() + "'" +
            ", reasonForCommunication='" + getReasonForCommunication() + "'" +
            ", patientPhoneNo='" + getPatientPhoneNo() + "'" +
            ", personSpokenToName='" + getPersonSpokenToName() + "'" +
            ", personSpokenToRelationWithPatient='" + getPersonSpokenToRelationWithPatient() + "'" +
            ", communicationSummery='" + getCommunicationSummery() + "'" +
            ", csrName='" + getCsrName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deliveryPatientCommunicationsUuid='" + getDeliveryPatientCommunicationsUuid() + "'" +
            "}";
    }
}
