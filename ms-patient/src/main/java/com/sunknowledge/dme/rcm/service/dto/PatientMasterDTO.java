package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientMasterDTO implements Serializable {

    private Long patientId;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private LocalDate dob;

    private String gender;

    private String ssn;

    private Long taxZoneId;

    private Double discountPercent;

    private Long posId;

    private String priorSystemKey;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long branchId;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID patientMasterUUID;

    private String patientIdNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zip;

    private String contactNo1;

    private String contactNo2;

    private String fax;

    private String efax;

    private String email;

    private String modeOfContact;

    private LocalDate updatedDate;

    private String branchName;

    private String billingAddressLine1;

    private String billingAddressLine2;

    private String billingAddressCity;

    private String billingAddressState;

    private String billingAddressZip;

    private String caregiverName;

    private String caregiverContact;

    private String caregiverRelatinshipPatient;

    private String taxZoneName;

    private Double taxRate;

    private LocalDate patientDod;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getTaxZoneId() {
        return taxZoneId;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getPriorSystemKey() {
        return priorSystemKey;
    }

    public void setPriorSystemKey(String priorSystemKey) {
        this.priorSystemKey = priorSystemKey;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public UUID getPatientMasterUUID() {
        return patientMasterUUID;
    }

    public void setPatientMasterUUID(UUID patientMasterUUID) {
        this.patientMasterUUID = patientMasterUUID;
    }

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactNo1() {
        return contactNo1;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return contactNo2;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return efax;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModeOfContact() {
        return modeOfContact;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZip() {
        return billingAddressZip;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }

    public String getCaregiverName() {
        return caregiverName;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getCaregiverContact() {
        return caregiverContact;
    }

    public void setCaregiverContact(String caregiverContact) {
        this.caregiverContact = caregiverContact;
    }

    public String getCaregiverRelatinshipPatient() {
        return caregiverRelatinshipPatient;
    }

    public void setCaregiverRelatinshipPatient(String caregiverRelatinshipPatient) {
        this.caregiverRelatinshipPatient = caregiverRelatinshipPatient;
    }

    public String getTaxZoneName() {
        return taxZoneName;
    }

    public void setTaxZoneName(String taxZoneName) {
        this.taxZoneName = taxZoneName;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public LocalDate getPatientDod() {
        return patientDod;
    }

    public void setPatientDod(LocalDate patientDod) {
        this.patientDod = patientDod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientMasterDTO)) {
            return false;
        }

        PatientMasterDTO patientMasterDTO = (PatientMasterDTO) o;
        if (this.patientId == null) {
            return false;
        }
        return Objects.equals(this.patientId, patientMasterDTO.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientMasterDTO{" +
            "patientId=" + getPatientId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", dob='" + getDob() + "'" +
            ", gender='" + getGender() + "'" +
            ", ssn='" + getSsn() + "'" +
            ", taxZoneId=" + getTaxZoneId() +
            ", discountPercent=" + getDiscountPercent() +
            ", posId=" + getPosId() +
            ", priorSystemKey='" + getPriorSystemKey() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", branchId=" + getBranchId() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", patientMasterUUID='" + getPatientMasterUUID() + "'" +
            ", patientIdNumber='" + getPatientIdNumber() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", zip='" + getZip() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", fax='" + getFax() + "'" +
            ", efax='" + getEfax() + "'" +
            ", email='" + getEmail() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", branchName='" + getBranchName() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingAddressCity='" + getBillingAddressCity() + "'" +
            ", billingAddressState='" + getBillingAddressState() + "'" +
            ", billingAddressZip='" + getBillingAddressZip() + "'" +
            ", caregiverName='" + getCaregiverName() + "'" +
            ", caregiverContact='" + getCaregiverContact() + "'" +
            ", caregiverRelatinshipPatient='" + getCaregiverRelatinshipPatient() + "'" +
            ", taxZoneName='" + getTaxZoneName() + "'" +
            ", taxRate=" + getTaxRate() +
            ", patientDod='" + getPatientDod() + "'" +
            "}";
    }
}
