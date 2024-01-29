package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDocumentSoMapDTO implements Serializable {

    private Long patientDocumentSoMapId;

    private Long patientId;

    private String patientIdNo;

    private Long patientDocumentId;

    private String patientDocumentNo;

    private Long soId;

    private String soNo;

    private Long uploadedById;

    private String uploadedByName;

    private LocalDate uploadedDate;

    private String status;

    private UUID patientDocumentSoMapUuid;

    public Long getPatientDocumentSoMapId() {
        return patientDocumentSoMapId;
    }

    public void setPatientDocumentSoMapId(Long patientDocumentSoMapId) {
        this.patientDocumentSoMapId = patientDocumentSoMapId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

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

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
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

    public UUID getPatientDocumentSoMapUuid() {
        return patientDocumentSoMapUuid;
    }

    public void setPatientDocumentSoMapUuid(UUID patientDocumentSoMapUuid) {
        this.patientDocumentSoMapUuid = patientDocumentSoMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDocumentSoMapDTO)) {
            return false;
        }

        PatientDocumentSoMapDTO patientDocumentSoMapDTO = (PatientDocumentSoMapDTO) o;
        if (this.patientDocumentSoMapId == null) {
            return false;
        }
        return Objects.equals(this.patientDocumentSoMapId, patientDocumentSoMapDTO.patientDocumentSoMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientDocumentSoMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDocumentSoMapDTO{" +
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
