package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoItemTransactionDetailsDTO implements Serializable {

    @NotNull
    private Long soItemTransactionDetailsId;

    private String salesOrderNo;

    private String saleType;

    private Long itemId;

    private String itemNo;

    private String itemName;

    private String itemUom;

    private Long itemQty;

    private String wheatherSerialized;

    private String serialNo;

    private String wheatherAssetTagged;

    private String assetNo;

    private LocalDate originalDos;

    private Long branchId;

    private Long locationId;

    private String locationName;

    private String status;

    private LocalDate transactionDate;

    private String transactionNo;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID soItemTransactionDetailsUuid;

    private String updatedByName;

    private String isDropship;

    private String poNo;

    private String itemTransactionStatus;

    public Long getSoItemTransactionDetailsId() {
        return soItemTransactionDetailsId;
    }

    public void setSoItemTransactionDetailsId(Long soItemTransactionDetailsId) {
        this.soItemTransactionDetailsId = soItemTransactionDetailsId;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getWheatherAssetTagged() {
        return wheatherAssetTagged;
    }

    public void setWheatherAssetTagged(String wheatherAssetTagged) {
        this.wheatherAssetTagged = wheatherAssetTagged;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public LocalDate getOriginalDos() {
        return originalDos;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
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

    public UUID getSoItemTransactionDetailsUuid() {
        return soItemTransactionDetailsUuid;
    }

    public void setSoItemTransactionDetailsUuid(UUID soItemTransactionDetailsUuid) {
        this.soItemTransactionDetailsUuid = soItemTransactionDetailsUuid;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getIsDropship() {
        return isDropship;
    }

    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getItemTransactionStatus() {
        return itemTransactionStatus;
    }

    public void setItemTransactionStatus(String itemTransactionStatus) {
        this.itemTransactionStatus = itemTransactionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoItemTransactionDetailsDTO)) {
            return false;
        }

        SoItemTransactionDetailsDTO soItemTransactionDetailsDTO = (SoItemTransactionDetailsDTO) o;
        if (this.soItemTransactionDetailsId == null) {
            return false;
        }
        return Objects.equals(this.soItemTransactionDetailsId, soItemTransactionDetailsDTO.soItemTransactionDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.soItemTransactionDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoItemTransactionDetailsDTO{" +
            "soItemTransactionDetailsId=" + getSoItemTransactionDetailsId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", saleType='" + getSaleType() + "'" +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", itemQty=" + getItemQty() +
            ", wheatherSerialized='" + getWheatherSerialized() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            ", wheatherAssetTagged='" + getWheatherAssetTagged() + "'" +
            ", assetNo='" + getAssetNo() + "'" +
            ", originalDos='" + getOriginalDos() + "'" +
            ", branchId=" + getBranchId() +
            ", locationId=" + getLocationId() +
            ", locationName='" + getLocationName() + "'" +
            ", status='" + getStatus() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionNo='" + getTransactionNo() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", soItemTransactionDetailsUuid='" + getSoItemTransactionDetailsUuid() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", isDropship='" + getIsDropship() + "'" +
            ", poNo='" + getPoNo() + "'" +
            ", itemTransactionStatus='" + getItemTransactionStatus() + "'" +
            "}";
    }
}
