package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DoctorMaster.
 */
@Entity
@Table(name = "t_doctor_master")
public class DoctorMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "title")
    private String title;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "doctor_speciality_id")
    private Long doctorSpecialityId;

    @Column(name = "addl_identifier")
    private String addlIdentifier;

    @Column(name = "doctor_master_uuid")
    private UUID doctorMasterUuid;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "zip")
    private String zip;

    @Column(name = "contact_no_1")
    private String contactNo1;

    @Column(name = "contact_no_2")
    private String contactNo2;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "efax")
    private String efax;

    @Column(name = "email")
    private String email;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "mode_of_contact")
    private String modeOfContact;

    @Column(name = "npi_number")
    private String npiNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "enumeration_date")
    private LocalDate enumerationDate;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "address_purpose")
    private String addressPurpose;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "taxonomy_code")
    private String taxonomyCode;

    @Column(name = "taxonomy_group")
    private String taxonomyGroup;

    @Column(name = "taxonomy_desc")
    private String taxonomyDesc;

    @Column(name = "practice_state")
    private String practiceState;

    @Column(name = "licence_no")
    private String licenceNo;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDoctorId() {
        return this.doctorId;
    }

    public DoctorMaster doctorId(Long doctorId) {
        this.setDoctorId(doctorId);
        return this;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public DoctorMaster firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public DoctorMaster middleName(String middleName) {
        this.setMiddleName(middleName);
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public DoctorMaster lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return this.title;
    }

    public DoctorMaster title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public DoctorMaster suffix(String suffix) {
        this.setSuffix(suffix);
        return this;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getDoctorSpecialityId() {
        return this.doctorSpecialityId;
    }

    public DoctorMaster doctorSpecialityId(Long doctorSpecialityId) {
        this.setDoctorSpecialityId(doctorSpecialityId);
        return this;
    }

    public void setDoctorSpecialityId(Long doctorSpecialityId) {
        this.doctorSpecialityId = doctorSpecialityId;
    }

    public String getAddlIdentifier() {
        return this.addlIdentifier;
    }

    public DoctorMaster addlIdentifier(String addlIdentifier) {
        this.setAddlIdentifier(addlIdentifier);
        return this;
    }

    public void setAddlIdentifier(String addlIdentifier) {
        this.addlIdentifier = addlIdentifier;
    }

    public UUID getDoctorMasterUuid() {
        return this.doctorMasterUuid;
    }

    public DoctorMaster doctorMasterUuid(UUID doctorMasterUuid) {
        this.setDoctorMasterUuid(doctorMasterUuid);
        return this;
    }

    public void setDoctorMasterUuid(UUID doctorMasterUuid) {
        this.doctorMasterUuid = doctorMasterUuid;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public DoctorMaster addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public DoctorMaster addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public DoctorMaster city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public DoctorMaster state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public DoctorMaster countryName(String countryName) {
        this.setCountryName(countryName);
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getZip() {
        return this.zip;
    }

    public DoctorMaster zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public DoctorMaster contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public DoctorMaster contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFaxNumber() {
        return this.faxNumber;
    }

    public DoctorMaster faxNumber(String faxNumber) {
        this.setFaxNumber(faxNumber);
        return this;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEfax() {
        return this.efax;
    }

    public DoctorMaster efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getEmail() {
        return this.email;
    }

    public DoctorMaster email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public DoctorMaster relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public DoctorMaster modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getNpiNumber() {
        return this.npiNumber;
    }

    public DoctorMaster npiNumber(String npiNumber) {
        this.setNpiNumber(npiNumber);
        return this;
    }

    public void setNpiNumber(String npiNumber) {
        this.npiNumber = npiNumber;
    }

    public String getGender() {
        return this.gender;
    }

    public DoctorMaster gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getEnumerationDate() {
        return this.enumerationDate;
    }

    public DoctorMaster enumerationDate(LocalDate enumerationDate) {
        this.setEnumerationDate(enumerationDate);
        return this;
    }

    public void setEnumerationDate(LocalDate enumerationDate) {
        this.enumerationDate = enumerationDate;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public DoctorMaster countryCode(String countryCode) {
        this.setCountryCode(countryCode);
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddressPurpose() {
        return this.addressPurpose;
    }

    public DoctorMaster addressPurpose(String addressPurpose) {
        this.setAddressPurpose(addressPurpose);
        return this;
    }

    public void setAddressPurpose(String addressPurpose) {
        this.addressPurpose = addressPurpose;
    }

    public String getAddressType() {
        return this.addressType;
    }

    public DoctorMaster addressType(String addressType) {
        this.setAddressType(addressType);
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public DoctorMaster postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTaxonomyCode() {
        return this.taxonomyCode;
    }

    public DoctorMaster taxonomyCode(String taxonomyCode) {
        this.setTaxonomyCode(taxonomyCode);
        return this;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getTaxonomyGroup() {
        return this.taxonomyGroup;
    }

    public DoctorMaster taxonomyGroup(String taxonomyGroup) {
        this.setTaxonomyGroup(taxonomyGroup);
        return this;
    }

    public void setTaxonomyGroup(String taxonomyGroup) {
        this.taxonomyGroup = taxonomyGroup;
    }

    public String getTaxonomyDesc() {
        return this.taxonomyDesc;
    }

    public DoctorMaster taxonomyDesc(String taxonomyDesc) {
        this.setTaxonomyDesc(taxonomyDesc);
        return this;
    }

    public void setTaxonomyDesc(String taxonomyDesc) {
        this.taxonomyDesc = taxonomyDesc;
    }

    public String getPracticeState() {
        return this.practiceState;
    }

    public DoctorMaster practiceState(String practiceState) {
        this.setPracticeState(practiceState);
        return this;
    }

    public void setPracticeState(String practiceState) {
        this.practiceState = practiceState;
    }

    public String getLicenceNo() {
        return this.licenceNo;
    }

    public DoctorMaster licenceNo(String licenceNo) {
        this.setLicenceNo(licenceNo);
        return this;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getStatus() {
        return this.status;
    }

    public DoctorMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DoctorMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DoctorMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DoctorMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DoctorMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DoctorMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DoctorMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorMaster)) {
            return false;
        }
        return doctorId != null && doctorId.equals(((DoctorMaster) o).doctorId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoctorMaster{" +
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
