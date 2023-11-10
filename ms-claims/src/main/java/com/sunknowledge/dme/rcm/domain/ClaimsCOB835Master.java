package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ClaimsCOB835Master.
 */
@Entity
@Table(name = "t_claims_cob_835_master")
public class ClaimsCOB835Master implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claim_cob_835_master_id", nullable = false)
    private Long claimCob835MasterId;

    @Column(name = "patient_control_number")
    private String patientControlNumber;

    @Column(name = "payer_claim_control_number")
    private String payerClaimControlNumber;

    @Column(name = "patient_first_name")
    private String patientFirstName;

    @Column(name = "patient_last_name")
    private String patientLastName;

    @Column(name = "patient_member_id")
    private String patientMemberId;

    @Column(name = "check_or_eft_trace_number")
    private String checkOrEFTTraceNumber;

    @Column(name = "check_issue_or_eft_effective_date")
    private LocalDate checkIssueOrEFTEffectiveDate;

    @Column(name = "credit_or_debit_flag_code")
    private String creditOrDebitFlagCode;

    @Column(name = "payment_method_code")
    private String paymentMethodCode;

    @Column(name = "total_claim_charge_amount")
    private Double totalClaimChargeAmount;

    @Column(name = "total_claim_payment_amount")
    private Double totalClaimPaymentAmount;

    @Column(name = "total_patient_responsibility_amount")
    private Double totalPatientResponsibilityAmount;

    @Column(name = "crossover_carrier_name")
    private Boolean crossoverCarrierName;

    @Column(name = "crossover_carrier_payer_id")
    private String crossoverCarrierPayerId;

    @Column(name = "crossover_carrier_payer_name")
    private String crossoverCarrierPayerName;

    @Column(name = "payer_name")
    private String payerName;

    @Column(name = "payee_name")
    private String payeeName;

    @Column(name = "payee_npi")
    private String payeeNpi;

    @Column(name = "claim_received_date")
    private LocalDate claimReceivedDate;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "received_on")
    private LocalDate receivedOn;

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

    @Column(name = "claims_cob_835_master_uuid")
    private UUID claimsCob835MasterUuid;

    @Column(name = "cheque_eft_amount")
    private Double chequeEftAmount;

    @Column(name = "cob_type")
    private String cobType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public ClaimsCOB835Master claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPatientControlNumber() {
        return this.patientControlNumber;
    }

    public ClaimsCOB835Master patientControlNumber(String patientControlNumber) {
        this.setPatientControlNumber(patientControlNumber);
        return this;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPayerClaimControlNumber() {
        return this.payerClaimControlNumber;
    }

    public ClaimsCOB835Master payerClaimControlNumber(String payerClaimControlNumber) {
        this.setPayerClaimControlNumber(payerClaimControlNumber);
        return this;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public ClaimsCOB835Master patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public ClaimsCOB835Master patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientMemberId() {
        return this.patientMemberId;
    }

    public ClaimsCOB835Master patientMemberId(String patientMemberId) {
        this.setPatientMemberId(patientMemberId);
        return this;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getCheckOrEFTTraceNumber() {
        return this.checkOrEFTTraceNumber;
    }

    public ClaimsCOB835Master checkOrEFTTraceNumber(String checkOrEFTTraceNumber) {
        this.setCheckOrEFTTraceNumber(checkOrEFTTraceNumber);
        return this;
    }

    public void setCheckOrEFTTraceNumber(String checkOrEFTTraceNumber) {
        this.checkOrEFTTraceNumber = checkOrEFTTraceNumber;
    }

    public LocalDate getCheckIssueOrEFTEffectiveDate() {
        return this.checkIssueOrEFTEffectiveDate;
    }

    public ClaimsCOB835Master checkIssueOrEFTEffectiveDate(LocalDate checkIssueOrEFTEffectiveDate) {
        this.setCheckIssueOrEFTEffectiveDate(checkIssueOrEFTEffectiveDate);
        return this;
    }

    public void setCheckIssueOrEFTEffectiveDate(LocalDate checkIssueOrEFTEffectiveDate) {
        this.checkIssueOrEFTEffectiveDate = checkIssueOrEFTEffectiveDate;
    }

    public String getCreditOrDebitFlagCode() {
        return this.creditOrDebitFlagCode;
    }

    public ClaimsCOB835Master creditOrDebitFlagCode(String creditOrDebitFlagCode) {
        this.setCreditOrDebitFlagCode(creditOrDebitFlagCode);
        return this;
    }

    public void setCreditOrDebitFlagCode(String creditOrDebitFlagCode) {
        this.creditOrDebitFlagCode = creditOrDebitFlagCode;
    }

    public String getPaymentMethodCode() {
        return this.paymentMethodCode;
    }

    public ClaimsCOB835Master paymentMethodCode(String paymentMethodCode) {
        this.setPaymentMethodCode(paymentMethodCode);
        return this;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public Double getTotalClaimChargeAmount() {
        return this.totalClaimChargeAmount;
    }

    public ClaimsCOB835Master totalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.setTotalClaimChargeAmount(totalClaimChargeAmount);
        return this;
    }

    public void setTotalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.totalClaimChargeAmount = totalClaimChargeAmount;
    }

    public Double getTotalClaimPaymentAmount() {
        return this.totalClaimPaymentAmount;
    }

    public ClaimsCOB835Master totalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.setTotalClaimPaymentAmount(totalClaimPaymentAmount);
        return this;
    }

    public void setTotalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.totalClaimPaymentAmount = totalClaimPaymentAmount;
    }

    public Double getTotalPatientResponsibilityAmount() {
        return this.totalPatientResponsibilityAmount;
    }

    public ClaimsCOB835Master totalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.setTotalPatientResponsibilityAmount(totalPatientResponsibilityAmount);
        return this;
    }

    public void setTotalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.totalPatientResponsibilityAmount = totalPatientResponsibilityAmount;
    }

    public Boolean getCrossoverCarrierName() {
        return this.crossoverCarrierName;
    }

    public ClaimsCOB835Master crossoverCarrierName(Boolean crossoverCarrierName) {
        this.setCrossoverCarrierName(crossoverCarrierName);
        return this;
    }

    public void setCrossoverCarrierName(Boolean crossoverCarrierName) {
        this.crossoverCarrierName = crossoverCarrierName;
    }

    public String getCrossoverCarrierPayerId() {
        return this.crossoverCarrierPayerId;
    }

    public ClaimsCOB835Master crossoverCarrierPayerId(String crossoverCarrierPayerId) {
        this.setCrossoverCarrierPayerId(crossoverCarrierPayerId);
        return this;
    }

    public void setCrossoverCarrierPayerId(String crossoverCarrierPayerId) {
        this.crossoverCarrierPayerId = crossoverCarrierPayerId;
    }

    public String getCrossoverCarrierPayerName() {
        return this.crossoverCarrierPayerName;
    }

    public ClaimsCOB835Master crossoverCarrierPayerName(String crossoverCarrierPayerName) {
        this.setCrossoverCarrierPayerName(crossoverCarrierPayerName);
        return this;
    }

    public void setCrossoverCarrierPayerName(String crossoverCarrierPayerName) {
        this.crossoverCarrierPayerName = crossoverCarrierPayerName;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public ClaimsCOB835Master payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayeeName() {
        return this.payeeName;
    }

    public ClaimsCOB835Master payeeName(String payeeName) {
        this.setPayeeName(payeeName);
        return this;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeNpi() {
        return this.payeeNpi;
    }

    public ClaimsCOB835Master payeeNpi(String payeeNpi) {
        this.setPayeeNpi(payeeNpi);
        return this;
    }

    public void setPayeeNpi(String payeeNpi) {
        this.payeeNpi = payeeNpi;
    }

    public LocalDate getClaimReceivedDate() {
        return this.claimReceivedDate;
    }

    public ClaimsCOB835Master claimReceivedDate(LocalDate claimReceivedDate) {
        this.setClaimReceivedDate(claimReceivedDate);
        return this;
    }

    public void setClaimReceivedDate(LocalDate claimReceivedDate) {
        this.claimReceivedDate = claimReceivedDate;
    }

    public String getFileName() {
        return this.fileName;
    }

    public ClaimsCOB835Master fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getReceivedOn() {
        return this.receivedOn;
    }

    public ClaimsCOB835Master receivedOn(LocalDate receivedOn) {
        this.setReceivedOn(receivedOn);
        return this;
    }

    public void setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimsCOB835Master status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ClaimsCOB835Master createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ClaimsCOB835Master createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ClaimsCOB835Master createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimsCOB835Master updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimsCOB835Master updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimsCOB835Master updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getClaimsCob835MasterUuid() {
        return this.claimsCob835MasterUuid;
    }

    public ClaimsCOB835Master claimsCob835MasterUuid(UUID claimsCob835MasterUuid) {
        this.setClaimsCob835MasterUuid(claimsCob835MasterUuid);
        return this;
    }

    public void setClaimsCob835MasterUuid(UUID claimsCob835MasterUuid) {
        this.claimsCob835MasterUuid = claimsCob835MasterUuid;
    }

    public Double getChequeEftAmount() {
        return this.chequeEftAmount;
    }

    public ClaimsCOB835Master chequeEftAmount(Double chequeEftAmount) {
        this.setChequeEftAmount(chequeEftAmount);
        return this;
    }

    public void setChequeEftAmount(Double chequeEftAmount) {
        this.chequeEftAmount = chequeEftAmount;
    }

    public String getCobType() {
        return this.cobType;
    }

    public ClaimsCOB835Master cobType(String cobType) {
        this.setCobType(cobType);
        return this;
    }

    public void setCobType(String cobType) {
        this.cobType = cobType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsCOB835Master)) {
            return false;
        }
        return claimCob835MasterId != null && claimCob835MasterId.equals(((ClaimsCOB835Master) o).claimCob835MasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsCOB835Master{" +
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
