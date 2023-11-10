package com.sunknowledge.changehealthcare.pojo.attachmentSubmission;

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
@JsonPropertyOrder({ "providerType", "npi", "employerId", "organizationName", "address", "contactInformation" })
@Generated("jsonschema2pojo")
public class Provider {

	@JsonProperty("providerType")
	private String providerType;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("employerId")
	private String employerId;
	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("contactInformation")
	private ContactInformation contactInformation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("contactInformation")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	@JsonProperty("contactInformation")
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
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