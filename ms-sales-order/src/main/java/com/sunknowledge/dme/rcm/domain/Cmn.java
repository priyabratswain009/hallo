package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Cmn.
 */
@Table("t_cmn")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cmn implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("cmn_id")
    private Long cmnId;

    @Column("cmn_number")
    private String cmnNumber;

    @Column("cmn_type")
    private String cmnType;

    @Column("cmn_form_name")
    private String cmnFormName;

    @Column("patient_id")
    private Long patientId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("sales_order_no")
    private String salesOrderNo;

    @Column("cmn_create_date")
    private LocalDate cmnCreateDate;

    @Column("cmn_initial_date")
    private LocalDate cmnInitialDate;

    @Column("cmn_revision_date")
    private LocalDate cmnRevisionDate;

    @Column("cmn_recertification_date")
    private LocalDate cmnRecertificationDate;

    @Column("cmn_expiration_date")
    private LocalDate cmnExpirationDate;

    @Column("cmn_logged_by")
    private Long cmnLoggedBy;

    @Column("cmn_logged_date")
    private LocalDate cmnLoggedDate;

    @Column("cmn_approved_by")
    private Long cmnApprovedBy;

    @Column("cmn_approved_date")
    private LocalDate cmnApprovedDate;

    @Column("cmn_printed_by")
    private Long cmnPrintedBy;

    @Column("cmn_printed_date")
    private LocalDate cmnPrintedDate;

    @Column("length_of_need")
    private String lengthOfNeed;

    @Column("fax_cmn_option")
    private String faxCmnOption;

    @Column("cmn_cover_letter_inclusion_option")
    private String cmnCoverLetterInclusionOption;

    @Column("cmn_faxed_by")
    private Long cmnFaxedBy;

    @Column("cmn_faxed_date")
    private LocalDate cmnFaxedDate;

    @Column("fax_status")
    private String faxStatus;

    @Column("fax_status_reason")
    private String faxStatusReason;

    @Column("print_cmn_option")
    private String printCmnOption;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("status")
    private String status;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("cmn_id_uuid")
    private UUID cmnIdUuid;

    @Column("patient_prognosis")
    private String patientPrognosis;

    @Column("cmn_status")
    private String cmnStatus;

    @Column("refill_authorised")
    private String refillAuthorised;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCmnId() {
        return this.cmnId;
    }

    public Cmn cmnId(Long cmnId) {
        this.setCmnId(cmnId);
        return this;
    }

    public void setCmnId(Long cmnId) {
        this.cmnId = cmnId;
    }

    public String getCmnNumber() {
        return this.cmnNumber;
    }

    public Cmn cmnNumber(String cmnNumber) {
        this.setCmnNumber(cmnNumber);
        return this;
    }

    public void setCmnNumber(String cmnNumber) {
        this.cmnNumber = cmnNumber;
    }

    public String getCmnType() {
        return this.cmnType;
    }

    public Cmn cmnType(String cmnType) {
        this.setCmnType(cmnType);
        return this;
    }

    public void setCmnType(String cmnType) {
        this.cmnType = cmnType;
    }

    public String getCmnFormName() {
        return this.cmnFormName;
    }

    public Cmn cmnFormName(String cmnFormName) {
        this.setCmnFormName(cmnFormName);
        return this;
    }

    public void setCmnFormName(String cmnFormName) {
        this.cmnFormName = cmnFormName;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public Cmn patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public Cmn salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public Cmn salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public LocalDate getCmnCreateDate() {
        return this.cmnCreateDate;
    }

    public Cmn cmnCreateDate(LocalDate cmnCreateDate) {
        this.setCmnCreateDate(cmnCreateDate);
        return this;
    }

    public void setCmnCreateDate(LocalDate cmnCreateDate) {
        this.cmnCreateDate = cmnCreateDate;
    }

    public LocalDate getCmnInitialDate() {
        return this.cmnInitialDate;
    }

    public Cmn cmnInitialDate(LocalDate cmnInitialDate) {
        this.setCmnInitialDate(cmnInitialDate);
        return this;
    }

    public void setCmnInitialDate(LocalDate cmnInitialDate) {
        this.cmnInitialDate = cmnInitialDate;
    }

    public LocalDate getCmnRevisionDate() {
        return this.cmnRevisionDate;
    }

    public Cmn cmnRevisionDate(LocalDate cmnRevisionDate) {
        this.setCmnRevisionDate(cmnRevisionDate);
        return this;
    }

    public void setCmnRevisionDate(LocalDate cmnRevisionDate) {
        this.cmnRevisionDate = cmnRevisionDate;
    }

    public LocalDate getCmnRecertificationDate() {
        return this.cmnRecertificationDate;
    }

    public Cmn cmnRecertificationDate(LocalDate cmnRecertificationDate) {
        this.setCmnRecertificationDate(cmnRecertificationDate);
        return this;
    }

    public void setCmnRecertificationDate(LocalDate cmnRecertificationDate) {
        this.cmnRecertificationDate = cmnRecertificationDate;
    }

    public LocalDate getCmnExpirationDate() {
        return this.cmnExpirationDate;
    }

    public Cmn cmnExpirationDate(LocalDate cmnExpirationDate) {
        this.setCmnExpirationDate(cmnExpirationDate);
        return this;
    }

    public void setCmnExpirationDate(LocalDate cmnExpirationDate) {
        this.cmnExpirationDate = cmnExpirationDate;
    }

    public Long getCmnLoggedBy() {
        return this.cmnLoggedBy;
    }

    public Cmn cmnLoggedBy(Long cmnLoggedBy) {
        this.setCmnLoggedBy(cmnLoggedBy);
        return this;
    }

    public void setCmnLoggedBy(Long cmnLoggedBy) {
        this.cmnLoggedBy = cmnLoggedBy;
    }

    public LocalDate getCmnLoggedDate() {
        return this.cmnLoggedDate;
    }

    public Cmn cmnLoggedDate(LocalDate cmnLoggedDate) {
        this.setCmnLoggedDate(cmnLoggedDate);
        return this;
    }

    public void setCmnLoggedDate(LocalDate cmnLoggedDate) {
        this.cmnLoggedDate = cmnLoggedDate;
    }

    public Long getCmnApprovedBy() {
        return this.cmnApprovedBy;
    }

    public Cmn cmnApprovedBy(Long cmnApprovedBy) {
        this.setCmnApprovedBy(cmnApprovedBy);
        return this;
    }

    public void setCmnApprovedBy(Long cmnApprovedBy) {
        this.cmnApprovedBy = cmnApprovedBy;
    }

    public LocalDate getCmnApprovedDate() {
        return this.cmnApprovedDate;
    }

    public Cmn cmnApprovedDate(LocalDate cmnApprovedDate) {
        this.setCmnApprovedDate(cmnApprovedDate);
        return this;
    }

    public void setCmnApprovedDate(LocalDate cmnApprovedDate) {
        this.cmnApprovedDate = cmnApprovedDate;
    }

    public Long getCmnPrintedBy() {
        return this.cmnPrintedBy;
    }

    public Cmn cmnPrintedBy(Long cmnPrintedBy) {
        this.setCmnPrintedBy(cmnPrintedBy);
        return this;
    }

    public void setCmnPrintedBy(Long cmnPrintedBy) {
        this.cmnPrintedBy = cmnPrintedBy;
    }

    public LocalDate getCmnPrintedDate() {
        return this.cmnPrintedDate;
    }

    public Cmn cmnPrintedDate(LocalDate cmnPrintedDate) {
        this.setCmnPrintedDate(cmnPrintedDate);
        return this;
    }

    public void setCmnPrintedDate(LocalDate cmnPrintedDate) {
        this.cmnPrintedDate = cmnPrintedDate;
    }

    public String getLengthOfNeed() {
        return this.lengthOfNeed;
    }

    public Cmn lengthOfNeed(String lengthOfNeed) {
        this.setLengthOfNeed(lengthOfNeed);
        return this;
    }

    public void setLengthOfNeed(String lengthOfNeed) {
        this.lengthOfNeed = lengthOfNeed;
    }

    public String getFaxCmnOption() {
        return this.faxCmnOption;
    }

    public Cmn faxCmnOption(String faxCmnOption) {
        this.setFaxCmnOption(faxCmnOption);
        return this;
    }

    public void setFaxCmnOption(String faxCmnOption) {
        this.faxCmnOption = faxCmnOption;
    }

    public String getCmnCoverLetterInclusionOption() {
        return this.cmnCoverLetterInclusionOption;
    }

    public Cmn cmnCoverLetterInclusionOption(String cmnCoverLetterInclusionOption) {
        this.setCmnCoverLetterInclusionOption(cmnCoverLetterInclusionOption);
        return this;
    }

    public void setCmnCoverLetterInclusionOption(String cmnCoverLetterInclusionOption) {
        this.cmnCoverLetterInclusionOption = cmnCoverLetterInclusionOption;
    }

    public Long getCmnFaxedBy() {
        return this.cmnFaxedBy;
    }

    public Cmn cmnFaxedBy(Long cmnFaxedBy) {
        this.setCmnFaxedBy(cmnFaxedBy);
        return this;
    }

    public void setCmnFaxedBy(Long cmnFaxedBy) {
        this.cmnFaxedBy = cmnFaxedBy;
    }

    public LocalDate getCmnFaxedDate() {
        return this.cmnFaxedDate;
    }

    public Cmn cmnFaxedDate(LocalDate cmnFaxedDate) {
        this.setCmnFaxedDate(cmnFaxedDate);
        return this;
    }

    public void setCmnFaxedDate(LocalDate cmnFaxedDate) {
        this.cmnFaxedDate = cmnFaxedDate;
    }

    public String getFaxStatus() {
        return this.faxStatus;
    }

    public Cmn faxStatus(String faxStatus) {
        this.setFaxStatus(faxStatus);
        return this;
    }

    public void setFaxStatus(String faxStatus) {
        this.faxStatus = faxStatus;
    }

    public String getFaxStatusReason() {
        return this.faxStatusReason;
    }

    public Cmn faxStatusReason(String faxStatusReason) {
        this.setFaxStatusReason(faxStatusReason);
        return this;
    }

    public void setFaxStatusReason(String faxStatusReason) {
        this.faxStatusReason = faxStatusReason;
    }

    public String getPrintCmnOption() {
        return this.printCmnOption;
    }

    public Cmn printCmnOption(String printCmnOption) {
        this.setPrintCmnOption(printCmnOption);
        return this;
    }

    public void setPrintCmnOption(String printCmnOption) {
        this.printCmnOption = printCmnOption;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public Cmn createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Cmn createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public Cmn status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public Cmn updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public Cmn updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public Cmn createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public Cmn updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getCmnIdUuid() {
        return this.cmnIdUuid;
    }

    public Cmn cmnIdUuid(UUID cmnIdUuid) {
        this.setCmnIdUuid(cmnIdUuid);
        return this;
    }

    public void setCmnIdUuid(UUID cmnIdUuid) {
        this.cmnIdUuid = cmnIdUuid;
    }

    public String getPatientPrognosis() {
        return this.patientPrognosis;
    }

    public Cmn patientPrognosis(String patientPrognosis) {
        this.setPatientPrognosis(patientPrognosis);
        return this;
    }

    public void setPatientPrognosis(String patientPrognosis) {
        this.patientPrognosis = patientPrognosis;
    }

    public String getCmnStatus() {
        return this.cmnStatus;
    }

    public Cmn cmnStatus(String cmnStatus) {
        this.setCmnStatus(cmnStatus);
        return this;
    }

    public void setCmnStatus(String cmnStatus) {
        this.cmnStatus = cmnStatus;
    }

    public String getRefillAuthorised() {
        return this.refillAuthorised;
    }

    public Cmn refillAuthorised(String refillAuthorised) {
        this.setRefillAuthorised(refillAuthorised);
        return this;
    }

    public void setRefillAuthorised(String refillAuthorised) {
        this.refillAuthorised = refillAuthorised;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cmn)) {
            return false;
        }
        return cmnId != null && cmnId.equals(((Cmn) o).cmnId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cmn{" +
            "cmnId=" + getCmnId() +
            ", cmnNumber='" + getCmnNumber() + "'" +
            ", cmnType='" + getCmnType() + "'" +
            ", cmnFormName='" + getCmnFormName() + "'" +
            ", patientId=" + getPatientId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", cmnCreateDate='" + getCmnCreateDate() + "'" +
            ", cmnInitialDate='" + getCmnInitialDate() + "'" +
            ", cmnRevisionDate='" + getCmnRevisionDate() + "'" +
            ", cmnRecertificationDate='" + getCmnRecertificationDate() + "'" +
            ", cmnExpirationDate='" + getCmnExpirationDate() + "'" +
            ", cmnLoggedBy=" + getCmnLoggedBy() +
            ", cmnLoggedDate='" + getCmnLoggedDate() + "'" +
            ", cmnApprovedBy=" + getCmnApprovedBy() +
            ", cmnApprovedDate='" + getCmnApprovedDate() + "'" +
            ", cmnPrintedBy=" + getCmnPrintedBy() +
            ", cmnPrintedDate='" + getCmnPrintedDate() + "'" +
            ", lengthOfNeed='" + getLengthOfNeed() + "'" +
            ", faxCmnOption='" + getFaxCmnOption() + "'" +
            ", cmnCoverLetterInclusionOption='" + getCmnCoverLetterInclusionOption() + "'" +
            ", cmnFaxedBy=" + getCmnFaxedBy() +
            ", cmnFaxedDate='" + getCmnFaxedDate() + "'" +
            ", faxStatus='" + getFaxStatus() + "'" +
            ", faxStatusReason='" + getFaxStatusReason() + "'" +
            ", printCmnOption='" + getPrintCmnOption() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", cmnIdUuid='" + getCmnIdUuid() + "'" +
            ", patientPrognosis='" + getPatientPrognosis() + "'" +
            ", cmnStatus='" + getCmnStatus() + "'" +
            ", refillAuthorised='" + getRefillAuthorised() + "'" +
            "}";
    }
}
