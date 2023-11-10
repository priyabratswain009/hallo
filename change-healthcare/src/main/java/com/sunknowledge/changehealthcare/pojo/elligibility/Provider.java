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
@JsonPropertyOrder({ "organizationName", "firstName", "lastName", "npi", "serviceProviderNumber", "payorID", "taxId",
		"ssn", "pharmacyProcessorNumber", "servicesPlanID", "employersId", "providerCode", "referenceIdentification",
		"providerType", "address" })
@Generated("jsonschema2pojo")
public class Provider {

	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("serviceProviderNumber")
	private String serviceProviderNumber;
	@JsonProperty("payorID")
	private String payorID;
	@JsonProperty("taxId")
	private String taxId;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("pharmacyProcessorNumber")
	private String pharmacyProcessorNumber;
	@JsonProperty("servicesPlanID")
	private String servicesPlanID;
	@JsonProperty("employersId")
	private String employersId;
	@JsonProperty("providerCode")
	private String providerCode;
	@JsonProperty("referenceIdentification")
	private String referenceIdentification;
	@JsonProperty("providerType")
	private String providerType;
	@JsonProperty("address")
	private Address__1 address;
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

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@JsonProperty("taxId")
	public String getTaxId() {
		return taxId;
	}

	@JsonProperty("taxId")
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("pharmacyProcessorNumber")
	public String getPharmacyProcessorNumber() {
		return pharmacyProcessorNumber;
	}

	@JsonProperty("pharmacyProcessorNumber")
	public void setPharmacyProcessorNumber(String pharmacyProcessorNumber) {
		this.pharmacyProcessorNumber = pharmacyProcessorNumber;
	}

	@JsonProperty("servicesPlanID")
	public String getServicesPlanID() {
		return servicesPlanID;
	}

	@JsonProperty("servicesPlanID")
	public void setServicesPlanID(String servicesPlanID) {
		this.servicesPlanID = servicesPlanID;
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

	@JsonProperty("referenceIdentification")
	public String getReferenceIdentification() {
		return referenceIdentification;
	}

	@JsonProperty("referenceIdentification")
	public void setReferenceIdentification(String referenceIdentification) {
		this.referenceIdentification = referenceIdentification;
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
	public Address__1 getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address__1 address) {
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