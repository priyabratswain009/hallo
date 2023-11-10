package com.sunknowledge.dme.rcm.pojo.Resubmission;

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
@JsonPropertyOrder({ "claimControlNumber" })
@Generated("jsonschema2pojo")
public class ClaimSupplementalInformation {

	@JsonProperty("claimControlNumber")
	private String claimControlNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("claimControlNumber")
	public String getClaimControlNumber() {
		return claimControlNumber;
	}

	@JsonProperty("claimControlNumber")
	public void setClaimControlNumber(String claimControlNumber) {
		this.claimControlNumber = claimControlNumber;
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