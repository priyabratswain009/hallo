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
@JsonPropertyOrder({ "lastName", "firstName", "middleName", "pregnancyIndicator", "dateOfBirth", "gender", "ssn",
		"memberId", "paymentResponsibilityLevelCode", "policyNumber", "relationshipToSubscriberCode",
		"contactInformation", "address" })
@Generated("jsonschema2pojo")
public class Dependents {

	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("pregnancyIndicator")
	private String pregnancyIndicator;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("paymentResponsibilityLevelCode")
	private String paymentResponsibilityLevelCode;
	@JsonProperty("policyNumber")
	private String policyNumber;
	@JsonProperty("relationshipToSubscriberCode")
	private String relationshipToSubscriberCode;
	@JsonProperty("contactInformation")
	private ContactInformation contactInformation;
	@JsonProperty("address")
	private Address address;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("pregnancyIndicator")
	public String getPregnancyIndicator() {
		return pregnancyIndicator;
	}

	@JsonProperty("pregnancyIndicator")
	public void setPregnancyIndicator(String pregnancyIndicator) {
		this.pregnancyIndicator = pregnancyIndicator;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("paymentResponsibilityLevelCode")
	public String getPaymentResponsibilityLevelCode() {
		return paymentResponsibilityLevelCode;
	}

	@JsonProperty("paymentResponsibilityLevelCode")
	public void setPaymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
		this.paymentResponsibilityLevelCode = paymentResponsibilityLevelCode;
	}

	@JsonProperty("policyNumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("policyNumber")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("relationshipToSubscriberCode")
	public String getRelationshipToSubscriberCode() {
		return relationshipToSubscriberCode;
	}

	@JsonProperty("relationshipToSubscriberCode")
	public void setRelationshipToSubscriberCode(String relationshipToSubscriberCode) {
		this.relationshipToSubscriberCode = relationshipToSubscriberCode;
	}

	@JsonProperty("contactInformation")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	@JsonProperty("contactInformation")
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
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