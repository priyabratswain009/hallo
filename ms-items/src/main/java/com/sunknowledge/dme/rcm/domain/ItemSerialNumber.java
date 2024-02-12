package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A ItemSerialNumber.
 */
@Entity
@Table(name = "t_item_serial_number")
public class ItemSerialNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_serial_number_id")
    private Long itemSerialNumberId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "asset_number")
    private String assetNumber;

    @Column(name = "on_hand_status")
    private String onHandStatus;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "depreciation_status")
    private String depreciationStatus;

    @Column(name = "useful_life_in_years")
    private Long usefulLifeInYears;

    @Column(name = "start_depreciation_date")
    private LocalDate startDepreciationDate;

    @Column(name = "original_cost")
    private Double originalCost;

    @Column(name = "book_value")
    private Double bookValue;

    @Column(name = "user_value_1")
    private String userValue1;

    @Column(name = "user_value_2")
    private String userValue2;

    @Column(name = "user_value_3")
    private String userValue3;

    @Column(name = "user_value_4")
    private String userValue4;

    @Column(name = "lot_batch_no")
    private String lotBatchNo;

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

    @Column(name = "item_serial_number_uuid")
    private UUID itemSerialNumberUuid;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "on_rent_status")
    private String onRentStatus;

    @Column(name = "lot_batch_date")
    private LocalDate lotBatchDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "mfg_date")
    private LocalDate mfgDate;

    @Column(name = "po_id")
    private Long poId;

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "adjustment_id")
    private Long adjustmentId;

    @Column(name = "adjustment_no")
    private String adjustmentNo;

    @Column(name = "is_dropship")
    private String isDropship;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemSerialNumberId() {
        return this.itemSerialNumberId;
    }

    public ItemSerialNumber itemSerialNumberId(Long itemSerialNumberId) {
        this.setItemSerialNumberId(itemSerialNumberId);
        return this;
    }

    public void setItemSerialNumberId(Long itemSerialNumberId) {
        this.itemSerialNumberId = itemSerialNumberId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public ItemSerialNumber itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public ItemSerialNumber locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public ItemSerialNumber serialNumber(String serialNumber) {
        this.setSerialNumber(serialNumber);
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetNumber() {
        return this.assetNumber;
    }

    public ItemSerialNumber assetNumber(String assetNumber) {
        this.setAssetNumber(assetNumber);
        return this;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getOnHandStatus() {
        return this.onHandStatus;
    }

    public ItemSerialNumber onHandStatus(String onHandStatus) {
        this.setOnHandStatus(onHandStatus);
        return this;
    }

    public void setOnHandStatus(String onHandStatus) {
        this.onHandStatus = onHandStatus;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public ItemSerialNumber purchaseDate(LocalDate purchaseDate) {
        this.setPurchaseDate(purchaseDate);
        return this;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getSaleDate() {
        return this.saleDate;
    }

    public ItemSerialNumber saleDate(LocalDate saleDate) {
        this.setSaleDate(saleDate);
        return this;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getDepreciationStatus() {
        return this.depreciationStatus;
    }

    public ItemSerialNumber depreciationStatus(String depreciationStatus) {
        this.setDepreciationStatus(depreciationStatus);
        return this;
    }

    public void setDepreciationStatus(String depreciationStatus) {
        this.depreciationStatus = depreciationStatus;
    }

    public Long getUsefulLifeInYears() {
        return this.usefulLifeInYears;
    }

    public ItemSerialNumber usefulLifeInYears(Long usefulLifeInYears) {
        this.setUsefulLifeInYears(usefulLifeInYears);
        return this;
    }

    public void setUsefulLifeInYears(Long usefulLifeInYears) {
        this.usefulLifeInYears = usefulLifeInYears;
    }

    public LocalDate getStartDepreciationDate() {
        return this.startDepreciationDate;
    }

    public ItemSerialNumber startDepreciationDate(LocalDate startDepreciationDate) {
        this.setStartDepreciationDate(startDepreciationDate);
        return this;
    }

    public void setStartDepreciationDate(LocalDate startDepreciationDate) {
        this.startDepreciationDate = startDepreciationDate;
    }

    public Double getOriginalCost() {
        return this.originalCost;
    }

    public ItemSerialNumber originalCost(Double originalCost) {
        this.setOriginalCost(originalCost);
        return this;
    }

    public void setOriginalCost(Double originalCost) {
        this.originalCost = originalCost;
    }

    public Double getBookValue() {
        return this.bookValue;
    }

    public ItemSerialNumber bookValue(Double bookValue) {
        this.setBookValue(bookValue);
        return this;
    }

    public void setBookValue(Double bookValue) {
        this.bookValue = bookValue;
    }

    public String getUserValue1() {
        return this.userValue1;
    }

    public ItemSerialNumber userValue1(String userValue1) {
        this.setUserValue1(userValue1);
        return this;
    }

    public void setUserValue1(String userValue1) {
        this.userValue1 = userValue1;
    }

    public String getUserValue2() {
        return this.userValue2;
    }

    public ItemSerialNumber userValue2(String userValue2) {
        this.setUserValue2(userValue2);
        return this;
    }

    public void setUserValue2(String userValue2) {
        this.userValue2 = userValue2;
    }

    public String getUserValue3() {
        return this.userValue3;
    }

    public ItemSerialNumber userValue3(String userValue3) {
        this.setUserValue3(userValue3);
        return this;
    }

    public void setUserValue3(String userValue3) {
        this.userValue3 = userValue3;
    }

    public String getUserValue4() {
        return this.userValue4;
    }

    public ItemSerialNumber userValue4(String userValue4) {
        this.setUserValue4(userValue4);
        return this;
    }

    public void setUserValue4(String userValue4) {
        this.userValue4 = userValue4;
    }

    public String getLotBatchNo() {
        return this.lotBatchNo;
    }

    public ItemSerialNumber lotBatchNo(String lotBatchNo) {
        this.setLotBatchNo(lotBatchNo);
        return this;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemSerialNumber status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemSerialNumber createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemSerialNumber createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemSerialNumber createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemSerialNumber updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemSerialNumber updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemSerialNumberUuid() {
        return this.itemSerialNumberUuid;
    }

    public ItemSerialNumber itemSerialNumberUuid(UUID itemSerialNumberUuid) {
        this.setItemSerialNumberUuid(itemSerialNumberUuid);
        return this;
    }

    public void setItemSerialNumberUuid(UUID itemSerialNumberUuid) {
        this.itemSerialNumberUuid = itemSerialNumberUuid;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public ItemSerialNumber itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemSerialNumber itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public ItemSerialNumber locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getOnRentStatus() {
        return this.onRentStatus;
    }

    public ItemSerialNumber onRentStatus(String onRentStatus) {
        this.setOnRentStatus(onRentStatus);
        return this;
    }

    public void setOnRentStatus(String onRentStatus) {
        this.onRentStatus = onRentStatus;
    }

    public LocalDate getLotBatchDate() {
        return this.lotBatchDate;
    }

    public ItemSerialNumber lotBatchDate(LocalDate lotBatchDate) {
        this.setLotBatchDate(lotBatchDate);
        return this;
    }

    public void setLotBatchDate(LocalDate lotBatchDate) {
        this.lotBatchDate = lotBatchDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemSerialNumber updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public ItemSerialNumber lotNo(String lotNo) {
        this.setLotNo(lotNo);
        return this;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public LocalDate getMfgDate() {
        return this.mfgDate;
    }

    public ItemSerialNumber mfgDate(LocalDate mfgDate) {
        this.setMfgDate(mfgDate);
        return this;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Long getPoId() {
        return this.poId;
    }

    public ItemSerialNumber poId(Long poId) {
        this.setPoId(poId);
        return this;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNo() {
        return this.poNo;
    }

    public ItemSerialNumber poNo(String poNo) {
        this.setPoNo(poNo);
        return this;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Long getAdjustmentId() {
        return this.adjustmentId;
    }

    public ItemSerialNumber adjustmentId(Long adjustmentId) {
        this.setAdjustmentId(adjustmentId);
        return this;
    }

    public void setAdjustmentId(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentNo() {
        return this.adjustmentNo;
    }

    public ItemSerialNumber adjustmentNo(String adjustmentNo) {
        this.setAdjustmentNo(adjustmentNo);
        return this;
    }

    public void setAdjustmentNo(String adjustmentNo) {
        this.adjustmentNo = adjustmentNo;
    }

    public String getIsDropship() {
        return this.isDropship;
    }

    public ItemSerialNumber isDropship(String isDropship) {
        this.setIsDropship(isDropship);
        return this;
    }

    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemSerialNumber)) {
            return false;
        }
        return itemSerialNumberId != null && itemSerialNumberId.equals(((ItemSerialNumber) o).itemSerialNumberId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemSerialNumber{" +
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
