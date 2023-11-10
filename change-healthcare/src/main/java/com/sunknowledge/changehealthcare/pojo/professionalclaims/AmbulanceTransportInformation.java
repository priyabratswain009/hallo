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
@JsonPropertyOrder({ "patientWeightInPounds", "ambulanceTransportReasonCode", "transportDistanceInMiles",
		"roundTripPurposeDescription", "stretcherPurposeDescription" })
@Generated("jsonschema2pojo")
public class AmbulanceTransportInformation {

	@JsonProperty("patientWeightInPounds")
	private String patientWeightInPounds;
	@JsonProperty("ambulanceTransportReasonCode")
	private String ambulanceTransportReasonCode;
	@JsonProperty("transportDistanceInMiles")
	private String transportDistanceInMiles;
	@JsonProperty("roundTripPurposeDescription")
	private String roundTripPurposeDescription;
	@JsonProperty("stretcherPurposeDescription")
	private String stretcherPurposeDescription;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("patientWeightInPounds")
	public String getPatientWeightInPounds() {
		return patientWeightInPounds;
	}

	@JsonProperty("patientWeightInPounds")
	public void setPatientWeightInPounds(String patientWeightInPounds) {
		this.patientWeightInPounds = patientWeightInPounds;
	}

	@JsonProperty("ambulanceTransportReasonCode")
	public String getAmbulanceTransportReasonCode() {
		return ambulanceTransportReasonCode;
	}

	@JsonProperty("ambulanceTransportReasonCode")
	public void setAmbulanceTransportReasonCode(String ambulanceTransportReasonCode) {
		this.ambulanceTransportReasonCode = ambulanceTransportReasonCode;
	}

	@JsonProperty("transportDistanceInMiles")
	public String getTransportDistanceInMiles() {
		return transportDistanceInMiles;
	}

	@JsonProperty("transportDistanceInMiles")
	public void setTransportDistanceInMiles(String transportDistanceInMiles) {
		this.transportDistanceInMiles = transportDistanceInMiles;
	}

	@JsonProperty("roundTripPurposeDescription")
	public String getRoundTripPurposeDescription() {
		return roundTripPurposeDescription;
	}

	@JsonProperty("roundTripPurposeDescription")
	public void setRoundTripPurposeDescription(String roundTripPurposeDescription) {
		this.roundTripPurposeDescription = roundTripPurposeDescription;
	}

	@JsonProperty("stretcherPurposeDescription")
	public String getStretcherPurposeDescription() {
		return stretcherPurposeDescription;
	}

	@JsonProperty("stretcherPurposeDescription")
	public void setStretcherPurposeDescription(String stretcherPurposeDescription) {
		this.stretcherPurposeDescription = stretcherPurposeDescription;
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