package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A HcpcsDocType.
 */
@Entity
@Table(name = "t_hcpcs_doc_type")
public class HcpcsDocType implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "hcpcs_doctype_id", nullable = false)
    private Long hcpcsDoctypeId;

    @Column(name = "hcpcs_code")
    private String hcpcsCode;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_file_path")
    private String documentFilePath;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "hcpcs_doc_type_uuid")
    private UUID hcpcsDocTypeUuid;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "form_name")
    private String formName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getHcpcsDoctypeId() {
        return this.hcpcsDoctypeId;
    }

    public HcpcsDocType hcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.setHcpcsDoctypeId(hcpcsDoctypeId);
        return this;
    }

    public void setHcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.hcpcsDoctypeId = hcpcsDoctypeId;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public HcpcsDocType hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public HcpcsDocType documentId(Long documentId) {
        this.setDocumentId(documentId);
        return this;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public HcpcsDocType documentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFilePath() {
        return this.documentFilePath;
    }

    public HcpcsDocType documentFilePath(String documentFilePath) {
        this.setDocumentFilePath(documentFilePath);
        return this;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getStatus() {
        return this.status;
    }

    public HcpcsDocType status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public HcpcsDocType createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public HcpcsDocType createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public HcpcsDocType createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public HcpcsDocType updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public HcpcsDocType updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public HcpcsDocType updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getHcpcsDocTypeUuid() {
        return this.hcpcsDocTypeUuid;
    }

    public HcpcsDocType hcpcsDocTypeUuid(UUID hcpcsDocTypeUuid) {
        this.setHcpcsDocTypeUuid(hcpcsDocTypeUuid);
        return this;
    }

    public void setHcpcsDocTypeUuid(UUID hcpcsDocTypeUuid) {
        this.hcpcsDocTypeUuid = hcpcsDocTypeUuid;
    }

    public String getFileType() {
        return this.fileType;
    }

    public HcpcsDocType fileType(String fileType) {
        this.setFileType(fileType);
        return this;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFormName() {
        return this.formName;
    }

    public HcpcsDocType formName(String formName) {
        this.setFormName(formName);
        return this;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HcpcsDocType)) {
            return false;
        }
        return hcpcsDoctypeId != null && hcpcsDoctypeId.equals(((HcpcsDocType) o).hcpcsDoctypeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HcpcsDocType{" +
            "hcpcsDoctypeId=" + getHcpcsDoctypeId() +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", documentId=" + getDocumentId() +
            ", documentName='" + getDocumentName() + "'" +
            ", documentFilePath='" + getDocumentFilePath() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", hcpcsDocTypeUuid='" + getHcpcsDocTypeUuid() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", formName='" + getFormName() + "'" +
            "}";
    }
}
