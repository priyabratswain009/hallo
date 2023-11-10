package com.sunknowledge.changehealthcare.pojo.elligibility;

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
@JsonPropertyOrder({ "planNumber", "groupNumber" })
@Generated("jsonschema2pojo")
public class BenefitsAdditionalInformation {

	@JsonProperty("planNumber")
	private String planNumber;
	@JsonProperty("groupNumber")
	private String groupNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("planNumber")
	public String getPlanNumber() {
		return planNumber;
	}

	@JsonProperty("planNumber")
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	@JsonProperty("groupNumber")
	public String getGroupNumber() {
		return groupNumber;
	}

	@JsonProperty("groupNumber")
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
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