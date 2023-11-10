package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientMaster.
 */
@Table("t_patient_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_id")
    private Long patientId;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("dob")
    private LocalDate dob;

    @Column("gender")
    private String gender;

    @Column("ssn")
    private String ssn;

    @Column("tax_zone_id")
    private Long taxZoneId;

    @Column("discount_percent")
    private Double discountPercent;

    @Column("pos_id")
    private Long posId;

    @Column("prior_system_key")
    private String priorSystemKey;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("branch_id")
    private Long branchId;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("patient_master_uuid")
    private UUID patientMasterUUID;

    @Column("patient_id_number")
    private String patientIdNumber;

    @Column("address_line_1")
    private String addressLine1;

    @Column("address_line_2")
    private String addressLine2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("country")
    private String country;

    @Column("zip")
    private String zip;

    @Column("contact_no_1")
    private String contactNo1;

    @Column("contact_no_2")
    private String contactNo2;

    @Column("fax")
    private String fax;

    @Column("efax")
    private String efax;

    @Column("email")
    private String email;

    @Column("mode_of_contact")
    private String modeOfContact;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("branch_name")
    private String branchName;

    @Column("billing_address_line_1")
    private String billingAddressLine1;

    @Column("billing_address_line_2")
    private String billingAddressLine2;

    @Column("billing_address_city")
    private String billingAddressCity;

    @Column("billing_address_state")
    private String billingAddressState;

    @Column("billing_address_zip")
    private String billingAddressZip;

    @Column("caregiver_name")
    private String caregiverName;

    @Column("caregiver_contact")
    private String caregiverContact;

    @Column("caregiver_relatinship_patient")
    private String caregiverRelatinshipPatient;

    @Column("tax_zone_name")
    private String taxZoneName;

    @Column("tax_rate")
    private Double taxRate;

    @Column("patient_dod")
    private LocalDate patientDod;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientMaster patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public PatientMaster patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public PatientMaster patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public PatientMaster patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public PatientMaster dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return this.gender;
    }

    public PatientMaster gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return this.ssn;
    }

    public PatientMaster ssn(String ssn) {
        this.setSsn(ssn);
        return this;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getTaxZoneId() {
        return this.taxZoneId;
    }

    public PatientMaster taxZoneId(Long taxZoneId) {
        this.setTaxZoneId(taxZoneId);
        return this;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public Double getDiscountPercent() {
        return this.discountPercent;
    }

    public PatientMaster discountPercent(Double discountPercent) {
        this.setDiscountPercent(discountPercent);
        return this;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Long getPosId() {
        return this.posId;
    }

    public PatientMaster posId(Long posId) {
        this.setPosId(posId);
        return this;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getPriorSystemKey() {
        return this.priorSystemKey;
    }

    public PatientMaster priorSystemKey(String priorSystemKey) {
        this.setPriorSystemKey(priorSystemKey);
        return this;
    }

    public void setPriorSystemKey(String priorSystemKey) {
        this.priorSystemKey = priorSystemKey;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public PatientMaster branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getPatientMasterUUID() {
        return this.patientMasterUUID;
    }

    public PatientMaster patientMasterUUID(UUID patientMasterUUID) {
        this.setPatientMasterUUID(patientMasterUUID);
        return this;
    }

    public void setPatientMasterUUID(UUID patientMasterUUID) {
        this.patientMasterUUID = patientMasterUUID;
    }

    public String getPatientIdNumber() {
        return this.patientIdNumber;
    }

    public PatientMaster patientIdNumber(String patientIdNumber) {
        this.setPatientIdNumber(patientIdNumber);
        return this;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public PatientMaster addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public PatientMaster addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public PatientMaster city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public PatientMaster state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public PatientMaster country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public PatientMaster zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public PatientMaster contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public PatientMaster contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFax() {
        return this.fax;
    }

    public PatientMaster fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return this.efax;
    }

    public PatientMaster efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getEmail() {
        return this.email;
    }

    public PatientMaster email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public PatientMaster modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public PatientMaster branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBillingAddressLine1() {
        return this.billingAddressLine1;
    }

    public PatientMaster billingAddressLine1(String billingAddressLine1) {
        this.setBillingAddressLine1(billingAddressLine1);
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return this.billingAddressLine2;
    }

    public PatientMaster billingAddressLine2(String billingAddressLine2) {
        this.setBillingAddressLine2(billingAddressLine2);
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingAddressCity() {
        return this.billingAddressCity;
    }

    public PatientMaster billingAddressCity(String billingAddressCity) {
        this.setBillingAddressCity(billingAddressCity);
        return this;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return this.billingAddressState;
    }

    public PatientMaster billingAddressState(String billingAddressState) {
        this.setBillingAddressState(billingAddressState);
        return this;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZip() {
        return this.billingAddressZip;
    }

    public PatientMaster billingAddressZip(String billingAddressZip) {
        this.setBillingAddressZip(billingAddressZip);
        return this;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }

    public String getCaregiverName() {
        return this.caregiverName;
    }

    public PatientMaster caregiverName(String caregiverName) {
        this.setCaregiverName(caregiverName);
        return this;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getCaregiverContact() {
        return this.caregiverContact;
    }

    public PatientMaster caregiverContact(String caregiverContact) {
        this.setCaregiverContact(caregiverContact);
        return this;
    }

    public void setCaregiverContact(String caregiverContact) {
        this.caregiverContact = caregiverContact;
    }

    public String getCaregiverRelatinshipPatient() {
        return this.caregiverRelatinshipPatient;
    }

    public PatientMaster caregiverRelatinshipPatient(String caregiverRelatinshipPatient) {
        this.setCaregiverRelatinshipPatient(caregiverRelatinshipPatient);
        return this;
    }

    public void setCaregiverRelatinshipPatient(String caregiverRelatinshipPatient) {
        this.caregiverRelatinshipPatient = caregiverRelatinshipPatient;
    }

    public String getTaxZoneName() {
        return this.taxZoneName;
    }

    public PatientMaster taxZoneName(String taxZoneName) {
        this.setTaxZoneName(taxZoneName);
        return this;
    }

    public void setTaxZoneName(String taxZoneName) {
        this.taxZoneName = taxZoneName;
    }

    public Double getTaxRate() {
        return this.taxRate;
    }

    public PatientMaster taxRate(Double taxRate) {
        this.setTaxRate(taxRate);
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public LocalDate getPatientDod() {
        return this.patientDod;
    }

    public PatientMaster patientDod(LocalDate patientDod) {
        this.setPatientDod(patientDod);
        return this;
    }

    public void setPatientDod(LocalDate patientDod) {
        this.patientDod = patientDod;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientMaster)) {
            return false;
        }
        return patientId != null && patientId.equals(((PatientMaster) o).patientId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientMaster{" +
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
