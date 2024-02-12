package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponse} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ElligibilityResponseDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long elligibilityResponseStatusId;

    @NotNull(message = "must not be null")
    private String elligibilityControlNumber;

    private String traceid;

    private String subscriberFirstName;

    private String subscriberLastName;

    private String subscriberGender;

    private LocalDate subscriberDob;

    private String subscriberIdentifier;

    private String subscriberEntitytype;

    private String subscriberSsn;

    private String payerIdentifier;

    private String payerEntitytype;

    private String payerName;

    private String payerIdentification;

    private String planSsn;

    private String planDate;

    private String planStatusCode;

    private String planStatus;

    private String planDetails;

    private String serviceTypeCodes;

    private UUID elligibilityResponseStatusUuid;

    public Long getElligibilityResponseStatusId() {
        return elligibilityResponseStatusId;
    }

    public void setElligibilityResponseStatusId(Long elligibilityResponseStatusId) {
        this.elligibilityResponseStatusId = elligibilityResponseStatusId;
    }

    public String getElligibilityControlNumber() {
        return elligibilityControlNumber;
    }

    public void setElligibilityControlNumber(String elligibilityControlNumber) {
        this.elligibilityControlNumber = elligibilityControlNumber;
    }

    public String getTraceid() {
        return traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public String getSubscriberFirstName() {
        return subscriberFirstName;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return subscriberLastName;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return subscriberGender;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return subscriberDob;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getSubscriberIdentifier() {
        return subscriberIdentifier;
    }

    public void setSubscriberIdentifier(String subscriberIdentifier) {
        this.subscriberIdentifier = subscriberIdentifier;
    }

    public String getSubscriberEntitytype() {
        return subscriberEntitytype;
    }

    public void setSubscriberEntitytype(String subscriberEntitytype) {
        this.subscriberEntitytype = subscriberEntitytype;
    }

    public String getSubscriberSsn() {
        return subscriberSsn;
    }

    public void setSubscriberSsn(String subscriberSsn) {
        this.subscriberSsn = subscriberSsn;
    }

    public String getPayerIdentifier() {
        return payerIdentifier;
    }

    public void setPayerIdentifier(String payerIdentifier) {
        this.payerIdentifier = payerIdentifier;
    }

    public String getPayerEntitytype() {
        return payerEntitytype;
    }

    public void setPayerEntitytype(String payerEntitytype) {
        this.payerEntitytype = payerEntitytype;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerIdentification() {
        return payerIdentification;
    }

    public void setPayerIdentification(String payerIdentification) {
        this.payerIdentification = payerIdentification;
    }

    public String getPlanSsn() {
        return planSsn;
    }

    public void setPlanSsn(String planSsn) {
        this.planSsn = planSsn;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getPlanStatusCode() {
        return planStatusCode;
    }

    public void setPlanStatusCode(String planStatusCode) {
        this.planStatusCode = planStatusCode;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails;
    }

    public String getServiceTypeCodes() {
        return serviceTypeCodes;
    }

    public void setServiceTypeCodes(String serviceTypeCodes) {
        this.serviceTypeCodes = serviceTypeCodes;
    }

    public UUID getElligibilityResponseStatusUuid() {
        return elligibilityResponseStatusUuid;
    }

    public void setElligibilityResponseStatusUuid(UUID elligibilityResponseStatusUuid) {
        this.elligibilityResponseStatusUuid = elligibilityResponseStatusUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElligibilityResponseDTO)) {
            return false;
        }

        ElligibilityResponseDTO elligibilityResponseDTO = (ElligibilityResponseDTO) o;
        if (this.elligibilityResponseStatusId == null) {
            return false;
        }
        return Objects.equals(this.elligibilityResponseStatusId, elligibilityResponseDTO.elligibilityResponseStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.elligibilityResponseStatusId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElligibilityResponseDTO{" +
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
