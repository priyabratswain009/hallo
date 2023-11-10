package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TaskTypeMaster.
 */
@Entity
@Table(name = "t_task_type_master")
public class TaskTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_name")
    private String objectName;

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

    @Column(name = "task_type_master_uuid")
    private UUID taskTypeMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTaskId() {
        return this.taskId;
    }

    public TaskTypeMaster taskId(Long taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public TaskTypeMaster taskName(String taskName) {
        this.setTaskName(taskName);
        return this;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public TaskTypeMaster objectId(Long objectId) {
        this.setObjectId(objectId);
        return this;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public TaskTypeMaster objectName(String objectName) {
        this.setObjectName(objectName);
        return this;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getStatus() {
        return this.status;
    }

    public TaskTypeMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public TaskTypeMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public TaskTypeMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public TaskTypeMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public TaskTypeMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public TaskTypeMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public TaskTypeMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getTaskTypeMasterUuid() {
        return this.taskTypeMasterUuid;
    }

    public TaskTypeMaster taskTypeMasterUuid(UUID taskTypeMasterUuid) {
        this.setTaskTypeMasterUuid(taskTypeMasterUuid);
        return this;
    }

    public void setTaskTypeMasterUuid(UUID taskTypeMasterUuid) {
        this.taskTypeMasterUuid = taskTypeMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskTypeMaster)) {
            return false;
        }
        return taskId != null && taskId.equals(((TaskTypeMaster) o).taskId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskTypeMaster{" +
            "taskId=" + getTaskId() +
            ", taskName='" + getTaskName() + "'" +
            ", objectId=" + getObjectId() +
            ", objectName='" + getObjectName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taskTypeMasterUuid='" + getTaskTypeMasterUuid() + "'" +
            "}";
    }
}
