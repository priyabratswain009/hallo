package com.sunknowledge.dme.rcm.domain.coverage;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "lastName", "subscriberRelationship", "subscriberRelationshipCode", "gender",
		"genderCode", "birthDate" })
@Generated("jsonschema2pojo")
public class Patient {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("subscriberRelationship")
	private String subscriberRelationship;
	@JsonProperty("subscriberRelationshipCode")
	private String subscriberRelationshipCode;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("genderCode")
	private String genderCode;
	@JsonProperty("birthDate")
	private String birthDate;
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

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@JsonProperty("birthDate")
	public String getBirthDate() {
		return birthDate;
	}

	@JsonProperty("birthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
