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
@JsonPropertyOrder({ "address1", "address2", "city", "state", "postalCode", "countryCode", "countrySubDivisionCode",
		"payerIdentificationNumber", "employerIdentificationNumber", "claimOfficeNumber", "naic", "commercialNumber",
		"locationNumber" })
@Generated("jsonschema2pojo")
public class PayerAddress {

	@JsonProperty("address1")
	private String address1;
	@JsonProperty("address2")
	private String address2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("postalCode")
	private String postalCode;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("countrySubDivisionCode")
	private String countrySubDivisionCode;
	@JsonProperty("payerIdentificationNumber")
	private String payerIdentificationNumber;
	@JsonProperty("employerIdentificationNumber")
	private String employerIdentificationNumber;
	@JsonProperty("claimOfficeNumber")
	private String claimOfficeNumber;
	@JsonProperty("naic")
	private String naic;
	@JsonProperty("commercialNumber")
	private String commercialNumber;
	@JsonProperty("locationNumber")
	private String locationNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address1")
	public String getAddress1() {
		return address1;
	}

	@JsonProperty("address1")
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@JsonProperty("address2")
	public String getAddress2() {
		return address2;
	}

	@JsonProperty("address2")
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

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("countrySubDivisionCode")
	public String getCountrySubDivisionCode() {
		return countrySubDivisionCode;
	}

	@JsonProperty("countrySubDivisionCode")
	public void setCountrySubDivisionCode(String countrySubDivisionCode) {
		this.countrySubDivisionCode = countrySubDivisionCode;
	}

	@JsonProperty("payerIdentificationNumber")
	public String getPayerIdentificationNumber() {
		return payerIdentificationNumber;
	}

	@JsonProperty("payerIdentificationNumber")
	public void setPayerIdentificationNumber(String payerIdentificationNumber) {
		this.payerIdentificationNumber = payerIdentificationNumber;
	}

	@JsonProperty("employerIdentificationNumber")
	public String getEmployerIdentificationNumber() {
		return employerIdentificationNumber;
	}

	@JsonProperty("employerIdentificationNumber")
	public void setEmployerIdentificationNumber(String employerIdentificationNumber) {
		this.employerIdentificationNumber = employerIdentificationNumber;
	}

	@JsonProperty("claimOfficeNumber")
	public String getClaimOfficeNumber() {
		return claimOfficeNumber;
	}

	@JsonProperty("claimOfficeNumber")
	public void setClaimOfficeNumber(String claimOfficeNumber) {
		this.claimOfficeNumber = claimOfficeNumber;
	}

	@JsonProperty("naic")
	public String getNaic() {
		return naic;
	}

	@JsonProperty("naic")
	public void setNaic(String naic) {
		this.naic = naic;
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}