package com.sunknowledge.dme.rcm.domain.abn;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "procCode", "chargeAmount", "reason" })
@Generated("jsonschema2pojo")
public class Equipment {

	@JsonProperty("procCode")
	private String procCode;
	@JsonProperty("chargeAmount")
	private String chargeAmount;
	@JsonProperty("reason")
	private String reason;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("procCode")
	public String getProcCode() {
		return procCode;
	}

	@JsonProperty("procCode")
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}

	@JsonProperty("chargeAmount")
	public String getChargeAmount() {
		return chargeAmount;
	}

	@JsonProperty("chargeAmount")
	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	@JsonProperty("reason")
	public String getReason() {
		return reason;
	}

	@JsonProperty("reason")
	public void setReason(String reason) {
		this.reason = reason;
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