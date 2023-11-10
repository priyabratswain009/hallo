package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A PurchaseOrderItems.
 */
@Entity
@Table(name = "t_purchase_order_items")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseOrderItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "po_items_id")
    private Long poItemsId;

    @Column(name = "po_id")
    private Long poId;

    @Column(name = "po_number")
    private String poNumber;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "ordered_qty")
    private Long orderedQty;

    @Column(name = "received_qty")
    private Long receivedQty;

    @Column(name = "cancelled_qty")
    private Long cancelledQty;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_name")
    private String updatedName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "purchase_order_items_uuid")
    private UUID purchaseOrderItemsUuid;

    @Column(name = "po_status")
    private String poStatus;

    @Column(name = "wheather_serialised")
    private String wheatherSerialised;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "mfg_date")
    private LocalDate mfgDate;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPoItemsId() {
        return this.poItemsId;
    }

    public PurchaseOrderItems poItemsId(Long poItemsId) {
        this.setPoItemsId(poItemsId);
        return this;
    }

    public void setPoItemsId(Long poItemsId) {
        this.poItemsId = poItemsId;
    }

    public Long getPoId() {
        return this.poId;
    }

    public PurchaseOrderItems poId(Long poId) {
        this.setPoId(poId);
        return this;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public PurchaseOrderItems poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public PurchaseOrderItems itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public PurchaseOrderItems itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public PurchaseOrderItems itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public PurchaseOrderItems itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Long getOrderedQty() {
        return this.orderedQty;
    }

    public PurchaseOrderItems orderedQty(Long orderedQty) {
        this.setOrderedQty(orderedQty);
        return this;
    }

    public void setOrderedQty(Long orderedQty) {
        this.orderedQty = orderedQty;
    }

    public Long getReceivedQty() {
        return this.receivedQty;
    }

    public PurchaseOrderItems receivedQty(Long receivedQty) {
        this.setReceivedQty(receivedQty);
        return this;
    }

    public void setReceivedQty(Long receivedQty) {
        this.receivedQty = receivedQty;
    }

    public Long getCancelledQty() {
        return this.cancelledQty;
    }

    public PurchaseOrderItems cancelledQty(Long cancelledQty) {
        this.setCancelledQty(cancelledQty);
        return this;
    }

    public void setCancelledQty(Long cancelledQty) {
        this.cancelledQty = cancelledQty;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public PurchaseOrderItems unitPrice(Double unitPrice) {
        this.setUnitPrice(unitPrice);
        return this;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public PurchaseOrderItems totalAmount(Double totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public PurchaseOrderItems status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return this.notes;
    }

    public PurchaseOrderItems notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PurchaseOrderItems createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PurchaseOrderItems createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PurchaseOrderItems createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PurchaseOrderItems updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedName() {
        return this.updatedName;
    }

    public PurchaseOrderItems updatedName(String updatedName) {
        this.setUpdatedName(updatedName);
        return this;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PurchaseOrderItems updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPurchaseOrderItemsUuid() {
        return this.purchaseOrderItemsUuid;
    }

    public PurchaseOrderItems purchaseOrderItemsUuid(UUID purchaseOrderItemsUuid) {
        this.setPurchaseOrderItemsUuid(purchaseOrderItemsUuid);
        return this;
    }

    public void setPurchaseOrderItemsUuid(UUID purchaseOrderItemsUuid) {
        this.purchaseOrderItemsUuid = purchaseOrderItemsUuid;
    }

    public String getPoStatus() {
        return this.poStatus;
    }

    public PurchaseOrderItems poStatus(String poStatus) {
        this.setPoStatus(poStatus);
        return this;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public String getWheatherSerialised() {
        return this.wheatherSerialised;
    }

    public PurchaseOrderItems wheatherSerialised(String wheatherSerialised) {
        this.setWheatherSerialised(wheatherSerialised);
        return this;
    }

    public void setWheatherSerialised(String wheatherSerialised) {
        this.wheatherSerialised = wheatherSerialised;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public PurchaseOrderItems lotNo(String lotNo) {
        this.setLotNo(lotNo);
        return this;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public LocalDate getMfgDate() {
        return this.mfgDate;
    }

    public PurchaseOrderItems mfgDate(LocalDate mfgDate) {
        this.setMfgDate(mfgDate);
        return this;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public LocalDate getReceivedDate() {
        return this.receivedDate;
    }

    public PurchaseOrderItems receivedDate(LocalDate receivedDate) {
        this.setReceivedDate(receivedDate);
        return this;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderItems)) {
            return false;
        }
        return poItemsId != null && poItemsId.equals(((PurchaseOrderItems) o).poItemsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderItems{" +
            "poItemsId=" + getPoItemsId() +
            ", poId=" + getPoId() +
            ", poNumber='" + getPoNumber() + "'" +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", orderedQty=" + getOrderedQty() +
            ", receivedQty=" + getReceivedQty() +
            ", cancelledQty=" + getCancelledQty() +
            ", unitPrice=" + getUnitPrice() +
            ", totalAmount=" + getTotalAmount() +
            ", status='" + getStatus() + "'" +
            ", notes='" + getNotes() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedName='" + getUpdatedName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", purchaseOrderItemsUuid='" + getPurchaseOrderItemsUuid() + "'" +
            ", poStatus='" + getPoStatus() + "'" +
            ", wheatherSerialised='" + getWheatherSerialised() + "'" +
            ", lotNo='" + getLotNo() + "'" +
            ", mfgDate='" + getMfgDate() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            "}";
    }
}
