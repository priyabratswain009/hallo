package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientDto} entity.
 */
public class PatientDtoDTO implements Serializable {

    private Long patientDtoId;

    private Long patientId;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private LocalDate dob;

    private String gender;

    private String ssn;

    private Long taxZoneId;

    private String taxZoneName;

    private Double taxRate;

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

    private String branchName;

    private String billingAddressLine1;

    private String billingAddressLine2;

    private String billingAddressCity;

    private String billingAddressState;

    private String billingAddressZip;

    private String caregiverName;

    private String caregiverContact;

    private String caregiverRelatinshipPatient;

    private Long primaryInsuranceId;

    private String primaryInsuranceName;

    private String primaryInsurancePayerIdNo;

    private String primaryInsurancePayerContactNo;

    private String primaryInsurancePolicyNum;

    private String primaryInsurancePolicyGroupNum;

    private Long primaryInsurancePolicyGroupId;

    private LocalDate primaryInsurancePolicyStartDate;

    private LocalDate primaryInsurancePolicyEndDate;

    private Double primaryInsurancePayPercentage;

    private Double primaryInsuranceDeductableAmt;

    private Long secondaryInsuranceId;

    private String secondaryInsuranceName;

    private String secondaryInsurancePayerIdNo;

    private String secondaryInsurancePayerContactNo;

    private String secondaryInsurancePolicyNum;

    private String secondaryInsurancePolicyGroupNum;

    private Long secondaryInsurancePolicyGroupId;

    private LocalDate secondaryInsurancePolicyStartDate;

    private LocalDate secondaryInsurancePolicyEndDate;

    private Double secondaryInsurancePayPercentage;

    private Double secondaryInsuranceDeductableAmt;

    private Long tertiaryInsuranceId;

    private String tertiaryInsuranceName;

    private String tertiaryInsurancePayerIdNo;

    private String tertiaryInsurancePayerContactNo;

    private String tertiaryInsurancePolicyNum;

    private String tertiaryInsurancePolicyGroupNum;

    private Long tertiaryInsurancePolicyGroupId;

    private LocalDate tertiaryInsurancePolicyStartDate;

    private LocalDate tertiaryInsurancePolicyEndDate;

    private Double tertiaryInsurancePayPercentage;

    private Double tertiaryInsuranceDeductableAmt;

    private String relationship;

    private String insuredFirstName;

    private String insuredMiddleName;

    private String insuredLastName;

    private String insuredSuffix;

    private LocalDate insuredDob;

    private String insuredSsn;

    private String insuredGender;

    private String primaryInsurerAddressLine1;

    private String primaryInsurerAddressLine2;

    private String primaryInsurerCity;

    private String primaryInsurerState;

    private String primaryInsurerZip;

    private String primaryInsurerContact1;

    private String primaryInsurerFax;

    private String alwaysCrossoverStatus;

    private String primaryInsuranceMemberId;

    private String secondaryInsuranceMemberId;

    private String tertiaryInsuranceMemberId;

    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

    private String insuredEmployer;

    private String workersCompensationPayerIdNumber;

    private String workersCompensationPlanName;

    private String workersCompensationAdditionalDtls;

    private String workersCompensationClaimFillingCode;

    private String workersCompensationTplCode;

    private String workersCompensationTplName;

    private String wcPropertyCasualtyAgencyClaimNo;

    private String wcCarrierId;

    private String employerAddressLine1;

    private String employerAddressLine2;

    private String employerCity;

    private String employerState;

    private String employerCountry;

    private String employerZip;

    private String employerContactNo1;

    private String employerContactNo2;

    private String employerFax;

    private String employerEfax;

    private String employerEmail;

    private String employeeRelationship;

    private Double height;

    private Double weight;

    private String functionalAbilities;

    private String infectionConditionStatus;

    private String diabetesStatus;

    private String diagnosisCodeType;

    private String icd10DiagnosisCode1;

    private String icd10DiagnosisCode2;

    private String icd10DiagnosisCode3;

    private String icd10DiagnosisCode4;

    private String icd10DiagnosisCode5;

    private String icd10DiagnosisCode6;

    private String icd10DiagnosisCode7;

    private String icd10DiagnosisCode8;

    private String icd10DiagnosisCode9;

    private String icd10DiagnosisCode10;

    private String icd10DiagnosisCode11;

    private String icd10DiagnosisCode12;

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

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID patientDtoUuid;

    private String secondaryInsurerAddressLine1;

    private String secondaryInsurerAddressLine2;

    private String secondaryInsurerCity;

    private String secondaryInsurerState;

    private String secondaryInsurerZip;

    private String secondaryInsurerContact1;

    private String secondaryInsurerFax;

    private String tertiaryInsurerAddressLine1;

    private String tertiaryInsurerAddressLine2;

    private String tertiaryInsurerCity;

    private String tertiaryInsurerState;

    private String tertiaryInsurerZip;

    private String tertiaryInsurerContact1;

    private String tertiaryInsurerFax;

    public Long getPatientDtoId() {
        return patientDtoId;
    }

    public void setPatientDtoId(Long patientDtoId) {
        this.patientDtoId = patientDtoId;
    }

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

