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
@JsonPropertyOrder({ "measurementReferenceIdentificationCode", "measurementQualifier", "testResults" })
@Generated("jsonschema2pojo")
public class TestResult {

	@JsonProperty("measurementReferenceIdentificationCode")
	private String measurementReferenceIdentificationCode;
	@JsonProperty("measurementQualifier")
	private String measurementQualifier;
	@JsonProperty("testResults")
	private String testResults;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("measurementReferenceIdentificationCode")
	public String getMeasurementReferenceIdentificationCode() {
		return measurementReferenceIdentificationCode;
	}

	@JsonProperty("measurementReferenceIdentificationCode")
	public void setMeasurementReferenceIdentificationCode(String measurementReferenceIdentificationCode) {
		this.measurementReferenceIdentificationCode = measurementReferenceIdentificationCode;
	}

	@JsonProperty("measurementQualifier")
	public String getMeasurementQualifier() {
		return measurementQualifier;
	}

	@JsonProperty("measurementQualifier")
	public void setMeasurementQualifier(String measurementQualifier) {
		this.measurementQualifier = measurementQualifier;
	}

	@JsonProperty("testResults")
	public String getTestResults() {
		return testResults;
	}

	@JsonProperty("testResults")
	public void setTestResults(String testResults) {
		this.testResults = testResults;
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