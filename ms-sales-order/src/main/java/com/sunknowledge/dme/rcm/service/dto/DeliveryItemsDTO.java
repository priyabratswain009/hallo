package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryItems} entity.
 */
public class DeliveryItemsDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long deliveryItemId;

    private Long deliveryTicketId;

    private String deliveryTicketNo;

    private Long soId;

    private String soNo;

    private Long itemId;

    private String itemNo;

    private String itemName;

    private String itemDescription;

    private String hcpcsCode;

    private Integer itemQuantity;

    private String itemUnit;

    private String whetherItemSerialized;

    private String itemSerial;

    private String itemBatchLotNo;

    private LocalDate itemMfgDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID deliveryItemsUuid;

    private Double chargedAmount;

    private Double allowedAmount;

    private String itemManufacturerName;

    private String isDropship;

    private String poNumber;

    private String soSaleType;

    public Long getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(Long deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public Long getDeliveryTicketId() {
        return deliveryTicketId;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return deliveryTicketNo;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getWhetherItemSerialized() {
        return whetherItemSerialized;
    }

    public void setWhetherItemSerialized(String whetherItemSerialized) {
        this.whetherItemSerialized = whetherItemSerialized;
    }

    public String getItemSerial() {
        return itemSerial;
    }

    public void setItemSerial(String itemSerial) {
        this.itemSerial = itemSerial;
    }

    public String getItemBatchLotNo() {
        return itemBatchLotNo;
    }

    public void setItemBatchLotNo(String itemBatchLotNo) {
        this.itemBatchLotNo = itemBatchLotNo;
    }

    public LocalDate getItemMfgDate() {
        return itemMfgDate;
    }

    public void setItemMfgDate(LocalDate itemMfgDate) {
        this.itemMfgDate = itemMfgDate;
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

    public UUID getDeliveryItemsUuid() {
        return deliveryItemsUuid;
    }

    public void setDeliveryItemsUuid(UUID deliveryItemsUuid) {
        this.deliveryItemsUuid = deliveryItemsUuid;
    }

    public Double getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowedAmount() {
        return allowedAmount;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public String getItemManufacturerName() {
        return itemManufacturerName;
    }

    public void setItemManufacturerName(String itemManufacturerName) {
        this.itemManufacturerName = itemManufacturerName;
    }

    public String getIsDropship() {
        return isDropship;
    }

    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getSoSaleType() {
        return soSaleType;
    }

    public void setSoSaleType(String soSaleType) {
        this.soSaleType = soSaleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryItemsDTO)) {
            return false;
        }

        DeliveryItemsDTO deliveryItemsDTO = (DeliveryItemsDTO) o;
        if (this.deliveryItemId == null) {
            return false;
        }
        return Objects.equals(this.deliveryItemId, deliveryItemsDTO.deliveryItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryItemId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryItemsDTO{" +
            "deliveryItemId=" + getDeliveryItemId() +
            ", deliveryTicketId=" + getDeliveryTicketId() +
            ", deliveryTicketNo='" + getDeliveryTicketNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", itemQuantity=" + getItemQuantity() +
            ", itemUnit='" + getItemUnit() + "'" +
            ", whetherItemSerialized='" + getWhetherItemSerialized() + "'" +
            ", itemSerial='" + getItemSerial() + "'" +
            ", itemBatchLotNo='" + getItemBatchLotNo() + "'" +
            ", itemMfgDate='" + getItemMfgDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deliveryItemsUuid='" + getDeliveryItemsUuid() + "'" +
            ", chargedAmount=" + getChargedAmount() +
            ", allowedAmount=" + getAllowedAmount() +
            ", itemManufacturerName='" + getItemManufacturerName() + "'" +
            ", isDropship='" + getIsDropship() + "'" +
            ", poNumber='" + getPoNumber() + "'" +
            ", soSaleType='" + getSoSaleType() + "'" +
            "}";
    }
}
