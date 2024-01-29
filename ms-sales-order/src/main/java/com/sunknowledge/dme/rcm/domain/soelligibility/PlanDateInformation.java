package com.sunknowledge.dme.rcm.domain.soelligibility;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "plan" })
@Generated("jsonschema2pojo")
public class PlanDateInformation {

	@JsonProperty("plan")
	private String plan;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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
