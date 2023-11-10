package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ReceiptMasterDetails.
 */
@Entity
@Table(name = "t_receipt_master_details")
public class ReceiptMasterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "receipt_id", nullable = false)
    private Long receiptId;

    @Column(name = "receipt_no")
    private String receiptNo;

    @Column(name = "deposit_id")
    private Long depositId;

    @Column(name = "deposit_no")
    private String depositNo;

    @Column(name = "receipt_reference")
    private String receiptReference;

    @Column(name = "receipt_amount")
    private Double receiptAmount;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "receipt_notes")
    private String receiptNotes;

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

    @Column(name = "receipt_master_details_uuid")
    private UUID receiptMasterDetailsUuid;

    @Column(name = "receipt_status")
    private String receiptStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getReceiptId() {
        return this.receiptId;
    }

    public ReceiptMasterDetails receiptId(Long receiptId) {
        this.setReceiptId(receiptId);
        return this;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNo() {
        return this.receiptNo;
    }

    public ReceiptMasterDetails receiptNo(String receiptNo) {
        this.setReceiptNo(receiptNo);
        return this;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Long getDepositId() {
        return this.depositId;
    }

    public ReceiptMasterDetails depositId(Long depositId) {
        this.setDepositId(depositId);
        return this;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getDepositNo() {
        return this.depositNo;
    }

    public ReceiptMasterDetails depositNo(String depositNo) {
        this.setDepositNo(depositNo);
        return this;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public String getReceiptReference() {
        return this.receiptReference;
    }

    public ReceiptMasterDetails receiptReference(String receiptReference) {
        this.setReceiptReference(receiptReference);
        return this;
    }

    public void setReceiptReference(String receiptReference) {
        this.receiptReference = receiptReference;
    }

    public Double getReceiptAmount() {
        return this.receiptAmount;
    }

    public ReceiptMasterDetails receiptAmount(Double receiptAmount) {
        this.setReceiptAmount(receiptAmount);
        return this;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public ReceiptMasterDetails paymentMode(String paymentMode) {
        this.setPaymentMode(paymentMode);
        return this;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getPaymentDate() {
        return this.paymentDate;
    }

    public ReceiptMasterDetails paymentDate(LocalDate paymentDate) {
        this.setPaymentDate(paymentDate);
        return this;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceiptNotes() {
        return this.receiptNotes;
    }

    public ReceiptMasterDetails receiptNotes(String receiptNotes) {
        this.setReceiptNotes(receiptNotes);
        return this;
    }

    public void setReceiptNotes(String receiptNotes) {
        this.receiptNotes = receiptNotes;
    }

    public String getStatus() {
        return this.status;
    }

    public ReceiptMasterDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ReceiptMasterDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ReceiptMasterDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ReceiptMasterDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ReceiptMasterDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ReceiptMasterDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ReceiptMasterDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getReceiptMasterDetailsUuid() {
        return this.receiptMasterDetailsUuid;
    }

    public ReceiptMasterDetails receiptMasterDetailsUuid(UUID receiptMasterDetailsUuid) {
        this.setReceiptMasterDetailsUuid(receiptMasterDetailsUuid);
        return this;
    }

    public void setReceiptMasterDetailsUuid(UUID receiptMasterDetailsUuid) {
        this.receiptMasterDetailsUuid = receiptMasterDetailsUuid;
    }

    public String getReceiptStatus() {
        return this.receiptStatus;
    }

    public ReceiptMasterDetails receiptStatus(String receiptStatus) {
        this.setReceiptStatus(receiptStatus);
        return this;
    }

    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReceiptMasterDetails)) {
            return false;
        }
        return receiptId != null && receiptId.equals(((ReceiptMasterDetails) o).receiptId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReceiptMasterDetails{" +
            "receiptId=" + getReceiptId() +
            ", receiptNo='" + getReceiptNo() + "'" +
            ", depositId=" + getDepositId() +
            ", depositNo='" + getDepositNo() + "'" +
            ", receiptReference='" + getReceiptReference() + "'" +
            ", receiptAmount=" + getReceiptAmount() +
            ", paymentMode='" + getPaymentMode() + "'" +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", receiptNotes='" + getReceiptNotes() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", receiptMasterDetailsUuid='" + getReceiptMasterDetailsUuid() + "'" +
            ", receiptStatus='" + getReceiptStatus() + "'" +
            "}";
    }
}
