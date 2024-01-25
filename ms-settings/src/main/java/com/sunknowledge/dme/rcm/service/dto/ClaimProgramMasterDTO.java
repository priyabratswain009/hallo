package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimProgramMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClaimProgramMasterDTO implements Serializable {

    private Long claimProgramMasterId;

    private String claimProgramMasterKey;

    private UUID claimProgramMasterUuid;

    private String claimProgramMasterValue;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    public Long getClaimProgramMasterId() {
        return claimProgramMasterId;
    }

    public void setClaimProgramMasterId(Long claimProgramMasterId) {
        this.claimProgramMasterId = claimProgramMasterId;
    }

    public String getClaimProgramMasterKey() {
        return claimProgramMasterKey;
    }

    public void setClaimProgramMasterKey(String claimProgramMasterKey) {
        this.claimProgramMasterKey = claimProgramMasterKey;
    }

    public UUID getClaimProgramMasterUuid() {
        return claimProgramMasterUuid;
    }

    public void setClaimProgramMasterUuid(UUID claimProgramMasterUuid) {
        this.claimProgramMasterUuid = claimProgramMasterUuid;
    }

    public String getClaimProgramMasterValue() {
        return claimProgramMasterValue;
    }

    public void setClaimProgramMasterValue(String claimProgramMasterValue) {
        this.claimProgramMasterValue = claimProgramMasterValue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimProgramMasterDTO)) {
            return false;
        }

        ClaimProgramMasterDTO claimProgramMasterDTO = (ClaimProgramMasterDTO) o;
        if (this.claimProgramMasterId == null) {
            return false;
        }
        return Objects.equals(this.claimProgramMasterId, claimProgramMasterDTO.claimProgramMasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimProgramMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimProgramMasterDTO{" +
            "claimProgramMasterId=" + getClaimProgramMasterId() +
            ", claimProgramMasterKey='" + getClaimProgramMasterKey() + "'" +
            ", claimProgramMasterUuid='" + getClaimProgramMasterUuid() + "'" +
            ", claimProgramMasterValue='" + getClaimProgramMasterValue() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
