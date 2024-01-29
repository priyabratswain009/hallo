package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.StockAdjustment} entity.
 */
public class StockAdjustmentDTO implements Serializable {

    private Long stockAdjustmentId;

    private LocalDate adjustmentDate;

    private String adjustmentNo;

    private String adjustmentType;

    private String notes;

    private Long locationId;

    private String locationName;

    private Long itemId;

    private String itemName;

    private String itemNo;

    private String itemUom;

    private Long itemQty;

    private String wheatherSerialized;

    private String wheatherAssetTagged;

    private Double avgPrice;

    private String adjustmentStatus;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID stockAdjustmentUuid;

    private String status;

    private String updatedByName;

    private Long branchId;

    private String branchName;

    public Long getStockAdjustmentId() {
        return stockAdjustmentId;
    }

    public void setStockAdjustmentId(Long stockAdjustmentId) {
        this.stockAdjustmentId = stockAdjustmentId;
    }

    public LocalDate getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDate adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getAdjustmentNo() {
        return adjustmentNo;
    }

    public void setAdjustmentNo(String adjustmentNo) {
        this.adjustmentNo = adjustmentNo;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public Long getItemQty() {
        return itemQty;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getWheatherSerialized() {
        return wheatherSerialized;
    }

    public void setWheatherSerialized(String wheatherSerialized) {
        this.wheatherSerialized = wheatherSerialized;
    }

    public String getWheatherAssetTagged() {
        return wheatherAssetTagged;
    }

    public void setWheatherAssetTagged(String wheatherAssetTagged) {
        this.wheatherAssetTagged = wheatherAssetTagged;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAdjustmentStatus() {
        return adjustmentStatus;
    }

    public void setAdjustmentStatus(String adjustmentStatus) {
        this.adjustmentStatus = adjustmentStatus;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getStockAdjustmentUuid() {
        return stockAdjustmentUuid;
    }

    public void setStockAdjustmentUuid(UUID stockAdjustmentUuid) {
        this.stockAdjustmentUuid = stockAdjustmentUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockAdjustmentDTO)) {
            return false;
        }

        StockAdjustmentDTO stockAdjustmentDTO = (StockAdjustmentDTO) o;
        if (this.stockAdjustmentId == null) {
            return false;
        }
        return Objects.equals(this.stockAdjustmentId, stockAdjustmentDTO.stockAdjustmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stockAdjustmentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockAdjustmentDTO{" +
            "stockAdjustmentId=" + getStockAdjustmentId() +
            ", adjustmentDate='" + getAdjustmentDate() + "'" +
            ", adjustmentNo='" + getAdjustmentNo() + "'" +
            ", adjustmentType='" + getAdjustmentType() + "'" +
            ", notes='" + getNotes() + "'" +
            ", locationId=" + getLocationId() +
            ", locationName='" + getLocationName() + "'" +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", itemQty=" + getItemQty() +
            ", wheatherSerialized='" + getWheatherSerialized() + "'" +
            ", wheatherAssetTagged='" + getWheatherAssetTagged() + "'" +
            ", avgPrice=" + getAvgPrice() +
            ", adjustmentStatus='" + getAdjustmentStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", stockAdjustmentUuid='" + getStockAdjustmentUuid() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            "}";
    }
}
