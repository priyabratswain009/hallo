package com.sunknowledge.changehealthcare.pojo.professionalclaims;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "days", "rentalPrice", "purchasePrice", "frequencyCode" })
@Generated("jsonschema2pojo")
public class DurableMedicalEquipmentService {

	@JsonProperty("days")
	private String days;
	@JsonProperty("rentalPrice")
	private String rentalPrice;
	@JsonProperty("purchasePrice")
	private String purchasePrice;
	@JsonProperty("frequencyCode")
	private String frequencyCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("days")
	public String getDays() {
		return days;
	}

	@JsonProperty("days")
	public void setDays(String days) {
		this.days = days;
	}

	@JsonProperty("rentalPrice")
	public String getRentalPrice() {
		return rentalPrice;
	}

	@JsonProperty("rentalPrice")
	public void setRentalPrice(String rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	@JsonProperty("purchasePrice")
	public String getPurchasePrice() {
		return purchasePrice;
	}

	@JsonProperty("purchasePrice")
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@JsonProperty("frequencyCode")
	public String getFrequencyCode() {
		return frequencyCode;
	}

	@JsonProperty("frequencyCode")
	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
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