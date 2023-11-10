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
@JsonPropertyOrder({ "address", "submitterIdNumber", "nationalProviderIdentifier", "providerPlanNetworkIdNumber",
		"facilityNetworkIdNumber" })
@Generated("jsonschema2pojo")
public class InformationReceiverName {

	@JsonProperty("address")
	private Address address;
	@JsonProperty("submitterIdNumber")
	private String submitterIdNumber;
	@JsonProperty("nationalProviderIdentifier")
	private String nationalProviderIdentifier;
	@JsonProperty("providerPlanNetworkIdNumber")
	private String providerPlanNetworkIdNumber;
	@JsonProperty("facilityNetworkIdNumber")
	private String facilityNetworkIdNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("submitterIdNumber")
	public String getSubmitterIdNumber() {
		return submitterIdNumber;
	}

	@JsonProperty("submitterIdNumber")
	public void setSubmitterIdNumber(String submitterIdNumber) {
		this.submitterIdNumber = submitterIdNumber;
	}

	@JsonProperty("nationalProviderIdentifier")
	public String getNationalProviderIdentifier() {
		return nationalProviderIdentifier;
	}

	@JsonProperty("nationalProviderIdentifier")
	public void setNationalProviderIdentifier(String nationalProviderIdentifier) {
		this.nationalProviderIdentifier = nationalProviderIdentifier;
	}

	@JsonProperty("providerPlanNetworkIdNumber")
	public String getProviderPlanNetworkIdNumber() {
		return providerPlanNetworkIdNumber;
	}

	@JsonProperty("providerPlanNetworkIdNumber")
	public void setProviderPlanNetworkIdNumber(String providerPlanNetworkIdNumber) {
		this.providerPlanNetworkIdNumber = providerPlanNetworkIdNumber;
	}

	@JsonProperty("facilityNetworkIdNumber")
	public String getFacilityNetworkIdNumber() {
		return facilityNetworkIdNumber;
	}

	@JsonProperty("facilityNetworkIdNumber")
	public void setFacilityNetworkIdNumber(String facilityNetworkIdNumber) {
		this.facilityNetworkIdNumber = facilityNetworkIdNumber;
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