package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BenefitCoverageResponseDTO implements Serializable {

    private Long benefitCoverageResponseId;

    private Long benefitCoverageRequestId;

    private String requestControlNumberExt;

    private LocalDate asOnDate;

    private String serviceType;

    private String memberFirstName;

    private String memberLastName;

    private String subscriberMemberId;

    private String memberGender;

    private String patientFirstName;

    private String patientLastName;

    private String patientGender;

    private String payerName;

    private String patientRelationshipCode;

    private String patientState;

    private String coverageStatus;

    private String payerGroupNumber;

    private LocalDate serviceDate;

    private LocalDate planStartDate;

    private String responseJsonText;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID benefitCoverageResponseUuid;

    public Long getBenefitCoverageResponseId() {
        return benefitCoverageResponseId;
    }

    public void setBenefitCoverageResponseId(Long benefitCoverageResponseId) {
        this.benefitCoverageResponseId = benefitCoverageResponseId;
    }

    public Long getBenefitCoverageRequestId() {
        return benefitCoverageRequestId;
    }

    public void setBenefitCoverageRequestId(Long benefitCoverageRequestId) {
        this.benefitCoverageRequestId = benefitCoverageRequestId;
    }

    public String getRequestControlNumberExt() {
        return requestControlNumberExt;
    }

    public void setRequestControlNumberExt(String requestControlNumberExt) {
        this.requestControlNumberExt = requestControlNumberExt;
    }

    public LocalDate getAsOnDate() {
        return asOnDate;
    }

    public void setAsOnDate(LocalDate asOnDate) {
        this.asOnDate = asOnDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getSubscriberMemberId() {
        return subscriberMemberId;
    }

    public void setSubscriberMemberId(String subscriberMemberId) {
        this.subscriberMemberId = subscriberMemberId;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPatientRelationshipCode() {
        return patientRelationshipCode;
    }

    public void setPatientRelationshipCode(String patientRelationshipCode) {
        this.patientRelationshipCode = patientRelationshipCode;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getCoverageStatus() {
        return coverageStatus;
    }

    public void setCoverageStatus(String coverageStatus) {
        this.coverageStatus = coverageStatus;
    }

    public String getPayerGroupNumber() {
        return payerGroupNumber;
    }

    public void setPayerGroupNumber(String payerGroupNumber) {
        this.payerGroupNumber = payerGroupNumber;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getResponseJsonText() {
        return responseJsonText;
    }

    public void setResponseJsonText(String responseJsonText) {
        this.responseJsonText = responseJsonText;
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

    public UUID getBenefitCoverageResponseUuid() {
        return benefitCoverageResponseUuid;
    }

    public void setBenefitCoverageResponseUuid(UUID benefitCoverageResponseUuid) {
        this.benefitCoverageResponseUuid = benefitCoverageResponseUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BenefitCoverageResponseDTO)) {
            return false;
        }

        BenefitCoverageResponseDTO benefitCoverageResponseDTO = (BenefitCoverageResponseDTO) o;
        if (this.benefitCoverageResponseId == null) {
            return false;
        }
        return Objects.equals(this.benefitCoverageResponseId, benefitCoverageResponseDTO.benefitCoverageResponseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.benefitCoverageResponseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BenefitCoverageResponseDTO{" +
            "benefitCoverageResponseId=" + getBenefitCoverageResponseId() +
            ", benefitCoverageRequestId=" + getBenefitCoverageRequestId() +
            ", requestControlNumberExt='" + getRequestControlNumberExt() + "'" +
            ", asOnDate='" + getAsOnDate() + "'" +
            ", serviceType='" + getServiceType() + "'" +
            ", memberFirstName='" + getMemberFirstName() + "'" +
            ", memberLastName='" + getMemberLastName() + "'" +
            ", subscriberMemberId='" + getSubscriberMemberId() + "'" +
            ", memberGender='" + getMemberGender() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientGender='" + getPatientGender() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", patientRelationshipCode='" + getPatientRelationshipCode() + "'" +
            ", patientState='" + getPatientState() + "'" +
            ", coverageStatus='" + getCoverageStatus() + "'" +
            ", payerGroupNumber='" + getPayerGroupNumber() + "'" +
            ", serviceDate='" + getServiceDate() + "'" +
            ", planStartDate='" + getPlanStartDate() + "'" +
            ", responseJsonText='" + getResponseJsonText() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", benefitCoverageResponseUuid='" + getBenefitCoverageResponseUuid() + "'" +
            "}";
    }
}
