package com.sunknowledge.dme.rcm.domain.patientelligibility;

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
@JsonPropertyOrder({ "entityIdentifier", "entityType", "serviceProviderNumber" })
@Generated("jsonschema2pojo")
public class Provider {

	@JsonProperty("entityIdentifier")
	private String entityIdentifier;
	@JsonProperty("entityType")
	private String entityType;
	@JsonProperty("serviceProviderNumber")
	private String serviceProviderNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

	@JsonProperty("serviceProviderNumber")
	public String getServiceProviderNumber() {
		return serviceProviderNumber;
	}

	@JsonProperty("serviceProviderNumber")
	public void setServiceProviderNumber(String serviceProviderNumber) {
		this.serviceProviderNumber = serviceProviderNumber;
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