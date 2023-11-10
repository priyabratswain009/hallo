package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ChecklistDocumentReferenceMap.
 */
@Table("t_checklist_document_reference_map")
public class ChecklistDocumentReferenceMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("checklist_document_reference_id")
    private Long checklistDocumentReferenceId;

    @Column("checklist_id")
    private Long checklistId;

    @Column("checklist_name")
    private String checklistName;

    @Column("document_reference_id")
    private Long documentReferenceId;

    @Column("document_reference_name")
    private String documentReferenceName;

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

    @Column("checklist_document_reference_map_uuid")
    private UUID checklistDocumentReferenceMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChecklistDocumentReferenceId() {
        return this.checklistDocumentReferenceId;
    }

    public ChecklistDocumentReferenceMap checklistDocumentReferenceId(Long checklistDocumentReferenceId) {
        this.setChecklistDocumentReferenceId(checklistDocumentReferenceId);
        return this;
    }

    public void setChecklistDocumentReferenceId(Long checklistDocumentReferenceId) {
        this.checklistDocumentReferenceId = checklistDocumentReferenceId;
    }

    public Long getChecklistId() {
        return this.checklistId;
    }

    public ChecklistDocumentReferenceMap checklistId(Long checklistId) {
        this.setChecklistId(checklistId);
        return this;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return this.checklistName;
    }

    public ChecklistDocumentReferenceMap checklistName(String checklistName) {
        this.setChecklistName(checklistName);
        return this;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getDocumentReferenceId() {
        return this.documentReferenceId;
    }

    public ChecklistDocumentReferenceMap documentReferenceId(Long documentReferenceId) {
        this.setDocumentReferenceId(documentReferenceId);
        return this;
    }

    public void setDocumentReferenceId(Long documentReferenceId) {
        this.documentReferenceId = documentReferenceId;
    }

    public String getDocumentReferenceName() {
        return this.documentReferenceName;
    }

    public ChecklistDocumentReferenceMap documentReferenceName(String documentReferenceName) {
        this.setDocumentReferenceName(documentReferenceName);
        return this;
    }

    public void setDocumentReferenceName(String documentReferenceName) {
        this.documentReferenceName = documentReferenceName;
    }

    public String getStatus() {
        return this.status;
    }

    public ChecklistDocumentReferenceMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ChecklistDocumentReferenceMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ChecklistDocumentReferenceMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ChecklistDocumentReferenceMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ChecklistDocumentReferenceMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ChecklistDocumentReferenceMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ChecklistDocumentReferenceMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getChecklistDocumentReferenceMapUuid() {
        return this.checklistDocumentReferenceMapUuid;
    }

    public ChecklistDocumentReferenceMap checklistDocumentReferenceMapUuid(UUID checklistDocumentReferenceMapUuid) {
        this.setChecklistDocumentReferenceMapUuid(checklistDocumentReferenceMapUuid);
        return this;
    }

    public void setChecklistDocumentReferenceMapUuid(UUID checklistDocumentReferenceMapUuid) {
        this.checklistDocumentReferenceMapUuid = checklistDocumentReferenceMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChecklistDocumentReferenceMap)) {
            return false;
        }
        return (
            checklistDocumentReferenceId != null &&
            checklistDocumentReferenceId.equals(((ChecklistDocumentReferenceMap) o).checklistDocumentReferenceId)
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
        return "ChecklistDocumentReferenceMap{" +
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
