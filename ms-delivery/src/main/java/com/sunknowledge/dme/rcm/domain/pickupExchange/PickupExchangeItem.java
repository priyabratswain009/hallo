package com.sunknowledge.dme.rcm.domain.pickupExchange;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "pickupExchangeItemId", "pickupExchangeId", "soId", "itemId", "itemNo", "itemName",
		"whetherSerialized", "pickupItemSerialNo", "pickupItemAssetNo", "replacementItemSerialNo",
		"replacementItemAssetNo", "quantity", "itemPickupExchangeType", "itemPickupExchangeNote",
		"itemPickupExchangeComment", "itemPickupExchangeStatus", "status", "createdById", "createdByName",
		"createdDate", "updatedById", "updatedByName", "updatedDate", "pickupExchangeItemUuid" })
@Generated("jsonschema2pojo")
public class PickupExchangeItem {

	@JsonProperty("pickupExchangeItemId")
	private Long pickupExchangeItemId;
	@JsonProperty("pickupExchangeId")
	private Long pickupExchangeId;
	@JsonProperty("soId")
	private Long soId;
	@JsonProperty("itemId")
	private Long itemId;
	@JsonProperty("itemNo")
	private String itemNo;
	@JsonProperty("itemName")
	private String itemName;
	@JsonProperty("whetherSerialized")
	private String whetherSerialized;
	@JsonProperty("pickupItemSerialNo")
	private String pickupItemSerialNo;
	@JsonProperty("pickupItemAssetNo")
	private String pickupItemAssetNo;
	@JsonProperty("replacementItemSerialNo")
	private String replacementItemSerialNo;
	@JsonProperty("replacementItemAssetNo")
	private String replacementItemAssetNo;
	@JsonProperty("quantity")
	private Long quantity;
	@JsonProperty("itemPickupExchangeType")
	private String itemPickupExchangeType;
	@JsonProperty("itemPickupExchangeNote")
	private String itemPickupExchangeNote;
	@JsonProperty("itemPickupExchangeComment")
	private String itemPickupExchangeComment;
	@JsonProperty("itemPickupExchangeStatus")
	private String itemPickupExchangeStatus;
	@JsonProperty("status")
	private String status;
	@JsonProperty("createdById")
	private Long createdById;
	@JsonProperty("createdByName")
	private String createdByName;
	@JsonProperty("createdDate")
	private LocalDate createdDate;
	@JsonProperty("updatedById")
	private Long updatedById;
	@JsonProperty("updatedByName")
	private String updatedByName;
	@JsonProperty("updatedDate")
	private LocalDate updatedDate;
	@JsonProperty("pickupExchangeItemUuid")
	private UUID pickupExchangeItemUuid;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("pickupExchangeItemId")
	public Long getPickupExchangeItemId() {
		return pickupExchangeItemId;
	}

	@JsonProperty("pickupExchangeItemId")
	public void setPickupExchangeItemId(Long pickupExchangeItemId) {
		this.pickupExchangeItemId = pickupExchangeItemId;
	}

	@JsonProperty("pickupExchangeId")
	public Long getPickupExchangeId() {
		return pickupExchangeId;
	}

	@JsonProperty("pickupExchangeId")
	public void setPickupExchangeId(Long pickupExchangeId) {
		this.pickupExchangeId = pickupExchangeId;
	}

	@JsonProperty("soId")
	public Long getSoId() {
		return soId;
	}

	@JsonProperty("soId")
	public void setSoId(Long soId) {
		this.soId = soId;
	}

	@JsonProperty("itemId")
	public Long getItemId() {
		return itemId;
	}

	@JsonProperty("itemId")
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@JsonProperty("itemNo")
	public String getItemNo() {
		return itemNo;
	}

	@JsonProperty("itemNo")
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	@JsonProperty("itemName")
	public String getItemName() {
		return itemName;
	}

	@JsonProperty("itemName")
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JsonProperty("whetherSerialized")
	public String getWhetherSerialized() {
		return whetherSerialized;
	}

