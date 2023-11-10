package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.WipQueueDetails} entity.
 */
public class WipQueueDetailsDTO implements Serializable {

    @NotNull
    private Long wipQueueDetailsId;

    private String wipStatusId;

    private String wipStatusName;

    private Long taskId;

    private String taskName;

    private Long objectId;

    private String objectName;

    private Long objectInstanceId;

    private Long wipSetById;

    private String wipSetByName;

    private LocalDate wipSetDateTime;

    private Long assignedById;

    private String assignedByName;

    private Long assignedToId;

    private String assignedToName;

    private LocalDate assignedDate;

    private String assignedStatus;

    private String wipNotes;

    private String assignmentNotes;

    private String assignmentStatusNotes;

    private String objectInstanceIdNo;

    private String state;

    private UUID objectInstanceIdUuid;

    private LocalDate targetedDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID wipQueueDetailsUuid;

    public Long getWipQueueDetailsId() {
        return wipQueueDetailsId;
    }

    public void setWipQueueDetailsId(Long wipQueueDetailsId) {
        this.wipQueueDetailsId = wipQueueDetailsId;
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

    public Long getObjectInstanceId() {
        return objectInstanceId;
    }

    public void setObjectInstanceId(Long objectInstanceId) {
        this.objectInstanceId = objectInstanceId;
    }

    public Long getWipSetById() {
        return wipSetById;
    }

    public void setWipSetById(Long wipSetById) {
        this.wipSetById = wipSetById;
    }

    public String getWipSetByName() {
        return wipSetByName;
    }

    public void setWipSetByName(String wipSetByName) {
        this.wipSetByName = wipSetByName;
    }

    public LocalDate getWipSetDateTime() {
        return wipSetDateTime;
    }

    public void setWipSetDateTime(LocalDate wipSetDateTime) {
        this.wipSetDateTime = wipSetDateTime;
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

    public String getAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(String assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public String getWipNotes() {
        return wipNotes;
    }

    public void setWipNotes(String wipNotes) {
        this.wipNotes = wipNotes;
    }

    public String getAssignmentNotes() {
        return assignmentNotes;
    }

    public void setAssignmentNotes(String assignmentNotes) {
        this.assignmentNotes = assignmentNotes;
    }

    public String getAssignmentStatusNotes() {
        return assignmentStatusNotes;
    }

    public void setAssignmentStatusNotes(String assignmentStatusNotes) {
        this.assignmentStatusNotes = assignmentStatusNotes;
    }

    public String getObjectInstanceIdNo() {
        return objectInstanceIdNo;
    }

    public void setObjectInstanceIdNo(String objectInstanceIdNo) {
        this.objectInstanceIdNo = objectInstanceIdNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UUID getObjectInstanceIdUuid() {
        return objectInstanceIdUuid;
    }

    public void setObjectInstanceIdUuid(UUID objectInstanceIdUuid) {
        this.objectInstanceIdUuid = objectInstanceIdUuid;
    }

    public LocalDate getTargetedDate() {
        return targetedDate;
    }

    public void setTargetedDate(LocalDate targetedDate) {
        this.targetedDate = targetedDate;
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

    public UUID getWipQueueDetailsUuid() {
        return wipQueueDetailsUuid;
    }

    public void setWipQueueDetailsUuid(UUID wipQueueDetailsUuid) {
        this.wipQueueDetailsUuid = wipQueueDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WipQueueDetailsDTO)) {
            return false;
        }

        WipQueueDetailsDTO wipQueueDetailsDTO = (WipQueueDetailsDTO) o;
        if (this.wipQueueDetailsId == null) {
            return false;
        }
        return Objects.equals(this.wipQueueDetailsId, wipQueueDetailsDTO.wipQueueDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.wipQueueDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WipQueueDetailsDTO{" +
            "wipQueueDetailsId=" + getWipQueueDetailsId() +
            ", wipStatusId='" + getWipStatusId() + "'" +
            ", wipStatusName='" + getWipStatusName() + "'" +
            ", taskId=" + getTaskId() +
            ", taskName='" + getTaskName() + "'" +
            ", objectId=" + getObjectId() +
            ", objectName='" + getObjectName() + "'" +
            ", objectInstanceId=" + getObjectInstanceId() +
            ", wipSetById=" + getWipSetById() +
            ", wipSetByName='" + getWipSetByName() + "'" +
            ", wipSetDateTime='" + getWipSetDateTime() + "'" +
            ", assignedById=" + getAssignedById() +
            ", assignedByName='" + getAssignedByName() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", assignedToName='" + getAssignedToName() + "'" +
            ", assignedDate='" + getAssignedDate() + "'" +
            ", assignedStatus='" + getAssignedStatus() + "'" +
            ", wipNotes='" + getWipNotes() + "'" +
            ", assignmentNotes='" + getAssignmentNotes() + "'" +
            ", assignmentStatusNotes='" + getAssignmentStatusNotes() + "'" +
            ", objectInstanceIdNo='" + getObjectInstanceIdNo() + "'" +
            ", state='" + getState() + "'" +
            ", objectInstanceIdUuid='" + getObjectInstanceIdUuid() + "'" +
            ", targetedDate='" + getTargetedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", wipQueueDetailsUuid='" + getWipQueueDetailsUuid() + "'" +
            "}";
    }
}
