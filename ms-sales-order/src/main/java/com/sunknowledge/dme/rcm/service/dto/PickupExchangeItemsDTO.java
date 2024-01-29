package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PickupExchangeItems} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PickupExchangeItemsDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long pickupExchangeItemId;

    private Long pickupExchangeId;

    private Long soId;

    private Long itemId;

    private String itemNo;

    private String itemName;

    private String whetherSerialized;

    private String pickupItemSerialNo;

    private String pickupItemAssetNo;

    private String replacementItemSerialNo;

    private String replacementItemAssetNo;

    private Double quantity;

    private String itemPickupExchangeType;

    private String itemPickupExchangeNote;

    private String itemPickupExchangeComment;

    private String itemPickupExchangeStatus;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID pickupExchangeItemUuid;

    public Long getPickupExchangeItemId() {
        return pickupExchangeItemId;
    }

    public void setPickupExchangeItemId(Long pickupExchangeItemId) {
        this.pickupExchangeItemId = pickupExchangeItemId;
    }

    public Long getPickupExchangeId() {
        return pickupExchangeId;
    }

    public void setPickupExchangeId(Long pickupExchangeId) {
        this.pickupExchangeId = pickupExchangeId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
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

    public String getWhetherSerialized() {
        return whetherSerialized;
    }

    public void setWhetherSerialized(String whetherSerialized) {
        this.whetherSerialized = whetherSerialized;
    }

    public String getPickupItemSerialNo() {
        return pickupItemSerialNo;
    }

    public void setPickupItemSerialNo(String pickupItemSerialNo) {
        this.pickupItemSerialNo = pickupItemSerialNo;
    }

    public String getPickupItemAssetNo() {
        return pickupItemAssetNo;
    }

    public void setPickupItemAssetNo(String pickupItemAssetNo) {
        this.pickupItemAssetNo = pickupItemAssetNo;
    }

    public String getReplacementItemSerialNo() {
        return replacementItemSerialNo;
    }

    public void setReplacementItemSerialNo(String replacementItemSerialNo) {
        this.replacementItemSerialNo = replacementItemSerialNo;
    }

    public String getReplacementItemAssetNo() {
        return replacementItemAssetNo;
    }

    public void setReplacementItemAssetNo(String replacementItemAssetNo) {
        this.replacementItemAssetNo = replacementItemAssetNo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getItemPickupExchangeType() {
        return itemPickupExchangeType;
    }

    public void setItemPickupExchangeType(String itemPickupExchangeType) {
        this.itemPickupExchangeType = itemPickupExchangeType;
    }

    public String getItemPickupExchangeNote() {
        return itemPickupExchangeNote;
    }

    public void setItemPickupExchangeNote(String itemPickupExchangeNote) {
        this.itemPickupExchangeNote = itemPickupExchangeNote;
    }

    public String getItemPickupExchangeComment() {
        return itemPickupExchangeComment;
    }

    public void setItemPickupExchangeComment(String itemPickupExchangeComment) {
        this.itemPickupExchangeComment = itemPickupExchangeComment;
    }

    public String getItemPickupExchangeStatus() {
        return itemPickupExchangeStatus;
    }

    public void setItemPickupExchangeStatus(String itemPickupExchangeStatus) {
        this.itemPickupExchangeStatus = itemPickupExchangeStatus;
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

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPickupExchangeItemUuid() {
        return pickupExchangeItemUuid;
    }

    public void setPickupExchangeItemUuid(UUID pickupExchangeItemUuid) {
        this.pickupExchangeItemUuid = pickupExchangeItemUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PickupExchangeItemsDTO)) {
            return false;
        }

        PickupExchangeItemsDTO pickupExchangeItemsDTO = (PickupExchangeItemsDTO) o;
        if (this.pickupExchangeItemId == null) {
            return false;
        }
        return Objects.equals(this.pickupExchangeItemId, pickupExchangeItemsDTO.pickupExchangeItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pickupExchangeItemId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PickupExchangeItemsDTO{" +
            "pickupExchangeItemId=" + getPickupExchangeItemId() +
            ", pickupExchangeId=" + getPickupExchangeId() +
            ", soId=" + getSoId() +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", whetherSerialized='" + getWhetherSerialized() + "'" +
            ", pickupItemSerialNo='" + getPickupItemSerialNo() + "'" +
            ", pickupItemAssetNo='" + getPickupItemAssetNo() + "'" +
            ", replacementItemSerialNo='" + getReplacementItemSerialNo() + "'" +
            ", replacementItemAssetNo='" + getReplacementItemAssetNo() + "'" +
            ", quantity=" + getQuantity() +
            ", itemPickupExchangeType='" + getItemPickupExchangeType() + "'" +
            ", itemPickupExchangeNote='" + getItemPickupExchangeNote() + "'" +
            ", itemPickupExchangeComment='" + getItemPickupExchangeComment() + "'" +
            ", itemPickupExchangeStatus='" + getItemPickupExchangeStatus() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", pickupExchangeItemUuid='" + getPickupExchangeItemUuid() + "'" +
            "}";
    }
}
