package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.StopReasonMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StopReasonMasterDTO implements Serializable {

    private Long stopReasonId;

    private String stopReasonName;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID stopReasonMasterUuid;

    public Long getStopReasonId() {
        return stopReasonId;
    }

    public void setStopReasonId(Long stopReasonId) {
        this.stopReasonId = stopReasonId;
    }

    public String getStopReasonName() {
        return stopReasonName;
    }

    public void setStopReasonName(String stopReasonName) {
        this.stopReasonName = stopReasonName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public UUID getStopReasonMasterUuid() {
        return stopReasonMasterUuid;
    }

    public void setStopReasonMasterUuid(UUID stopReasonMasterUuid) {
        this.stopReasonMasterUuid = stopReasonMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StopReasonMasterDTO)) {
            return false;
        }

        StopReasonMasterDTO stopReasonMasterDTO = (StopReasonMasterDTO) o;
        if (this.stopReasonId == null) {
            return false;
        }
        return Objects.equals(this.stopReasonId, stopReasonMasterDTO.stopReasonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stopReasonId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StopReasonMasterDTO{" +
            "stopReasonId=" + getStopReasonId() +
            ", stopReasonName='" + getStopReasonName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", stopReasonMasterUuid='" + getStopReasonMasterUuid() + "'" +
            "}";
    }
}
