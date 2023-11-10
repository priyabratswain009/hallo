package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Claim835277Exception} entity.
 */
public class Claim835277ExceptionDTO implements Serializable {

    @NotNull
    private Long exceptionId;

    private String fileName;

    private LocalDate runDate;

    private Boolean is277;

    private Boolean is835;

    private Long claimStatus277MasterId;

    private Long claimCob835MasterId;

    private String payerClaimControlNumber;

    private String patientControlNumber;

    private String patientMemberId;

    private String patientAccountNumber;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID claim835277ExceptionUuid;

    public Long getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public Boolean getIs277() {
        return is277;
    }

    public void setIs277(Boolean is277) {
        this.is277 = is277;
    }

    public Boolean getIs835() {
        return is835;
    }

    public void setIs835(Boolean is835) {
        this.is835 = is835;
    }

    public Long getClaimStatus277MasterId() {
        return claimStatus277MasterId;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPayerClaimControlNumber() {
        return payerClaimControlNumber;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getPatientControlNumber() {
        return patientControlNumber;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPatientMemberId() {
        return patientMemberId;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getPatientAccountNumber() {
        return patientAccountNumber;
    }

    public void setPatientAccountNumber(String patientAccountNumber) {
        this.patientAccountNumber = patientAccountNumber;
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

    public UUID getClaim835277ExceptionUuid() {
        return claim835277ExceptionUuid;
    }

    public void setClaim835277ExceptionUuid(UUID claim835277ExceptionUuid) {
        this.claim835277ExceptionUuid = claim835277ExceptionUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Claim835277ExceptionDTO)) {
            return false;
        }

        Claim835277ExceptionDTO claim835277ExceptionDTO = (Claim835277ExceptionDTO) o;
        if (this.exceptionId == null) {
            return false;
        }
        return Objects.equals(this.exceptionId, claim835277ExceptionDTO.exceptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.exceptionId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Claim835277ExceptionDTO{" +
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
