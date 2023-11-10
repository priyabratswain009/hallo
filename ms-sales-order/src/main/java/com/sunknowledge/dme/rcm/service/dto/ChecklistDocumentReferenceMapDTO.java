package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap} entity.
 */
public class ChecklistDocumentReferenceMapDTO implements Serializable {

    private Long checklistDocumentReferenceId;

    private Long checklistId;

    private String checklistName;

    private Long documentReferenceId;

    private String documentReferenceName;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID checklistDocumentReferenceMapUuid;

    public Long getChecklistDocumentReferenceId() {
        return checklistDocumentReferenceId;
    }

    public void setChecklistDocumentReferenceId(Long checklistDocumentReferenceId) {
        this.checklistDocumentReferenceId = checklistDocumentReferenceId;
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

    public UUID getChecklistDocumentReferenceMapUuid() {
        return checklistDocumentReferenceMapUuid;
    }

    public void setChecklistDocumentReferenceMapUuid(UUID checklistDocumentReferenceMapUuid) {
        this.checklistDocumentReferenceMapUuid = checklistDocumentReferenceMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChecklistDocumentReferenceMapDTO)) {
            return false;
        }

        ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO = (ChecklistDocumentReferenceMapDTO) o;
        if (this.checklistDocumentReferenceId == null) {
            return false;
        }
        return Objects.equals(this.checklistDocumentReferenceId, checklistDocumentReferenceMapDTO.checklistDocumentReferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.checklistDocumentReferenceId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChecklistDocumentReferenceMapDTO{" +
            "checklistDocumentReferenceId=" + getChecklistDocumentReferenceId() +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", documentReferenceId=" + getDocumentReferenceId() +
            ", documentReferenceName='" + getDocumentReferenceName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", checklistDocumentReferenceMapUuid='" + getChecklistDocumentReferenceMapUuid() + "'" +
            "}";
    }
}