    public Long getPrimaryInsuranceId() {
        return primaryInsuranceId;
    }

    public void setPrimaryInsuranceId(Long primaryInsuranceId) {
        this.primaryInsuranceId = primaryInsuranceId;
    }

    public String getPrimaryInsuranceName() {
        return primaryInsuranceName;
    }

    public void setPrimaryInsuranceName(String primaryInsuranceName) {
        this.primaryInsuranceName = primaryInsuranceName;
    }

    public String getPrimaryInsurancePayerIdNo() {
        return primaryInsurancePayerIdNo;
    }

    public void setPrimaryInsurancePayerIdNo(String primaryInsurancePayerIdNo) {
        this.primaryInsurancePayerIdNo = primaryInsurancePayerIdNo;
    }

    public String getPrimaryInsurancePayerContactNo() {
        return primaryInsurancePayerContactNo;
    }

    public void setPrimaryInsurancePayerContactNo(String primaryInsurancePayerContactNo) {
        this.primaryInsurancePayerContactNo = primaryInsurancePayerContactNo;
    }

    public String getPrimaryInsurancePolicyNum() {
        return primaryInsurancePolicyNum;
    }

    public void setPrimaryInsurancePolicyNum(String primaryInsurancePolicyNum) {
        this.primaryInsurancePolicyNum = primaryInsurancePolicyNum;
    }

    public String getPrimaryInsurancePolicyGroupNum() {
        return primaryInsurancePolicyGroupNum;
    }

    public void setPrimaryInsurancePolicyGroupNum(String primaryInsurancePolicyGroupNum) {
        this.primaryInsurancePolicyGroupNum = primaryInsurancePolicyGroupNum;
    }

    public Long getPrimaryInsurancePolicyGroupId() {
        return primaryInsurancePolicyGroupId;
    }

    public void setPrimaryInsurancePolicyGroupId(Long primaryInsurancePolicyGroupId) {
        this.primaryInsurancePolicyGroupId = primaryInsurancePolicyGroupId;
    }

    public LocalDate getPrimaryInsurancePolicyStartDate() {
        return primaryInsurancePolicyStartDate;
    }

    public void setPrimaryInsurancePolicyStartDate(LocalDate primaryInsurancePolicyStartDate) {
        this.primaryInsurancePolicyStartDate = primaryInsurancePolicyStartDate;
    }

    public LocalDate getPrimaryInsurancePolicyEndDate() {
        return primaryInsurancePolicyEndDate;
    }

    public void setPrimaryInsurancePolicyEndDate(LocalDate primaryInsurancePolicyEndDate) {
        this.primaryInsurancePolicyEndDate = primaryInsurancePolicyEndDate;
    }

    public Double getPrimaryInsurancePayPercentage() {
        return primaryInsurancePayPercentage;
    }

    public void setPrimaryInsurancePayPercentage(Double primaryInsurancePayPercentage) {
        this.primaryInsurancePayPercentage = primaryInsurancePayPercentage;
    }

    public Double getPrimaryInsuranceDeductableAmt() {
        return primaryInsuranceDeductableAmt;
    }

    public void setPrimaryInsuranceDeductableAmt(Double primaryInsuranceDeductableAmt) {
        this.primaryInsuranceDeductableAmt = primaryInsuranceDeductableAmt;
    }

    public Long getSecondaryInsuranceId() {
        return secondaryInsuranceId;
    }

    public void setSecondaryInsuranceId(Long secondaryInsuranceId) {
        this.secondaryInsuranceId = secondaryInsuranceId;
    }

    public String getSecondaryInsuranceName() {
        return secondaryInsuranceName;
    }

    public void setSecondaryInsuranceName(String secondaryInsuranceName) {
        this.secondaryInsuranceName = secondaryInsuranceName;
    }

    public String getSecondaryInsurancePayerIdNo() {
        return secondaryInsurancePayerIdNo;
    }

    public void setSecondaryInsurancePayerIdNo(String secondaryInsurancePayerIdNo) {
        this.secondaryInsurancePayerIdNo = secondaryInsurancePayerIdNo;
    }

    public String getSecondaryInsurancePayerContactNo() {
        return secondaryInsurancePayerContactNo;
    }

    public void setSecondaryInsurancePayerContactNo(String secondaryInsurancePayerContactNo) {
        this.secondaryInsurancePayerContactNo = secondaryInsurancePayerContactNo;
    }

    public String getSecondaryInsurancePolicyNum() {
        return secondaryInsurancePolicyNum;
    }

    public void setSecondaryInsurancePolicyNum(String secondaryInsurancePolicyNum) {
        this.secondaryInsurancePolicyNum = secondaryInsurancePolicyNum;
    }

    public String getSecondaryInsurancePolicyGroupNum() {
        return secondaryInsurancePolicyGroupNum;
    }

    public void setSecondaryInsurancePolicyGroupNum(String secondaryInsurancePolicyGroupNum) {
        this.secondaryInsurancePolicyGroupNum = secondaryInsurancePolicyGroupNum;
    }

