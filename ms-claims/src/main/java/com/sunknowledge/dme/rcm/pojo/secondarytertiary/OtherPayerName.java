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
@JsonPropertyOrder({ "otherPayerOrganizationName", "otherPayerIdentifierTypeCode", "otherPayerIdentifier" })
@Generated("jsonschema2pojo")
public class OtherPayerName {

	@JsonProperty("otherPayerOrganizationName")
	private String otherPayerOrganizationName;
	@JsonProperty("otherPayerIdentifierTypeCode")
	private String otherPayerIdentifierTypeCode;
	@JsonProperty("otherPayerIdentifier")
	private String otherPayerIdentifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherPayerOrganizationName")
	public String getOtherPayerOrganizationName() {
		return otherPayerOrganizationName;
	}

	@JsonProperty("otherPayerOrganizationName")
	public void setOtherPayerOrganizationName(String otherPayerOrganizationName) {
		this.otherPayerOrganizationName = otherPayerOrganizationName;
	}

	@JsonProperty("otherPayerIdentifierTypeCode")
	public String getOtherPayerIdentifierTypeCode() {
		return otherPayerIdentifierTypeCode;
	}

	@JsonProperty("otherPayerIdentifierTypeCode")
	public void setOtherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
		this.otherPayerIdentifierTypeCode = otherPayerIdentifierTypeCode;
	}

	@JsonProperty("otherPayerIdentifier")
	public String getOtherPayerIdentifier() {
		return otherPayerIdentifier;
	}

	@JsonProperty("otherPayerIdentifier")
	public void setOtherPayerIdentifier(String otherPayerIdentifier) {
		this.otherPayerIdentifier = otherPayerIdentifier;
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