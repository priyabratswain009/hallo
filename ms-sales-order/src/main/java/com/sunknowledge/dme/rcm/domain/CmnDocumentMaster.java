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
 * A CmnDocumentMaster.
 */
@Table("t_cmn_document_master")
public class CmnDocumentMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("cmn_document_id")
    private Long cmnDocumentId;

    @Column("cmn_id")
    private Long cmnId;

    @Column("cmn_no")
    private String cmnNo;

    @Column("generation_date")
    private LocalDate generationDate;

    @Column("initial_document_name")
    private String initialDocumentName;

    @Column("fax_status")
    private String faxStatus;

    @Column("out_bound_fax_status")
    private String outBoundFaxStatus;

    @Column("out_bound_fax_no")
    private String outBoundFaxNo;

    @Column("out_bound_fax_date_time")
    private LocalDate outBoundFaxDateTime;

    @Column("in_bound_fax_status")
    private String inBoundFaxStatus;

    @Column("in_bound_fax_no")
    private String inBoundFaxNo;

    @Column("in_bound_fax_date_time")
    private LocalDate inBoundFaxDateTime;

    @Column("cmn_document_master_uuid")
    private UUID cmnDocumentMasterUuid;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("return_document_name")
    private String returnDocumentName;

    @Column("cmn_comments")
    private String cmnComments;

    @Column("status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCmnDocumentId() {
        return this.cmnDocumentId;
    }

    public CmnDocumentMaster cmnDocumentId(Long cmnDocumentId) {
        this.setCmnDocumentId(cmnDocumentId);
        return this;
    }

    public void setCmnDocumentId(Long cmnDocumentId) {
        this.cmnDocumentId = cmnDocumentId;
    }

    public Long getCmnId() {
        return this.cmnId;
    }

    public CmnDocumentMaster cmnId(Long cmnId) {
        this.setCmnId(cmnId);
        return this;
    }

    public void setCmnId(Long cmnId) {
        this.cmnId = cmnId;
    }

    public String getCmnNo() {
        return this.cmnNo;
    }

    public CmnDocumentMaster cmnNo(String cmnNo) {
        this.setCmnNo(cmnNo);
        return this;
    }

    public void setCmnNo(String cmnNo) {
        this.cmnNo = cmnNo;
    }

    public LocalDate getGenerationDate() {
        return this.generationDate;
    }

    public CmnDocumentMaster generationDate(LocalDate generationDate) {
        this.setGenerationDate(generationDate);
        return this;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public String getInitialDocumentName() {
        return this.initialDocumentName;
    }

    public CmnDocumentMaster initialDocumentName(String initialDocumentName) {
        this.setInitialDocumentName(initialDocumentName);
        return this;
    }

    public void setInitialDocumentName(String initialDocumentName) {
        this.initialDocumentName = initialDocumentName;
    }

    public String getFaxStatus() {
        return this.faxStatus;
    }

    public CmnDocumentMaster faxStatus(String faxStatus) {
        this.setFaxStatus(faxStatus);
        return this;
    }

    public void setFaxStatus(String faxStatus) {
        this.faxStatus = faxStatus;
    }

    public String getOutBoundFaxStatus() {
        return this.outBoundFaxStatus;
    }

    public CmnDocumentMaster outBoundFaxStatus(String outBoundFaxStatus) {
        this.setOutBoundFaxStatus(outBoundFaxStatus);
        return this;
    }

    public void setOutBoundFaxStatus(String outBoundFaxStatus) {
        this.outBoundFaxStatus = outBoundFaxStatus;
    }

    public String getOutBoundFaxNo() {
        return this.outBoundFaxNo;
    }

    public CmnDocumentMaster outBoundFaxNo(String outBoundFaxNo) {
        this.setOutBoundFaxNo(outBoundFaxNo);
        return this;
    }

    public void setOutBoundFaxNo(String outBoundFaxNo) {
        this.outBoundFaxNo = outBoundFaxNo;
    }

    public LocalDate getOutBoundFaxDateTime() {
        return this.outBoundFaxDateTime;
    }

    public CmnDocumentMaster outBoundFaxDateTime(LocalDate outBoundFaxDateTime) {
        this.setOutBoundFaxDateTime(outBoundFaxDateTime);
        return this;
    }

    public void setOutBoundFaxDateTime(LocalDate outBoundFaxDateTime) {
        this.outBoundFaxDateTime = outBoundFaxDateTime;
    }

    public String getInBoundFaxStatus() {
        return this.inBoundFaxStatus;
    }

    public CmnDocumentMaster inBoundFaxStatus(String inBoundFaxStatus) {
        this.setInBoundFaxStatus(inBoundFaxStatus);
        return this;
    }

    public void setInBoundFaxStatus(String inBoundFaxStatus) {
        this.inBoundFaxStatus = inBoundFaxStatus;
    }

    public String getInBoundFaxNo() {
        return this.inBoundFaxNo;
    }

    public CmnDocumentMaster inBoundFaxNo(String inBoundFaxNo) {
        this.setInBoundFaxNo(inBoundFaxNo);
        return this;
    }

    public void setInBoundFaxNo(String inBoundFaxNo) {
        this.inBoundFaxNo = inBoundFaxNo;
    }

    public LocalDate getInBoundFaxDateTime() {
        return this.inBoundFaxDateTime;
    }

    public CmnDocumentMaster inBoundFaxDateTime(LocalDate inBoundFaxDateTime) {
        this.setInBoundFaxDateTime(inBoundFaxDateTime);
        return this;
    }

    public void setInBoundFaxDateTime(LocalDate inBoundFaxDateTime) {
        this.inBoundFaxDateTime = inBoundFaxDateTime;
    }

    public UUID getCmnDocumentMasterUuid() {
        return this.cmnDocumentMasterUuid;
    }

    public CmnDocumentMaster cmnDocumentMasterUuid(UUID cmnDocumentMasterUuid) {
        this.setCmnDocumentMasterUuid(cmnDocumentMasterUuid);
        return this;
    }

    public void setCmnDocumentMasterUuid(UUID cmnDocumentMasterUuid) {
        this.cmnDocumentMasterUuid = cmnDocumentMasterUuid;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public CmnDocumentMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public CmnDocumentMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public CmnDocumentMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public CmnDocumentMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public CmnDocumentMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public CmnDocumentMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getReturnDocumentName() {
        return this.returnDocumentName;
    }

    public CmnDocumentMaster returnDocumentName(String returnDocumentName) {
        this.setReturnDocumentName(returnDocumentName);
        return this;
    }

    public void setReturnDocumentName(String returnDocumentName) {
        this.returnDocumentName = returnDocumentName;
    }

    public String getCmnComments() {
        return this.cmnComments;
    }

    public CmnDocumentMaster cmnComments(String cmnComments) {
        this.setCmnComments(cmnComments);
        return this;
    }

    public void setCmnComments(String cmnComments) {
        this.cmnComments = cmnComments;
    }

    public String getStatus() {
        return this.status;
    }

    public CmnDocumentMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CmnDocumentMaster)) {
            return false;
        }
        return cmnDocumentId != null && cmnDocumentId.equals(((CmnDocumentMaster) o).cmnDocumentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CmnDocumentMaster{" +
            "cmnDocumentId=" + getCmnDocumentId() +
            ", cmnId=" + getCmnId() +
            ", cmnNo='" + getCmnNo() + "'" +
            ", generationDate='" + getGenerationDate() + "'" +
            ", initialDocumentName='" + getInitialDocumentName() + "'" +
            ", faxStatus='" + getFaxStatus() + "'" +
            ", outBoundFaxStatus='" + getOutBoundFaxStatus() + "'" +
            ", outBoundFaxNo='" + getOutBoundFaxNo() + "'" +
            ", outBoundFaxDateTime='" + getOutBoundFaxDateTime() + "'" +
            ", inBoundFaxStatus='" + getInBoundFaxStatus() + "'" +
            ", inBoundFaxNo='" + getInBoundFaxNo() + "'" +
            ", inBoundFaxDateTime='" + getInBoundFaxDateTime() + "'" +
            ", cmnDocumentMasterUuid='" + getCmnDocumentMasterUuid() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", returnDocumentName='" + getReturnDocumentName() + "'" +
            ", cmnComments='" + getCmnComments() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
