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
@JsonPropertyOrder({ "memberId", "paymentResponsibilityLevelCode", "firstName", "lastName", "gender", "dateOfBirth",
		"ssn", "idCard", "address" })
@Generated("jsonschema2pojo")
public class Subscriber {

	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("paymentResponsibilityLevelCode")
	private String paymentResponsibilityLevelCode;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("idCard")
	private String idCard;
	@JsonProperty("address")
	private Address address;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("idCard")
	public String getIdCard() {
		return idCard;
	}

	@JsonProperty("idCard")
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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