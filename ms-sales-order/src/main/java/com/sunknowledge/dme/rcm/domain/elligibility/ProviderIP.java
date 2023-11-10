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
@JsonPropertyOrder({ "organizationName", "firstName", "npi", "serviceProviderNumber", "payorID", "employersId",
		"providerCode", "providerType", "address" })
@Generated("jsonschema2pojo")
public class ProviderIP {

	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("serviceProviderNumber")
	private String serviceProviderNumber;
	@JsonProperty("payorID")
	private String payorID;
	@JsonProperty("employersId")
	private String employersId;
	@JsonProperty("providerCode")
	private String providerCode;
	@JsonProperty("providerType")
	private String providerType;
	@JsonProperty("address")
	private Address address;
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

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
	}

	@JsonProperty("serviceProviderNumber")
	public String getServiceProviderNumber() {
		return serviceProviderNumber;
	}

	@JsonProperty("serviceProviderNumber")
	public void setServiceProviderNumber(String serviceProviderNumber) {
		this.serviceProviderNumber = serviceProviderNumber;
	}

	@JsonProperty("payorID")
	public String getPayorID() {
		return payorID;
	}

	@JsonProperty("payorID")
	public void setPayorID(String payorID) {
		this.payorID = payorID;
	}

	@JsonProperty("employersId")
	public String getEmployersId() {
		return employersId;
	}

	@JsonProperty("employersId")
	public void setEmployersId(String employersId) {
		this.employersId = employersId;
	}

	@JsonProperty("providerCode")
	public String getProviderCode() {
		return providerCode;
	}

	@JsonProperty("providerCode")
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	@JsonProperty("providerType")
	public String getProviderType() {
		return providerType;
	}

	@JsonProperty("providerType")
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
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