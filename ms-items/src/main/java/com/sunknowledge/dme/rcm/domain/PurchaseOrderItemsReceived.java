package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A PurchaseOrderItemsReceived.
 */
@Entity
@Table(name = "t_purchase_order_items_received")
public class PurchaseOrderItemsReceived implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "po_item_received_id")
    private Long poItemReceivedId;

    @Column(name = "po_id")
    private Long poId;

    @Column(name = "po_number")
    private String poNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_no")
    private String locationNo;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "item_qty")
    private Long itemQty;

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

    @Column(name = "lot_batch_no")
    private String lotBatchNo;

    @Column(name = "mfg_date")
    private LocalDate mfgDate;

    @Column(name = "purchase_order_items_received_uuid")
    private UUID purchaseOrderItemsReceivedUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPoItemReceivedId() {
        return this.poItemReceivedId;
    }

    public PurchaseOrderItemsReceived poItemReceivedId(Long poItemReceivedId) {
        this.setPoItemReceivedId(poItemReceivedId);
        return this;
    }

    public void setPoItemReceivedId(Long poItemReceivedId) {
        this.poItemReceivedId = poItemReceivedId;
    }

    public Long getPoId() {
        return this.poId;
    }

    public PurchaseOrderItemsReceived poId(Long poId) {
        this.setPoId(poId);
        return this;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public PurchaseOrderItemsReceived poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public PurchaseOrderItemsReceived status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public PurchaseOrderItemsReceived locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationNo() {
        return this.locationNo;
    }

    public PurchaseOrderItemsReceived locationNo(String locationNo) {
        this.setLocationNo(locationNo);
        return this;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public PurchaseOrderItemsReceived itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public PurchaseOrderItemsReceived itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public PurchaseOrderItemsReceived itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getItemName() {
        return this.itemName;
    }

    public PurchaseOrderItemsReceived itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getReceivedDate() {
        return this.receivedDate;
    }

    public PurchaseOrderItemsReceived receivedDate(LocalDate receivedDate) {
        this.setReceivedDate(receivedDate);
        return this;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Long getItemQty() {
        return this.itemQty;
    }

    public PurchaseOrderItemsReceived itemQty(Long itemQty) {
        this.setItemQty(itemQty);
        return this;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PurchaseOrderItemsReceived createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PurchaseOrderItemsReceived createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PurchaseOrderItemsReceived createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PurchaseOrderItemsReceived updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PurchaseOrderItemsReceived updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PurchaseOrderItemsReceived updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getLotBatchNo() {
        return this.lotBatchNo;
    }

    public PurchaseOrderItemsReceived lotBatchNo(String lotBatchNo) {
        this.setLotBatchNo(lotBatchNo);
        return this;
    }

    public void setLotBatchNo(String lotBatchNo) {
        this.lotBatchNo = lotBatchNo;
    }

    public LocalDate getMfgDate() {
        return this.mfgDate;
    }

    public PurchaseOrderItemsReceived mfgDate(LocalDate mfgDate) {
        this.setMfgDate(mfgDate);
        return this;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public UUID getPurchaseOrderItemsReceivedUuid() {
        return this.purchaseOrderItemsReceivedUuid;
    }

    public PurchaseOrderItemsReceived purchaseOrderItemsReceivedUuid(UUID purchaseOrderItemsReceivedUuid) {
        this.setPurchaseOrderItemsReceivedUuid(purchaseOrderItemsReceivedUuid);
        return this;
    }

    public void setPurchaseOrderItemsReceivedUuid(UUID purchaseOrderItemsReceivedUuid) {
        this.purchaseOrderItemsReceivedUuid = purchaseOrderItemsReceivedUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderItemsReceived)) {
            return false;
        }
        return poItemReceivedId != null && poItemReceivedId.equals(((PurchaseOrderItemsReceived) o).poItemReceivedId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderItemsReceived{" +
            "poItemReceivedId=" + getPoItemReceivedId() +
            ", poId=" + getPoId() +
            ", poNumber='" + getPoNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", locationId=" + getLocationId() +
            ", locationNo='" + getLocationNo() + "'" +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", itemQty=" + getItemQty() +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", lotBatchNo='" + getLotBatchNo() + "'" +
            ", mfgDate='" + getMfgDate() + "'" +
            ", purchaseOrderItemsReceivedUuid='" + getPurchaseOrderItemsReceivedUuid() + "'" +
            "}";
    }
}
