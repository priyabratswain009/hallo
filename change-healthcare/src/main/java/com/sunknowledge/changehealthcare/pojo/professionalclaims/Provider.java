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
@JsonPropertyOrder({ "providerType", "npi", "employerId", "ssn", "commercialNumber", "locationNumber",
		"stateLicenseNumber", "providerUpinNumber", "taxonomyCode", "organizationName", "lastName", "firstName",
		"middleName", "address", "contactInformation" })
@Generated("jsonschema2pojo")
public class Provider {

	@JsonProperty("providerType")
	private String providerType;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("employerId")
	private String employerId;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("commercialNumber")
	private String commercialNumber;
	@JsonProperty("locationNumber")
	private String locationNumber;
	@JsonProperty("stateLicenseNumber")
	private String stateLicenseNumber;
	@JsonProperty("providerUpinNumber")
	private String providerUpinNumber;
	@JsonProperty("taxonomyCode")
	private String taxonomyCode;
	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
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

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("commercialNumber")
	public String getCommercialNumber() {
		return commercialNumber;
	}

	@JsonProperty("commercialNumber")
	public void setCommercialNumber(String commercialNumber) {
		this.commercialNumber = commercialNumber;
	}

	@JsonProperty("locationNumber")
	public String getLocationNumber() {
		return locationNumber;
	}

	@JsonProperty("locationNumber")
	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}

	@JsonProperty("stateLicenseNumber")
	public String getStateLicenseNumber() {
		return stateLicenseNumber;
	}

	@JsonProperty("stateLicenseNumber")
	public void setStateLicenseNumber(String stateLicenseNumber) {
		this.stateLicenseNumber = stateLicenseNumber;
	}

	@JsonProperty("providerUpinNumber")
	public String getProviderUpinNumber() {
		return providerUpinNumber;
	}

	@JsonProperty("providerUpinNumber")
	public void setProviderUpinNumber(String providerUpinNumber) {
		this.providerUpinNumber = providerUpinNumber;
	}

	@JsonProperty("taxonomyCode")
	public String getTaxonomyCode() {
		return taxonomyCode;
	}

	@JsonProperty("taxonomyCode")
	public void setTaxonomyCode(String taxonomyCode) {
		this.taxonomyCode = taxonomyCode;
	}

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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