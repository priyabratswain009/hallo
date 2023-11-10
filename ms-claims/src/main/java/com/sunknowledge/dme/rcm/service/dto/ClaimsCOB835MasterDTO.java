package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master} entity.
 */
public class ClaimsCOB835MasterDTO implements Serializable {

    @NotNull
    private Long claimCob835MasterId;

    private String patientControlNumber;

    private String payerClaimControlNumber;

    private String patientFirstName;

    private String patientLastName;

    private String patientMemberId;

    private String checkOrEFTTraceNumber;

    private LocalDate checkIssueOrEFTEffectiveDate;

    private String creditOrDebitFlagCode;

    private String paymentMethodCode;

    private Double totalClaimChargeAmount;

    private Double totalClaimPaymentAmount;

    private Double totalPatientResponsibilityAmount;

    private Boolean crossoverCarrierName;

    private String crossoverCarrierPayerId;

    private String crossoverCarrierPayerName;

    private String payerName;

    private String payeeName;

    private String payeeNpi;

    private LocalDate claimReceivedDate;

    private String fileName;

    private LocalDate receivedOn;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID claimsCob835MasterUuid;

    private Double chequeEftAmount;

    private String cobType;

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPatientControlNumber() {
        return patientControlNumber;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPayerClaimControlNumber() {
        return payerClaimControlNumber;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
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

    public String getCheckOrEFTTraceNumber() {
        return checkOrEFTTraceNumber;
    }

    public void setCheckOrEFTTraceNumber(String checkOrEFTTraceNumber) {
        this.checkOrEFTTraceNumber = checkOrEFTTraceNumber;
    }

    public LocalDate getCheckIssueOrEFTEffectiveDate() {
        return checkIssueOrEFTEffectiveDate;
    }

    public void setCheckIssueOrEFTEffectiveDate(LocalDate checkIssueOrEFTEffectiveDate) {
        this.checkIssueOrEFTEffectiveDate = checkIssueOrEFTEffectiveDate;
    }

    public String getCreditOrDebitFlagCode() {
        return creditOrDebitFlagCode;
    }

    public void setCreditOrDebitFlagCode(String creditOrDebitFlagCode) {
        this.creditOrDebitFlagCode = creditOrDebitFlagCode;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
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

    public Double getTotalPatientResponsibilityAmount() {
        return totalPatientResponsibilityAmount;
    }

    public void setTotalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.totalPatientResponsibilityAmount = totalPatientResponsibilityAmount;
    }

    public Boolean getCrossoverCarrierName() {
        return crossoverCarrierName;
    }

    public void setCrossoverCarrierName(Boolean crossoverCarrierName) {
        this.crossoverCarrierName = crossoverCarrierName;
    }

    public String getCrossoverCarrierPayerId() {
        return crossoverCarrierPayerId;
    }

    public void setCrossoverCarrierPayerId(String crossoverCarrierPayerId) {
        this.crossoverCarrierPayerId = crossoverCarrierPayerId;
    }

    public String getCrossoverCarrierPayerName() {
        return crossoverCarrierPayerName;
    }

    public void setCrossoverCarrierPayerName(String crossoverCarrierPayerName) {
        this.crossoverCarrierPayerName = crossoverCarrierPayerName;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeNpi() {
        return payeeNpi;
    }

    public void setPayeeNpi(String payeeNpi) {
        this.payeeNpi = payeeNpi;
    }

    public LocalDate getClaimReceivedDate() {
        return claimReceivedDate;
    }

    public void setClaimReceivedDate(LocalDate claimReceivedDate) {
        this.claimReceivedDate = claimReceivedDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
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

    public UUID getClaimsCob835MasterUuid() {
        return claimsCob835MasterUuid;
    }

    public void setClaimsCob835MasterUuid(UUID claimsCob835MasterUuid) {
        this.claimsCob835MasterUuid = claimsCob835MasterUuid;
    }

    public Double getChequeEftAmount() {
        return chequeEftAmount;
    }

    public void setChequeEftAmount(Double chequeEftAmount) {
        this.chequeEftAmount = chequeEftAmount;
    }

    public String getCobType() {
        return cobType;
    }

    public void setCobType(String cobType) {
        this.cobType = cobType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsCOB835MasterDTO)) {
            return false;
        }

        ClaimsCOB835MasterDTO claimsCOB835MasterDTO = (ClaimsCOB835MasterDTO) o;
        if (this.claimCob835MasterId == null) {
            return false;
        }
        return Objects.equals(this.claimCob835MasterId, claimsCOB835MasterDTO.claimCob835MasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimCob835MasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsCOB835MasterDTO{" +
            "claimCob835MasterId=" + getClaimCob835MasterId() +
            ", patientControlNumber='" + getPatientControlNumber() + "'" +
            ", payerClaimControlNumber='" + getPayerClaimControlNumber() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientMemberId='" + getPatientMemberId() + "'" +
            ", checkOrEFTTraceNumber='" + getCheckOrEFTTraceNumber() + "'" +
            ", checkIssueOrEFTEffectiveDate='" + getCheckIssueOrEFTEffectiveDate() + "'" +
            ", creditOrDebitFlagCode='" + getCreditOrDebitFlagCode() + "'" +
            ", paymentMethodCode='" + getPaymentMethodCode() + "'" +
            ", totalClaimChargeAmount=" + getTotalClaimChargeAmount() +
            ", totalClaimPaymentAmount=" + getTotalClaimPaymentAmount() +
            ", totalPatientResponsibilityAmount=" + getTotalPatientResponsibilityAmount() +
            ", crossoverCarrierName='" + getCrossoverCarrierName() + "'" +
            ", crossoverCarrierPayerId='" + getCrossoverCarrierPayerId() + "'" +
            ", crossoverCarrierPayerName='" + getCrossoverCarrierPayerName() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", payeeName='" + getPayeeName() + "'" +
            ", payeeNpi='" + getPayeeNpi() + "'" +
            ", claimReceivedDate='" + getClaimReceivedDate() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", receivedOn='" + getReceivedOn() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", claimsCob835MasterUuid='" + getClaimsCob835MasterUuid() + "'" +
            ", chequeEftAmount=" + getChequeEftAmount() +
            ", cobType='" + getCobType() + "'" +
            "}";
    }
}
