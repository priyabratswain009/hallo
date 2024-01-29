package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentReferenceFileMapDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long documentReferenceFileMapId;

    private Long checklistId;

    private String checklistName;

    private Long coverageCriteriaId;

    private String fileName;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID documentReferenceFileMapUuid;

    private String fileReference;

    private String documentReferenceNotes;

    private Long soId;

    private Long itemGroupId;

    private String itemGroupName;

    public Long getDocumentReferenceFileMapId() {
        return documentReferenceFileMapId;
    }

    public void setDocumentReferenceFileMapId(Long documentReferenceFileMapId) {
        this.documentReferenceFileMapId = documentReferenceFileMapId;
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

    public UUID getDocumentReferenceFileMapUuid() {
        return documentReferenceFileMapUuid;
    }

    public void setDocumentReferenceFileMapUuid(UUID documentReferenceFileMapUuid) {
        this.documentReferenceFileMapUuid = documentReferenceFileMapUuid;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getDocumentReferenceNotes() {
        return documentReferenceNotes;
    }

    public void setDocumentReferenceNotes(String documentReferenceNotes) {
        this.documentReferenceNotes = documentReferenceNotes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentReferenceFileMapDTO)) {
            return false;
        }

        DocumentReferenceFileMapDTO documentReferenceFileMapDTO = (DocumentReferenceFileMapDTO) o;
        if (this.documentReferenceFileMapId == null) {
            return false;
        }
        return Objects.equals(this.documentReferenceFileMapId, documentReferenceFileMapDTO.documentReferenceFileMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.documentReferenceFileMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentReferenceFileMapDTO{" +
            "documentReferenceFileMapId=" + getDocumentReferenceFileMapId() +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", coverageCriteriaId=" + getCoverageCriteriaId() +
            ", fileName='" + getFileName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", documentReferenceFileMapUuid='" + getDocumentReferenceFileMapUuid() + "'" +
            ", fileReference='" + getFileReference() + "'" +
            ", documentReferenceNotes='" + getDocumentReferenceNotes() + "'" +
            ", soId=" + getSoId() +
            ", itemGroupId=" + getItemGroupId() +
            ", itemGroupName='" + getItemGroupName() + "'" +
            "}";
    }
}
