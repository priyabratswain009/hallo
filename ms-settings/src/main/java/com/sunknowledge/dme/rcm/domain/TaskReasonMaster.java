package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaskReasonMaster.
 */
@Entity
@Table(name = "t_task_reason_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaskReasonMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "task_reason_id")
    private Long taskReasonId;

    @Column(name = "task_reason_name")
    private String taskReasonName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "task_reason_master_uuid")
    private UUID taskReasonMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTaskReasonId() {
        return this.taskReasonId;
    }

    public TaskReasonMaster taskReasonId(Long taskReasonId) {
        this.setTaskReasonId(taskReasonId);
        return this;
    }

    public void setTaskReasonId(Long taskReasonId) {
        this.taskReasonId = taskReasonId;
    }

    public String getTaskReasonName() {
        return this.taskReasonName;
    }

    public TaskReasonMaster taskReasonName(String taskReasonName) {
        this.setTaskReasonName(taskReasonName);
        return this;
    }

    public void setTaskReasonName(String taskReasonName) {
        this.taskReasonName = taskReasonName;
    }

    public String getStatus() {
        return this.status;
    }

    public TaskReasonMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public TaskReasonMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public TaskReasonMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public TaskReasonMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public TaskReasonMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public TaskReasonMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public TaskReasonMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getTaskReasonMasterUuid() {
        return this.taskReasonMasterUuid;
    }

    public TaskReasonMaster taskReasonMasterUuid(UUID taskReasonMasterUuid) {
        this.setTaskReasonMasterUuid(taskReasonMasterUuid);
        return this;
    }

    public void setTaskReasonMasterUuid(UUID taskReasonMasterUuid) {
        this.taskReasonMasterUuid = taskReasonMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskReasonMaster)) {
            return false;
        }
        return taskReasonId != null && taskReasonId.equals(((TaskReasonMaster) o).taskReasonId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskReasonMaster{" +
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