	@JsonProperty("whetherSerialized")
	public void setWhetherSerialized(String whetherSerialized) {
		this.whetherSerialized = whetherSerialized;
	}

	@JsonProperty("pickupItemSerialNo")
	public String getPickupItemSerialNo() {
		return pickupItemSerialNo;
	}

	@JsonProperty("pickupItemSerialNo")
	public void setPickupItemSerialNo(String pickupItemSerialNo) {
		this.pickupItemSerialNo = pickupItemSerialNo;
	}

	@JsonProperty("pickupItemAssetNo")
	public String getPickupItemAssetNo() {
		return pickupItemAssetNo;
	}

	@JsonProperty("pickupItemAssetNo")
	public void setPickupItemAssetNo(String pickupItemAssetNo) {
		this.pickupItemAssetNo = pickupItemAssetNo;
	}

	@JsonProperty("replacementItemSerialNo")
	public String getReplacementItemSerialNo() {
		return replacementItemSerialNo;
	}

	@JsonProperty("replacementItemSerialNo")
	public void setReplacementItemSerialNo(String replacementItemSerialNo) {
		this.replacementItemSerialNo = replacementItemSerialNo;
	}

	@JsonProperty("replacementItemAssetNo")
	public String getReplacementItemAssetNo() {
		return replacementItemAssetNo;
	}

	@JsonProperty("replacementItemAssetNo")
	public void setReplacementItemAssetNo(String replacementItemAssetNo) {
		this.replacementItemAssetNo = replacementItemAssetNo;
	}

	@JsonProperty("quantity")
	public Long getQuantity() {
		return quantity;
	}

	@JsonProperty("quantity")
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("itemPickupExchangeType")
	public String getItemPickupExchangeType() {
		return itemPickupExchangeType;
	}

	@JsonProperty("itemPickupExchangeType")
	public void setItemPickupExchangeType(String itemPickupExchangeType) {
		this.itemPickupExchangeType = itemPickupExchangeType;
	}

	@JsonProperty("itemPickupExchangeNote")
	public String getItemPickupExchangeNote() {
		return itemPickupExchangeNote;
	}

	@JsonProperty("itemPickupExchangeNote")
	public void setItemPickupExchangeNote(String itemPickupExchangeNote) {
		this.itemPickupExchangeNote = itemPickupExchangeNote;
	}

	@JsonProperty("itemPickupExchangeComment")
	public String getItemPickupExchangeComment() {
		return itemPickupExchangeComment;
	}

	@JsonProperty("itemPickupExchangeComment")
	public void setItemPickupExchangeComment(String itemPickupExchangeComment) {
		this.itemPickupExchangeComment = itemPickupExchangeComment;
	}

	@JsonProperty("itemPickupExchangeStatus")
	public String getItemPickupExchangeStatus() {
		return itemPickupExchangeStatus;
	}

	@JsonProperty("itemPickupExchangeStatus")
	public void setItemPickupExchangeStatus(String itemPickupExchangeStatus) {
		this.itemPickupExchangeStatus = itemPickupExchangeStatus;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("createdById")
	public Long getCreatedById() {
		return createdById;
	}

	@JsonProperty("createdById")
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	@JsonProperty("createdByName")
	public String getCreatedByName() {
		return createdByName;
	}

	@JsonProperty("createdByName")
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	@JsonProperty("createdDate")
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("createdDate")
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@JsonProperty("updatedById")
	public Long getUpdatedById() {
		return updatedById;
	}

	@JsonProperty("updatedById")
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	@JsonProperty("updatedByName")
	public String getUpdatedByName() {
		return updatedByName;
	}

	@JsonProperty("updatedByName")
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	@JsonProperty("updatedDate")
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	@JsonProperty("updatedDate")
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonProperty("pickupExchangeItemUuid")
	public UUID getPickupExchangeItemUuid() {
		return pickupExchangeItemUuid;
	}

	@JsonProperty("pickupExchangeItemUuid")
	public void setPickupExchangeItemUuid(UUID pickupExchangeItemUuid) {
		this.pickupExchangeItemUuid = pickupExchangeItemUuid;
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