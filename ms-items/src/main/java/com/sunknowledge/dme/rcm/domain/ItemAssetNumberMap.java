package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A ItemAssetNumberMap.
 */
@Entity
@Table(name = "t_item_asset_number_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemAssetNumberMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_asset_number_id")
    private Long itemAssetNumberId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "asset_number")
    private String assetNumber;

    @Column(name = "on_hand_status")
    private String onHandStatus;

    @Column(name = "on_rent_status")
    private String onRentStatus;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "depreciation_ready_status")
    private String depreciationReadyStatus;

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

    @Column(name = "accumulated_depreciation")
    private Double accumulatedDepreciation;

    @Column(name = "residual_value")
    private Double residualValue;

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

    @Column(name = "lot_batch_date")
    private LocalDate lotBatchDate;

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

    @Column(name = "item_asset_number_uuid")
    private UUID itemAssetNumberUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemAssetNumberId() {
        return this.itemAssetNumberId;
    }

    public ItemAssetNumberMap itemAssetNumberId(Long itemAssetNumberId) {
        this.setItemAssetNumberId(itemAssetNumberId);
        return this;
    }

    public void setItemAssetNumberId(Long itemAssetNumberId) {
        this.itemAssetNumberId = itemAssetNumberId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public ItemAssetNumberMap itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public ItemAssetNumberMap itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemAssetNumberMap itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public ItemAssetNumberMap locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public ItemAssetNumberMap locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAssetNumber() {
        return this.assetNumber;
    }

    public ItemAssetNumberMap assetNumber(String assetNumber) {
        this.setAssetNumber(assetNumber);
        return this;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getOnHandStatus() {
        return this.onHandStatus;
    }

    public ItemAssetNumberMap onHandStatus(String onHandStatus) {
        this.setOnHandStatus(onHandStatus);
        return this;
    }

    public void setOnHandStatus(String onHandStatus) {
        this.onHandStatus = onHandStatus;
    }

    public String getOnRentStatus() {
        return this.onRentStatus;
    }

    public ItemAssetNumberMap onRentStatus(String onRentStatus) {
        this.setOnRentStatus(onRentStatus);
        return this;
    }

    public void setOnRentStatus(String onRentStatus) {
        this.onRentStatus = onRentStatus;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public ItemAssetNumberMap purchaseDate(LocalDate purchaseDate) {
        this.setPurchaseDate(purchaseDate);
        return this;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getSaleDate() {
        return this.saleDate;
    }

    public ItemAssetNumberMap saleDate(LocalDate saleDate) {
        this.setSaleDate(saleDate);
        return this;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getDepreciationReadyStatus() {
        return this.depreciationReadyStatus;
    }

    public ItemAssetNumberMap depreciationReadyStatus(String depreciationReadyStatus) {
        this.setDepreciationReadyStatus(depreciationReadyStatus);
        return this;
    }

    public void setDepreciationReadyStatus(String depreciationReadyStatus) {
        this.depreciationReadyStatus = depreciationReadyStatus;
    }

    public String getDepreciationStatus() {
        return this.depreciationStatus;
    }

    public ItemAssetNumberMap depreciationStatus(String depreciationStatus) {
        this.setDepreciationStatus(depreciationStatus);
        return this;
    }

    public void setDepreciationStatus(String depreciationStatus) {
        this.depreciationStatus = depreciationStatus;
    }

    public Long getUsefulLifeInYears() {
        return this.usefulLifeInYears;
    }

    public ItemAssetNumberMap usefulLifeInYears(Long usefulLifeInYears) {
        this.setUsefulLifeInYears(usefulLifeInYears);
        return this;
    }

    public void setUsefulLifeInYears(Long usefulLifeInYears) {
        this.usefulLifeInYears = usefulLifeInYears;
    }

    public LocalDate getStartDepreciationDate() {
        return this.startDepreciationDate;
    }

    public ItemAssetNumberMap startDepreciationDate(LocalDate startDepreciationDate) {
        this.setStartDepreciationDate(startDepreciationDate);
        return this;
    }

    public void setStartDepreciationDate(LocalDate startDepreciationDate) {
        this.startDepreciationDate = startDepreciationDate;
    }

    public Double getOriginalCost() {
        return this.originalCost;
    }

    public ItemAssetNumberMap originalCost(Double originalCost) {
        this.setOriginalCost(originalCost);
        return this;
    }

    public void setOriginalCost(Double originalCost) {
        this.originalCost = originalCost;
    }

    public Double getBookValue() {
        return this.bookValue;
    }

    public ItemAssetNumberMap bookValue(Double bookValue) {
        this.setBookValue(bookValue);
        return this;
    }

    public void setBookValue(Double bookValue) {
        this.bookValue = bookValue;
    }

    public Double getAccumulatedDepreciation() {
        return this.accumulatedDepreciation;
    }

    public ItemAssetNumberMap accumulatedDepreciation(Double accumulatedDepreciation) {
        this.setAccumulatedDepreciation(accumulatedDepreciation);
        return this;
    }

    public void setAccumulatedDepreciation(Double accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    public Double getResidualValue() {
        return this.residualValue;
    }

    public ItemAssetNumberMap residualValue(Double residualValue) {
        this.setResidualValue(residualValue);
        return this;
    }

    public void setResidualValue(Double residualValue) {
        this.residualValue = residualValue;
    }

    public String getUserValue1() {
        return this.userValue1;
    }

    public ItemAssetNumberMap userValue1(String userValue1) {
        this.setUserValue1(userValue1);
        return this;
    }

    public void setUserValue1(String userValue1) {
        this.userValue1 = userValue1;
    }

    public String getUserValue2() {
        return this.userValue2;
    }

    public ItemAssetNumberMap userValue2(String userValue2) {
        this.setUserValue2(userValue2);
        return this;
    }

    public void setUserValue2(String userValue2) {
        this.userValue2 = userValue2;
    }

    public String getUserValue3() {
        return this.userValue3;
    }

    public ItemAssetNumberMap userValue3(String userValue3) {
        this.setUserValue3(userValue3);
        return this;
    }

    public void setUserValue3(String userValue3) {
        this.userValue3 = userValue3;
    }

    public String getUserValue4() {
        return this.userValue4;
    }

    public ItemAssetNumberMap userValue4(String userValue4) {
        this.setUserValue4(userValue4);
        return this;
    }

    public void setUserValue4(String userValue4) {
        this.userValue4 = userValue4;
    }

    public String getLotBatchNo() {
        return this.lotBatchNo;
    }

    public ItemAssetNumberMap lotBatchNo(String lotBatchNo) {
        this.setLotBatchNo(lotBatchNo);
        return this;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
    }

    public LocalDate getLotBatchDate() {
        return this.lotBatchDate;
    }

    public ItemAssetNumberMap lotBatchDate(LocalDate lotBatchDate) {
        this.setLotBatchDate(lotBatchDate);
        return this;
    }

    public void setLotBatchDate(LocalDate lotBatchDate) {
        this.lotBatchDate = lotBatchDate;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemAssetNumberMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemAssetNumberMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemAssetNumberMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemAssetNumberMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemAssetNumberMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemAssetNumberMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemAssetNumberMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getItemAssetNumberUuid() {
        return this.itemAssetNumberUuid;
    }

    public ItemAssetNumberMap itemAssetNumberUuid(UUID itemAssetNumberUuid) {
        this.setItemAssetNumberUuid(itemAssetNumberUuid);
        return this;
    }

    public void setItemAssetNumberUuid(UUID itemAssetNumberUuid) {
        this.itemAssetNumberUuid = itemAssetNumberUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemAssetNumberMap)) {
            return false;
        }
        return itemAssetNumberId != null && itemAssetNumberId.equals(((ItemAssetNumberMap) o).itemAssetNumberId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemAssetNumberMap{" +
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
