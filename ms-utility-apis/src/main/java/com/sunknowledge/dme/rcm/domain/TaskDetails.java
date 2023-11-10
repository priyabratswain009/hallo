package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TaskDetails.
 */
@Entity
@Table(name = "t_task_details")
public class TaskDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "task_details_id", nullable = false)
    private Long taskDetailsId;

    @Column(name = "task_no")
    private String taskNo;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_reason")
    private String taskReason;

    @Column(name = "severity")
    private String severity;

    @Column(name = "subject_text")
    private String subjectText;

    @Column(name = "description_text")
    private String descriptionText;

    @Column(name = "assigned_to_id")
    private Long assignedToId;

    @Column(name = "assignment_date")
    private LocalDate assignmentDate;

    @Column(name = "date_needed")
    private LocalDate dateNeeded;

    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Column(name = "task_state")
    private String taskState;

    @Column(name = "deactive_status")
    private String deactiveStatus;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "object_instance_id_no")
    private String objectInstanceIdNo;

    @Column(name = "assign_to_name")
    private String assignToName;

    @Column(name = "object_instance_uuid")
    private UUID objectInstanceUuid;

    @Column(name = "wip_status_id")
    private Long wipStatusId;

    @Column(name = "wip_status_name")
    private String wipStatusName;

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

    @Column(name = "task_details_uuid")
    private UUID taskDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTaskDetailsId() {
        return this.taskDetailsId;
    }

    public TaskDetails taskDetailsId(Long taskDetailsId) {
        this.setTaskDetailsId(taskDetailsId);
        return this;
    }

    public void setTaskDetailsId(Long taskDetailsId) {
        this.taskDetailsId = taskDetailsId;
    }

    public String getTaskNo() {
        return this.taskNo;
    }

    public TaskDetails taskNo(String taskNo) {
        this.setTaskNo(taskNo);
        return this;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public TaskDetails taskId(Long taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public TaskDetails taskName(String taskName) {
        this.setTaskName(taskName);
        return this;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskReason() {
        return this.taskReason;
    }

    public TaskDetails taskReason(String taskReason) {
        this.setTaskReason(taskReason);
        return this;
    }

    public void setTaskReason(String taskReason) {
        this.taskReason = taskReason;
    }

    public String getSeverity() {
        return this.severity;
    }

    public TaskDetails severity(String severity) {
        this.setSeverity(severity);
        return this;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSubjectText() {
        return this.subjectText;
    }

    public TaskDetails subjectText(String subjectText) {
        this.setSubjectText(subjectText);
        return this;
    }

    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    public String getDescriptionText() {
        return this.descriptionText;
    }

    public TaskDetails descriptionText(String descriptionText) {
        this.setDescriptionText(descriptionText);
        return this;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Long getAssignedToId() {
        return this.assignedToId;
    }

    public TaskDetails assignedToId(Long assignedToId) {
        this.setAssignedToId(assignedToId);
        return this;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public LocalDate getAssignmentDate() {
        return this.assignmentDate;
    }

    public TaskDetails assignmentDate(LocalDate assignmentDate) {
        this.setAssignmentDate(assignmentDate);
        return this;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public LocalDate getDateNeeded() {
        return this.dateNeeded;
    }

    public TaskDetails dateNeeded(LocalDate dateNeeded) {
        this.setDateNeeded(dateNeeded);
        return this;
    }

    public void setDateNeeded(LocalDate dateNeeded) {
        this.dateNeeded = dateNeeded;
    }

    public LocalDate getDateCompleted() {
        return this.dateCompleted;
    }

    public TaskDetails dateCompleted(LocalDate dateCompleted) {
        this.setDateCompleted(dateCompleted);
        return this;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getTaskState() {
        return this.taskState;
    }

    public TaskDetails taskState(String taskState) {
        this.setTaskState(taskState);
        return this;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getDeactiveStatus() {
        return this.deactiveStatus;
    }

    public TaskDetails deactiveStatus(String deactiveStatus) {
        this.setDeactiveStatus(deactiveStatus);
        return this;
    }

    public void setDeactiveStatus(String deactiveStatus) {
        this.deactiveStatus = deactiveStatus;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public TaskDetails objectId(Long objectId) {
        this.setObjectId(objectId);
        return this;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public TaskDetails objectName(String objectName) {
        this.setObjectName(objectName);
        return this;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectInstanceIdNo() {
        return this.objectInstanceIdNo;
    }

    public TaskDetails objectInstanceIdNo(String objectInstanceIdNo) {
        this.setObjectInstanceIdNo(objectInstanceIdNo);
        return this;
    }

    public void setObjectInstanceIdNo(String objectInstanceIdNo) {
        this.objectInstanceIdNo = objectInstanceIdNo;
    }

    public String getAssignToName() {
        return this.assignToName;
    }

    public TaskDetails assignToName(String assignToName) {
        this.setAssignToName(assignToName);
        return this;
    }

    public void setAssignToName(String assignToName) {
        this.assignToName = assignToName;
    }

    public UUID getObjectInstanceUuid() {
        return this.objectInstanceUuid;
    }

    public TaskDetails objectInstanceUuid(UUID objectInstanceUuid) {
        this.setObjectInstanceUuid(objectInstanceUuid);
        return this;
    }

    public void setObjectInstanceUuid(UUID objectInstanceUuid) {
        this.objectInstanceUuid = objectInstanceUuid;
    }

    public Long getWipStatusId() {
        return this.wipStatusId;
    }

    public TaskDetails wipStatusId(Long wipStatusId) {
        this.setWipStatusId(wipStatusId);
        return this;
    }

    public void setWipStatusId(Long wipStatusId) {
        this.wipStatusId = wipStatusId;
    }

    public String getWipStatusName() {
        return this.wipStatusName;
    }

    public TaskDetails wipStatusName(String wipStatusName) {
        this.setWipStatusName(wipStatusName);
        return this;
    }

    public void setWipStatusName(String wipStatusName) {
        this.wipStatusName = wipStatusName;
    }

    public String getStatus() {
        return this.status;
    }

    public TaskDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public TaskDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public TaskDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public TaskDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public TaskDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public TaskDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public TaskDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getTaskDetailsUuid() {
        return this.taskDetailsUuid;
    }

    public TaskDetails taskDetailsUuid(UUID taskDetailsUuid) {
        this.setTaskDetailsUuid(taskDetailsUuid);
        return this;
    }

    public void setTaskDetailsUuid(UUID taskDetailsUuid) {
        this.taskDetailsUuid = taskDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskDetails)) {
            return false;
        }
        return taskDetailsId != null && taskDetailsId.equals(((TaskDetails) o).taskDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskDetails{" +
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
