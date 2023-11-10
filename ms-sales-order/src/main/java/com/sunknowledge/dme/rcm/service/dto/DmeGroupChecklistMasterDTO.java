package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster} entity.
 */
public class DmeGroupChecklistMasterDTO implements Serializable {

    private Long dmeGroupChecklistId;

    private Long dmeGroupId;

    private String dmeGroupName;

    private Long checklistId;

    private String checklistName;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID dmeGroupChecklistMasterUuid;

    public Long getDmeGroupChecklistId() {
        return dmeGroupChecklistId;
    }

    public void setDmeGroupChecklistId(Long dmeGroupChecklistId) {
        this.dmeGroupChecklistId = dmeGroupChecklistId;
    }

    public Long getDmeGroupId() {
        return dmeGroupId;
    }

    public void setDmeGroupId(Long dmeGroupId) {
        this.dmeGroupId = dmeGroupId;
    }

    public String getDmeGroupName() {
        return dmeGroupName;
    }

    public void setDmeGroupName(String dmeGroupName) {
        this.dmeGroupName = dmeGroupName;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return checklistName;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public UUID getDmeGroupChecklistMasterUuid() {
        return dmeGroupChecklistMasterUuid;
    }

    public void setDmeGroupChecklistMasterUuid(UUID dmeGroupChecklistMasterUuid) {
        this.dmeGroupChecklistMasterUuid = dmeGroupChecklistMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmeGroupChecklistMasterDTO)) {
            return false;
        }

        DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO = (DmeGroupChecklistMasterDTO) o;
        if (this.dmeGroupChecklistId == null) {
            return false;
        }
        return Objects.equals(this.dmeGroupChecklistId, dmeGroupChecklistMasterDTO.dmeGroupChecklistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.dmeGroupChecklistId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmeGroupChecklistMasterDTO{" +
            "dmeGroupChecklistId=" + getDmeGroupChecklistId() +
            ", dmeGroupId=" + getDmeGroupId() +
            ", dmeGroupName='" + getDmeGroupName() + "'" +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", dmeGroupChecklistMasterUuid='" + getDmeGroupChecklistMasterUuid() + "'" +
            "}";
    }
}
