package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InsuranceDocument.
 */
@Entity
@Table(name = "t_insurance_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InsuranceDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "insurance_document_id")
    private Long insuranceDocumentId;

    @Column(name = "insurance_id")
    private Long insuranceId;

    @Column(name = "insurance_name")
    private String insuranceName;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "document_type_id")
    private Long documentTypeId;

    @Column(name = "document_type_name")
    private String documentTypeName;

    @Column(name = "document_date")
    private LocalDate documentDate;

    @Column(name = "document_note")
    private String documentNote;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_id")
    private String createdById;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "document_title")
    private String documentTitle;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "scan_date")
    private LocalDate scanDate;

    @Column(name = "scan_by")
    private Long scanBy;

    @Column(name = "file_upload_path")
    private String fileUploadPath;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "upload_by")
    private Long uploadBy;

    @Column(name = "review_status")
    private String reviewStatus;

    @Column(name = "review_reason")
    private String reviewReason;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @Column(name = "review_by")
    private Long reviewBy;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "insurance_document_uuid")
    private UUID insuranceDocumentUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInsuranceDocumentId() {
        return this.insuranceDocumentId;
    }

    public InsuranceDocument insuranceDocumentId(Long insuranceDocumentId) {
        this.setInsuranceDocumentId(insuranceDocumentId);
        return this;
    }

    public void setInsuranceDocumentId(Long insuranceDocumentId) {
        this.insuranceDocumentId = insuranceDocumentId;
    }

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public InsuranceDocument insuranceId(Long insuranceId) {
        this.setInsuranceId(insuranceId);
        return this;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public InsuranceDocument insuranceName(String insuranceName) {
        this.setInsuranceName(insuranceName);
        return this;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public InsuranceDocument branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public InsuranceDocument branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getDocumentTypeId() {
        return this.documentTypeId;
    }

    public InsuranceDocument documentTypeId(Long documentTypeId) {
        this.setDocumentTypeId(documentTypeId);
        return this;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeName() {
        return this.documentTypeName;
    }

    public InsuranceDocument documentTypeName(String documentTypeName) {
        this.setDocumentTypeName(documentTypeName);
        return this;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public LocalDate getDocumentDate() {
        return this.documentDate;
    }

    public InsuranceDocument documentDate(LocalDate documentDate) {
        this.setDocumentDate(documentDate);
        return this;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNote() {
        return this.documentNote;
    }

    public InsuranceDocument documentNote(String documentNote) {
        this.setDocumentNote(documentNote);
        return this;
    }

    public void setDocumentNote(String documentNote) {
        this.documentNote = documentNote;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InsuranceDocument createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedById() {
        return this.createdById;
    }

    public InsuranceDocument createdById(String createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getStatus() {
        return this.status;
    }

    public InsuranceDocument status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InsuranceDocument updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InsuranceDocument updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public InsuranceDocument documentTitle(String documentTitle) {
        this.setDocumentTitle(documentTitle);
        return this;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public InsuranceDocument documentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public LocalDate getScanDate() {
        return this.scanDate;
    }

    public InsuranceDocument scanDate(LocalDate scanDate) {
        this.setScanDate(scanDate);
        return this;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }

    public Long getScanBy() {
        return this.scanBy;
    }

    public InsuranceDocument scanBy(Long scanBy) {
        this.setScanBy(scanBy);
        return this;
    }

    public void setScanBy(Long scanBy) {
        this.scanBy = scanBy;
    }

    public String getFileUploadPath() {
        return this.fileUploadPath;
    }

    public InsuranceDocument fileUploadPath(String fileUploadPath) {
        this.setFileUploadPath(fileUploadPath);
        return this;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public LocalDate getUploadDate() {
        return this.uploadDate;
    }

    public InsuranceDocument uploadDate(LocalDate uploadDate) {
        this.setUploadDate(uploadDate);
        return this;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getUploadBy() {
        return this.uploadBy;
    }

    public InsuranceDocument uploadBy(Long uploadBy) {
        this.setUploadBy(uploadBy);
        return this;
    }

    public void setUploadBy(Long uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getReviewStatus() {
        return this.reviewStatus;
    }

    public InsuranceDocument reviewStatus(String reviewStatus) {
        this.setReviewStatus(reviewStatus);
        return this;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewReason() {
        return this.reviewReason;
    }

    public InsuranceDocument reviewReason(String reviewReason) {
        this.setReviewReason(reviewReason);
        return this;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public LocalDate getReviewDate() {
        return this.reviewDate;
    }

    public InsuranceDocument reviewDate(LocalDate reviewDate) {
        this.setReviewDate(reviewDate);
        return this;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewBy() {
        return this.reviewBy;
    }

    public InsuranceDocument reviewBy(Long reviewBy) {
        this.setReviewBy(reviewBy);
        return this;
    }

    public void setReviewBy(Long reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InsuranceDocument createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InsuranceDocument updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getInsuranceDocumentUuid() {
        return this.insuranceDocumentUuid;
    }

    public InsuranceDocument insuranceDocumentUuid(UUID insuranceDocumentUuid) {
        this.setInsuranceDocumentUuid(insuranceDocumentUuid);
        return this;
    }

    public void setInsuranceDocumentUuid(UUID insuranceDocumentUuid) {
        this.insuranceDocumentUuid = insuranceDocumentUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceDocument)) {
            return false;
        }
        return insuranceDocumentId != null && insuranceDocumentId.equals(((InsuranceDocument) o).insuranceDocumentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceDocument{" +
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
