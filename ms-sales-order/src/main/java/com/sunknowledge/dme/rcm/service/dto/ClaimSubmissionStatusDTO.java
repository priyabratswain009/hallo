package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClaimSubmissionStatusDTO implements Serializable {

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

    private Long response277RecordId;

    private String eraStatus;

    private String eraStatusNotes;

    private LocalDate eraDate;

    private Long era835RecordId;

    private String resubmissinStatus;

    private Long resubmissionDetailId;

    private String resubmissionNotes;

    private String voidStatus;

    private String voidNote;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID claimSubmissionStatusUuid;

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

    public Long getResponse277RecordId() {
        return response277RecordId;
    }

    public void setResponse277RecordId(Long response277RecordId) {
        this.response277RecordId = response277RecordId;
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

    public Long getEra835RecordId() {
        return era835RecordId;
    }

    public void setEra835RecordId(Long era835RecordId) {
        this.era835RecordId = era835RecordId;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public UUID getClaimSubmissionStatusUuid() {
        return claimSubmissionStatusUuid;
    }

    public void setClaimSubmissionStatusUuid(UUID claimSubmissionStatusUuid) {
        this.claimSubmissionStatusUuid = claimSubmissionStatusUuid;
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
            ", response277RecordId=" + getResponse277RecordId() +
            ", eraStatus='" + getEraStatus() + "'" +
            ", eraStatusNotes='" + getEraStatusNotes() + "'" +
            ", eraDate='" + getEraDate() + "'" +
            ", era835RecordId=" + getEra835RecordId() +
            ", resubmissinStatus='" + getResubmissinStatus() + "'" +
            ", resubmissionDetailId=" + getResubmissionDetailId() +
            ", resubmissionNotes='" + getResubmissionNotes() + "'" +
            ", voidStatus='" + getVoidStatus() + "'" +
            ", voidNote='" + getVoidNote() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", claimSubmissionStatusUuid='" + getClaimSubmissionStatusUuid() + "'" +
            "}";
    }
}
