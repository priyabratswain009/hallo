package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryDocuments} entity.
 */
public class DeliveryDocumentsDTO implements Serializable {

    @NotNull
    private Long deliveryDocId;

    private Long deliveryTicketId;

    private String deliveryTicketNo;

    private Long soId;

    private String soNo;

    private Long hcpcsDoctypeId;

    private String docPatientName;

    private String documentPatientFilePath;

    private String isPatientSigned;

    private String isCaregiverSigned;

    private LocalDate signedDate;

    private String isDeliveryAgentSigned;

    private String isScannedUploaded;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID deliveryDocumentsUuid;

    private String responseJsonData;

    public Long getDeliveryDocId() {
        return deliveryDocId;
    }

    public void setDeliveryDocId(Long deliveryDocId) {
        this.deliveryDocId = deliveryDocId;
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

    public Long getHcpcsDoctypeId() {
        return hcpcsDoctypeId;
    }

    public void setHcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.hcpcsDoctypeId = hcpcsDoctypeId;
    }

    public String getDocPatientName() {
        return docPatientName;
    }

    public void setDocPatientName(String docPatientName) {
        this.docPatientName = docPatientName;
    }

    public String getDocumentPatientFilePath() {
        return documentPatientFilePath;
    }

    public void setDocumentPatientFilePath(String documentPatientFilePath) {
        this.documentPatientFilePath = documentPatientFilePath;
    }

    public String getIsPatientSigned() {
        return isPatientSigned;
    }

    public void setIsPatientSigned(String isPatientSigned) {
        this.isPatientSigned = isPatientSigned;
    }

    public String getIsCaregiverSigned() {
        return isCaregiverSigned;
    }

    public void setIsCaregiverSigned(String isCaregiverSigned) {
        this.isCaregiverSigned = isCaregiverSigned;
    }

    public LocalDate getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(LocalDate signedDate) {
        this.signedDate = signedDate;
    }

    public String getIsDeliveryAgentSigned() {
        return isDeliveryAgentSigned;
    }

    public void setIsDeliveryAgentSigned(String isDeliveryAgentSigned) {
        this.isDeliveryAgentSigned = isDeliveryAgentSigned;
    }

    public String getIsScannedUploaded() {
        return isScannedUploaded;
    }

    public void setIsScannedUploaded(String isScannedUploaded) {
        this.isScannedUploaded = isScannedUploaded;
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

    public UUID getDeliveryDocumentsUuid() {
        return deliveryDocumentsUuid;
    }

    public void setDeliveryDocumentsUuid(UUID deliveryDocumentsUuid) {
        this.deliveryDocumentsUuid = deliveryDocumentsUuid;
    }

    public String getResponseJsonData() {
        return responseJsonData;
    }

    public void setResponseJsonData(String responseJsonData) {
        this.responseJsonData = responseJsonData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryDocumentsDTO)) {
            return false;
        }

        DeliveryDocumentsDTO deliveryDocumentsDTO = (DeliveryDocumentsDTO) o;
        if (this.deliveryDocId == null) {
            return false;
        }
        return Objects.equals(this.deliveryDocId, deliveryDocumentsDTO.deliveryDocId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryDocId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryDocumentsDTO{" +
            "deliveryDocId=" + getDeliveryDocId() +
            ", deliveryTicketId=" + getDeliveryTicketId() +
            ", deliveryTicketNo='" + getDeliveryTicketNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", hcpcsDoctypeId=" + getHcpcsDoctypeId() +
            ", docPatientName='" + getDocPatientName() + "'" +
            ", documentPatientFilePath='" + getDocumentPatientFilePath() + "'" +
            ", isPatientSigned='" + getIsPatientSigned() + "'" +
            ", isCaregiverSigned='" + getIsCaregiverSigned() + "'" +
            ", signedDate='" + getSignedDate() + "'" +
            ", isDeliveryAgentSigned='" + getIsDeliveryAgentSigned() + "'" +
            ", isScannedUploaded='" + getIsScannedUploaded() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deliveryDocumentsUuid='" + getDeliveryDocumentsUuid() + "'" +
            ", responseJsonData='" + getResponseJsonData() + "'" +
            "}";
    }
}
