package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientClinicalInformation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientClinicalInformationDTO implements Serializable {

    private Long patientClinicalInformationId;

    private Double height;

    private Double weight;

    private String functionalAbilities;

    private LocalDate captureDate;

    private String infectionConditionStatus;

    private String diabetesStatus;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long patientId;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID patientClinicalInformationUuid;

    public Long getPatientClinicalInformationId() {
        return patientClinicalInformationId;
    }

    public void setPatientClinicalInformationId(Long patientClinicalInformationId) {
        this.patientClinicalInformationId = patientClinicalInformationId;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFunctionalAbilities() {
        return functionalAbilities;
    }

    public void setFunctionalAbilities(String functionalAbilities) {
        this.functionalAbilities = functionalAbilities;
    }

    public LocalDate getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }

    public String getInfectionConditionStatus() {
        return infectionConditionStatus;
    }

    public void setInfectionConditionStatus(String infectionConditionStatus) {
        this.infectionConditionStatus = infectionConditionStatus;
    }

    public String getDiabetesStatus() {
        return diabetesStatus;
    }

    public void setDiabetesStatus(String diabetesStatus) {
        this.diabetesStatus = diabetesStatus;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public UUID getPatientClinicalInformationUuid() {
        return patientClinicalInformationUuid;
    }

    public void setPatientClinicalInformationUuid(UUID patientClinicalInformationUuid) {
        this.patientClinicalInformationUuid = patientClinicalInformationUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientClinicalInformationDTO)) {
            return false;
        }

        PatientClinicalInformationDTO patientClinicalInformationDTO = (PatientClinicalInformationDTO) o;
        if (this.patientClinicalInformationId == null) {
            return false;
        }
        return Objects.equals(this.patientClinicalInformationId, patientClinicalInformationDTO.patientClinicalInformationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientClinicalInformationId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientClinicalInformationDTO{" +
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
