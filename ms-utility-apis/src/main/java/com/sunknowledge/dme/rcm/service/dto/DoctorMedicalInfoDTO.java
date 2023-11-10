package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo} entity.
 */
public class DoctorMedicalInfoDTO implements Serializable {

    @NotNull
    private Long medicalInfoId;

    private Long doctorId;

    private String licenceNo;

    private LocalDate licExperiationDate;

    private String dea;

    private String upin;

    private String stateMedicaidId;

    private String npi;

    private String commericialNo;

    private Long locationId;

    private String taxonomyCode;

    private String pecosCertifiedStatus;

    private String user1;

    private String user2;

    private String notes;

    private String taxonomyGroup;

    private String taxonomyDesc;

    private String practiceState;

    private String primaryTaxonomy;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID doctorMedicalInfoUuid;

    public Long getMedicalInfoId() {
        return medicalInfoId;
    }

    public void setMedicalInfoId(Long medicalInfoId) {
        this.medicalInfoId = medicalInfoId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public LocalDate getLicExperiationDate() {
        return licExperiationDate;
    }

    public void setLicExperiationDate(LocalDate licExperiationDate) {
        this.licExperiationDate = licExperiationDate;
    }

    public String getDea() {
        return dea;
    }

    public void setDea(String dea) {
        this.dea = dea;
    }

    public String getUpin() {
        return upin;
    }

    public void setUpin(String upin) {
        this.upin = upin;
    }

    public String getStateMedicaidId() {
        return stateMedicaidId;
    }

    public void setStateMedicaidId(String stateMedicaidId) {
        this.stateMedicaidId = stateMedicaidId;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getCommericialNo() {
        return commericialNo;
    }

    public void setCommericialNo(String commericialNo) {
        this.commericialNo = commericialNo;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getPecosCertifiedStatus() {
        return pecosCertifiedStatus;
    }

    public void setPecosCertifiedStatus(String pecosCertifiedStatus) {
        this.pecosCertifiedStatus = pecosCertifiedStatus;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTaxonomyGroup() {
        return taxonomyGroup;
    }

    public void setTaxonomyGroup(String taxonomyGroup) {
        this.taxonomyGroup = taxonomyGroup;
    }

    public String getTaxonomyDesc() {
        return taxonomyDesc;
    }

    public void setTaxonomyDesc(String taxonomyDesc) {
        this.taxonomyDesc = taxonomyDesc;
    }

    public String getPracticeState() {
        return practiceState;
    }

    public void setPracticeState(String practiceState) {
        this.practiceState = practiceState;
    }

    public String getPrimaryTaxonomy() {
        return primaryTaxonomy;
    }

    public void setPrimaryTaxonomy(String primaryTaxonomy) {
        this.primaryTaxonomy = primaryTaxonomy;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDoctorMedicalInfoUuid() {
        return doctorMedicalInfoUuid;
    }

    public void setDoctorMedicalInfoUuid(UUID doctorMedicalInfoUuid) {
        this.doctorMedicalInfoUuid = doctorMedicalInfoUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorMedicalInfoDTO)) {
            return false;
        }

        DoctorMedicalInfoDTO doctorMedicalInfoDTO = (DoctorMedicalInfoDTO) o;
        if (this.medicalInfoId == null) {
            return false;
        }
        return Objects.equals(this.medicalInfoId, doctorMedicalInfoDTO.medicalInfoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.medicalInfoId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoctorMedicalInfoDTO{" +
            "medicalInfoId=" + getMedicalInfoId() +
            ", doctorId=" + getDoctorId() +
            ", licenceNo='" + getLicenceNo() + "'" +
            ", licExperiationDate='" + getLicExperiationDate() + "'" +
            ", dea='" + getDea() + "'" +
            ", upin='" + getUpin() + "'" +
            ", stateMedicaidId='" + getStateMedicaidId() + "'" +
            ", npi='" + getNpi() + "'" +
            ", commericialNo='" + getCommericialNo() + "'" +
            ", locationId=" + getLocationId() +
            ", taxonomyCode='" + getTaxonomyCode() + "'" +
            ", pecosCertifiedStatus='" + getPecosCertifiedStatus() + "'" +
            ", user1='" + getUser1() + "'" +
            ", user2='" + getUser2() + "'" +
            ", notes='" + getNotes() + "'" +
            ", taxonomyGroup='" + getTaxonomyGroup() + "'" +
            ", taxonomyDesc='" + getTaxonomyDesc() + "'" +
            ", practiceState='" + getPracticeState() + "'" +
            ", primaryTaxonomy='" + getPrimaryTaxonomy() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", doctorMedicalInfoUuid='" + getDoctorMedicalInfoUuid() + "'" +
            "}";
    }
}
