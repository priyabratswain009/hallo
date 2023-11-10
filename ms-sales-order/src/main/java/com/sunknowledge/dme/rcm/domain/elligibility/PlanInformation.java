package com.sunknowledge.dme.rcm.domain.elligibility;

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
@JsonPropertyOrder({ "familyUnitNumber", "socialSecurityNumber" })
@Generated("jsonschema2pojo")
public class PlanInformation {

	@JsonProperty("familyUnitNumber")
	private String familyUnitNumber;
	@JsonProperty("socialSecurityNumber")
	private String socialSecurityNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("familyUnitNumber")
	public String getFamilyUnitNumber() {
		return familyUnitNumber;
	}

	@JsonProperty("familyUnitNumber")
	public void setFamilyUnitNumber(String familyUnitNumber) {
		this.familyUnitNumber = familyUnitNumber;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

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