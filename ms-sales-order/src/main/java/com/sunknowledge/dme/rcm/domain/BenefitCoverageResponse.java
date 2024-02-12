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
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BenefitCoverageResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("benefit_coverage_response_id")
    private Long benefitCoverageResponseId;

    @Column("benefit_coverage_request_id")
    private Long benefitCoverageRequestId;

    @Column("request_control_number_ext")
    private String requestControlNumberExt;

    @Column("as_on_date")
    private LocalDate asOnDate;

    @Column("service_type")
    private String serviceType;

    @Column("member_first_name")
    private String memberFirstName;

    @Column("member_last_name")
    private String memberLastName;

    @Column("subscriber_member_id")
    private String subscriberMemberId;

    @Column("member_gender")
    private String memberGender;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("patient_gender")
    private String patientGender;

    @Column("payer_name")
    private String payerName;

    @Column("patient_relationship_code")
    private String patientRelationshipCode;

    @Column("patient_state")
    private String patientState;

    @Column("coverage_status")
    private String coverageStatus;

    @Column("payer_group_number")
    private String payerGroupNumber;

    @Column("service_date")
    private LocalDate serviceDate;

    @Column("plan_start_date")
    private LocalDate planStartDate;

    @Column("response_json_text")
    private String responseJsonText;

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

    @Column("benefit_coverage_response_uuid")
    private UUID benefitCoverageResponseUuid;

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

    public Long getBenefitCoverageRequestId() {
        return this.benefitCoverageRequestId;
    }

    public BenefitCoverageResponse benefitCoverageRequestId(Long benefitCoverageRequestId) {
        this.setBenefitCoverageRequestId(benefitCoverageRequestId);
        return this;
    }

    public void setBenefitCoverageRequestId(Long benefitCoverageRequestId) {
        this.benefitCoverageRequestId = benefitCoverageRequestId;
    }

    public String getRequestControlNumberExt() {
        return this.requestControlNumberExt;
    }

    public BenefitCoverageResponse requestControlNumberExt(String requestControlNumberExt) {
        this.setRequestControlNumberExt(requestControlNumberExt);
        return this;
    }

    public void setRequestControlNumberExt(String requestControlNumberExt) {
        this.requestControlNumberExt = requestControlNumberExt;
    }

    public LocalDate getAsOnDate() {
        return this.asOnDate;
    }

    public BenefitCoverageResponse asOnDate(LocalDate asOnDate) {
        this.setAsOnDate(asOnDate);
        return this;
    }

    public void setAsOnDate(LocalDate asOnDate) {
        this.asOnDate = asOnDate;
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

    public String getMemberFirstName() {
        return this.memberFirstName;
    }

    public BenefitCoverageResponse memberFirstName(String memberFirstName) {
        this.setMemberFirstName(memberFirstName);
        return this;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getMemberLastName() {
        return this.memberLastName;
    }

    public BenefitCoverageResponse memberLastName(String memberLastName) {
        this.setMemberLastName(memberLastName);
        return this;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
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

    public String getMemberGender() {
        return this.memberGender;
    }

    public BenefitCoverageResponse memberGender(String memberGender) {
        this.setMemberGender(memberGender);
        return this;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public BenefitCoverageResponse patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public BenefitCoverageResponse patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientGender() {
        return this.patientGender;
    }

    public BenefitCoverageResponse patientGender(String patientGender) {
        this.setPatientGender(patientGender);
        return this;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public BenefitCoverageResponse payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
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

    public String getCoverageStatus() {
        return this.coverageStatus;
    }

    public BenefitCoverageResponse coverageStatus(String coverageStatus) {
        this.setCoverageStatus(coverageStatus);
        return this;
    }

    public void setCoverageStatus(String coverageStatus) {
        this.coverageStatus = coverageStatus;
    }

    public String getPayerGroupNumber() {
        return this.payerGroupNumber;
    }

    public BenefitCoverageResponse payerGroupNumber(String payerGroupNumber) {
        this.setPayerGroupNumber(payerGroupNumber);
        return this;
    }

    public void setPayerGroupNumber(String payerGroupNumber) {
        this.payerGroupNumber = payerGroupNumber;
    }

    public LocalDate getServiceDate() {
        return this.serviceDate;
    }

    public BenefitCoverageResponse serviceDate(LocalDate serviceDate) {
        this.setServiceDate(serviceDate);
        return this;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public LocalDate getPlanStartDate() {
        return this.planStartDate;
    }

    public BenefitCoverageResponse planStartDate(LocalDate planStartDate) {
        this.setPlanStartDate(planStartDate);
        return this;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getResponseJsonText() {
        return this.responseJsonText;
    }

    public BenefitCoverageResponse responseJsonText(String responseJsonText) {
        this.setResponseJsonText(responseJsonText);
        return this;
    }

    public void setResponseJsonText(String responseJsonText) {
        this.responseJsonText = responseJsonText;
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
