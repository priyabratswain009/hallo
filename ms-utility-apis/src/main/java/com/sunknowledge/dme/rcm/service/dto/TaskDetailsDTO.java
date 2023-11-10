package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.TaskDetails} entity.
 */
public class TaskDetailsDTO implements Serializable {

    @NotNull
    private Long taskDetailsId;

    private String taskNo;

    private Long taskId;

    private String taskName;

    private String taskReason;

    private String severity;

    private String subjectText;

    private String descriptionText;

    private Long assignedToId;

    private LocalDate assignmentDate;

    private LocalDate dateNeeded;

    private LocalDate dateCompleted;

    private String taskState;

    private String deactiveStatus;

    private Long objectId;

    private String objectName;

    private String objectInstanceIdNo;

    private String assignToName;

    private UUID objectInstanceUuid;

    private Long wipStatusId;

    private String wipStatusName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID taskDetailsUuid;

    public Long getTaskDetailsId() {
        return taskDetailsId;
    }

    public void setTaskDetailsId(Long taskDetailsId) {
        this.taskDetailsId = taskDetailsId;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
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

    public String getTaskReason() {
        return taskReason;
    }

    public void setTaskReason(String taskReason) {
        this.taskReason = taskReason;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSubjectText() {
        return subjectText;
    }

    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public LocalDate getDateNeeded() {
        return dateNeeded;
    }

    public void setDateNeeded(LocalDate dateNeeded) {
        this.dateNeeded = dateNeeded;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getDeactiveStatus() {
        return deactiveStatus;
    }

    public void setDeactiveStatus(String deactiveStatus) {
        this.deactiveStatus = deactiveStatus;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectInstanceIdNo() {
        return objectInstanceIdNo;
    }

    public void setObjectInstanceIdNo(String objectInstanceIdNo) {
        this.objectInstanceIdNo = objectInstanceIdNo;
    }

    public String getAssignToName() {
        return assignToName;
    }

    public void setAssignToName(String assignToName) {
        this.assignToName = assignToName;
    }

    public UUID getObjectInstanceUuid() {
        return objectInstanceUuid;
    }

    public void setObjectInstanceUuid(UUID objectInstanceUuid) {
        this.objectInstanceUuid = objectInstanceUuid;
    }

    public Long getWipStatusId() {
        return wipStatusId;
    }

    public void setWipStatusId(Long wipStatusId) {
        this.wipStatusId = wipStatusId;
    }

    public String getWipStatusName() {
        return wipStatusName;
    }

    public void setWipStatusName(String wipStatusName) {
        this.wipStatusName = wipStatusName;
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

    public UUID getTaskDetailsUuid() {
        return taskDetailsUuid;
    }

    public void setTaskDetailsUuid(UUID taskDetailsUuid) {
        this.taskDetailsUuid = taskDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskDetailsDTO)) {
            return false;
        }

        TaskDetailsDTO taskDetailsDTO = (TaskDetailsDTO) o;
        if (this.taskDetailsId == null) {
            return false;
        }
        return Objects.equals(this.taskDetailsId, taskDetailsDTO.taskDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taskDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskDetailsDTO{" +
            "taskDetailsId=" + getTaskDetailsId() +
            ", taskNo='" + getTaskNo() + "'" +
            ", taskId=" + getTaskId() +
            ", taskName='" + getTaskName() + "'" +
            ", taskReason='" + getTaskReason() + "'" +
            ", severity='" + getSeverity() + "'" +
            ", subjectText='" + getSubjectText() + "'" +
            ", descriptionText='" + getDescriptionText() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", assignmentDate='" + getAssignmentDate() + "'" +
            ", dateNeeded='" + getDateNeeded() + "'" +
            ", dateCompleted='" + getDateCompleted() + "'" +
            ", taskState='" + getTaskState() + "'" +
            ", deactiveStatus='" + getDeactiveStatus() + "'" +
            ", objectId=" + getObjectId() +
            ", objectName='" + getObjectName() + "'" +
            ", objectInstanceIdNo='" + getObjectInstanceIdNo() + "'" +
            ", assignToName='" + getAssignToName() + "'" +
            ", objectInstanceUuid='" + getObjectInstanceUuid() + "'" +
            ", wipStatusId=" + getWipStatusId() +
            ", wipStatusName='" + getWipStatusName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taskDetailsUuid='" + getTaskDetailsUuid() + "'" +
            "}";
    }
}
