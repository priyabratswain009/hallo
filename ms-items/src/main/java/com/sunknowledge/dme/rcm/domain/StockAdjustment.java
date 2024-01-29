package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A StockAdjustment.
 */
@Entity
@Table(name = "t_stock_adjustment")
public class StockAdjustment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "stock_adjustment_id")
    private Long stockAdjustmentId;

    @Column(name = "adjustment_date")
    private LocalDate adjustmentDate;

    @Column(name = "adjustment_no")
    private String adjustmentNo;

    @Column(name = "adjustment_type")
    private String adjustmentType;

    @Column(name = "notes")
    private String notes;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "item_qty")
    private Long itemQty;

    @Column(name = "wheather_serialized")
    private String wheatherSerialized;

    @Column(name = "wheather_asset_tagged")
    private String wheatherAssetTagged;

    @Column(name = "avg_price")
    private Double avgPrice;

    @Column(name = "adjustment_status")
    private String adjustmentStatus;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "stock_adjustment_uuid")
    private UUID stockAdjustmentUuid;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name")
    private String branchName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getStockAdjustmentId() {
        return this.stockAdjustmentId;
    }

    public StockAdjustment stockAdjustmentId(Long stockAdjustmentId) {
        this.setStockAdjustmentId(stockAdjustmentId);
        return this;
    }

    public void setStockAdjustmentId(Long stockAdjustmentId) {
        this.stockAdjustmentId = stockAdjustmentId;
    }

    public LocalDate getAdjustmentDate() {
        return this.adjustmentDate;
    }

    public StockAdjustment adjustmentDate(LocalDate adjustmentDate) {
        this.setAdjustmentDate(adjustmentDate);
        return this;
    }

    public void setAdjustmentDate(LocalDate adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getAdjustmentNo() {
        return this.adjustmentNo;
    }

    public StockAdjustment adjustmentNo(String adjustmentNo) {
        this.setAdjustmentNo(adjustmentNo);
        return this;
    }

    public void setAdjustmentNo(String adjustmentNo) {
        this.adjustmentNo = adjustmentNo;
    }

    public String getAdjustmentType() {
        return this.adjustmentType;
    }

    public StockAdjustment adjustmentType(String adjustmentType) {
        this.setAdjustmentType(adjustmentType);
        return this;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getNotes() {
        return this.notes;
    }

    public StockAdjustment notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public StockAdjustment locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public StockAdjustment locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public StockAdjustment itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public StockAdjustment itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public StockAdjustment itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public StockAdjustment itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Long getItemQty() {
        return this.itemQty;
    }

    public StockAdjustment itemQty(Long itemQty) {
        this.setItemQty(itemQty);
        return this;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getWheatherSerialized() {
        return this.wheatherSerialized;
    }

    public StockAdjustment wheatherSerialized(String wheatherSerialized) {
        this.setWheatherSerialized(wheatherSerialized);
        return this;
    }

    public void setWheatherSerialized(String wheatherSerialized) {
        this.wheatherSerialized = wheatherSerialized;
    }

    public String getWheatherAssetTagged() {
        return this.wheatherAssetTagged;
    }

    public StockAdjustment wheatherAssetTagged(String wheatherAssetTagged) {
        this.setWheatherAssetTagged(wheatherAssetTagged);
        return this;
    }

    public void setWheatherAssetTagged(String wheatherAssetTagged) {
        this.wheatherAssetTagged = wheatherAssetTagged;
    }

    public Double getAvgPrice() {
        return this.avgPrice;
    }

    public StockAdjustment avgPrice(Double avgPrice) {
        this.setAvgPrice(avgPrice);
        return this;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAdjustmentStatus() {
        return this.adjustmentStatus;
    }

    public StockAdjustment adjustmentStatus(String adjustmentStatus) {
        this.setAdjustmentStatus(adjustmentStatus);
        return this;
    }

    public void setAdjustmentStatus(String adjustmentStatus) {
        this.adjustmentStatus = adjustmentStatus;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public StockAdjustment createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public StockAdjustment createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public StockAdjustment createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public StockAdjustment updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public StockAdjustment updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getStockAdjustmentUuid() {
        return this.stockAdjustmentUuid;
    }

    public StockAdjustment stockAdjustmentUuid(UUID stockAdjustmentUuid) {
        this.setStockAdjustmentUuid(stockAdjustmentUuid);
        return this;
    }

    public void setStockAdjustmentUuid(UUID stockAdjustmentUuid) {
        this.stockAdjustmentUuid = stockAdjustmentUuid;
    }

    public String getStatus() {
        return this.status;
    }

    public StockAdjustment status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public StockAdjustment updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public StockAdjustment branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public StockAdjustment branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockAdjustment)) {
            return false;
        }
        return stockAdjustmentId != null && stockAdjustmentId.equals(((StockAdjustment) o).stockAdjustmentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockAdjustment{" +
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
