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
@JsonPropertyOrder({ "adjustmentReasonCode", "adjustmentAmount", "adjustmentQuantity", "adjustmentGroupCode" })
@Generated("jsonschema2pojo")
public class Adjustmentdetail {

	@JsonProperty("adjustmentReasonCode")
	private String adjustmentReasonCode;
	@JsonProperty("adjustmentAmount")
	private String adjustmentAmount;
	@JsonProperty("adjustmentQuantity")
	private String adjustmentQuantity;
	@JsonProperty("adjustmentGroupCode")
	private String adjustmentGroupCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("adjustmentReasonCode")
	public String getAdjustmentReasonCode() {
		return adjustmentReasonCode;
	}

	@JsonProperty("adjustmentReasonCode")
	public void setAdjustmentReasonCode(String adjustmentReasonCode) {
		this.adjustmentReasonCode = adjustmentReasonCode;
	}

	@JsonProperty("adjustmentAmount")
	public String getAdjustmentAmount() {
		return adjustmentAmount;
	}

	@JsonProperty("adjustmentAmount")
	public void setAdjustmentAmount(String adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	@JsonProperty("adjustmentQuantity")
	public String getAdjustmentQuantity() {
		return adjustmentQuantity;
	}

	@JsonProperty("adjustmentQuantity")
	public void setAdjustmentQuantity(String adjustmentQuantity) {
		this.adjustmentQuantity = adjustmentQuantity;
	}

	@JsonProperty("adjustmentGroupCode")
	public String getAdjustmentGroupCode() {
		return adjustmentGroupCode;
	}

	@JsonProperty("adjustmentGroupCode")
	public void setAdjustmentGroupCode(String adjustmentGroupCode) {
		this.adjustmentGroupCode = adjustmentGroupCode;
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