package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemSerialNumber} entity.
 */
public class ItemSerialNumberDTO implements Serializable {

    private Long itemSerialNumberId;

    private Long itemId;

    private Long locationId;

    private String serialNumber;

    private String assetNumber;

    private String onHandStatus;

    private LocalDate purchaseDate;

    private LocalDate saleDate;

    private String depreciationStatus;

    private Long usefulLifeInYears;

    private LocalDate startDepreciationDate;

    private Double originalCost;

    private Double bookValue;

    private String userValue1;

    private String userValue2;

    private String userValue3;

    private String userValue4;

    private String lotBatchNo;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID itemSerialNumberUuid;

    private String itemNo;

    private String itemName;

    private String locationName;

    private String onRentStatus;

    private LocalDate lotBatchDate;

    private LocalDate updatedDate;

    private String lotNo;

    private LocalDate mfgDate;

    private Long poId;

    private String poNo;

    private Long adjustmentId;

    private String adjustmentNo;

    private String isDropship;

    public Long getItemSerialNumberId() {
        return itemSerialNumberId;
    }

    public void setItemSerialNumberId(Long itemSerialNumberId) {
        this.itemSerialNumberId = itemSerialNumberId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getOnHandStatus() {
        return onHandStatus;
    }

    public void setOnHandStatus(String onHandStatus) {
        this.onHandStatus = onHandStatus;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getDepreciationStatus() {
        return depreciationStatus;
    }

    public void setDepreciationStatus(String depreciationStatus) {
        this.depreciationStatus = depreciationStatus;
    }

    public Long getUsefulLifeInYears() {
        return usefulLifeInYears;
    }

    public void setUsefulLifeInYears(Long usefulLifeInYears) {
        this.usefulLifeInYears = usefulLifeInYears;
    }

    public LocalDate getStartDepreciationDate() {
        return startDepreciationDate;
    }

    public void setStartDepreciationDate(LocalDate startDepreciationDate) {
        this.startDepreciationDate = startDepreciationDate;
    }

    public Double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Double originalCost) {
        this.originalCost = originalCost;
    }

    public Double getBookValue() {
        return bookValue;
    }

    public void setBookValue(Double bookValue) {
        this.bookValue = bookValue;
    }

    public String getUserValue1() {
        return userValue1;
    }

    public void setUserValue1(String userValue1) {
        this.userValue1 = userValue1;
    }

    public String getUserValue2() {
        return userValue2;
    }

    public void setUserValue2(String userValue2) {
        this.userValue2 = userValue2;
    }

    public String getUserValue3() {
        return userValue3;
    }

    public void setUserValue3(String userValue3) {
        this.userValue3 = userValue3;
    }

    public String getUserValue4() {
        return userValue4;
    }

    public void setUserValue4(String userValue4) {
        this.userValue4 = userValue4;
    }

    public String getLotBatchNo() {
        return lotBatchNo;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemSerialNumberUuid() {
        return itemSerialNumberUuid;
    }

    public void setItemSerialNumberUuid(UUID itemSerialNumberUuid) {
        this.itemSerialNumberUuid = itemSerialNumberUuid;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getOnRentStatus() {
        return onRentStatus;
    }

    public void setOnRentStatus(String onRentStatus) {
        this.onRentStatus = onRentStatus;
    }

    public LocalDate getLotBatchDate() {
        return lotBatchDate;
    }

    public void setLotBatchDate(LocalDate lotBatchDate) {
        this.lotBatchDate = lotBatchDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Long getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentNo() {
        return adjustmentNo;
    }

    public void setAdjustmentNo(String adjustmentNo) {
        this.adjustmentNo = adjustmentNo;
    }

    public String getIsDropship() {
        return isDropship;
    }

    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemSerialNumberDTO)) {
            return false;
        }

        ItemSerialNumberDTO itemSerialNumberDTO = (ItemSerialNumberDTO) o;
        if (this.itemSerialNumberId == null) {
            return false;
        }
        return Objects.equals(this.itemSerialNumberId, itemSerialNumberDTO.itemSerialNumberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemSerialNumberId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemSerialNumberDTO{" +
            "itemSerialNumberId=" + getItemSerialNumberId() +
            ", itemId=" + getItemId() +
            ", locationId=" + getLocationId() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", assetNumber='" + getAssetNumber() + "'" +
            ", onHandStatus='" + getOnHandStatus() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            ", saleDate='" + getSaleDate() + "'" +
            ", depreciationStatus='" + getDepreciationStatus() + "'" +
            ", usefulLifeInYears=" + getUsefulLifeInYears() +
            ", startDepreciationDate='" + getStartDepreciationDate() + "'" +
            ", originalCost=" + getOriginalCost() +
            ", bookValue=" + getBookValue() +
            ", userValue1='" + getUserValue1() + "'" +
            ", userValue2='" + getUserValue2() + "'" +
            ", userValue3='" + getUserValue3() + "'" +
            ", userValue4='" + getUserValue4() + "'" +
            ", lotBatchNo='" + getLotBatchNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemSerialNumberUuid='" + getItemSerialNumberUuid() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", onRentStatus='" + getOnRentStatus() + "'" +
            ", lotBatchDate='" + getLotBatchDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", lotNo='" + getLotNo() + "'" +
            ", mfgDate='" + getMfgDate() + "'" +
            ", poId=" + getPoId() +
            ", poNo='" + getPoNo() + "'" +
            ", adjustmentId=" + getAdjustmentId() +
            ", adjustmentNo='" + getAdjustmentNo() + "'" +
            ", isDropship='" + getIsDropship() + "'" +
            "}";
    }
}
