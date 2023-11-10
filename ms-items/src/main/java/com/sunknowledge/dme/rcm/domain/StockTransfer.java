package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A StockTransfer.
 */
@Entity
@Table(name = "t_stock_transfer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StockTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "stock_transfer_id")
    private Long stockTransferId;

    @Column(name = "source_loaction_id")
    private Long sourceLoactionId;

    @Column(name = "source_loaction_name")
    private String sourceLoactionName;

    @Column(name = "transfer_date")
    private LocalDate transferDate;

    @Column(name = "destination_location_id")
    private Long destinationLocationId;

    @Column(name = "destination_location_name")
    private String destinationLocationName;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "item_qty")
    private Long itemQty;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "wheather_serialized")
    private String wheatherSerialized;

    @Column(name = "transfer_status")
    private String transferStatus;

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

    @Column(name = "stock_transfer_uuid")
    private UUID stockTransferUuid;

    @Column(name = "transfer_no")
    private String transferNo;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "received_status")
    private String receivedStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name")
    private String branchName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getStockTransferId() {
        return this.stockTransferId;
    }

    public StockTransfer stockTransferId(Long stockTransferId) {
        this.setStockTransferId(stockTransferId);
        return this;
    }

    public void setStockTransferId(Long stockTransferId) {
        this.stockTransferId = stockTransferId;
    }

    public Long getSourceLoactionId() {
        return this.sourceLoactionId;
    }

    public StockTransfer sourceLoactionId(Long sourceLoactionId) {
        this.setSourceLoactionId(sourceLoactionId);
        return this;
    }

    public void setSourceLoactionId(Long sourceLoactionId) {
        this.sourceLoactionId = sourceLoactionId;
    }

    public String getSourceLoactionName() {
        return this.sourceLoactionName;
    }

    public StockTransfer sourceLoactionName(String sourceLoactionName) {
        this.setSourceLoactionName(sourceLoactionName);
        return this;
    }

    public void setSourceLoactionName(String sourceLoactionName) {
        this.sourceLoactionName = sourceLoactionName;
    }

    public LocalDate getTransferDate() {
        return this.transferDate;
    }

    public StockTransfer transferDate(LocalDate transferDate) {
        this.setTransferDate(transferDate);
        return this;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public Long getDestinationLocationId() {
        return this.destinationLocationId;
    }

    public StockTransfer destinationLocationId(Long destinationLocationId) {
        this.setDestinationLocationId(destinationLocationId);
        return this;
    }

    public void setDestinationLocationId(Long destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public String getDestinationLocationName() {
        return this.destinationLocationName;
    }

    public StockTransfer destinationLocationName(String destinationLocationName) {
        this.setDestinationLocationName(destinationLocationName);
        return this;
    }

    public void setDestinationLocationName(String destinationLocationName) {
        this.destinationLocationName = destinationLocationName;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public StockTransfer itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public StockTransfer itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public StockTransfer itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Long getItemQty() {
        return this.itemQty;
    }

    public StockTransfer itemQty(Long itemQty) {
        this.setItemQty(itemQty);
        return this;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public StockTransfer itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getWheatherSerialized() {
        return this.wheatherSerialized;
    }

    public StockTransfer wheatherSerialized(String wheatherSerialized) {
        this.setWheatherSerialized(wheatherSerialized);
        return this;
    }

    public void setWheatherSerialized(String wheatherSerialized) {
        this.wheatherSerialized = wheatherSerialized;
    }

    public String getTransferStatus() {
        return this.transferStatus;
    }

    public StockTransfer transferStatus(String transferStatus) {
        this.setTransferStatus(transferStatus);
        return this;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public StockTransfer createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public StockTransfer createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public StockTransfer createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public StockTransfer updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public StockTransfer updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public StockTransfer updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getStockTransferUuid() {
        return this.stockTransferUuid;
    }

    public StockTransfer stockTransferUuid(UUID stockTransferUuid) {
        this.setStockTransferUuid(stockTransferUuid);
        return this;
    }

    public void setStockTransferUuid(UUID stockTransferUuid) {
        this.stockTransferUuid = stockTransferUuid;
    }

    public String getTransferNo() {
        return this.transferNo;
    }

    public StockTransfer transferNo(String transferNo) {
        this.setTransferNo(transferNo);
        return this;
    }

    public void setTransferNo(String transferNo) {
        this.transferNo = transferNo;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public StockTransfer serialNo(String serialNo) {
        this.setSerialNo(serialNo);
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public LocalDate getReceivedDate() {
        return this.receivedDate;
    }

    public StockTransfer receivedDate(LocalDate receivedDate) {
        this.setReceivedDate(receivedDate);
        return this;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivedStatus() {
        return this.receivedStatus;
    }

    public StockTransfer receivedStatus(String receivedStatus) {
        this.setReceivedStatus(receivedStatus);
        return this;
    }

    public void setReceivedStatus(String receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public StockTransfer status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public StockTransfer branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public StockTransfer branchName(String branchName) {
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
        if (!(o instanceof StockTransfer)) {
            return false;
        }
        return stockTransferId != null && stockTransferId.equals(((StockTransfer) o).stockTransferId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockTransfer{" +
            "stockTransferId=" + getStockTransferId() +
            ", sourceLoactionId=" + getSourceLoactionId() +
            ", sourceLoactionName='" + getSourceLoactionName() + "'" +
            ", transferDate='" + getTransferDate() + "'" +
            ", destinationLocationId=" + getDestinationLocationId() +
            ", destinationLocationName='" + getDestinationLocationName() + "'" +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", itemQty=" + getItemQty() +
            ", itemNo='" + getItemNo() + "'" +
            ", wheatherSerialized='" + getWheatherSerialized() + "'" +
            ", transferStatus='" + getTransferStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", stockTransferUuid='" + getStockTransferUuid() + "'" +
            ", transferNo='" + getTransferNo() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", receivedStatus='" + getReceivedStatus() + "'" +
            ", status='" + getStatus() + "'" +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            "}";
    }
}
