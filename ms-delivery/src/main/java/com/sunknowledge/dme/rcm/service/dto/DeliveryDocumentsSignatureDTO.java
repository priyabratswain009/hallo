package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature} entity.
 */
public class DeliveryDocumentsSignatureDTO implements Serializable {

    @NotNull
    private Long deliveryDocumentSignatureId;

    private Long deliveryTicketId;

    private String deliveryTicketNo;

    private String caregiverSignatureFile;

    private String patientSignatureFile;

    private String driverAgentSignatureFile;

    private LocalDate dateTime;

    private String status;

    private UUID deliveryDocumentsSignatureUuid;

    private String signatureFileResponseJsonData;

    private String caregiverName;

    private String caregiverRelationship;

    private String patientReasonnotsigned;

    public Long getDeliveryDocumentSignatureId() {
        return deliveryDocumentSignatureId;
    }

    public void setDeliveryDocumentSignatureId(Long deliveryDocumentSignatureId) {
        this.deliveryDocumentSignatureId = deliveryDocumentSignatureId;
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

    public String getCaregiverSignatureFile() {
        return caregiverSignatureFile;
    }

    public void setCaregiverSignatureFile(String caregiverSignatureFile) {
        this.caregiverSignatureFile = caregiverSignatureFile;
    }

    public String getPatientSignatureFile() {
        return patientSignatureFile;
    }

    public void setPatientSignatureFile(String patientSignatureFile) {
        this.patientSignatureFile = patientSignatureFile;
    }

    public String getDriverAgentSignatureFile() {
        return driverAgentSignatureFile;
    }

    public void setDriverAgentSignatureFile(String driverAgentSignatureFile) {
        this.driverAgentSignatureFile = driverAgentSignatureFile;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getDeliveryDocumentsSignatureUuid() {
        return deliveryDocumentsSignatureUuid;
    }

    public void setDeliveryDocumentsSignatureUuid(UUID deliveryDocumentsSignatureUuid) {
        this.deliveryDocumentsSignatureUuid = deliveryDocumentsSignatureUuid;
    }

    public String getSignatureFileResponseJsonData() {
        return signatureFileResponseJsonData;
    }

    public void setSignatureFileResponseJsonData(String signatureFileResponseJsonData) {
        this.signatureFileResponseJsonData = signatureFileResponseJsonData;
    }

    public String getCaregiverName() {
        return caregiverName;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getCaregiverRelationship() {
        return caregiverRelationship;
    }

    public void setCaregiverRelationship(String caregiverRelationship) {
        this.caregiverRelationship = caregiverRelationship;
    }

    public String getPatientReasonnotsigned() {
        return patientReasonnotsigned;
    }

    public void setPatientReasonnotsigned(String patientReasonnotsigned) {
        this.patientReasonnotsigned = patientReasonnotsigned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryDocumentsSignatureDTO)) {
            return false;
        }

        DeliveryDocumentsSignatureDTO deliveryDocumentsSignatureDTO = (DeliveryDocumentsSignatureDTO) o;
        if (this.deliveryDocumentSignatureId == null) {
            return false;
        }
        return Objects.equals(this.deliveryDocumentSignatureId, deliveryDocumentsSignatureDTO.deliveryDocumentSignatureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryDocumentSignatureId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryDocumentsSignatureDTO{" +
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
