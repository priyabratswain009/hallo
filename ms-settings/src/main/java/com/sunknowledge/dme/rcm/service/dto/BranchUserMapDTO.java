package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BranchUserMap} entity.
 */
public class BranchUserMapDTO implements Serializable {

    private Long mapId;

    private Long branchId;

    private Long userId;

    private String description;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private String createdByName;

    private UUID branchUserMapUuid;

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public UUID getBranchUserMapUuid() {
        return branchUserMapUuid;
    }

    public void setBranchUserMapUuid(UUID branchUserMapUuid) {
        this.branchUserMapUuid = branchUserMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchUserMapDTO)) {
            return false;
        }

        BranchUserMapDTO branchUserMapDTO = (BranchUserMapDTO) o;
        if (this.mapId == null) {
            return false;
        }
        return Objects.equals(this.mapId, branchUserMapDTO.mapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchUserMapDTO{" +
            "mapId=" + getMapId() +
            ", branchId=" + getBranchId() +
            ", userId=" + getUserId() +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", branchUserMapUuid='" + getBranchUserMapUuid() + "'" +
            "}";
    }
}
