package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientInsurance.
 */
@Table("t_patient_insurance")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientInsurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_insurance_id")
    private Long patientInsuranceId;

    @Column("patient_id")
    private Long patientId;

    @Column("payer_level")
    private String payerLevel;

    @Column("insurance_name")
    private String insuranceName;

    @Column("insurance_id")
    private Long insuranceId;

    @Column("coverage_type")
    private String coverageType;

    @Column("payer_contact")
    private String payerContact;

    @Column("policy_num")
    private String policyNum;

    @Column("policy_group_num")
    private String policyGroupNum;

    @Column("policy_start_date")
    private LocalDate policyStartDate;

    @Column("policy_end_date")
    private LocalDate policyEndDate;

    @Column("pay_percentage")
    private Double payPercentage;

    @Column("deductable_amt")
    private Double deductableAmt;

    @Column("relationship")
    private String relationship;

    @Column("insured_first_name")
    private String insuredFirstName;

    @Column("insured_middle_name")
    private String insuredMiddleName;

    @Column("insured_suffix")
    private String insuredSuffix;

    @Column("insured_dob")
    private LocalDate insuredDob;

    @Column("insured_ssn")
    private String insuredSsn;

    @Column("insured_gender")
    private String insuredGender;

    @Column("always_crossover_status")
    private String alwaysCrossoverStatus;

    @Column("claim_codes")
    private String claimCodes;

    @Column("addtnl_claim_info")
    private String addtnlClaimInfo;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("insured_last_name")
    private String insuredLastName;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("patient_insurance_uuid")
    private UUID patientInsuranceUuid;

    @Column("member_id")
    private String memberId;

    @Column("patient_relationship_insured")
    private String patientRelationshipInsured;

    @Column("patient_condition_employment")
    private String patientConditionEmployment;

    @Column("patient_condition_auto_accident")
    private String patientConditionAutoAccident;

    @Column("patient_condition_other_accident")
    private String patientConditionOtherAccident;

    @Column("insurance_payer_id_no")
    private String insurancePayerIdNo;

    @Column("expiration_status")
    private String expirationStatus;

    @Column("insurer_address_line_1")
    private String insurerAddressLine1;

    @Column("insurer_address_line_2")
    private String insurerAddressLine2;

    @Column("insurer_city")
    private String insurerCity;

    @Column("insurer_state")
    private String insurerState;

    @Column("insurer_zip")
    private String insurerZip;

    @Column("insurer_contact_1")
    private String insurerContact1;

    @Column("insurer_fax")
    private String insurerFax;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientInsuranceId() {
        return this.patientInsuranceId;
    }

    public PatientInsurance patientInsuranceId(Long patientInsuranceId) {
        this.setPatientInsuranceId(patientInsuranceId);
        return this;
    }

    public void setPatientInsuranceId(Long patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientInsurance patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPayerLevel() {
        return this.payerLevel;
    }

    public PatientInsurance payerLevel(String payerLevel) {
        this.setPayerLevel(payerLevel);
        return this;
    }

    public void setPayerLevel(String payerLevel) {
        this.payerLevel = payerLevel;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public PatientInsurance insuranceName(String insuranceName) {
        this.setInsuranceName(insuranceName);
        return this;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public PatientInsurance insuranceId(Long insuranceId) {
        this.setInsuranceId(insuranceId);
        return this;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getCoverageType() {
        return this.coverageType;
    }

    public PatientInsurance coverageType(String coverageType) {
        this.setCoverageType(coverageType);
        return this;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public String getPayerContact() {
        return this.payerContact;
    }

    public PatientInsurance payerContact(String payerContact) {
        this.setPayerContact(payerContact);
        return this;
    }

    public void setPayerContact(String payerContact) {
        this.payerContact = payerContact;
    }

    public String getPolicyNum() {
        return this.policyNum;
    }

    public PatientInsurance policyNum(String policyNum) {
        this.setPolicyNum(policyNum);
        return this;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public String getPolicyGroupNum() {
        return this.policyGroupNum;
    }

    public PatientInsurance policyGroupNum(String policyGroupNum) {
        this.setPolicyGroupNum(policyGroupNum);
        return this;
    }

    public void setPolicyGroupNum(String policyGroupNum) {
        this.policyGroupNum = policyGroupNum;
    }

    public LocalDate getPolicyStartDate() {
        return this.policyStartDate;
    }

    public PatientInsurance policyStartDate(LocalDate policyStartDate) {
        this.setPolicyStartDate(policyStartDate);
        return this;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return this.policyEndDate;
    }

    public PatientInsurance policyEndDate(LocalDate policyEndDate) {
        this.setPolicyEndDate(policyEndDate);
        return this;
    }

    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public Double getPayPercentage() {
        return this.payPercentage;
    }

    public PatientInsurance payPercentage(Double payPercentage) {
        this.setPayPercentage(payPercentage);
        return this;
    }

    public void setPayPercentage(Double payPercentage) {
        this.payPercentage = payPercentage;
    }

    public Double getDeductableAmt() {
        return this.deductableAmt;
    }

    public PatientInsurance deductableAmt(Double deductableAmt) {
        this.setDeductableAmt(deductableAmt);
        return this;
    }

    public void setDeductableAmt(Double deductableAmt) {
        this.deductableAmt = deductableAmt;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public PatientInsurance relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public PatientInsurance insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredMiddleName() {
        return this.insuredMiddleName;
    }

    public PatientInsurance insuredMiddleName(String insuredMiddleName) {
        this.setInsuredMiddleName(insuredMiddleName);
        return this;
    }

    public void setInsuredMiddleName(String insuredMiddleName) {
        this.insuredMiddleName = insuredMiddleName;
    }

    public String getInsuredSuffix() {
        return this.insuredSuffix;
    }

    public PatientInsurance insuredSuffix(String insuredSuffix) {
        this.setInsuredSuffix(insuredSuffix);
        return this;
    }

    public void setInsuredSuffix(String insuredSuffix) {
        this.insuredSuffix = insuredSuffix;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public PatientInsurance insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredSsn() {
        return this.insuredSsn;
    }

    public PatientInsurance insuredSsn(String insuredSsn) {
        this.setInsuredSsn(insuredSsn);
        return this;
    }

    public void setInsuredSsn(String insuredSsn) {
        this.insuredSsn = insuredSsn;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public PatientInsurance insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getAlwaysCrossoverStatus() {
        return this.alwaysCrossoverStatus;
    }

    public PatientInsurance alwaysCrossoverStatus(String alwaysCrossoverStatus) {
        this.setAlwaysCrossoverStatus(alwaysCrossoverStatus);
        return this;
    }

    public void setAlwaysCrossoverStatus(String alwaysCrossoverStatus) {
        this.alwaysCrossoverStatus = alwaysCrossoverStatus;
    }

    public String getClaimCodes() {
        return this.claimCodes;
    }

    public PatientInsurance claimCodes(String claimCodes) {
        this.setClaimCodes(claimCodes);
        return this;
    }

    public void setClaimCodes(String claimCodes) {
        this.claimCodes = claimCodes;
    }

    public String getAddtnlClaimInfo() {
        return this.addtnlClaimInfo;
    }

    public PatientInsurance addtnlClaimInfo(String addtnlClaimInfo) {
        this.setAddtnlClaimInfo(addtnlClaimInfo);
        return this;
    }

    public void setAddtnlClaimInfo(String addtnlClaimInfo) {
        this.addtnlClaimInfo = addtnlClaimInfo;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientInsurance status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientInsurance createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientInsurance createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public PatientInsurance insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientInsurance createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientInsurance updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientInsurance updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientInsurance updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPatientInsuranceUuid() {
        return this.patientInsuranceUuid;
    }

    public PatientInsurance patientInsuranceUuid(UUID patientInsuranceUuid) {
        this.setPatientInsuranceUuid(patientInsuranceUuid);
        return this;
    }

    public void setPatientInsuranceUuid(UUID patientInsuranceUuid) {
        this.patientInsuranceUuid = patientInsuranceUuid;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public PatientInsurance memberId(String memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public PatientInsurance patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public PatientInsurance patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public PatientInsurance patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public PatientInsurance patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getInsurancePayerIdNo() {
        return this.insurancePayerIdNo;
    }

    public PatientInsurance insurancePayerIdNo(String insurancePayerIdNo) {
        this.setInsurancePayerIdNo(insurancePayerIdNo);
        return this;
    }

    public void setInsurancePayerIdNo(String insurancePayerIdNo) {
        this.insurancePayerIdNo = insurancePayerIdNo;
    }

    public String getExpirationStatus() {
        return this.expirationStatus;
    }

    public PatientInsurance expirationStatus(String expirationStatus) {
        this.setExpirationStatus(expirationStatus);
        return this;
    }

    public void setExpirationStatus(String expirationStatus) {
        this.expirationStatus = expirationStatus;
    }

    public String getInsurerAddressLine1() {
        return this.insurerAddressLine1;
    }

    public PatientInsurance insurerAddressLine1(String insurerAddressLine1) {
        this.setInsurerAddressLine1(insurerAddressLine1);
        return this;
    }

    public void setInsurerAddressLine1(String insurerAddressLine1) {
        this.insurerAddressLine1 = insurerAddressLine1;
    }

    public String getInsurerAddressLine2() {
        return this.insurerAddressLine2;
    }

    public PatientInsurance insurerAddressLine2(String insurerAddressLine2) {
        this.setInsurerAddressLine2(insurerAddressLine2);
        return this;
    }

    public void setInsurerAddressLine2(String insurerAddressLine2) {
        this.insurerAddressLine2 = insurerAddressLine2;
    }

    public String getInsurerCity() {
        return this.insurerCity;
    }

    public PatientInsurance insurerCity(String insurerCity) {
        this.setInsurerCity(insurerCity);
        return this;
    }

    public void setInsurerCity(String insurerCity) {
        this.insurerCity = insurerCity;
    }

    public String getInsurerState() {
        return this.insurerState;
    }

    public PatientInsurance insurerState(String insurerState) {
        this.setInsurerState(insurerState);
        return this;
    }

    public void setInsurerState(String insurerState) {
        this.insurerState = insurerState;
    }

    public String getInsurerZip() {
        return this.insurerZip;
    }

    public PatientInsurance insurerZip(String insurerZip) {
        this.setInsurerZip(insurerZip);
        return this;
    }

    public void setInsurerZip(String insurerZip) {
        this.insurerZip = insurerZip;
    }

    public String getInsurerContact1() {
        return this.insurerContact1;
    }

    public PatientInsurance insurerContact1(String insurerContact1) {
        this.setInsurerContact1(insurerContact1);
        return this;
    }

    public void setInsurerContact1(String insurerContact1) {
        this.insurerContact1 = insurerContact1;
    }

    public String getInsurerFax() {
        return this.insurerFax;
    }

    public PatientInsurance insurerFax(String insurerFax) {
        this.setInsurerFax(insurerFax);
        return this;
    }

    public void setInsurerFax(String insurerFax) {
        this.insurerFax = insurerFax;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientInsurance)) {
            return false;
        }
        return patientInsuranceId != null && patientInsuranceId.equals(((PatientInsurance) o).patientInsuranceId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientInsurance{" +
            "patientInsuranceId=" + getPatientInsuranceId() +
            ", patientId=" + getPatientId() +
            ", payerLevel='" + getPayerLevel() + "'" +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", insuranceId=" + getInsuranceId() +
            ", coverageType='" + getCoverageType() + "'" +
            ", payerContact='" + getPayerContact() + "'" +
            ", policyNum='" + getPolicyNum() + "'" +
            ", policyGroupNum='" + getPolicyGroupNum() + "'" +
            ", policyStartDate='" + getPolicyStartDate() + "'" +
            ", policyEndDate='" + getPolicyEndDate() + "'" +
            ", payPercentage=" + getPayPercentage() +
            ", deductableAmt=" + getDeductableAmt() +
            ", relationship='" + getRelationship() + "'" +
            ", insuredFirstName='" + getInsuredFirstName() + "'" +
            ", insuredMiddleName='" + getInsuredMiddleName() + "'" +
            ", insuredSuffix='" + getInsuredSuffix() + "'" +
            ", insuredDob='" + getInsuredDob() + "'" +
            ", insuredSsn='" + getInsuredSsn() + "'" +
            ", insuredGender='" + getInsuredGender() + "'" +
            ", alwaysCrossoverStatus='" + getAlwaysCrossoverStatus() + "'" +
            ", claimCodes='" + getClaimCodes() + "'" +
            ", addtnlClaimInfo='" + getAddtnlClaimInfo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", insuredLastName='" + getInsuredLastName() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", patientInsuranceUuid='" + getPatientInsuranceUuid() + "'" +
            ", memberId='" + getMemberId() + "'" +
            ", patientRelationshipInsured='" + getPatientRelationshipInsured() + "'" +
            ", patientConditionEmployment='" + getPatientConditionEmployment() + "'" +
            ", patientConditionAutoAccident='" + getPatientConditionAutoAccident() + "'" +
            ", patientConditionOtherAccident='" + getPatientConditionOtherAccident() + "'" +
            ", insurancePayerIdNo='" + getInsurancePayerIdNo() + "'" +
            ", expirationStatus='" + getExpirationStatus() + "'" +
            ", insurerAddressLine1='" + getInsurerAddressLine1() + "'" +
            ", insurerAddressLine2='" + getInsurerAddressLine2() + "'" +
            ", insurerCity='" + getInsurerCity() + "'" +
            ", insurerState='" + getInsurerState() + "'" +
            ", insurerZip='" + getInsurerZip() + "'" +
            ", insurerContact1='" + getInsurerContact1() + "'" +
            ", insurerFax='" + getInsurerFax() + "'" +
            "}";
    }
}
