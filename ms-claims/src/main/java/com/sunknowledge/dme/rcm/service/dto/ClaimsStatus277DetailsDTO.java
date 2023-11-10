package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimsStatus277Details} entity.
 */
public class ClaimsStatus277DetailsDTO implements Serializable {

    @NotNull
    private Long claimStatus277DetailId;

    private Long claimStatus277MasterId;

    private String procedureCode;

    private String procedureModifiers;

    private String submittedUnits;

    private Double chargeAmount;

    private Double paidAmount;

    private LocalDate serviceLineBeginDate;

    private LocalDate serviceLineEndDate;

    private String claimStatusCategoryCode;

    private String claimStatusCategoryCodeValue;

    private String statusCode;

    private String statusCodeValue;

    private LocalDate effectiveDate;

    private String status;

    private UUID claimsStatus277DetailsUuid;

    public Long getClaimStatus277DetailId() {
        return claimStatus277DetailId;
    }

    public void setClaimStatus277DetailId(Long claimStatus277DetailId) {
        this.claimStatus277DetailId = claimStatus277DetailId;
    }

    public Long getClaimStatus277MasterId() {
        return claimStatus277MasterId;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureModifiers() {
        return procedureModifiers;
    }

    public void setProcedureModifiers(String procedureModifiers) {
        this.procedureModifiers = procedureModifiers;
    }

    public String getSubmittedUnits() {
        return submittedUnits;
    }

    public void setSubmittedUnits(String submittedUnits) {
        this.submittedUnits = submittedUnits;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getServiceLineBeginDate() {
        return serviceLineBeginDate;
    }

    public void setServiceLineBeginDate(LocalDate serviceLineBeginDate) {
        this.serviceLineBeginDate = serviceLineBeginDate;
    }

    public LocalDate getServiceLineEndDate() {
        return serviceLineEndDate;
    }

    public void setServiceLineEndDate(LocalDate serviceLineEndDate) {
        this.serviceLineEndDate = serviceLineEndDate;
    }

    public String getClaimStatusCategoryCode() {
        return claimStatusCategoryCode;
    }

    public void setClaimStatusCategoryCode(String claimStatusCategoryCode) {
        this.claimStatusCategoryCode = claimStatusCategoryCode;
    }

    public String getClaimStatusCategoryCodeValue() {
        return claimStatusCategoryCodeValue;
    }

    public void setClaimStatusCategoryCodeValue(String claimStatusCategoryCodeValue) {
        this.claimStatusCategoryCodeValue = claimStatusCategoryCodeValue;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCodeValue() {
        return statusCodeValue;
    }

    public void setStatusCodeValue(String statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getClaimsStatus277DetailsUuid() {
        return claimsStatus277DetailsUuid;
    }

    public void setClaimsStatus277DetailsUuid(UUID claimsStatus277DetailsUuid) {
        this.claimsStatus277DetailsUuid = claimsStatus277DetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsStatus277DetailsDTO)) {
            return false;
        }

        ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO = (ClaimsStatus277DetailsDTO) o;
        if (this.claimStatus277DetailId == null) {
            return false;
        }
        return Objects.equals(this.claimStatus277DetailId, claimsStatus277DetailsDTO.claimStatus277DetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimStatus277DetailId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsStatus277DetailsDTO{" +
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
