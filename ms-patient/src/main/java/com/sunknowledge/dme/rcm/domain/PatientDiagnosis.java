package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDiagnosis.
 */
@Table("t_patient_diagnosis")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDiagnosis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_diagnosis_id")
    private Long patientDiagnosisId;

    @Column("patient_id")
    private Long patientId;

    @Column("diagnosis_code_type")
    private String diagnosisCodeType;

    @Column("diagnosis_code")
    private String diagnosisCode;

    @Column("diagnosis_description")
    private String diagnosisDescription;

    @Column("effective_date")
    private LocalDate effectiveDate;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("patient_diagnosis_uuid")
    private UUID patientDiagnosisUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientDiagnosisId() {
        return this.patientDiagnosisId;
    }

    public PatientDiagnosis patientDiagnosisId(Long patientDiagnosisId) {
        this.setPatientDiagnosisId(patientDiagnosisId);
        return this;
    }

    public void setPatientDiagnosisId(Long patientDiagnosisId) {
        this.patientDiagnosisId = patientDiagnosisId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientDiagnosis patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public PatientDiagnosis diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public String getDiagnosisCode() {
        return this.diagnosisCode;
    }

    public PatientDiagnosis diagnosisCode(String diagnosisCode) {
        this.setDiagnosisCode(diagnosisCode);
        return this;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getDiagnosisDescription() {
        return this.diagnosisDescription;
    }

    public PatientDiagnosis diagnosisDescription(String diagnosisDescription) {
        this.setDiagnosisDescription(diagnosisDescription);
        return this;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public LocalDate getEffectiveDate() {
        return this.effectiveDate;
    }

    public PatientDiagnosis effectiveDate(LocalDate effectiveDate) {
        this.setEffectiveDate(effectiveDate);
        return this;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientDiagnosis status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientDiagnosis createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientDiagnosis createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientDiagnosis createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientDiagnosis updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientDiagnosis updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientDiagnosis updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPatientDiagnosisUuid() {
        return this.patientDiagnosisUuid;
    }

    public PatientDiagnosis patientDiagnosisUuid(UUID patientDiagnosisUuid) {
        this.setPatientDiagnosisUuid(patientDiagnosisUuid);
        return this;
    }

    public void setPatientDiagnosisUuid(UUID patientDiagnosisUuid) {
        this.patientDiagnosisUuid = patientDiagnosisUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDiagnosis)) {
            return false;
        }
        return patientDiagnosisId != null && patientDiagnosisId.equals(((PatientDiagnosis) o).patientDiagnosisId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDiagnosis{" +
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