    public Long getSecondaryInsurancePolicyGroupId() {
        return secondaryInsurancePolicyGroupId;
    }

    public void setSecondaryInsurancePolicyGroupId(Long secondaryInsurancePolicyGroupId) {
        this.secondaryInsurancePolicyGroupId = secondaryInsurancePolicyGroupId;
    }

    public LocalDate getSecondaryInsurancePolicyStartDate() {
        return secondaryInsurancePolicyStartDate;
    }

    public void setSecondaryInsurancePolicyStartDate(LocalDate secondaryInsurancePolicyStartDate) {
        this.secondaryInsurancePolicyStartDate = secondaryInsurancePolicyStartDate;
    }

    public LocalDate getSecondaryInsurancePolicyEndDate() {
        return secondaryInsurancePolicyEndDate;
    }

    public void setSecondaryInsurancePolicyEndDate(LocalDate secondaryInsurancePolicyEndDate) {
        this.secondaryInsurancePolicyEndDate = secondaryInsurancePolicyEndDate;
    }

    public Double getSecondaryInsurancePayPercentage() {
        return secondaryInsurancePayPercentage;
    }

    public void setSecondaryInsurancePayPercentage(Double secondaryInsurancePayPercentage) {
        this.secondaryInsurancePayPercentage = secondaryInsurancePayPercentage;
    }

    public Double getSecondaryInsuranceDeductableAmt() {
        return secondaryInsuranceDeductableAmt;
    }

    public void setSecondaryInsuranceDeductableAmt(Double secondaryInsuranceDeductableAmt) {
        this.secondaryInsuranceDeductableAmt = secondaryInsuranceDeductableAmt;
    }

    public Long getTertiaryInsuranceId() {
        return tertiaryInsuranceId;
    }

    public void setTertiaryInsuranceId(Long tertiaryInsuranceId) {
        this.tertiaryInsuranceId = tertiaryInsuranceId;
    }

    public String getTertiaryInsuranceName() {
        return tertiaryInsuranceName;
    }

    public void setTertiaryInsuranceName(String tertiaryInsuranceName) {
        this.tertiaryInsuranceName = tertiaryInsuranceName;
    }

    public String getTertiaryInsurancePayerIdNo() {
        return tertiaryInsurancePayerIdNo;
    }

    public void setTertiaryInsurancePayerIdNo(String tertiaryInsurancePayerIdNo) {
        this.tertiaryInsurancePayerIdNo = tertiaryInsurancePayerIdNo;
    }

    public String getTertiaryInsurancePayerContactNo() {
        return tertiaryInsurancePayerContactNo;
    }

    public void setTertiaryInsurancePayerContactNo(String tertiaryInsurancePayerContactNo) {
        this.tertiaryInsurancePayerContactNo = tertiaryInsurancePayerContactNo;
    }

    public String getTertiaryInsurancePolicyNum() {
        return tertiaryInsurancePolicyNum;
    }

    public void setTertiaryInsurancePolicyNum(String tertiaryInsurancePolicyNum) {
        this.tertiaryInsurancePolicyNum = tertiaryInsurancePolicyNum;
    }

    public String getTertiaryInsurancePolicyGroupNum() {
        return tertiaryInsurancePolicyGroupNum;
    }

    public void setTertiaryInsurancePolicyGroupNum(String tertiaryInsurancePolicyGroupNum) {
        this.tertiaryInsurancePolicyGroupNum = tertiaryInsurancePolicyGroupNum;
    }

    public Long getTertiaryInsurancePolicyGroupId() {
        return tertiaryInsurancePolicyGroupId;
    }

    public void setTertiaryInsurancePolicyGroupId(Long tertiaryInsurancePolicyGroupId) {
        this.tertiaryInsurancePolicyGroupId = tertiaryInsurancePolicyGroupId;
    }

    public LocalDate getTertiaryInsurancePolicyStartDate() {
        return tertiaryInsurancePolicyStartDate;
    }

    public void setTertiaryInsurancePolicyStartDate(LocalDate tertiaryInsurancePolicyStartDate) {
        this.tertiaryInsurancePolicyStartDate = tertiaryInsurancePolicyStartDate;
    }

    public LocalDate getTertiaryInsurancePolicyEndDate() {
        return tertiaryInsurancePolicyEndDate;
    }

    public void setTertiaryInsurancePolicyEndDate(LocalDate tertiaryInsurancePolicyEndDate) {
        this.tertiaryInsurancePolicyEndDate = tertiaryInsurancePolicyEndDate;
    }

    public Double getTertiaryInsurancePayPercentage() {
        return tertiaryInsurancePayPercentage;
    }

    public void setTertiaryInsurancePayPercentage(Double tertiaryInsurancePayPercentage) {
        this.tertiaryInsurancePayPercentage = tertiaryInsurancePayPercentage;
    }

    public Double getTertiaryInsuranceDeductableAmt() {
        return tertiaryInsuranceDeductableAmt;
    }

