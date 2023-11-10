package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DoctorMedicalInfo.
 */
@Entity
@Table(name = "t_doctor_medical_info")
public class DoctorMedicalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "medical_info_id", nullable = false)
    private Long medicalInfoId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "licence_no")
    private String licenceNo;

    @Column(name = "lic_experiation_date")
    private LocalDate licExperiationDate;

    @Column(name = "dea")
    private String dea;

    @Column(name = "upin")
    private String upin;

    @Column(name = "state_medicaid_id")
    private String stateMedicaidId;

    @Column(name = "npi")
    private String npi;

    @Column(name = "commericial_no")
    private String commericialNo;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "taxonomy_code")
    private String taxonomyCode;

    @Column(name = "pecos_certified_status")
    private String pecosCertifiedStatus;

    @Column(name = "user_1")
    private String user1;

    @Column(name = "user_2")
    private String user2;

    @Column(name = "notes")
    private String notes;

    @Column(name = "taxonomy_group")
    private String taxonomyGroup;

    @Column(name = "taxonomy_desc")
    private String taxonomyDesc;

    @Column(name = "practice_state")
    private String practiceState;

    @Column(name = "primary_taxonomy")
    private String primaryTaxonomy;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "doctor_medical_info_uuid")
    private UUID doctorMedicalInfoUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMedicalInfoId() {
        return this.medicalInfoId;
    }

    public DoctorMedicalInfo medicalInfoId(Long medicalInfoId) {
        this.setMedicalInfoId(medicalInfoId);
        return this;
    }

    public void setMedicalInfoId(Long medicalInfoId) {
        this.medicalInfoId = medicalInfoId;
    }

    public Long getDoctorId() {
        return this.doctorId;
    }

    public DoctorMedicalInfo doctorId(Long doctorId) {
        this.setDoctorId(doctorId);
        return this;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getLicenceNo() {
        return this.licenceNo;
    }

    public DoctorMedicalInfo licenceNo(String licenceNo) {
        this.setLicenceNo(licenceNo);
        return this;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public LocalDate getLicExperiationDate() {
        return this.licExperiationDate;
    }

    public DoctorMedicalInfo licExperiationDate(LocalDate licExperiationDate) {
        this.setLicExperiationDate(licExperiationDate);
        return this;
    }

    public void setLicExperiationDate(LocalDate licExperiationDate) {
        this.licExperiationDate = licExperiationDate;
    }

    public String getDea() {
        return this.dea;
    }

    public DoctorMedicalInfo dea(String dea) {
        this.setDea(dea);
        return this;
    }

    public void setDea(String dea) {
        this.dea = dea;
    }

    public String getUpin() {
        return this.upin;
    }

    public DoctorMedicalInfo upin(String upin) {
        this.setUpin(upin);
        return this;
    }

    public void setUpin(String upin) {
        this.upin = upin;
    }

    public String getStateMedicaidId() {
        return this.stateMedicaidId;
    }

    public DoctorMedicalInfo stateMedicaidId(String stateMedicaidId) {
        this.setStateMedicaidId(stateMedicaidId);
        return this;
    }

    public void setStateMedicaidId(String stateMedicaidId) {
        this.stateMedicaidId = stateMedicaidId;
    }

    public String getNpi() {
        return this.npi;
    }

    public DoctorMedicalInfo npi(String npi) {
        this.setNpi(npi);
        return this;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getCommericialNo() {
        return this.commericialNo;
    }

    public DoctorMedicalInfo commericialNo(String commericialNo) {
        this.setCommericialNo(commericialNo);
        return this;
    }

    public void setCommericialNo(String commericialNo) {
        this.commericialNo = commericialNo;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public DoctorMedicalInfo locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getTaxonomyCode() {
        return this.taxonomyCode;
    }

    public DoctorMedicalInfo taxonomyCode(String taxonomyCode) {
        this.setTaxonomyCode(taxonomyCode);
        return this;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getPecosCertifiedStatus() {
        return this.pecosCertifiedStatus;
    }

    public DoctorMedicalInfo pecosCertifiedStatus(String pecosCertifiedStatus) {
        this.setPecosCertifiedStatus(pecosCertifiedStatus);
        return this;
    }

    public void setPecosCertifiedStatus(String pecosCertifiedStatus) {
        this.pecosCertifiedStatus = pecosCertifiedStatus;
    }

    public String getUser1() {
        return this.user1;
    }

    public DoctorMedicalInfo user1(String user1) {
        this.setUser1(user1);
        return this;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return this.user2;
    }

    public DoctorMedicalInfo user2(String user2) {
        this.setUser2(user2);
        return this;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getNotes() {
        return this.notes;
    }

    public DoctorMedicalInfo notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTaxonomyGroup() {
        return this.taxonomyGroup;
    }

    public DoctorMedicalInfo taxonomyGroup(String taxonomyGroup) {
        this.setTaxonomyGroup(taxonomyGroup);
        return this;
    }

    public void setTaxonomyGroup(String taxonomyGroup) {
        this.taxonomyGroup = taxonomyGroup;
    }

    public String getTaxonomyDesc() {
        return this.taxonomyDesc;
    }

    public DoctorMedicalInfo taxonomyDesc(String taxonomyDesc) {
        this.setTaxonomyDesc(taxonomyDesc);
        return this;
    }

    public void setTaxonomyDesc(String taxonomyDesc) {
        this.taxonomyDesc = taxonomyDesc;
    }

    public String getPracticeState() {
        return this.practiceState;
    }

    public DoctorMedicalInfo practiceState(String practiceState) {
        this.setPracticeState(practiceState);
        return this;
    }

    public void setPracticeState(String practiceState) {
        this.practiceState = practiceState;
    }

    public String getPrimaryTaxonomy() {
        return this.primaryTaxonomy;
    }

    public DoctorMedicalInfo primaryTaxonomy(String primaryTaxonomy) {
        this.setPrimaryTaxonomy(primaryTaxonomy);
        return this;
    }

    public void setPrimaryTaxonomy(String primaryTaxonomy) {
        this.primaryTaxonomy = primaryTaxonomy;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DoctorMedicalInfo createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DoctorMedicalInfo createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DoctorMedicalInfo createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DoctorMedicalInfo updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DoctorMedicalInfo updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DoctorMedicalInfo updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDoctorMedicalInfoUuid() {
        return this.doctorMedicalInfoUuid;
    }

    public DoctorMedicalInfo doctorMedicalInfoUuid(UUID doctorMedicalInfoUuid) {
        this.setDoctorMedicalInfoUuid(doctorMedicalInfoUuid);
        return this;
    }

    public void setDoctorMedicalInfoUuid(UUID doctorMedicalInfoUuid) {
        this.doctorMedicalInfoUuid = doctorMedicalInfoUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorMedicalInfo)) {
            return false;
        }
        return medicalInfoId != null && medicalInfoId.equals(((DoctorMedicalInfo) o).medicalInfoId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoctorMedicalInfo{" +
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
