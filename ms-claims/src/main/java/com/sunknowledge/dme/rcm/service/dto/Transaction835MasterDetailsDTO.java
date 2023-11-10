package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails} entity.
 */
public class Transaction835MasterDetailsDTO implements Serializable {

    @NotNull
    private Long transactionId;

    private String patientControlNo;

    private String payorClaimControlNo;

    private LocalDate serviceDateFrom;

    private LocalDate serviceDateTo;

    private Long receiptId;

    private String receiptNo;

    private String itemProcCode;

    private String itemModifiers;

    private Long itemQty;

    private String transactionType;

    private String transactionNotes;

    private Double transactionAmount;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID edi835TransactionMasterDetailsUuid;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getPatientControlNo() {
        return patientControlNo;
    }

    public void setPatientControlNo(String patientControlNo) {
        this.patientControlNo = patientControlNo;
    }

    public String getPayorClaimControlNo() {
        return payorClaimControlNo;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public LocalDate getServiceDateFrom() {
        return serviceDateFrom;
    }

    public void setServiceDateFrom(LocalDate serviceDateFrom) {
        this.serviceDateFrom = serviceDateFrom;
    }

    public LocalDate getServiceDateTo() {
        return serviceDateTo;
    }

    public void setServiceDateTo(LocalDate serviceDateTo) {
        this.serviceDateTo = serviceDateTo;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getItemProcCode() {
        return itemProcCode;
    }

    public void setItemProcCode(String itemProcCode) {
        this.itemProcCode = itemProcCode;
    }

    public String getItemModifiers() {
        return itemModifiers;
    }

    public void setItemModifiers(String itemModifiers) {
        this.itemModifiers = itemModifiers;
    }

    public Long getItemQty() {
        return itemQty;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionNotes() {
        return transactionNotes;
    }

    public void setTransactionNotes(String transactionNotes) {
        this.transactionNotes = transactionNotes;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
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

    public UUID getEdi835TransactionMasterDetailsUuid() {
        return edi835TransactionMasterDetailsUuid;
    }

    public void setEdi835TransactionMasterDetailsUuid(UUID edi835TransactionMasterDetailsUuid) {
        this.edi835TransactionMasterDetailsUuid = edi835TransactionMasterDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction835MasterDetailsDTO)) {
            return false;
        }

        Transaction835MasterDetailsDTO transaction835MasterDetailsDTO = (Transaction835MasterDetailsDTO) o;
        if (this.transactionId == null) {
            return false;
        }
        return Objects.equals(this.transactionId, transaction835MasterDetailsDTO.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.transactionId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction835MasterDetailsDTO{" +
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
