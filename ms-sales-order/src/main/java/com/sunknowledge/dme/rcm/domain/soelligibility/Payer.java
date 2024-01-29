package com.sunknowledge.dme.rcm.domain.soelligibility;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "entityIdentifier", "entityType", "name", "payorIdentification" })
@Generated("jsonschema2pojo")
public class Payer {

	@JsonProperty("entityIdentifier")
	private String entityIdentifier;
	@JsonProperty("entityType")
	private String entityType;
	@JsonProperty("name")
	private String name;
	@JsonProperty("payorIdentification")
	private String payorIdentification;
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

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("payorIdentification")
	public String getPayorIdentification() {
		return payorIdentification;
	}

	@JsonProperty("payorIdentification")
	public void setPayorIdentification(String payorIdentification) {
		this.payorIdentification = payorIdentification;
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
