package com.sunknowledge.dme.rcm.pojo.common;

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
@JsonPropertyOrder({ "organizationName", "contactInformation" })
@Generated("jsonschema2pojo")
public class Submitter {

	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("contactInformation")
	private ContactInformation contactInformation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("contactInformation")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	@JsonProperty("contactInformation")
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
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