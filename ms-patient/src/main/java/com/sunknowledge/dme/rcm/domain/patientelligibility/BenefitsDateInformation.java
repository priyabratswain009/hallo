package com.sunknowledge.dme.rcm.domain.patientelligibility;

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
@JsonPropertyOrder({ "benefit", "plan" })
@Generated("jsonschema2pojo")
public class BenefitsDateInformation {

	@JsonProperty("benefit")
	private String benefit;
	@JsonProperty("plan")
	private String plan;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("benefit")
	public String getBenefit() {
		return benefit;
	}

	@JsonProperty("benefit")
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	@JsonProperty("plan")
	public String getPlan() {
		return plan;
	}

	@JsonProperty("plan")
	public void setPlan(String plan) {
		this.plan = plan;
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