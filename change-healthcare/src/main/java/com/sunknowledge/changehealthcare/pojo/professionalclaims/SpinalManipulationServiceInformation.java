package com.sunknowledge.changehealthcare.pojo.professionalclaims;

import java.util.HashMap;
import java.util.Map;

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
@JsonPropertyOrder({ "patientConditionCode", "patientConditionDescription1", "patientConditionDescription2" })
@Generated("jsonschema2pojo")
public class SpinalManipulationServiceInformation {

	@JsonProperty("patientConditionCode")
	private String patientConditionCode;
	@JsonProperty("patientConditionDescription1")
	private String patientConditionDescription1;
	@JsonProperty("patientConditionDescription2")
	private String patientConditionDescription2;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("patientConditionCode")
	public String getPatientConditionCode() {
		return patientConditionCode;
	}

	@JsonProperty("patientConditionCode")
	public void setPatientConditionCode(String patientConditionCode) {
		this.patientConditionCode = patientConditionCode;
	}

	@JsonProperty("patientConditionDescription1")
	public String getPatientConditionDescription1() {
		return patientConditionDescription1;
	}

	@JsonProperty("patientConditionDescription1")
	public void setPatientConditionDescription1(String patientConditionDescription1) {
		this.patientConditionDescription1 = patientConditionDescription1;
	}

	@JsonProperty("patientConditionDescription2")
	public String getPatientConditionDescription2() {
		return patientConditionDescription2;
	}

	@JsonProperty("patientConditionDescription2")
	public void setPatientConditionDescription2(String patientConditionDescription2) {
		this.patientConditionDescription2 = patientConditionDescription2;
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