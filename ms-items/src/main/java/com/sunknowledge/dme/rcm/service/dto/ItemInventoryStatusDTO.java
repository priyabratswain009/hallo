package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemInventoryStatus} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemInventoryStatusDTO implements Serializable {

    private Long itemInventoryStatusId;

    private Long itemId;

    private Long itemLocationId;

    private Long onhandQty;

    private Long onrentQty;

    private Long comittedQty;

    private Long inorderQty;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID itemInventoryStatusUuid;

    private String itemNo;

    private String itemName;

    private String itemDescription;

    private String itemLocationName;

    private String binNo;

    private String whetherSerialised;

    private String whetherAssetTagged;

    private LocalDate updatedDate;

    private Integer onBackorder;

    public Long getItemInventoryStatusId() {
        return itemInventoryStatusId;
    }

    public void setItemInventoryStatusId(Long itemInventoryStatusId) {
        this.itemInventoryStatusId = itemInventoryStatusId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public Long getOnhandQty() {
        return onhandQty;
    }

    public void setOnhandQty(Long onhandQty) {
        this.onhandQty = onhandQty;
    }

    public Long getOnrentQty() {
        return onrentQty;
    }

    public void setOnrentQty(Long onrentQty) {
        this.onrentQty = onrentQty;
    }

    public Long getComittedQty() {
        return comittedQty;
    }

    public void setComittedQty(Long comittedQty) {
        this.comittedQty = comittedQty;
    }

    public Long getInorderQty() {
        return inorderQty;
    }

    public void setInorderQty(Long inorderQty) {
        this.inorderQty = inorderQty;
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

    public UUID getItemInventoryStatusUuid() {
        return itemInventoryStatusUuid;
    }

    public void setItemInventoryStatusUuid(UUID itemInventoryStatusUuid) {
        this.itemInventoryStatusUuid = itemInventoryStatusUuid;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemLocationName() {
        return itemLocationName;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public String getBinNo() {
        return binNo;
    }

    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }

    public String getWhetherSerialised() {
        return whetherSerialised;
    }

    public void setWhetherSerialised(String whetherSerialised) {
        this.whetherSerialised = whetherSerialised;
    }

    public String getWhetherAssetTagged() {
        return whetherAssetTagged;
    }

    public void setWhetherAssetTagged(String whetherAssetTagged) {
        this.whetherAssetTagged = whetherAssetTagged;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getOnBackorder() {
        return onBackorder;
    }

    public void setOnBackorder(Integer onBackorder) {
        this.onBackorder = onBackorder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemInventoryStatusDTO)) {
            return false;
        }

        ItemInventoryStatusDTO itemInventoryStatusDTO = (ItemInventoryStatusDTO) o;
        if (this.itemInventoryStatusId == null) {
            return false;
        }
        return Objects.equals(this.itemInventoryStatusId, itemInventoryStatusDTO.itemInventoryStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemInventoryStatusId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemInventoryStatusDTO{" +
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
