package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DeliveryDocuments.
 */
@Entity
@Table(name = "t_delivery_documents")
public class DeliveryDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "delivery_doc_id", nullable = false)
    private Long deliveryDocId;

    @Column(name = "delivery_ticket_id")
    private Long deliveryTicketId;

    @Column(name = "delivery_ticket_no")
    private String deliveryTicketNo;

    @Column(name = "so_id")
    private Long soId;

    @Column(name = "so_no")
    private String soNo;

    @Column(name = "hcpcs_doctype_id")
    private Long hcpcsDoctypeId;

    @Column(name = "doc_patient_name")
    private String docPatientName;

    @Column(name = "document_patient_file_path")
    private String documentPatientFilePath;

    @Column(name = "is_patient_signed")
    private String isPatientSigned;

    @Column(name = "is_caregiver_signed")
    private String isCaregiverSigned;

    @Column(name = "signed_date")
    private LocalDate signedDate;

    @Column(name = "is_delivery_agent_signed")
    private String isDeliveryAgentSigned;

    @Column(name = "is_scanned_uploaded")
    private String isScannedUploaded;

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

    @Column(name = "delivery_documents_uuid")
    private UUID deliveryDocumentsUuid;

    @Column(name = "response_json_data")
    private String responseJsonData;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryDocId() {
        return this.deliveryDocId;
    }

    public DeliveryDocuments deliveryDocId(Long deliveryDocId) {
        this.setDeliveryDocId(deliveryDocId);
        return this;
    }

    public void setDeliveryDocId(Long deliveryDocId) {
        this.deliveryDocId = deliveryDocId;
    }

    public Long getDeliveryTicketId() {
        return this.deliveryTicketId;
    }

    public DeliveryDocuments deliveryTicketId(Long deliveryTicketId) {
        this.setDeliveryTicketId(deliveryTicketId);
        return this;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return this.deliveryTicketNo;
    }

    public DeliveryDocuments deliveryTicketNo(String deliveryTicketNo) {
        this.setDeliveryTicketNo(deliveryTicketNo);
        return this;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public DeliveryDocuments soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public DeliveryDocuments soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getHcpcsDoctypeId() {
        return this.hcpcsDoctypeId;
    }

    public DeliveryDocuments hcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.setHcpcsDoctypeId(hcpcsDoctypeId);
        return this;
    }

    public void setHcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.hcpcsDoctypeId = hcpcsDoctypeId;
    }

    public String getDocPatientName() {
        return this.docPatientName;
    }

    public DeliveryDocuments docPatientName(String docPatientName) {
        this.setDocPatientName(docPatientName);
        return this;
    }

    public void setDocPatientName(String docPatientName) {
        this.docPatientName = docPatientName;
    }

    public String getDocumentPatientFilePath() {
        return this.documentPatientFilePath;
    }

    public DeliveryDocuments documentPatientFilePath(String documentPatientFilePath) {
        this.setDocumentPatientFilePath(documentPatientFilePath);
        return this;
    }

    public void setDocumentPatientFilePath(String documentPatientFilePath) {
        this.documentPatientFilePath = documentPatientFilePath;
    }

    public String getIsPatientSigned() {
        return this.isPatientSigned;
    }

    public DeliveryDocuments isPatientSigned(String isPatientSigned) {
        this.setIsPatientSigned(isPatientSigned);
        return this;
    }

    public void setIsPatientSigned(String isPatientSigned) {
        this.isPatientSigned = isPatientSigned;
    }

    public String getIsCaregiverSigned() {
        return this.isCaregiverSigned;
    }

    public DeliveryDocuments isCaregiverSigned(String isCaregiverSigned) {
        this.setIsCaregiverSigned(isCaregiverSigned);
        return this;
    }

    public void setIsCaregiverSigned(String isCaregiverSigned) {
        this.isCaregiverSigned = isCaregiverSigned;
    }

    public LocalDate getSignedDate() {
        return this.signedDate;
    }

    public DeliveryDocuments signedDate(LocalDate signedDate) {
        this.setSignedDate(signedDate);
        return this;
    }

    public void setSignedDate(LocalDate signedDate) {
        this.signedDate = signedDate;
    }

    public String getIsDeliveryAgentSigned() {
        return this.isDeliveryAgentSigned;
    }

    public DeliveryDocuments isDeliveryAgentSigned(String isDeliveryAgentSigned) {
        this.setIsDeliveryAgentSigned(isDeliveryAgentSigned);
        return this;
    }

    public void setIsDeliveryAgentSigned(String isDeliveryAgentSigned) {
        this.isDeliveryAgentSigned = isDeliveryAgentSigned;
    }

    public String getIsScannedUploaded() {
        return this.isScannedUploaded;
    }

    public DeliveryDocuments isScannedUploaded(String isScannedUploaded) {
        this.setIsScannedUploaded(isScannedUploaded);
        return this;
    }

    public void setIsScannedUploaded(String isScannedUploaded) {
        this.isScannedUploaded = isScannedUploaded;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryDocuments status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DeliveryDocuments createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DeliveryDocuments createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DeliveryDocuments createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DeliveryDocuments updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DeliveryDocuments updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DeliveryDocuments updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryDocumentsUuid() {
        return this.deliveryDocumentsUuid;
    }

    public DeliveryDocuments deliveryDocumentsUuid(UUID deliveryDocumentsUuid) {
        this.setDeliveryDocumentsUuid(deliveryDocumentsUuid);
        return this;
    }

    public void setDeliveryDocumentsUuid(UUID deliveryDocumentsUuid) {
        this.deliveryDocumentsUuid = deliveryDocumentsUuid;
    }

    public String getResponseJsonData() {
        return this.responseJsonData;
    }

    public DeliveryDocuments responseJsonData(String responseJsonData) {
        this.setResponseJsonData(responseJsonData);
        return this;
    }

    public void setResponseJsonData(String responseJsonData) {
        this.responseJsonData = responseJsonData;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryDocuments)) {
            return false;
        }
        return deliveryDocId != null && deliveryDocId.equals(((DeliveryDocuments) o).deliveryDocId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryDocuments{" +
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
