package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master} entity.
 */
public class ClaimsStatus277MasterDTO implements Serializable {

    @NotNull
    private Long claimStatus277MasterId;

    private String controlNumber;

    private String fileName;

    private String tradingPartnerClaimNumber;

    private String patientFirstName;

    private String patientLastName;

    private String patientMemberId;

    private String serviceProviderName;

    private String serviceProviderNpi;

    private Double totalClaimChargeAmount;

    private Double totalClaimPaymentAmount;

    private LocalDate adjudicatedFinalizedDate;

    private LocalDate remittanceDate;

    private String claimStatusCategoryCode;

    private String claimStatusCategoryCodeValue;

    private String statusCode;

    private String statusCodeValue;

    private String clearinghouseTraceNumber;

    private String remittanceTraceNumber;

    private String patientAccountNumber;

    private LocalDate claimServiceBeginDate;

    private LocalDate claimServiceEndDate;

    private LocalDate statusInformationEffectiveDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID claimsStatus277MasterUuid;

    public Long getClaimStatus277MasterId() {
        return claimStatus277MasterId;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTradingPartnerClaimNumber() {
        return tradingPartnerClaimNumber;
    }

    public void setTradingPartnerClaimNumber(String tradingPartnerClaimNumber) {
        this.tradingPartnerClaimNumber = tradingPartnerClaimNumber;
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

    public String getPatientMemberId() {
        return patientMemberId;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public String getServiceProviderNpi() {
        return serviceProviderNpi;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public Double getTotalClaimChargeAmount() {
        return totalClaimChargeAmount;
    }

    public void setTotalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.totalClaimChargeAmount = totalClaimChargeAmount;
    }

    public Double getTotalClaimPaymentAmount() {
        return totalClaimPaymentAmount;
    }

    public void setTotalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.totalClaimPaymentAmount = totalClaimPaymentAmount;
    }

    public LocalDate getAdjudicatedFinalizedDate() {
        return adjudicatedFinalizedDate;
    }

    public void setAdjudicatedFinalizedDate(LocalDate adjudicatedFinalizedDate) {
        this.adjudicatedFinalizedDate = adjudicatedFinalizedDate;
    }

    public LocalDate getRemittanceDate() {
        return remittanceDate;
    }

    public void setRemittanceDate(LocalDate remittanceDate) {
        this.remittanceDate = remittanceDate;
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

    public String getClearinghouseTraceNumber() {
        return clearinghouseTraceNumber;
    }

    public void setClearinghouseTraceNumber(String clearinghouseTraceNumber) {
        this.clearinghouseTraceNumber = clearinghouseTraceNumber;
    }

    public String getRemittanceTraceNumber() {
        return remittanceTraceNumber;
    }

    public void setRemittanceTraceNumber(String remittanceTraceNumber) {
        this.remittanceTraceNumber = remittanceTraceNumber;
    }

    public String getPatientAccountNumber() {
        return patientAccountNumber;
    }

    public void setPatientAccountNumber(String patientAccountNumber) {
        this.patientAccountNumber = patientAccountNumber;
    }

    public LocalDate getClaimServiceBeginDate() {
        return claimServiceBeginDate;
    }

    public void setClaimServiceBeginDate(LocalDate claimServiceBeginDate) {
        this.claimServiceBeginDate = claimServiceBeginDate;
    }

    public LocalDate getClaimServiceEndDate() {
        return claimServiceEndDate;
    }

    public void setClaimServiceEndDate(LocalDate claimServiceEndDate) {
        this.claimServiceEndDate = claimServiceEndDate;
    }

    public LocalDate getStatusInformationEffectiveDate() {
        return statusInformationEffectiveDate;
    }

    public void setStatusInformationEffectiveDate(LocalDate statusInformationEffectiveDate) {
        this.statusInformationEffectiveDate = statusInformationEffectiveDate;
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

    public UUID getClaimsStatus277MasterUuid() {
        return claimsStatus277MasterUuid;
    }

    public void setClaimsStatus277MasterUuid(UUID claimsStatus277MasterUuid) {
        this.claimsStatus277MasterUuid = claimsStatus277MasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsStatus277MasterDTO)) {
            return false;
        }

        ClaimsStatus277MasterDTO claimsStatus277MasterDTO = (ClaimsStatus277MasterDTO) o;
        if (this.claimStatus277MasterId == null) {
            return false;
        }
        return Objects.equals(this.claimStatus277MasterId, claimsStatus277MasterDTO.claimStatus277MasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimStatus277MasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsStatus277MasterDTO{" +
            "claimStatus277MasterId=" + getClaimStatus277MasterId() +
            ", controlNumber='" + getControlNumber() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", tradingPartnerClaimNumber='" + getTradingPartnerClaimNumber() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientMemberId='" + getPatientMemberId() + "'" +
            ", serviceProviderName='" + getServiceProviderName() + "'" +
            ", serviceProviderNpi='" + getServiceProviderNpi() + "'" +
            ", totalClaimChargeAmount=" + getTotalClaimChargeAmount() +
            ", totalClaimPaymentAmount=" + getTotalClaimPaymentAmount() +
            ", adjudicatedFinalizedDate='" + getAdjudicatedFinalizedDate() + "'" +
            ", remittanceDate='" + getRemittanceDate() + "'" +
            ", claimStatusCategoryCode='" + getClaimStatusCategoryCode() + "'" +
            ", claimStatusCategoryCodeValue='" + getClaimStatusCategoryCodeValue() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", statusCodeValue='" + getStatusCodeValue() + "'" +
            ", clearinghouseTraceNumber='" + getClearinghouseTraceNumber() + "'" +
            ", remittanceTraceNumber='" + getRemittanceTraceNumber() + "'" +
            ", patientAccountNumber='" + getPatientAccountNumber() + "'" +
            ", claimServiceBeginDate='" + getClaimServiceBeginDate() + "'" +
            ", claimServiceEndDate='" + getClaimServiceEndDate() + "'" +
            ", statusInformationEffectiveDate='" + getStatusInformationEffectiveDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", claimsStatus277MasterUuid='" + getClaimsStatus277MasterUuid() + "'" +
            "}";
    }
}
