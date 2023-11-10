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
@JsonPropertyOrder({ "conditionCodes", "certificationConditionIndicator", "sequenceOrder", "conditionCode" })
@Generated("jsonschema2pojo")
public class AmbulanceCertification {

	@JsonProperty("conditionCodes")
	private List<String> conditionCodes = null;
	@JsonProperty("certificationConditionIndicator")
	private String certificationConditionIndicator;
	@JsonProperty("sequenceOrder")
	private String sequenceOrder;
	@JsonProperty("conditionCode")
	private String conditionCode;
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

	@JsonProperty("certificationConditionIndicator")
	public String getCertificationConditionIndicator() {
		return certificationConditionIndicator;
	}

	@JsonProperty("certificationConditionIndicator")
	public void setCertificationConditionIndicator(String certificationConditionIndicator) {
		this.certificationConditionIndicator = certificationConditionIndicator;
	}

	@JsonProperty("sequenceOrder")
	public String getSequenceOrder() {
		return sequenceOrder;
	}

	@JsonProperty("sequenceOrder")
	public void setSequenceOrder(String sequenceOrder) {
		this.sequenceOrder = sequenceOrder;
	}

	@JsonProperty("conditionCode")
	public String getConditionCode() {
		return conditionCode;
	}

	@JsonProperty("conditionCode")
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
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