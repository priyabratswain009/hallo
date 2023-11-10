package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDocument.
 */
@Table("t_patient_document")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_document_id")
    private Long patientDocumentId;

    @Column("patient_document_no")
    private String patientDocumentNo;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_uuid")
    private UUID patientUuid;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("patient_document_type")
    private String patientDocumentType;

    @Column("patient_document_repository_name")
    private String patientDocumentRepositoryName;

    @Column("patient_document_name")
    private String patientDocumentName;

    @Column("patient_document_description")
    private String patientDocumentDescription;

    @Column("patient_document_status")
    private String patientDocumentStatus;

    @Column("uploaded_by_id")
    private Long uploadedById;

    @Column("uploaded_by_name")
    private String uploadedByName;

    @Column("uploaded_date")
    private LocalDate uploadedDate;

    @Column("status")
    private String status;

    @Column("patient_document_uuid")
    private UUID patientDocumentUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientDocumentId() {
        return this.patientDocumentId;
    }

    public PatientDocument patientDocumentId(Long patientDocumentId) {
        this.setPatientDocumentId(patientDocumentId);
        return this;
    }

    public void setPatientDocumentId(Long patientDocumentId) {
        this.patientDocumentId = patientDocumentId;
    }

    public String getPatientDocumentNo() {
        return this.patientDocumentNo;
    }

    public PatientDocument patientDocumentNo(String patientDocumentNo) {
        this.setPatientDocumentNo(patientDocumentNo);
        return this;
    }

    public void setPatientDocumentNo(String patientDocumentNo) {
        this.patientDocumentNo = patientDocumentNo;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientDocument patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public UUID getPatientUuid() {
        return this.patientUuid;
    }

    public PatientDocument patientUuid(UUID patientUuid) {
        this.setPatientUuid(patientUuid);
        return this;
    }

    public void setPatientUuid(UUID patientUuid) {
        this.patientUuid = patientUuid;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public PatientDocument patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPatientDocumentType() {
        return this.patientDocumentType;
    }

    public PatientDocument patientDocumentType(String patientDocumentType) {
        this.setPatientDocumentType(patientDocumentType);
        return this;
    }

    public void setPatientDocumentType(String patientDocumentType) {
        this.patientDocumentType = patientDocumentType;
    }

    public String getPatientDocumentRepositoryName() {
        return this.patientDocumentRepositoryName;
    }

    public PatientDocument patientDocumentRepositoryName(String patientDocumentRepositoryName) {
        this.setPatientDocumentRepositoryName(patientDocumentRepositoryName);
        return this;
    }

    public void setPatientDocumentRepositoryName(String patientDocumentRepositoryName) {
        this.patientDocumentRepositoryName = patientDocumentRepositoryName;
    }

    public String getPatientDocumentName() {
        return this.patientDocumentName;
    }

    public PatientDocument patientDocumentName(String patientDocumentName) {
        this.setPatientDocumentName(patientDocumentName);
        return this;
    }

    public void setPatientDocumentName(String patientDocumentName) {
        this.patientDocumentName = patientDocumentName;
    }

    public String getPatientDocumentDescription() {
        return this.patientDocumentDescription;
    }

    public PatientDocument patientDocumentDescription(String patientDocumentDescription) {
        this.setPatientDocumentDescription(patientDocumentDescription);
        return this;
    }

    public void setPatientDocumentDescription(String patientDocumentDescription) {
        this.patientDocumentDescription = patientDocumentDescription;
    }

    public String getPatientDocumentStatus() {
        return this.patientDocumentStatus;
    }

    public PatientDocument patientDocumentStatus(String patientDocumentStatus) {
        this.setPatientDocumentStatus(patientDocumentStatus);
        return this;
    }

    public void setPatientDocumentStatus(String patientDocumentStatus) {
        this.patientDocumentStatus = patientDocumentStatus;
    }

    public Long getUploadedById() {
        return this.uploadedById;
    }

    public PatientDocument uploadedById(Long uploadedById) {
        this.setUploadedById(uploadedById);
        return this;
    }

    public void setUploadedById(Long uploadedById) {
        this.uploadedById = uploadedById;
    }

    public String getUploadedByName() {
        return this.uploadedByName;
    }

    public PatientDocument uploadedByName(String uploadedByName) {
        this.setUploadedByName(uploadedByName);
        return this;
    }

    public void setUploadedByName(String uploadedByName) {
        this.uploadedByName = uploadedByName;
    }

    public LocalDate getUploadedDate() {
        return this.uploadedDate;
    }

    public PatientDocument uploadedDate(LocalDate uploadedDate) {
        this.setUploadedDate(uploadedDate);
        return this;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientDocument status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getPatientDocumentUuid() {
        return this.patientDocumentUuid;
    }

    public PatientDocument patientDocumentUuid(UUID patientDocumentUuid) {
        this.setPatientDocumentUuid(patientDocumentUuid);
        return this;
    }

    public void setPatientDocumentUuid(UUID patientDocumentUuid) {
        this.patientDocumentUuid = patientDocumentUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDocument)) {
            return false;
        }
        return patientDocumentId != null && patientDocumentId.equals(((PatientDocument) o).patientDocumentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDocument{" +
            "patientDocumentId=" + getPatientDocumentId() +
            ", patientDocumentNo='" + getPatientDocumentNo() + "'" +
            ", patientId=" + getPatientId() +
            ", patientUuid='" + getPatientUuid() + "'" +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", patientDocumentType='" + getPatientDocumentType() + "'" +
            ", patientDocumentRepositoryName='" + getPatientDocumentRepositoryName() + "'" +
            ", patientDocumentName='" + getPatientDocumentName() + "'" +
            ", patientDocumentDescription='" + getPatientDocumentDescription() + "'" +
            ", patientDocumentStatus='" + getPatientDocumentStatus() + "'" +
            ", uploadedById=" + getUploadedById() +
            ", uploadedByName='" + getUploadedByName() + "'" +
            ", uploadedDate='" + getUploadedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", patientDocumentUuid='" + getPatientDocumentUuid() + "'" +
            "}";
    }
}
