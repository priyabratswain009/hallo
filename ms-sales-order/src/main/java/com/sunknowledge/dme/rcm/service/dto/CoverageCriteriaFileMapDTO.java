package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CoverageCriteriaFileMapDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long coverageCriteriaFileMapId;

    private Long checklistId;

    private String checklistName;

    private Long documentReferenceId;

    private String documentReferenceName;

    private String fileName;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID coverageCriteriaFileMapUuid;

    private String fileReference;

    private String coverageCriteriaNotes;

    private Long soId;

    private Long itemGroupId;

    private String itemGroupName;

    private Long coverageCriteriaId;

    public Long getCoverageCriteriaFileMapId() {
        return coverageCriteriaFileMapId;
    }

    public void setCoverageCriteriaFileMapId(Long coverageCriteriaFileMapId) {
        this.coverageCriteriaFileMapId = coverageCriteriaFileMapId;
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

    public Long getDocumentReferenceId() {
        return documentReferenceId;
    }

    public void setDocumentReferenceId(Long documentReferenceId) {
        this.documentReferenceId = documentReferenceId;
    }

    public String getDocumentReferenceName() {
        return documentReferenceName;
    }

    public void setDocumentReferenceName(String documentReferenceName) {
        this.documentReferenceName = documentReferenceName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public UUID getCoverageCriteriaFileMapUuid() {
        return coverageCriteriaFileMapUuid;
    }

    public void setCoverageCriteriaFileMapUuid(UUID coverageCriteriaFileMapUuid) {
        this.coverageCriteriaFileMapUuid = coverageCriteriaFileMapUuid;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getCoverageCriteriaNotes() {
        return coverageCriteriaNotes;
    }

    public void setCoverageCriteriaNotes(String coverageCriteriaNotes) {
        this.coverageCriteriaNotes = coverageCriteriaNotes;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public Long getCoverageCriteriaId() {
        return coverageCriteriaId;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoverageCriteriaFileMapDTO)) {
            return false;
        }

        CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO = (CoverageCriteriaFileMapDTO) o;
        if (this.coverageCriteriaFileMapId == null) {
            return false;
        }
        return Objects.equals(this.coverageCriteriaFileMapId, coverageCriteriaFileMapDTO.coverageCriteriaFileMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.coverageCriteriaFileMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoverageCriteriaFileMapDTO{" +
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
