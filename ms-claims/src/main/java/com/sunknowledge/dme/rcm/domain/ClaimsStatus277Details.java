package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ClaimsStatus277Details.
 */
@Entity
@Table(name = "t_claims_status_277_details")
public class ClaimsStatus277Details implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claim_status_277_detail_id", nullable = false)
    private Long claimStatus277DetailId;

    @Column(name = "claim_status_277_master_id")
    private Long claimStatus277MasterId;

    @Column(name = "procedure_code")
    private String procedureCode;

    @Column(name = "procedure_modifiers")
    private String procedureModifiers;

    @Column(name = "submitted_units")
    private String submittedUnits;

    @Column(name = "charge_amount")
    private Double chargeAmount;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "service_line_begin_date")
    private LocalDate serviceLineBeginDate;

    @Column(name = "service_line_end_date")
    private LocalDate serviceLineEndDate;

    @Column(name = "claim_status_category_code")
    private String claimStatusCategoryCode;

    @Column(name = "claim_status_category_code_value")
    private String claimStatusCategoryCodeValue;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "status_code_value")
    private String statusCodeValue;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "status")
    private String status;

    @Column(name = "claims_status_277_details_uuid")
    private UUID claimsStatus277DetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimStatus277DetailId() {
        return this.claimStatus277DetailId;
    }

    public ClaimsStatus277Details claimStatus277DetailId(Long claimStatus277DetailId) {
        this.setClaimStatus277DetailId(claimStatus277DetailId);
        return this;
    }

    public void setClaimStatus277DetailId(Long claimStatus277DetailId) {
        this.claimStatus277DetailId = claimStatus277DetailId;
    }

    public Long getClaimStatus277MasterId() {
        return this.claimStatus277MasterId;
    }

    public ClaimsStatus277Details claimStatus277MasterId(Long claimStatus277MasterId) {
        this.setClaimStatus277MasterId(claimStatus277MasterId);
        return this;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public String getProcedureCode() {
        return this.procedureCode;
    }

    public ClaimsStatus277Details procedureCode(String procedureCode) {
        this.setProcedureCode(procedureCode);
        return this;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureModifiers() {
        return this.procedureModifiers;
    }

    public ClaimsStatus277Details procedureModifiers(String procedureModifiers) {
        this.setProcedureModifiers(procedureModifiers);
        return this;
    }

    public void setProcedureModifiers(String procedureModifiers) {
        this.procedureModifiers = procedureModifiers;
    }

    public String getSubmittedUnits() {
        return this.submittedUnits;
    }

    public ClaimsStatus277Details submittedUnits(String submittedUnits) {
        this.setSubmittedUnits(submittedUnits);
        return this;
    }

    public void setSubmittedUnits(String submittedUnits) {
        this.submittedUnits = submittedUnits;
    }

    public Double getChargeAmount() {
        return this.chargeAmount;
    }

    public ClaimsStatus277Details chargeAmount(Double chargeAmount) {
        this.setChargeAmount(chargeAmount);
        return this;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Double getPaidAmount() {
        return this.paidAmount;
    }

    public ClaimsStatus277Details paidAmount(Double paidAmount) {
        this.setPaidAmount(paidAmount);
        return this;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getServiceLineBeginDate() {
        return this.serviceLineBeginDate;
    }

    public ClaimsStatus277Details serviceLineBeginDate(LocalDate serviceLineBeginDate) {
        this.setServiceLineBeginDate(serviceLineBeginDate);
        return this;
    }

    public void setServiceLineBeginDate(LocalDate serviceLineBeginDate) {
        this.serviceLineBeginDate = serviceLineBeginDate;
    }

    public LocalDate getServiceLineEndDate() {
        return this.serviceLineEndDate;
    }

    public ClaimsStatus277Details serviceLineEndDate(LocalDate serviceLineEndDate) {
        this.setServiceLineEndDate(serviceLineEndDate);
        return this;
    }

    public void setServiceLineEndDate(LocalDate serviceLineEndDate) {
        this.serviceLineEndDate = serviceLineEndDate;
    }

    public String getClaimStatusCategoryCode() {
        return this.claimStatusCategoryCode;
    }

    public ClaimsStatus277Details claimStatusCategoryCode(String claimStatusCategoryCode) {
        this.setClaimStatusCategoryCode(claimStatusCategoryCode);
        return this;
    }

    public void setClaimStatusCategoryCode(String claimStatusCategoryCode) {
        this.claimStatusCategoryCode = claimStatusCategoryCode;
    }

    public String getClaimStatusCategoryCodeValue() {
        return this.claimStatusCategoryCodeValue;
    }

    public ClaimsStatus277Details claimStatusCategoryCodeValue(String claimStatusCategoryCodeValue) {
        this.setClaimStatusCategoryCodeValue(claimStatusCategoryCodeValue);
        return this;
    }

    public void setClaimStatusCategoryCodeValue(String claimStatusCategoryCodeValue) {
        this.claimStatusCategoryCodeValue = claimStatusCategoryCodeValue;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public ClaimsStatus277Details statusCode(String statusCode) {
        this.setStatusCode(statusCode);
        return this;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCodeValue() {
        return this.statusCodeValue;
    }

    public ClaimsStatus277Details statusCodeValue(String statusCodeValue) {
        this.setStatusCodeValue(statusCodeValue);
        return this;
    }

    public void setStatusCodeValue(String statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    public LocalDate getEffectiveDate() {
        return this.effectiveDate;
    }

    public ClaimsStatus277Details effectiveDate(LocalDate effectiveDate) {
        this.setEffectiveDate(effectiveDate);
        return this;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimsStatus277Details status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getClaimsStatus277DetailsUuid() {
        return this.claimsStatus277DetailsUuid;
    }

    public ClaimsStatus277Details claimsStatus277DetailsUuid(UUID claimsStatus277DetailsUuid) {
        this.setClaimsStatus277DetailsUuid(claimsStatus277DetailsUuid);
        return this;
    }

    public void setClaimsStatus277DetailsUuid(UUID claimsStatus277DetailsUuid) {
        this.claimsStatus277DetailsUuid = claimsStatus277DetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsStatus277Details)) {
            return false;
        }
        return claimStatus277DetailId != null && claimStatus277DetailId.equals(((ClaimsStatus277Details) o).claimStatus277DetailId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsStatus277Details{" +
            "claimStatus277DetailId=" + getClaimStatus277DetailId() +
            ", claimStatus277MasterId=" + getClaimStatus277MasterId() +
            ", procedureCode='" + getProcedureCode() + "'" +
            ", procedureModifiers='" + getProcedureModifiers() + "'" +
            ", submittedUnits='" + getSubmittedUnits() + "'" +
            ", chargeAmount=" + getChargeAmount() +
            ", paidAmount=" + getPaidAmount() +
            ", serviceLineBeginDate='" + getServiceLineBeginDate() + "'" +
            ", serviceLineEndDate='" + getServiceLineEndDate() + "'" +
            ", claimStatusCategoryCode='" + getClaimStatusCategoryCode() + "'" +
            ", claimStatusCategoryCodeValue='" + getClaimStatusCategoryCodeValue() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", statusCodeValue='" + getStatusCodeValue() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", claimsStatus277DetailsUuid='" + getClaimsStatus277DetailsUuid() + "'" +
            "}";
    }
}
