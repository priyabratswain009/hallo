package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DocumentTypeMaster.
 */
@Entity
@Table(name = "t_document_type_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DocumentTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "document_type_id")
    private Long documentTypeId;

    @Column(name = "document_type_name")
    private String documentTypeName;

    @Column(name = "created_by_id")
    private String createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "document_type_master_uuid")
    private UUID documentTypeMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDocumentTypeId() {
        return this.documentTypeId;
    }

    public DocumentTypeMaster documentTypeId(Long documentTypeId) {
        this.setDocumentTypeId(documentTypeId);
        return this;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeName() {
        return this.documentTypeName;
    }

    public DocumentTypeMaster documentTypeName(String documentTypeName) {
        this.setDocumentTypeName(documentTypeName);
        return this;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getCreatedById() {
        return this.createdById;
    }

    public DocumentTypeMaster createdById(String createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DocumentTypeMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public DocumentTypeMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DocumentTypeMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DocumentTypeMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DocumentTypeMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DocumentTypeMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getDocumentTypeMasterUuid() {
        return this.documentTypeMasterUuid;
    }

    public DocumentTypeMaster documentTypeMasterUuid(UUID documentTypeMasterUuid) {
        this.setDocumentTypeMasterUuid(documentTypeMasterUuid);
        return this;
    }

    public void setDocumentTypeMasterUuid(UUID documentTypeMasterUuid) {
        this.documentTypeMasterUuid = documentTypeMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentTypeMaster)) {
            return false;
        }
        return documentTypeId != null && documentTypeId.equals(((DocumentTypeMaster) o).documentTypeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentTypeMaster{" +
            "documentTypeId=" + getDocumentTypeId() +
            ", documentTypeName='" + getDocumentTypeName() + "'" +
            ", createdById='" + getCreatedById() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", documentTypeMasterUuid='" + getDocumentTypeMasterUuid() + "'" +
            "}";
    }
}
