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
 * A DocumentReferenceFileMap.
 */
@Table("t_document_reference_file_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentReferenceFileMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("document_reference_file_map_id")
    private Long documentReferenceFileMapId;

    @Column("checklist_id")
    private Long checklistId;

    @Column("checklist_name")
    private String checklistName;

    @Column("coverage_criteria_id")
    private Long coverageCriteriaId;

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

    @Column("document_reference_file_map_uuid")
    private UUID documentReferenceFileMapUuid;

    @Column("file_reference")
    private String fileReference;

    @Column("document_reference_notes")
    private String documentReferenceNotes;

    @Column("so_id")
    private Long soId;

    @Column("item_group_id")
    private Long itemGroupId;

    @Column("item_group_name")
    private String itemGroupName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDocumentReferenceFileMapId() {
        return this.documentReferenceFileMapId;
    }

    public DocumentReferenceFileMap documentReferenceFileMapId(Long documentReferenceFileMapId) {
        this.setDocumentReferenceFileMapId(documentReferenceFileMapId);
        return this;
    }

    public void setDocumentReferenceFileMapId(Long documentReferenceFileMapId) {
        this.documentReferenceFileMapId = documentReferenceFileMapId;
    }

    public Long getChecklistId() {
        return this.checklistId;
    }

    public DocumentReferenceFileMap checklistId(Long checklistId) {
        this.setChecklistId(checklistId);
        return this;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return this.checklistName;
    }

    public DocumentReferenceFileMap checklistName(String checklistName) {
        this.setChecklistName(checklistName);
        return this;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getCoverageCriteriaId() {
        return this.coverageCriteriaId;
    }

    public DocumentReferenceFileMap coverageCriteriaId(Long coverageCriteriaId) {
        this.setCoverageCriteriaId(coverageCriteriaId);
        return this;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public DocumentReferenceFileMap fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return this.status;
    }

    public DocumentReferenceFileMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DocumentReferenceFileMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DocumentReferenceFileMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DocumentReferenceFileMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DocumentReferenceFileMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DocumentReferenceFileMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DocumentReferenceFileMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getDocumentReferenceFileMapUuid() {
        return this.documentReferenceFileMapUuid;
    }

    public DocumentReferenceFileMap documentReferenceFileMapUuid(UUID documentReferenceFileMapUuid) {
        this.setDocumentReferenceFileMapUuid(documentReferenceFileMapUuid);
        return this;
    }

    public void setDocumentReferenceFileMapUuid(UUID documentReferenceFileMapUuid) {
        this.documentReferenceFileMapUuid = documentReferenceFileMapUuid;
    }

    public String getFileReference() {
        return this.fileReference;
    }

    public DocumentReferenceFileMap fileReference(String fileReference) {
        this.setFileReference(fileReference);
        return this;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getDocumentReferenceNotes() {
        return this.documentReferenceNotes;
    }

    public DocumentReferenceFileMap documentReferenceNotes(String documentReferenceNotes) {
        this.setDocumentReferenceNotes(documentReferenceNotes);
        return this;
    }

    public void setDocumentReferenceNotes(String documentReferenceNotes) {
        this.documentReferenceNotes = documentReferenceNotes;
    }

    public Long getSoId() {
        return this.soId;
    }

    public DocumentReferenceFileMap soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getItemGroupId() {
        return this.itemGroupId;
    }

    public DocumentReferenceFileMap itemGroupId(Long itemGroupId) {
        this.setItemGroupId(itemGroupId);
        return this;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return this.itemGroupName;
    }

    public DocumentReferenceFileMap itemGroupName(String itemGroupName) {
        this.setItemGroupName(itemGroupName);
        return this;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentReferenceFileMap)) {
            return false;
        }
        return (
            documentReferenceFileMapId != null &&
            documentReferenceFileMapId.equals(((DocumentReferenceFileMap) o).documentReferenceFileMapId)
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
        return "DocumentReferenceFileMap{" +
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
