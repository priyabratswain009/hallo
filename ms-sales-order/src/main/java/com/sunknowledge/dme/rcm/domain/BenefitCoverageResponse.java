package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A BenefitCoverageResponse.
 */
@Table("t_benefit_coverage_response")
public class BenefitCoverageResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("benefit_coverage_response_id")
    private Long benefitCoverageResponseId;

    @Column("benefit_coverage_request_id")
    private String benefitCoverageRequestId;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("expiration_date")
    private LocalDate expirationDate;

    @Column("requested_date")
    private LocalDate requestedDate;

    @Column("response_date")
    private LocalDate responseDate;

    @Column("service_type")
    private String serviceType;

    @Column("subscriber_member_id")
    private String subscriberMemberId;

    @Column("patient_relationship_code")
    private String patientRelationshipCode;

    @Column("payer_id")
    private String payerId;

    @Column("provider_npi")
    private String providerNpi;

    @Column("plans_status_code")
    private String plansStatusCode;

    @Column("plans_status")
    private String plansStatus;

    @Column("primary_response")
    private String primaryResponse;

    @Column("secondary_response")
    private String secondaryResponse;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("benefit_coverage_response_uuid")
    private UUID benefitCoverageResponseUuid;

    @Column("patient_state")
    private String patientState;

    @Column("subscriber_relationship")
    private String subscriberRelationship;

    @Column("member_id")
    private String memberId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getBenefitCoverageResponseId() {
        return this.benefitCoverageResponseId;
    }

    public BenefitCoverageResponse benefitCoverageResponseId(Long benefitCoverageResponseId) {
        this.setBenefitCoverageResponseId(benefitCoverageResponseId);
        return this;
    }

    public void setBenefitCoverageResponseId(Long benefitCoverageResponseId) {
        this.benefitCoverageResponseId = benefitCoverageResponseId;
    }

    public String getBenefitCoverageRequestId() {
        return this.benefitCoverageRequestId;
    }

    public BenefitCoverageResponse benefitCoverageRequestId(String benefitCoverageRequestId) {
        this.setBenefitCoverageRequestId(benefitCoverageRequestId);
        return this;
    }

    public void setBenefitCoverageRequestId(String benefitCoverageRequestId) {
        this.benefitCoverageRequestId = benefitCoverageRequestId;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BenefitCoverageResponse createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BenefitCoverageResponse updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public BenefitCoverageResponse expirationDate(LocalDate expirationDate) {
        this.setExpirationDate(expirationDate);
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getRequestedDate() {
        return this.requestedDate;
    }

    public BenefitCoverageResponse requestedDate(LocalDate requestedDate) {
        this.setRequestedDate(requestedDate);
        return this;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDate getResponseDate() {
        return this.responseDate;
    }

    public BenefitCoverageResponse responseDate(LocalDate responseDate) {
        this.setResponseDate(responseDate);
        return this;
    }

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public BenefitCoverageResponse serviceType(String serviceType) {
        this.setServiceType(serviceType);
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSubscriberMemberId() {
        return this.subscriberMemberId;
    }

    public BenefitCoverageResponse subscriberMemberId(String subscriberMemberId) {
        this.setSubscriberMemberId(subscriberMemberId);
        return this;
    }

    public void setSubscriberMemberId(String subscriberMemberId) {
        this.subscriberMemberId = subscriberMemberId;
    }

    public String getPatientRelationshipCode() {
        return this.patientRelationshipCode;
    }

    public BenefitCoverageResponse patientRelationshipCode(String patientRelationshipCode) {
        this.setPatientRelationshipCode(patientRelationshipCode);
        return this;
    }

    public void setPatientRelationshipCode(String patientRelationshipCode) {
        this.patientRelationshipCode = patientRelationshipCode;
    }

    public String getPayerId() {
        return this.payerId;
    }

    public BenefitCoverageResponse payerId(String payerId) {
        this.setPayerId(payerId);
        return this;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getProviderNpi() {
        return this.providerNpi;
    }

    public BenefitCoverageResponse providerNpi(String providerNpi) {
        this.setProviderNpi(providerNpi);
        return this;
    }

    public void setProviderNpi(String providerNpi) {
        this.providerNpi = providerNpi;
    }

    public String getPlansStatusCode() {
        return this.plansStatusCode;
    }

    public BenefitCoverageResponse plansStatusCode(String plansStatusCode) {
        this.setPlansStatusCode(plansStatusCode);
        return this;
    }

    public void setPlansStatusCode(String plansStatusCode) {
        this.plansStatusCode = plansStatusCode;
    }

    public String getPlansStatus() {
        return this.plansStatus;
    }

    public BenefitCoverageResponse plansStatus(String plansStatus) {
        this.setPlansStatus(plansStatus);
        return this;
    }

    public void setPlansStatus(String plansStatus) {
        this.plansStatus = plansStatus;
    }

    public String getPrimaryResponse() {
        return this.primaryResponse;
    }

    public BenefitCoverageResponse primaryResponse(String primaryResponse) {
        this.setPrimaryResponse(primaryResponse);
        return this;
    }

    public void setPrimaryResponse(String primaryResponse) {
        this.primaryResponse = primaryResponse;
    }

    public String getSecondaryResponse() {
        return this.secondaryResponse;
    }

    public BenefitCoverageResponse secondaryResponse(String secondaryResponse) {
        this.setSecondaryResponse(secondaryResponse);
        return this;
    }

    public void setSecondaryResponse(String secondaryResponse) {
        this.secondaryResponse = secondaryResponse;
    }

    public String getStatus() {
        return this.status;
    }

    public BenefitCoverageResponse status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BenefitCoverageResponse createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BenefitCoverageResponse createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BenefitCoverageResponse updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BenefitCoverageResponse updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getBenefitCoverageResponseUuid() {
        return this.benefitCoverageResponseUuid;
    }

    public BenefitCoverageResponse benefitCoverageResponseUuid(UUID benefitCoverageResponseUuid) {
        this.setBenefitCoverageResponseUuid(benefitCoverageResponseUuid);
        return this;
    }

    public void setBenefitCoverageResponseUuid(UUID benefitCoverageResponseUuid) {
        this.benefitCoverageResponseUuid = benefitCoverageResponseUuid;
    }

    public String getPatientState() {
        return this.patientState;
    }

    public BenefitCoverageResponse patientState(String patientState) {
        this.setPatientState(patientState);
        return this;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getSubscriberRelationship() {
        return this.subscriberRelationship;
    }

    public BenefitCoverageResponse subscriberRelationship(String subscriberRelationship) {
        this.setSubscriberRelationship(subscriberRelationship);
        return this;
    }

    public void setSubscriberRelationship(String subscriberRelationship) {
        this.subscriberRelationship = subscriberRelationship;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public BenefitCoverageResponse memberId(String memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BenefitCoverageResponse)) {
            return false;
        }
        return (
            benefitCoverageResponseId != null && benefitCoverageResponseId.equals(((BenefitCoverageResponse) o).benefitCoverageResponseId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BenefitCoverageResponse{" +
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
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", benefitCoverageResponseUuid='" + getBenefitCoverageResponseUuid() + "'" +
            ", patientState='" + getPatientState() + "'" +
            ", subscriberRelationship='" + getSubscriberRelationship() + "'" +
            ", memberId='" + getMemberId() + "'" +
            "}";
    }
}
