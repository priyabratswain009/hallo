package com.sunknowledge.dme.rcm.service.dto.inventory;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deliveryItemId",
    "itemId",
    "itemNumber",
    "itemName",
    "hcpcsCode",
    "itemQuantity",
    "itemSerialNumber",
    "itemSaleType",
    "isDropship",
    "poNumber",
    "deliveryItemsUuid"
})
public class DeliveryItemData {
    @JsonProperty("deliveryItemId")
    private Long deliveryItemId;
    @JsonProperty("itemId")
    private Long itemId;
    @JsonProperty("itemNumber")
    private String itemNumber;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("hcpcsCode")
    private String hcpcsCode;
    @JsonProperty("itemQuantity")
    private Integer itemQuantity;
    @JsonProperty("itemSerialNumber")
    private String itemSerialNumber;
    @JsonProperty("itemSaleType")
    private String itemSaleType;
    @JsonProperty("isDropship")
    private String isDropship;
    @JsonProperty("poNumber")
    private String poNumber;
    @JsonProperty("deliveryItemsUuid")
    private String deliveryItemsUuid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("deliveryItemId")
    public Long getDeliveryItemId() {
        return deliveryItemId;
    }

    @JsonProperty("deliveryItemId")
    public void setDeliveryItemId(Long deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    @JsonProperty("itemId")
    public Long getItemId() {
        return itemId;
    }

    @JsonProperty("itemId")
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("itemNumber")
    public String getItemNumber() {
        return itemNumber;
    }

    @JsonProperty("itemNumber")
    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    @JsonProperty("itemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("hcpcsCode")
    public String getHcpcsCode() {
        return hcpcsCode;
    }

    @JsonProperty("hcpcsCode")
    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    @JsonProperty("itemQuantity")
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    @JsonProperty("itemQuantity")
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @JsonProperty("itemSerialNumber")
    public String getItemSerialNumber() {
        return itemSerialNumber;
    }

    @JsonProperty("itemSerialNumber")
    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber = itemSerialNumber;
    }

    @JsonProperty("itemSaleType")
    public String getItemSaleType() {
        return itemSaleType;
    }

    @JsonProperty("itemSaleType")
    public void setItemSaleType(String itemSaleType) {
        this.itemSaleType = itemSaleType;
    }

    @JsonProperty("isDropship")
    public String getIsDropship() {
        return isDropship;
    }

    @JsonProperty("isDropship")
    public void setIsDropship(String isDropship) {
        this.isDropship = isDropship;
    }

    @JsonProperty("poNumber")
    public String getPoNumber() {
        return poNumber;
    }

    @JsonProperty("poNumber")
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    @JsonProperty("deliveryItemsUuid")
    public String getDeliveryItemsUuid() {
        return deliveryItemsUuid;
    }

    @JsonProperty("deliveryItemsUuid")
    public void setDeliveryItemsUuid(String deliveryItemsUuid) {
        this.deliveryItemsUuid = deliveryItemsUuid;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
