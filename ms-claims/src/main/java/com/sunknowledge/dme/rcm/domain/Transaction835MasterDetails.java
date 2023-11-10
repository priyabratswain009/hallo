package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Transaction835MasterDetails.
 */
@Entity
@Table(name = "t_835_transaction_master_details")
public class Transaction835MasterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "patient_control_no")
    private String patientControlNo;

    @Column(name = "payor_claim_control_no")
    private String payorClaimControlNo;

    @Column(name = "service_date_from")
    private LocalDate serviceDateFrom;

    @Column(name = "service_date_to")
    private LocalDate serviceDateTo;

    @Column(name = "receipt_id")
    private Long receiptId;

    @Column(name = "receipt_no")
    private String receiptNo;

    @Column(name = "item_proc_code")
    private String itemProcCode;

    @Column(name = "item_modifiers")
    private String itemModifiers;

    @Column(name = "item_qty")
    private Long itemQty;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_notes")
    private String transactionNotes;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

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

    @Column(name = "edi_835_transaction_master_details_uuid")
    private UUID edi835TransactionMasterDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTransactionId() {
        return this.transactionId;
    }

    public Transaction835MasterDetails transactionId(Long transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getPatientControlNo() {
        return this.patientControlNo;
    }

    public Transaction835MasterDetails patientControlNo(String patientControlNo) {
        this.setPatientControlNo(patientControlNo);
        return this;
    }

    public void setPatientControlNo(String patientControlNo) {
        this.patientControlNo = patientControlNo;
    }

    public String getPayorClaimControlNo() {
        return this.payorClaimControlNo;
    }

    public Transaction835MasterDetails payorClaimControlNo(String payorClaimControlNo) {
        this.setPayorClaimControlNo(payorClaimControlNo);
        return this;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public LocalDate getServiceDateFrom() {
        return this.serviceDateFrom;
    }

    public Transaction835MasterDetails serviceDateFrom(LocalDate serviceDateFrom) {
        this.setServiceDateFrom(serviceDateFrom);
        return this;
    }

    public void setServiceDateFrom(LocalDate serviceDateFrom) {
        this.serviceDateFrom = serviceDateFrom;
    }

    public LocalDate getServiceDateTo() {
        return this.serviceDateTo;
    }

    public Transaction835MasterDetails serviceDateTo(LocalDate serviceDateTo) {
        this.setServiceDateTo(serviceDateTo);
        return this;
    }

    public void setServiceDateTo(LocalDate serviceDateTo) {
        this.serviceDateTo = serviceDateTo;
    }

    public Long getReceiptId() {
        return this.receiptId;
    }

    public Transaction835MasterDetails receiptId(Long receiptId) {
        this.setReceiptId(receiptId);
        return this;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNo() {
        return this.receiptNo;
    }

    public Transaction835MasterDetails receiptNo(String receiptNo) {
        this.setReceiptNo(receiptNo);
        return this;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getItemProcCode() {
        return this.itemProcCode;
    }

    public Transaction835MasterDetails itemProcCode(String itemProcCode) {
        this.setItemProcCode(itemProcCode);
        return this;
    }

    public void setItemProcCode(String itemProcCode) {
        this.itemProcCode = itemProcCode;
    }

    public String getItemModifiers() {
        return this.itemModifiers;
    }

    public Transaction835MasterDetails itemModifiers(String itemModifiers) {
        this.setItemModifiers(itemModifiers);
        return this;
    }

    public void setItemModifiers(String itemModifiers) {
        this.itemModifiers = itemModifiers;
    }

    public Long getItemQty() {
        return this.itemQty;
    }

    public Transaction835MasterDetails itemQty(Long itemQty) {
        this.setItemQty(itemQty);
        return this;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public Transaction835MasterDetails transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionNotes() {
        return this.transactionNotes;
    }

    public Transaction835MasterDetails transactionNotes(String transactionNotes) {
        this.setTransactionNotes(transactionNotes);
        return this;
    }

    public void setTransactionNotes(String transactionNotes) {
        this.transactionNotes = transactionNotes;
    }

    public Double getTransactionAmount() {
        return this.transactionAmount;
    }

    public Transaction835MasterDetails transactionAmount(Double transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public Transaction835MasterDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public Transaction835MasterDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public Transaction835MasterDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Transaction835MasterDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public Transaction835MasterDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public Transaction835MasterDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public Transaction835MasterDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getEdi835TransactionMasterDetailsUuid() {
        return this.edi835TransactionMasterDetailsUuid;
    }

    public Transaction835MasterDetails edi835TransactionMasterDetailsUuid(UUID edi835TransactionMasterDetailsUuid) {
        this.setEdi835TransactionMasterDetailsUuid(edi835TransactionMasterDetailsUuid);
        return this;
    }

    public void setEdi835TransactionMasterDetailsUuid(UUID edi835TransactionMasterDetailsUuid) {
        this.edi835TransactionMasterDetailsUuid = edi835TransactionMasterDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction835MasterDetails)) {
            return false;
        }
        return transactionId != null && transactionId.equals(((Transaction835MasterDetails) o).transactionId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction835MasterDetails{" +
            "transactionId=" + getTransactionId() +
            ", patientControlNo='" + getPatientControlNo() + "'" +
            ", payorClaimControlNo='" + getPayorClaimControlNo() + "'" +
            ", serviceDateFrom='" + getServiceDateFrom() + "'" +
            ", serviceDateTo='" + getServiceDateTo() + "'" +
            ", receiptId=" + getReceiptId() +
            ", receiptNo='" + getReceiptNo() + "'" +
            ", itemProcCode='" + getItemProcCode() + "'" +
            ", itemModifiers='" + getItemModifiers() + "'" +
            ", itemQty=" + getItemQty() +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionNotes='" + getTransactionNotes() + "'" +
            ", transactionAmount=" + getTransactionAmount() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", edi835TransactionMasterDetailsUuid='" + getEdi835TransactionMasterDetailsUuid() + "'" +
            "}";
    }
}
