package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus} entity.
 */
public class ClaimSubmissionStatusDTO implements Serializable {

    @NotNull
    private Long claimStatusId;

    private Long salesOrderId;

    private String salesOrderNo;

    private Long invoiceId;

    private String invoiceNo;

    private String payorLevel;

    private String payorIdNo;

    private Long claimSubmissionDataId;

    private String intClaimNo;

    private String patientAccountNo;

    private String payorClaimControlNo;

    private String originalClaimControlNo;

    private String patientIdNo;

    private String payor;

    private LocalDate claimSubmissionDate;

    private String submissionStatus;

    private String submissionNote;

    private String responseStatus;

    private String responseStatusNotes;

    private LocalDate responseStatusDate;

    private String eraStatus;

    private String eraStatusNotes;

    private LocalDate eraDate;

    private String resubmissinStatus;

    private Long resubmissionDetailId;

    private String resubmissionNotes;

    private String voidStatus;

    private String voidNote;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID claimSubmissionStatusUuid;

    private Long claimCob835MasterId;

    private Long claimStatus277MasterId;

    private String payorId;

    private String readyForSubmissionStatus;

    private String periodNo;

    private Long branchId;

    public Long getClaimStatusId() {
        return claimStatusId;
    }

    public void setClaimStatusId(Long claimStatusId) {
        this.claimStatusId = claimStatusId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPayorLevel() {
        return payorLevel;
    }

    public void setPayorLevel(String payorLevel) {
        this.payorLevel = payorLevel;
    }

    public String getPayorIdNo() {
        return payorIdNo;
    }

    public void setPayorIdNo(String payorIdNo) {
        this.payorIdNo = payorIdNo;
    }

    public Long getClaimSubmissionDataId() {
        return claimSubmissionDataId;
    }

    public void setClaimSubmissionDataId(Long claimSubmissionDataId) {
        this.claimSubmissionDataId = claimSubmissionDataId;
    }

    public String getIntClaimNo() {
        return intClaimNo;
    }

    public void setIntClaimNo(String intClaimNo) {
        this.intClaimNo = intClaimNo;
    }

    public String getPatientAccountNo() {
        return patientAccountNo;
    }

    public void setPatientAccountNo(String patientAccountNo) {
        this.patientAccountNo = patientAccountNo;
    }

    public String getPayorClaimControlNo() {
        return payorClaimControlNo;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public String getOriginalClaimControlNo() {
        return originalClaimControlNo;
    }

    public void setOriginalClaimControlNo(String originalClaimControlNo) {
        this.originalClaimControlNo = originalClaimControlNo;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPayor() {
        return payor;
    }

    public void setPayor(String payor) {
        this.payor = payor;
    }

    public LocalDate getClaimSubmissionDate() {
        return claimSubmissionDate;
    }

    public void setClaimSubmissionDate(LocalDate claimSubmissionDate) {
        this.claimSubmissionDate = claimSubmissionDate;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getSubmissionNote() {
        return submissionNote;
    }

    public void setSubmissionNote(String submissionNote) {
        this.submissionNote = submissionNote;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseStatusNotes() {
        return responseStatusNotes;
    }

    public void setResponseStatusNotes(String responseStatusNotes) {
        this.responseStatusNotes = responseStatusNotes;
    }

    public LocalDate getResponseStatusDate() {
        return responseStatusDate;
    }

    public void setResponseStatusDate(LocalDate responseStatusDate) {
        this.responseStatusDate = responseStatusDate;
    }

    public String getEraStatus() {
        return eraStatus;
    }

    public void setEraStatus(String eraStatus) {
        this.eraStatus = eraStatus;
    }

    public String getEraStatusNotes() {
        return eraStatusNotes;
    }

    public void setEraStatusNotes(String eraStatusNotes) {
        this.eraStatusNotes = eraStatusNotes;
    }

    public LocalDate getEraDate() {
        return eraDate;
    }

    public void setEraDate(LocalDate eraDate) {
        this.eraDate = eraDate;
    }

    public String getResubmissinStatus() {
        return resubmissinStatus;
    }

    public void setResubmissinStatus(String resubmissinStatus) {
        this.resubmissinStatus = resubmissinStatus;
    }

    public Long getResubmissionDetailId() {
        return resubmissionDetailId;
    }

    public void setResubmissionDetailId(Long resubmissionDetailId) {
        this.resubmissionDetailId = resubmissionDetailId;
    }

    public String getResubmissionNotes() {
        return resubmissionNotes;
    }

    public void setResubmissionNotes(String resubmissionNotes) {
        this.resubmissionNotes = resubmissionNotes;
    }

    public String getVoidStatus() {
        return voidStatus;
    }

    public void setVoidStatus(String voidStatus) {
        this.voidStatus = voidStatus;
    }

    public String getVoidNote() {
        return voidNote;
    }

    public void setVoidNote(String voidNote) {
        this.voidNote = voidNote;
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

    public UUID getClaimSubmissionStatusUuid() {
        return claimSubmissionStatusUuid;
    }

    public void setClaimSubmissionStatusUuid(UUID claimSubmissionStatusUuid) {
        this.claimSubmissionStatusUuid = claimSubmissionStatusUuid;
    }

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public Long getClaimStatus277MasterId() {
        return claimStatus277MasterId;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public String getPayorId() {
        return payorId;
    }

    public void setPayorId(String payorId) {
        this.payorId = payorId;
    }

    public String getReadyForSubmissionStatus() {
        return readyForSubmissionStatus;
    }

    public void setReadyForSubmissionStatus(String readyForSubmissionStatus) {
        this.readyForSubmissionStatus = readyForSubmissionStatus;
    }

    public String getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimSubmissionStatusDTO)) {
            return false;
        }

        ClaimSubmissionStatusDTO claimSubmissionStatusDTO = (ClaimSubmissionStatusDTO) o;
        if (this.claimStatusId == null) {
            return false;
        }
        return Objects.equals(this.claimStatusId, claimSubmissionStatusDTO.claimStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimStatusId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimSubmissionStatusDTO{" +
            "claimStatusId=" + getClaimStatusId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", invoiceId=" + getInvoiceId() +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", payorLevel='" + getPayorLevel() + "'" +
            ", payorIdNo='" + getPayorIdNo() + "'" +
            ", claimSubmissionDataId=" + getClaimSubmissionDataId() +
            ", intClaimNo='" + getIntClaimNo() + "'" +
            ", patientAccountNo='" + getPatientAccountNo() + "'" +
            ", payorClaimControlNo='" + getPayorClaimControlNo() + "'" +
            ", originalClaimControlNo='" + getOriginalClaimControlNo() + "'" +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", payor='" + getPayor() + "'" +
            ", claimSubmissionDate='" + getClaimSubmissionDate() + "'" +
            ", submissionStatus='" + getSubmissionStatus() + "'" +
            ", submissionNote='" + getSubmissionNote() + "'" +
            ", responseStatus='" + getResponseStatus() + "'" +
            ", responseStatusNotes='" + getResponseStatusNotes() + "'" +
            ", responseStatusDate='" + getResponseStatusDate() + "'" +
            ", eraStatus='" + getEraStatus() + "'" +
            ", eraStatusNotes='" + getEraStatusNotes() + "'" +
            ", eraDate='" + getEraDate() + "'" +
            ", resubmissinStatus='" + getResubmissinStatus() + "'" +
            ", resubmissionDetailId=" + getResubmissionDetailId() +
            ", resubmissionNotes='" + getResubmissionNotes() + "'" +
            ", voidStatus='" + getVoidStatus() + "'" +
            ", voidNote='" + getVoidNote() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", claimSubmissionStatusUuid='" + getClaimSubmissionStatusUuid() + "'" +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", claimStatus277MasterId=" + getClaimStatus277MasterId() +
            ", payorId='" + getPayorId() + "'" +
            ", readyForSubmissionStatus='" + getReadyForSubmissionStatus() + "'" +
            ", periodNo='" + getPeriodNo() + "'" +
            ", branchId=" + getBranchId() +
            "}";
    }
}
