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
@JsonPropertyOrder({ "organizationName", "primaryIdentifier", "primaryIdentifierTypeCode", "taxIdentificationNumber",
		"secondaryIdentifier", "secondaryIdentifierTypeCode", "address" })
@Generated("jsonschema2pojo")
public class PayToPlan {

	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("primaryIdentifier")
	private String primaryIdentifier;
	@JsonProperty("primaryIdentifierTypeCode")
	private String primaryIdentifierTypeCode;
	@JsonProperty("taxIdentificationNumber")
	private String taxIdentificationNumber;
	@JsonProperty("secondaryIdentifier")
	private String secondaryIdentifier;
	@JsonProperty("secondaryIdentifierTypeCode")
	private String secondaryIdentifierTypeCode;
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

	@JsonProperty("primaryIdentifier")
	public String getPrimaryIdentifier() {
		return primaryIdentifier;
	}

	@JsonProperty("primaryIdentifier")
	public void setPrimaryIdentifier(String primaryIdentifier) {
		this.primaryIdentifier = primaryIdentifier;
	}

	@JsonProperty("primaryIdentifierTypeCode")
	public String getPrimaryIdentifierTypeCode() {
		return primaryIdentifierTypeCode;
	}

	@JsonProperty("primaryIdentifierTypeCode")
	public void setPrimaryIdentifierTypeCode(String primaryIdentifierTypeCode) {
		this.primaryIdentifierTypeCode = primaryIdentifierTypeCode;
	}

	@JsonProperty("taxIdentificationNumber")
	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	@JsonProperty("taxIdentificationNumber")
	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	@JsonProperty("secondaryIdentifier")
	public String getSecondaryIdentifier() {
		return secondaryIdentifier;
	}

	@JsonProperty("secondaryIdentifier")
	public void setSecondaryIdentifier(String secondaryIdentifier) {
		this.secondaryIdentifier = secondaryIdentifier;
	}

	@JsonProperty("secondaryIdentifierTypeCode")
	public String getSecondaryIdentifierTypeCode() {
		return secondaryIdentifierTypeCode;
	}

	@JsonProperty("secondaryIdentifierTypeCode")
	public void setSecondaryIdentifierTypeCode(String secondaryIdentifierTypeCode) {
		this.secondaryIdentifierTypeCode = secondaryIdentifierTypeCode;
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