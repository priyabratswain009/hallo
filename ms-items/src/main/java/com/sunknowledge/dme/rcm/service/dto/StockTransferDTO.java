package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.StockTransfer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StockTransferDTO implements Serializable {

    private Long stockTransferId;

    private Long sourceLoactionId;

    private String sourceLoactionName;

    private LocalDate transferDate;

    private Long destinationLocationId;

    private String destinationLocationName;

    private Long itemId;

    private String itemName;

    private String itemUom;

    private Long itemQty;

    private String itemNo;

    private String wheatherSerialized;

    private String transferStatus;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID stockTransferUuid;

    private String transferNo;

    private String serialNo;

    private LocalDate receivedDate;

    private String receivedStatus;

    private String status;

    private Long branchId;

    private String branchName;

    public Long getStockTransferId() {
        return stockTransferId;
    }

    public void setStockTransferId(Long stockTransferId) {
        this.stockTransferId = stockTransferId;
    }

    public Long getSourceLoactionId() {
        return sourceLoactionId;
    }

    public void setSourceLoactionId(Long sourceLoactionId) {
        this.sourceLoactionId = sourceLoactionId;
    }

    public String getSourceLoactionName() {
        return sourceLoactionName;
    }

    public void setSourceLoactionName(String sourceLoactionName) {
        this.sourceLoactionName = sourceLoactionName;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public Long getDestinationLocationId() {
        return destinationLocationId;
    }

    public void setDestinationLocationId(Long destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public String getDestinationLocationName() {
        return destinationLocationName;
    }

    public void setDestinationLocationName(String destinationLocationName) {
        this.destinationLocationName = destinationLocationName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Long getItemQty() {
        return itemQty;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getWheatherSerialized() {
        return wheatherSerialized;
    }

    public void setWheatherSerialized(String wheatherSerialized) {
        this.wheatherSerialized = wheatherSerialized;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
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

    public UUID getStockTransferUuid() {
        return stockTransferUuid;
    }

    public void setStockTransferUuid(UUID stockTransferUuid) {
        this.stockTransferUuid = stockTransferUuid;
    }

    public String getTransferNo() {
        return transferNo;
    }

    public void setTransferNo(String transferNo) {
        this.transferNo = transferNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(String receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockTransferDTO)) {
            return false;
        }

        StockTransferDTO stockTransferDTO = (StockTransferDTO) o;
        if (this.stockTransferId == null) {
            return false;
        }
        return Objects.equals(this.stockTransferId, stockTransferDTO.stockTransferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stockTransferId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockTransferDTO{" +
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
