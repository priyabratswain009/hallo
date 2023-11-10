package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientClinicalInformation.
 */
@Table("t_patient_clinical_information")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientClinicalInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_clinical_information_id")
    private Long patientClinicalInformationId;

    @Column("height")
    private Double height;

    @Column("weight")
    private Double weight;

    @Column("functional_abilities")
    private String functionalAbilities;

    @Column("capture_date")
    private LocalDate captureDate;

    @Column("infection_condition_status")
    private String infectionConditionStatus;

    @Column("diabetes_status")
    private String diabetesStatus;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("patient_id")
    private Long patientId;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("patient_clinical_information_uuid")
    private UUID patientClinicalInformationUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientClinicalInformationId() {
        return this.patientClinicalInformationId;
    }

    public PatientClinicalInformation patientClinicalInformationId(Long patientClinicalInformationId) {
        this.setPatientClinicalInformationId(patientClinicalInformationId);
        return this;
    }

    public void setPatientClinicalInformationId(Long patientClinicalInformationId) {
        this.patientClinicalInformationId = patientClinicalInformationId;
    }

    public Double getHeight() {
        return this.height;
    }

    public PatientClinicalInformation height(Double height) {
        this.setHeight(height);
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return this.weight;
    }

    public PatientClinicalInformation weight(Double weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFunctionalAbilities() {
        return this.functionalAbilities;
    }

    public PatientClinicalInformation functionalAbilities(String functionalAbilities) {
        this.setFunctionalAbilities(functionalAbilities);
        return this;
    }

    public void setFunctionalAbilities(String functionalAbilities) {
        this.functionalAbilities = functionalAbilities;
    }

    public LocalDate getCaptureDate() {
        return this.captureDate;
    }

    public PatientClinicalInformation captureDate(LocalDate captureDate) {
        this.setCaptureDate(captureDate);
        return this;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }

    public String getInfectionConditionStatus() {
        return this.infectionConditionStatus;
    }

    public PatientClinicalInformation infectionConditionStatus(String infectionConditionStatus) {
        this.setInfectionConditionStatus(infectionConditionStatus);
        return this;
    }

    public void setInfectionConditionStatus(String infectionConditionStatus) {
        this.infectionConditionStatus = infectionConditionStatus;
    }

    public String getDiabetesStatus() {
        return this.diabetesStatus;
    }

    public PatientClinicalInformation diabetesStatus(String diabetesStatus) {
        this.setDiabetesStatus(diabetesStatus);
        return this;
    }

    public void setDiabetesStatus(String diabetesStatus) {
        this.diabetesStatus = diabetesStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientClinicalInformation status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientClinicalInformation createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientClinicalInformation createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientClinicalInformation patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientClinicalInformation createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientClinicalInformation updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientClinicalInformation updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientClinicalInformation updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPatientClinicalInformationUuid() {
        return this.patientClinicalInformationUuid;
    }

    public PatientClinicalInformation patientClinicalInformationUuid(UUID patientClinicalInformationUuid) {
        this.setPatientClinicalInformationUuid(patientClinicalInformationUuid);
        return this;
    }

    public void setPatientClinicalInformationUuid(UUID patientClinicalInformationUuid) {
        this.patientClinicalInformationUuid = patientClinicalInformationUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientClinicalInformation)) {
            return false;
        }
        return (
            patientClinicalInformationId != null &&
            patientClinicalInformationId.equals(((PatientClinicalInformation) o).patientClinicalInformationId)
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
        return "PatientClinicalInformation{" +
            "patientClinicalInformationId=" + getPatientClinicalInformationId() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", functionalAbilities='" + getFunctionalAbilities() + "'" +
            ", captureDate='" + getCaptureDate() + "'" +
            ", infectionConditionStatus='" + getInfectionConditionStatus() + "'" +
            ", diabetesStatus='" + getDiabetesStatus() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", patientId=" + getPatientId() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", patientClinicalInformationUuid='" + getPatientClinicalInformationUuid() + "'" +
            "}";
    }
}
