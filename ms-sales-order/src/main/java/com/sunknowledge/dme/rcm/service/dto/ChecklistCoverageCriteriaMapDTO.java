package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap} entity.
 */
public class ChecklistCoverageCriteriaMapDTO implements Serializable {

    private Long checklistCoverageCriteriaId;

    private Long checklistId;

    private String checklistName;

    private Long coverageCriteriaId;

    private String coverageCriteriaDetails;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID checklistCoverageCriteriaMapUuid;

    private String coverageCriteriaName;

    public Long getChecklistCoverageCriteriaId() {
        return checklistCoverageCriteriaId;
    }

    public void setChecklistCoverageCriteriaId(Long checklistCoverageCriteriaId) {
        this.checklistCoverageCriteriaId = checklistCoverageCriteriaId;
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

    public Long getCoverageCriteriaId() {
        return coverageCriteriaId;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    public String getCoverageCriteriaDetails() {
        return coverageCriteriaDetails;
    }

    public void setCoverageCriteriaDetails(String coverageCriteriaDetails) {
        this.coverageCriteriaDetails = coverageCriteriaDetails;
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

    public UUID getChecklistCoverageCriteriaMapUuid() {
        return checklistCoverageCriteriaMapUuid;
    }

    public void setChecklistCoverageCriteriaMapUuid(UUID checklistCoverageCriteriaMapUuid) {
        this.checklistCoverageCriteriaMapUuid = checklistCoverageCriteriaMapUuid;
    }

    public String getCoverageCriteriaName() {
        return coverageCriteriaName;
    }

    public void setCoverageCriteriaName(String coverageCriteriaName) {
        this.coverageCriteriaName = coverageCriteriaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChecklistCoverageCriteriaMapDTO)) {
            return false;
        }

        ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO = (ChecklistCoverageCriteriaMapDTO) o;
        if (this.checklistCoverageCriteriaId == null) {
            return false;
        }
        return Objects.equals(this.checklistCoverageCriteriaId, checklistCoverageCriteriaMapDTO.checklistCoverageCriteriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.checklistCoverageCriteriaId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChecklistCoverageCriteriaMapDTO{" +
            "checklistCoverageCriteriaId=" + getChecklistCoverageCriteriaId() +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", coverageCriteriaId=" + getCoverageCriteriaId() +
            ", coverageCriteriaDetails='" + getCoverageCriteriaDetails() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", checklistCoverageCriteriaMapUuid='" + getChecklistCoverageCriteriaMapUuid() + "'" +
            ", coverageCriteriaName='" + getCoverageCriteriaName() + "'" +
            "}";
    }
}
