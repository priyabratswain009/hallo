package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientDoctorMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDoctorMapDTO implements Serializable {

    private Long patientDoctorMapId;

    private Long patientId;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private String doctorFirstName;

    private String doctorMiddleName;

    private String doctorLastName;

    private String doctorNameSuffix;

    private String doctorAddressLine1;

    private String doctorAddressLine2;

    private String doctorAddressCity;

    private String doctorAddressState;

    private String doctorAddressZip;

    private String doctorContact1;

    private String doctorContact2;

    private String doctorFax;

    private String doctorNpiNumber;

    private String doctorGender;

    private String doctorTaxonomyCode;

    private String doctorTaxonomyDescription;

    private String doctorPracticeState;

    private String doctorLicenseNumber;

    private UUID patientDoctorMapUuid;

    public Long getPatientDoctorMapId() {
        return patientDoctorMapId;
    }

    public void setPatientDoctorMapId(Long patientDoctorMapId) {
        this.patientDoctorMapId = patientDoctorMapId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorMiddleName() {
        return doctorMiddleName;
    }

    public void setDoctorMiddleName(String doctorMiddleName) {
        this.doctorMiddleName = doctorMiddleName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorNameSuffix() {
        return doctorNameSuffix;
    }

    public void setDoctorNameSuffix(String doctorNameSuffix) {
        this.doctorNameSuffix = doctorNameSuffix;
    }

    public String getDoctorAddressLine1() {
        return doctorAddressLine1;
    }

    public void setDoctorAddressLine1(String doctorAddressLine1) {
        this.doctorAddressLine1 = doctorAddressLine1;
    }

    public String getDoctorAddressLine2() {
        return doctorAddressLine2;
    }

    public void setDoctorAddressLine2(String doctorAddressLine2) {
        this.doctorAddressLine2 = doctorAddressLine2;
    }

    public String getDoctorAddressCity() {
        return doctorAddressCity;
    }

    public void setDoctorAddressCity(String doctorAddressCity) {
        this.doctorAddressCity = doctorAddressCity;
    }

    public String getDoctorAddressState() {
        return doctorAddressState;
    }

    public void setDoctorAddressState(String doctorAddressState) {
        this.doctorAddressState = doctorAddressState;
    }

    public String getDoctorAddressZip() {
        return doctorAddressZip;
    }

    public void setDoctorAddressZip(String doctorAddressZip) {
        this.doctorAddressZip = doctorAddressZip;
    }

    public String getDoctorContact1() {
        return doctorContact1;
    }

    public void setDoctorContact1(String doctorContact1) {
        this.doctorContact1 = doctorContact1;
    }

    public String getDoctorContact2() {
        return doctorContact2;
    }

    public void setDoctorContact2(String doctorContact2) {
        this.doctorContact2 = doctorContact2;
    }

    public String getDoctorFax() {
        return doctorFax;
    }

    public void setDoctorFax(String doctorFax) {
        this.doctorFax = doctorFax;
    }

    public String getDoctorNpiNumber() {
        return doctorNpiNumber;
    }

    public void setDoctorNpiNumber(String doctorNpiNumber) {
        this.doctorNpiNumber = doctorNpiNumber;
    }

    public String getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorTaxonomyCode() {
        return doctorTaxonomyCode;
    }

    public void setDoctorTaxonomyCode(String doctorTaxonomyCode) {
        this.doctorTaxonomyCode = doctorTaxonomyCode;
    }

    public String getDoctorTaxonomyDescription() {
        return doctorTaxonomyDescription;
    }

    public void setDoctorTaxonomyDescription(String doctorTaxonomyDescription) {
        this.doctorTaxonomyDescription = doctorTaxonomyDescription;
    }

    public String getDoctorPracticeState() {
        return doctorPracticeState;
    }

    public void setDoctorPracticeState(String doctorPracticeState) {
        this.doctorPracticeState = doctorPracticeState;
    }

    public String getDoctorLicenseNumber() {
        return doctorLicenseNumber;
    }

    public void setDoctorLicenseNumber(String doctorLicenseNumber) {
        this.doctorLicenseNumber = doctorLicenseNumber;
    }

    public UUID getPatientDoctorMapUuid() {
        return patientDoctorMapUuid;
    }

    public void setPatientDoctorMapUuid(UUID patientDoctorMapUuid) {
        this.patientDoctorMapUuid = patientDoctorMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDoctorMapDTO)) {
            return false;
        }

        PatientDoctorMapDTO patientDoctorMapDTO = (PatientDoctorMapDTO) o;
        if (this.patientDoctorMapId == null) {
            return false;
        }
        return Objects.equals(this.patientDoctorMapId, patientDoctorMapDTO.patientDoctorMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientDoctorMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDoctorMapDTO{" +
            "patientDoctorMapId=" + getPatientDoctorMapId() +
            ", patientId=" + getPatientId() +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", doctorFirstName='" + getDoctorFirstName() + "'" +
            ", doctorMiddleName='" + getDoctorMiddleName() + "'" +
            ", doctorLastName='" + getDoctorLastName() + "'" +
            ", doctorNameSuffix='" + getDoctorNameSuffix() + "'" +
            ", doctorAddressLine1='" + getDoctorAddressLine1() + "'" +
            ", doctorAddressLine2='" + getDoctorAddressLine2() + "'" +
            ", doctorAddressCity='" + getDoctorAddressCity() + "'" +
            ", doctorAddressState='" + getDoctorAddressState() + "'" +
            ", doctorAddressZip='" + getDoctorAddressZip() + "'" +
            ", doctorContact1='" + getDoctorContact1() + "'" +
            ", doctorContact2='" + getDoctorContact2() + "'" +
            ", doctorFax='" + getDoctorFax() + "'" +
            ", doctorNpiNumber='" + getDoctorNpiNumber() + "'" +
            ", doctorGender='" + getDoctorGender() + "'" +
            ", doctorTaxonomyCode='" + getDoctorTaxonomyCode() + "'" +
            ", doctorTaxonomyDescription='" + getDoctorTaxonomyDescription() + "'" +
            ", doctorPracticeState='" + getDoctorPracticeState() + "'" +
            ", doctorLicenseNumber='" + getDoctorLicenseNumber() + "'" +
            ", patientDoctorMapUuid='" + getPatientDoctorMapUuid() + "'" +
            "}";
    }
}
