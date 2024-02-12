package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimFormMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClaimFormMasterDTO implements Serializable {

    private Long claimFormId;

    private String claimFormName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID claimFormMasterUuid;

    public Long getClaimFormId() {
        return claimFormId;
    }

    public void setClaimFormId(Long claimFormId) {
        this.claimFormId = claimFormId;
    }

    public String getClaimFormName() {
        return claimFormName;
    }

    public void setClaimFormName(String claimFormName) {
        this.claimFormName = claimFormName;
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

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getClaimFormMasterUuid() {
        return claimFormMasterUuid;
    }

    public void setClaimFormMasterUuid(UUID claimFormMasterUuid) {
        this.claimFormMasterUuid = claimFormMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimFormMasterDTO)) {
            return false;
        }

        ClaimFormMasterDTO claimFormMasterDTO = (ClaimFormMasterDTO) o;
        if (this.claimFormId == null) {
            return false;
        }
        return Objects.equals(this.claimFormId, claimFormMasterDTO.claimFormId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimFormId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimFormMasterDTO{" +
            "claimFormId=" + getClaimFormId() +
            ", claimFormName='" + getClaimFormName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", claimFormMasterUuid='" + getClaimFormMasterUuid() + "'" +
            "}";
    }
}
