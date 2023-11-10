package com.sunknowledge.changehealthcare.pojo.attachmentSubmission;

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
@JsonPropertyOrder({ "repricedClaimNumber", "claimNumber" })
@Generated("jsonschema2pojo")
public class ClaimSupplementalInformation {

	@JsonProperty("repricedClaimNumber")
	private String repricedClaimNumber;
	@JsonProperty("claimNumber")
	private String claimNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("repricedClaimNumber")
	public String getRepricedClaimNumber() {
		return repricedClaimNumber;
	}

	@JsonProperty("repricedClaimNumber")
	public void setRepricedClaimNumber(String repricedClaimNumber) {
		this.repricedClaimNumber = repricedClaimNumber;
	}

	@JsonProperty("claimNumber")
	public String getClaimNumber() {
		return claimNumber;
	}

	@JsonProperty("claimNumber")
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
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