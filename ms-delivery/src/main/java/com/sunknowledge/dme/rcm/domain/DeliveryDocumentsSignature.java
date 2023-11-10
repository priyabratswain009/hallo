package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DeliveryDocumentsSignature.
 */
@Entity
@Table(name = "t_delivery_documents_signature")
public class DeliveryDocumentsSignature implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "delivery_document_signature_id", nullable = false)
    private Long deliveryDocumentSignatureId;

    @Column(name = "delivery_ticket_id")
    private Long deliveryTicketId;

    @Column(name = "delivery_ticket_no")
    private String deliveryTicketNo;

    @Column(name = "caregiver_signature_file")
    private String caregiverSignatureFile;

    @Column(name = "patient_signature_file")
    private String patientSignatureFile;

    @Column(name = "driver_agent_signature_file")
    private String driverAgentSignatureFile;

    @Column(name = "date_time")
    private LocalDate dateTime;

    @Column(name = "status")
    private String status;

    @Column(name = "delivery_documents_signature_uuid")
    private UUID deliveryDocumentsSignatureUuid;

    @Column(name = "signature_file_response_json_data")
    private String signatureFileResponseJsonData;

    @Column(name = "caregiver_name")
    private String caregiverName;

    @Column(name = "caregiver_relationship")
    private String caregiverRelationship;

    @Column(name = "patient_reasonnotsigned")
    private String patientReasonnotsigned;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryDocumentSignatureId() {
        return this.deliveryDocumentSignatureId;
    }

    public DeliveryDocumentsSignature deliveryDocumentSignatureId(Long deliveryDocumentSignatureId) {
        this.setDeliveryDocumentSignatureId(deliveryDocumentSignatureId);
        return this;
    }

    public void setDeliveryDocumentSignatureId(Long deliveryDocumentSignatureId) {
        this.deliveryDocumentSignatureId = deliveryDocumentSignatureId;
    }

    public Long getDeliveryTicketId() {
        return this.deliveryTicketId;
    }

    public DeliveryDocumentsSignature deliveryTicketId(Long deliveryTicketId) {
        this.setDeliveryTicketId(deliveryTicketId);
        return this;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return this.deliveryTicketNo;
    }

    public DeliveryDocumentsSignature deliveryTicketNo(String deliveryTicketNo) {
        this.setDeliveryTicketNo(deliveryTicketNo);
        return this;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public String getCaregiverSignatureFile() {
        return this.caregiverSignatureFile;
    }

    public DeliveryDocumentsSignature caregiverSignatureFile(String caregiverSignatureFile) {
        this.setCaregiverSignatureFile(caregiverSignatureFile);
        return this;
    }

    public void setCaregiverSignatureFile(String caregiverSignatureFile) {
        this.caregiverSignatureFile = caregiverSignatureFile;
    }

    public String getPatientSignatureFile() {
        return this.patientSignatureFile;
    }

    public DeliveryDocumentsSignature patientSignatureFile(String patientSignatureFile) {
        this.setPatientSignatureFile(patientSignatureFile);
        return this;
    }

    public void setPatientSignatureFile(String patientSignatureFile) {
        this.patientSignatureFile = patientSignatureFile;
    }

    public String getDriverAgentSignatureFile() {
        return this.driverAgentSignatureFile;
    }

    public DeliveryDocumentsSignature driverAgentSignatureFile(String driverAgentSignatureFile) {
        this.setDriverAgentSignatureFile(driverAgentSignatureFile);
        return this;
    }

    public void setDriverAgentSignatureFile(String driverAgentSignatureFile) {
        this.driverAgentSignatureFile = driverAgentSignatureFile;
    }

    public LocalDate getDateTime() {
        return this.dateTime;
    }

    public DeliveryDocumentsSignature dateTime(LocalDate dateTime) {
        this.setDateTime(dateTime);
        return this;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryDocumentsSignature status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getDeliveryDocumentsSignatureUuid() {
        return this.deliveryDocumentsSignatureUuid;
    }

    public DeliveryDocumentsSignature deliveryDocumentsSignatureUuid(UUID deliveryDocumentsSignatureUuid) {
        this.setDeliveryDocumentsSignatureUuid(deliveryDocumentsSignatureUuid);
        return this;
    }

    public void setDeliveryDocumentsSignatureUuid(UUID deliveryDocumentsSignatureUuid) {
        this.deliveryDocumentsSignatureUuid = deliveryDocumentsSignatureUuid;
    }

    public String getSignatureFileResponseJsonData() {
        return this.signatureFileResponseJsonData;
    }

    public DeliveryDocumentsSignature signatureFileResponseJsonData(String signatureFileResponseJsonData) {
        this.setSignatureFileResponseJsonData(signatureFileResponseJsonData);
        return this;
    }

    public void setSignatureFileResponseJsonData(String signatureFileResponseJsonData) {
        this.signatureFileResponseJsonData = signatureFileResponseJsonData;
    }

    public String getCaregiverName() {
        return this.caregiverName;
    }

    public DeliveryDocumentsSignature caregiverName(String caregiverName) {
        this.setCaregiverName(caregiverName);
        return this;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getCaregiverRelationship() {
        return this.caregiverRelationship;
    }

    public DeliveryDocumentsSignature caregiverRelationship(String caregiverRelationship) {
        this.setCaregiverRelationship(caregiverRelationship);
        return this;
    }

    public void setCaregiverRelationship(String caregiverRelationship) {
        this.caregiverRelationship = caregiverRelationship;
    }

    public String getPatientReasonnotsigned() {
        return this.patientReasonnotsigned;
    }

    public DeliveryDocumentsSignature patientReasonnotsigned(String patientReasonnotsigned) {
        this.setPatientReasonnotsigned(patientReasonnotsigned);
        return this;
    }

    public void setPatientReasonnotsigned(String patientReasonnotsigned) {
        this.patientReasonnotsigned = patientReasonnotsigned;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryDocumentsSignature)) {
            return false;
        }
        return (
            deliveryDocumentSignatureId != null &&
            deliveryDocumentSignatureId.equals(((DeliveryDocumentsSignature) o).deliveryDocumentSignatureId)
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
        return "DeliveryDocumentsSignature{" +
            "deliveryDocumentSignatureId=" + getDeliveryDocumentSignatureId() +
            ", deliveryTicketId=" + getDeliveryTicketId() +
            ", deliveryTicketNo='" + getDeliveryTicketNo() + "'" +
            ", caregiverSignatureFile='" + getCaregiverSignatureFile() + "'" +
            ", patientSignatureFile='" + getPatientSignatureFile() + "'" +
            ", driverAgentSignatureFile='" + getDriverAgentSignatureFile() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", deliveryDocumentsSignatureUuid='" + getDeliveryDocumentsSignatureUuid() + "'" +
            ", signatureFileResponseJsonData='" + getSignatureFileResponseJsonData() + "'" +
            ", caregiverName='" + getCaregiverName() + "'" +
            ", caregiverRelationship='" + getCaregiverRelationship() + "'" +
            ", patientReasonnotsigned='" + getPatientReasonnotsigned() + "'" +
            "}";
    }
}
