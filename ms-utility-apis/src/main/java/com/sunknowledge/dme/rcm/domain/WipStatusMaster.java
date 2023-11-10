package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WipStatusMaster.
 */
@Entity
@Table(name = "t_wip_status_master")
public class WipStatusMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "wip_status_id", nullable = false)
    private Long wipStatusId;

    @Column(name = "wip_status_name")
    private String wipStatusName;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

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

    @Column(name = "wip_status_master_uuid")
    private UUID wipStatusMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getWipStatusId() {
        return this.wipStatusId;
    }

    public WipStatusMaster wipStatusId(Long wipStatusId) {
        this.setWipStatusId(wipStatusId);
        return this;
    }

    public void setWipStatusId(Long wipStatusId) {
        this.wipStatusId = wipStatusId;
    }

    public String getWipStatusName() {
        return this.wipStatusName;
    }

    public WipStatusMaster wipStatusName(String wipStatusName) {
        this.setWipStatusName(wipStatusName);
        return this;
    }

    public void setWipStatusName(String wipStatusName) {
        this.wipStatusName = wipStatusName;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public WipStatusMaster taskId(Long taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public WipStatusMaster taskName(String taskName) {
        this.setTaskName(taskName);
        return this;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return this.status;
    }

    public WipStatusMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public WipStatusMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public WipStatusMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public WipStatusMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public WipStatusMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public WipStatusMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public WipStatusMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getWipStatusMasterUuid() {
        return this.wipStatusMasterUuid;
    }

    public WipStatusMaster wipStatusMasterUuid(UUID wipStatusMasterUuid) {
        this.setWipStatusMasterUuid(wipStatusMasterUuid);
        return this;
    }

    public void setWipStatusMasterUuid(UUID wipStatusMasterUuid) {
        this.wipStatusMasterUuid = wipStatusMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WipStatusMaster)) {
            return false;
        }
        return wipStatusId != null && wipStatusId.equals(((WipStatusMaster) o).wipStatusId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WipStatusMaster{" +
            "wipStatusId=" + getWipStatusId() +
            ", wipStatusName='" + getWipStatusName() + "'" +
            ", taskId=" + getTaskId() +
            ", taskName='" + getTaskName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", wipStatusMasterUuid='" + getWipStatusMasterUuid() + "'" +
            "}";
    }
}
