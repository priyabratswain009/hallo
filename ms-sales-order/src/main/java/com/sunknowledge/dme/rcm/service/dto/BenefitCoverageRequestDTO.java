package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest} entity.
 */
public class BenefitCoverageRequestDTO implements Serializable {

    private Long benefitCoverageRequestId;

    private String payerId;

    private String providerFirstName;

    private String providerLastName;

    private String providerType;

    private String providerNpi;

    private String providerCity;

    private String providerState;

    private String providerZipcode;

    private String submitterId;

    private LocalDate asOfDate;

    private String serviceType;

    private String memberId;

    private String patientLastName;

    private String patientFirstName;

    private LocalDate patientDob;

    private String patientGender;

    private String patientState;

    private String subscriberRelationship;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID benefitCoverageRequestUuid;

    public Long getBenefitCoverageRequestId() {
        return benefitCoverageRequestId;
    }

    public void setBenefitCoverageRequestId(Long benefitCoverageRequestId) {
        this.benefitCoverageRequestId = benefitCoverageRequestId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getProviderFirstName() {
        return providerFirstName;
    }

    public void setProviderFirstName(String providerFirstName) {
        this.providerFirstName = providerFirstName;
    }

    public String getProviderLastName() {
        return providerLastName;
    }

    public void setProviderLastName(String providerLastName) {
        this.providerLastName = providerLastName;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getProviderNpi() {
        return providerNpi;
    }

    public void setProviderNpi(String providerNpi) {
        this.providerNpi = providerNpi;
    }

    public String getProviderCity() {
        return providerCity;
    }

    public void setProviderCity(String providerCity) {
        this.providerCity = providerCity;
    }

    public String getProviderState() {
        return providerState;
    }

    public void setProviderState(String providerState) {
        this.providerState = providerState;
    }

    public String getProviderZipcode() {
        return providerZipcode;
    }

    public void setProviderZipcode(String providerZipcode) {
        this.providerZipcode = providerZipcode;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public LocalDate getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDate asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public LocalDate getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getBenefitCoverageRequestUuid() {
        return benefitCoverageRequestUuid;
    }

    public void setBenefitCoverageRequestUuid(UUID benefitCoverageRequestUuid) {
        this.benefitCoverageRequestUuid = benefitCoverageRequestUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BenefitCoverageRequestDTO)) {
            return false;
        }

        BenefitCoverageRequestDTO benefitCoverageRequestDTO = (BenefitCoverageRequestDTO) o;
        if (this.benefitCoverageRequestId == null) {
            return false;
        }
        return Objects.equals(this.benefitCoverageRequestId, benefitCoverageRequestDTO.benefitCoverageRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.benefitCoverageRequestId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BenefitCoverageRequestDTO{" +
            "benefitCoverageRequestId=" + getBenefitCoverageRequestId() +
            ", payerId='" + getPayerId() + "'" +
            ", providerFirstName='" + getProviderFirstName() + "'" +
            ", providerLastName='" + getProviderLastName() + "'" +
            ", providerType='" + getProviderType() + "'" +
            ", providerNpi='" + getProviderNpi() + "'" +
            ", providerCity='" + getProviderCity() + "'" +
            ", providerState='" + getProviderState() + "'" +
            ", providerZipcode='" + getProviderZipcode() + "'" +
            ", submitterId='" + getSubmitterId() + "'" +
            ", asOfDate='" + getAsOfDate() + "'" +
            ", serviceType='" + getServiceType() + "'" +
            ", memberId='" + getMemberId() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientDob='" + getPatientDob() + "'" +
            ", patientGender='" + getPatientGender() + "'" +
            ", patientState='" + getPatientState() + "'" +
            ", subscriberRelationship='" + getSubscriberRelationship() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", benefitCoverageRequestUuid='" + getBenefitCoverageRequestUuid() + "'" +
            "}";
    }
}
