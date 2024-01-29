package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItems} entity.
 */
public class PurchaseOrderItemsDTO implements Serializable {

    private Long poItemsId;

    private Long poId;

    private String poNumber;

    private Long itemId;

    private String itemName;

    private String itemNo;

    private String itemUom;

    private Long orderedQty;

    private Long receivedQty;

    private Long cancelledQty;

    private Double unitPrice;

    private Double totalAmount;

    private String status;

    private String notes;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedName;

    private LocalDate updatedDate;

    private UUID purchaseOrderItemsUuid;

    private String poStatus;

    private String wheatherSerialised;

    private String lotNo;

    private LocalDate mfgDate;

    private LocalDate receivedDate;

    public Long getPoItemsId() {
        return poItemsId;
    }

    public void setPoItemsId(Long poItemsId) {
        this.poItemsId = poItemsId;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Long getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(Long orderedQty) {
        this.orderedQty = orderedQty;
    }

    public Long getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(Long receivedQty) {
        this.receivedQty = receivedQty;
    }

    public Long getCancelledQty() {
        return cancelledQty;
    }

    public void setCancelledQty(Long cancelledQty) {
        this.cancelledQty = cancelledQty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPurchaseOrderItemsUuid() {
        return purchaseOrderItemsUuid;
    }

    public void setPurchaseOrderItemsUuid(UUID purchaseOrderItemsUuid) {
        this.purchaseOrderItemsUuid = purchaseOrderItemsUuid;
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public String getWheatherSerialised() {
        return wheatherSerialised;
    }

    public void setWheatherSerialised(String wheatherSerialised) {
        this.wheatherSerialised = wheatherSerialised;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public LocalDate getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderItemsDTO)) {
            return false;
        }

        PurchaseOrderItemsDTO purchaseOrderItemsDTO = (PurchaseOrderItemsDTO) o;
        if (this.poItemsId == null) {
            return false;
        }
        return Objects.equals(this.poItemsId, purchaseOrderItemsDTO.poItemsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.poItemsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderItemsDTO{" +
            "poItemsId=" + getPoItemsId() +
            ", poId=" + getPoId() +
            ", poNumber='" + getPoNumber() + "'" +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", orderedQty=" + getOrderedQty() +
            ", receivedQty=" + getReceivedQty() +
            ", cancelledQty=" + getCancelledQty() +
            ", unitPrice=" + getUnitPrice() +
            ", totalAmount=" + getTotalAmount() +
            ", status='" + getStatus() + "'" +
            ", notes='" + getNotes() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedName='" + getUpdatedName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", purchaseOrderItemsUuid='" + getPurchaseOrderItemsUuid() + "'" +
            ", poStatus='" + getPoStatus() + "'" +
            ", wheatherSerialised='" + getWheatherSerialised() + "'" +
            ", lotNo='" + getLotNo() + "'" +
            ", mfgDate='" + getMfgDate() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            "}";
    }
}
