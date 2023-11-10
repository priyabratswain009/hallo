package com.sunknowledge.changehealthcare.pojo.elligibility;

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
@JsonPropertyOrder({ "providerName", "entityIdentifier", "entityType", "npi" })
@Generated("jsonschema2pojo")
public class Provider_ {

	@JsonProperty("providerName")
	private String providerName;
	@JsonProperty("entityIdentifier")
	private String entityIdentifier;
	@JsonProperty("entityType")
	private String entityType;
	@JsonProperty("npi")
	private String npi;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("providerName")
	public String getProviderName() {
		return providerName;
	}

	@JsonProperty("providerName")
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@JsonProperty("entityIdentifier")
	public String getEntityIdentifier() {
		return entityIdentifier;
	}

	@JsonProperty("entityIdentifier")
	public void setEntityIdentifier(String entityIdentifier) {
		this.entityIdentifier = entityIdentifier;
	}

	@JsonProperty("entityType")
	public String getEntityType() {
		return entityType;
	}

	@JsonProperty("entityType")
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
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