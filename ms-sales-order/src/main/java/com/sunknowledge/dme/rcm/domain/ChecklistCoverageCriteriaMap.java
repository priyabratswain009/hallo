package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ChecklistCoverageCriteriaMap.
 */
@Table("t_checklist_coverage_criteria_map")
public class ChecklistCoverageCriteriaMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("checklist_coverage_criteria_id")
    private Long checklistCoverageCriteriaId;

    @Column("checklist_id")
    private Long checklistId;

    @Column("checklist_name")
    private String checklistName;

    @Column("coverage_criteria_id")
    private Long coverageCriteriaId;

    @Column("coverage_criteria_details")
    private String coverageCriteriaDetails;

    @Column("status")
    private String status;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("checklist_coverage_criteria_map_uuid")
    private UUID checklistCoverageCriteriaMapUuid;

    @Column("coverage_criteria_name")
    private String coverageCriteriaName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChecklistCoverageCriteriaId() {
        return this.checklistCoverageCriteriaId;
    }

    public ChecklistCoverageCriteriaMap checklistCoverageCriteriaId(Long checklistCoverageCriteriaId) {
        this.setChecklistCoverageCriteriaId(checklistCoverageCriteriaId);
        return this;
    }

    public void setChecklistCoverageCriteriaId(Long checklistCoverageCriteriaId) {
        this.checklistCoverageCriteriaId = checklistCoverageCriteriaId;
    }

    public Long getChecklistId() {
        return this.checklistId;
    }

    public ChecklistCoverageCriteriaMap checklistId(Long checklistId) {
        this.setChecklistId(checklistId);
        return this;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return this.checklistName;
    }

    public ChecklistCoverageCriteriaMap checklistName(String checklistName) {
        this.setChecklistName(checklistName);
        return this;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getCoverageCriteriaId() {
        return this.coverageCriteriaId;
    }

    public ChecklistCoverageCriteriaMap coverageCriteriaId(Long coverageCriteriaId) {
        this.setCoverageCriteriaId(coverageCriteriaId);
        return this;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    public String getCoverageCriteriaDetails() {
        return this.coverageCriteriaDetails;
    }

    public ChecklistCoverageCriteriaMap coverageCriteriaDetails(String coverageCriteriaDetails) {
        this.setCoverageCriteriaDetails(coverageCriteriaDetails);
        return this;
    }

    public void setCoverageCriteriaDetails(String coverageCriteriaDetails) {
        this.coverageCriteriaDetails = coverageCriteriaDetails;
    }

    public String getStatus() {
        return this.status;
    }

    public ChecklistCoverageCriteriaMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ChecklistCoverageCriteriaMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ChecklistCoverageCriteriaMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ChecklistCoverageCriteriaMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ChecklistCoverageCriteriaMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ChecklistCoverageCriteriaMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ChecklistCoverageCriteriaMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getChecklistCoverageCriteriaMapUuid() {
        return this.checklistCoverageCriteriaMapUuid;
    }

    public ChecklistCoverageCriteriaMap checklistCoverageCriteriaMapUuid(UUID checklistCoverageCriteriaMapUuid) {
        this.setChecklistCoverageCriteriaMapUuid(checklistCoverageCriteriaMapUuid);
        return this;
    }

    public void setChecklistCoverageCriteriaMapUuid(UUID checklistCoverageCriteriaMapUuid) {
        this.checklistCoverageCriteriaMapUuid = checklistCoverageCriteriaMapUuid;
    }

    public String getCoverageCriteriaName() {
        return this.coverageCriteriaName;
    }

    public ChecklistCoverageCriteriaMap coverageCriteriaName(String coverageCriteriaName) {
        this.setCoverageCriteriaName(coverageCriteriaName);
        return this;
    }

    public void setCoverageCriteriaName(String coverageCriteriaName) {
        this.coverageCriteriaName = coverageCriteriaName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChecklistCoverageCriteriaMap)) {
            return false;
        }
        return (
            checklistCoverageCriteriaId != null &&
            checklistCoverageCriteriaId.equals(((ChecklistCoverageCriteriaMap) o).checklistCoverageCriteriaId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChecklistCoverageCriteriaMap{" +
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
