package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PickupExchangeItems.
 */
@Entity
@Table(name = "t_pickup_exchange_items")
public class PickupExchangeItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "pickup_exchange_item_id", nullable = false)
    private Long pickupExchangeItemId;

    @Column(name = "pickup_exchange_id")
    private Long pickupExchangeId;

    @Column(name = "so_id")
    private Long soId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "whether_serialized")
    private String whetherSerialized;

    @Column(name = "pickup_item_serial_no")
    private String pickupItemSerialNo;

    @Column(name = "pickup_item_asset_no")
    private String pickupItemAssetNo;

    @Column(name = "replacement_item_serial_no")
    private String replacementItemSerialNo;

    @Column(name = "replacement_item_asset_no")
    private String replacementItemAssetNo;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "item_pickup_exchange_type")
    private String itemPickupExchangeType;

    @Column(name = "item_pickup_exchange_note")
    private String itemPickupExchangeNote;

    @Column(name = "item_pickup_exchange_comment")
    private String itemPickupExchangeComment;

    @Column(name = "item_pickup_exchange_status")
    private String itemPickupExchangeStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "pickup_exchange_item_uuid")
    private UUID pickupExchangeItemUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPickupExchangeItemId() {
        return this.pickupExchangeItemId;
    }

    public PickupExchangeItems pickupExchangeItemId(Long pickupExchangeItemId) {
        this.setPickupExchangeItemId(pickupExchangeItemId);
        return this;
    }

    public void setPickupExchangeItemId(Long pickupExchangeItemId) {
        this.pickupExchangeItemId = pickupExchangeItemId;
    }

    public Long getPickupExchangeId() {
        return this.pickupExchangeId;
    }

    public PickupExchangeItems pickupExchangeId(Long pickupExchangeId) {
        this.setPickupExchangeId(pickupExchangeId);
        return this;
    }

    public void setPickupExchangeId(Long pickupExchangeId) {
        this.pickupExchangeId = pickupExchangeId;
    }

    public Long getSoId() {
        return this.soId;
    }

    public PickupExchangeItems soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public PickupExchangeItems itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public PickupExchangeItems itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public PickupExchangeItems itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getWhetherSerialized() {
        return this.whetherSerialized;
    }

    public PickupExchangeItems whetherSerialized(String whetherSerialized) {
        this.setWhetherSerialized(whetherSerialized);
        return this;
    }

    public void setWhetherSerialized(String whetherSerialized) {
        this.whetherSerialized = whetherSerialized;
    }

    public String getPickupItemSerialNo() {
        return this.pickupItemSerialNo;
    }

    public PickupExchangeItems pickupItemSerialNo(String pickupItemSerialNo) {
        this.setPickupItemSerialNo(pickupItemSerialNo);
        return this;
    }

    public void setPickupItemSerialNo(String pickupItemSerialNo) {
        this.pickupItemSerialNo = pickupItemSerialNo;
    }

    public String getPickupItemAssetNo() {
        return this.pickupItemAssetNo;
    }

    public PickupExchangeItems pickupItemAssetNo(String pickupItemAssetNo) {
        this.setPickupItemAssetNo(pickupItemAssetNo);
        return this;
    }

    public void setPickupItemAssetNo(String pickupItemAssetNo) {
        this.pickupItemAssetNo = pickupItemAssetNo;
    }

    public String getReplacementItemSerialNo() {
        return this.replacementItemSerialNo;
    }

    public PickupExchangeItems replacementItemSerialNo(String replacementItemSerialNo) {
        this.setReplacementItemSerialNo(replacementItemSerialNo);
        return this;
    }

    public void setReplacementItemSerialNo(String replacementItemSerialNo) {
        this.replacementItemSerialNo = replacementItemSerialNo;
    }

    public String getReplacementItemAssetNo() {
        return this.replacementItemAssetNo;
    }

    public PickupExchangeItems replacementItemAssetNo(String replacementItemAssetNo) {
        this.setReplacementItemAssetNo(replacementItemAssetNo);
        return this;
    }

    public void setReplacementItemAssetNo(String replacementItemAssetNo) {
        this.replacementItemAssetNo = replacementItemAssetNo;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public PickupExchangeItems quantity(Long quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getItemPickupExchangeType() {
        return this.itemPickupExchangeType;
    }

    public PickupExchangeItems itemPickupExchangeType(String itemPickupExchangeType) {
        this.setItemPickupExchangeType(itemPickupExchangeType);
        return this;
    }

    public void setItemPickupExchangeType(String itemPickupExchangeType) {
        this.itemPickupExchangeType = itemPickupExchangeType;
    }

    public String getItemPickupExchangeNote() {
        return this.itemPickupExchangeNote;
    }

    public PickupExchangeItems itemPickupExchangeNote(String itemPickupExchangeNote) {
        this.setItemPickupExchangeNote(itemPickupExchangeNote);
        return this;
    }

    public void setItemPickupExchangeNote(String itemPickupExchangeNote) {
        this.itemPickupExchangeNote = itemPickupExchangeNote;
    }

    public String getItemPickupExchangeComment() {
        return this.itemPickupExchangeComment;
    }

    public PickupExchangeItems itemPickupExchangeComment(String itemPickupExchangeComment) {
        this.setItemPickupExchangeComment(itemPickupExchangeComment);
        return this;
    }

    public void setItemPickupExchangeComment(String itemPickupExchangeComment) {
        this.itemPickupExchangeComment = itemPickupExchangeComment;
    }

    public String getItemPickupExchangeStatus() {
        return this.itemPickupExchangeStatus;
    }

    public PickupExchangeItems itemPickupExchangeStatus(String itemPickupExchangeStatus) {
        this.setItemPickupExchangeStatus(itemPickupExchangeStatus);
        return this;
    }

    public void setItemPickupExchangeStatus(String itemPickupExchangeStatus) {
        this.itemPickupExchangeStatus = itemPickupExchangeStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public PickupExchangeItems status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PickupExchangeItems createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PickupExchangeItems createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PickupExchangeItems createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PickupExchangeItems updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PickupExchangeItems updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PickupExchangeItems updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPickupExchangeItemUuid() {
        return this.pickupExchangeItemUuid;
    }

    public PickupExchangeItems pickupExchangeItemUuid(UUID pickupExchangeItemUuid) {
        this.setPickupExchangeItemUuid(pickupExchangeItemUuid);
        return this;
    }

    public void setPickupExchangeItemUuid(UUID pickupExchangeItemUuid) {
        this.pickupExchangeItemUuid = pickupExchangeItemUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PickupExchangeItems)) {
            return false;
        }
        return pickupExchangeItemId != null && pickupExchangeItemId.equals(((PickupExchangeItems) o).pickupExchangeItemId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PickupExchangeItems{" +
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
