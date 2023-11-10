package com.sunknowledge.dme.rcm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ItemMaster.
 */
@Entity
@Table(name = "t_item_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_id")
    private Long itemId;

    @NotNull
    @Column(name = "item_name", nullable = false)
    private String itemName;

    @NotNull
    @Column(name = "item_description", nullable = false)
    private String itemDescription;

    @Column(name = "item_type_id")
    private Long itemTypeId;

    @Column(name = "item_group_id")
    private Long itemGroupId;

    @Column(name = "sale_type")
    private String saleType;

    @Column(name = "coverage_type")
    private String coverageType;

    @Column(name = "weight")
    private String weight;

    @Column(name = "lot_status")
    private String lotStatus;

    @Column(name = "kit_status")
    private String kitStatus;

    @Column(name = "kit_type")
    private String kitType;

    @Column(name = "item_pricing_default_rental_amt")
    private Double itemPricingDefaultRentalAmt;

    @Column(name = "item_pricing_default_purchase_amt")
    private Double itemPricingDefaultPurchaseAmt;

    @Column(name = "auto_reorder_status")
    private String autoReorderStatus;

    @Column(name = "exclude_po_status")
    private String excludePoStatus;

    @Column(name = "exclude_so_status")
    private String excludeSoStatus;

    @Column(name = "ndc")
    private String ndc;

    @Column(name = "ndc_unit_of_measurement")
    private String ndcUnitOfMeasurement;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    @Column(name = "manufacturer_barcode")
    private String manufacturerBarcode;

    @Column(name = "default_vendor_id")
    private Long defaultVendorId;

    @Column(name = "exclude_standard_priceing_stat")
    private String excludeStandardPriceingStat;

    @Column(name = "user_field_1")
    private String userField1;

    @Column(name = "user_field_2")
    private String userField2;

    @Column(name = "user_field_3")
    private String userField3;

    @Column(name = "billing_multiplier")
    private String billingMultiplier;

    @Column(name = "claim_note")
    private String claimNote;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "item_master_uuid")
    private UUID itemMasterUuid;

    @Column(name = "item_number")
    private String itemNumber;

    @Column(name = "item_group_name")
    private String itemGroupName;

    @Column(name = "item_type_name")
    private String itemTypeName;

    @Column(name = "primary_procedure_code_value")
    private String primaryProcedureCodeValue;

    @Column(name = "default_vendor_name")
    private String defaultVendorName;

    @Column(name = "resupply_type_status")
    private String resupplyTypeStatus;

    @OneToMany(mappedBy = "itemMaster")
    @JsonIgnoreProperties(value = { "itemMaster" }, allowSetters = true)
    private Set<ItemProcedureCodeMap> procedureDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemId() {
        return this.itemId;
    }

    public ItemMaster itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemMaster itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public ItemMaster itemDescription(String itemDescription) {
        this.setItemDescription(itemDescription);
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getItemTypeId() {
        return this.itemTypeId;
    }

    public ItemMaster itemTypeId(Long itemTypeId) {
        this.setItemTypeId(itemTypeId);
        return this;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getItemGroupId() {
        return this.itemGroupId;
    }

    public ItemMaster itemGroupId(Long itemGroupId) {
        this.setItemGroupId(itemGroupId);
        return this;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getSaleType() {
        return this.saleType;
    }

    public ItemMaster saleType(String saleType) {
        this.setSaleType(saleType);
        return this;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getCoverageType() {
        return this.coverageType;
    }

    public ItemMaster coverageType(String coverageType) {
        this.setCoverageType(coverageType);
        return this;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public String getWeight() {
        return this.weight;
    }

    public ItemMaster weight(String weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLotStatus() {
        return this.lotStatus;
    }

    public ItemMaster lotStatus(String lotStatus) {
        this.setLotStatus(lotStatus);
        return this;
    }

    public void setLotStatus(String lotStatus) {
        this.lotStatus = lotStatus;
    }

    public String getKitStatus() {
        return this.kitStatus;
    }

    public ItemMaster kitStatus(String kitStatus) {
        this.setKitStatus(kitStatus);
        return this;
    }

    public void setKitStatus(String kitStatus) {
        this.kitStatus = kitStatus;
    }

    public String getKitType() {
        return this.kitType;
    }

    public ItemMaster kitType(String kitType) {
        this.setKitType(kitType);
        return this;
    }

    public void setKitType(String kitType) {
        this.kitType = kitType;
    }

    public Double getItemPricingDefaultRentalAmt() {
        return this.itemPricingDefaultRentalAmt;
    }

    public ItemMaster itemPricingDefaultRentalAmt(Double itemPricingDefaultRentalAmt) {
        this.setItemPricingDefaultRentalAmt(itemPricingDefaultRentalAmt);
        return this;
    }

    public void setItemPricingDefaultRentalAmt(Double itemPricingDefaultRentalAmt) {
        this.itemPricingDefaultRentalAmt = itemPricingDefaultRentalAmt;
    }

    public Double getItemPricingDefaultPurchaseAmt() {
        return this.itemPricingDefaultPurchaseAmt;
    }

    public ItemMaster itemPricingDefaultPurchaseAmt(Double itemPricingDefaultPurchaseAmt) {
        this.setItemPricingDefaultPurchaseAmt(itemPricingDefaultPurchaseAmt);
        return this;
    }

    public void setItemPricingDefaultPurchaseAmt(Double itemPricingDefaultPurchaseAmt) {
        this.itemPricingDefaultPurchaseAmt = itemPricingDefaultPurchaseAmt;
    }

    public String getAutoReorderStatus() {
        return this.autoReorderStatus;
    }

    public ItemMaster autoReorderStatus(String autoReorderStatus) {
        this.setAutoReorderStatus(autoReorderStatus);
        return this;
    }

    public void setAutoReorderStatus(String autoReorderStatus) {
        this.autoReorderStatus = autoReorderStatus;
    }

    public String getExcludePoStatus() {
        return this.excludePoStatus;
    }

    public ItemMaster excludePoStatus(String excludePoStatus) {
        this.setExcludePoStatus(excludePoStatus);
        return this;
    }

    public void setExcludePoStatus(String excludePoStatus) {
        this.excludePoStatus = excludePoStatus;
    }

    public String getExcludeSoStatus() {
        return this.excludeSoStatus;
    }

    public ItemMaster excludeSoStatus(String excludeSoStatus) {
        this.setExcludeSoStatus(excludeSoStatus);
        return this;
    }

    public void setExcludeSoStatus(String excludeSoStatus) {
        this.excludeSoStatus = excludeSoStatus;
    }

    public String getNdc() {
        return this.ndc;
    }

    public ItemMaster ndc(String ndc) {
        this.setNdc(ndc);
        return this;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getNdcUnitOfMeasurement() {
        return this.ndcUnitOfMeasurement;
    }

    public ItemMaster ndcUnitOfMeasurement(String ndcUnitOfMeasurement) {
        this.setNdcUnitOfMeasurement(ndcUnitOfMeasurement);
        return this;
    }

    public void setNdcUnitOfMeasurement(String ndcUnitOfMeasurement) {
        this.ndcUnitOfMeasurement = ndcUnitOfMeasurement;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public ItemMaster manufacturerName(String manufacturerName) {
        this.setManufacturerName(manufacturerName);
        return this;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getManufacturerId() {
        return this.manufacturerId;
    }

    public ItemMaster manufacturerId(Long manufacturerId) {
        this.setManufacturerId(manufacturerId);
        return this;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerBarcode() {
        return this.manufacturerBarcode;
    }

    public ItemMaster manufacturerBarcode(String manufacturerBarcode) {
        this.setManufacturerBarcode(manufacturerBarcode);
        return this;
    }

    public void setManufacturerBarcode(String manufacturerBarcode) {
        this.manufacturerBarcode = manufacturerBarcode;
    }

    public Long getDefaultVendorId() {
        return this.defaultVendorId;
    }

    public ItemMaster defaultVendorId(Long defaultVendorId) {
        this.setDefaultVendorId(defaultVendorId);
        return this;
    }

    public void setDefaultVendorId(Long defaultVendorId) {
        this.defaultVendorId = defaultVendorId;
    }

    public String getExcludeStandardPriceingStat() {
        return this.excludeStandardPriceingStat;
    }

    public ItemMaster excludeStandardPriceingStat(String excludeStandardPriceingStat) {
        this.setExcludeStandardPriceingStat(excludeStandardPriceingStat);
        return this;
    }

    public void setExcludeStandardPriceingStat(String excludeStandardPriceingStat) {
        this.excludeStandardPriceingStat = excludeStandardPriceingStat;
    }

    public String getUserField1() {
        return this.userField1;
    }

    public ItemMaster userField1(String userField1) {
        this.setUserField1(userField1);
        return this;
    }

    public void setUserField1(String userField1) {
        this.userField1 = userField1;
    }

    public String getUserField2() {
        return this.userField2;
    }

    public ItemMaster userField2(String userField2) {
        this.setUserField2(userField2);
        return this;
    }

    public void setUserField2(String userField2) {
        this.userField2 = userField2;
    }

    public String getUserField3() {
        return this.userField3;
    }

    public ItemMaster userField3(String userField3) {
        this.setUserField3(userField3);
        return this;
    }

    public void setUserField3(String userField3) {
        this.userField3 = userField3;
    }

    public String getBillingMultiplier() {
        return this.billingMultiplier;
    }

    public ItemMaster billingMultiplier(String billingMultiplier) {
        this.setBillingMultiplier(billingMultiplier);
        return this;
    }

    public void setBillingMultiplier(String billingMultiplier) {
        this.billingMultiplier = billingMultiplier;
    }

    public String getClaimNote() {
        return this.claimNote;
    }

    public ItemMaster claimNote(String claimNote) {
        this.setClaimNote(claimNote);
        return this;
    }

    public void setClaimNote(String claimNote) {
        this.claimNote = claimNote;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public ItemMaster itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getItemMasterUuid() {
        return this.itemMasterUuid;
    }

    public ItemMaster itemMasterUuid(UUID itemMasterUuid) {
        this.setItemMasterUuid(itemMasterUuid);
        return this;
    }

    public void setItemMasterUuid(UUID itemMasterUuid) {
        this.itemMasterUuid = itemMasterUuid;
    }

    public String getItemNumber() {
        return this.itemNumber;
    }

    public ItemMaster itemNumber(String itemNumber) {
        this.setItemNumber(itemNumber);
        return this;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemGroupName() {
        return this.itemGroupName;
    }

    public ItemMaster itemGroupName(String itemGroupName) {
        this.setItemGroupName(itemGroupName);
        return this;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public String getItemTypeName() {
        return this.itemTypeName;
    }

    public ItemMaster itemTypeName(String itemTypeName) {
        this.setItemTypeName(itemTypeName);
        return this;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getPrimaryProcedureCodeValue() {
        return this.primaryProcedureCodeValue;
    }

    public ItemMaster primaryProcedureCodeValue(String primaryProcedureCodeValue) {
        this.setPrimaryProcedureCodeValue(primaryProcedureCodeValue);
        return this;
    }

    public void setPrimaryProcedureCodeValue(String primaryProcedureCodeValue) {
        this.primaryProcedureCodeValue = primaryProcedureCodeValue;
    }

    public String getDefaultVendorName() {
        return this.defaultVendorName;
    }

    public ItemMaster defaultVendorName(String defaultVendorName) {
        this.setDefaultVendorName(defaultVendorName);
        return this;
    }

    public void setDefaultVendorName(String defaultVendorName) {
        this.defaultVendorName = defaultVendorName;
    }

    public String getResupplyTypeStatus() {
        return this.resupplyTypeStatus;
    }

    public ItemMaster resupplyTypeStatus(String resupplyTypeStatus) {
        this.setResupplyTypeStatus(resupplyTypeStatus);
        return this;
    }

    public void setResupplyTypeStatus(String resupplyTypeStatus) {
        this.resupplyTypeStatus = resupplyTypeStatus;
    }

    public Set<ItemProcedureCodeMap> getProcedureDetails() {
        return this.procedureDetails;
    }

    public void setProcedureDetails(Set<ItemProcedureCodeMap> itemProcedureCodeMaps) {
        if (this.procedureDetails != null) {
            this.procedureDetails.forEach(i -> i.setItemMaster(null));
        }
        if (itemProcedureCodeMaps != null) {
            itemProcedureCodeMaps.forEach(i -> i.setItemMaster(this));
        }
        this.procedureDetails = itemProcedureCodeMaps;
    }

    public ItemMaster procedureDetails(Set<ItemProcedureCodeMap> itemProcedureCodeMaps) {
        this.setProcedureDetails(itemProcedureCodeMaps);
        return this;
    }

    public ItemMaster addProcedureDetails(ItemProcedureCodeMap itemProcedureCodeMap) {
        this.procedureDetails.add(itemProcedureCodeMap);
        itemProcedureCodeMap.setItemMaster(this);
        return this;
    }

    public ItemMaster removeProcedureDetails(ItemProcedureCodeMap itemProcedureCodeMap) {
        this.procedureDetails.remove(itemProcedureCodeMap);
        itemProcedureCodeMap.setItemMaster(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemMaster)) {
            return false;
        }
        return itemId != null && itemId.equals(((ItemMaster) o).itemId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemMaster{" +
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
