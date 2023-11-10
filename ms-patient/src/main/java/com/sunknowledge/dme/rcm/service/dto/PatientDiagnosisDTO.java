package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientDiagnosis} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDiagnosisDTO implements Serializable {

    private Long patientDiagnosisId;

    private Long patientId;

    private String diagnosisCodeType;

    private String diagnosisCode;

    private String diagnosisDescription;

    private LocalDate effectiveDate;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID patientDiagnosisUuid;

    public Long getPatientDiagnosisId() {
        return patientDiagnosisId;
    }

    public void setPatientDiagnosisId(Long patientDiagnosisId) {
        this.patientDiagnosisId = patientDiagnosisId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosisCodeType() {
        return diagnosisCodeType;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPatientDiagnosisUuid() {
        return patientDiagnosisUuid;
    }

    public void setPatientDiagnosisUuid(UUID patientDiagnosisUuid) {
        this.patientDiagnosisUuid = patientDiagnosisUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDiagnosisDTO)) {
            return false;
        }

        PatientDiagnosisDTO patientDiagnosisDTO = (PatientDiagnosisDTO) o;
        if (this.patientDiagnosisId == null) {
            return false;
        }
        return Objects.equals(this.patientDiagnosisId, patientDiagnosisDTO.patientDiagnosisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientDiagnosisId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDiagnosisDTO{" +
            "patientDiagnosisId=" + getPatientDiagnosisId() +
            ", patientId=" + getPatientId() +
            ", diagnosisCodeType='" + getDiagnosisCodeType() + "'" +
            ", diagnosisCode='" + getDiagnosisCode() + "'" +
            ", diagnosisDescription='" + getDiagnosisDescription() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", patientDiagnosisUuid='" + getPatientDiagnosisUuid() + "'" +
            "}";
    }
}