    public void setTertiaryInsuranceDeductableAmt(Double tertiaryInsuranceDeductableAmt) {
        this.tertiaryInsuranceDeductableAmt = tertiaryInsuranceDeductableAmt;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getInsuredFirstName() {
        return insuredFirstName;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredMiddleName() {
        return insuredMiddleName;
    }

    public void setInsuredMiddleName(String insuredMiddleName) {
        this.insuredMiddleName = insuredMiddleName;
    }

    public String getInsuredLastName() {
        return insuredLastName;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredSuffix() {
        return insuredSuffix;
    }

    public void setInsuredSuffix(String insuredSuffix) {
        this.insuredSuffix = insuredSuffix;
    }

    public LocalDate getInsuredDob() {
        return insuredDob;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredSsn() {
        return insuredSsn;
    }

    public void setInsuredSsn(String insuredSsn) {
        this.insuredSsn = insuredSsn;
    }

    public String getInsuredGender() {
        return insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getPrimaryInsurerAddressLine1() {
        return primaryInsurerAddressLine1;
    }

    public void setPrimaryInsurerAddressLine1(String primaryInsurerAddressLine1) {
        this.primaryInsurerAddressLine1 = primaryInsurerAddressLine1;
    }

    public String getPrimaryInsurerAddressLine2() {
        return primaryInsurerAddressLine2;
    }

    public void setPrimaryInsurerAddressLine2(String primaryInsurerAddressLine2) {
        this.primaryInsurerAddressLine2 = primaryInsurerAddressLine2;
    }

    public String getPrimaryInsurerCity() {
        return primaryInsurerCity;
    }

    public void setPrimaryInsurerCity(String primaryInsurerCity) {
        this.primaryInsurerCity = primaryInsurerCity;
    }

    public String getPrimaryInsurerState() {
        return primaryInsurerState;
    }

    public void setPrimaryInsurerState(String primaryInsurerState) {
        this.primaryInsurerState = primaryInsurerState;
    }

    public String getPrimaryInsurerZip() {
        return primaryInsurerZip;
    }

    public void setPrimaryInsurerZip(String primaryInsurerZip) {
        this.primaryInsurerZip = primaryInsurerZip;
    }

    public String getPrimaryInsurerContact1() {
        return primaryInsurerContact1;
    }

    public void setPrimaryInsurerContact1(String primaryInsurerContact1) {
        this.primaryInsurerContact1 = primaryInsurerContact1;
    }

    public String getPrimaryInsurerFax() {
        return primaryInsurerFax;
    }

    public void setPrimaryInsurerFax(String primaryInsurerFax) {
        this.primaryInsurerFax = primaryInsurerFax;
    }

    public String getAlwaysCrossoverStatus() {
        return alwaysCrossoverStatus;
    }

    public void setAlwaysCrossoverStatus(String alwaysCrossoverStatus) {
        this.alwaysCrossoverStatus = alwaysCrossoverStatus;
    }

    public String getPrimaryInsuranceMemberId() {
        return primaryInsuranceMemberId;
    }

    public void setPrimaryInsuranceMemberId(String primaryInsuranceMemberId) {
        this.primaryInsuranceMemberId = primaryInsuranceMemberId;
    }

    public String getSecondaryInsuranceMemberId() {
        return secondaryInsuranceMemberId;
    }

    public void setSecondaryInsuranceMemberId(String secondaryInsuranceMemberId) {
        this.secondaryInsuranceMemberId = secondaryInsuranceMemberId;
    }

    public String getTertiaryInsuranceMemberId() {
        return tertiaryInsuranceMemberId;
    }

    public void setTertiaryInsuranceMemberId(String tertiaryInsuranceMemberId) {
        this.tertiaryInsuranceMemberId = tertiaryInsuranceMemberId;
    }

    public String getPatientRelationshipInsured() {
        return patientRelationshipInsured;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return patientConditionEmployment;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return patientConditionAutoAccident;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return patientConditionOtherAccident;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getInsuredEmployer() {
        return insuredEmployer;
    }

    public void setInsuredEmployer(String insuredEmployer) {
        this.insuredEmployer = insuredEmployer;
    }

    public String getWorkersCompensationPayerIdNumber() {
        return workersCompensationPayerIdNumber;
    }

    public void setWorkersCompensationPayerIdNumber(String workersCompensationPayerIdNumber) {
        this.workersCompensationPayerIdNumber = workersCompensationPayerIdNumber;
    }

    public String getWorkersCompensationPlanName() {
        return workersCompensationPlanName;
    }

    public void setWorkersCompensationPlanName(String workersCompensationPlanName) {
        this.workersCompensationPlanName = workersCompensationPlanName;
    }

    public String getWorkersCompensationAdditionalDtls() {
        return workersCompensationAdditionalDtls;
    }

    public void setWorkersCompensationAdditionalDtls(String workersCompensationAdditionalDtls) {
        this.workersCompensationAdditionalDtls = workersCompensationAdditionalDtls;
    }

    public String getWorkersCompensationClaimFillingCode() {
        return workersCompensationClaimFillingCode;
    }

    public void setWorkersCompensationClaimFillingCode(String workersCompensationClaimFillingCode) {
        this.workersCompensationClaimFillingCode = workersCompensationClaimFillingCode;
    }

    public String getWorkersCompensationTplCode() {
        return workersCompensationTplCode;
    }

    public void setWorkersCompensationTplCode(String workersCompensationTplCode) {
        this.workersCompensationTplCode = workersCompensationTplCode;
    }

    public String getWorkersCompensationTplName() {
        return workersCompensationTplName;
    }

    public void setWorkersCompensationTplName(String workersCompensationTplName) {
        this.workersCompensationTplName = workersCompensationTplName;
    }

    public String getWcPropertyCasualtyAgencyClaimNo() {
        return wcPropertyCasualtyAgencyClaimNo;
    }

    public void setWcPropertyCasualtyAgencyClaimNo(String wcPropertyCasualtyAgencyClaimNo) {
        this.wcPropertyCasualtyAgencyClaimNo = wcPropertyCasualtyAgencyClaimNo;
    }

    public String getWcCarrierId() {
        return wcCarrierId;
    }

    public void setWcCarrierId(String wcCarrierId) {
        this.wcCarrierId = wcCarrierId;
    }

    public String getEmployerAddressLine1() {
        return employerAddressLine1;
    }

    public void setEmployerAddressLine1(String employerAddressLine1) {
        this.employerAddressLine1 = employerAddressLine1;
    }

    public String getEmployerAddressLine2() {
        return employerAddressLine2;
    }

    public void setEmployerAddressLine2(String employerAddressLine2) {
        this.employerAddressLine2 = employerAddressLine2;
    }

    public String getEmployerCity() {
        return employerCity;
    }

    public void setEmployerCity(String employerCity) {
        this.employerCity = employerCity;
    }

    public String getEmployerState() {
        return employerState;
    }

    public void setEmployerState(String employerState) {
        this.employerState = employerState;
    }

    public String getEmployerCountry() {
        return employerCountry;
    }

    public void setEmployerCountry(String employerCountry) {
        this.employerCountry = employerCountry;
    }

    public String getEmployerZip() {
        return employerZip;
    }

    public void setEmployerZip(String employerZip) {
        this.employerZip = employerZip;
    }

    public String getEmployerContactNo1() {
        return employerContactNo1;
    }

    public void setEmployerContactNo1(String employerContactNo1) {
        this.employerContactNo1 = employerContactNo1;
    }

    public String getEmployerContactNo2() {
        return employerContactNo2;
    }

    public void setEmployerContactNo2(String employerContactNo2) {
        this.employerContactNo2 = employerContactNo2;
    }

    public String getEmployerFax() {
        return employerFax;
    }

    public void setEmployerFax(String employerFax) {
        this.employerFax = employerFax;
    }

    public String getEmployerEfax() {
        return employerEfax;
    }

    public void setEmployerEfax(String employerEfax) {
        this.employerEfax = employerEfax;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getEmployeeRelationship() {
        return employeeRelationship;
    }

    public void setEmployeeRelationship(String employeeRelationship) {
        this.employeeRelationship = employeeRelationship;
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

    public String getDiagnosisCodeType() {
        return diagnosisCodeType;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public String getIcd10DiagnosisCode1() {
        return icd10DiagnosisCode1;
    }

    public void setIcd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.icd10DiagnosisCode1 = icd10DiagnosisCode1;
    }

    public String getIcd10DiagnosisCode2() {
        return icd10DiagnosisCode2;
    }

    public void setIcd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.icd10DiagnosisCode2 = icd10DiagnosisCode2;
    }

    public String getIcd10DiagnosisCode3() {
        return icd10DiagnosisCode3;
    }

    public void setIcd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.icd10DiagnosisCode3 = icd10DiagnosisCode3;
    }

    public String getIcd10DiagnosisCode4() {
        return icd10DiagnosisCode4;
    }

    public void setIcd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.icd10DiagnosisCode4 = icd10DiagnosisCode4;
    }

    public String getIcd10DiagnosisCode5() {
        return icd10DiagnosisCode5;
    }

    public void setIcd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.icd10DiagnosisCode5 = icd10DiagnosisCode5;
    }

    public String getIcd10DiagnosisCode6() {
        return icd10DiagnosisCode6;
    }

    public void setIcd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.icd10DiagnosisCode6 = icd10DiagnosisCode6;
    }

    public String getIcd10DiagnosisCode7() {
        return icd10DiagnosisCode7;
    }

    public void setIcd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.icd10DiagnosisCode7 = icd10DiagnosisCode7;
    }

    public String getIcd10DiagnosisCode8() {
        return icd10DiagnosisCode8;
    }

    public void setIcd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.icd10DiagnosisCode8 = icd10DiagnosisCode8;
    }

    public String getIcd10DiagnosisCode9() {
        return icd10DiagnosisCode9;
    }

    public void setIcd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.icd10DiagnosisCode9 = icd10DiagnosisCode9;
    }

