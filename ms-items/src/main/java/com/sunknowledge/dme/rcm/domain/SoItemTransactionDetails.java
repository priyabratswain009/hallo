package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SoItemTransactionDetails.
 */
@Entity
@Table(name = "t_so_item_transaction_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoItemTransactionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "so_item_transaction_details_id", nullable = false)
    private Long soItemTransactionDetailsId;

    @Column(name = "sales_order_no")
    private String salesOrderNo;

    @Column(name = "sale_type")
    private String saleType;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "item_qty")
    private Long itemQty;

    @Column(name = "wheather_serialized")
    private String wheatherSerialized;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "wheather_asset_tagged")
    private String wheatherAssetTagged;

    @Column(name = "asset_no")
    private String assetNo;

    @Column(name = "original_dos")
    private LocalDate originalDos;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "status")
    private String status;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "transaction_no")
    private String transactionNo;

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

    @Column(name = "so_item_transaction_details_uuid")
    private UUID soItemTransactionDetailsUuid;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "is_dropship")
    private String isDropship;

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "item_transaction_status")
    private String itemTransactionStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSoItemTransactionDetailsId() {
        return this.soItemTransactionDetailsId;
    }

    public SoItemTransactionDetails soItemTransactionDetailsId(Long soItemTransactionDetailsId) {
        this.setSoItemTransactionDetailsId(soItemTransactionDetailsId);
        return this;
    }

    public void setSoItemTransactionDetailsId(Long soItemTransactionDetailsId) {
        this.soItemTransactionDetailsId = soItemTransactionDetailsId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public SoItemTransactionDetails salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getSaleType() {
        return this.saleType;
    }

    public SoItemTransactionDetails saleType(String saleType) {
        this.setSaleType(saleType);
        return this;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public SoItemTransactionDetails itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public SoItemTransactionDetails itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public SoItemTransactionDetails itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public SoItemTransactionDetails itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Long getItemQty() {
        return this.itemQty;
    }

    public SoItemTransactionDetails itemQty(Long itemQty) {
        this.setItemQty(itemQty);
        return this;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getWheatherSerialized() {
        return this.wheatherSerialized;
    }

    public SoItemTransactionDetails wheatherSerialized(String wheatherSerialized) {
        this.setWheatherSerialized(wheatherSerialized);
        return this;
    }

    public void setWheatherSerialized(String wheatherSerialized) {
        this.wheatherSerialized = wheatherSerialized;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public SoItemTransactionDetails serialNo(String serialNo) {
        this.setSerialNo(serialNo);
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getWheatherAssetTagged() {
        return this.wheatherAssetTagged;
    }

    public SoItemTransactionDetails wheatherAssetTagged(String wheatherAssetTagged) {
        this.setWheatherAssetTagged(wheatherAssetTagged);
        return this;
    }

    public void setWheatherAssetTagged(String wheatherAssetTagged) {
        this.wheatherAssetTagged = wheatherAssetTagged;
    }

    public String getAssetNo() {
        return this.assetNo;
    }

    public SoItemTransactionDetails assetNo(String assetNo) {
        this.setAssetNo(assetNo);
        return this;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public SoItemTransactionDetails originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public SoItemTransactionDetails branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public SoItemTransactionDetails locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public SoItemTransactionDetails locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStatus() {
        return this.status;
    }

    public SoItemTransactionDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public SoItemTransactionDetails transactionDate(LocalDate transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionNo() {
        return this.transactionNo;
    }

    public SoItemTransactionDetails transactionNo(String transactionNo) {
        this.setTransactionNo(transactionNo);
        return this;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SoItemTransactionDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SoItemTransactionDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SoItemTransactionDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SoItemTransactionDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SoItemTransactionDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getSoItemTransactionDetailsUuid() {
        return this.soItemTransactionDetailsUuid;
    }

    public SoItemTransactionDetails soItemTransactionDetailsUuid(UUID soItemTransactionDetailsUuid) {
        this.setSoItemTransactionDetailsUuid(soItemTransactionDetailsUuid);
        return this;
    }

    public void setSoItemTransactionDetailsUuid(UUID soItemTransactionDetailsUuid) {
        this.soItemTransactionDetailsUuid = soItemTransactionDetailsUuid;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SoItemTransactionDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getIsDropship() {
        return this.isDropship;
    }

    public SoItemTransactionDetails isDropship(String isDropship) {
        this.setIsDropship(isDropship);
        return this;
    }

    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    public String getPoNo() {
        return this.poNo;
    }

    public SoItemTransactionDetails poNo(String poNo) {
        this.setPoNo(poNo);
        return this;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getItemTransactionStatus() {
        return this.itemTransactionStatus;
    }

    public SoItemTransactionDetails itemTransactionStatus(String itemTransactionStatus) {
        this.setItemTransactionStatus(itemTransactionStatus);
        return this;
    }

    public void setItemTransactionStatus(String itemTransactionStatus) {
        this.itemTransactionStatus = itemTransactionStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoItemTransactionDetails)) {
            return false;
        }
        return (
            soItemTransactionDetailsId != null &&
            soItemTransactionDetailsId.equals(((SoItemTransactionDetails) o).soItemTransactionDetailsId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoItemTransactionDetails{" +
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
