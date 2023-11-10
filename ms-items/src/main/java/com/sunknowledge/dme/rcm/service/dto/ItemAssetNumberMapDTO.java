package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemAssetNumberMapDTO implements Serializable {

    private Long itemAssetNumberId;

    private Long itemId;

    private String itemNo;

    private String itemName;

    private Long locationId;

    private String locationName;

    private String assetNumber;

    private String onHandStatus;

    private String onRentStatus;

    private LocalDate purchaseDate;

    private LocalDate saleDate;

    private String depreciationReadyStatus;

    private String depreciationStatus;

    private Long usefulLifeInYears;

    private LocalDate startDepreciationDate;

    private Double originalCost;

    private Double bookValue;

    private Double accumulatedDepreciation;

    private Double residualValue;

    private String userValue1;

    private String userValue2;

    private String userValue3;

    private String userValue4;

    private String lotBatchNo;

    private LocalDate lotBatchDate;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID itemAssetNumberUuid;

    public Long getItemAssetNumberId() {
        return itemAssetNumberId;
    }

    public void setItemAssetNumberId(Long itemAssetNumberId) {
        this.itemAssetNumberId = itemAssetNumberId;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getOnRentStatus() {
        return onRentStatus;
    }

    public void setOnRentStatus(String onRentStatus) {
        this.onRentStatus = onRentStatus;
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

    public String getDepreciationReadyStatus() {
        return depreciationReadyStatus;
    }

    public void setDepreciationReadyStatus(String depreciationReadyStatus) {
        this.depreciationReadyStatus = depreciationReadyStatus;
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

    public Double getAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }

    public void setAccumulatedDepreciation(Double accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    public Double getResidualValue() {
        return residualValue;
    }

    public void setResidualValue(Double residualValue) {
        this.residualValue = residualValue;
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

    public LocalDate getLotBatchDate() {
        return lotBatchDate;
    }

    public void setLotBatchDate(LocalDate lotBatchDate) {
        this.lotBatchDate = lotBatchDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getItemAssetNumberUuid() {
        return itemAssetNumberUuid;
    }

    public void setItemAssetNumberUuid(UUID itemAssetNumberUuid) {
        this.itemAssetNumberUuid = itemAssetNumberUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemAssetNumberMapDTO)) {
            return false;
        }

        ItemAssetNumberMapDTO itemAssetNumberMapDTO = (ItemAssetNumberMapDTO) o;
        if (this.itemAssetNumberId == null) {
            return false;
        }
        return Objects.equals(this.itemAssetNumberId, itemAssetNumberMapDTO.itemAssetNumberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemAssetNumberId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemAssetNumberMapDTO{" +
            "itemAssetNumberId=" + getItemAssetNumberId() +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", locationId=" + getLocationId() +
            ", locationName='" + getLocationName() + "'" +
            ", assetNumber='" + getAssetNumber() + "'" +
            ", onHandStatus='" + getOnHandStatus() + "'" +
            ", onRentStatus='" + getOnRentStatus() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            ", saleDate='" + getSaleDate() + "'" +
            ", depreciationReadyStatus='" + getDepreciationReadyStatus() + "'" +
            ", depreciationStatus='" + getDepreciationStatus() + "'" +
            ", usefulLifeInYears=" + getUsefulLifeInYears() +
            ", startDepreciationDate='" + getStartDepreciationDate() + "'" +
            ", originalCost=" + getOriginalCost() +
            ", bookValue=" + getBookValue() +
            ", accumulatedDepreciation=" + getAccumulatedDepreciation() +
            ", residualValue=" + getResidualValue() +
            ", userValue1='" + getUserValue1() + "'" +
            ", userValue2='" + getUserValue2() + "'" +
            ", userValue3='" + getUserValue3() + "'" +
            ", userValue4='" + getUserValue4() + "'" +
            ", lotBatchNo='" + getLotBatchNo() + "'" +
            ", lotBatchDate='" + getLotBatchDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", itemAssetNumberUuid='" + getItemAssetNumberUuid() + "'" +
            "}";
    }
}
