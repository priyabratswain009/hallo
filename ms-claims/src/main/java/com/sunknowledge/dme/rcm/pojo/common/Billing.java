package com.sunknowledge.dme.rcm.pojo.common;

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
@JsonPropertyOrder({ "organizationName", "address", "providerType", "npi", "employerId", "taxonomyCode",
		"employerIdentificationNumber" })
@Generated("jsonschema2pojo")
public class Billing {

	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("providerType")
	private String providerType;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("employerId")
	private String employerId;
	@JsonProperty("taxonomyCode")
	private String taxonomyCode;
	@JsonProperty("employerIdentificationNumber")
	private String employerIdentificationNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("providerType")
	public String getProviderType() {
		return providerType;
	}

	@JsonProperty("providerType")
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
	}

	@JsonProperty("employerId")
	public String getEmployerId() {
		return employerId;
	}

	@JsonProperty("employerId")
	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}

	@JsonProperty("taxonomyCode")
	public String getTaxonomyCode() {
		return taxonomyCode;
	}

	@JsonProperty("taxonomyCode")
	public void setTaxonomyCode(String taxonomyCode) {
		this.taxonomyCode = taxonomyCode;
	}

	@JsonProperty("employerIdentificationNumber")
	public String getEmployerIdentificationNumber() {
		return employerIdentificationNumber;
	}

	@JsonProperty("employerIdentificationNumber")
	public void setEmployerIdentificationNumber(String employerIdentificationNumber) {
		this.employerIdentificationNumber = employerIdentificationNumber;
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