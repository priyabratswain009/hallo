package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DmeGroupChecklistMaster.
 */
@Table("t_dme_group_checklist_master")
public class DmeGroupChecklistMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("dme_group_checklist_id")
    private Long dmeGroupChecklistId;

    @Column("dme_group_id")
    private Long dmeGroupId;

    @Column("dme_group_name")
    private String dmeGroupName;

    @Column("checklist_id")
    private Long checklistId;

    @Column("checklist_name")
    private String checklistName;

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

    @Column("dme_group_checklist_master_uuid")
    private UUID dmeGroupChecklistMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDmeGroupChecklistId() {
        return this.dmeGroupChecklistId;
    }

    public DmeGroupChecklistMaster dmeGroupChecklistId(Long dmeGroupChecklistId) {
        this.setDmeGroupChecklistId(dmeGroupChecklistId);
        return this;
    }

    public void setDmeGroupChecklistId(Long dmeGroupChecklistId) {
        this.dmeGroupChecklistId = dmeGroupChecklistId;
    }

    public Long getDmeGroupId() {
        return this.dmeGroupId;
    }

    public DmeGroupChecklistMaster dmeGroupId(Long dmeGroupId) {
        this.setDmeGroupId(dmeGroupId);
        return this;
    }

    public void setDmeGroupId(Long dmeGroupId) {
        this.dmeGroupId = dmeGroupId;
    }

    public String getDmeGroupName() {
        return this.dmeGroupName;
    }

    public DmeGroupChecklistMaster dmeGroupName(String dmeGroupName) {
        this.setDmeGroupName(dmeGroupName);
        return this;
    }

    public void setDmeGroupName(String dmeGroupName) {
        this.dmeGroupName = dmeGroupName;
    }

    public Long getChecklistId() {
        return this.checklistId;
    }

    public DmeGroupChecklistMaster checklistId(Long checklistId) {
        this.setChecklistId(checklistId);
        return this;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return this.checklistName;
    }

    public DmeGroupChecklistMaster checklistName(String checklistName) {
        this.setChecklistName(checklistName);
        return this;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public String getStatus() {
        return this.status;
    }

    public DmeGroupChecklistMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DmeGroupChecklistMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DmeGroupChecklistMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DmeGroupChecklistMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DmeGroupChecklistMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DmeGroupChecklistMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DmeGroupChecklistMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getDmeGroupChecklistMasterUuid() {
        return this.dmeGroupChecklistMasterUuid;
    }

    public DmeGroupChecklistMaster dmeGroupChecklistMasterUuid(UUID dmeGroupChecklistMasterUuid) {
        this.setDmeGroupChecklistMasterUuid(dmeGroupChecklistMasterUuid);
        return this;
    }

    public void setDmeGroupChecklistMasterUuid(UUID dmeGroupChecklistMasterUuid) {
        this.dmeGroupChecklistMasterUuid = dmeGroupChecklistMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmeGroupChecklistMaster)) {
            return false;
        }
        return dmeGroupChecklistId != null && dmeGroupChecklistId.equals(((DmeGroupChecklistMaster) o).dmeGroupChecklistId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmeGroupChecklistMaster{" +
            "dmeGroupChecklistId=" + getDmeGroupChecklistId() +
            ", dmeGroupId=" + getDmeGroupId() +
            ", dmeGroupName='" + getDmeGroupName() + "'" +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", dmeGroupChecklistMasterUuid='" + getDmeGroupChecklistMasterUuid() + "'" +
            "}";
    }
}
