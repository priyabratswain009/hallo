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
@JsonPropertyOrder({ "firstName", "middleName", "lastName", "suffix", "subscriberRelationship",
		"subscriberRelationshipCode", "birthDate", "gender", "genderCode", "addressLine1", "addressLine2", "city",
		"state", "stateCode", "zipCode", "status", "statusCode", "condition", "conditionCode", "medicareCoverage",
		"prognosis", "prognosisCode" })
@Generated("jsonschema2pojo")
public class Patient {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("subscriberRelationship")
	private String subscriberRelationship;
	@JsonProperty("subscriberRelationshipCode")
	private String subscriberRelationshipCode;
	@JsonProperty("birthDate")
	private String birthDate;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("genderCode")
	private String genderCode;
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
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("condition")
	private String condition;
	@JsonProperty("conditionCode")
	private String conditionCode;
	@JsonProperty("medicareCoverage")
	private Boolean medicareCoverage;
	@JsonProperty("prognosis")
	private String prognosis;
	@JsonProperty("prognosisCode")
	private String prognosisCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("suffix")
	public String getSuffix() {
		return suffix;
	}

	@JsonProperty("suffix")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonProperty("subscriberRelationship")
	public String getSubscriberRelationship() {
		return subscriberRelationship;
	}

	@JsonProperty("subscriberRelationship")
	public void setSubscriberRelationship(String subscriberRelationship) {
		this.subscriberRelationship = subscriberRelationship;
	}

	@JsonProperty("subscriberRelationshipCode")
	public String getSubscriberRelationshipCode() {
		return subscriberRelationshipCode;
	}

	@JsonProperty("subscriberRelationshipCode")
	public void setSubscriberRelationshipCode(String subscriberRelationshipCode) {
		this.subscriberRelationshipCode = subscriberRelationshipCode;
	}

	@JsonProperty("birthDate")
	public String getBirthDate() {
		return birthDate;
	}

	@JsonProperty("birthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("genderCode")
	public String getGenderCode() {
		return genderCode;
	}

	@JsonProperty("genderCode")
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
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

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("condition")
	public String getCondition() {
		return condition;
	}

	@JsonProperty("condition")
	public void setCondition(String condition) {
		this.condition = condition;
	}

	@JsonProperty("conditionCode")
	public String getConditionCode() {
		return conditionCode;
	}

	@JsonProperty("conditionCode")
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}

	@JsonProperty("medicareCoverage")
	public Boolean getMedicareCoverage() {
		return medicareCoverage;
	}

	@JsonProperty("medicareCoverage")
	public void setMedicareCoverage(Boolean medicareCoverage) {
		this.medicareCoverage = medicareCoverage;
	}

	@JsonProperty("prognosis")
	public String getPrognosis() {
		return prognosis;
	}

	@JsonProperty("prognosis")
	public void setPrognosis(String prognosis) {
		this.prognosis = prognosis;
	}

	@JsonProperty("prognosisCode")
	public String getPrognosisCode() {
		return prognosisCode;
	}

	@JsonProperty("prognosisCode")
	public void setPrognosisCode(String prognosisCode) {
		this.prognosisCode = prognosisCode;
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