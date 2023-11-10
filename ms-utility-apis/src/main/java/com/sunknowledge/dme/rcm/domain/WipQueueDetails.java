package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A WipQueueDetails.
 */
@Entity
@Table(name = "t_wip_queue_details")
public class WipQueueDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "wip_queue_details_id", nullable = false)
    private Long wipQueueDetailsId;

    @Column(name = "wip_status_id")
    private String wipStatusId;

    @Column(name = "wip_status_name")
    private String wipStatusName;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "object_instance_id")
    private Long objectInstanceId;

    @Column(name = "wip_set_by_id")
    private Long wipSetById;

    @Column(name = "wip_set_by_name")
    private String wipSetByName;

    @Column(name = "wip_set_date_time")
    private LocalDate wipSetDateTime;

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

    @Column(name = "assigned_status")
    private String assignedStatus;

    @Column(name = "wip_notes")
    private String wipNotes;

    @Column(name = "assignment_notes")
    private String assignmentNotes;

    @Column(name = "assignment_status_notes")
    private String assignmentStatusNotes;

    @Column(name = "object_instance_id_no")
    private String objectInstanceIdNo;

    @Column(name = "state")
    private String state;

    @Column(name = "object_instance_id_uuid")
    private UUID objectInstanceIdUuid;

    @Column(name = "targeted_date")
    private LocalDate targetedDate;

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

    @Column(name = "wip_queue_details_uuid")
    private UUID wipQueueDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getWipQueueDetailsId() {
        return this.wipQueueDetailsId;
    }

    public WipQueueDetails wipQueueDetailsId(Long wipQueueDetailsId) {
        this.setWipQueueDetailsId(wipQueueDetailsId);
        return this;
    }

    public void setWipQueueDetailsId(Long wipQueueDetailsId) {
        this.wipQueueDetailsId = wipQueueDetailsId;
    }

    public String getWipStatusId() {
        return this.wipStatusId;
    }

    public WipQueueDetails wipStatusId(String wipStatusId) {
        this.setWipStatusId(wipStatusId);
        return this;
    }

    public void setWipStatusId(String wipStatusId) {
        this.wipStatusId = wipStatusId;
    }

    public String getWipStatusName() {
        return this.wipStatusName;
    }

    public WipQueueDetails wipStatusName(String wipStatusName) {
        this.setWipStatusName(wipStatusName);
        return this;
    }

    public void setWipStatusName(String wipStatusName) {
        this.wipStatusName = wipStatusName;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public WipQueueDetails taskId(Long taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public WipQueueDetails taskName(String taskName) {
        this.setTaskName(taskName);
        return this;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public WipQueueDetails objectId(Long objectId) {
        this.setObjectId(objectId);
        return this;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public WipQueueDetails objectName(String objectName) {
        this.setObjectName(objectName);
        return this;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Long getObjectInstanceId() {
        return this.objectInstanceId;
    }

    public WipQueueDetails objectInstanceId(Long objectInstanceId) {
        this.setObjectInstanceId(objectInstanceId);
        return this;
    }

    public void setObjectInstanceId(Long objectInstanceId) {
        this.objectInstanceId = objectInstanceId;
    }

    public Long getWipSetById() {
        return this.wipSetById;
    }

    public WipQueueDetails wipSetById(Long wipSetById) {
        this.setWipSetById(wipSetById);
        return this;
    }

    public void setWipSetById(Long wipSetById) {
        this.wipSetById = wipSetById;
    }

    public String getWipSetByName() {
        return this.wipSetByName;
    }

    public WipQueueDetails wipSetByName(String wipSetByName) {
        this.setWipSetByName(wipSetByName);
        return this;
    }

    public void setWipSetByName(String wipSetByName) {
        this.wipSetByName = wipSetByName;
    }

    public LocalDate getWipSetDateTime() {
        return this.wipSetDateTime;
    }

    public WipQueueDetails wipSetDateTime(LocalDate wipSetDateTime) {
        this.setWipSetDateTime(wipSetDateTime);
        return this;
    }

    public void setWipSetDateTime(LocalDate wipSetDateTime) {
        this.wipSetDateTime = wipSetDateTime;
    }

    public Long getAssignedById() {
        return this.assignedById;
    }

    public WipQueueDetails assignedById(Long assignedById) {
        this.setAssignedById(assignedById);
        return this;
    }

    public void setAssignedById(Long assignedById) {
        this.assignedById = assignedById;
    }

    public String getAssignedByName() {
        return this.assignedByName;
    }

    public WipQueueDetails assignedByName(String assignedByName) {
        this.setAssignedByName(assignedByName);
        return this;
    }

    public void setAssignedByName(String assignedByName) {
        this.assignedByName = assignedByName;
    }

    public Long getAssignedToId() {
        return this.assignedToId;
    }

    public WipQueueDetails assignedToId(Long assignedToId) {
        this.setAssignedToId(assignedToId);
        return this;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public String getAssignedToName() {
        return this.assignedToName;
    }

    public WipQueueDetails assignedToName(String assignedToName) {
        this.setAssignedToName(assignedToName);
        return this;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public LocalDate getAssignedDate() {
        return this.assignedDate;
    }

    public WipQueueDetails assignedDate(LocalDate assignedDate) {
        this.setAssignedDate(assignedDate);
        return this;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getAssignedStatus() {
        return this.assignedStatus;
    }

    public WipQueueDetails assignedStatus(String assignedStatus) {
        this.setAssignedStatus(assignedStatus);
        return this;
    }

    public void setAssignedStatus(String assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public String getWipNotes() {
        return this.wipNotes;
    }

    public WipQueueDetails wipNotes(String wipNotes) {
        this.setWipNotes(wipNotes);
        return this;
    }

    public void setWipNotes(String wipNotes) {
        this.wipNotes = wipNotes;
    }

    public String getAssignmentNotes() {
        return this.assignmentNotes;
    }

    public WipQueueDetails assignmentNotes(String assignmentNotes) {
        this.setAssignmentNotes(assignmentNotes);
        return this;
    }

    public void setAssignmentNotes(String assignmentNotes) {
        this.assignmentNotes = assignmentNotes;
    }

    public String getAssignmentStatusNotes() {
        return this.assignmentStatusNotes;
    }

    public WipQueueDetails assignmentStatusNotes(String assignmentStatusNotes) {
        this.setAssignmentStatusNotes(assignmentStatusNotes);
        return this;
    }

    public void setAssignmentStatusNotes(String assignmentStatusNotes) {
        this.assignmentStatusNotes = assignmentStatusNotes;
    }

    public String getObjectInstanceIdNo() {
        return this.objectInstanceIdNo;
    }

    public WipQueueDetails objectInstanceIdNo(String objectInstanceIdNo) {
        this.setObjectInstanceIdNo(objectInstanceIdNo);
        return this;
    }

    public void setObjectInstanceIdNo(String objectInstanceIdNo) {
        this.objectInstanceIdNo = objectInstanceIdNo;
    }

    public String getState() {
        return this.state;
    }

    public WipQueueDetails state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UUID getObjectInstanceIdUuid() {
        return this.objectInstanceIdUuid;
    }

    public WipQueueDetails objectInstanceIdUuid(UUID objectInstanceIdUuid) {
        this.setObjectInstanceIdUuid(objectInstanceIdUuid);
        return this;
    }

    public void setObjectInstanceIdUuid(UUID objectInstanceIdUuid) {
        this.objectInstanceIdUuid = objectInstanceIdUuid;
    }

    public LocalDate getTargetedDate() {
        return this.targetedDate;
    }

    public WipQueueDetails targetedDate(LocalDate targetedDate) {
        this.setTargetedDate(targetedDate);
        return this;
    }

    public void setTargetedDate(LocalDate targetedDate) {
        this.targetedDate = targetedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public WipQueueDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public WipQueueDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public WipQueueDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public WipQueueDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public WipQueueDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public WipQueueDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public WipQueueDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getWipQueueDetailsUuid() {
        return this.wipQueueDetailsUuid;
    }

    public WipQueueDetails wipQueueDetailsUuid(UUID wipQueueDetailsUuid) {
        this.setWipQueueDetailsUuid(wipQueueDetailsUuid);
        return this;
    }

    public void setWipQueueDetailsUuid(UUID wipQueueDetailsUuid) {
        this.wipQueueDetailsUuid = wipQueueDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WipQueueDetails)) {
            return false;
        }
        return wipQueueDetailsId != null && wipQueueDetailsId.equals(((WipQueueDetails) o).wipQueueDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WipQueueDetails{" +
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
