package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.TaskComments} entity.
 */
public class TaskCommentsDTO implements Serializable {

    @NotNull
    private Long taskCommentId;

    private String commentText;

    private Long commentById;

    private LocalDate commentDate;

    private String commentByName;

    private UUID taskDetailsUuid;

    private Long taskDetailsId;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID taskCommentsUuid;

    public Long getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Long taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentById() {
        return commentById;
    }

    public void setCommentById(Long commentById) {
        this.commentById = commentById;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentByName() {
        return commentByName;
    }

    public void setCommentByName(String commentByName) {
        this.commentByName = commentByName;
    }

    public UUID getTaskDetailsUuid() {
        return taskDetailsUuid;
    }

    public void setTaskDetailsUuid(UUID taskDetailsUuid) {
        this.taskDetailsUuid = taskDetailsUuid;
    }

    public Long getTaskDetailsId() {
        return taskDetailsId;
    }

    public void setTaskDetailsId(Long taskDetailsId) {
        this.taskDetailsId = taskDetailsId;
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

    public UUID getTaskCommentsUuid() {
        return taskCommentsUuid;
    }

    public void setTaskCommentsUuid(UUID taskCommentsUuid) {
        this.taskCommentsUuid = taskCommentsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskCommentsDTO)) {
            return false;
        }

        TaskCommentsDTO taskCommentsDTO = (TaskCommentsDTO) o;
        if (this.taskCommentId == null) {
            return false;
        }
        return Objects.equals(this.taskCommentId, taskCommentsDTO.taskCommentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taskCommentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskCommentsDTO{" +
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
