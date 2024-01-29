package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderDocuments.
 */
@Table("t_sales_order_documents")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_dob")
    private LocalDate patientDob;

    @Column("patient_dod")
    private LocalDate patientDod;

    @Column("patient_ssn")
    private String patientSsn;

    @Column("qmb_status")
    private String qmbStatus;

    @Column("patient_gender")
    private String patientGender;

    @Column("patient_height")
    private Double patientHeight;

    @Column("patient_weight")
    private Double patientWeight;

    @Column("patient_contact_1")
    private String patientContact1;

    @Column("patient_contact_2")
    private String patientContact2;

    @Column("email")
    private String email;

    @Column("hipaa_on_file_status")
    private String hipaaOnFileStatus;

    @Column("branch_id")
    private Long branchId;

    @Column("branch_name")
    private String branchName;

    @Column("document_type_id")
    private Long documentTypeId;

    @Column("document_type_name")
    private String documentTypeName;

    @Column("document_date")
    private LocalDate documentDate;

    @Column("document_note")
    private String documentNote;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("status")
    private String status;

    @Id
    @Column("sales_order_document_id")
    private Long salesOrderDocumentId;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("fax")
    private String fax;

    @Column("document_title")
    private String documentTitle;

    @Column("document_name")
    private String documentName;

    @Column("scan_by")
    private Long scanBy;

    @Column("file_upload_path")
    private String fileUploadPath;

    @Column("upload_date")
    private LocalDate uploadDate;

    @Column("upload_by")
    private Long uploadBy;

    @Column("scan_date")
    private LocalDate scanDate;

    @Column("review_status")
    private String reviewStatus;

    @Column("review_reason")
    private String reviewReason;

    @Column("review_date")
    private LocalDate reviewDate;

    @Column("review_by")
    private Long reviewBy;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("sales_order_no")
    private String salesOrderNo;

    @Column("sales_order_creation_date")
    private LocalDate salesOrderCreationDate;

    @Column("sales_order_documents_uuid")
    private UUID salesOrderDocumentsUuid;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientId() {
        return this.patientId;
    }

    public SalesOrderDocuments patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public SalesOrderDocuments patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public LocalDate getPatientDob() {
        return this.patientDob;
    }

    public SalesOrderDocuments patientDob(LocalDate patientDob) {
        this.setPatientDob(patientDob);
        return this;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public LocalDate getPatientDod() {
        return this.patientDod;
    }

    public SalesOrderDocuments patientDod(LocalDate patientDod) {
        this.setPatientDod(patientDod);
        return this;
    }

    public void setPatientDod(LocalDate patientDod) {
        this.patientDod = patientDod;
    }

    public String getPatientSsn() {
        return this.patientSsn;
    }

    public SalesOrderDocuments patientSsn(String patientSsn) {
        this.setPatientSsn(patientSsn);
        return this;
    }

    public void setPatientSsn(String patientSsn) {
        this.patientSsn = patientSsn;
    }

    public String getQmbStatus() {
        return this.qmbStatus;
    }

    public SalesOrderDocuments qmbStatus(String qmbStatus) {
        this.setQmbStatus(qmbStatus);
        return this;
    }

    public void setQmbStatus(String qmbStatus) {
        this.qmbStatus = qmbStatus;
    }

    public String getPatientGender() {
        return this.patientGender;
    }

    public SalesOrderDocuments patientGender(String patientGender) {
        this.setPatientGender(patientGender);
        return this;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public Double getPatientHeight() {
        return this.patientHeight;
    }

    public SalesOrderDocuments patientHeight(Double patientHeight) {
        this.setPatientHeight(patientHeight);
        return this;
    }

    public void setPatientHeight(Double patientHeight) {
        this.patientHeight = patientHeight;
    }

    public Double getPatientWeight() {
        return this.patientWeight;
    }

    public SalesOrderDocuments patientWeight(Double patientWeight) {
        this.setPatientWeight(patientWeight);
        return this;
    }

    public void setPatientWeight(Double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientContact1() {
        return this.patientContact1;
    }

    public SalesOrderDocuments patientContact1(String patientContact1) {
        this.setPatientContact1(patientContact1);
        return this;
    }

    public void setPatientContact1(String patientContact1) {
        this.patientContact1 = patientContact1;
    }

    public String getPatientContact2() {
        return this.patientContact2;
    }

    public SalesOrderDocuments patientContact2(String patientContact2) {
        this.setPatientContact2(patientContact2);
        return this;
    }

    public void setPatientContact2(String patientContact2) {
        this.patientContact2 = patientContact2;
    }

    public String getEmail() {
        return this.email;
    }

    public SalesOrderDocuments email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHipaaOnFileStatus() {
        return this.hipaaOnFileStatus;
    }

    public SalesOrderDocuments hipaaOnFileStatus(String hipaaOnFileStatus) {
        this.setHipaaOnFileStatus(hipaaOnFileStatus);
        return this;
    }

    public void setHipaaOnFileStatus(String hipaaOnFileStatus) {
        this.hipaaOnFileStatus = hipaaOnFileStatus;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public SalesOrderDocuments branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public SalesOrderDocuments branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getDocumentTypeId() {
        return this.documentTypeId;
    }

    public SalesOrderDocuments documentTypeId(Long documentTypeId) {
        this.setDocumentTypeId(documentTypeId);
        return this;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeName() {
        return this.documentTypeName;
    }

    public SalesOrderDocuments documentTypeName(String documentTypeName) {
        this.setDocumentTypeName(documentTypeName);
        return this;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public LocalDate getDocumentDate() {
        return this.documentDate;
    }

    public SalesOrderDocuments documentDate(LocalDate documentDate) {
        this.setDocumentDate(documentDate);
        return this;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNote() {
        return this.documentNote;
    }

    public SalesOrderDocuments documentNote(String documentNote) {
        this.setDocumentNote(documentNote);
        return this;
    }

    public void setDocumentNote(String documentNote) {
        this.documentNote = documentNote;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderDocuments createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderDocuments createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getStatus() {
        return this.status;
    }

    public SalesOrderDocuments status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSalesOrderDocumentId() {
        return this.salesOrderDocumentId;
    }

    public SalesOrderDocuments salesOrderDocumentId(Long salesOrderDocumentId) {
        this.setSalesOrderDocumentId(salesOrderDocumentId);
        return this;
    }

    public void setSalesOrderDocumentId(Long salesOrderDocumentId) {
        this.salesOrderDocumentId = salesOrderDocumentId;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderDocuments updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderDocuments updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getFax() {
        return this.fax;
    }

    public SalesOrderDocuments fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDocumentTitle() {
        return this.documentTitle;
    }

    public SalesOrderDocuments documentTitle(String documentTitle) {
        this.setDocumentTitle(documentTitle);
        return this;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public SalesOrderDocuments documentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getScanBy() {
        return this.scanBy;
    }

    public SalesOrderDocuments scanBy(Long scanBy) {
        this.setScanBy(scanBy);
        return this;
    }

    public void setScanBy(Long scanBy) {
        this.scanBy = scanBy;
    }

    public String getFileUploadPath() {
        return this.fileUploadPath;
    }

    public SalesOrderDocuments fileUploadPath(String fileUploadPath) {
        this.setFileUploadPath(fileUploadPath);
        return this;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public LocalDate getUploadDate() {
        return this.uploadDate;
    }

    public SalesOrderDocuments uploadDate(LocalDate uploadDate) {
        this.setUploadDate(uploadDate);
        return this;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getUploadBy() {
        return this.uploadBy;
    }

    public SalesOrderDocuments uploadBy(Long uploadBy) {
        this.setUploadBy(uploadBy);
        return this;
    }

    public void setUploadBy(Long uploadBy) {
        this.uploadBy = uploadBy;
    }

    public LocalDate getScanDate() {
        return this.scanDate;
    }

    public SalesOrderDocuments scanDate(LocalDate scanDate) {
        this.setScanDate(scanDate);
        return this;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }

    public String getReviewStatus() {
        return this.reviewStatus;
    }

    public SalesOrderDocuments reviewStatus(String reviewStatus) {
        this.setReviewStatus(reviewStatus);
        return this;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewReason() {
        return this.reviewReason;
    }

    public SalesOrderDocuments reviewReason(String reviewReason) {
        this.setReviewReason(reviewReason);
        return this;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public LocalDate getReviewDate() {
        return this.reviewDate;
    }

    public SalesOrderDocuments reviewDate(LocalDate reviewDate) {
        this.setReviewDate(reviewDate);
        return this;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewBy() {
        return this.reviewBy;
    }

    public SalesOrderDocuments reviewBy(Long reviewBy) {
        this.setReviewBy(reviewBy);
        return this;
    }

    public void setReviewBy(Long reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderDocuments createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderDocuments updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SalesOrderDocuments salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public SalesOrderDocuments salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public LocalDate getSalesOrderCreationDate() {
        return this.salesOrderCreationDate;
    }

    public SalesOrderDocuments salesOrderCreationDate(LocalDate salesOrderCreationDate) {
        this.setSalesOrderCreationDate(salesOrderCreationDate);
        return this;
    }

    public void setSalesOrderCreationDate(LocalDate salesOrderCreationDate) {
        this.salesOrderCreationDate = salesOrderCreationDate;
    }

    public UUID getSalesOrderDocumentsUuid() {
        return this.salesOrderDocumentsUuid;
    }

    public SalesOrderDocuments salesOrderDocumentsUuid(UUID salesOrderDocumentsUuid) {
        this.setSalesOrderDocumentsUuid(salesOrderDocumentsUuid);
        return this;
    }

    public void setSalesOrderDocumentsUuid(UUID salesOrderDocumentsUuid) {
        this.salesOrderDocumentsUuid = salesOrderDocumentsUuid;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public SalesOrderDocuments patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public SalesOrderDocuments patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderDocuments)) {
            return false;
        }
        return salesOrderDocumentId != null && salesOrderDocumentId.equals(((SalesOrderDocuments) o).salesOrderDocumentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderDocuments{" +
            "salesOrderDocumentId=" + getSalesOrderDocumentId() +
            ", patientId=" + getPatientId() +
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
