package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDocumentSoMap.
 */
@Table("t_patient_document_so_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDocumentSoMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_document_so_map_id")
    private Long patientDocumentSoMapId;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("patient_document_id")
    private Long patientDocumentId;

    @Column("patient_document_no")
    private String patientDocumentNo;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("uploaded_by_id")
    private Long uploadedById;

    @Column("uploaded_by_name")
    private String uploadedByName;

    @Column("uploaded_date")
    private LocalDate uploadedDate;

    @Column("status")
    private String status;

    @Column("patient_document_so_map_uuid")
    private UUID patientDocumentSoMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientDocumentSoMapId() {
        return this.patientDocumentSoMapId;
    }

    public PatientDocumentSoMap patientDocumentSoMapId(Long patientDocumentSoMapId) {
        this.setPatientDocumentSoMapId(patientDocumentSoMapId);
        return this;
    }

    public void setPatientDocumentSoMapId(Long patientDocumentSoMapId) {
        this.patientDocumentSoMapId = patientDocumentSoMapId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientDocumentSoMap patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public PatientDocumentSoMap patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public Long getPatientDocumentId() {
        return this.patientDocumentId;
    }

    public PatientDocumentSoMap patientDocumentId(Long patientDocumentId) {
        this.setPatientDocumentId(patientDocumentId);
        return this;
    }

    public void setPatientDocumentId(Long patientDocumentId) {
        this.patientDocumentId = patientDocumentId;
    }

    public String getPatientDocumentNo() {
        return this.patientDocumentNo;
    }

    public PatientDocumentSoMap patientDocumentNo(String patientDocumentNo) {
        this.setPatientDocumentNo(patientDocumentNo);
        return this;
    }

    public void setPatientDocumentNo(String patientDocumentNo) {
        this.patientDocumentNo = patientDocumentNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public PatientDocumentSoMap soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public PatientDocumentSoMap soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getUploadedById() {
        return this.uploadedById;
    }

    public PatientDocumentSoMap uploadedById(Long uploadedById) {
        this.setUploadedById(uploadedById);
        return this;
    }

    public void setUploadedById(Long uploadedById) {
        this.uploadedById = uploadedById;
    }

    public String getUploadedByName() {
        return this.uploadedByName;
    }

    public PatientDocumentSoMap uploadedByName(String uploadedByName) {
        this.setUploadedByName(uploadedByName);
        return this;
    }

    public void setUploadedByName(String uploadedByName) {
        this.uploadedByName = uploadedByName;
    }

    public LocalDate getUploadedDate() {
        return this.uploadedDate;
    }

    public PatientDocumentSoMap uploadedDate(LocalDate uploadedDate) {
        this.setUploadedDate(uploadedDate);
        return this;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientDocumentSoMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getPatientDocumentSoMapUuid() {
        return this.patientDocumentSoMapUuid;
    }

    public PatientDocumentSoMap patientDocumentSoMapUuid(UUID patientDocumentSoMapUuid) {
        this.setPatientDocumentSoMapUuid(patientDocumentSoMapUuid);
        return this;
    }

    public void setPatientDocumentSoMapUuid(UUID patientDocumentSoMapUuid) {
        this.patientDocumentSoMapUuid = patientDocumentSoMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDocumentSoMap)) {
            return false;
        }
        return patientDocumentSoMapId != null && patientDocumentSoMapId.equals(((PatientDocumentSoMap) o).patientDocumentSoMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDocumentSoMap{" +
            "patientDocumentSoMapId=" + getPatientDocumentSoMapId() +
            ", patientId=" + getPatientId() +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", patientDocumentId=" + getPatientDocumentId() +
            ", patientDocumentNo='" + getPatientDocumentNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", uploadedById=" + getUploadedById() +
            ", uploadedByName='" + getUploadedByName() + "'" +
            ", uploadedDate='" + getUploadedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", patientDocumentSoMapUuid='" + getPatientDocumentSoMapUuid() + "'" +
            "}";
    }
}
