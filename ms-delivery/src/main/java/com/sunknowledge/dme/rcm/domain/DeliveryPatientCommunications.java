package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DeliveryPatientCommunications.
 */
@Entity
@Table(name = "t_delivery_patient_communications")
public class DeliveryPatientCommunications implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "delivery_patient_communications_id", nullable = false)
    private Long deliveryPatientCommunicationsId;

    @Column(name = "delivery_ticket_id")
    private Long deliveryTicketId;

    @Column(name = "delivery_ticket_no")
    private String deliveryTicketNo;

    @Column(name = "reason_for_communication")
    private String reasonForCommunication;

    @Column(name = "patient_phone_no")
    private String patientPhoneNo;

    @Column(name = "person_spoken_to_name")
    private String personSpokenToName;

    @Column(name = "person_spoken_to_relation_with_patient")
    private String personSpokenToRelationWithPatient;

    @Column(name = "communication_summery")
    private String communicationSummery;

    @Column(name = "csr_name")
    private String csrName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "delivery_patient_communications_uuid")
    private UUID deliveryPatientCommunicationsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryPatientCommunicationsId() {
        return this.deliveryPatientCommunicationsId;
    }

    public DeliveryPatientCommunications deliveryPatientCommunicationsId(Long deliveryPatientCommunicationsId) {
        this.setDeliveryPatientCommunicationsId(deliveryPatientCommunicationsId);
        return this;
    }

    public void setDeliveryPatientCommunicationsId(Long deliveryPatientCommunicationsId) {
        this.deliveryPatientCommunicationsId = deliveryPatientCommunicationsId;
    }

    public Long getDeliveryTicketId() {
        return this.deliveryTicketId;
    }

    public DeliveryPatientCommunications deliveryTicketId(Long deliveryTicketId) {
        this.setDeliveryTicketId(deliveryTicketId);
        return this;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return this.deliveryTicketNo;
    }

    public DeliveryPatientCommunications deliveryTicketNo(String deliveryTicketNo) {
        this.setDeliveryTicketNo(deliveryTicketNo);
        return this;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public String getReasonForCommunication() {
        return this.reasonForCommunication;
    }

    public DeliveryPatientCommunications reasonForCommunication(String reasonForCommunication) {
        this.setReasonForCommunication(reasonForCommunication);
        return this;
    }

    public void setReasonForCommunication(String reasonForCommunication) {
        this.reasonForCommunication = reasonForCommunication;
    }

    public String getPatientPhoneNo() {
        return this.patientPhoneNo;
    }

    public DeliveryPatientCommunications patientPhoneNo(String patientPhoneNo) {
        this.setPatientPhoneNo(patientPhoneNo);
        return this;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public String getPersonSpokenToName() {
        return this.personSpokenToName;
    }

    public DeliveryPatientCommunications personSpokenToName(String personSpokenToName) {
        this.setPersonSpokenToName(personSpokenToName);
        return this;
    }

    public void setPersonSpokenToName(String personSpokenToName) {
        this.personSpokenToName = personSpokenToName;
    }

    public String getPersonSpokenToRelationWithPatient() {
        return this.personSpokenToRelationWithPatient;
    }

    public DeliveryPatientCommunications personSpokenToRelationWithPatient(String personSpokenToRelationWithPatient) {
        this.setPersonSpokenToRelationWithPatient(personSpokenToRelationWithPatient);
        return this;
    }

    public void setPersonSpokenToRelationWithPatient(String personSpokenToRelationWithPatient) {
        this.personSpokenToRelationWithPatient = personSpokenToRelationWithPatient;
    }

    public String getCommunicationSummery() {
        return this.communicationSummery;
    }

    public DeliveryPatientCommunications communicationSummery(String communicationSummery) {
        this.setCommunicationSummery(communicationSummery);
        return this;
    }

    public void setCommunicationSummery(String communicationSummery) {
        this.communicationSummery = communicationSummery;
    }

    public String getCsrName() {
        return this.csrName;
    }

    public DeliveryPatientCommunications csrName(String csrName) {
        this.setCsrName(csrName);
        return this;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryPatientCommunications status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DeliveryPatientCommunications createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DeliveryPatientCommunications createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DeliveryPatientCommunications createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DeliveryPatientCommunications updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DeliveryPatientCommunications updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DeliveryPatientCommunications updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryPatientCommunicationsUuid() {
        return this.deliveryPatientCommunicationsUuid;
    }

    public DeliveryPatientCommunications deliveryPatientCommunicationsUuid(UUID deliveryPatientCommunicationsUuid) {
        this.setDeliveryPatientCommunicationsUuid(deliveryPatientCommunicationsUuid);
        return this;
    }

    public void setDeliveryPatientCommunicationsUuid(UUID deliveryPatientCommunicationsUuid) {
        this.deliveryPatientCommunicationsUuid = deliveryPatientCommunicationsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryPatientCommunications)) {
            return false;
        }
        return (
            deliveryPatientCommunicationsId != null &&
            deliveryPatientCommunicationsId.equals(((DeliveryPatientCommunications) o).deliveryPatientCommunicationsId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryPatientCommunications{" +
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
