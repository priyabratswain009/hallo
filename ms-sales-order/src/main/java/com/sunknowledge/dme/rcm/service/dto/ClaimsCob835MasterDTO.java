package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimsCob835Master} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClaimsCob835MasterDTO implements Serializable {

    private Long claimCob835MasterId;

    private String patientFirstName;

    private String patientLastName;

    private String patientMemberId;

    private String fileName;

    private Double totalClaimChargeAmount;

    private Double totalClaimPaymentAmount;

    private Double totalPatientResponsibilityAmount;

    private LocalDate claimReceivedDate;

    private LocalDate receivedOn;

    private String status;

    private String patientControlNumber;

    private String payerClaimControlNumber;

    private String checkOrEftTraceNumber;

    private LocalDate checkIssueOrEftEffectiveDate;

    private String creditOrDebitFlagCode;

    private String paymentMethodCode;

    private Boolean crossoverCarrierName;

    private String entityIdentifierCode;

    private String entityTypeQualifier;

    private String payerName;

    private String payeeName;

    private String payeeNpi;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private Long createdById;

    private String createdByName;

    private Long updatedById;

    private String updatedByName;

    private UUID claimsCob835MasterUuid;

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public LocalDate getClaimReceivedDate() {
        return claimReceivedDate;
    }

    public void setClaimReceivedDate(LocalDate claimReceivedDate) {
        this.claimReceivedDate = claimReceivedDate;
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

    public String getCheckOrEftTraceNumber() {
        return checkOrEftTraceNumber;
    }

    public void setCheckOrEftTraceNumber(String checkOrEftTraceNumber) {
        this.checkOrEftTraceNumber = checkOrEftTraceNumber;
    }

    public LocalDate getCheckIssueOrEftEffectiveDate() {
        return checkIssueOrEftEffectiveDate;
    }

    public void setCheckIssueOrEftEffectiveDate(LocalDate checkIssueOrEftEffectiveDate) {
        this.checkIssueOrEftEffectiveDate = checkIssueOrEftEffectiveDate;
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

    public Boolean getCrossoverCarrierName() {
        return crossoverCarrierName;
    }

    public void setCrossoverCarrierName(Boolean crossoverCarrierName) {
        this.crossoverCarrierName = crossoverCarrierName;
    }

    public String getEntityIdentifierCode() {
        return entityIdentifierCode;
    }

    public void setEntityIdentifierCode(String entityIdentifierCode) {
        this.entityIdentifierCode = entityIdentifierCode;
    }

    public String getEntityTypeQualifier() {
        return entityTypeQualifier;
    }

    public void setEntityTypeQualifier(String entityTypeQualifier) {
        this.entityTypeQualifier = entityTypeQualifier;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public UUID getClaimsCob835MasterUuid() {
        return claimsCob835MasterUuid;
    }

    public void setClaimsCob835MasterUuid(UUID claimsCob835MasterUuid) {
        this.claimsCob835MasterUuid = claimsCob835MasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsCob835MasterDTO)) {
            return false;
        }

        ClaimsCob835MasterDTO claimsCob835MasterDTO = (ClaimsCob835MasterDTO) o;
        if (this.claimCob835MasterId == null) {
            return false;
        }
        return Objects.equals(this.claimCob835MasterId, claimsCob835MasterDTO.claimCob835MasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimCob835MasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsCob835MasterDTO{" +
            "claimCob835MasterId=" + getClaimCob835MasterId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientMemberId='" + getPatientMemberId() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", totalClaimChargeAmount=" + getTotalClaimChargeAmount() +
            ", totalClaimPaymentAmount=" + getTotalClaimPaymentAmount() +
            ", totalPatientResponsibilityAmount=" + getTotalPatientResponsibilityAmount() +
            ", claimReceivedDate='" + getClaimReceivedDate() + "'" +
            ", receivedOn='" + getReceivedOn() + "'" +
            ", status='" + getStatus() + "'" +
            ", patientControlNumber='" + getPatientControlNumber() + "'" +
            ", payerClaimControlNumber='" + getPayerClaimControlNumber() + "'" +
            ", checkOrEftTraceNumber='" + getCheckOrEftTraceNumber() + "'" +
            ", checkIssueOrEftEffectiveDate='" + getCheckIssueOrEftEffectiveDate() + "'" +
            ", creditOrDebitFlagCode='" + getCreditOrDebitFlagCode() + "'" +
            ", paymentMethodCode='" + getPaymentMethodCode() + "'" +
            ", crossoverCarrierName='" + getCrossoverCarrierName() + "'" +
            ", entityIdentifierCode='" + getEntityIdentifierCode() + "'" +
            ", entityTypeQualifier='" + getEntityTypeQualifier() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", payeeName='" + getPayeeName() + "'" +
            ", payeeNpi='" + getPayeeNpi() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", claimsCob835MasterUuid='" + getClaimsCob835MasterUuid() + "'" +
            "}";
    }
}
