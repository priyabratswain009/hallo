package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientDocument} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDocumentDTO implements Serializable {

    private Long patientDocumentId;

    private String patientDocumentNo;

    private Long patientId;

    private UUID patientUuid;

    private String patientIdNo;

    private String patientDocumentType;

    private String patientDocumentRepositoryName;

    private String patientDocumentName;

    private String patientDocumentDescription;

    private String patientDocumentStatus;

    private Long uploadedById;

    private String uploadedByName;

    private LocalDate uploadedDate;

    private String status;

    private UUID patientDocumentUuid;

    public Long getPatientDocumentId() {
        return patientDocumentId;
    }

    public void setPatientDocumentId(Long patientDocumentId) {
        this.patientDocumentId = patientDocumentId;
    }

    public String getPatientDocumentNo() {
        return patientDocumentNo;
    }

    public void setPatientDocumentNo(String patientDocumentNo) {
        this.patientDocumentNo = patientDocumentNo;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public UUID getPatientUuid() {
        return patientUuid;
    }

    public void setPatientUuid(UUID patientUuid) {
        this.patientUuid = patientUuid;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPatientDocumentType() {
        return patientDocumentType;
    }

    public void setPatientDocumentType(String patientDocumentType) {
        this.patientDocumentType = patientDocumentType;
    }

    public String getPatientDocumentRepositoryName() {
        return patientDocumentRepositoryName;
    }

    public void setPatientDocumentRepositoryName(String patientDocumentRepositoryName) {
        this.patientDocumentRepositoryName = patientDocumentRepositoryName;
    }

    public String getPatientDocumentName() {
        return patientDocumentName;
    }

    public void setPatientDocumentName(String patientDocumentName) {
        this.patientDocumentName = patientDocumentName;
    }

    public String getPatientDocumentDescription() {
        return patientDocumentDescription;
    }

    public void setPatientDocumentDescription(String patientDocumentDescription) {
        this.patientDocumentDescription = patientDocumentDescription;
    }

    public String getPatientDocumentStatus() {
        return patientDocumentStatus;
    }

    public void setPatientDocumentStatus(String patientDocumentStatus) {
        this.patientDocumentStatus = patientDocumentStatus;
    }

    public Long getUploadedById() {
        return uploadedById;
    }

    public void setUploadedById(Long uploadedById) {
        this.uploadedById = uploadedById;
    }

    public String getUploadedByName() {
        return uploadedByName;
    }

    public void setUploadedByName(String uploadedByName) {
        this.uploadedByName = uploadedByName;
    }

    public LocalDate getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getPatientDocumentUuid() {
        return patientDocumentUuid;
    }

    public void setPatientDocumentUuid(UUID patientDocumentUuid) {
        this.patientDocumentUuid = patientDocumentUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDocumentDTO)) {
            return false;
        }

        PatientDocumentDTO patientDocumentDTO = (PatientDocumentDTO) o;
        if (this.patientDocumentId == null) {
            return false;
        }
        return Objects.equals(this.patientDocumentId, patientDocumentDTO.patientDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientDocumentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDocumentDTO{" +
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
