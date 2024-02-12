package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CoverageCriteriaFileMap.
 */
@Table("t_coverage_criteria_file_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CoverageCriteriaFileMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("coverage_criteria_file_map_id")
    private Long coverageCriteriaFileMapId;

    @Column("checklist_id")
    private Long checklistId;

    @Column("checklist_name")
    private String checklistName;

    @Column("document_reference_id")
    private Long documentReferenceId;

    @Column("document_reference_name")
    private String documentReferenceName;

    @Column("file_name")
    private String fileName;

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

    @Column("coverage_criteria_file_map_uuid")
    private UUID coverageCriteriaFileMapUuid;

    @Column("file_reference")
    private String fileReference;

    @Column("coverage_criteria_notes")
    private String coverageCriteriaNotes;

    @Column("so_id")
    private Long soId;

    @Column("item_group_id")
    private Long itemGroupId;

    @Column("item_group_name")
    private String itemGroupName;

    @Column("coverage_criteria_id")
    private Long coverageCriteriaId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCoverageCriteriaFileMapId() {
        return this.coverageCriteriaFileMapId;
    }

    public CoverageCriteriaFileMap coverageCriteriaFileMapId(Long coverageCriteriaFileMapId) {
        this.setCoverageCriteriaFileMapId(coverageCriteriaFileMapId);
        return this;
    }

    public void setCoverageCriteriaFileMapId(Long coverageCriteriaFileMapId) {
        this.coverageCriteriaFileMapId = coverageCriteriaFileMapId;
    }

    public Long getChecklistId() {
        return this.checklistId;
    }

    public CoverageCriteriaFileMap checklistId(Long checklistId) {
        this.setChecklistId(checklistId);
        return this;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return this.checklistName;
    }

    public CoverageCriteriaFileMap checklistName(String checklistName) {
        this.setChecklistName(checklistName);
        return this;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getDocumentReferenceId() {
        return this.documentReferenceId;
    }

    public CoverageCriteriaFileMap documentReferenceId(Long documentReferenceId) {
        this.setDocumentReferenceId(documentReferenceId);
        return this;
    }

    public void setDocumentReferenceId(Long documentReferenceId) {
        this.documentReferenceId = documentReferenceId;
    }

    public String getDocumentReferenceName() {
        return this.documentReferenceName;
    }

    public CoverageCriteriaFileMap documentReferenceName(String documentReferenceName) {
        this.setDocumentReferenceName(documentReferenceName);
        return this;
    }

    public void setDocumentReferenceName(String documentReferenceName) {
        this.documentReferenceName = documentReferenceName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public CoverageCriteriaFileMap fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return this.status;
    }

    public CoverageCriteriaFileMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public CoverageCriteriaFileMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public CoverageCriteriaFileMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public CoverageCriteriaFileMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public CoverageCriteriaFileMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public CoverageCriteriaFileMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public CoverageCriteriaFileMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getCoverageCriteriaFileMapUuid() {
        return this.coverageCriteriaFileMapUuid;
    }

    public CoverageCriteriaFileMap coverageCriteriaFileMapUuid(UUID coverageCriteriaFileMapUuid) {
        this.setCoverageCriteriaFileMapUuid(coverageCriteriaFileMapUuid);
        return this;
    }

    public void setCoverageCriteriaFileMapUuid(UUID coverageCriteriaFileMapUuid) {
        this.coverageCriteriaFileMapUuid = coverageCriteriaFileMapUuid;
    }

    public String getFileReference() {
        return this.fileReference;
    }

    public CoverageCriteriaFileMap fileReference(String fileReference) {
        this.setFileReference(fileReference);
        return this;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getCoverageCriteriaNotes() {
        return this.coverageCriteriaNotes;
    }

    public CoverageCriteriaFileMap coverageCriteriaNotes(String coverageCriteriaNotes) {
        this.setCoverageCriteriaNotes(coverageCriteriaNotes);
        return this;
    }

    public void setCoverageCriteriaNotes(String coverageCriteriaNotes) {
        this.coverageCriteriaNotes = coverageCriteriaNotes;
    }

    public Long getSoId() {
        return this.soId;
    }

    public CoverageCriteriaFileMap soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getItemGroupId() {
        return this.itemGroupId;
    }

    public CoverageCriteriaFileMap itemGroupId(Long itemGroupId) {
        this.setItemGroupId(itemGroupId);
        return this;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return this.itemGroupName;
    }

    public CoverageCriteriaFileMap itemGroupName(String itemGroupName) {
        this.setItemGroupName(itemGroupName);
        return this;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public Long getCoverageCriteriaId() {
        return this.coverageCriteriaId;
    }

    public CoverageCriteriaFileMap coverageCriteriaId(Long coverageCriteriaId) {
        this.setCoverageCriteriaId(coverageCriteriaId);
        return this;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoverageCriteriaFileMap)) {
            return false;
        }
        return (
            coverageCriteriaFileMapId != null && coverageCriteriaFileMapId.equals(((CoverageCriteriaFileMap) o).coverageCriteriaFileMapId)
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
        return "CoverageCriteriaFileMap{" +
            "coverageCriteriaFileMapId=" + getCoverageCriteriaFileMapId() +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", documentReferenceId=" + getDocumentReferenceId() +
            ", documentReferenceName='" + getDocumentReferenceName() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", coverageCriteriaFileMapUuid='" + getCoverageCriteriaFileMapUuid() + "'" +
            ", fileReference='" + getFileReference() + "'" +
            ", coverageCriteriaNotes='" + getCoverageCriteriaNotes() + "'" +
            ", soId=" + getSoId() +
            ", itemGroupId=" + getItemGroupId() +
            ", itemGroupName='" + getItemGroupName() + "'" +
            ", coverageCriteriaId=" + getCoverageCriteriaId() +
            "}";
    }
}
