package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse} entity.
 */
public class BenefitCoverageResponseDTO implements Serializable {

    private Long benefitCoverageResponseId;

    private String benefitCoverageRequestId;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private LocalDate expirationDate;

    private LocalDate requestedDate;

    private LocalDate responseDate;

    private String serviceType;

    private String subscriberMemberId;

    private String patientRelationshipCode;

    private String payerId;

    private String providerNpi;

    private String plansStatusCode;

    private String plansStatus;

    private String primaryResponse;

    private String secondaryResponse;

    private String status;

    private String patientState;

    private String subscriberRelationship;

    private Long createdById;

    private String createdByName;

    private Long updatedById;

    private String updatedByName;

    private UUID benefitCoverageResponseUuid;

    private String memberId;

    public Long getBenefitCoverageResponseId() {
        return benefitCoverageResponseId;
    }

    public void setBenefitCoverageResponseId(Long benefitCoverageResponseId) {
        this.benefitCoverageResponseId = benefitCoverageResponseId;
    }

    public String getBenefitCoverageRequestId() {
        return benefitCoverageRequestId;
    }

    public void setBenefitCoverageRequestId(String benefitCoverageRequestId) {
        this.benefitCoverageRequestId = benefitCoverageRequestId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSubscriberMemberId() {
        return subscriberMemberId;
    }

    public void setSubscriberMemberId(String subscriberMemberId) {
        this.subscriberMemberId = subscriberMemberId;
    }

    public String getPatientRelationshipCode() {
        return patientRelationshipCode;
    }

    public void setPatientRelationshipCode(String patientRelationshipCode) {
        this.patientRelationshipCode = patientRelationshipCode;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getProviderNpi() {
        return providerNpi;
    }

    public void setProviderNpi(String providerNpi) {
        this.providerNpi = providerNpi;
    }

    public String getPlansStatusCode() {
        return plansStatusCode;
    }

    public void setPlansStatusCode(String plansStatusCode) {
        this.plansStatusCode = plansStatusCode;
    }

    public String getPlansStatus() {
        return plansStatus;
    }

    public void setPlansStatus(String plansStatus) {
        this.plansStatus = plansStatus;
    }

    public String getPrimaryResponse() {
        return primaryResponse;
    }

    public void setPrimaryResponse(String primaryResponse) {
        this.primaryResponse = primaryResponse;
    }

    public String getSecondaryResponse() {
        return secondaryResponse;
    }

    public void setSecondaryResponse(String secondaryResponse) {
        this.secondaryResponse = secondaryResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getSubscriberRelationship() {
        return subscriberRelationship;
    }

    public void setSubscriberRelationship(String subscriberRelationship) {
        this.subscriberRelationship = subscriberRelationship;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
            ", benefitCoverageRequestId='" + getBenefitCoverageRequestId() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", requestedDate='" + getRequestedDate() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            ", serviceType='" + getServiceType() + "'" +
            ", subscriberMemberId='" + getSubscriberMemberId() + "'" +
            ", patientRelationshipCode='" + getPatientRelationshipCode() + "'" +
            ", payerId='" + getPayerId() + "'" +
            ", providerNpi='" + getProviderNpi() + "'" +
            ", plansStatusCode='" + getPlansStatusCode() + "'" +
            ", plansStatus='" + getPlansStatus() + "'" +
            ", primaryResponse='" + getPrimaryResponse() + "'" +
            ", secondaryResponse='" + getSecondaryResponse() + "'" +
            ", status='" + getStatus() + "'" +
            ", patientState='" + getPatientState() + "'" +
            ", subscriberRelationship='" + getSubscriberRelationship() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", benefitCoverageResponseUuid='" + getBenefitCoverageResponseUuid() + "'" +
            ", memberId='" + getMemberId() + "'" +
            "}";
    }
}
