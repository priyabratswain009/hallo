package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.HoldReasonMaster} entity.
 */
public class HoldReasonMasterDTO implements Serializable {

    private Long holdReasonId;

    private String holdReasonName;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID holdReasonMasterUuid;

    public Long getHoldReasonId() {
        return holdReasonId;
    }

    public void setHoldReasonId(Long holdReasonId) {
        this.holdReasonId = holdReasonId;
    }

    public String getHoldReasonName() {
        return holdReasonName;
    }

    public void setHoldReasonName(String holdReasonName) {
        this.holdReasonName = holdReasonName;
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

    public UUID getHoldReasonMasterUuid() {
        return holdReasonMasterUuid;
    }

    public void setHoldReasonMasterUuid(UUID holdReasonMasterUuid) {
        this.holdReasonMasterUuid = holdReasonMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HoldReasonMasterDTO)) {
            return false;
        }

        HoldReasonMasterDTO holdReasonMasterDTO = (HoldReasonMasterDTO) o;
        if (this.holdReasonId == null) {
            return false;
        }
        return Objects.equals(this.holdReasonId, holdReasonMasterDTO.holdReasonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.holdReasonId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HoldReasonMasterDTO{" +
            "holdReasonId=" + getHoldReasonId() +
            ", holdReasonName='" + getHoldReasonName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", holdReasonMasterUuid='" + getHoldReasonMasterUuid() + "'" +
            "}";
    }
}
