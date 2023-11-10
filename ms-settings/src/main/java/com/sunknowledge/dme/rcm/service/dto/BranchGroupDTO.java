package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BranchGroup} entity.
 */
public class BranchGroupDTO implements Serializable {

    private Long branchGroupId;

    private String branchGroupName;

    private Long companyId;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private Long updatedById;

    private String createdByName;

    private String updatedByName;

    private UUID branchGroupUuid;

    public Long getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Long branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public String getBranchGroupName() {
        return branchGroupName;
    }

    public void setBranchGroupName(String branchGroupName) {
        this.branchGroupName = branchGroupName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public UUID getBranchGroupUuid() {
        return branchGroupUuid;
    }

    public void setBranchGroupUuid(UUID branchGroupUuid) {
        this.branchGroupUuid = branchGroupUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchGroupDTO)) {
            return false;
        }

        BranchGroupDTO branchGroupDTO = (BranchGroupDTO) o;
        if (this.branchGroupId == null) {
            return false;
        }
        return Objects.equals(this.branchGroupId, branchGroupDTO.branchGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.branchGroupId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchGroupDTO{" +
            "branchGroupId=" + getBranchGroupId() +
            ", branchGroupName='" + getBranchGroupName() + "'" +
            ", companyId=" + getCompanyId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", branchGroupUuid='" + getBranchGroupUuid() + "'" +
            "}";
    }
}
