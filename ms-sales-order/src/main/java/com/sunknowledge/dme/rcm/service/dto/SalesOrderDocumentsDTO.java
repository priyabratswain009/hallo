package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderDocuments} entity.
 */
public class SalesOrderDocumentsDTO implements Serializable {

    private Long patientId;

    private String patientFirstName;

    private LocalDate patientDob;

    private LocalDate patientDod;

    private String patientSsn;

    private String qmbStatus;

    private String patientGender;

    private Double patientHeight;

    private Double patientWeight;

    private String patientContact1;

    private String patientContact2;

    private String email;

    private String hipaaOnFileStatus;

    private Long branchId;

    private String branchName;

    private Long documentTypeId;

    private String documentTypeName;

    private LocalDate documentDate;

    private String documentNote;

    private LocalDate createdDate;

    private Long createdById;

    private String status;

    private Long salesOrderDocumentId;

    private Long updatedById;

    private LocalDate updatedDate;

    private String fax;

    private String documentTitle;

    private String documentName;

    private Long scanBy;

    private String fileUploadPath;

    private LocalDate uploadDate;

    private Long uploadBy;

    private LocalDate scanDate;

    private String reviewStatus;

    private String reviewReason;

    private LocalDate reviewDate;

    private Long reviewBy;

    private String createdByName;

    private String updatedByName;

    private Long salesOrderId;

    private String salesOrderNo;

    private LocalDate salesOrderCreationDate;

    private UUID salesOrderDocumentsUuid;

    private String patientMiddleName;

    private String patientLastName;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public LocalDate getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public LocalDate getPatientDod() {
        return patientDod;
    }

    public void setPatientDod(LocalDate patientDod) {
        this.patientDod = patientDod;
    }

    public String getPatientSsn() {
        return patientSsn;
    }

    public void setPatientSsn(String patientSsn) {
        this.patientSsn = patientSsn;
    }

    public String getQmbStatus() {
        return qmbStatus;
    }

    public void setQmbStatus(String qmbStatus) {
        this.qmbStatus = qmbStatus;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public Double getPatientHeight() {
        return patientHeight;
    }

    public void setPatientHeight(Double patientHeight) {
        this.patientHeight = patientHeight;
    }

    public Double getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(Double patientWeight) {
        this.patientWeight = patientWeight;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHipaaOnFileStatus() {
        return hipaaOnFileStatus;
    }

    public void setHipaaOnFileStatus(String hipaaOnFileStatus) {
        this.hipaaOnFileStatus = hipaaOnFileStatus;
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

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNote() {
        return documentNote;
    }

    public void setDocumentNote(String documentNote) {
        this.documentNote = documentNote;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSalesOrderDocumentId() {
        return salesOrderDocumentId;
    }

    public void setSalesOrderDocumentId(Long salesOrderDocumentId) {
        this.salesOrderDocumentId = salesOrderDocumentId;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getScanBy() {
        return scanBy;
    }

    public void setScanBy(Long scanBy) {
        this.scanBy = scanBy;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(Long uploadBy) {
        this.uploadBy = uploadBy;
    }

    public LocalDate getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(Long reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public LocalDate getSalesOrderCreationDate() {
        return salesOrderCreationDate;
    }

    public void setSalesOrderCreationDate(LocalDate salesOrderCreationDate) {
        this.salesOrderCreationDate = salesOrderCreationDate;
    }

    public UUID getSalesOrderDocumentsUuid() {
        return salesOrderDocumentsUuid;
    }

    public void setSalesOrderDocumentsUuid(UUID salesOrderDocumentsUuid) {
        this.salesOrderDocumentsUuid = salesOrderDocumentsUuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderDocumentsDTO)) {
            return false;
        }

        SalesOrderDocumentsDTO salesOrderDocumentsDTO = (SalesOrderDocumentsDTO) o;
        if (this.salesOrderDocumentId == null) {
            return false;
        }
        return Objects.equals(this.salesOrderDocumentId, salesOrderDocumentsDTO.salesOrderDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.salesOrderDocumentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderDocumentsDTO{" +
            "patientId=" + getPatientId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientDob='" + getPatientDob() + "'" +
            ", patientDod='" + getPatientDod() + "'" +
            ", patientSsn='" + getPatientSsn() + "'" +
            ", qmbStatus='" + getQmbStatus() + "'" +
            ", patientGender='" + getPatientGender() + "'" +
            ", patientHeight=" + getPatientHeight() +
            ", patientWeight=" + getPatientWeight() +
            ", patientContact1='" + getPatientContact1() + "'" +
            ", patientContact2='" + getPatientContact2() + "'" +
            ", email='" + getEmail() + "'" +
            ", hipaaOnFileStatus='" + getHipaaOnFileStatus() + "'" +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", documentTypeId=" + getDocumentTypeId() +
            ", documentTypeName='" + getDocumentTypeName() + "'" +
            ", documentDate='" + getDocumentDate() + "'" +
            ", documentNote='" + getDocumentNote() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", status='" + getStatus() + "'" +
            ", salesOrderDocumentId=" + getSalesOrderDocumentId() +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", fax='" + getFax() + "'" +
            ", documentTitle='" + getDocumentTitle() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", scanBy=" + getScanBy() +
            ", fileUploadPath='" + getFileUploadPath() + "'" +
            ", uploadDate='" + getUploadDate() + "'" +
            ", uploadBy=" + getUploadBy() +
            ", scanDate='" + getScanDate() + "'" +
            ", reviewStatus='" + getReviewStatus() + "'" +
            ", reviewReason='" + getReviewReason() + "'" +
            ", reviewDate='" + getReviewDate() + "'" +
            ", reviewBy=" + getReviewBy() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", salesOrderCreationDate='" + getSalesOrderCreationDate() + "'" +
            ", salesOrderDocumentsUuid='" + getSalesOrderDocumentsUuid() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            "}";
    }
}
