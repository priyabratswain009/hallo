package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemMaster} entity.
 */
public class ItemMasterDTO implements Serializable {

    private Long itemId;

    @NotNull
    private String itemName;

    @NotNull
    private String itemDescription;

    private Long itemTypeId;

    private Long itemGroupId;

    private String saleType;

    private String coverageType;

    private String weight;

    private String lotStatus;

    private String kitStatus;

    private String kitType;

    private Double itemPricingDefaultRentalAmt;

    private Double itemPricingDefaultPurchaseAmt;

    private String autoReorderStatus;

    private String excludePoStatus;

    private String excludeSoStatus;

    private String ndc;

    private String ndcUnitOfMeasurement;

    private String manufacturerName;

    private Long manufacturerId;

    private String manufacturerBarcode;

    private Long defaultVendorId;

    private String excludeStandardPriceingStat;

    private String userField1;

    private String userField2;

    private String userField3;

    private String billingMultiplier;

    private String claimNote;

    private String itemUom;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID itemMasterUuid;

    private String itemNumber;

    private String itemGroupName;

    private String itemTypeName;

    private String primaryProcedureCodeValue;

    private String defaultVendorName;

    private String resupplyTypeStatus;

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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(String lotStatus) {
        this.lotStatus = lotStatus;
    }

    public String getKitStatus() {
        return kitStatus;
    }

    public void setKitStatus(String kitStatus) {
        this.kitStatus = kitStatus;
    }

    public String getKitType() {
        return kitType;
    }

    public void setKitType(String kitType) {
        this.kitType = kitType;
    }

    public Double getItemPricingDefaultRentalAmt() {
        return itemPricingDefaultRentalAmt;
    }

    public void setItemPricingDefaultRentalAmt(Double itemPricingDefaultRentalAmt) {
        this.itemPricingDefaultRentalAmt = itemPricingDefaultRentalAmt;
    }

    public Double getItemPricingDefaultPurchaseAmt() {
        return itemPricingDefaultPurchaseAmt;
    }

    public void setItemPricingDefaultPurchaseAmt(Double itemPricingDefaultPurchaseAmt) {
        this.itemPricingDefaultPurchaseAmt = itemPricingDefaultPurchaseAmt;
    }

    public String getAutoReorderStatus() {
        return autoReorderStatus;
    }

    public void setAutoReorderStatus(String autoReorderStatus) {
        this.autoReorderStatus = autoReorderStatus;
    }

    public String getExcludePoStatus() {
        return excludePoStatus;
    }

    public void setExcludePoStatus(String excludePoStatus) {
        this.excludePoStatus = excludePoStatus;
    }

    public String getExcludeSoStatus() {
        return excludeSoStatus;
    }

