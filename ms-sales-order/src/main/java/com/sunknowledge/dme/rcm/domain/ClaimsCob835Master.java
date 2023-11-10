package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ClaimsCob835Master.
 */
@Table("t_claims_cob_835_master")
public class ClaimsCob835Master implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("patient_member_id")
    private String patientMemberId;

    @Column("file_name")
    private String fileName;

    @Column("total_claim_charge_amount")
    private Double totalClaimChargeAmount;

    @Column("total_claim_payment_amount")
    private Double totalClaimPaymentAmount;

    @Column("total_patient_responsibility_amount")
    private Double totalPatientResponsibilityAmount;

    @Column("claim_received_date")
    private LocalDate claimReceivedDate;

    @Column("received_on")
    private LocalDate receivedOn;

    @Column("status")
    private String status;

    @Column("patient_control_number")
    private String patientControlNumber;

    @Column("payer_claim_control_number")
    private String payerClaimControlNumber;

    @Column("check_or_eft_trace_number")
    private String checkOrEftTraceNumber;

    @Column("check_issue_or_eft_effective_date")
    private LocalDate checkIssueOrEftEffectiveDate;

    @Column("credit_or_debit_flag_code")
    private String creditOrDebitFlagCode;

    @Column("payment_method_code")
    private String paymentMethodCode;

    @Column("crossover_carrier_name")
    private Boolean crossoverCarrierName;

    @Column("entity_identifier_code")
    private String entityIdentifierCode;

    @Column("entity_type_qualifier")
    private String entityTypeQualifier;

    @Column("payer_name")
    private String payerName;

    @Column("payee_name")
    private String payeeName;

    @Column("payee_npi")
    private String payeeNpi;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("claims_cob_835_master_uuid")
    private UUID claimsCob835MasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public ClaimsCob835Master claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public ClaimsCob835Master patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public ClaimsCob835Master patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientMemberId() {
        return this.patientMemberId;
    }

    public ClaimsCob835Master patientMemberId(String patientMemberId) {
        this.setPatientMemberId(patientMemberId);
        return this;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public ClaimsCob835Master fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getTotalClaimChargeAmount() {
        return this.totalClaimChargeAmount;
    }

    public ClaimsCob835Master totalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.setTotalClaimChargeAmount(totalClaimChargeAmount);
        return this;
    }

    public void setTotalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.totalClaimChargeAmount = totalClaimChargeAmount;
    }

    public Double getTotalClaimPaymentAmount() {
        return this.totalClaimPaymentAmount;
    }

    public ClaimsCob835Master totalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.setTotalClaimPaymentAmount(totalClaimPaymentAmount);
        return this;
    }

    public void setTotalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.totalClaimPaymentAmount = totalClaimPaymentAmount;
    }

    public Double getTotalPatientResponsibilityAmount() {
        return this.totalPatientResponsibilityAmount;
    }

    public ClaimsCob835Master totalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.setTotalPatientResponsibilityAmount(totalPatientResponsibilityAmount);
        return this;
    }

    public void setTotalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.totalPatientResponsibilityAmount = totalPatientResponsibilityAmount;
    }

    public LocalDate getClaimReceivedDate() {
        return this.claimReceivedDate;
    }

    public ClaimsCob835Master claimReceivedDate(LocalDate claimReceivedDate) {
        this.setClaimReceivedDate(claimReceivedDate);
        return this;
    }

    public void setClaimReceivedDate(LocalDate claimReceivedDate) {
        this.claimReceivedDate = claimReceivedDate;
    }

    public LocalDate getReceivedOn() {
        return this.receivedOn;
    }

    public ClaimsCob835Master receivedOn(LocalDate receivedOn) {
        this.setReceivedOn(receivedOn);
        return this;
    }

    public void setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimsCob835Master status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientControlNumber() {
        return this.patientControlNumber;
    }

    public ClaimsCob835Master patientControlNumber(String patientControlNumber) {
        this.setPatientControlNumber(patientControlNumber);
        return this;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPayerClaimControlNumber() {
        return this.payerClaimControlNumber;
    }

    public ClaimsCob835Master payerClaimControlNumber(String payerClaimControlNumber) {
        this.setPayerClaimControlNumber(payerClaimControlNumber);
        return this;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getCheckOrEftTraceNumber() {
        return this.checkOrEftTraceNumber;
    }

    public ClaimsCob835Master checkOrEftTraceNumber(String checkOrEftTraceNumber) {
        this.setCheckOrEftTraceNumber(checkOrEftTraceNumber);
        return this;
    }

    public void setCheckOrEftTraceNumber(String checkOrEftTraceNumber) {
        this.checkOrEftTraceNumber = checkOrEftTraceNumber;
    }

    public LocalDate getCheckIssueOrEftEffectiveDate() {
        return this.checkIssueOrEftEffectiveDate;
    }

    public ClaimsCob835Master checkIssueOrEftEffectiveDate(LocalDate checkIssueOrEftEffectiveDate) {
        this.setCheckIssueOrEftEffectiveDate(checkIssueOrEftEffectiveDate);
        return this;
    }

    public void setCheckIssueOrEftEffectiveDate(LocalDate checkIssueOrEftEffectiveDate) {
        this.checkIssueOrEftEffectiveDate = checkIssueOrEftEffectiveDate;
    }

    public String getCreditOrDebitFlagCode() {
        return this.creditOrDebitFlagCode;
    }

    public ClaimsCob835Master creditOrDebitFlagCode(String creditOrDebitFlagCode) {
        this.setCreditOrDebitFlagCode(creditOrDebitFlagCode);
        return this;
    }

    public void setCreditOrDebitFlagCode(String creditOrDebitFlagCode) {
        this.creditOrDebitFlagCode = creditOrDebitFlagCode;
    }

    public String getPaymentMethodCode() {
        return this.paymentMethodCode;
    }

    public ClaimsCob835Master paymentMethodCode(String paymentMethodCode) {
        this.setPaymentMethodCode(paymentMethodCode);
        return this;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public Boolean getCrossoverCarrierName() {
        return this.crossoverCarrierName;
    }

    public ClaimsCob835Master crossoverCarrierName(Boolean crossoverCarrierName) {
        this.setCrossoverCarrierName(crossoverCarrierName);
        return this;
    }

    public void setCrossoverCarrierName(Boolean crossoverCarrierName) {
        this.crossoverCarrierName = crossoverCarrierName;
    }

    public String getEntityIdentifierCode() {
        return this.entityIdentifierCode;
    }

    public ClaimsCob835Master entityIdentifierCode(String entityIdentifierCode) {
        this.setEntityIdentifierCode(entityIdentifierCode);
        return this;
    }

    public void setEntityIdentifierCode(String entityIdentifierCode) {
        this.entityIdentifierCode = entityIdentifierCode;
    }

    public String getEntityTypeQualifier() {
        return this.entityTypeQualifier;
    }

    public ClaimsCob835Master entityTypeQualifier(String entityTypeQualifier) {
        this.setEntityTypeQualifier(entityTypeQualifier);
        return this;
    }

    public void setEntityTypeQualifier(String entityTypeQualifier) {
        this.entityTypeQualifier = entityTypeQualifier;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public ClaimsCob835Master payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayeeName() {
        return this.payeeName;
    }

    public ClaimsCob835Master payeeName(String payeeName) {
        this.setPayeeName(payeeName);
        return this;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeNpi() {
        return this.payeeNpi;
    }

    public ClaimsCob835Master payeeNpi(String payeeNpi) {
        this.setPayeeNpi(payeeNpi);
        return this;
    }

    public void setPayeeNpi(String payeeNpi) {
        this.payeeNpi = payeeNpi;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ClaimsCob835Master createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimsCob835Master updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ClaimsCob835Master createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ClaimsCob835Master createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimsCob835Master updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimsCob835Master updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getClaimsCob835MasterUuid() {
        return this.claimsCob835MasterUuid;
    }

    public ClaimsCob835Master claimsCob835MasterUuid(UUID claimsCob835MasterUuid) {
        this.setClaimsCob835MasterUuid(claimsCob835MasterUuid);
        return this;
    }

    public void setClaimsCob835MasterUuid(UUID claimsCob835MasterUuid) {
        this.claimsCob835MasterUuid = claimsCob835MasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsCob835Master)) {
            return false;
        }
        return claimCob835MasterId != null && claimCob835MasterId.equals(((ClaimsCob835Master) o).claimCob835MasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsCob835Master{" +
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
