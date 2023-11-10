package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DeliveryItems.
 */
@Table("t_delivery_items")
public class DeliveryItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("delivery_item_id")
    private Long deliveryItemId;

    @Column("delivery_ticket_id")
    private Long deliveryTicketId;

    @Column("delivery_ticket_no")
    private String deliveryTicketNo;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("item_id")
    private Long itemId;

    @Column("item_no")
    private String itemNo;

    @Column("item_name")
    private String itemName;

    @Column("item_description")
    private String itemDescription;

    @Column("hcpcs_code")
    private String hcpcsCode;

    @Column("item_quantity")
    private Integer itemQuantity;

    @Column("item_unit")
    private String itemUnit;

    @Column("whether_item_serialized")
    private String whetherItemSerialized;

    @Column("item_serial")
    private String itemSerial;

    @Column("item_batch_lot_no")
    private String itemBatchLotNo;

    @Column("item_mfg_date")
    private LocalDate itemMfgDate;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("delivery_items_uuid")
    private UUID deliveryItemsUuid;

    @Column("charged_amount")
    private Double chargedAmount;

    @Column("allowed_amount")
    private Double allowedAmount;

    @Column("item_manufacturer_name")
    private String itemManufacturerName;

    @Column("is_dropship")
    private String isDropship;

    @Column("po_number")
    private String poNumber;

    @Column("so_sale_type")
    private String soSaleType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryItemId() {
        return this.deliveryItemId;
    }

    public DeliveryItems deliveryItemId(Long deliveryItemId) {
        this.setDeliveryItemId(deliveryItemId);
        return this;
    }

    public void setDeliveryItemId(Long deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public Long getDeliveryTicketId() {
        return this.deliveryTicketId;
    }

    public DeliveryItems deliveryTicketId(Long deliveryTicketId) {
        this.setDeliveryTicketId(deliveryTicketId);
        return this;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return this.deliveryTicketNo;
    }

    public DeliveryItems deliveryTicketNo(String deliveryTicketNo) {
        this.setDeliveryTicketNo(deliveryTicketNo);
        return this;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public DeliveryItems soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public DeliveryItems soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public DeliveryItems itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public DeliveryItems itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public DeliveryItems itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public DeliveryItems itemDescription(String itemDescription) {
        this.setItemDescription(itemDescription);
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public DeliveryItems hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Integer getItemQuantity() {
        return this.itemQuantity;
    }

    public DeliveryItems itemQuantity(Integer itemQuantity) {
        this.setItemQuantity(itemQuantity);
        return this;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemUnit() {
        return this.itemUnit;
    }

    public DeliveryItems itemUnit(String itemUnit) {
        this.setItemUnit(itemUnit);
        return this;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getWhetherItemSerialized() {
        return this.whetherItemSerialized;
    }

    public DeliveryItems whetherItemSerialized(String whetherItemSerialized) {
        this.setWhetherItemSerialized(whetherItemSerialized);
        return this;
    }

    public void setWhetherItemSerialized(String whetherItemSerialized) {
        this.whetherItemSerialized = whetherItemSerialized;
    }

    public String getItemSerial() {
        return this.itemSerial;
    }

    public DeliveryItems itemSerial(String itemSerial) {
        this.setItemSerial(itemSerial);
        return this;
    }

    public void setItemSerial(String itemSerial) {
        this.itemSerial = itemSerial;
    }

    public String getItemBatchLotNo() {
        return this.itemBatchLotNo;
    }

    public DeliveryItems itemBatchLotNo(String itemBatchLotNo) {
        this.setItemBatchLotNo(itemBatchLotNo);
        return this;
    }

    public void setItemBatchLotNo(String itemBatchLotNo) {
        this.itemBatchLotNo = itemBatchLotNo;
    }

    public LocalDate getItemMfgDate() {
        return this.itemMfgDate;
    }

    public DeliveryItems itemMfgDate(LocalDate itemMfgDate) {
        this.setItemMfgDate(itemMfgDate);
        return this;
    }

    public void setItemMfgDate(LocalDate itemMfgDate) {
        this.itemMfgDate = itemMfgDate;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryItems status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DeliveryItems createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DeliveryItems createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DeliveryItems createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DeliveryItems updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DeliveryItems updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DeliveryItems updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryItemsUuid() {
        return this.deliveryItemsUuid;
    }

    public DeliveryItems deliveryItemsUuid(UUID deliveryItemsUuid) {
        this.setDeliveryItemsUuid(deliveryItemsUuid);
        return this;
    }

    public void setDeliveryItemsUuid(UUID deliveryItemsUuid) {
        this.deliveryItemsUuid = deliveryItemsUuid;
    }

    public Double getChargedAmount() {
        return this.chargedAmount;
    }

    public DeliveryItems chargedAmount(Double chargedAmount) {
        this.setChargedAmount(chargedAmount);
        return this;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowedAmount() {
        return this.allowedAmount;
    }

    public DeliveryItems allowedAmount(Double allowedAmount) {
        this.setAllowedAmount(allowedAmount);
        return this;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public String getItemManufacturerName() {
        return this.itemManufacturerName;
    }

    public DeliveryItems itemManufacturerName(String itemManufacturerName) {
        this.setItemManufacturerName(itemManufacturerName);
        return this;
    }

    public void setItemManufacturerName(String itemManufacturerName) {
        this.itemManufacturerName = itemManufacturerName;
    }

    public String getIsDropship() {
        return this.isDropship;
    }

    public DeliveryItems isDropship(String isDropship) {
        this.setIsDropship(isDropship);
        return this;
    }

    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public DeliveryItems poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getSoSaleType() {
        return this.soSaleType;
    }

    public DeliveryItems soSaleType(String soSaleType) {
        this.setSoSaleType(soSaleType);
        return this;
    }

    public void setSoSaleType(String soSaleType) {
        this.soSaleType = soSaleType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryItems)) {
            return false;
        }
        return deliveryItemId != null && deliveryItemId.equals(((DeliveryItems) o).deliveryItemId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryItems{" +
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