    public void setExcludeSoStatus(String excludeSoStatus) {
        this.excludeSoStatus = excludeSoStatus;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getNdcUnitOfMeasurement() {
        return ndcUnitOfMeasurement;
    }

    public void setNdcUnitOfMeasurement(String ndcUnitOfMeasurement) {
        this.ndcUnitOfMeasurement = ndcUnitOfMeasurement;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerBarcode() {
        return manufacturerBarcode;
    }

    public void setManufacturerBarcode(String manufacturerBarcode) {
        this.manufacturerBarcode = manufacturerBarcode;
    }

    public Long getDefaultVendorId() {
        return defaultVendorId;
    }

    public void setDefaultVendorId(Long defaultVendorId) {
        this.defaultVendorId = defaultVendorId;
    }

    public String getExcludeStandardPriceingStat() {
        return excludeStandardPriceingStat;
    }

    public void setExcludeStandardPriceingStat(String excludeStandardPriceingStat) {
        this.excludeStandardPriceingStat = excludeStandardPriceingStat;
    }

    public String getUserField1() {
        return userField1;
    }

    public void setUserField1(String userField1) {
        this.userField1 = userField1;
    }

    public String getUserField2() {
        return userField2;
    }

    public void setUserField2(String userField2) {
        this.userField2 = userField2;
    }

    public String getUserField3() {
        return userField3;
    }

    public void setUserField3(String userField3) {
        this.userField3 = userField3;
    }

    public String getBillingMultiplier() {
        return billingMultiplier;
    }

    public void setBillingMultiplier(String billingMultiplier) {
        this.billingMultiplier = billingMultiplier;
    }

    public String getClaimNote() {
        return claimNote;
    }

    public void setClaimNote(String claimNote) {
        this.claimNote = claimNote;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
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

    public UUID getItemMasterUuid() {
        return itemMasterUuid;
    }

    public void setItemMasterUuid(UUID itemMasterUuid) {
        this.itemMasterUuid = itemMasterUuid;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getPrimaryProcedureCodeValue() {
        return primaryProcedureCodeValue;
    }

    public void setPrimaryProcedureCodeValue(String primaryProcedureCodeValue) {
        this.primaryProcedureCodeValue = primaryProcedureCodeValue;
    }

    public String getDefaultVendorName() {
        return defaultVendorName;
    }

    public void setDefaultVendorName(String defaultVendorName) {
        this.defaultVendorName = defaultVendorName;
    }

    public String getResupplyTypeStatus() {
        return resupplyTypeStatus;
    }

    public void setResupplyTypeStatus(String resupplyTypeStatus) {
        this.resupplyTypeStatus = resupplyTypeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemMasterDTO)) {
            return false;
        }

        ItemMasterDTO itemMasterDTO = (ItemMasterDTO) o;
        if (this.itemId == null) {
            return false;
        }
        return Objects.equals(this.itemId, itemMasterDTO.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemMasterDTO{" +
            "itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemTypeId=" + getItemTypeId() +
            ", itemGroupId=" + getItemGroupId() +
            ", saleType='" + getSaleType() + "'" +
            ", coverageType='" + getCoverageType() + "'" +
            ", weight='" + getWeight() + "'" +
            ", lotStatus='" + getLotStatus() + "'" +
            ", kitStatus='" + getKitStatus() + "'" +
            ", kitType='" + getKitType() + "'" +
            ", itemPricingDefaultRentalAmt=" + getItemPricingDefaultRentalAmt() +
            ", itemPricingDefaultPurchaseAmt=" + getItemPricingDefaultPurchaseAmt() +
            ", autoReorderStatus='" + getAutoReorderStatus() + "'" +
            ", excludePoStatus='" + getExcludePoStatus() + "'" +
            ", excludeSoStatus='" + getExcludeSoStatus() + "'" +
            ", ndc='" + getNdc() + "'" +
            ", ndcUnitOfMeasurement='" + getNdcUnitOfMeasurement() + "'" +
            ", manufacturerName='" + getManufacturerName() + "'" +
            ", manufacturerId=" + getManufacturerId() +
            ", manufacturerBarcode='" + getManufacturerBarcode() + "'" +
            ", defaultVendorId=" + getDefaultVendorId() +
            ", excludeStandardPriceingStat='" + getExcludeStandardPriceingStat() + "'" +
            ", userField1='" + getUserField1() + "'" +
            ", userField2='" + getUserField2() + "'" +
            ", userField3='" + getUserField3() + "'" +
            ", billingMultiplier='" + getBillingMultiplier() + "'" +
            ", claimNote='" + getClaimNote() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", itemMasterUuid='" + getItemMasterUuid() + "'" +
            ", itemNumber='" + getItemNumber() + "'" +
            ", itemGroupName='" + getItemGroupName() + "'" +
            ", itemTypeName='" + getItemTypeName() + "'" +
            ", primaryProcedureCodeValue='" + getPrimaryProcedureCodeValue() + "'" +
            ", defaultVendorName='" + getDefaultVendorName() + "'" +
            ", resupplyTypeStatus='" + getResupplyTypeStatus() + "'" +
            "}";
    }
}
