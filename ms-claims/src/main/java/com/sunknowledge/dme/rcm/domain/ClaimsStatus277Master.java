package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ClaimsStatus277Master.
 */
@Entity
@Table(name = "t_claims_status_277_master")
public class ClaimsStatus277Master implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claim_status_277_master_id", nullable = false)
    private Long claimStatus277MasterId;

    @Column(name = "control_number")
    private String controlNumber;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "trading_partner_claim_number")
    private String tradingPartnerClaimNumber;

    @Column(name = "patient_first_name")
    private String patientFirstName;

    @Column(name = "patient_last_name")
    private String patientLastName;

    @Column(name = "patient_member_id")
    private String patientMemberId;

    @Column(name = "service_provider_name")
    private String serviceProviderName;

    @Column(name = "service_provider_npi")
    private String serviceProviderNpi;

    @Column(name = "total_claim_charge_amount")
    private Double totalClaimChargeAmount;

    @Column(name = "total_claim_payment_amount")
    private Double totalClaimPaymentAmount;

    @Column(name = "adjudicated_finalized_date")
    private LocalDate adjudicatedFinalizedDate;

    @Column(name = "remittance_date")
    private LocalDate remittanceDate;

    @Column(name = "claim_status_category_code")
    private String claimStatusCategoryCode;

    @Column(name = "claim_status_category_code_value")
    private String claimStatusCategoryCodeValue;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "status_code_value")
    private String statusCodeValue;

    @Column(name = "clearinghouse_trace_number")
    private String clearinghouseTraceNumber;

    @Column(name = "remittance_trace_number")
    private String remittanceTraceNumber;

    @Column(name = "patient_account_number")
    private String patientAccountNumber;

    @Column(name = "claim_service_begin_date")
    private LocalDate claimServiceBeginDate;

    @Column(name = "claim_service_end_date")
    private LocalDate claimServiceEndDate;

    @Column(name = "status_information_effective_date")
    private LocalDate statusInformationEffectiveDate;

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

    @Column(name = "claims_status_277_master_uuid")
    private UUID claimsStatus277MasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimStatus277MasterId() {
        return this.claimStatus277MasterId;
    }

    public ClaimsStatus277Master claimStatus277MasterId(Long claimStatus277MasterId) {
        this.setClaimStatus277MasterId(claimStatus277MasterId);
        return this;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public String getControlNumber() {
        return this.controlNumber;
    }

    public ClaimsStatus277Master controlNumber(String controlNumber) {
        this.setControlNumber(controlNumber);
        return this;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getFileName() {
        return this.fileName;
    }

    public ClaimsStatus277Master fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTradingPartnerClaimNumber() {
        return this.tradingPartnerClaimNumber;
    }

    public ClaimsStatus277Master tradingPartnerClaimNumber(String tradingPartnerClaimNumber) {
        this.setTradingPartnerClaimNumber(tradingPartnerClaimNumber);
        return this;
    }

    public void setTradingPartnerClaimNumber(String tradingPartnerClaimNumber) {
        this.tradingPartnerClaimNumber = tradingPartnerClaimNumber;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public ClaimsStatus277Master patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public ClaimsStatus277Master patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientMemberId() {
        return this.patientMemberId;
    }

    public ClaimsStatus277Master patientMemberId(String patientMemberId) {
        this.setPatientMemberId(patientMemberId);
        return this;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getServiceProviderName() {
        return this.serviceProviderName;
    }

    public ClaimsStatus277Master serviceProviderName(String serviceProviderName) {
        this.setServiceProviderName(serviceProviderName);
        return this;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public String getServiceProviderNpi() {
        return this.serviceProviderNpi;
    }

    public ClaimsStatus277Master serviceProviderNpi(String serviceProviderNpi) {
        this.setServiceProviderNpi(serviceProviderNpi);
        return this;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public Double getTotalClaimChargeAmount() {
        return this.totalClaimChargeAmount;
    }

    public ClaimsStatus277Master totalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.setTotalClaimChargeAmount(totalClaimChargeAmount);
        return this;
    }

    public void setTotalClaimChargeAmount(Double totalClaimChargeAmount) {
        this.totalClaimChargeAmount = totalClaimChargeAmount;
    }

    public Double getTotalClaimPaymentAmount() {
        return this.totalClaimPaymentAmount;
    }

    public ClaimsStatus277Master totalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.setTotalClaimPaymentAmount(totalClaimPaymentAmount);
        return this;
    }

    public void setTotalClaimPaymentAmount(Double totalClaimPaymentAmount) {
        this.totalClaimPaymentAmount = totalClaimPaymentAmount;
    }

    public LocalDate getAdjudicatedFinalizedDate() {
        return this.adjudicatedFinalizedDate;
    }

    public ClaimsStatus277Master adjudicatedFinalizedDate(LocalDate adjudicatedFinalizedDate) {
        this.setAdjudicatedFinalizedDate(adjudicatedFinalizedDate);
        return this;
    }

    public void setAdjudicatedFinalizedDate(LocalDate adjudicatedFinalizedDate) {
        this.adjudicatedFinalizedDate = adjudicatedFinalizedDate;
    }

    public LocalDate getRemittanceDate() {
        return this.remittanceDate;
    }

    public ClaimsStatus277Master remittanceDate(LocalDate remittanceDate) {
        this.setRemittanceDate(remittanceDate);
        return this;
    }

    public void setRemittanceDate(LocalDate remittanceDate) {
        this.remittanceDate = remittanceDate;
    }

    public String getClaimStatusCategoryCode() {
        return this.claimStatusCategoryCode;
    }

    public ClaimsStatus277Master claimStatusCategoryCode(String claimStatusCategoryCode) {
        this.setClaimStatusCategoryCode(claimStatusCategoryCode);
        return this;
    }

    public void setClaimStatusCategoryCode(String claimStatusCategoryCode) {
        this.claimStatusCategoryCode = claimStatusCategoryCode;
    }

    public String getClaimStatusCategoryCodeValue() {
        return this.claimStatusCategoryCodeValue;
    }

    public ClaimsStatus277Master claimStatusCategoryCodeValue(String claimStatusCategoryCodeValue) {
        this.setClaimStatusCategoryCodeValue(claimStatusCategoryCodeValue);
        return this;
    }

    public void setClaimStatusCategoryCodeValue(String claimStatusCategoryCodeValue) {
        this.claimStatusCategoryCodeValue = claimStatusCategoryCodeValue;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public ClaimsStatus277Master statusCode(String statusCode) {
        this.setStatusCode(statusCode);
        return this;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCodeValue() {
        return this.statusCodeValue;
    }

    public ClaimsStatus277Master statusCodeValue(String statusCodeValue) {
        this.setStatusCodeValue(statusCodeValue);
        return this;
    }

    public void setStatusCodeValue(String statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    public String getClearinghouseTraceNumber() {
        return this.clearinghouseTraceNumber;
    }

    public ClaimsStatus277Master clearinghouseTraceNumber(String clearinghouseTraceNumber) {
        this.setClearinghouseTraceNumber(clearinghouseTraceNumber);
        return this;
    }

    public void setClearinghouseTraceNumber(String clearinghouseTraceNumber) {
        this.clearinghouseTraceNumber = clearinghouseTraceNumber;
    }

    public String getRemittanceTraceNumber() {
        return this.remittanceTraceNumber;
    }

    public ClaimsStatus277Master remittanceTraceNumber(String remittanceTraceNumber) {
        this.setRemittanceTraceNumber(remittanceTraceNumber);
        return this;
    }

    public void setRemittanceTraceNumber(String remittanceTraceNumber) {
        this.remittanceTraceNumber = remittanceTraceNumber;
    }

    public String getPatientAccountNumber() {
        return this.patientAccountNumber;
    }

    public ClaimsStatus277Master patientAccountNumber(String patientAccountNumber) {
        this.setPatientAccountNumber(patientAccountNumber);
        return this;
    }

    public void setPatientAccountNumber(String patientAccountNumber) {
        this.patientAccountNumber = patientAccountNumber;
    }

    public LocalDate getClaimServiceBeginDate() {
        return this.claimServiceBeginDate;
    }

    public ClaimsStatus277Master claimServiceBeginDate(LocalDate claimServiceBeginDate) {
        this.setClaimServiceBeginDate(claimServiceBeginDate);
        return this;
    }

    public void setClaimServiceBeginDate(LocalDate claimServiceBeginDate) {
        this.claimServiceBeginDate = claimServiceBeginDate;
    }

    public LocalDate getClaimServiceEndDate() {
        return this.claimServiceEndDate;
    }

    public ClaimsStatus277Master claimServiceEndDate(LocalDate claimServiceEndDate) {
        this.setClaimServiceEndDate(claimServiceEndDate);
        return this;
    }

    public void setClaimServiceEndDate(LocalDate claimServiceEndDate) {
        this.claimServiceEndDate = claimServiceEndDate;
    }

    public LocalDate getStatusInformationEffectiveDate() {
        return this.statusInformationEffectiveDate;
    }

    public ClaimsStatus277Master statusInformationEffectiveDate(LocalDate statusInformationEffectiveDate) {
        this.setStatusInformationEffectiveDate(statusInformationEffectiveDate);
        return this;
    }

    public void setStatusInformationEffectiveDate(LocalDate statusInformationEffectiveDate) {
        this.statusInformationEffectiveDate = statusInformationEffectiveDate;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimsStatus277Master status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ClaimsStatus277Master createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ClaimsStatus277Master createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ClaimsStatus277Master createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimsStatus277Master updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimsStatus277Master updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimsStatus277Master updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getClaimsStatus277MasterUuid() {
        return this.claimsStatus277MasterUuid;
    }

    public ClaimsStatus277Master claimsStatus277MasterUuid(UUID claimsStatus277MasterUuid) {
        this.setClaimsStatus277MasterUuid(claimsStatus277MasterUuid);
        return this;
    }

    public void setClaimsStatus277MasterUuid(UUID claimsStatus277MasterUuid) {
        this.claimsStatus277MasterUuid = claimsStatus277MasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsStatus277Master)) {
            return false;
        }
        return claimStatus277MasterId != null && claimStatus277MasterId.equals(((ClaimsStatus277Master) o).claimStatus277MasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsStatus277Master{" +
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
