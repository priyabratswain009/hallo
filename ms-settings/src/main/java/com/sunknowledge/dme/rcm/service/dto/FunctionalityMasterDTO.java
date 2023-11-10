package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.FunctionalityMaster} entity.
 */
public class FunctionalityMasterDTO implements Serializable {

    private Long functionalityId;

    private String functionalityNo;

    private String functionalityName;

    private String functionalityDescription;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID functionalityMasterUuid;

    public Long getFunctionalityId() {
        return functionalityId;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
    }

    public String getFunctionalityNo() {
        return functionalityNo;
    }

    public void setFunctionalityNo(String functionalityNo) {
        this.functionalityNo = functionalityNo;
    }

    public String getFunctionalityName() {
        return functionalityName;
    }

    public void setFunctionalityName(String functionalityName) {
        this.functionalityName = functionalityName;
    }

    public String getFunctionalityDescription() {
        return functionalityDescription;
    }

    public void setFunctionalityDescription(String functionalityDescription) {
        this.functionalityDescription = functionalityDescription;
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

    public UUID getFunctionalityMasterUuid() {
        return functionalityMasterUuid;
    }

    public void setFunctionalityMasterUuid(UUID functionalityMasterUuid) {
        this.functionalityMasterUuid = functionalityMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionalityMasterDTO)) {
            return false;
        }

        FunctionalityMasterDTO functionalityMasterDTO = (FunctionalityMasterDTO) o;
        if (this.functionalityId == null) {
            return false;
        }
        return Objects.equals(this.functionalityId, functionalityMasterDTO.functionalityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.functionalityId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FunctionalityMasterDTO{" +
            "functionalityId=" + getFunctionalityId() +
            ", functionalityNo='" + getFunctionalityNo() + "'" +
            ", functionalityName='" + getFunctionalityName() + "'" +
            ", functionalityDescription='" + getFunctionalityDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", functionalityMasterUuid='" + getFunctionalityMasterUuid() + "'" +
            "}";
    }
}
