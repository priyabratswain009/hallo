package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDoctorMap.
 */
@Table("t_patient_doctor_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDoctorMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_doctor_map_id")
    private Long patientDoctorMapId;

    @Column("patient_id")
    private Long patientId;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("status")
    private String status;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("doctor_first_name")
    private String doctorFirstName;

    @Column("doctor_middle_name")
    private String doctorMiddleName;

    @Column("doctor_last_name")
    private String doctorLastName;

    @Column("doctor_name_suffix")
    private String doctorNameSuffix;

    @Column("doctor_address_line_1")
    private String doctorAddressLine1;

    @Column("doctor_address_line_2")
    private String doctorAddressLine2;

    @Column("doctor_address_city")
    private String doctorAddressCity;

    @Column("doctor_address_state")
    private String doctorAddressState;

    @Column("doctor_address_zip")
    private String doctorAddressZip;

    @Column("doctor_contact_1")
    private String doctorContact1;

    @Column("doctor_contact_2")
    private String doctorContact2;

    @Column("doctor_fax")
    private String doctorFax;

    @Column("doctor_npi_number")
    private String doctorNpiNumber;

    @Column("doctor_gender")
    private String doctorGender;

    @Column("doctor_taxonomy_code")
    private String doctorTaxonomyCode;

    @Column("doctor_taxonomy_description")
    private String doctorTaxonomyDescription;

    @Column("doctor_practice_state")
    private String doctorPracticeState;

    @Column("doctor_license_number")
    private String doctorLicenseNumber;

    @Column("patient_doctor_map_uuid")
    private UUID patientDoctorMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientDoctorMapId() {
        return this.patientDoctorMapId;
    }

    public PatientDoctorMap patientDoctorMapId(Long patientDoctorMapId) {
        this.setPatientDoctorMapId(patientDoctorMapId);
        return this;
    }

    public void setPatientDoctorMapId(Long patientDoctorMapId) {
        this.patientDoctorMapId = patientDoctorMapId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientDoctorMap patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientDoctorMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientDoctorMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientDoctorMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientDoctorMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientDoctorMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientDoctorMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientDoctorMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDoctorFirstName() {
        return this.doctorFirstName;
    }

    public PatientDoctorMap doctorFirstName(String doctorFirstName) {
        this.setDoctorFirstName(doctorFirstName);
        return this;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorMiddleName() {
        return this.doctorMiddleName;
    }

    public PatientDoctorMap doctorMiddleName(String doctorMiddleName) {
        this.setDoctorMiddleName(doctorMiddleName);
        return this;
    }

    public void setDoctorMiddleName(String doctorMiddleName) {
        this.doctorMiddleName = doctorMiddleName;
    }

    public String getDoctorLastName() {
        return this.doctorLastName;
    }

    public PatientDoctorMap doctorLastName(String doctorLastName) {
        this.setDoctorLastName(doctorLastName);
        return this;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorNameSuffix() {
        return this.doctorNameSuffix;
    }

    public PatientDoctorMap doctorNameSuffix(String doctorNameSuffix) {
        this.setDoctorNameSuffix(doctorNameSuffix);
        return this;
    }

    public void setDoctorNameSuffix(String doctorNameSuffix) {
        this.doctorNameSuffix = doctorNameSuffix;
    }

    public String getDoctorAddressLine1() {
        return this.doctorAddressLine1;
    }

    public PatientDoctorMap doctorAddressLine1(String doctorAddressLine1) {
        this.setDoctorAddressLine1(doctorAddressLine1);
        return this;
    }

    public void setDoctorAddressLine1(String doctorAddressLine1) {
        this.doctorAddressLine1 = doctorAddressLine1;
    }

    public String getDoctorAddressLine2() {
        return this.doctorAddressLine2;
    }

    public PatientDoctorMap doctorAddressLine2(String doctorAddressLine2) {
        this.setDoctorAddressLine2(doctorAddressLine2);
        return this;
    }

    public void setDoctorAddressLine2(String doctorAddressLine2) {
        this.doctorAddressLine2 = doctorAddressLine2;
    }

    public String getDoctorAddressCity() {
        return this.doctorAddressCity;
    }

    public PatientDoctorMap doctorAddressCity(String doctorAddressCity) {
        this.setDoctorAddressCity(doctorAddressCity);
        return this;
    }

    public void setDoctorAddressCity(String doctorAddressCity) {
        this.doctorAddressCity = doctorAddressCity;
    }

    public String getDoctorAddressState() {
        return this.doctorAddressState;
    }

    public PatientDoctorMap doctorAddressState(String doctorAddressState) {
        this.setDoctorAddressState(doctorAddressState);
        return this;
    }

    public void setDoctorAddressState(String doctorAddressState) {
        this.doctorAddressState = doctorAddressState;
    }

    public String getDoctorAddressZip() {
        return this.doctorAddressZip;
    }

    public PatientDoctorMap doctorAddressZip(String doctorAddressZip) {
        this.setDoctorAddressZip(doctorAddressZip);
        return this;
    }

    public void setDoctorAddressZip(String doctorAddressZip) {
        this.doctorAddressZip = doctorAddressZip;
    }

    public String getDoctorContact1() {
        return this.doctorContact1;
    }

    public PatientDoctorMap doctorContact1(String doctorContact1) {
        this.setDoctorContact1(doctorContact1);
        return this;
    }

    public void setDoctorContact1(String doctorContact1) {
        this.doctorContact1 = doctorContact1;
    }

    public String getDoctorContact2() {
        return this.doctorContact2;
    }

    public PatientDoctorMap doctorContact2(String doctorContact2) {
        this.setDoctorContact2(doctorContact2);
        return this;
    }

    public void setDoctorContact2(String doctorContact2) {
        this.doctorContact2 = doctorContact2;
    }

    public String getDoctorFax() {
        return this.doctorFax;
    }

    public PatientDoctorMap doctorFax(String doctorFax) {
        this.setDoctorFax(doctorFax);
        return this;
    }

    public void setDoctorFax(String doctorFax) {
        this.doctorFax = doctorFax;
    }

    public String getDoctorNpiNumber() {
        return this.doctorNpiNumber;
    }

    public PatientDoctorMap doctorNpiNumber(String doctorNpiNumber) {
        this.setDoctorNpiNumber(doctorNpiNumber);
        return this;
    }

    public void setDoctorNpiNumber(String doctorNpiNumber) {
        this.doctorNpiNumber = doctorNpiNumber;
    }

    public String getDoctorGender() {
        return this.doctorGender;
    }

    public PatientDoctorMap doctorGender(String doctorGender) {
        this.setDoctorGender(doctorGender);
        return this;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorTaxonomyCode() {
        return this.doctorTaxonomyCode;
    }

    public PatientDoctorMap doctorTaxonomyCode(String doctorTaxonomyCode) {
        this.setDoctorTaxonomyCode(doctorTaxonomyCode);
        return this;
    }

    public void setDoctorTaxonomyCode(String doctorTaxonomyCode) {
        this.doctorTaxonomyCode = doctorTaxonomyCode;
    }

    public String getDoctorTaxonomyDescription() {
        return this.doctorTaxonomyDescription;
    }

    public PatientDoctorMap doctorTaxonomyDescription(String doctorTaxonomyDescription) {
        this.setDoctorTaxonomyDescription(doctorTaxonomyDescription);
        return this;
    }

    public void setDoctorTaxonomyDescription(String doctorTaxonomyDescription) {
        this.doctorTaxonomyDescription = doctorTaxonomyDescription;
    }

    public String getDoctorPracticeState() {
        return this.doctorPracticeState;
    }

    public PatientDoctorMap doctorPracticeState(String doctorPracticeState) {
        this.setDoctorPracticeState(doctorPracticeState);
        return this;
    }

    public void setDoctorPracticeState(String doctorPracticeState) {
        this.doctorPracticeState = doctorPracticeState;
    }

    public String getDoctorLicenseNumber() {
        return this.doctorLicenseNumber;
    }

    public PatientDoctorMap doctorLicenseNumber(String doctorLicenseNumber) {
        this.setDoctorLicenseNumber(doctorLicenseNumber);
        return this;
    }

    public void setDoctorLicenseNumber(String doctorLicenseNumber) {
        this.doctorLicenseNumber = doctorLicenseNumber;
    }

    public UUID getPatientDoctorMapUuid() {
        return this.patientDoctorMapUuid;
    }

    public PatientDoctorMap patientDoctorMapUuid(UUID patientDoctorMapUuid) {
        this.setPatientDoctorMapUuid(patientDoctorMapUuid);
        return this;
    }

    public void setPatientDoctorMapUuid(UUID patientDoctorMapUuid) {
        this.patientDoctorMapUuid = patientDoctorMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDoctorMap)) {
            return false;
        }
        return patientDoctorMapId != null && patientDoctorMapId.equals(((PatientDoctorMap) o).patientDoctorMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDoctorMap{" +
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
