package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BranchItemLocationMap} entity.
 */
public class BranchItemLocationMapDTO implements Serializable {

    private Long branchItemLocationMapId;

    private Long branchId;

    private Long itemLocationId;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID branchItemLocationMapUuid;

    public Long getBranchItemLocationMapId() {
        return branchItemLocationMapId;
    }

    public void setBranchItemLocationMapId(Long branchItemLocationMapId) {
        this.branchItemLocationMapId = branchItemLocationMapId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
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

    public UUID getBranchItemLocationMapUuid() {
        return branchItemLocationMapUuid;
    }

    public void setBranchItemLocationMapUuid(UUID branchItemLocationMapUuid) {
        this.branchItemLocationMapUuid = branchItemLocationMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchItemLocationMapDTO)) {
            return false;
        }

        BranchItemLocationMapDTO branchItemLocationMapDTO = (BranchItemLocationMapDTO) o;
        if (this.branchItemLocationMapId == null) {
            return false;
        }
        return Objects.equals(this.branchItemLocationMapId, branchItemLocationMapDTO.branchItemLocationMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.branchItemLocationMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchItemLocationMapDTO{" +
            "branchItemLocationMapId=" + getBranchItemLocationMapId() +
            ", branchId=" + getBranchId() +
            ", itemLocationId=" + getItemLocationId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", branchItemLocationMapUuid='" + getBranchItemLocationMapUuid() + "'" +
            "}";
    }
}
