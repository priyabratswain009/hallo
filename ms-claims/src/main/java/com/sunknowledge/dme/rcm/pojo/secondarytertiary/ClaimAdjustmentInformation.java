package com.sunknowledge.dme.rcm.pojo.secondarytertiary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "adjustmentDetails", "adjustmentGroupCode" })
@Generated("jsonschema2pojo")
public class ClaimAdjustmentInformation {

	@JsonProperty("adjustmentDetails")
	private List<AdjustmentDetail> adjustmentDetails = null;
	@JsonProperty("adjustmentGroupCode")
	private String adjustmentGroupCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("adjustmentDetails")
	public List<AdjustmentDetail> getAdjustmentDetails() {
		return adjustmentDetails;
	}

	@JsonProperty("adjustmentDetails")
	public void setAdjustmentDetails(List<AdjustmentDetail> adjustmentDetails) {
		this.adjustmentDetails = adjustmentDetails;
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