package com.sunknowledge.dme.rcm.domain.soelligibility;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "socialSecurityNumber" })
@Generated("jsonschema2pojo")
public class PlanInformation {

	@JsonProperty("socialSecurityNumber")
	private String socialSecurityNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("socialSecurityNumber")
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	@JsonProperty("socialSecurityNumber")
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
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
