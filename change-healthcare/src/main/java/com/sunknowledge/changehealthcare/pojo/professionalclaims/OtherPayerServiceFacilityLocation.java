package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "otherPayerServiceFacilityLocationSecondaryIdentifier" })
@Generated("jsonschema2pojo")
public class OtherPayerServiceFacilityLocation {

	@JsonProperty("otherPayerServiceFacilityLocationSecondaryIdentifier")
	private List<OtherPayerServiceFacilityLocationSecondaryIdentifier> otherPayerServiceFacilityLocationSecondaryIdentifier = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherPayerServiceFacilityLocationSecondaryIdentifier")
	public List<OtherPayerServiceFacilityLocationSecondaryIdentifier> getOtherPayerServiceFacilityLocationSecondaryIdentifier() {
		return otherPayerServiceFacilityLocationSecondaryIdentifier;
	}

	@JsonProperty("otherPayerServiceFacilityLocationSecondaryIdentifier")
	public void setOtherPayerServiceFacilityLocationSecondaryIdentifier(
			List<OtherPayerServiceFacilityLocationSecondaryIdentifier> otherPayerServiceFacilityLocationSecondaryIdentifier) {
		this.otherPayerServiceFacilityLocationSecondaryIdentifier = otherPayerServiceFacilityLocationSecondaryIdentifier;
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