package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.WipStatusMaster} entity.
 */
public class WipStatusMasterDTO implements Serializable {

    private Long wipStatusId;

    private String wipStatusName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
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
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", wipStatusMasterUuid='" + getWipStatusMasterUuid() + "'" +
            "}";
    }
}
