package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InsuranceDocument} entity.
 */
public class InsuranceDocumentDTO implements Serializable {

    private Long insuranceDocumentId;

    private Long insuranceId;

    private String insuranceName;

    private Long branchId;

    private String branchName;

    private Long documentTypeId;

    private String documentTypeName;

    private LocalDate documentDate;

    private String documentNote;

    private LocalDate createdDate;

    private String createdById;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String documentTitle;

    private String documentName;

    private LocalDate scanDate;

    private Long scanBy;

    private String fileUploadPath;

    private LocalDate uploadDate;

    private Long uploadBy;

    private String reviewStatus;

    private String reviewReason;

    private LocalDate reviewDate;

    private Long reviewBy;

    private String createdByName;

    private String updatedByName;

    private UUID insuranceDocumentUuid;

    public Long getInsuranceDocumentId() {
        return insuranceDocumentId;
    }

    public void setInsuranceDocumentId(Long insuranceDocumentId) {
        this.insuranceDocumentId = insuranceDocumentId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
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

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDate getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
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

    public UUID getInsuranceDocumentUuid() {
        return insuranceDocumentUuid;
    }

    public void setInsuranceDocumentUuid(UUID insuranceDocumentUuid) {
        this.insuranceDocumentUuid = insuranceDocumentUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceDocumentDTO)) {
            return false;
        }

        InsuranceDocumentDTO insuranceDocumentDTO = (InsuranceDocumentDTO) o;
        if (this.insuranceDocumentId == null) {
            return false;
        }
        return Objects.equals(this.insuranceDocumentId, insuranceDocumentDTO.insuranceDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.insuranceDocumentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceDocumentDTO{" +
            "insuranceDocumentId=" + getInsuranceDocumentId() +
            ", insuranceId=" + getInsuranceId() +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", documentTypeId=" + getDocumentTypeId() +
            ", documentTypeName='" + getDocumentTypeName() + "'" +
            ", documentDate='" + getDocumentDate() + "'" +
            ", documentNote='" + getDocumentNote() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById='" + getCreatedById() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", documentTitle='" + getDocumentTitle() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", scanDate='" + getScanDate() + "'" +
            ", scanBy=" + getScanBy() +
            ", fileUploadPath='" + getFileUploadPath() + "'" +
            ", uploadDate='" + getUploadDate() + "'" +
            ", uploadBy=" + getUploadBy() +
            ", reviewStatus='" + getReviewStatus() + "'" +
            ", reviewReason='" + getReviewReason() + "'" +
            ", reviewDate='" + getReviewDate() + "'" +
            ", reviewBy=" + getReviewBy() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", insuranceDocumentUuid='" + getInsuranceDocumentUuid() + "'" +
            "}";
    }
}