    public String getIcd10DiagnosisCode10() {
        return icd10DiagnosisCode10;
    }

    public void setIcd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.icd10DiagnosisCode10 = icd10DiagnosisCode10;
    }

    public String getIcd10DiagnosisCode11() {
        return icd10DiagnosisCode11;
    }

    public void setIcd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.icd10DiagnosisCode11 = icd10DiagnosisCode11;
    }

    public String getIcd10DiagnosisCode12() {
        return icd10DiagnosisCode12;
    }

    public void setIcd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.icd10DiagnosisCode12 = icd10DiagnosisCode12;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public UUID getPatientDtoUuid() {
        return patientDtoUuid;
    }

    public void setPatientDtoUuid(UUID patientDtoUuid) {
        this.patientDtoUuid = patientDtoUuid;
    }

    public String getSecondaryInsurerAddressLine1() {
        return secondaryInsurerAddressLine1;
    }

    public void setSecondaryInsurerAddressLine1(String secondaryInsurerAddressLine1) {
        this.secondaryInsurerAddressLine1 = secondaryInsurerAddressLine1;
    }

    public String getSecondaryInsurerAddressLine2() {
        return secondaryInsurerAddressLine2;
    }

    public void setSecondaryInsurerAddressLine2(String secondaryInsurerAddressLine2) {
        this.secondaryInsurerAddressLine2 = secondaryInsurerAddressLine2;
    }

    public String getSecondaryInsurerCity() {
        return secondaryInsurerCity;
    }

    public void setSecondaryInsurerCity(String secondaryInsurerCity) {
        this.secondaryInsurerCity = secondaryInsurerCity;
    }

    public String getSecondaryInsurerState() {
        return secondaryInsurerState;
    }

    public void setSecondaryInsurerState(String secondaryInsurerState) {
        this.secondaryInsurerState = secondaryInsurerState;
    }

    public String getSecondaryInsurerZip() {
        return secondaryInsurerZip;
    }

    public void setSecondaryInsurerZip(String secondaryInsurerZip) {
        this.secondaryInsurerZip = secondaryInsurerZip;
    }

    public String getSecondaryInsurerContact1() {
        return secondaryInsurerContact1;
    }

    public void setSecondaryInsurerContact1(String secondaryInsurerContact1) {
        this.secondaryInsurerContact1 = secondaryInsurerContact1;
    }

    public String getSecondaryInsurerFax() {
        return secondaryInsurerFax;
    }

    public void setSecondaryInsurerFax(String secondaryInsurerFax) {
        this.secondaryInsurerFax = secondaryInsurerFax;
    }

    public String getTertiaryInsurerAddressLine1() {
        return tertiaryInsurerAddressLine1;
    }

    public void setTertiaryInsurerAddressLine1(String tertiaryInsurerAddressLine1) {
        this.tertiaryInsurerAddressLine1 = tertiaryInsurerAddressLine1;
    }

    public String getTertiaryInsurerAddressLine2() {
        return tertiaryInsurerAddressLine2;
    }

    public void setTertiaryInsurerAddressLine2(String tertiaryInsurerAddressLine2) {
        this.tertiaryInsurerAddressLine2 = tertiaryInsurerAddressLine2;
    }

    public String getTertiaryInsurerCity() {
        return tertiaryInsurerCity;
    }

    public void setTertiaryInsurerCity(String tertiaryInsurerCity) {
        this.tertiaryInsurerCity = tertiaryInsurerCity;
    }

    public String getTertiaryInsurerState() {
        return tertiaryInsurerState;
    }

    public void setTertiaryInsurerState(String tertiaryInsurerState) {
        this.tertiaryInsurerState = tertiaryInsurerState;
    }

    public String getTertiaryInsurerZip() {
        return tertiaryInsurerZip;
    }

    public void setTertiaryInsurerZip(String tertiaryInsurerZip) {
        this.tertiaryInsurerZip = tertiaryInsurerZip;
    }

    public String getTertiaryInsurerContact1() {
        return tertiaryInsurerContact1;
    }

    public void setTertiaryInsurerContact1(String tertiaryInsurerContact1) {
        this.tertiaryInsurerContact1 = tertiaryInsurerContact1;
    }

    public String getTertiaryInsurerFax() {
        return tertiaryInsurerFax;
    }

    public void setTertiaryInsurerFax(String tertiaryInsurerFax) {
        this.tertiaryInsurerFax = tertiaryInsurerFax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDtoDTO)) {
            return false;
        }

        PatientDtoDTO patientDtoDTO = (PatientDtoDTO) o;
        if (this.patientDtoId == null) {
            return false;
        }
        return Objects.equals(this.patientDtoId, patientDtoDTO.patientDtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientDtoId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDtoDTO{" +
            "patientDtoId=" + getPatientDtoId() +
            ", patientId=" + getPatientId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", dob='" + getDob() + "'" +
            ", gender='" + getGender() + "'" +
            ", ssn='" + getSsn() + "'" +
            ", taxZoneId=" + getTaxZoneId() +
            ", taxZoneName='" + getTaxZoneName() + "'" +
            ", taxRate=" + getTaxRate() +
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
            ", branchName='" + getBranchName() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingAddressCity='" + getBillingAddressCity() + "'" +
            ", billingAddressState='" + getBillingAddressState() + "'" +
            ", billingAddressZip='" + getBillingAddressZip() + "'" +
            ", caregiverName='" + getCaregiverName() + "'" +
            ", caregiverContact='" + getCaregiverContact() + "'" +
            ", caregiverRelatinshipPatient='" + getCaregiverRelatinshipPatient() + "'" +
            ", primaryInsuranceId=" + getPrimaryInsuranceId() +
            ", primaryInsuranceName='" + getPrimaryInsuranceName() + "'" +
            ", primaryInsurancePayerIdNo='" + getPrimaryInsurancePayerIdNo() + "'" +
            ", primaryInsurancePayerContactNo='" + getPrimaryInsurancePayerContactNo() + "'" +
            ", primaryInsurancePolicyNum='" + getPrimaryInsurancePolicyNum() + "'" +
            ", primaryInsurancePolicyGroupNum='" + getPrimaryInsurancePolicyGroupNum() + "'" +
            ", primaryInsurancePolicyGroupId=" + getPrimaryInsurancePolicyGroupId() +
            ", primaryInsurancePolicyStartDate='" + getPrimaryInsurancePolicyStartDate() + "'" +
            ", primaryInsurancePolicyEndDate='" + getPrimaryInsurancePolicyEndDate() + "'" +
            ", primaryInsurancePayPercentage=" + getPrimaryInsurancePayPercentage() +
            ", primaryInsuranceDeductableAmt=" + getPrimaryInsuranceDeductableAmt() +
            ", secondaryInsuranceId=" + getSecondaryInsuranceId() +
            ", secondaryInsuranceName='" + getSecondaryInsuranceName() + "'" +
            ", secondaryInsurancePayerIdNo='" + getSecondaryInsurancePayerIdNo() + "'" +
            ", secondaryInsurancePayerContactNo='" + getSecondaryInsurancePayerContactNo() + "'" +
            ", secondaryInsurancePolicyNum='" + getSecondaryInsurancePolicyNum() + "'" +
            ", secondaryInsurancePolicyGroupNum='" + getSecondaryInsurancePolicyGroupNum() + "'" +
            ", secondaryInsurancePolicyGroupId=" + getSecondaryInsurancePolicyGroupId() +
            ", secondaryInsurancePolicyStartDate='" + getSecondaryInsurancePolicyStartDate() + "'" +
            ", secondaryInsurancePolicyEndDate='" + getSecondaryInsurancePolicyEndDate() + "'" +
            ", secondaryInsurancePayPercentage=" + getSecondaryInsurancePayPercentage() +
            ", secondaryInsuranceDeductableAmt=" + getSecondaryInsuranceDeductableAmt() +
            ", tertiaryInsuranceId=" + getTertiaryInsuranceId() +
            ", tertiaryInsuranceName='" + getTertiaryInsuranceName() + "'" +
            ", tertiaryInsurancePayerIdNo='" + getTertiaryInsurancePayerIdNo() + "'" +
            ", tertiaryInsurancePayerContactNo='" + getTertiaryInsurancePayerContactNo() + "'" +
            ", tertiaryInsurancePolicyNum='" + getTertiaryInsurancePolicyNum() + "'" +
            ", tertiaryInsurancePolicyGroupNum='" + getTertiaryInsurancePolicyGroupNum() + "'" +
            ", tertiaryInsurancePolicyGroupId=" + getTertiaryInsurancePolicyGroupId() +
            ", tertiaryInsurancePolicyStartDate='" + getTertiaryInsurancePolicyStartDate() + "'" +
            ", tertiaryInsurancePolicyEndDate='" + getTertiaryInsurancePolicyEndDate() + "'" +
            ", tertiaryInsurancePayPercentage=" + getTertiaryInsurancePayPercentage() +
            ", tertiaryInsuranceDeductableAmt=" + getTertiaryInsuranceDeductableAmt() +
            ", relationship='" + getRelationship() + "'" +
            ", insuredFirstName='" + getInsuredFirstName() + "'" +
            ", insuredMiddleName='" + getInsuredMiddleName() + "'" +
            ", insuredLastName='" + getInsuredLastName() + "'" +
            ", insuredSuffix='" + getInsuredSuffix() + "'" +
            ", insuredDob='" + getInsuredDob() + "'" +
            ", insuredSsn='" + getInsuredSsn() + "'" +
            ", insuredGender='" + getInsuredGender() + "'" +
            ", primaryInsurerAddressLine1='" + getPrimaryInsurerAddressLine1() + "'" +
            ", primaryInsurerAddressLine2='" + getPrimaryInsurerAddressLine2() + "'" +
            ", primaryInsurerCity='" + getPrimaryInsurerCity() + "'" +
            ", primaryInsurerState='" + getPrimaryInsurerState() + "'" +
            ", primaryInsurerZip='" + getPrimaryInsurerZip() + "'" +
            ", primaryInsurerContact1='" + getPrimaryInsurerContact1() + "'" +
            ", primaryInsurerFax='" + getPrimaryInsurerFax() + "'" +
            ", alwaysCrossoverStatus='" + getAlwaysCrossoverStatus() + "'" +
            ", primaryInsuranceMemberId='" + getPrimaryInsuranceMemberId() + "'" +
            ", secondaryInsuranceMemberId='" + getSecondaryInsuranceMemberId() + "'" +
            ", tertiaryInsuranceMemberId='" + getTertiaryInsuranceMemberId() + "'" +
            ", patientRelationshipInsured='" + getPatientRelationshipInsured() + "'" +
            ", patientConditionEmployment='" + getPatientConditionEmployment() + "'" +
            ", patientConditionAutoAccident='" + getPatientConditionAutoAccident() + "'" +
            ", patientConditionOtherAccident='" + getPatientConditionOtherAccident() + "'" +
            ", insuredEmployer='" + getInsuredEmployer() + "'" +
            ", workersCompensationPayerIdNumber='" + getWorkersCompensationPayerIdNumber() + "'" +
            ", workersCompensationPlanName='" + getWorkersCompensationPlanName() + "'" +
            ", workersCompensationAdditionalDtls='" + getWorkersCompensationAdditionalDtls() + "'" +
            ", workersCompensationClaimFillingCode='" + getWorkersCompensationClaimFillingCode() + "'" +
            ", workersCompensationTplCode='" + getWorkersCompensationTplCode() + "'" +
            ", workersCompensationTplName='" + getWorkersCompensationTplName() + "'" +
            ", wcPropertyCasualtyAgencyClaimNo='" + getWcPropertyCasualtyAgencyClaimNo() + "'" +
            ", wcCarrierId='" + getWcCarrierId() + "'" +
            ", employerAddressLine1='" + getEmployerAddressLine1() + "'" +
            ", employerAddressLine2='" + getEmployerAddressLine2() + "'" +
            ", employerCity='" + getEmployerCity() + "'" +
            ", employerState='" + getEmployerState() + "'" +
            ", employerCountry='" + getEmployerCountry() + "'" +
            ", employerZip='" + getEmployerZip() + "'" +
            ", employerContactNo1='" + getEmployerContactNo1() + "'" +
            ", employerContactNo2='" + getEmployerContactNo2() + "'" +
            ", employerFax='" + getEmployerFax() + "'" +
            ", employerEfax='" + getEmployerEfax() + "'" +
            ", employerEmail='" + getEmployerEmail() + "'" +
            ", employeeRelationship='" + getEmployeeRelationship() + "'" +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", functionalAbilities='" + getFunctionalAbilities() + "'" +
            ", infectionConditionStatus='" + getInfectionConditionStatus() + "'" +
            ", diabetesStatus='" + getDiabetesStatus() + "'" +
            ", diagnosisCodeType='" + getDiagnosisCodeType() + "'" +
            ", icd10DiagnosisCode1='" + getIcd10DiagnosisCode1() + "'" +
            ", icd10DiagnosisCode2='" + getIcd10DiagnosisCode2() + "'" +
            ", icd10DiagnosisCode3='" + getIcd10DiagnosisCode3() + "'" +
            ", icd10DiagnosisCode4='" + getIcd10DiagnosisCode4() + "'" +
            ", icd10DiagnosisCode5='" + getIcd10DiagnosisCode5() + "'" +
            ", icd10DiagnosisCode6='" + getIcd10DiagnosisCode6() + "'" +
            ", icd10DiagnosisCode7='" + getIcd10DiagnosisCode7() + "'" +
            ", icd10DiagnosisCode8='" + getIcd10DiagnosisCode8() + "'" +
            ", icd10DiagnosisCode9='" + getIcd10DiagnosisCode9() + "'" +
            ", icd10DiagnosisCode10='" + getIcd10DiagnosisCode10() + "'" +
            ", icd10DiagnosisCode11='" + getIcd10DiagnosisCode11() + "'" +
            ", icd10DiagnosisCode12='" + getIcd10DiagnosisCode12() + "'" +
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
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", patientDtoUuid='" + getPatientDtoUuid() + "'" +
            ", secondaryInsurerAddressLine1='" + getSecondaryInsurerAddressLine1() + "'" +
            ", secondaryInsurerAddressLine2='" + getSecondaryInsurerAddressLine2() + "'" +
            ", secondaryInsurerCity='" + getSecondaryInsurerCity() + "'" +
            ", secondaryInsurerState='" + getSecondaryInsurerState() + "'" +
            ", secondaryInsurerZip='" + getSecondaryInsurerZip() + "'" +
            ", secondaryInsurerContact1='" + getSecondaryInsurerContact1() + "'" +
            ", secondaryInsurerFax='" + getSecondaryInsurerFax() + "'" +
            ", tertiaryInsurerAddressLine1='" + getTertiaryInsurerAddressLine1() + "'" +
            ", tertiaryInsurerAddressLine2='" + getTertiaryInsurerAddressLine2() + "'" +
            ", tertiaryInsurerCity='" + getTertiaryInsurerCity() + "'" +
            ", tertiaryInsurerState='" + getTertiaryInsurerState() + "'" +
            ", tertiaryInsurerZip='" + getTertiaryInsurerZip() + "'" +
            ", tertiaryInsurerContact1='" + getTertiaryInsurerContact1() + "'" +
            ", tertiaryInsurerFax='" + getTertiaryInsurerFax() + "'" +
            "}";
    }
}
