package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails} entity.
 */
public class InvoiceMasterDetailsDTO implements Serializable {

    private Long invoiceId;

    private String invoiceNo;

    private LocalDate invoiceDate;

    private String invoiceToEntity;

    private Long invoiceToPayorId;

    private String invoiceToPayorName;

    private Long salesOrderId;

    private String salesOrderNo;

    private Long patientId;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private Long itemQtyTotal;

    private Double chargedAmountTotal;

    private Double allowAmountTotal;

    private Double paymentAmountTotal;

    private Double taxAmountTotal;

    private Double adjAmountTotal;

    private Double balanceAmountTotal;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID invoiceMasterDetailsUuid;

    private String invoiceStatus;

    private Long primarySubmissionMasterId;

    private String claimControlNo;

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

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceToEntity() {
        return invoiceToEntity;
    }

    public void setInvoiceToEntity(String invoiceToEntity) {
        this.invoiceToEntity = invoiceToEntity;
    }

    public Long getInvoiceToPayorId() {
        return invoiceToPayorId;
    }

    public void setInvoiceToPayorId(Long invoiceToPayorId) {
        this.invoiceToPayorId = invoiceToPayorId;
    }

    public String getInvoiceToPayorName() {
        return invoiceToPayorName;
    }

    public void setInvoiceToPayorName(String invoiceToPayorName) {
        this.invoiceToPayorName = invoiceToPayorName;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Long getItemQtyTotal() {
        return itemQtyTotal;
    }

    public void setItemQtyTotal(Long itemQtyTotal) {
        this.itemQtyTotal = itemQtyTotal;
    }

    public Double getChargedAmountTotal() {
        return chargedAmountTotal;
    }

    public void setChargedAmountTotal(Double chargedAmountTotal) {
        this.chargedAmountTotal = chargedAmountTotal;
    }

    public Double getAllowAmountTotal() {
        return allowAmountTotal;
    }

    public void setAllowAmountTotal(Double allowAmountTotal) {
        this.allowAmountTotal = allowAmountTotal;
    }

    public Double getPaymentAmountTotal() {
        return paymentAmountTotal;
    }

    public void setPaymentAmountTotal(Double paymentAmountTotal) {
        this.paymentAmountTotal = paymentAmountTotal;
    }

    public Double getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(Double taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Double getAdjAmountTotal() {
        return adjAmountTotal;
    }

    public void setAdjAmountTotal(Double adjAmountTotal) {
        this.adjAmountTotal = adjAmountTotal;
    }

    public Double getBalanceAmountTotal() {
        return balanceAmountTotal;
    }

    public void setBalanceAmountTotal(Double balanceAmountTotal) {
        this.balanceAmountTotal = balanceAmountTotal;
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

    public UUID getInvoiceMasterDetailsUuid() {
        return invoiceMasterDetailsUuid;
    }

    public void setInvoiceMasterDetailsUuid(UUID invoiceMasterDetailsUuid) {
        this.invoiceMasterDetailsUuid = invoiceMasterDetailsUuid;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Long getPrimarySubmissionMasterId() {
        return primarySubmissionMasterId;
    }

    public void setPrimarySubmissionMasterId(Long primarySubmissionMasterId) {
        this.primarySubmissionMasterId = primarySubmissionMasterId;
    }

    public String getClaimControlNo() {
        return claimControlNo;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceMasterDetailsDTO)) {
            return false;
        }

        InvoiceMasterDetailsDTO invoiceMasterDetailsDTO = (InvoiceMasterDetailsDTO) o;
        if (this.invoiceId == null) {
            return false;
        }
        return Objects.equals(this.invoiceId, invoiceMasterDetailsDTO.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.invoiceId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceMasterDetailsDTO{" +
            "invoiceId=" + getInvoiceId() +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", invoiceToEntity='" + getInvoiceToEntity() + "'" +
            ", invoiceToPayorId=" + getInvoiceToPayorId() +
            ", invoiceToPayorName='" + getInvoiceToPayorName() + "'" +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", patientId=" + getPatientId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", itemQtyTotal=" + getItemQtyTotal() +
            ", chargedAmountTotal=" + getChargedAmountTotal() +
            ", allowAmountTotal=" + getAllowAmountTotal() +
            ", paymentAmountTotal=" + getPaymentAmountTotal() +
            ", taxAmountTotal=" + getTaxAmountTotal() +
            ", adjAmountTotal=" + getAdjAmountTotal() +
            ", balanceAmountTotal=" + getBalanceAmountTotal() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", invoiceMasterDetailsUuid='" + getInvoiceMasterDetailsUuid() + "'" +
            ", invoiceStatus='" + getInvoiceStatus() + "'" +
            ", primarySubmissionMasterId=" + getPrimarySubmissionMasterId() +
            ", claimControlNo='" + getClaimControlNo() + "'" +
            "}";
    }
}
