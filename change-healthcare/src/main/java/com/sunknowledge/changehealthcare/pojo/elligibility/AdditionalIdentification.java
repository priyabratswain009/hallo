package com.sunknowledge.changehealthcare.pojo.elligibility;

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
@JsonPropertyOrder({ "identificationCardSerialNumber", "insurancePolicyNumber", "planNetworkIdentificationNumber",
		"agencyClaimNumber" })
@Generated("jsonschema2pojo")
public class AdditionalIdentification {

	@JsonProperty("identificationCardSerialNumber")
	private String identificationCardSerialNumber;
	@JsonProperty("insurancePolicyNumber")
	private String insurancePolicyNumber;
	@JsonProperty("planNetworkIdentificationNumber")
	private String planNetworkIdentificationNumber;
	@JsonProperty("agencyClaimNumber")
	private String agencyClaimNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("identificationCardSerialNumber")
	public String getIdentificationCardSerialNumber() {
		return identificationCardSerialNumber;
	}

	@JsonProperty("identificationCardSerialNumber")
	public void setIdentificationCardSerialNumber(String identificationCardSerialNumber) {
		this.identificationCardSerialNumber = identificationCardSerialNumber;
	}

	@JsonProperty("insurancePolicyNumber")
	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	@JsonProperty("insurancePolicyNumber")
	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	@JsonProperty("planNetworkIdentificationNumber")
	public String getPlanNetworkIdentificationNumber() {
		return planNetworkIdentificationNumber;
	}

	@JsonProperty("planNetworkIdentificationNumber")
	public void setPlanNetworkIdentificationNumber(String planNetworkIdentificationNumber) {
		this.planNetworkIdentificationNumber = planNetworkIdentificationNumber;
	}

	@JsonProperty("agencyClaimNumber")
	public String getAgencyClaimNumber() {
		return agencyClaimNumber;
	}

	@JsonProperty("agencyClaimNumber")
	public void setAgencyClaimNumber(String agencyClaimNumber) {
		this.agencyClaimNumber = agencyClaimNumber;
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