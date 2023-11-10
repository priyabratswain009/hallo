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
@JsonPropertyOrder({
	"correlationId",
	"submitterId",
	"customerClaimNumber",
	"patientControlNumber",
	"timeOfResponse",
	"claimType",
	"formatVersion"
})
@Generated("jsonschema2pojo")
public class ClaimReferenceValidation {
	@JsonProperty("correlationId")
	private String correlationId;
	@JsonProperty("submitterId")
	private String submitterId;
	@JsonProperty("customerClaimNumber")
	private String customerClaimNumber;
	@JsonProperty("patientControlNumber")
	private String patientControlNumber;
	@JsonProperty("timeOfResponse")
	private String timeOfResponse;
	@JsonProperty("claimType")
	private String claimType;
	@JsonProperty("formatVersion")
	private String formatVersion;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("correlationId")
	public String getCorrelationId() {
		return correlationId;
	}

	@JsonProperty("correlationId")
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	@JsonProperty("submitterId")
	public String getSubmitterId() {
		return submitterId;
	}

	@JsonProperty("submitterId")
	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}

	@JsonProperty("customerClaimNumber")
	public String getCustomerClaimNumber() {
		return customerClaimNumber;
	}

	@JsonProperty("customerClaimNumber")
	public void setCustomerClaimNumber(String customerClaimNumber) {
		this.customerClaimNumber = customerClaimNumber;
	}

	@JsonProperty("patientControlNumber")
	public String getPatientControlNumber() {
		return patientControlNumber;
	}

	@JsonProperty("patientControlNumber")
	public void setPatientControlNumber(String patientControlNumber) {
		this.patientControlNumber = patientControlNumber;
	}

	@JsonProperty("timeOfResponse")
	public String getTimeOfResponse() {
		return timeOfResponse;
	}

	@JsonProperty("timeOfResponse")
	public void setTimeOfResponse(String timeOfResponse) {
		this.timeOfResponse = timeOfResponse;
	}

	@JsonProperty("claimType")
	public String getClaimType() {
		return claimType;
	}

	@JsonProperty("claimType")
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	@JsonProperty("formatVersion")
	public String getFormatVersion() {
		return formatVersion;
	}

	@JsonProperty("formatVersion")
	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
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
