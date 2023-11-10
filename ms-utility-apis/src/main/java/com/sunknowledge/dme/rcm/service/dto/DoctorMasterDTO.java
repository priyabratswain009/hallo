package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DoctorMaster} entity.
 */
public class DoctorMasterDTO implements Serializable {

    @NotNull
    private Long doctorId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String title;

    private String suffix;

    private Long doctorSpecialityId;

    private String addlIdentifier;

    private UUID doctorMasterUuid;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String countryName;

    private String zip;

    private String contactNo1;

    private String contactNo2;

    private String faxNumber;

    private String efax;

    private String email;

    private String relationship;

    private String modeOfContact;

    private String npiNumber;

    private String gender;

    private LocalDate enumerationDate;

    private String countryCode;

    private String addressPurpose;

    private String addressType;

    private String postalCode;

    private String taxonomyCode;

    private String taxonomyGroup;

    private String taxonomyDesc;

    private String practiceState;

    private String licenceNo;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getDoctorSpecialityId() {
        return doctorSpecialityId;
    }

    public void setDoctorSpecialityId(Long doctorSpecialityId) {
        this.doctorSpecialityId = doctorSpecialityId;
    }

    public String getAddlIdentifier() {
        return addlIdentifier;
    }

    public void setAddlIdentifier(String addlIdentifier) {
        this.addlIdentifier = addlIdentifier;
    }

    public UUID getDoctorMasterUuid() {
        return doctorMasterUuid;
    }

    public void setDoctorMasterUuid(UUID doctorMasterUuid) {
        this.doctorMasterUuid = doctorMasterUuid;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return modeOfContact;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(String npiNumber) {
        this.npiNumber = npiNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getEnumerationDate() {
        return enumerationDate;
    }

    public void setEnumerationDate(LocalDate enumerationDate) {
        this.enumerationDate = enumerationDate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddressPurpose() {
        return addressPurpose;
    }

    public void setAddressPurpose(String addressPurpose) {
        this.addressPurpose = addressPurpose;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
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

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorMasterDTO)) {
            return false;
        }

        DoctorMasterDTO doctorMasterDTO = (DoctorMasterDTO) o;
        if (this.doctorId == null) {
            return false;
        }
        return Objects.equals(this.doctorId, doctorMasterDTO.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.doctorId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoctorMasterDTO{" +
            "doctorId=" + getDoctorId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", title='" + getTitle() + "'" +
            ", suffix='" + getSuffix() + "'" +
            ", doctorSpecialityId=" + getDoctorSpecialityId() +
            ", addlIdentifier='" + getAddlIdentifier() + "'" +
            ", doctorMasterUuid='" + getDoctorMasterUuid() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", countryName='" + getCountryName() + "'" +
            ", zip='" + getZip() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", faxNumber='" + getFaxNumber() + "'" +
            ", efax='" + getEfax() + "'" +
            ", email='" + getEmail() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", npiNumber='" + getNpiNumber() + "'" +
            ", gender='" + getGender() + "'" +
            ", enumerationDate='" + getEnumerationDate() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", addressPurpose='" + getAddressPurpose() + "'" +
            ", addressType='" + getAddressType() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", taxonomyCode='" + getTaxonomyCode() + "'" +
            ", taxonomyGroup='" + getTaxonomyGroup() + "'" +
            ", taxonomyDesc='" + getTaxonomyDesc() + "'" +
            ", practiceState='" + getPracticeState() + "'" +
            ", licenceNo='" + getLicenceNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
