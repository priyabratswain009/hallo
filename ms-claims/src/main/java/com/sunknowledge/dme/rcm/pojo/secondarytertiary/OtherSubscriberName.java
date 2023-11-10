package com.sunknowledge.dme.rcm.pojo.secondarytertiary;

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
@JsonPropertyOrder({ "otherInsuredQualifier", "otherInsuredLastName", "otherInsuredFirstName", "otherInsuredMiddleName",
		"otherInsuredIdentifierTypeCode", "otherInsuredIdentifier" })
@Generated("jsonschema2pojo")
public class OtherSubscriberName {

	@JsonProperty("otherInsuredQualifier")
	private String otherInsuredQualifier;
	@JsonProperty("otherInsuredLastName")
	private String otherInsuredLastName;
	@JsonProperty("otherInsuredFirstName")
	private String otherInsuredFirstName;
	@JsonProperty("otherInsuredMiddleName")
	private String otherInsuredMiddleName;
	@JsonProperty("otherInsuredIdentifierTypeCode")
	private String otherInsuredIdentifierTypeCode;
	@JsonProperty("otherInsuredIdentifier")
	private String otherInsuredIdentifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherInsuredQualifier")
	public String getOtherInsuredQualifier() {
		return otherInsuredQualifier;
	}

	@JsonProperty("otherInsuredQualifier")
	public void setOtherInsuredQualifier(String otherInsuredQualifier) {
		this.otherInsuredQualifier = otherInsuredQualifier;
	}

	@JsonProperty("otherInsuredLastName")
	public String getOtherInsuredLastName() {
		return otherInsuredLastName;
	}

	@JsonProperty("otherInsuredLastName")
	public void setOtherInsuredLastName(String otherInsuredLastName) {
		this.otherInsuredLastName = otherInsuredLastName;
	}

	@JsonProperty("otherInsuredFirstName")
	public String getOtherInsuredFirstName() {
		return otherInsuredFirstName;
	}

	@JsonProperty("otherInsuredFirstName")
	public void setOtherInsuredFirstName(String otherInsuredFirstName) {
		this.otherInsuredFirstName = otherInsuredFirstName;
	}

	@JsonProperty("otherInsuredMiddleName")
	public String getOtherInsuredMiddleName() {
		return otherInsuredMiddleName;
	}

	@JsonProperty("otherInsuredMiddleName")
	public void setOtherInsuredMiddleName(String otherInsuredMiddleName) {
		this.otherInsuredMiddleName = otherInsuredMiddleName;
	}

	@JsonProperty("otherInsuredIdentifierTypeCode")
	public String getOtherInsuredIdentifierTypeCode() {
		return otherInsuredIdentifierTypeCode;
	}

	@JsonProperty("otherInsuredIdentifierTypeCode")
	public void setOtherInsuredIdentifierTypeCode(String otherInsuredIdentifierTypeCode) {
		this.otherInsuredIdentifierTypeCode = otherInsuredIdentifierTypeCode;
	}

	@JsonProperty("otherInsuredIdentifier")
	public String getOtherInsuredIdentifier() {
		return otherInsuredIdentifier;
	}

	@JsonProperty("otherInsuredIdentifier")
	public void setOtherInsuredIdentifier(String otherInsuredIdentifier) {
		this.otherInsuredIdentifier = otherInsuredIdentifier;
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