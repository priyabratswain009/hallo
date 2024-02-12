package com.sunknowledge.dme.rcm.domain.ServiceReview;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lastName", "firstName", "middleName", "suffix", "npi", "taxId", "payerAssignedProviderId",
		"submitterId", "specialty", "specialtyCode", "addressLine1", "addressLine2", "city", "state", "stateCode",
		"zipCode", "contactName", "phone", "extension", "fax", "emailAddress", "url" })
@Generated("jsonschema2pojo")
public class RequestingProvider {

	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("taxId")
	private String taxId;
	@JsonProperty("payerAssignedProviderId")
	private String payerAssignedProviderId;
	@JsonProperty("submitterId")
	private String submitterId;
	@JsonProperty("specialty")
	private String specialty;
	@JsonProperty("specialtyCode")
	private String specialtyCode;
	@JsonProperty("addressLine1")
	private String addressLine1;
	@JsonProperty("addressLine2")
	private String addressLine2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("stateCode")
	private String stateCode;
	@JsonProperty("zipCode")
	private String zipCode;
	@JsonProperty("contactName")
	private String contactName;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("extension")
	private String extension;
	@JsonProperty("fax")
	private String fax;
	@JsonProperty("emailAddress")
	private String emailAddress;
	@JsonProperty("url")
	private String url;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

	@JsonProperty("suffix")
	public String getSuffix() {
		return suffix;
	}

	@JsonProperty("suffix")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
	}

	@JsonProperty("taxId")
	public String getTaxId() {
		return taxId;
	}

	@JsonProperty("taxId")
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@JsonProperty("payerAssignedProviderId")
	public String getPayerAssignedProviderId() {
		return payerAssignedProviderId;
	}

	@JsonProperty("payerAssignedProviderId")
	public void setPayerAssignedProviderId(String payerAssignedProviderId) {
		this.payerAssignedProviderId = payerAssignedProviderId;
	}

	@JsonProperty("submitterId")
	public String getSubmitterId() {
		return submitterId;
	}

	@JsonProperty("submitterId")
	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}

	@JsonProperty("specialty")
	public String getSpecialty() {
		return specialty;
	}

	@JsonProperty("specialty")
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@JsonProperty("specialtyCode")
	public String getSpecialtyCode() {
		return specialtyCode;
	}

	@JsonProperty("specialtyCode")
	public void setSpecialtyCode(String specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	@JsonProperty("addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	@JsonProperty("addressLine1")
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@JsonProperty("addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}

	@JsonProperty("addressLine2")
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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

	@JsonProperty("stateCode")
	public String getStateCode() {
		return stateCode;
	}

	@JsonProperty("stateCode")
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@JsonProperty("zipCode")
	public String getZipCode() {
		return zipCode;
	}

	@JsonProperty("zipCode")
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonProperty("contactName")
	public String getContactName() {
		return contactName;
	}

	@JsonProperty("contactName")
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("extension")
	public String getExtension() {
		return extension;
	}

	@JsonProperty("extension")
	public void setExtension(String extension) {
		this.extension = extension;
	}

	@JsonProperty("fax")
	public String getFax() {
		return fax;
	}

	@JsonProperty("fax")
	public void setFax(String fax) {
		this.fax = fax;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
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