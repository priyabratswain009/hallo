package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A IcdMaster.
 */
@Entity
@Table(name = "t_icd_master")
public class IcdMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "icd_master_id", nullable = false)
    private Long icdMasterId;

    @Column(name = "icd_code")
    private String icdCode;

    @Column(name = "icd_code_desc")
    private String icdCodeDesc;

    @Column(name = "icd_code_type")
    private String icdCodeType;

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

    @Column(name = "icd_master_uuid")
    private UUID icdMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIcdMasterId() {
        return this.icdMasterId;
    }

    public IcdMaster icdMasterId(Long icdMasterId) {
        this.setIcdMasterId(icdMasterId);
        return this;
    }

    public void setIcdMasterId(Long icdMasterId) {
        this.icdMasterId = icdMasterId;
    }

    public String getIcdCode() {
        return this.icdCode;
    }

    public IcdMaster icdCode(String icdCode) {
        this.setIcdCode(icdCode);
        return this;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getIcdCodeDesc() {
        return this.icdCodeDesc;
    }

    public IcdMaster icdCodeDesc(String icdCodeDesc) {
        this.setIcdCodeDesc(icdCodeDesc);
        return this;
    }

    public void setIcdCodeDesc(String icdCodeDesc) {
        this.icdCodeDesc = icdCodeDesc;
    }

    public String getIcdCodeType() {
        return this.icdCodeType;
    }

    public IcdMaster icdCodeType(String icdCodeType) {
        this.setIcdCodeType(icdCodeType);
        return this;
    }

    public void setIcdCodeType(String icdCodeType) {
        this.icdCodeType = icdCodeType;
    }

    public String getStatus() {
        return this.status;
    }

    public IcdMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public IcdMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public IcdMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public IcdMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public IcdMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public IcdMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public IcdMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getIcdMasterUuid() {
        return this.icdMasterUuid;
    }

    public IcdMaster icdMasterUuid(UUID icdMasterUuid) {
        this.setIcdMasterUuid(icdMasterUuid);
        return this;
    }

    public void setIcdMasterUuid(UUID icdMasterUuid) {
        this.icdMasterUuid = icdMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IcdMaster)) {
            return false;
        }
        return icdMasterId != null && icdMasterId.equals(((IcdMaster) o).icdMasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IcdMaster{" +
            "icdMasterId=" + getIcdMasterId() +
            ", icdCode='" + getIcdCode() + "'" +
            ", icdCodeDesc='" + getIcdCodeDesc() + "'" +
            ", icdCodeType='" + getIcdCodeType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", icdMasterUuid='" + getIcdMasterUuid() + "'" +
            "}";
    }
}
