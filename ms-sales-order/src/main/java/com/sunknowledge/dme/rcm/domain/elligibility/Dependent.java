package com.sunknowledge.dme.rcm.domain.elligibility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "individualRelationshipCode", "eligibilityCategory", "firstName", "lastName", "dateOfBirth",
		"gender", "providerCode", "beginningCardIssueDate", "endCardIssueDate", "memberId", "beginningPlanIssueDate",
		"endPlanIssueDate", "healthCareCodeInformation", "address" })
@Generated("jsonschema2pojo")
public class Dependent {

	@JsonProperty("individualRelationshipCode")
	private String individualRelationshipCode;
	@JsonProperty("eligibilityCategory")
	private String eligibilityCategory;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("providerCode")
	private String providerCode;
	@JsonProperty("beginningCardIssueDate")
	private String beginningCardIssueDate;
	@JsonProperty("endCardIssueDate")
	private String endCardIssueDate;
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("beginningPlanIssueDate")
	private String beginningPlanIssueDate;
	@JsonProperty("endPlanIssueDate")
	private String endPlanIssueDate;
	@JsonProperty("healthCareCodeInformation")
	private List<HealthCareCodeInformation> healthCareCodeInformation = null;
	@JsonProperty("address")
	private Address address;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("individualRelationshipCode")
	public String getIndividualRelationshipCode() {
		return individualRelationshipCode;
	}

	@JsonProperty("individualRelationshipCode")
	public void setIndividualRelationshipCode(String individualRelationshipCode) {
		this.individualRelationshipCode = individualRelationshipCode;
	}

	@JsonProperty("eligibilityCategory")
	public String getEligibilityCategory() {
		return eligibilityCategory;
	}

	@JsonProperty("eligibilityCategory")
	public void setEligibilityCategory(String eligibilityCategory) {
		this.eligibilityCategory = eligibilityCategory;
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

	@JsonProperty("providerCode")
	public String getProviderCode() {
		return providerCode;
	}

	@JsonProperty("providerCode")
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	@JsonProperty("beginningCardIssueDate")
	public String getBeginningCardIssueDate() {
		return beginningCardIssueDate;
	}

	@JsonProperty("beginningCardIssueDate")
	public void setBeginningCardIssueDate(String beginningCardIssueDate) {
		this.beginningCardIssueDate = beginningCardIssueDate;
	}

	@JsonProperty("endCardIssueDate")
	public String getEndCardIssueDate() {
		return endCardIssueDate;
	}

	@JsonProperty("endCardIssueDate")
	public void setEndCardIssueDate(String endCardIssueDate) {
		this.endCardIssueDate = endCardIssueDate;
	}

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("beginningPlanIssueDate")
	public String getBeginningPlanIssueDate() {
		return beginningPlanIssueDate;
	}

	@JsonProperty("beginningPlanIssueDate")
	public void setBeginningPlanIssueDate(String beginningPlanIssueDate) {
		this.beginningPlanIssueDate = beginningPlanIssueDate;
	}

	@JsonProperty("endPlanIssueDate")
	public String getEndPlanIssueDate() {
		return endPlanIssueDate;
	}

	@JsonProperty("endPlanIssueDate")
	public void setEndPlanIssueDate(String endPlanIssueDate) {
		this.endPlanIssueDate = endPlanIssueDate;
	}

	@JsonProperty("healthCareCodeInformation")
	public List<HealthCareCodeInformation> getHealthCareCodeInformation() {
		return healthCareCodeInformation;
	}

	@JsonProperty("healthCareCodeInformation")
	public void setHealthCareCodeInformation(List<HealthCareCodeInformation> healthCareCodeInformation) {
		this.healthCareCodeInformation = healthCareCodeInformation;
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