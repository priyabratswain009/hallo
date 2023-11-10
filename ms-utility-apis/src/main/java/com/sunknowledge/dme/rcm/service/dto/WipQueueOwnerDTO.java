package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.WipQueueOwner} entity.
 */
public class WipQueueOwnerDTO implements Serializable {

    @NotNull
    private Long wipQueueOwnerId;

    private String wipStatusId;

    private String wipStatusName;

    private Long taskId;

    private String taskName;

    private Long assignedById;

    private String assignedByName;

    private Long assignedToId;

    private String assignedToName;

    private LocalDate assignedDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID wipQueueOwnerUuid;

    public Long getWipQueueOwnerId() {
        return wipQueueOwnerId;
    }

    public void setWipQueueOwnerId(Long wipQueueOwnerId) {
        this.wipQueueOwnerId = wipQueueOwnerId;
    }

    public String getWipStatusId() {
        return wipStatusId;
    }

    public void setWipStatusId(String wipStatusId) {
        this.wipStatusId = wipStatusId;
    }

    public String getWipStatusName() {
        return wipStatusName;
    }

    public void setWipStatusName(String wipStatusName) {
        this.wipStatusName = wipStatusName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getAssignedById() {
        return assignedById;
    }

    public void setAssignedById(Long assignedById) {
        this.assignedById = assignedById;
    }

    public String getAssignedByName() {
        return assignedByName;
    }

    public void setAssignedByName(String assignedByName) {
        this.assignedByName = assignedByName;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public String getAssignedToName() {
        return assignedToName;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getWipQueueOwnerUuid() {
        return wipQueueOwnerUuid;
    }

    public void setWipQueueOwnerUuid(UUID wipQueueOwnerUuid) {
        this.wipQueueOwnerUuid = wipQueueOwnerUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WipQueueOwnerDTO)) {
            return false;
        }

        WipQueueOwnerDTO wipQueueOwnerDTO = (WipQueueOwnerDTO) o;
        if (this.wipQueueOwnerId == null) {
            return false;
        }
        return Objects.equals(this.wipQueueOwnerId, wipQueueOwnerDTO.wipQueueOwnerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.wipQueueOwnerId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WipQueueOwnerDTO{" +
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
