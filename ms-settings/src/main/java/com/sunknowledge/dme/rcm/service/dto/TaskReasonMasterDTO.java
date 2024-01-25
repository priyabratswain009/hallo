package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.TaskReasonMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaskReasonMasterDTO implements Serializable {

    private Long taskReasonId;

    private String taskReasonName;

    private String status;

    private Long createdById;

    private Long updatedById;

    private LocalDate updatedDate;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private UUID taskReasonMasterUuid;

    public Long getTaskReasonId() {
        return taskReasonId;
    }

    public void setTaskReasonId(Long taskReasonId) {
        this.taskReasonId = taskReasonId;
    }

    public String getTaskReasonName() {
        return taskReasonName;
    }

    public void setTaskReasonName(String taskReasonName) {
        this.taskReasonName = taskReasonName;
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

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getTaskReasonMasterUuid() {
        return taskReasonMasterUuid;
    }

    public void setTaskReasonMasterUuid(UUID taskReasonMasterUuid) {
        this.taskReasonMasterUuid = taskReasonMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskReasonMasterDTO)) {
            return false;
        }

        TaskReasonMasterDTO taskReasonMasterDTO = (TaskReasonMasterDTO) o;
        if (this.taskReasonId == null) {
            return false;
        }
        return Objects.equals(this.taskReasonId, taskReasonMasterDTO.taskReasonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taskReasonId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskReasonMasterDTO{" +
            "taskReasonId=" + getTaskReasonId() +
            ", taskReasonName='" + getTaskReasonName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", taskReasonMasterUuid='" + getTaskReasonMasterUuid() + "'" +
            "}";
    }
}
