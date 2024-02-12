package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvoiceLineItemDetailsDTO implements Serializable {

    private Long invoiceLineItemDetailsId;

    private String primaryInvoiceNo;

    private LocalDate invoiceDate;

    private Long itemId;

    private Long itemQty;

    private String itemName;

    private String hcpcsCode;

    private Long chargedAmount;

    private Double allowAmount;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID invoiceLineItemDetailsUuid;

    private String secondaryInvoiceNo;

    private String tertiaryInvoiceNo;

    private String patientInvoiceNo;

    private Long primaryInvoiceId;

    private Long secondaryInvoiceId;

    private Long tertiaryInvoiceId;

    private Long patientInvoiceId;

    public Long getInvoiceLineItemDetailsId() {
        return invoiceLineItemDetailsId;
    }

    public void setInvoiceLineItemDetailsId(Long invoiceLineItemDetailsId) {
        this.invoiceLineItemDetailsId = invoiceLineItemDetailsId;
    }

    public String getPrimaryInvoiceNo() {
        return primaryInvoiceNo;
    }

    public void setPrimaryInvoiceNo(String primaryInvoiceNo) {
        this.primaryInvoiceNo = primaryInvoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemQty() {
        return itemQty;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Long getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Long chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowAmount() {
        return allowAmount;
    }

    public void setAllowAmount(Double allowAmount) {
        this.allowAmount = allowAmount;
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

    public UUID getInvoiceLineItemDetailsUuid() {
        return invoiceLineItemDetailsUuid;
    }

    public void setInvoiceLineItemDetailsUuid(UUID invoiceLineItemDetailsUuid) {
        this.invoiceLineItemDetailsUuid = invoiceLineItemDetailsUuid;
    }

    public String getSecondaryInvoiceNo() {
        return secondaryInvoiceNo;
    }

    public void setSecondaryInvoiceNo(String secondaryInvoiceNo) {
        this.secondaryInvoiceNo = secondaryInvoiceNo;
    }

    public String getTertiaryInvoiceNo() {
        return tertiaryInvoiceNo;
    }

    public void setTertiaryInvoiceNo(String tertiaryInvoiceNo) {
        this.tertiaryInvoiceNo = tertiaryInvoiceNo;
    }

    public String getPatientInvoiceNo() {
        return patientInvoiceNo;
    }

    public void setPatientInvoiceNo(String patientInvoiceNo) {
        this.patientInvoiceNo = patientInvoiceNo;
    }

    public Long getPrimaryInvoiceId() {
        return primaryInvoiceId;
    }

    public void setPrimaryInvoiceId(Long primaryInvoiceId) {
        this.primaryInvoiceId = primaryInvoiceId;
    }

    public Long getSecondaryInvoiceId() {
        return secondaryInvoiceId;
    }

    public void setSecondaryInvoiceId(Long secondaryInvoiceId) {
        this.secondaryInvoiceId = secondaryInvoiceId;
    }

    public Long getTertiaryInvoiceId() {
        return tertiaryInvoiceId;
    }

    public void setTertiaryInvoiceId(Long tertiaryInvoiceId) {
        this.tertiaryInvoiceId = tertiaryInvoiceId;
    }

    public Long getPatientInvoiceId() {
        return patientInvoiceId;
    }

    public void setPatientInvoiceId(Long patientInvoiceId) {
        this.patientInvoiceId = patientInvoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceLineItemDetailsDTO)) {
            return false;
        }

        InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO = (InvoiceLineItemDetailsDTO) o;
        if (this.invoiceLineItemDetailsId == null) {
            return false;
        }
        return Objects.equals(this.invoiceLineItemDetailsId, invoiceLineItemDetailsDTO.invoiceLineItemDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.invoiceLineItemDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceLineItemDetailsDTO{" +
            "invoiceLineItemDetailsId=" + getInvoiceLineItemDetailsId() +
            ", primaryInvoiceNo='" + getPrimaryInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", itemId=" + getItemId() +
            ", itemQty=" + getItemQty() +
            ", itemName='" + getItemName() + "'" +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", chargedAmount=" + getChargedAmount() +
            ", allowAmount=" + getAllowAmount() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", invoiceLineItemDetailsUuid='" + getInvoiceLineItemDetailsUuid() + "'" +
            ", secondaryInvoiceNo='" + getSecondaryInvoiceNo() + "'" +
            ", tertiaryInvoiceNo='" + getTertiaryInvoiceNo() + "'" +
            ", patientInvoiceNo='" + getPatientInvoiceNo() + "'" +
            ", primaryInvoiceId=" + getPrimaryInvoiceId() +
            ", secondaryInvoiceId=" + getSecondaryInvoiceId() +
            ", tertiaryInvoiceId=" + getTertiaryInvoiceId() +
            ", patientInvoiceId=" + getPatientInvoiceId() +
            "}";
    }
}
