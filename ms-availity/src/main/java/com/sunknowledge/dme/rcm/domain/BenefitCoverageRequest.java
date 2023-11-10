package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A BenefitCoverageRequest.
 */
@Entity
@Table(name = "t_benefit_coverage_request")
public class BenefitCoverageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "benefit_coverage_request_id")
    private Long benefitCoverageRequestId;

    @Column(name = "payer_id")
    private String payerId;

    @Column(name = "provider_first_name")
    private String providerFirstName;

    @Column(name = "provider_last_name")
    private String providerLastName;

    @Column(name = "provider_type")
    private String providerType;

    @Column(name = "provider_npi")
    private String providerNpi;

    @Column(name = "provider_city")
    private String providerCity;

    @Column(name = "provider_state")
    private String providerState;

    @Column(name = "provider_zipcode")
    private String providerZipcode;

    @Column(name = "submitter_id")
    private String submitterId;

    @Column(name = "as_of_date")
    private LocalDate asOfDate;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "patient_last_name")
    private String patientLastName;

    @Column(name = "patient_first_name")
    private String patientFirstName;

    @Column(name = "patient_dob")
    private LocalDate patientDob;

    @Column(name = "patient_gender")
    private String patientGender;

    @Column(name = "patient_state")
    private String patientState;

    @Column(name = "subscriber_relationship")
    private String subscriberRelationship;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "benefit_coverage_request_uuid")
    private UUID benefitCoverageRequestUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getBenefitCoverageRequestId() {
        return this.benefitCoverageRequestId;
    }

    public BenefitCoverageRequest benefitCoverageRequestId(Long benefitCoverageRequestId) {
        this.setBenefitCoverageRequestId(benefitCoverageRequestId);
        return this;
    }

    public void setBenefitCoverageRequestId(Long benefitCoverageRequestId) {
        this.benefitCoverageRequestId = benefitCoverageRequestId;
    }

    public String getPayerId() {
        return this.payerId;
    }

    public BenefitCoverageRequest payerId(String payerId) {
        this.setPayerId(payerId);
        return this;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getProviderFirstName() {
        return this.providerFirstName;
    }

    public BenefitCoverageRequest providerFirstName(String providerFirstName) {
        this.setProviderFirstName(providerFirstName);
        return this;
    }

    public void setProviderFirstName(String providerFirstName) {
        this.providerFirstName = providerFirstName;
    }

    public String getProviderLastName() {
        return this.providerLastName;
    }

    public BenefitCoverageRequest providerLastName(String providerLastName) {
        this.setProviderLastName(providerLastName);
        return this;
    }

    public void setProviderLastName(String providerLastName) {
        this.providerLastName = providerLastName;
    }

    public String getProviderType() {
        return this.providerType;
    }

    public BenefitCoverageRequest providerType(String providerType) {
        this.setProviderType(providerType);
        return this;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getProviderNpi() {
        return this.providerNpi;
    }

    public BenefitCoverageRequest providerNpi(String providerNpi) {
        this.setProviderNpi(providerNpi);
        return this;
    }

    public void setProviderNpi(String providerNpi) {
        this.providerNpi = providerNpi;
    }

    public String getProviderCity() {
        return this.providerCity;
    }

    public BenefitCoverageRequest providerCity(String providerCity) {
        this.setProviderCity(providerCity);
        return this;
    }

    public void setProviderCity(String providerCity) {
        this.providerCity = providerCity;
    }

    public String getProviderState() {
        return this.providerState;
    }

    public BenefitCoverageRequest providerState(String providerState) {
        this.setProviderState(providerState);
        return this;
    }

    public void setProviderState(String providerState) {
        this.providerState = providerState;
    }

    public String getProviderZipcode() {
        return this.providerZipcode;
    }

    public BenefitCoverageRequest providerZipcode(String providerZipcode) {
        this.setProviderZipcode(providerZipcode);
        return this;
    }

    public void setProviderZipcode(String providerZipcode) {
        this.providerZipcode = providerZipcode;
    }

    public String getSubmitterId() {
        return this.submitterId;
    }

    public BenefitCoverageRequest submitterId(String submitterId) {
        this.setSubmitterId(submitterId);
        return this;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public LocalDate getAsOfDate() {
        return this.asOfDate;
    }

    public BenefitCoverageRequest asOfDate(LocalDate asOfDate) {
        this.setAsOfDate(asOfDate);
        return this;
    }

    public void setAsOfDate(LocalDate asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public BenefitCoverageRequest serviceType(String serviceType) {
        this.setServiceType(serviceType);
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public BenefitCoverageRequest memberId(String memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public BenefitCoverageRequest patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public BenefitCoverageRequest patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public LocalDate getPatientDob() {
        return this.patientDob;
    }

    public BenefitCoverageRequest patientDob(LocalDate patientDob) {
        this.setPatientDob(patientDob);
        return this;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientGender() {
        return this.patientGender;
    }

    public BenefitCoverageRequest patientGender(String patientGender) {
        this.setPatientGender(patientGender);
        return this;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientState() {
        return this.patientState;
    }

    public BenefitCoverageRequest patientState(String patientState) {
        this.setPatientState(patientState);
        return this;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getSubscriberRelationship() {
        return this.subscriberRelationship;
    }

    public BenefitCoverageRequest subscriberRelationship(String subscriberRelationship) {
        this.setSubscriberRelationship(subscriberRelationship);
        return this;
    }

    public void setSubscriberRelationship(String subscriberRelationship) {
        this.subscriberRelationship = subscriberRelationship;
    }

    public String getStatus() {
        return this.status;
    }

    public BenefitCoverageRequest status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BenefitCoverageRequest createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BenefitCoverageRequest createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BenefitCoverageRequest createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BenefitCoverageRequest updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BenefitCoverageRequest updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BenefitCoverageRequest updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getBenefitCoverageRequestUuid() {
        return this.benefitCoverageRequestUuid;
    }

    public BenefitCoverageRequest benefitCoverageRequestUuid(UUID benefitCoverageRequestUuid) {
        this.setBenefitCoverageRequestUuid(benefitCoverageRequestUuid);
        return this;
    }

    public void setBenefitCoverageRequestUuid(UUID benefitCoverageRequestUuid) {
        this.benefitCoverageRequestUuid = benefitCoverageRequestUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BenefitCoverageRequest)) {
            return false;
        }
        return benefitCoverageRequestId != null && benefitCoverageRequestId.equals(((BenefitCoverageRequest) o).benefitCoverageRequestId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BenefitCoverageRequest{" +
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
