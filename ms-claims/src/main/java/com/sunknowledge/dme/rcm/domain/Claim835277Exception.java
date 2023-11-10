package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Claim835277Exception.
 */
@Entity
@Table(name = "t_claim_835_277_exception")
public class Claim835277Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "exception_id", nullable = false)
    private Long exceptionId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "run_date")
    private LocalDate runDate;

    @Column(name = "is_277")
    private Boolean is277;

    @Column(name = "is_835")
    private Boolean is835;

    @Column(name = "claim_status_277_master_id")
    private Long claimStatus277MasterId;

    @Column(name = "claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column(name = "payer_claim_control_number")
    private String payerClaimControlNumber;

    @Column(name = "patient_control_number")
    private String patientControlNumber;

    @Column(name = "patient_member_id")
    private String patientMemberId;

    @Column(name = "patient_account_number")
    private String patientAccountNumber;

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

    @Column(name = "claim_835277_exception_uuid")
    private UUID claim835277ExceptionUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getExceptionId() {
        return this.exceptionId;
    }

    public Claim835277Exception exceptionId(Long exceptionId) {
        this.setExceptionId(exceptionId);
        return this;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Claim835277Exception fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getRunDate() {
        return this.runDate;
    }

    public Claim835277Exception runDate(LocalDate runDate) {
        this.setRunDate(runDate);
        return this;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public Boolean getIs277() {
        return this.is277;
    }

    public Claim835277Exception is277(Boolean is277) {
        this.setIs277(is277);
        return this;
    }

    public void setIs277(Boolean is277) {
        this.is277 = is277;
    }

    public Boolean getIs835() {
        return this.is835;
    }

    public Claim835277Exception is835(Boolean is835) {
        this.setIs835(is835);
        return this;
    }

    public void setIs835(Boolean is835) {
        this.is835 = is835;
    }

    public Long getClaimStatus277MasterId() {
        return this.claimStatus277MasterId;
    }

    public Claim835277Exception claimStatus277MasterId(Long claimStatus277MasterId) {
        this.setClaimStatus277MasterId(claimStatus277MasterId);
        return this;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public Claim835277Exception claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPayerClaimControlNumber() {
        return this.payerClaimControlNumber;
    }

    public Claim835277Exception payerClaimControlNumber(String payerClaimControlNumber) {
        this.setPayerClaimControlNumber(payerClaimControlNumber);
        return this;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getPatientControlNumber() {
        return this.patientControlNumber;
    }

    public Claim835277Exception patientControlNumber(String patientControlNumber) {
        this.setPatientControlNumber(patientControlNumber);
        return this;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPatientMemberId() {
        return this.patientMemberId;
    }

    public Claim835277Exception patientMemberId(String patientMemberId) {
        this.setPatientMemberId(patientMemberId);
        return this;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getPatientAccountNumber() {
        return this.patientAccountNumber;
    }

    public Claim835277Exception patientAccountNumber(String patientAccountNumber) {
        this.setPatientAccountNumber(patientAccountNumber);
        return this;
    }

    public void setPatientAccountNumber(String patientAccountNumber) {
        this.patientAccountNumber = patientAccountNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public Claim835277Exception status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public Claim835277Exception createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public Claim835277Exception createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Claim835277Exception createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public Claim835277Exception updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public Claim835277Exception updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public Claim835277Exception updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getClaim835277ExceptionUuid() {
        return this.claim835277ExceptionUuid;
    }

    public Claim835277Exception claim835277ExceptionUuid(UUID claim835277ExceptionUuid) {
        this.setClaim835277ExceptionUuid(claim835277ExceptionUuid);
        return this;
    }

    public void setClaim835277ExceptionUuid(UUID claim835277ExceptionUuid) {
        this.claim835277ExceptionUuid = claim835277ExceptionUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Claim835277Exception)) {
            return false;
        }
        return exceptionId != null && exceptionId.equals(((Claim835277Exception) o).exceptionId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Claim835277Exception{" +
            "exceptionId=" + getExceptionId() +
            ", fileName='" + getFileName() + "'" +
            ", runDate='" + getRunDate() + "'" +
            ", is277='" + getIs277() + "'" +
            ", is835='" + getIs835() + "'" +
            ", claimStatus277MasterId=" + getClaimStatus277MasterId() +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", payerClaimControlNumber='" + getPayerClaimControlNumber() + "'" +
            ", patientControlNumber='" + getPatientControlNumber() + "'" +
            ", patientMemberId='" + getPatientMemberId() + "'" +
            ", patientAccountNumber='" + getPatientAccountNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", claim835277ExceptionUuid='" + getClaim835277ExceptionUuid() + "'" +
            "}";
    }
}
