package com.sunknowledge.dme.rcm.dto.nppes;

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
	"endpointType",
	"endpointTypeDescription",
	"endpoint",
	"endpointDescription",
	"affiliation",
	"affiliationName",
	"use",
	"useDescription",
	"useOtherDescription",
	"contentType",
	"contentTypeDescription",
	"contentOtherDescription",
	"address_type",
	"address_1",
	"address_2",
	"city",
	"state",
	"country_code",
	"postal_code",
	"country_name"
})
@Generated("jsonschema2pojo")
public class Endpoint {
	@JsonProperty("endpointType")
	private String endpointType;
	@JsonProperty("endpointTypeDescription")
	private String endpointTypeDescription;
	@JsonProperty("endpoint")
	private String endpoint;
	@JsonProperty("endpointDescription")
	private String endpointDescription;
	@JsonProperty("affiliation")
	private String affiliation;
	@JsonProperty("affiliationName")
	private String affiliationName;
	@JsonProperty("use")
	private String use;
	@JsonProperty("useDescription")
	private String useDescription;
	@JsonProperty("useOtherDescription")
	private String useOtherDescription;
	@JsonProperty("contentType")
	private String contentType;
	@JsonProperty("contentTypeDescription")
	private String contentTypeDescription;
	@JsonProperty("contentOtherDescription")
	private String contentOtherDescription;
	@JsonProperty("address_type")
	private String addressType;
	@JsonProperty("address_1")
	private String address1;
	@JsonProperty("address_2")
	private String address2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country_code")
	private String countryCode;
	@JsonProperty("postal_code")
	private String postalCode;
	@JsonProperty("country_name")
	private String countryName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("endpointType")
	public String getEndpointType() {
		return endpointType;
	}

	@JsonProperty("endpointType")
	public void setEndpointType(String endpointType) {
		this.endpointType = endpointType;
	}

	@JsonProperty("endpointTypeDescription")
	public String getEndpointTypeDescription() {
		return endpointTypeDescription;
	}

	@JsonProperty("endpointTypeDescription")
	public void setEndpointTypeDescription(String endpointTypeDescription) {
		this.endpointTypeDescription = endpointTypeDescription;
	}

	@JsonProperty("endpoint")
	public String getEndpoint() {
		return endpoint;
	}

	@JsonProperty("endpoint")
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	@JsonProperty("endpointDescription")
	public String getEndpointDescription() {
		return endpointDescription;
	}

	@JsonProperty("endpointDescription")
	public void setEndpointDescription(String endpointDescription) {
		this.endpointDescription = endpointDescription;
	}

	@JsonProperty("affiliation")
	public String getAffiliation() {
		return affiliation;
	}

	@JsonProperty("affiliation")
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@JsonProperty("affiliationName")
	public String getAffiliationName() {
		return affiliationName;
	}

	@JsonProperty("affiliationName")
	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	@JsonProperty("use")
	public String getUse() {
		return use;
	}

	@JsonProperty("use")
	public void setUse(String use) {
		this.use = use;
	}

	@JsonProperty("useDescription")
	public String getUseDescription() {
		return useDescription;
	}

	@JsonProperty("useDescription")
	public void setUseDescription(String useDescription) {
		this.useDescription = useDescription;
	}

	@JsonProperty("useOtherDescription")
	public String getUseOtherDescription() {
		return useOtherDescription;
	}

	@JsonProperty("useOtherDescription")
	public void setUseOtherDescription(String useOtherDescription) {
		this.useOtherDescription = useOtherDescription;
	}

	@JsonProperty("contentType")
	public String getContentType() {
		return contentType;
	}

	@JsonProperty("contentType")
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@JsonProperty("contentTypeDescription")
	public String getContentTypeDescription() {
		return contentTypeDescription;
	}

	@JsonProperty("contentTypeDescription")
	public void setContentTypeDescription(String contentTypeDescription) {
		this.contentTypeDescription = contentTypeDescription;
	}

	@JsonProperty("contentOtherDescription")
	public String getContentOtherDescription() {
		return contentOtherDescription;
	}

	@JsonProperty("contentOtherDescription")
	public void setContentOtherDescription(String contentOtherDescription) {
		this.contentOtherDescription = contentOtherDescription;
	}

	@JsonProperty("address_type")
	public String getAddressType() {
		return addressType;
	}

	@JsonProperty("address_type")
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@JsonProperty("address_1")
	public String getAddress1() {
		return address1;
	}

	@JsonProperty("address_1")
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@JsonProperty("address_2")
	public String getAddress2() {
		return address2;
	}

	@JsonProperty("address_2")
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("country_code")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("country_code")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postal_code")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty("country_name")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("country_name")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
