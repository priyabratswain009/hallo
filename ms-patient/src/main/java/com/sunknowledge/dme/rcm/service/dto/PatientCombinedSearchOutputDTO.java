package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.util.UUID;

public class PatientCombinedSearchOutputDTO implements Serializable{
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String dob;
    private String gender;
    private String ssn;
    private String patientIdNumber;
    private String branchName;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String city;
    private String state;
    private String zip;
    private String contactNo1;
    private String contactNo2;
    private String fax;
    private String efax;
    private String email;
    private UUID patientMasterUuid;
    private String insuranceName;
    private String policyNum;
    private String memberId;

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
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

    public UUID getPatientMasterUuid() {
        return patientMasterUuid;
    }

    public void setPatientMasterUuid(UUID patientMasterUuid) {
        this.patientMasterUuid = patientMasterUuid;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "PatientCombinedSearchOutputDTO{" +
            "patientFirstName='" + patientFirstName + '\'' +
            ", patientMiddleName='" + patientMiddleName + '\'' +
            ", patientLastName='" + patientLastName + '\'' +
            ", dob='" + dob + '\'' +
            ", gender='" + gender + '\'' +
            ", ssn='" + ssn + '\'' +
            ", patientIdNumber='" + patientIdNumber + '\'' +
            ", branchName='" + branchName + '\'' +
            ", billingAddressLine1='" + billingAddressLine1 + '\'' +
            ", billingAddressLine2='" + billingAddressLine2 + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", contactNo1='" + contactNo1 + '\'' +
            ", contactNo2='" + contactNo2 + '\'' +
            ", fax='" + fax + '\'' +
            ", efax='" + efax + '\'' +
            ", email='" + email + '\'' +
            ", patientMasterUuid=" + patientMasterUuid +
            ", insuranceName='" + insuranceName + '\'' +
            ", policyNum='" + policyNum + '\'' +
            ", memberId='" + memberId + '\'' +
            '}';
    }
}

