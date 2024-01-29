package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A ItemInventoryStatus.
 */
@Entity
@Table(name = "t_item_inventory_status")
public class ItemInventoryStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_inventory_status_id")
    private Long itemInventoryStatusId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_location_id")
    private Long itemLocationId;

    @Column(name = "onhand_qty")
    private Long onhandQty;

    @Column(name = "onrent_qty")
    private Long onrentQty;

    @Column(name = "comitted_qty")
    private Long comittedQty;

    @Column(name = "inorder_qty")
    private Long inorderQty;

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

    @Column(name = "item_inventory_status_uuid")
    private UUID itemInventoryStatusUuid;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_location_name")
    private String itemLocationName;

    @Column(name = "bin_no")
    private String binNo;

    @Column(name = "whether_serialised")
    private String whetherSerialised;

    @Column(name = "whether_asset_tagged")
    private String whetherAssetTagged;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "on_backorder")
    private Integer onBackorder;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemInventoryStatusId() {
        return this.itemInventoryStatusId;
    }

    public ItemInventoryStatus itemInventoryStatusId(Long itemInventoryStatusId) {
        this.setItemInventoryStatusId(itemInventoryStatusId);
        return this;
    }

    public void setItemInventoryStatusId(Long itemInventoryStatusId) {
        this.itemInventoryStatusId = itemInventoryStatusId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public ItemInventoryStatus itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemLocationId() {
        return this.itemLocationId;
    }

    public ItemInventoryStatus itemLocationId(Long itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public Long getOnhandQty() {
        return this.onhandQty;
    }

    public ItemInventoryStatus onhandQty(Long onhandQty) {
        this.setOnhandQty(onhandQty);
        return this;
    }

    public void setOnhandQty(Long onhandQty) {
        this.onhandQty = onhandQty;
    }

    public Long getOnrentQty() {
        return this.onrentQty;
    }

    public ItemInventoryStatus onrentQty(Long onrentQty) {
        this.setOnrentQty(onrentQty);
        return this;
    }

    public void setOnrentQty(Long onrentQty) {
        this.onrentQty = onrentQty;
    }

    public Long getComittedQty() {
        return this.comittedQty;
    }

    public ItemInventoryStatus comittedQty(Long comittedQty) {
        this.setComittedQty(comittedQty);
        return this;
    }

    public void setComittedQty(Long comittedQty) {
        this.comittedQty = comittedQty;
    }

    public Long getInorderQty() {
        return this.inorderQty;
    }

    public ItemInventoryStatus inorderQty(Long inorderQty) {
        this.setInorderQty(inorderQty);
        return this;
    }

    public void setInorderQty(Long inorderQty) {
        this.inorderQty = inorderQty;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemInventoryStatus status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemInventoryStatus createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemInventoryStatus createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemInventoryStatus createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemInventoryStatus updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemInventoryStatus updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemInventoryStatusUuid() {
        return this.itemInventoryStatusUuid;
    }

    public ItemInventoryStatus itemInventoryStatusUuid(UUID itemInventoryStatusUuid) {
        this.setItemInventoryStatusUuid(itemInventoryStatusUuid);
        return this;
    }

    public void setItemInventoryStatusUuid(UUID itemInventoryStatusUuid) {
        this.itemInventoryStatusUuid = itemInventoryStatusUuid;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public ItemInventoryStatus itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemInventoryStatus itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public ItemInventoryStatus itemDescription(String itemDescription) {
        this.setItemDescription(itemDescription);
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemLocationName() {
        return this.itemLocationName;
    }

    public ItemInventoryStatus itemLocationName(String itemLocationName) {
        this.setItemLocationName(itemLocationName);
        return this;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public String getBinNo() {
        return this.binNo;
    }

    public ItemInventoryStatus binNo(String binNo) {
        this.setBinNo(binNo);
        return this;
    }

    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }

    public String getWhetherSerialised() {
        return this.whetherSerialised;
    }

    public ItemInventoryStatus whetherSerialised(String whetherSerialised) {
        this.setWhetherSerialised(whetherSerialised);
        return this;
    }

    public void setWhetherSerialised(String whetherSerialised) {
        this.whetherSerialised = whetherSerialised;
    }

    public String getWhetherAssetTagged() {
        return this.whetherAssetTagged;
    }

    public ItemInventoryStatus whetherAssetTagged(String whetherAssetTagged) {
        this.setWhetherAssetTagged(whetherAssetTagged);
        return this;
    }

    public void setWhetherAssetTagged(String whetherAssetTagged) {
        this.whetherAssetTagged = whetherAssetTagged;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemInventoryStatus updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getOnBackorder() {
        return this.onBackorder;
    }

    public ItemInventoryStatus onBackorder(Integer onBackorder) {
        this.setOnBackorder(onBackorder);
        return this;
    }

    public void setOnBackorder(Integer onBackorder) {
        this.onBackorder = onBackorder;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemInventoryStatus)) {
            return false;
        }
        return itemInventoryStatusId != null && itemInventoryStatusId.equals(((ItemInventoryStatus) o).itemInventoryStatusId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemInventoryStatus{" +
            "itemInventoryStatusId=" + getItemInventoryStatusId() +
            ", itemId=" + getItemId() +
            ", itemLocationId=" + getItemLocationId() +
            ", onhandQty=" + getOnhandQty() +
            ", onrentQty=" + getOnrentQty() +
            ", comittedQty=" + getComittedQty() +
            ", inorderQty=" + getInorderQty() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemInventoryStatusUuid='" + getItemInventoryStatusUuid() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", binNo='" + getBinNo() + "'" +
            ", whetherSerialised='" + getWhetherSerialised() + "'" +
            ", whetherAssetTagged='" + getWhetherAssetTagged() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", onBackorder=" + getOnBackorder() +
            "}";
    }
}
