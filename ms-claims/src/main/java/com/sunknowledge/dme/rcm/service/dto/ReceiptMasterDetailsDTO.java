package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails} entity.
 */
public class ReceiptMasterDetailsDTO implements Serializable {

    @NotNull
    private Long receiptId;

    private String receiptNo;

    private Long depositId;

    private String depositNo;

    private String receiptReference;

    private Double receiptAmount;

    private String paymentMode;

    private LocalDate paymentDate;

    private String receiptNotes;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID receiptMasterDetailsUuid;

    private String receiptStatus;

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

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getDepositNo() {
        return depositNo;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public String getReceiptReference() {
        return receiptReference;
    }

    public void setReceiptReference(String receiptReference) {
        this.receiptReference = receiptReference;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceiptNotes() {
        return receiptNotes;
    }

    public void setReceiptNotes(String receiptNotes) {
        this.receiptNotes = receiptNotes;
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

    public UUID getReceiptMasterDetailsUuid() {
        return receiptMasterDetailsUuid;
    }

    public void setReceiptMasterDetailsUuid(UUID receiptMasterDetailsUuid) {
        this.receiptMasterDetailsUuid = receiptMasterDetailsUuid;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReceiptMasterDetailsDTO)) {
            return false;
        }

        ReceiptMasterDetailsDTO receiptMasterDetailsDTO = (ReceiptMasterDetailsDTO) o;
        if (this.receiptId == null) {
            return false;
        }
        return Objects.equals(this.receiptId, receiptMasterDetailsDTO.receiptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.receiptId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReceiptMasterDetailsDTO{" +
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
