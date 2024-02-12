package com.sunknowledge.dme.rcm.dto.claims;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientInsurance} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientInsuranceDTO implements Serializable {

    private Long patientInsuranceId;

    private Long patientId;

    private String payerLevel;

    private String insuranceName;

    private Long insuranceId;

    private String coverageType;

    private String payerContact;

    private String policyNum;

    private String policyGroupNum;

    private LocalDate policyStartDate;

    private LocalDate policyEndDate;

    private Double payPercentage;

    private Double deductableAmt;

    private String relationship;

    private String insuredFirstName;

    private String insuredMiddleName;

    private String insuredSuffix;

    private LocalDate insuredDob;

    private String insuredSsn;

    private String insuredGender;

    private String alwaysCrossoverStatus;

    private String claimCodes;

    private String addtnlClaimInfo;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String insuredLastName;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID patientInsuranceUuid;

    private String memberId;

    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

    private String insurancePayerIdNo;

    private String expirationStatus;

    private String insurerAddressLine1;

    private String insurerAddressLine2;

    private String insurerCity;

    private String insurerState;

    private String insurerZip;

    private String insurerContact1;

    private String insurerFax;

    public Long getPatientInsuranceId() {
        return patientInsuranceId;
    }

    public void setPatientInsuranceId(Long patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPayerLevel() {
        return payerLevel;
    }

    public void setPayerLevel(String payerLevel) {
        this.payerLevel = payerLevel;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public String getPayerContact() {
        return payerContact;
    }

    public void setPayerContact(String payerContact) {
        this.payerContact = payerContact;
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public String getPolicyGroupNum() {
        return policyGroupNum;
    }

    public void setPolicyGroupNum(String policyGroupNum) {
        this.policyGroupNum = policyGroupNum;
    }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public Double getPayPercentage() {
        return payPercentage;
    }

    public void setPayPercentage(Double payPercentage) {
        this.payPercentage = payPercentage;
    }

    public Double getDeductableAmt() {
        return deductableAmt;
    }

    public void setDeductableAmt(Double deductableAmt) {
        this.deductableAmt = deductableAmt;
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

    public String getAlwaysCrossoverStatus() {
        return alwaysCrossoverStatus;
    }

    public void setAlwaysCrossoverStatus(String alwaysCrossoverStatus) {
        this.alwaysCrossoverStatus = alwaysCrossoverStatus;
    }

    public String getClaimCodes() {
        return claimCodes;
    }

    public void setClaimCodes(String claimCodes) {
        this.claimCodes = claimCodes;
    }

    public String getAddtnlClaimInfo() {
        return addtnlClaimInfo;
    }

    public void setAddtnlClaimInfo(String addtnlClaimInfo) {
        this.addtnlClaimInfo = addtnlClaimInfo;
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

    public String getInsuredLastName() {
        return insuredLastName;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
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

    public UUID getPatientInsuranceUuid() {
        return patientInsuranceUuid;
    }

    public void setPatientInsuranceUuid(UUID patientInsuranceUuid) {
        this.patientInsuranceUuid = patientInsuranceUuid;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getInsurancePayerIdNo() {
        return insurancePayerIdNo;
    }

    public void setInsurancePayerIdNo(String insurancePayerIdNo) {
        this.insurancePayerIdNo = insurancePayerIdNo;
    }

    public String getExpirationStatus() {
        return expirationStatus;
    }

    public void setExpirationStatus(String expirationStatus) {
        this.expirationStatus = expirationStatus;
    }

    public String getInsurerAddressLine1() {
        return insurerAddressLine1;
    }

    public void setInsurerAddressLine1(String insurerAddressLine1) {
        this.insurerAddressLine1 = insurerAddressLine1;
    }

    public String getInsurerAddressLine2() {
        return insurerAddressLine2;
    }

    public void setInsurerAddressLine2(String insurerAddressLine2) {
        this.insurerAddressLine2 = insurerAddressLine2;
    }

    public String getInsurerCity() {
        return insurerCity;
    }

    public void setInsurerCity(String insurerCity) {
        this.insurerCity = insurerCity;
    }

    public String getInsurerState() {
        return insurerState;
    }

    public void setInsurerState(String insurerState) {
        this.insurerState = insurerState;
    }

    public String getInsurerZip() {
        return insurerZip;
    }

    public void setInsurerZip(String insurerZip) {
        this.insurerZip = insurerZip;
    }

    public String getInsurerContact1() {
        return insurerContact1;
    }

    public void setInsurerContact1(String insurerContact1) {
        this.insurerContact1 = insurerContact1;
    }

    public String getInsurerFax() {
        return insurerFax;
    }

    public void setInsurerFax(String insurerFax) {
        this.insurerFax = insurerFax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientInsuranceDTO)) {
            return false;
        }

        PatientInsuranceDTO patientInsuranceDTO = (PatientInsuranceDTO) o;
        if (this.patientInsuranceId == null) {
            return false;
        }
        return Objects.equals(this.patientInsuranceId, patientInsuranceDTO.patientInsuranceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.patientInsuranceId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientInsuranceDTO{" +
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
