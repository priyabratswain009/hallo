package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WipQueueOwner.
 */
@Entity
@Table(name = "t_wip_queue_owner")
public class WipQueueOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "wip_queue_owner_id", nullable = false)
    private Long wipQueueOwnerId;

    @Column(name = "wip_status_id")
    private String wipStatusId;

    @Column(name = "wip_status_name")
    private String wipStatusName;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "assigned_by_id")
    private Long assignedById;

    @Column(name = "assigned_by_name")
    private String assignedByName;

    @Column(name = "assigned_to_id")
    private Long assignedToId;

    @Column(name = "assigned_to_name")
    private String assignedToName;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

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

    @Column(name = "wip_queue_owner_uuid")
    private UUID wipQueueOwnerUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getWipQueueOwnerId() {
        return this.wipQueueOwnerId;
    }

    public WipQueueOwner wipQueueOwnerId(Long wipQueueOwnerId) {
        this.setWipQueueOwnerId(wipQueueOwnerId);
        return this;
    }

    public void setWipQueueOwnerId(Long wipQueueOwnerId) {
        this.wipQueueOwnerId = wipQueueOwnerId;
    }

    public String getWipStatusId() {
        return this.wipStatusId;
    }

    public WipQueueOwner wipStatusId(String wipStatusId) {
        this.setWipStatusId(wipStatusId);
        return this;
    }

    public void setWipStatusId(String wipStatusId) {
        this.wipStatusId = wipStatusId;
    }

    public String getWipStatusName() {
        return this.wipStatusName;
    }

    public WipQueueOwner wipStatusName(String wipStatusName) {
        this.setWipStatusName(wipStatusName);
        return this;
    }

    public void setWipStatusName(String wipStatusName) {
        this.wipStatusName = wipStatusName;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public WipQueueOwner taskId(Long taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public WipQueueOwner taskName(String taskName) {
        this.setTaskName(taskName);
        return this;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getAssignedById() {
        return this.assignedById;
    }

    public WipQueueOwner assignedById(Long assignedById) {
        this.setAssignedById(assignedById);
        return this;
    }

    public void setAssignedById(Long assignedById) {
        this.assignedById = assignedById;
    }

    public String getAssignedByName() {
        return this.assignedByName;
    }

    public WipQueueOwner assignedByName(String assignedByName) {
        this.setAssignedByName(assignedByName);
        return this;
    }

    public void setAssignedByName(String assignedByName) {
        this.assignedByName = assignedByName;
    }

    public Long getAssignedToId() {
        return this.assignedToId;
    }

    public WipQueueOwner assignedToId(Long assignedToId) {
        this.setAssignedToId(assignedToId);
        return this;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public String getAssignedToName() {
        return this.assignedToName;
    }

    public WipQueueOwner assignedToName(String assignedToName) {
        this.setAssignedToName(assignedToName);
        return this;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public LocalDate getAssignedDate() {
        return this.assignedDate;
    }

    public WipQueueOwner assignedDate(LocalDate assignedDate) {
        this.setAssignedDate(assignedDate);
        return this;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public WipQueueOwner status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public WipQueueOwner createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public WipQueueOwner createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public WipQueueOwner createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public WipQueueOwner updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public WipQueueOwner updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public WipQueueOwner updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getWipQueueOwnerUuid() {
        return this.wipQueueOwnerUuid;
    }

    public WipQueueOwner wipQueueOwnerUuid(UUID wipQueueOwnerUuid) {
        this.setWipQueueOwnerUuid(wipQueueOwnerUuid);
        return this;
    }

    public void setWipQueueOwnerUuid(UUID wipQueueOwnerUuid) {
        this.wipQueueOwnerUuid = wipQueueOwnerUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WipQueueOwner)) {
            return false;
        }
        return wipQueueOwnerId != null && wipQueueOwnerId.equals(((WipQueueOwner) o).wipQueueOwnerId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WipQueueOwner{" +
            "wipQueueOwnerId=" + getWipQueueOwnerId() +
            ", wipStatusId='" + getWipStatusId() + "'" +
            ", wipStatusName='" + getWipStatusName() + "'" +
            ", taskId=" + getTaskId() +
            ", taskName='" + getTaskName() + "'" +
            ", assignedById=" + getAssignedById() +
            ", assignedByName='" + getAssignedByName() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", assignedToName='" + getAssignedToName() + "'" +
            ", assignedDate='" + getAssignedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", wipQueueOwnerUuid='" + getWipQueueOwnerUuid() + "'" +
            "}";
    }
}
