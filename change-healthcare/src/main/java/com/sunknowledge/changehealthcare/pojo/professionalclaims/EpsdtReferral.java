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
@JsonPropertyOrder({ "conditionCodes", "certificationConditionCodeAppliesIndicator" })
@Generated("jsonschema2pojo")
public class EpsdtReferral {

	@JsonProperty("conditionCodes")
	private List<String> conditionCodes = null;
	@JsonProperty("certificationConditionCodeAppliesIndicator")
	private String certificationConditionCodeAppliesIndicator;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("conditionCodes")
	public List<String> getConditionCodes() {
		return conditionCodes;
	}

	@JsonProperty("conditionCodes")
	public void setConditionCodes(List<String> conditionCodes) {
		this.conditionCodes = conditionCodes;
	}

	@JsonProperty("certificationConditionCodeAppliesIndicator")
	public String getCertificationConditionCodeAppliesIndicator() {
		return certificationConditionCodeAppliesIndicator;
	}

	@JsonProperty("certificationConditionCodeAppliesIndicator")
	public void setCertificationConditionCodeAppliesIndicator(String certificationConditionCodeAppliesIndicator) {
		this.certificationConditionCodeAppliesIndicator = certificationConditionCodeAppliesIndicator;
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