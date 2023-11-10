package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TaskComments.
 */
@Entity
@Table(name = "t_task_comments")
public class TaskComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "task_comment_id", nullable = false)
    private Long taskCommentId;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "comment_by_id")
    private Long commentById;

    @Column(name = "comment_date")
    private LocalDate commentDate;

    @Column(name = "comment_by_name")
    private String commentByName;

    @Column(name = "task_details_uuid")
    private UUID taskDetailsUuid;

    @Column(name = "task_details_id")
    private Long taskDetailsId;

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

    @Column(name = "task_comments_uuid")
    private UUID taskCommentsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTaskCommentId() {
        return this.taskCommentId;
    }

    public TaskComments taskCommentId(Long taskCommentId) {
        this.setTaskCommentId(taskCommentId);
        return this;
    }

    public void setTaskCommentId(Long taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public String getCommentText() {
        return this.commentText;
    }

    public TaskComments commentText(String commentText) {
        this.setCommentText(commentText);
        return this;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentById() {
        return this.commentById;
    }

    public TaskComments commentById(Long commentById) {
        this.setCommentById(commentById);
        return this;
    }

    public void setCommentById(Long commentById) {
        this.commentById = commentById;
    }

    public LocalDate getCommentDate() {
        return this.commentDate;
    }

    public TaskComments commentDate(LocalDate commentDate) {
        this.setCommentDate(commentDate);
        return this;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentByName() {
        return this.commentByName;
    }

    public TaskComments commentByName(String commentByName) {
        this.setCommentByName(commentByName);
        return this;
    }

    public void setCommentByName(String commentByName) {
        this.commentByName = commentByName;
    }

    public UUID getTaskDetailsUuid() {
        return this.taskDetailsUuid;
    }

    public TaskComments taskDetailsUuid(UUID taskDetailsUuid) {
        this.setTaskDetailsUuid(taskDetailsUuid);
        return this;
    }

    public void setTaskDetailsUuid(UUID taskDetailsUuid) {
        this.taskDetailsUuid = taskDetailsUuid;
    }

    public Long getTaskDetailsId() {
        return this.taskDetailsId;
    }

    public TaskComments taskDetailsId(Long taskDetailsId) {
        this.setTaskDetailsId(taskDetailsId);
        return this;
    }

    public void setTaskDetailsId(Long taskDetailsId) {
        this.taskDetailsId = taskDetailsId;
    }

    public String getStatus() {
        return this.status;
    }

    public TaskComments status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public TaskComments createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public TaskComments createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public TaskComments createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public TaskComments updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public TaskComments updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public TaskComments updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getTaskCommentsUuid() {
        return this.taskCommentsUuid;
    }

    public TaskComments taskCommentsUuid(UUID taskCommentsUuid) {
        this.setTaskCommentsUuid(taskCommentsUuid);
        return this;
    }

    public void setTaskCommentsUuid(UUID taskCommentsUuid) {
        this.taskCommentsUuid = taskCommentsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskComments)) {
            return false;
        }
        return taskCommentId != null && taskCommentId.equals(((TaskComments) o).taskCommentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskComments{" +
            "taskCommentId=" + getTaskCommentId() +
            ", commentText='" + getCommentText() + "'" +
            ", commentById=" + getCommentById() +
            ", commentDate='" + getCommentDate() + "'" +
            ", commentByName='" + getCommentByName() + "'" +
            ", taskDetailsUuid='" + getTaskDetailsUuid() + "'" +
            ", taskDetailsId=" + getTaskDetailsId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taskCommentsUuid='" + getTaskCommentsUuid() + "'" +
            "}";
    }
}
