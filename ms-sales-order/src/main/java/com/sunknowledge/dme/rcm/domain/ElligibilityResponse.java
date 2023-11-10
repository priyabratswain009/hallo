package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ElligibilityResponse.
 */
@Table("t_elligibility_response_status")
public class ElligibilityResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("elligibility_response_status_id")
    private Long elligibilityResponseStatusId;

    @NotNull(message = "must not be null")
    @Column("elligibility_control_number")
    private String elligibilityControlNumber;

    @Column("traceid")
    private String traceid;

    @Column("subscriber_first_name")
    private String subscriberFirstName;

    @Column("subscriber_last_name")
    private String subscriberLastName;

    @Column("subscriber_gender")
    private String subscriberGender;

    @Column("subscriber_dob")
    private LocalDate subscriberDob;

    @Column("subscriber_identifier")
    private String subscriberIdentifier;

    @Column("subscriber_entitytype")
    private String subscriberEntitytype;

    @Column("subscriber_ssn")
    private String subscriberSsn;

    @Column("payer_identifier")
    private String payerIdentifier;

    @Column("payer_entitytype")
    private String payerEntitytype;

    @Column("payer_name")
    private String payerName;

    @Column("payer_identification")
    private String payerIdentification;

    @Column("plan_ssn")
    private String planSsn;

    @Column("plan_date")
    private String planDate;

    @Column("plan_status_code")
    private String planStatusCode;

    @Column("plan_status")
    private String planStatus;

    @Column("plan_details")
    private String planDetails;

    @Column("service_type_codes")
    private String serviceTypeCodes;

    @Column("elligibility_response_status_uuid")
    private UUID elligibilityResponseStatusUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getElligibilityResponseStatusId() {
        return this.elligibilityResponseStatusId;
    }

    public ElligibilityResponse elligibilityResponseStatusId(Long elligibilityResponseStatusId) {
        this.setElligibilityResponseStatusId(elligibilityResponseStatusId);
        return this;
    }

    public void setElligibilityResponseStatusId(Long elligibilityResponseStatusId) {
        this.elligibilityResponseStatusId = elligibilityResponseStatusId;
    }

    public String getElligibilityControlNumber() {
        return this.elligibilityControlNumber;
    }

    public ElligibilityResponse elligibilityControlNumber(String elligibilityControlNumber) {
        this.setElligibilityControlNumber(elligibilityControlNumber);
        return this;
    }

    public void setElligibilityControlNumber(String elligibilityControlNumber) {
        this.elligibilityControlNumber = elligibilityControlNumber;
    }

    public String getTraceid() {
        return this.traceid;
    }

    public ElligibilityResponse traceid(String traceid) {
        this.setTraceid(traceid);
        return this;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public String getSubscriberFirstName() {
        return this.subscriberFirstName;
    }

    public ElligibilityResponse subscriberFirstName(String subscriberFirstName) {
        this.setSubscriberFirstName(subscriberFirstName);
        return this;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return this.subscriberLastName;
    }

    public ElligibilityResponse subscriberLastName(String subscriberLastName) {
        this.setSubscriberLastName(subscriberLastName);
        return this;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return this.subscriberGender;
    }

    public ElligibilityResponse subscriberGender(String subscriberGender) {
        this.setSubscriberGender(subscriberGender);
        return this;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return this.subscriberDob;
    }

    public ElligibilityResponse subscriberDob(LocalDate subscriberDob) {
        this.setSubscriberDob(subscriberDob);
        return this;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getSubscriberIdentifier() {
        return this.subscriberIdentifier;
    }

    public ElligibilityResponse subscriberIdentifier(String subscriberIdentifier) {
        this.setSubscriberIdentifier(subscriberIdentifier);
        return this;
    }

    public void setSubscriberIdentifier(String subscriberIdentifier) {
        this.subscriberIdentifier = subscriberIdentifier;
    }

    public String getSubscriberEntitytype() {
        return this.subscriberEntitytype;
    }

    public ElligibilityResponse subscriberEntitytype(String subscriberEntitytype) {
        this.setSubscriberEntitytype(subscriberEntitytype);
        return this;
    }

    public void setSubscriberEntitytype(String subscriberEntitytype) {
        this.subscriberEntitytype = subscriberEntitytype;
    }

    public String getSubscriberSsn() {
        return this.subscriberSsn;
    }

    public ElligibilityResponse subscriberSsn(String subscriberSsn) {
        this.setSubscriberSsn(subscriberSsn);
        return this;
    }

    public void setSubscriberSsn(String subscriberSsn) {
        this.subscriberSsn = subscriberSsn;
    }

    public String getPayerIdentifier() {
        return this.payerIdentifier;
    }

    public ElligibilityResponse payerIdentifier(String payerIdentifier) {
        this.setPayerIdentifier(payerIdentifier);
        return this;
    }

    public void setPayerIdentifier(String payerIdentifier) {
        this.payerIdentifier = payerIdentifier;
    }

    public String getPayerEntitytype() {
        return this.payerEntitytype;
    }

    public ElligibilityResponse payerEntitytype(String payerEntitytype) {
        this.setPayerEntitytype(payerEntitytype);
        return this;
    }

    public void setPayerEntitytype(String payerEntitytype) {
        this.payerEntitytype = payerEntitytype;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public ElligibilityResponse payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerIdentification() {
        return this.payerIdentification;
    }

    public ElligibilityResponse payerIdentification(String payerIdentification) {
        this.setPayerIdentification(payerIdentification);
        return this;
    }

    public void setPayerIdentification(String payerIdentification) {
        this.payerIdentification = payerIdentification;
    }

    public String getPlanSsn() {
        return this.planSsn;
    }

    public ElligibilityResponse planSsn(String planSsn) {
        this.setPlanSsn(planSsn);
        return this;
    }

    public void setPlanSsn(String planSsn) {
        this.planSsn = planSsn;
    }

    public String getPlanDate() {
        return this.planDate;
    }

    public ElligibilityResponse planDate(String planDate) {
        this.setPlanDate(planDate);
        return this;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getPlanStatusCode() {
        return this.planStatusCode;
    }

    public ElligibilityResponse planStatusCode(String planStatusCode) {
        this.setPlanStatusCode(planStatusCode);
        return this;
    }

    public void setPlanStatusCode(String planStatusCode) {
        this.planStatusCode = planStatusCode;
    }

    public String getPlanStatus() {
        return this.planStatus;
    }

    public ElligibilityResponse planStatus(String planStatus) {
        this.setPlanStatus(planStatus);
        return this;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanDetails() {
        return this.planDetails;
    }

    public ElligibilityResponse planDetails(String planDetails) {
        this.setPlanDetails(planDetails);
        return this;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails;
    }

    public String getServiceTypeCodes() {
        return this.serviceTypeCodes;
    }

    public ElligibilityResponse serviceTypeCodes(String serviceTypeCodes) {
        this.setServiceTypeCodes(serviceTypeCodes);
        return this;
    }

    public void setServiceTypeCodes(String serviceTypeCodes) {
        this.serviceTypeCodes = serviceTypeCodes;
    }

    public UUID getElligibilityResponseStatusUuid() {
        return this.elligibilityResponseStatusUuid;
    }

    public ElligibilityResponse elligibilityResponseStatusUuid(UUID elligibilityResponseStatusUuid) {
        this.setElligibilityResponseStatusUuid(elligibilityResponseStatusUuid);
        return this;
    }

    public void setElligibilityResponseStatusUuid(UUID elligibilityResponseStatusUuid) {
        this.elligibilityResponseStatusUuid = elligibilityResponseStatusUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElligibilityResponse)) {
            return false;
        }
        return (
            elligibilityResponseStatusId != null &&
            elligibilityResponseStatusId.equals(((ElligibilityResponse) o).elligibilityResponseStatusId)
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
        return "ElligibilityResponse{" +
            "elligibilityResponseStatusId=" + getElligibilityResponseStatusId() +
            ", elligibilityControlNumber='" + getElligibilityControlNumber() + "'" +
            ", traceid='" + getTraceid() + "'" +
            ", subscriberFirstName='" + getSubscriberFirstName() + "'" +
            ", subscriberLastName='" + getSubscriberLastName() + "'" +
            ", subscriberGender='" + getSubscriberGender() + "'" +
            ", subscriberDob='" + getSubscriberDob() + "'" +
            ", subscriberIdentifier='" + getSubscriberIdentifier() + "'" +
            ", subscriberEntitytype='" + getSubscriberEntitytype() + "'" +
            ", subscriberSsn='" + getSubscriberSsn() + "'" +
            ", payerIdentifier='" + getPayerIdentifier() + "'" +
            ", payerEntitytype='" + getPayerEntitytype() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", payerIdentification='" + getPayerIdentification() + "'" +
            ", planSsn='" + getPlanSsn() + "'" +
            ", planDate='" + getPlanDate() + "'" +
            ", planStatusCode='" + getPlanStatusCode() + "'" +
            ", planStatus='" + getPlanStatus() + "'" +
            ", planDetails='" + getPlanDetails() + "'" +
            ", serviceTypeCodes='" + getServiceTypeCodes() + "'" +
            ", elligibilityResponseStatusUuid='" + getElligibilityResponseStatusUuid() + "'" +
            "}";
    }
}
