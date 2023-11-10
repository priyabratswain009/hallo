package com.sunknowledge.dme.rcm.pojo.common;

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
@JsonPropertyOrder({ "payerName", "payerID" })
@Generated("jsonschema2pojo")
public class Payer {

	@JsonProperty("payerName")
	private String payerName;
	@JsonProperty("payerID")
	private String payerID;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("payerName")
	public String getPayerName() {
		return payerName;
	}

	@JsonProperty("payerName")
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	@JsonProperty("payerID")
	public String getPayerID() {
		return payerID;
	}

	@JsonProperty("payerID")
	public void setPayerID(String payerID) {
		this.payerID = payerID;
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