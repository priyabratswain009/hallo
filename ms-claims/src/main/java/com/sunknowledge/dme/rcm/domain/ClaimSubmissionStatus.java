package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ClaimSubmissionStatus.
 */
@Entity
@Table(name = "t_claim_submission_status")
public class ClaimSubmissionStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claim_status_id", nullable = false)
    private Long claimStatusId;

    @Column(name = "sales_order_id")
    private Long salesOrderId;

    @Column(name = "sales_order_no")
    private String salesOrderNo;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "payor_level")
    private String payorLevel;

    @Column(name = "payor_id_no")
    private String payorIdNo;

    @Column(name = "claim_submission_data_id")
    private Long claimSubmissionDataId;

    @Column(name = "int_claim_no")
    private String intClaimNo;

    @Column(name = "patient_account_no")
    private String patientAccountNo;

    @Column(name = "payor_claim_control_no")
    private String payorClaimControlNo;

    @Column(name = "original_claim_control_no")
    private String originalClaimControlNo;

    @Column(name = "patient_id_no")
    private String patientIdNo;

    @Column(name = "payor")
    private String payor;

    @Column(name = "claim_submission_date")
    private LocalDate claimSubmissionDate;

    @Column(name = "submission_status")
    private String submissionStatus;

    @Column(name = "submission_note")
    private String submissionNote;

    @Column(name = "response_status")
    private String responseStatus;

    @Column(name = "response_status_notes")
    private String responseStatusNotes;

    @Column(name = "response_status_date")
    private LocalDate responseStatusDate;

    @Column(name = "era_status")
    private String eraStatus;

    @Column(name = "era_status_notes")
    private String eraStatusNotes;

    @Column(name = "era_date")
    private LocalDate eraDate;

    @Column(name = "resubmissin_status")
    private String resubmissinStatus;

    @Column(name = "resubmission_detail_id")
    private Long resubmissionDetailId;

    @Column(name = "resubmission_notes")
    private String resubmissionNotes;

    @Column(name = "void_status")
    private String voidStatus;

    @Column(name = "void_note")
    private String voidNote;

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

    @Column(name = "claim_submission_status_uuid")
    private UUID claimSubmissionStatusUuid;

    @Column(name = "claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column(name = "claim_status_277_master_id")
    private Long claimStatus277MasterId;

    @Column(name = "payor_id")
    private String payorId;

    @Column(name = "ready_for_submission_status")
    private String readyForSubmissionStatus;

    @Column(name = "period_no")
    private String periodNo;

    @Column(name = "branch_id")
    private Long branchId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimStatusId() {
        return this.claimStatusId;
    }

    public ClaimSubmissionStatus claimStatusId(Long claimStatusId) {
        this.setClaimStatusId(claimStatusId);
        return this;
    }

    public void setClaimStatusId(Long claimStatusId) {
        this.claimStatusId = claimStatusId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public ClaimSubmissionStatus salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public ClaimSubmissionStatus salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getInvoiceId() {
        return this.invoiceId;
    }

    public ClaimSubmissionStatus invoiceId(Long invoiceId) {
        this.setInvoiceId(invoiceId);
        return this;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public ClaimSubmissionStatus invoiceNo(String invoiceNo) {
        this.setInvoiceNo(invoiceNo);
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPayorLevel() {
        return this.payorLevel;
    }

    public ClaimSubmissionStatus payorLevel(String payorLevel) {
        this.setPayorLevel(payorLevel);
        return this;
    }

    public void setPayorLevel(String payorLevel) {
        this.payorLevel = payorLevel;
    }

    public String getPayorIdNo() {
        return this.payorIdNo;
    }

    public ClaimSubmissionStatus payorIdNo(String payorIdNo) {
        this.setPayorIdNo(payorIdNo);
        return this;
    }

    public void setPayorIdNo(String payorIdNo) {
        this.payorIdNo = payorIdNo;
    }

    public Long getClaimSubmissionDataId() {
        return this.claimSubmissionDataId;
    }

    public ClaimSubmissionStatus claimSubmissionDataId(Long claimSubmissionDataId) {
        this.setClaimSubmissionDataId(claimSubmissionDataId);
        return this;
    }

    public void setClaimSubmissionDataId(Long claimSubmissionDataId) {
        this.claimSubmissionDataId = claimSubmissionDataId;
    }

    public String getIntClaimNo() {
        return this.intClaimNo;
    }

    public ClaimSubmissionStatus intClaimNo(String intClaimNo) {
        this.setIntClaimNo(intClaimNo);
        return this;
    }

    public void setIntClaimNo(String intClaimNo) {
        this.intClaimNo = intClaimNo;
    }

    public String getPatientAccountNo() {
        return this.patientAccountNo;
    }

    public ClaimSubmissionStatus patientAccountNo(String patientAccountNo) {
        this.setPatientAccountNo(patientAccountNo);
        return this;
    }

    public void setPatientAccountNo(String patientAccountNo) {
        this.patientAccountNo = patientAccountNo;
    }

    public String getPayorClaimControlNo() {
        return this.payorClaimControlNo;
    }

    public ClaimSubmissionStatus payorClaimControlNo(String payorClaimControlNo) {
        this.setPayorClaimControlNo(payorClaimControlNo);
        return this;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public String getOriginalClaimControlNo() {
        return this.originalClaimControlNo;
    }

    public ClaimSubmissionStatus originalClaimControlNo(String originalClaimControlNo) {
        this.setOriginalClaimControlNo(originalClaimControlNo);
        return this;
    }

    public void setOriginalClaimControlNo(String originalClaimControlNo) {
        this.originalClaimControlNo = originalClaimControlNo;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public ClaimSubmissionStatus patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPayor() {
        return this.payor;
    }

    public ClaimSubmissionStatus payor(String payor) {
        this.setPayor(payor);
        return this;
    }

    public void setPayor(String payor) {
        this.payor = payor;
    }

    public LocalDate getClaimSubmissionDate() {
        return this.claimSubmissionDate;
    }

    public ClaimSubmissionStatus claimSubmissionDate(LocalDate claimSubmissionDate) {
        this.setClaimSubmissionDate(claimSubmissionDate);
        return this;
    }

    public void setClaimSubmissionDate(LocalDate claimSubmissionDate) {
        this.claimSubmissionDate = claimSubmissionDate;
    }

    public String getSubmissionStatus() {
        return this.submissionStatus;
    }

    public ClaimSubmissionStatus submissionStatus(String submissionStatus) {
        this.setSubmissionStatus(submissionStatus);
        return this;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getSubmissionNote() {
        return this.submissionNote;
    }

    public ClaimSubmissionStatus submissionNote(String submissionNote) {
        this.setSubmissionNote(submissionNote);
        return this;
    }

    public void setSubmissionNote(String submissionNote) {
        this.submissionNote = submissionNote;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public ClaimSubmissionStatus responseStatus(String responseStatus) {
        this.setResponseStatus(responseStatus);
        return this;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseStatusNotes() {
        return this.responseStatusNotes;
    }

    public ClaimSubmissionStatus responseStatusNotes(String responseStatusNotes) {
        this.setResponseStatusNotes(responseStatusNotes);
        return this;
    }

    public void setResponseStatusNotes(String responseStatusNotes) {
        this.responseStatusNotes = responseStatusNotes;
    }

    public LocalDate getResponseStatusDate() {
        return this.responseStatusDate;
    }

    public ClaimSubmissionStatus responseStatusDate(LocalDate responseStatusDate) {
        this.setResponseStatusDate(responseStatusDate);
        return this;
    }

    public void setResponseStatusDate(LocalDate responseStatusDate) {
        this.responseStatusDate = responseStatusDate;
    }

    public String getEraStatus() {
        return this.eraStatus;
    }

    public ClaimSubmissionStatus eraStatus(String eraStatus) {
        this.setEraStatus(eraStatus);
        return this;
    }

    public void setEraStatus(String eraStatus) {
        this.eraStatus = eraStatus;
    }

    public String getEraStatusNotes() {
        return this.eraStatusNotes;
    }

    public ClaimSubmissionStatus eraStatusNotes(String eraStatusNotes) {
        this.setEraStatusNotes(eraStatusNotes);
        return this;
    }

    public void setEraStatusNotes(String eraStatusNotes) {
        this.eraStatusNotes = eraStatusNotes;
    }

    public LocalDate getEraDate() {
        return this.eraDate;
    }

    public ClaimSubmissionStatus eraDate(LocalDate eraDate) {
        this.setEraDate(eraDate);
        return this;
    }

    public void setEraDate(LocalDate eraDate) {
        this.eraDate = eraDate;
    }

    public String getResubmissinStatus() {
        return this.resubmissinStatus;
    }

    public ClaimSubmissionStatus resubmissinStatus(String resubmissinStatus) {
        this.setResubmissinStatus(resubmissinStatus);
        return this;
    }

    public void setResubmissinStatus(String resubmissinStatus) {
        this.resubmissinStatus = resubmissinStatus;
    }

    public Long getResubmissionDetailId() {
        return this.resubmissionDetailId;
    }

    public ClaimSubmissionStatus resubmissionDetailId(Long resubmissionDetailId) {
        this.setResubmissionDetailId(resubmissionDetailId);
        return this;
    }

    public void setResubmissionDetailId(Long resubmissionDetailId) {
        this.resubmissionDetailId = resubmissionDetailId;
    }

    public String getResubmissionNotes() {
        return this.resubmissionNotes;
    }

    public ClaimSubmissionStatus resubmissionNotes(String resubmissionNotes) {
        this.setResubmissionNotes(resubmissionNotes);
        return this;
    }

    public void setResubmissionNotes(String resubmissionNotes) {
        this.resubmissionNotes = resubmissionNotes;
    }

    public String getVoidStatus() {
        return this.voidStatus;
    }

    public ClaimSubmissionStatus voidStatus(String voidStatus) {
        this.setVoidStatus(voidStatus);
        return this;
    }

    public void setVoidStatus(String voidStatus) {
        this.voidStatus = voidStatus;
    }

    public String getVoidNote() {
        return this.voidNote;
    }

    public ClaimSubmissionStatus voidNote(String voidNote) {
        this.setVoidNote(voidNote);
        return this;
    }

    public void setVoidNote(String voidNote) {
        this.voidNote = voidNote;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimSubmissionStatus status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ClaimSubmissionStatus createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ClaimSubmissionStatus createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ClaimSubmissionStatus createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimSubmissionStatus updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimSubmissionStatus updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimSubmissionStatus updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getClaimSubmissionStatusUuid() {
        return this.claimSubmissionStatusUuid;
    }

    public ClaimSubmissionStatus claimSubmissionStatusUuid(UUID claimSubmissionStatusUuid) {
        this.setClaimSubmissionStatusUuid(claimSubmissionStatusUuid);
        return this;
    }

    public void setClaimSubmissionStatusUuid(UUID claimSubmissionStatusUuid) {
        this.claimSubmissionStatusUuid = claimSubmissionStatusUuid;
    }

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public ClaimSubmissionStatus claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public Long getClaimStatus277MasterId() {
        return this.claimStatus277MasterId;
    }

    public ClaimSubmissionStatus claimStatus277MasterId(Long claimStatus277MasterId) {
        this.setClaimStatus277MasterId(claimStatus277MasterId);
        return this;
    }

    public void setClaimStatus277MasterId(Long claimStatus277MasterId) {
        this.claimStatus277MasterId = claimStatus277MasterId;
    }

    public String getPayorId() {
        return this.payorId;
    }

    public ClaimSubmissionStatus payorId(String payorId) {
        this.setPayorId(payorId);
        return this;
    }

    public void setPayorId(String payorId) {
        this.payorId = payorId;
    }

    public String getReadyForSubmissionStatus() {
        return this.readyForSubmissionStatus;
    }

    public ClaimSubmissionStatus readyForSubmissionStatus(String readyForSubmissionStatus) {
        this.setReadyForSubmissionStatus(readyForSubmissionStatus);
        return this;
    }

    public void setReadyForSubmissionStatus(String readyForSubmissionStatus) {
        this.readyForSubmissionStatus = readyForSubmissionStatus;
    }

    public String getPeriodNo() {
        return this.periodNo;
    }

    public ClaimSubmissionStatus periodNo(String periodNo) {
        this.setPeriodNo(periodNo);
        return this;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public ClaimSubmissionStatus branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimSubmissionStatus)) {
            return false;
        }
        return claimStatusId != null && claimStatusId.equals(((ClaimSubmissionStatus) o).claimStatusId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimSubmissionStatus{" +
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
