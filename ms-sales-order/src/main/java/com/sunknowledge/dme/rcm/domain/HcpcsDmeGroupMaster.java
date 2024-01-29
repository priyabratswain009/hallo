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
 * A HcpcsDmeGroupMaster.
 */
@Table("t_hcpcs_dme_group_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HcpcsDmeGroupMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("hcpcs_dme_id")
    private Long hcpcsDmeId;

    @Column("hcpcs_code")
    private String hcpcsCode;

    @Column("dme_group_name")
    private String dmeGroupName;

    @Column("dme_group_id")
    private Long dmeGroupId;

    @Column("status")
    private String status;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("hcpcs_dme_group_master_uuid")
    private UUID hcpcsDmeGroupMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getHcpcsDmeId() {
        return this.hcpcsDmeId;
    }

    public HcpcsDmeGroupMaster hcpcsDmeId(Long hcpcsDmeId) {
        this.setHcpcsDmeId(hcpcsDmeId);
        return this;
    }

    public void setHcpcsDmeId(Long hcpcsDmeId) {
        this.hcpcsDmeId = hcpcsDmeId;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public HcpcsDmeGroupMaster hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getDmeGroupName() {
        return this.dmeGroupName;
    }

    public HcpcsDmeGroupMaster dmeGroupName(String dmeGroupName) {
        this.setDmeGroupName(dmeGroupName);
        return this;
    }

    public void setDmeGroupName(String dmeGroupName) {
        this.dmeGroupName = dmeGroupName;
    }

    public Long getDmeGroupId() {
        return this.dmeGroupId;
    }

    public HcpcsDmeGroupMaster dmeGroupId(Long dmeGroupId) {
        this.setDmeGroupId(dmeGroupId);
        return this;
    }

    public void setDmeGroupId(Long dmeGroupId) {
        this.dmeGroupId = dmeGroupId;
    }

    public String getStatus() {
        return this.status;
    }

    public HcpcsDmeGroupMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public HcpcsDmeGroupMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public HcpcsDmeGroupMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public HcpcsDmeGroupMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public HcpcsDmeGroupMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public HcpcsDmeGroupMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public HcpcsDmeGroupMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getHcpcsDmeGroupMasterUuid() {
        return this.hcpcsDmeGroupMasterUuid;
    }

    public HcpcsDmeGroupMaster hcpcsDmeGroupMasterUuid(UUID hcpcsDmeGroupMasterUuid) {
        this.setHcpcsDmeGroupMasterUuid(hcpcsDmeGroupMasterUuid);
        return this;
    }

    public void setHcpcsDmeGroupMasterUuid(UUID hcpcsDmeGroupMasterUuid) {
        this.hcpcsDmeGroupMasterUuid = hcpcsDmeGroupMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HcpcsDmeGroupMaster)) {
            return false;
        }
        return hcpcsDmeId != null && hcpcsDmeId.equals(((HcpcsDmeGroupMaster) o).hcpcsDmeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HcpcsDmeGroupMaster{" +
            "hcpcsDmeId=" + getHcpcsDmeId() +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", dmeGroupName='" + getDmeGroupName() + "'" +
            ", dmeGroupId=" + getDmeGroupId() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", hcpcsDmeGroupMasterUuid='" + getHcpcsDmeGroupMasterUuid() + "'" +
            "}";
    }
}
