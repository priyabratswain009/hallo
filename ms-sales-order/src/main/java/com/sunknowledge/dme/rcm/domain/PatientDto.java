package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDto.
 */
@Table("t_patient_dto")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_dto_id")
    private Long patientDtoId;

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

    @Column("tax_zone_name")
    private String taxZoneName;

    @Column("tax_rate")
    private Double taxRate;

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

    @Column("primary_insurance_id")
    private Long primaryInsuranceId;

    @Column("primary_insurance_name")
    private String primaryInsuranceName;

    @Column("primary_insurance_payer_id_no")
    private String primaryInsurancePayerIdNo;

    @Column("primary_insurance_payer_contact_no")
    private String primaryInsurancePayerContactNo;

    @Column("primary_insurance_policy_num")
    private String primaryInsurancePolicyNum;

    @Column("primary_insurance_policy_group_num")
    private String primaryInsurancePolicyGroupNum;

    @Column("primary_insurance_policy_group_id")
    private Long primaryInsurancePolicyGroupId;

    @Column("primary_insurance_policy_start_date")
    private LocalDate primaryInsurancePolicyStartDate;

    @Column("primary_insurance_policy_end_date")
    private LocalDate primaryInsurancePolicyEndDate;

    @Column("primary_insurance_pay_percentage")
    private Double primaryInsurancePayPercentage;

    @Column("primary_insurance_deductable_amt")
    private Double primaryInsuranceDeductableAmt;

    @Column("secondary_insurance_id")
    private Long secondaryInsuranceId;

    @Column("secondary_insurance_name")
    private String secondaryInsuranceName;

    @Column("secondary_insurance_payer_id_no")
    private String secondaryInsurancePayerIdNo;

    @Column("secondary_insurance_payer_contact_no")
    private String secondaryInsurancePayerContactNo;

    @Column("secondary_insurance_policy_num")
    private String secondaryInsurancePolicyNum;

    @Column("secondary_insurance_policy_group_num")
    private String secondaryInsurancePolicyGroupNum;

    @Column("secondary_insurance_policy_group_id")
    private Long secondaryInsurancePolicyGroupId;

    @Column("secondary_insurance_policy_start_date")
    private LocalDate secondaryInsurancePolicyStartDate;

    @Column("secondary_insurance_policy_end_date")
    private LocalDate secondaryInsurancePolicyEndDate;

    @Column("secondary_insurance_pay_percentage")
    private Double secondaryInsurancePayPercentage;

    @Column("secondary_insurance_deductable_amt")
    private Double secondaryInsuranceDeductableAmt;

    @Column("tertiary_insurance_id")
    private Long tertiaryInsuranceId;

    @Column("tertiary_insurance_name")
    private String tertiaryInsuranceName;

    @Column("tertiary_insurance_payer_id_no")
    private String tertiaryInsurancePayerIdNo;

    @Column("tertiary_insurance_payer_contact_no")
    private String tertiaryInsurancePayerContactNo;

    @Column("tertiary_insurance_policy_num")
    private String tertiaryInsurancePolicyNum;

    @Column("tertiary_insurance_policy_group_num")
    private String tertiaryInsurancePolicyGroupNum;

    @Column("tertiary_insurance_policy_group_id")
    private Long tertiaryInsurancePolicyGroupId;

    @Column("tertiary_insurance_policy_start_date")
    private LocalDate tertiaryInsurancePolicyStartDate;

    @Column("tertiary_insurance_policy_end_date")
    private LocalDate tertiaryInsurancePolicyEndDate;

    @Column("tertiary_insurance_pay_percentage")
    private Double tertiaryInsurancePayPercentage;

    @Column("tertiary_insurance_deductable_amt")
    private Double tertiaryInsuranceDeductableAmt;

    @Column("relationship")
    private String relationship;

    @Column("insured_first_name")
    private String insuredFirstName;

    @Column("insured_middle_name")
    private String insuredMiddleName;

    @Column("insured_last_name")
    private String insuredLastName;

    @Column("insured_suffix")
    private String insuredSuffix;

    @Column("insured_dob")
    private LocalDate insuredDob;

    @Column("insured_ssn")
    private String insuredSsn;

    @Column("insured_gender")
    private String insuredGender;

    @Column("primary_insurer_address_line_1")
    private String primaryInsurerAddressLine1;

    @Column("primary_insurer_address_line_2")
    private String primaryInsurerAddressLine2;

    @Column("primary_insurer_city")
    private String primaryInsurerCity;

    @Column("primary_insurer_state")
    private String primaryInsurerState;

    @Column("primary_insurer_zip")
    private String primaryInsurerZip;

    @Column("primary_insurer_contact_1")
    private String primaryInsurerContact1;

    @Column("primary_insurer_fax")
    private String primaryInsurerFax;

    @Column("always_crossover_status")
    private String alwaysCrossoverStatus;

    @Column("primary_insurance_member_id")
    private String primaryInsuranceMemberId;

    @Column("secondary_insurance_member_id")
    private String secondaryInsuranceMemberId;

    @Column("tertiary_insurance_member_id")
    private String tertiaryInsuranceMemberId;

    @Column("patient_relationship_insured")
    private String patientRelationshipInsured;

    @Column("patient_condition_employment")
    private String patientConditionEmployment;

    @Column("patient_condition_auto_accident")
    private String patientConditionAutoAccident;

    @Column("patient_condition_other_accident")
    private String patientConditionOtherAccident;

    @Column("insured_employer")
    private String insuredEmployer;

    @Column("workers_compensation_payer_id_number")
    private String workersCompensationPayerIdNumber;

    @Column("workers_compensation_plan_name")
    private String workersCompensationPlanName;

    @Column("workers_compensation_additional_dtls")
    private String workersCompensationAdditionalDtls;

    @Column("workers_compensation_claim_filling_code")
    private String workersCompensationClaimFillingCode;

    @Column("workers_compensation_tpl_code")
    private String workersCompensationTplCode;

    @Column("workers_compensation_tpl_name")
    private String workersCompensationTplName;

    @Column("wc_property_casualty_agency_claim_no")
    private String wcPropertyCasualtyAgencyClaimNo;

    @Column("wc_carrier_id")
    private String wcCarrierId;

    @Column("employer_address_line_1")
    private String employerAddressLine1;

    @Column("employer_address_line_2")
    private String employerAddressLine2;

    @Column("employer_city")
    private String employerCity;

    @Column("employer_state")
    private String employerState;

    @Column("employer_country")
    private String employerCountry;

    @Column("employer_zip")
    private String employerZip;

    @Column("employer_contact_no_1")
    private String employerContactNo1;

    @Column("employer_contact_no_2")
    private String employerContactNo2;

    @Column("employer_fax")
    private String employerFax;

    @Column("employer_efax")
    private String employerEfax;

    @Column("employer_email")
    private String employerEmail;

    @Column("employee_relationship")
    private String employeeRelationship;

    @Column("height")
    private Double height;

    @Column("weight")
    private Double weight;

    @Column("functional_abilities")
    private String functionalAbilities;

    @Column("infection_condition_status")
    private String infectionConditionStatus;

    @Column("diabetes_status")
    private String diabetesStatus;

    @Column("diagnosis_code_type")
    private String diagnosisCodeType;

    @Column("icd_10_diagnosis_code_1")
    private String icd10DiagnosisCode1;

    @Column("icd_10_diagnosis_code_2")
    private String icd10DiagnosisCode2;

    @Column("icd_10_diagnosis_code_3")
    private String icd10DiagnosisCode3;

    @Column("icd_10_diagnosis_code_4")
    private String icd10DiagnosisCode4;

    @Column("icd_10_diagnosis_code_5")
    private String icd10DiagnosisCode5;

    @Column("icd_10_diagnosis_code_6")
    private String icd10DiagnosisCode6;

    @Column("icd_10_diagnosis_code_7")
    private String icd10DiagnosisCode7;

    @Column("icd_10_diagnosis_code_8")
    private String icd10DiagnosisCode8;

    @Column("icd_10_diagnosis_code_9")
    private String icd10DiagnosisCode9;

    @Column("icd_10_diagnosis_code_10")
    private String icd10DiagnosisCode10;

    @Column("icd_10_diagnosis_code_11")
    private String icd10DiagnosisCode11;

    @Column("icd_10_diagnosis_code_12")
    private String icd10DiagnosisCode12;

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

    @Column("status")
    private String status;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("patient_dto_uuid")
    private UUID patientDtoUuid;

    @Column("secondary_insurer_address_line_1")
    private String secondaryInsurerAddressLine1;

    @Column("secondary_insurer_address_line_2")
    private String secondaryInsurerAddressLine2;

    @Column("secondary_insurer_city")
    private String secondaryInsurerCity;

    @Column("secondary_insurer_state")
    private String secondaryInsurerState;

    @Column("secondary_insurer_zip")
    private String secondaryInsurerZip;

    @Column("secondary_insurer_contact_1")
    private String secondaryInsurerContact1;

    @Column("secondary_insurer_fax")
    private String secondaryInsurerFax;

    @Column("tertiary_insurer_address_line_1")
    private String tertiaryInsurerAddressLine1;

    @Column("tertiary_insurer_address_line_2")
    private String tertiaryInsurerAddressLine2;

    @Column("tertiary_insurer_city")
    private String tertiaryInsurerCity;

    @Column("tertiary_insurer_state")
    private String tertiaryInsurerState;

    @Column("tertiary_insurer_zip")
    private String tertiaryInsurerZip;

    @Column("tertiary_insurer_contact_1")
    private String tertiaryInsurerContact1;

    @Column("tertiary_insurer_fax")
    private String tertiaryInsurerFax;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientDtoId() {
        return this.patientDtoId;
    }

    public PatientDto patientDtoId(Long patientDtoId) {
        this.setPatientDtoId(patientDtoId);
        return this;
    }

    public void setPatientDtoId(Long patientDtoId) {
        this.patientDtoId = patientDtoId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientDto patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public PatientDto patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public PatientDto patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public PatientDto patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public PatientDto dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return this.gender;
    }

    public PatientDto gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return this.ssn;
    }

    public PatientDto ssn(String ssn) {
        this.setSsn(ssn);
        return this;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getTaxZoneId() {
        return this.taxZoneId;
    }

    public PatientDto taxZoneId(Long taxZoneId) {
        this.setTaxZoneId(taxZoneId);
        return this;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public String getTaxZoneName() {
        return this.taxZoneName;
    }

    public PatientDto taxZoneName(String taxZoneName) {
        this.setTaxZoneName(taxZoneName);
        return this;
    }

    public void setTaxZoneName(String taxZoneName) {
        this.taxZoneName = taxZoneName;
    }

    public Double getTaxRate() {
        return this.taxRate;
    }

    public PatientDto taxRate(Double taxRate) {
        this.setTaxRate(taxRate);
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getPatientIdNumber() {
        return this.patientIdNumber;
    }

    public PatientDto patientIdNumber(String patientIdNumber) {
        this.setPatientIdNumber(patientIdNumber);
        return this;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public PatientDto addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public PatientDto addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public PatientDto city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public PatientDto state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public PatientDto country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public PatientDto zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public PatientDto contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public PatientDto contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFax() {
        return this.fax;
    }

    public PatientDto fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return this.efax;
    }

    public PatientDto efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getEmail() {
        return this.email;
    }

    public PatientDto email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public PatientDto branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBillingAddressLine1() {
        return this.billingAddressLine1;
    }

    public PatientDto billingAddressLine1(String billingAddressLine1) {
        this.setBillingAddressLine1(billingAddressLine1);
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return this.billingAddressLine2;
    }

    public PatientDto billingAddressLine2(String billingAddressLine2) {
        this.setBillingAddressLine2(billingAddressLine2);
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingAddressCity() {
        return this.billingAddressCity;
    }

    public PatientDto billingAddressCity(String billingAddressCity) {
        this.setBillingAddressCity(billingAddressCity);
        return this;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return this.billingAddressState;
    }

    public PatientDto billingAddressState(String billingAddressState) {
        this.setBillingAddressState(billingAddressState);
        return this;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZip() {
        return this.billingAddressZip;
    }

    public PatientDto billingAddressZip(String billingAddressZip) {
        this.setBillingAddressZip(billingAddressZip);
        return this;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }

    public String getCaregiverName() {
        return this.caregiverName;
    }

    public PatientDto caregiverName(String caregiverName) {
        this.setCaregiverName(caregiverName);
        return this;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getCaregiverContact() {
        return this.caregiverContact;
    }

    public PatientDto caregiverContact(String caregiverContact) {
        this.setCaregiverContact(caregiverContact);
        return this;
    }

    public void setCaregiverContact(String caregiverContact) {
        this.caregiverContact = caregiverContact;
    }

    public String getCaregiverRelatinshipPatient() {
        return this.caregiverRelatinshipPatient;
    }

    public PatientDto caregiverRelatinshipPatient(String caregiverRelatinshipPatient) {
        this.setCaregiverRelatinshipPatient(caregiverRelatinshipPatient);
        return this;
    }

    public void setCaregiverRelatinshipPatient(String caregiverRelatinshipPatient) {
        this.caregiverRelatinshipPatient = caregiverRelatinshipPatient;
    }

    public Long getPrimaryInsuranceId() {
        return this.primaryInsuranceId;
    }

    public PatientDto primaryInsuranceId(Long primaryInsuranceId) {
        this.setPrimaryInsuranceId(primaryInsuranceId);
        return this;
    }

    public void setPrimaryInsuranceId(Long primaryInsuranceId) {
        this.primaryInsuranceId = primaryInsuranceId;
    }

    public String getPrimaryInsuranceName() {
        return this.primaryInsuranceName;
    }

    public PatientDto primaryInsuranceName(String primaryInsuranceName) {
        this.setPrimaryInsuranceName(primaryInsuranceName);
        return this;
    }

    public void setPrimaryInsuranceName(String primaryInsuranceName) {
        this.primaryInsuranceName = primaryInsuranceName;
    }

    public String getPrimaryInsurancePayerIdNo() {
        return this.primaryInsurancePayerIdNo;
    }

    public PatientDto primaryInsurancePayerIdNo(String primaryInsurancePayerIdNo) {
        this.setPrimaryInsurancePayerIdNo(primaryInsurancePayerIdNo);
        return this;
    }

    public void setPrimaryInsurancePayerIdNo(String primaryInsurancePayerIdNo) {
        this.primaryInsurancePayerIdNo = primaryInsurancePayerIdNo;
    }

    public String getPrimaryInsurancePayerContactNo() {
        return this.primaryInsurancePayerContactNo;
    }

    public PatientDto primaryInsurancePayerContactNo(String primaryInsurancePayerContactNo) {
        this.setPrimaryInsurancePayerContactNo(primaryInsurancePayerContactNo);
        return this;
    }

    public void setPrimaryInsurancePayerContactNo(String primaryInsurancePayerContactNo) {
        this.primaryInsurancePayerContactNo = primaryInsurancePayerContactNo;
    }

    public String getPrimaryInsurancePolicyNum() {
        return this.primaryInsurancePolicyNum;
    }

    public PatientDto primaryInsurancePolicyNum(String primaryInsurancePolicyNum) {
        this.setPrimaryInsurancePolicyNum(primaryInsurancePolicyNum);
        return this;
    }

    public void setPrimaryInsurancePolicyNum(String primaryInsurancePolicyNum) {
        this.primaryInsurancePolicyNum = primaryInsurancePolicyNum;
    }

    public String getPrimaryInsurancePolicyGroupNum() {
        return this.primaryInsurancePolicyGroupNum;
    }

    public PatientDto primaryInsurancePolicyGroupNum(String primaryInsurancePolicyGroupNum) {
        this.setPrimaryInsurancePolicyGroupNum(primaryInsurancePolicyGroupNum);
        return this;
    }

    public void setPrimaryInsurancePolicyGroupNum(String primaryInsurancePolicyGroupNum) {
        this.primaryInsurancePolicyGroupNum = primaryInsurancePolicyGroupNum;
    }

    public Long getPrimaryInsurancePolicyGroupId() {
        return this.primaryInsurancePolicyGroupId;
    }

    public PatientDto primaryInsurancePolicyGroupId(Long primaryInsurancePolicyGroupId) {
        this.setPrimaryInsurancePolicyGroupId(primaryInsurancePolicyGroupId);
        return this;
    }

    public void setPrimaryInsurancePolicyGroupId(Long primaryInsurancePolicyGroupId) {
        this.primaryInsurancePolicyGroupId = primaryInsurancePolicyGroupId;
    }

    public LocalDate getPrimaryInsurancePolicyStartDate() {
        return this.primaryInsurancePolicyStartDate;
    }

    public PatientDto primaryInsurancePolicyStartDate(LocalDate primaryInsurancePolicyStartDate) {
        this.setPrimaryInsurancePolicyStartDate(primaryInsurancePolicyStartDate);
        return this;
    }

    public void setPrimaryInsurancePolicyStartDate(LocalDate primaryInsurancePolicyStartDate) {
        this.primaryInsurancePolicyStartDate = primaryInsurancePolicyStartDate;
    }

    public LocalDate getPrimaryInsurancePolicyEndDate() {
        return this.primaryInsurancePolicyEndDate;
    }

    public PatientDto primaryInsurancePolicyEndDate(LocalDate primaryInsurancePolicyEndDate) {
        this.setPrimaryInsurancePolicyEndDate(primaryInsurancePolicyEndDate);
        return this;
    }

    public void setPrimaryInsurancePolicyEndDate(LocalDate primaryInsurancePolicyEndDate) {
        this.primaryInsurancePolicyEndDate = primaryInsurancePolicyEndDate;
    }

    public Double getPrimaryInsurancePayPercentage() {
        return this.primaryInsurancePayPercentage;
    }

    public PatientDto primaryInsurancePayPercentage(Double primaryInsurancePayPercentage) {
        this.setPrimaryInsurancePayPercentage(primaryInsurancePayPercentage);
        return this;
    }

    public void setPrimaryInsurancePayPercentage(Double primaryInsurancePayPercentage) {
        this.primaryInsurancePayPercentage = primaryInsurancePayPercentage;
    }

    public Double getPrimaryInsuranceDeductableAmt() {
        return this.primaryInsuranceDeductableAmt;
    }

    public PatientDto primaryInsuranceDeductableAmt(Double primaryInsuranceDeductableAmt) {
        this.setPrimaryInsuranceDeductableAmt(primaryInsuranceDeductableAmt);
        return this;
    }

    public void setPrimaryInsuranceDeductableAmt(Double primaryInsuranceDeductableAmt) {
        this.primaryInsuranceDeductableAmt = primaryInsuranceDeductableAmt;
    }

    public Long getSecondaryInsuranceId() {
        return this.secondaryInsuranceId;
    }

    public PatientDto secondaryInsuranceId(Long secondaryInsuranceId) {
        this.setSecondaryInsuranceId(secondaryInsuranceId);
        return this;
    }

    public void setSecondaryInsuranceId(Long secondaryInsuranceId) {
        this.secondaryInsuranceId = secondaryInsuranceId;
    }

    public String getSecondaryInsuranceName() {
        return this.secondaryInsuranceName;
    }

    public PatientDto secondaryInsuranceName(String secondaryInsuranceName) {
        this.setSecondaryInsuranceName(secondaryInsuranceName);
        return this;
    }

    public void setSecondaryInsuranceName(String secondaryInsuranceName) {
        this.secondaryInsuranceName = secondaryInsuranceName;
    }

    public String getSecondaryInsurancePayerIdNo() {
        return this.secondaryInsurancePayerIdNo;
    }

    public PatientDto secondaryInsurancePayerIdNo(String secondaryInsurancePayerIdNo) {
        this.setSecondaryInsurancePayerIdNo(secondaryInsurancePayerIdNo);
        return this;
    }

    public void setSecondaryInsurancePayerIdNo(String secondaryInsurancePayerIdNo) {
        this.secondaryInsurancePayerIdNo = secondaryInsurancePayerIdNo;
    }

    public String getSecondaryInsurancePayerContactNo() {
        return this.secondaryInsurancePayerContactNo;
    }

    public PatientDto secondaryInsurancePayerContactNo(String secondaryInsurancePayerContactNo) {
        this.setSecondaryInsurancePayerContactNo(secondaryInsurancePayerContactNo);
        return this;
    }

    public void setSecondaryInsurancePayerContactNo(String secondaryInsurancePayerContactNo) {
        this.secondaryInsurancePayerContactNo = secondaryInsurancePayerContactNo;
    }

    public String getSecondaryInsurancePolicyNum() {
        return this.secondaryInsurancePolicyNum;
    }

    public PatientDto secondaryInsurancePolicyNum(String secondaryInsurancePolicyNum) {
        this.setSecondaryInsurancePolicyNum(secondaryInsurancePolicyNum);
        return this;
    }

    public void setSecondaryInsurancePolicyNum(String secondaryInsurancePolicyNum) {
        this.secondaryInsurancePolicyNum = secondaryInsurancePolicyNum;
    }

    public String getSecondaryInsurancePolicyGroupNum() {
        return this.secondaryInsurancePolicyGroupNum;
    }

    public PatientDto secondaryInsurancePolicyGroupNum(String secondaryInsurancePolicyGroupNum) {
        this.setSecondaryInsurancePolicyGroupNum(secondaryInsurancePolicyGroupNum);
        return this;
    }

    public void setSecondaryInsurancePolicyGroupNum(String secondaryInsurancePolicyGroupNum) {
        this.secondaryInsurancePolicyGroupNum = secondaryInsurancePolicyGroupNum;
    }

    public Long getSecondaryInsurancePolicyGroupId() {
        return this.secondaryInsurancePolicyGroupId;
    }

    public PatientDto secondaryInsurancePolicyGroupId(Long secondaryInsurancePolicyGroupId) {
        this.setSecondaryInsurancePolicyGroupId(secondaryInsurancePolicyGroupId);
        return this;
    }

    public void setSecondaryInsurancePolicyGroupId(Long secondaryInsurancePolicyGroupId) {
        this.secondaryInsurancePolicyGroupId = secondaryInsurancePolicyGroupId;
    }

    public LocalDate getSecondaryInsurancePolicyStartDate() {
        return this.secondaryInsurancePolicyStartDate;
    }

    public PatientDto secondaryInsurancePolicyStartDate(LocalDate secondaryInsurancePolicyStartDate) {
        this.setSecondaryInsurancePolicyStartDate(secondaryInsurancePolicyStartDate);
        return this;
    }

    public void setSecondaryInsurancePolicyStartDate(LocalDate secondaryInsurancePolicyStartDate) {
        this.secondaryInsurancePolicyStartDate = secondaryInsurancePolicyStartDate;
    }

    public LocalDate getSecondaryInsurancePolicyEndDate() {
        return this.secondaryInsurancePolicyEndDate;
    }

    public PatientDto secondaryInsurancePolicyEndDate(LocalDate secondaryInsurancePolicyEndDate) {
        this.setSecondaryInsurancePolicyEndDate(secondaryInsurancePolicyEndDate);
        return this;
    }

    public void setSecondaryInsurancePolicyEndDate(LocalDate secondaryInsurancePolicyEndDate) {
        this.secondaryInsurancePolicyEndDate = secondaryInsurancePolicyEndDate;
    }

    public Double getSecondaryInsurancePayPercentage() {
        return this.secondaryInsurancePayPercentage;
    }

    public PatientDto secondaryInsurancePayPercentage(Double secondaryInsurancePayPercentage) {
        this.setSecondaryInsurancePayPercentage(secondaryInsurancePayPercentage);
        return this;
    }

    public void setSecondaryInsurancePayPercentage(Double secondaryInsurancePayPercentage) {
        this.secondaryInsurancePayPercentage = secondaryInsurancePayPercentage;
    }

    public Double getSecondaryInsuranceDeductableAmt() {
        return this.secondaryInsuranceDeductableAmt;
    }

    public PatientDto secondaryInsuranceDeductableAmt(Double secondaryInsuranceDeductableAmt) {
        this.setSecondaryInsuranceDeductableAmt(secondaryInsuranceDeductableAmt);
        return this;
    }

    public void setSecondaryInsuranceDeductableAmt(Double secondaryInsuranceDeductableAmt) {
        this.secondaryInsuranceDeductableAmt = secondaryInsuranceDeductableAmt;
    }

    public Long getTertiaryInsuranceId() {
        return this.tertiaryInsuranceId;
    }

    public PatientDto tertiaryInsuranceId(Long tertiaryInsuranceId) {
        this.setTertiaryInsuranceId(tertiaryInsuranceId);
        return this;
    }

    public void setTertiaryInsuranceId(Long tertiaryInsuranceId) {
        this.tertiaryInsuranceId = tertiaryInsuranceId;
    }

    public String getTertiaryInsuranceName() {
        return this.tertiaryInsuranceName;
    }

    public PatientDto tertiaryInsuranceName(String tertiaryInsuranceName) {
        this.setTertiaryInsuranceName(tertiaryInsuranceName);
        return this;
    }

    public void setTertiaryInsuranceName(String tertiaryInsuranceName) {
        this.tertiaryInsuranceName = tertiaryInsuranceName;
    }

    public String getTertiaryInsurancePayerIdNo() {
        return this.tertiaryInsurancePayerIdNo;
    }

    public PatientDto tertiaryInsurancePayerIdNo(String tertiaryInsurancePayerIdNo) {
        this.setTertiaryInsurancePayerIdNo(tertiaryInsurancePayerIdNo);
        return this;
    }

    public void setTertiaryInsurancePayerIdNo(String tertiaryInsurancePayerIdNo) {
        this.tertiaryInsurancePayerIdNo = tertiaryInsurancePayerIdNo;
    }

    public String getTertiaryInsurancePayerContactNo() {
        return this.tertiaryInsurancePayerContactNo;
    }

    public PatientDto tertiaryInsurancePayerContactNo(String tertiaryInsurancePayerContactNo) {
        this.setTertiaryInsurancePayerContactNo(tertiaryInsurancePayerContactNo);
        return this;
    }

    public void setTertiaryInsurancePayerContactNo(String tertiaryInsurancePayerContactNo) {
        this.tertiaryInsurancePayerContactNo = tertiaryInsurancePayerContactNo;
    }

    public String getTertiaryInsurancePolicyNum() {
        return this.tertiaryInsurancePolicyNum;
    }

    public PatientDto tertiaryInsurancePolicyNum(String tertiaryInsurancePolicyNum) {
        this.setTertiaryInsurancePolicyNum(tertiaryInsurancePolicyNum);
        return this;
    }

    public void setTertiaryInsurancePolicyNum(String tertiaryInsurancePolicyNum) {
        this.tertiaryInsurancePolicyNum = tertiaryInsurancePolicyNum;
    }

    public String getTertiaryInsurancePolicyGroupNum() {
        return this.tertiaryInsurancePolicyGroupNum;
    }

    public PatientDto tertiaryInsurancePolicyGroupNum(String tertiaryInsurancePolicyGroupNum) {
        this.setTertiaryInsurancePolicyGroupNum(tertiaryInsurancePolicyGroupNum);
        return this;
    }

    public void setTertiaryInsurancePolicyGroupNum(String tertiaryInsurancePolicyGroupNum) {
        this.tertiaryInsurancePolicyGroupNum = tertiaryInsurancePolicyGroupNum;
    }

    public Long getTertiaryInsurancePolicyGroupId() {
        return this.tertiaryInsurancePolicyGroupId;
    }

    public PatientDto tertiaryInsurancePolicyGroupId(Long tertiaryInsurancePolicyGroupId) {
        this.setTertiaryInsurancePolicyGroupId(tertiaryInsurancePolicyGroupId);
        return this;
    }

    public void setTertiaryInsurancePolicyGroupId(Long tertiaryInsurancePolicyGroupId) {
        this.tertiaryInsurancePolicyGroupId = tertiaryInsurancePolicyGroupId;
    }

    public LocalDate getTertiaryInsurancePolicyStartDate() {
        return this.tertiaryInsurancePolicyStartDate;
    }

    public PatientDto tertiaryInsurancePolicyStartDate(LocalDate tertiaryInsurancePolicyStartDate) {
        this.setTertiaryInsurancePolicyStartDate(tertiaryInsurancePolicyStartDate);
        return this;
    }

    public void setTertiaryInsurancePolicyStartDate(LocalDate tertiaryInsurancePolicyStartDate) {
        this.tertiaryInsurancePolicyStartDate = tertiaryInsurancePolicyStartDate;
    }

    public LocalDate getTertiaryInsurancePolicyEndDate() {
        return this.tertiaryInsurancePolicyEndDate;
    }

    public PatientDto tertiaryInsurancePolicyEndDate(LocalDate tertiaryInsurancePolicyEndDate) {
        this.setTertiaryInsurancePolicyEndDate(tertiaryInsurancePolicyEndDate);
        return this;
    }

    public void setTertiaryInsurancePolicyEndDate(LocalDate tertiaryInsurancePolicyEndDate) {
        this.tertiaryInsurancePolicyEndDate = tertiaryInsurancePolicyEndDate;
    }

    public Double getTertiaryInsurancePayPercentage() {
        return this.tertiaryInsurancePayPercentage;
    }

    public PatientDto tertiaryInsurancePayPercentage(Double tertiaryInsurancePayPercentage) {
        this.setTertiaryInsurancePayPercentage(tertiaryInsurancePayPercentage);
        return this;
    }

    public void setTertiaryInsurancePayPercentage(Double tertiaryInsurancePayPercentage) {
        this.tertiaryInsurancePayPercentage = tertiaryInsurancePayPercentage;
    }

    public Double getTertiaryInsuranceDeductableAmt() {
        return this.tertiaryInsuranceDeductableAmt;
    }

    public PatientDto tertiaryInsuranceDeductableAmt(Double tertiaryInsuranceDeductableAmt) {
        this.setTertiaryInsuranceDeductableAmt(tertiaryInsuranceDeductableAmt);
        return this;
    }

    public void setTertiaryInsuranceDeductableAmt(Double tertiaryInsuranceDeductableAmt) {
        this.tertiaryInsuranceDeductableAmt = tertiaryInsuranceDeductableAmt;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public PatientDto relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public PatientDto insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredMiddleName() {
        return this.insuredMiddleName;
    }

    public PatientDto insuredMiddleName(String insuredMiddleName) {
        this.setInsuredMiddleName(insuredMiddleName);
        return this;
    }

    public void setInsuredMiddleName(String insuredMiddleName) {
        this.insuredMiddleName = insuredMiddleName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public PatientDto insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredSuffix() {
        return this.insuredSuffix;
    }

    public PatientDto insuredSuffix(String insuredSuffix) {
        this.setInsuredSuffix(insuredSuffix);
        return this;
    }

    public void setInsuredSuffix(String insuredSuffix) {
        this.insuredSuffix = insuredSuffix;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public PatientDto insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredSsn() {
        return this.insuredSsn;
    }

    public PatientDto insuredSsn(String insuredSsn) {
        this.setInsuredSsn(insuredSsn);
        return this;
    }

    public void setInsuredSsn(String insuredSsn) {
        this.insuredSsn = insuredSsn;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public PatientDto insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getPrimaryInsurerAddressLine1() {
        return this.primaryInsurerAddressLine1;
    }

    public PatientDto primaryInsurerAddressLine1(String primaryInsurerAddressLine1) {
        this.setPrimaryInsurerAddressLine1(primaryInsurerAddressLine1);
        return this;
    }

    public void setPrimaryInsurerAddressLine1(String primaryInsurerAddressLine1) {
        this.primaryInsurerAddressLine1 = primaryInsurerAddressLine1;
    }

    public String getPrimaryInsurerAddressLine2() {
        return this.primaryInsurerAddressLine2;
    }

    public PatientDto primaryInsurerAddressLine2(String primaryInsurerAddressLine2) {
        this.setPrimaryInsurerAddressLine2(primaryInsurerAddressLine2);
        return this;
    }

    public void setPrimaryInsurerAddressLine2(String primaryInsurerAddressLine2) {
        this.primaryInsurerAddressLine2 = primaryInsurerAddressLine2;
    }

    public String getPrimaryInsurerCity() {
        return this.primaryInsurerCity;
    }

    public PatientDto primaryInsurerCity(String primaryInsurerCity) {
        this.setPrimaryInsurerCity(primaryInsurerCity);
        return this;
    }

    public void setPrimaryInsurerCity(String primaryInsurerCity) {
        this.primaryInsurerCity = primaryInsurerCity;
    }

    public String getPrimaryInsurerState() {
        return this.primaryInsurerState;
    }

    public PatientDto primaryInsurerState(String primaryInsurerState) {
        this.setPrimaryInsurerState(primaryInsurerState);
        return this;
    }

    public void setPrimaryInsurerState(String primaryInsurerState) {
        this.primaryInsurerState = primaryInsurerState;
    }

    public String getPrimaryInsurerZip() {
        return this.primaryInsurerZip;
    }

    public PatientDto primaryInsurerZip(String primaryInsurerZip) {
        this.setPrimaryInsurerZip(primaryInsurerZip);
        return this;
    }

    public void setPrimaryInsurerZip(String primaryInsurerZip) {
        this.primaryInsurerZip = primaryInsurerZip;
    }

    public String getPrimaryInsurerContact1() {
        return this.primaryInsurerContact1;
    }

    public PatientDto primaryInsurerContact1(String primaryInsurerContact1) {
        this.setPrimaryInsurerContact1(primaryInsurerContact1);
        return this;
    }

    public void setPrimaryInsurerContact1(String primaryInsurerContact1) {
        this.primaryInsurerContact1 = primaryInsurerContact1;
    }

    public String getPrimaryInsurerFax() {
        return this.primaryInsurerFax;
    }

    public PatientDto primaryInsurerFax(String primaryInsurerFax) {
        this.setPrimaryInsurerFax(primaryInsurerFax);
        return this;
    }

    public void setPrimaryInsurerFax(String primaryInsurerFax) {
        this.primaryInsurerFax = primaryInsurerFax;
    }

    public String getAlwaysCrossoverStatus() {
        return this.alwaysCrossoverStatus;
    }

    public PatientDto alwaysCrossoverStatus(String alwaysCrossoverStatus) {
        this.setAlwaysCrossoverStatus(alwaysCrossoverStatus);
        return this;
    }

    public void setAlwaysCrossoverStatus(String alwaysCrossoverStatus) {
        this.alwaysCrossoverStatus = alwaysCrossoverStatus;
    }

    public String getPrimaryInsuranceMemberId() {
        return this.primaryInsuranceMemberId;
    }

    public PatientDto primaryInsuranceMemberId(String primaryInsuranceMemberId) {
        this.setPrimaryInsuranceMemberId(primaryInsuranceMemberId);
        return this;
    }

    public void setPrimaryInsuranceMemberId(String primaryInsuranceMemberId) {
        this.primaryInsuranceMemberId = primaryInsuranceMemberId;
    }

    public String getSecondaryInsuranceMemberId() {
        return this.secondaryInsuranceMemberId;
    }

    public PatientDto secondaryInsuranceMemberId(String secondaryInsuranceMemberId) {
        this.setSecondaryInsuranceMemberId(secondaryInsuranceMemberId);
        return this;
    }

    public void setSecondaryInsuranceMemberId(String secondaryInsuranceMemberId) {
        this.secondaryInsuranceMemberId = secondaryInsuranceMemberId;
    }

    public String getTertiaryInsuranceMemberId() {
        return this.tertiaryInsuranceMemberId;
    }

    public PatientDto tertiaryInsuranceMemberId(String tertiaryInsuranceMemberId) {
        this.setTertiaryInsuranceMemberId(tertiaryInsuranceMemberId);
        return this;
    }

    public void setTertiaryInsuranceMemberId(String tertiaryInsuranceMemberId) {
        this.tertiaryInsuranceMemberId = tertiaryInsuranceMemberId;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public PatientDto patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public PatientDto patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public PatientDto patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public PatientDto patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getInsuredEmployer() {
        return this.insuredEmployer;
    }

    public PatientDto insuredEmployer(String insuredEmployer) {
        this.setInsuredEmployer(insuredEmployer);
        return this;
    }

    public void setInsuredEmployer(String insuredEmployer) {
        this.insuredEmployer = insuredEmployer;
    }

    public String getWorkersCompensationPayerIdNumber() {
        return this.workersCompensationPayerIdNumber;
    }

    public PatientDto workersCompensationPayerIdNumber(String workersCompensationPayerIdNumber) {
        this.setWorkersCompensationPayerIdNumber(workersCompensationPayerIdNumber);
        return this;
    }

    public void setWorkersCompensationPayerIdNumber(String workersCompensationPayerIdNumber) {
        this.workersCompensationPayerIdNumber = workersCompensationPayerIdNumber;
    }

    public String getWorkersCompensationPlanName() {
        return this.workersCompensationPlanName;
    }

    public PatientDto workersCompensationPlanName(String workersCompensationPlanName) {
        this.setWorkersCompensationPlanName(workersCompensationPlanName);
        return this;
    }

    public void setWorkersCompensationPlanName(String workersCompensationPlanName) {
        this.workersCompensationPlanName = workersCompensationPlanName;
    }

    public String getWorkersCompensationAdditionalDtls() {
        return this.workersCompensationAdditionalDtls;
    }

    public PatientDto workersCompensationAdditionalDtls(String workersCompensationAdditionalDtls) {
        this.setWorkersCompensationAdditionalDtls(workersCompensationAdditionalDtls);
        return this;
    }

    public void setWorkersCompensationAdditionalDtls(String workersCompensationAdditionalDtls) {
        this.workersCompensationAdditionalDtls = workersCompensationAdditionalDtls;
    }

    public String getWorkersCompensationClaimFillingCode() {
        return this.workersCompensationClaimFillingCode;
    }

    public PatientDto workersCompensationClaimFillingCode(String workersCompensationClaimFillingCode) {
        this.setWorkersCompensationClaimFillingCode(workersCompensationClaimFillingCode);
        return this;
    }

    public void setWorkersCompensationClaimFillingCode(String workersCompensationClaimFillingCode) {
        this.workersCompensationClaimFillingCode = workersCompensationClaimFillingCode;
    }

    public String getWorkersCompensationTplCode() {
        return this.workersCompensationTplCode;
    }

    public PatientDto workersCompensationTplCode(String workersCompensationTplCode) {
        this.setWorkersCompensationTplCode(workersCompensationTplCode);
        return this;
    }

    public void setWorkersCompensationTplCode(String workersCompensationTplCode) {
        this.workersCompensationTplCode = workersCompensationTplCode;
    }

    public String getWorkersCompensationTplName() {
        return this.workersCompensationTplName;
    }

    public PatientDto workersCompensationTplName(String workersCompensationTplName) {
        this.setWorkersCompensationTplName(workersCompensationTplName);
        return this;
    }

    public void setWorkersCompensationTplName(String workersCompensationTplName) {
        this.workersCompensationTplName = workersCompensationTplName;
    }

    public String getWcPropertyCasualtyAgencyClaimNo() {
        return this.wcPropertyCasualtyAgencyClaimNo;
    }

    public PatientDto wcPropertyCasualtyAgencyClaimNo(String wcPropertyCasualtyAgencyClaimNo) {
        this.setWcPropertyCasualtyAgencyClaimNo(wcPropertyCasualtyAgencyClaimNo);
        return this;
    }

    public void setWcPropertyCasualtyAgencyClaimNo(String wcPropertyCasualtyAgencyClaimNo) {
        this.wcPropertyCasualtyAgencyClaimNo = wcPropertyCasualtyAgencyClaimNo;
    }

    public String getWcCarrierId() {
        return this.wcCarrierId;
    }

    public PatientDto wcCarrierId(String wcCarrierId) {
        this.setWcCarrierId(wcCarrierId);
        return this;
    }

    public void setWcCarrierId(String wcCarrierId) {
        this.wcCarrierId = wcCarrierId;
    }

    public String getEmployerAddressLine1() {
        return this.employerAddressLine1;
    }

    public PatientDto employerAddressLine1(String employerAddressLine1) {
        this.setEmployerAddressLine1(employerAddressLine1);
        return this;
    }

    public void setEmployerAddressLine1(String employerAddressLine1) {
        this.employerAddressLine1 = employerAddressLine1;
    }

    public String getEmployerAddressLine2() {
        return this.employerAddressLine2;
    }

    public PatientDto employerAddressLine2(String employerAddressLine2) {
        this.setEmployerAddressLine2(employerAddressLine2);
        return this;
    }

    public void setEmployerAddressLine2(String employerAddressLine2) {
        this.employerAddressLine2 = employerAddressLine2;
    }

    public String getEmployerCity() {
        return this.employerCity;
    }

    public PatientDto employerCity(String employerCity) {
        this.setEmployerCity(employerCity);
        return this;
    }

    public void setEmployerCity(String employerCity) {
        this.employerCity = employerCity;
    }

    public String getEmployerState() {
        return this.employerState;
    }

    public PatientDto employerState(String employerState) {
        this.setEmployerState(employerState);
        return this;
    }

    public void setEmployerState(String employerState) {
        this.employerState = employerState;
    }

    public String getEmployerCountry() {
        return this.employerCountry;
    }

    public PatientDto employerCountry(String employerCountry) {
        this.setEmployerCountry(employerCountry);
        return this;
    }

    public void setEmployerCountry(String employerCountry) {
        this.employerCountry = employerCountry;
    }

    public String getEmployerZip() {
        return this.employerZip;
    }

    public PatientDto employerZip(String employerZip) {
        this.setEmployerZip(employerZip);
        return this;
    }

    public void setEmployerZip(String employerZip) {
        this.employerZip = employerZip;
    }

    public String getEmployerContactNo1() {
        return this.employerContactNo1;
    }

    public PatientDto employerContactNo1(String employerContactNo1) {
        this.setEmployerContactNo1(employerContactNo1);
        return this;
    }

    public void setEmployerContactNo1(String employerContactNo1) {
        this.employerContactNo1 = employerContactNo1;
    }

    public String getEmployerContactNo2() {
        return this.employerContactNo2;
    }

    public PatientDto employerContactNo2(String employerContactNo2) {
        this.setEmployerContactNo2(employerContactNo2);
        return this;
    }

    public void setEmployerContactNo2(String employerContactNo2) {
        this.employerContactNo2 = employerContactNo2;
    }

    public String getEmployerFax() {
        return this.employerFax;
    }

    public PatientDto employerFax(String employerFax) {
        this.setEmployerFax(employerFax);
        return this;
    }

    public void setEmployerFax(String employerFax) {
        this.employerFax = employerFax;
    }

    public String getEmployerEfax() {
        return this.employerEfax;
    }

    public PatientDto employerEfax(String employerEfax) {
        this.setEmployerEfax(employerEfax);
        return this;
    }

    public void setEmployerEfax(String employerEfax) {
        this.employerEfax = employerEfax;
    }

    public String getEmployerEmail() {
        return this.employerEmail;
    }

    public PatientDto employerEmail(String employerEmail) {
        this.setEmployerEmail(employerEmail);
        return this;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getEmployeeRelationship() {
        return this.employeeRelationship;
    }

    public PatientDto employeeRelationship(String employeeRelationship) {
        this.setEmployeeRelationship(employeeRelationship);
        return this;
    }

    public void setEmployeeRelationship(String employeeRelationship) {
        this.employeeRelationship = employeeRelationship;
    }

    public Double getHeight() {
        return this.height;
    }

    public PatientDto height(Double height) {
        this.setHeight(height);
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return this.weight;
    }

    public PatientDto weight(Double weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFunctionalAbilities() {
        return this.functionalAbilities;
    }

    public PatientDto functionalAbilities(String functionalAbilities) {
        this.setFunctionalAbilities(functionalAbilities);
        return this;
    }

    public void setFunctionalAbilities(String functionalAbilities) {
        this.functionalAbilities = functionalAbilities;
    }

    public String getInfectionConditionStatus() {
        return this.infectionConditionStatus;
    }

    public PatientDto infectionConditionStatus(String infectionConditionStatus) {
        this.setInfectionConditionStatus(infectionConditionStatus);
        return this;
    }

    public void setInfectionConditionStatus(String infectionConditionStatus) {
        this.infectionConditionStatus = infectionConditionStatus;
    }

    public String getDiabetesStatus() {
        return this.diabetesStatus;
    }

    public PatientDto diabetesStatus(String diabetesStatus) {
        this.setDiabetesStatus(diabetesStatus);
        return this;
    }

    public void setDiabetesStatus(String diabetesStatus) {
        this.diabetesStatus = diabetesStatus;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public PatientDto diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public String getIcd10DiagnosisCode1() {
        return this.icd10DiagnosisCode1;
    }

    public PatientDto icd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.setIcd10DiagnosisCode1(icd10DiagnosisCode1);
        return this;
    }

    public void setIcd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.icd10DiagnosisCode1 = icd10DiagnosisCode1;
    }

    public String getIcd10DiagnosisCode2() {
        return this.icd10DiagnosisCode2;
    }

    public PatientDto icd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.setIcd10DiagnosisCode2(icd10DiagnosisCode2);
        return this;
    }

    public void setIcd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.icd10DiagnosisCode2 = icd10DiagnosisCode2;
    }

    public String getIcd10DiagnosisCode3() {
        return this.icd10DiagnosisCode3;
    }

    public PatientDto icd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.setIcd10DiagnosisCode3(icd10DiagnosisCode3);
        return this;
    }

    public void setIcd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.icd10DiagnosisCode3 = icd10DiagnosisCode3;
    }

    public String getIcd10DiagnosisCode4() {
        return this.icd10DiagnosisCode4;
    }

    public PatientDto icd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.setIcd10DiagnosisCode4(icd10DiagnosisCode4);
        return this;
    }

    public void setIcd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.icd10DiagnosisCode4 = icd10DiagnosisCode4;
    }

    public String getIcd10DiagnosisCode5() {
        return this.icd10DiagnosisCode5;
    }

    public PatientDto icd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.setIcd10DiagnosisCode5(icd10DiagnosisCode5);
        return this;
    }

    public void setIcd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.icd10DiagnosisCode5 = icd10DiagnosisCode5;
    }

    public String getIcd10DiagnosisCode6() {
        return this.icd10DiagnosisCode6;
    }

    public PatientDto icd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.setIcd10DiagnosisCode6(icd10DiagnosisCode6);
        return this;
    }

    public void setIcd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.icd10DiagnosisCode6 = icd10DiagnosisCode6;
    }

    public String getIcd10DiagnosisCode7() {
        return this.icd10DiagnosisCode7;
    }

    public PatientDto icd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.setIcd10DiagnosisCode7(icd10DiagnosisCode7);
        return this;
    }

    public void setIcd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.icd10DiagnosisCode7 = icd10DiagnosisCode7;
    }

    public String getIcd10DiagnosisCode8() {
        return this.icd10DiagnosisCode8;
    }

    public PatientDto icd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.setIcd10DiagnosisCode8(icd10DiagnosisCode8);
        return this;
    }

    public void setIcd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.icd10DiagnosisCode8 = icd10DiagnosisCode8;
    }

    public String getIcd10DiagnosisCode9() {
        return this.icd10DiagnosisCode9;
    }

    public PatientDto icd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.setIcd10DiagnosisCode9(icd10DiagnosisCode9);
        return this;
    }

    public void setIcd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.icd10DiagnosisCode9 = icd10DiagnosisCode9;
    }

    public String getIcd10DiagnosisCode10() {
        return this.icd10DiagnosisCode10;
    }

    public PatientDto icd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.setIcd10DiagnosisCode10(icd10DiagnosisCode10);
        return this;
    }

    public void setIcd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.icd10DiagnosisCode10 = icd10DiagnosisCode10;
    }

    public String getIcd10DiagnosisCode11() {
        return this.icd10DiagnosisCode11;
    }

    public PatientDto icd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.setIcd10DiagnosisCode11(icd10DiagnosisCode11);
        return this;
    }

    public void setIcd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.icd10DiagnosisCode11 = icd10DiagnosisCode11;
    }

    public String getIcd10DiagnosisCode12() {
        return this.icd10DiagnosisCode12;
    }

    public PatientDto icd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.setIcd10DiagnosisCode12(icd10DiagnosisCode12);
        return this;
    }

    public void setIcd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.icd10DiagnosisCode12 = icd10DiagnosisCode12;
    }

    public String getDoctorFirstName() {
        return this.doctorFirstName;
    }

    public PatientDto doctorFirstName(String doctorFirstName) {
        this.setDoctorFirstName(doctorFirstName);
        return this;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorMiddleName() {
        return this.doctorMiddleName;
    }

    public PatientDto doctorMiddleName(String doctorMiddleName) {
        this.setDoctorMiddleName(doctorMiddleName);
        return this;
    }

    public void setDoctorMiddleName(String doctorMiddleName) {
        this.doctorMiddleName = doctorMiddleName;
    }

    public String getDoctorLastName() {
        return this.doctorLastName;
    }

    public PatientDto doctorLastName(String doctorLastName) {
        this.setDoctorLastName(doctorLastName);
        return this;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorNameSuffix() {
        return this.doctorNameSuffix;
    }

    public PatientDto doctorNameSuffix(String doctorNameSuffix) {
        this.setDoctorNameSuffix(doctorNameSuffix);
        return this;
    }

    public void setDoctorNameSuffix(String doctorNameSuffix) {
        this.doctorNameSuffix = doctorNameSuffix;
    }

    public String getDoctorAddressLine1() {
        return this.doctorAddressLine1;
    }

    public PatientDto doctorAddressLine1(String doctorAddressLine1) {
        this.setDoctorAddressLine1(doctorAddressLine1);
        return this;
    }

    public void setDoctorAddressLine1(String doctorAddressLine1) {
        this.doctorAddressLine1 = doctorAddressLine1;
    }

    public String getDoctorAddressLine2() {
        return this.doctorAddressLine2;
    }

    public PatientDto doctorAddressLine2(String doctorAddressLine2) {
        this.setDoctorAddressLine2(doctorAddressLine2);
        return this;
    }

    public void setDoctorAddressLine2(String doctorAddressLine2) {
        this.doctorAddressLine2 = doctorAddressLine2;
    }

    public String getDoctorAddressCity() {
        return this.doctorAddressCity;
    }

    public PatientDto doctorAddressCity(String doctorAddressCity) {
        this.setDoctorAddressCity(doctorAddressCity);
        return this;
    }

    public void setDoctorAddressCity(String doctorAddressCity) {
        this.doctorAddressCity = doctorAddressCity;
    }

    public String getDoctorAddressState() {
        return this.doctorAddressState;
    }

    public PatientDto doctorAddressState(String doctorAddressState) {
        this.setDoctorAddressState(doctorAddressState);
        return this;
    }

    public void setDoctorAddressState(String doctorAddressState) {
        this.doctorAddressState = doctorAddressState;
    }

    public String getDoctorAddressZip() {
        return this.doctorAddressZip;
    }

    public PatientDto doctorAddressZip(String doctorAddressZip) {
        this.setDoctorAddressZip(doctorAddressZip);
        return this;
    }

    public void setDoctorAddressZip(String doctorAddressZip) {
        this.doctorAddressZip = doctorAddressZip;
    }

    public String getDoctorContact1() {
        return this.doctorContact1;
    }

    public PatientDto doctorContact1(String doctorContact1) {
        this.setDoctorContact1(doctorContact1);
        return this;
    }

    public void setDoctorContact1(String doctorContact1) {
        this.doctorContact1 = doctorContact1;
    }

    public String getDoctorContact2() {
        return this.doctorContact2;
    }

    public PatientDto doctorContact2(String doctorContact2) {
        this.setDoctorContact2(doctorContact2);
        return this;
    }

    public void setDoctorContact2(String doctorContact2) {
        this.doctorContact2 = doctorContact2;
    }

    public String getDoctorFax() {
        return this.doctorFax;
    }

    public PatientDto doctorFax(String doctorFax) {
        this.setDoctorFax(doctorFax);
        return this;
    }

    public void setDoctorFax(String doctorFax) {
        this.doctorFax = doctorFax;
    }

    public String getDoctorNpiNumber() {
        return this.doctorNpiNumber;
    }

    public PatientDto doctorNpiNumber(String doctorNpiNumber) {
        this.setDoctorNpiNumber(doctorNpiNumber);
        return this;
    }

    public void setDoctorNpiNumber(String doctorNpiNumber) {
        this.doctorNpiNumber = doctorNpiNumber;
    }

    public String getDoctorGender() {
        return this.doctorGender;
    }

    public PatientDto doctorGender(String doctorGender) {
        this.setDoctorGender(doctorGender);
        return this;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorTaxonomyCode() {
        return this.doctorTaxonomyCode;
    }

    public PatientDto doctorTaxonomyCode(String doctorTaxonomyCode) {
        this.setDoctorTaxonomyCode(doctorTaxonomyCode);
        return this;
    }

    public void setDoctorTaxonomyCode(String doctorTaxonomyCode) {
        this.doctorTaxonomyCode = doctorTaxonomyCode;
    }

    public String getDoctorTaxonomyDescription() {
        return this.doctorTaxonomyDescription;
    }

    public PatientDto doctorTaxonomyDescription(String doctorTaxonomyDescription) {
        this.setDoctorTaxonomyDescription(doctorTaxonomyDescription);
        return this;
    }

    public void setDoctorTaxonomyDescription(String doctorTaxonomyDescription) {
        this.doctorTaxonomyDescription = doctorTaxonomyDescription;
    }

    public String getDoctorPracticeState() {
        return this.doctorPracticeState;
    }

    public PatientDto doctorPracticeState(String doctorPracticeState) {
        this.setDoctorPracticeState(doctorPracticeState);
        return this;
    }

    public void setDoctorPracticeState(String doctorPracticeState) {
        this.doctorPracticeState = doctorPracticeState;
    }

    public String getDoctorLicenseNumber() {
        return this.doctorLicenseNumber;
    }

    public PatientDto doctorLicenseNumber(String doctorLicenseNumber) {
        this.setDoctorLicenseNumber(doctorLicenseNumber);
        return this;
    }

    public void setDoctorLicenseNumber(String doctorLicenseNumber) {
        this.doctorLicenseNumber = doctorLicenseNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientDto status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientDto createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientDto createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientDto createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientDto updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientDto updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientDto updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getPatientDtoUuid() {
        return this.patientDtoUuid;
    }

    public PatientDto patientDtoUuid(UUID patientDtoUuid) {
        this.setPatientDtoUuid(patientDtoUuid);
        return this;
    }

    public void setPatientDtoUuid(UUID patientDtoUuid) {
        this.patientDtoUuid = patientDtoUuid;
    }

    public String getSecondaryInsurerAddressLine1() {
        return this.secondaryInsurerAddressLine1;
    }

    public PatientDto secondaryInsurerAddressLine1(String secondaryInsurerAddressLine1) {
        this.setSecondaryInsurerAddressLine1(secondaryInsurerAddressLine1);
        return this;
    }

    public void setSecondaryInsurerAddressLine1(String secondaryInsurerAddressLine1) {
        this.secondaryInsurerAddressLine1 = secondaryInsurerAddressLine1;
    }

    public String getSecondaryInsurerAddressLine2() {
        return this.secondaryInsurerAddressLine2;
    }

    public PatientDto secondaryInsurerAddressLine2(String secondaryInsurerAddressLine2) {
        this.setSecondaryInsurerAddressLine2(secondaryInsurerAddressLine2);
        return this;
    }

    public void setSecondaryInsurerAddressLine2(String secondaryInsurerAddressLine2) {
        this.secondaryInsurerAddressLine2 = secondaryInsurerAddressLine2;
    }

    public String getSecondaryInsurerCity() {
        return this.secondaryInsurerCity;
    }

    public PatientDto secondaryInsurerCity(String secondaryInsurerCity) {
        this.setSecondaryInsurerCity(secondaryInsurerCity);
        return this;
    }

    public void setSecondaryInsurerCity(String secondaryInsurerCity) {
        this.secondaryInsurerCity = secondaryInsurerCity;
    }

    public String getSecondaryInsurerState() {
        return this.secondaryInsurerState;
    }

    public PatientDto secondaryInsurerState(String secondaryInsurerState) {
        this.setSecondaryInsurerState(secondaryInsurerState);
        return this;
    }

    public void setSecondaryInsurerState(String secondaryInsurerState) {
        this.secondaryInsurerState = secondaryInsurerState;
    }

    public String getSecondaryInsurerZip() {
        return this.secondaryInsurerZip;
    }

    public PatientDto secondaryInsurerZip(String secondaryInsurerZip) {
        this.setSecondaryInsurerZip(secondaryInsurerZip);
        return this;
    }

    public void setSecondaryInsurerZip(String secondaryInsurerZip) {
        this.secondaryInsurerZip = secondaryInsurerZip;
    }

    public String getSecondaryInsurerContact1() {
        return this.secondaryInsurerContact1;
    }

    public PatientDto secondaryInsurerContact1(String secondaryInsurerContact1) {
        this.setSecondaryInsurerContact1(secondaryInsurerContact1);
        return this;
    }

    public void setSecondaryInsurerContact1(String secondaryInsurerContact1) {
        this.secondaryInsurerContact1 = secondaryInsurerContact1;
    }

    public String getSecondaryInsurerFax() {
        return this.secondaryInsurerFax;
    }

    public PatientDto secondaryInsurerFax(String secondaryInsurerFax) {
        this.setSecondaryInsurerFax(secondaryInsurerFax);
        return this;
    }

    public void setSecondaryInsurerFax(String secondaryInsurerFax) {
        this.secondaryInsurerFax = secondaryInsurerFax;
    }

    public String getTertiaryInsurerAddressLine1() {
        return this.tertiaryInsurerAddressLine1;
    }

    public PatientDto tertiaryInsurerAddressLine1(String tertiaryInsurerAddressLine1) {
        this.setTertiaryInsurerAddressLine1(tertiaryInsurerAddressLine1);
        return this;
    }

    public void setTertiaryInsurerAddressLine1(String tertiaryInsurerAddressLine1) {
        this.tertiaryInsurerAddressLine1 = tertiaryInsurerAddressLine1;
    }

    public String getTertiaryInsurerAddressLine2() {
        return this.tertiaryInsurerAddressLine2;
    }

    public PatientDto tertiaryInsurerAddressLine2(String tertiaryInsurerAddressLine2) {
        this.setTertiaryInsurerAddressLine2(tertiaryInsurerAddressLine2);
        return this;
    }

    public void setTertiaryInsurerAddressLine2(String tertiaryInsurerAddressLine2) {
        this.tertiaryInsurerAddressLine2 = tertiaryInsurerAddressLine2;
    }

    public String getTertiaryInsurerCity() {
        return this.tertiaryInsurerCity;
    }

    public PatientDto tertiaryInsurerCity(String tertiaryInsurerCity) {
        this.setTertiaryInsurerCity(tertiaryInsurerCity);
        return this;
    }

    public void setTertiaryInsurerCity(String tertiaryInsurerCity) {
        this.tertiaryInsurerCity = tertiaryInsurerCity;
    }

    public String getTertiaryInsurerState() {
        return this.tertiaryInsurerState;
    }

    public PatientDto tertiaryInsurerState(String tertiaryInsurerState) {
        this.setTertiaryInsurerState(tertiaryInsurerState);
        return this;
    }

    public void setTertiaryInsurerState(String tertiaryInsurerState) {
        this.tertiaryInsurerState = tertiaryInsurerState;
    }

    public String getTertiaryInsurerZip() {
        return this.tertiaryInsurerZip;
    }

    public PatientDto tertiaryInsurerZip(String tertiaryInsurerZip) {
        this.setTertiaryInsurerZip(tertiaryInsurerZip);
        return this;
    }

    public void setTertiaryInsurerZip(String tertiaryInsurerZip) {
        this.tertiaryInsurerZip = tertiaryInsurerZip;
    }

    public String getTertiaryInsurerContact1() {
        return this.tertiaryInsurerContact1;
    }

    public PatientDto tertiaryInsurerContact1(String tertiaryInsurerContact1) {
        this.setTertiaryInsurerContact1(tertiaryInsurerContact1);
        return this;
    }

    public void setTertiaryInsurerContact1(String tertiaryInsurerContact1) {
        this.tertiaryInsurerContact1 = tertiaryInsurerContact1;
    }

    public String getTertiaryInsurerFax() {
        return this.tertiaryInsurerFax;
    }

    public PatientDto tertiaryInsurerFax(String tertiaryInsurerFax) {
        this.setTertiaryInsurerFax(tertiaryInsurerFax);
        return this;
    }

    public void setTertiaryInsurerFax(String tertiaryInsurerFax) {
        this.tertiaryInsurerFax = tertiaryInsurerFax;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDto)) {
            return false;
        }
        return patientDtoId != null && patientDtoId.equals(((PatientDto) o).patientDtoId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDto{" +
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
