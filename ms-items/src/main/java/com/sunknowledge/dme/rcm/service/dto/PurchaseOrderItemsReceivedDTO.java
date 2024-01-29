package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived} entity.
 */
public class PurchaseOrderItemsReceivedDTO implements Serializable {

    private Long poItemReceivedId;

    private Long poId;

    private String poNumber;

    private String status;

    private Long locationId;

    private String locationNo;

    private Long itemId;

    private String itemNo;

    private String itemUom;

    private String itemName;

    private LocalDate receivedDate;

    private Long itemQty;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private String lotBatchNo;

    private LocalDate mfgDate;

    private UUID purchaseOrderItemsReceivedUuid;

    public Long getPoItemReceivedId() {
        return poItemReceivedId;
    }

    public void setPoItemReceivedId(Long poItemReceivedId) {
        this.poItemReceivedId = poItemReceivedId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Long getItemQty() {
        return itemQty;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
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

    public String getLotBatchNo() {
        return lotBatchNo;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
    }

    public LocalDate getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public UUID getPurchaseOrderItemsReceivedUuid() {
        return purchaseOrderItemsReceivedUuid;
    }

    public void setPurchaseOrderItemsReceivedUuid(UUID purchaseOrderItemsReceivedUuid) {
        this.purchaseOrderItemsReceivedUuid = purchaseOrderItemsReceivedUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderItemsReceivedDTO)) {
            return false;
        }

        PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO = (PurchaseOrderItemsReceivedDTO) o;
        if (this.poItemReceivedId == null) {
            return false;
        }
        return Objects.equals(this.poItemReceivedId, purchaseOrderItemsReceivedDTO.poItemReceivedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.poItemReceivedId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderItemsReceivedDTO{" +
            "poItemReceivedId=" + getPoItemReceivedId() +
            ", poId=" + getPoId() +
            ", poNumber='" + getPoNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", locationId=" + getLocationId() +
            ", locationNo='" + getLocationNo() + "'" +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", itemQty=" + getItemQty() +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", lotBatchNo='" + getLotBatchNo() + "'" +
            ", mfgDate='" + getMfgDate() + "'" +
            ", purchaseOrderItemsReceivedUuid='" + getPurchaseOrderItemsReceivedUuid() + "'" +
            "}";
    }
}
