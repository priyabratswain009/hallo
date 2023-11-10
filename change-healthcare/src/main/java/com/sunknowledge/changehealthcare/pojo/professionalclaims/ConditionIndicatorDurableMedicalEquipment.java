package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "certificationConditionIndicator", "conditionIndicator", "conditionIndicatorCode" })
@Generated("jsonschema2pojo")
public class ConditionIndicatorDurableMedicalEquipment {

	@JsonProperty("certificationConditionIndicator")
	private String certificationConditionIndicator;
	@JsonProperty("conditionIndicator")
	private String conditionIndicator;
	@JsonProperty("conditionIndicatorCode")
	private String conditionIndicatorCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("certificationConditionIndicator")
	public String getCertificationConditionIndicator() {
		return certificationConditionIndicator;
	}

	@JsonProperty("certificationConditionIndicator")
	public void setCertificationConditionIndicator(String certificationConditionIndicator) {
		this.certificationConditionIndicator = certificationConditionIndicator;
	}

	@JsonProperty("conditionIndicator")
	public String getConditionIndicator() {
		return conditionIndicator;
	}

	@JsonProperty("conditionIndicator")
	public void setConditionIndicator(String conditionIndicator) {
		this.conditionIndicator = conditionIndicator;
	}

	@JsonProperty("conditionIndicatorCode")
	public String getConditionIndicatorCode() {
		return conditionIndicatorCode;
	}

	@JsonProperty("conditionIndicatorCode")
	public void setConditionIndicatorCode(String conditionIndicatorCode) {
		this.conditionIndicatorCode = conditionIndicatorCode;
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