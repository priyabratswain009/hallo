package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.WipStatusMaster} entity.
 */
public class WipStatusMasterDTO implements Serializable {

    @NotNull
    private Long wipStatusId;

    private String wipStatusName;

    private Long taskId;

    private String taskName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID wipStatusMasterUuid;

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

    public UUID getWipStatusMasterUuid() {
        return wipStatusMasterUuid;
    }

    public void setWipStatusMasterUuid(UUID wipStatusMasterUuid) {
        this.wipStatusMasterUuid = wipStatusMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WipStatusMasterDTO)) {
            return false;
        }

        WipStatusMasterDTO wipStatusMasterDTO = (WipStatusMasterDTO) o;
        if (this.wipStatusId == null) {
            return false;
        }
        return Objects.equals(this.wipStatusId, wipStatusMasterDTO.wipStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.wipStatusId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WipStatusMasterDTO{" +
            "wipStatusId=" + getWipStatusId() +
            ", wipStatusName='" + getWipStatusName() + "'" +
            ", taskId=" + getTaskId() +
            ", taskName='" + getTaskName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", wipStatusMasterUuid='" + getWipStatusMasterUuid() + "'" +
            "}";
    }
}
