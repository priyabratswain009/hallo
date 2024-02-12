package com.sunknowledge.dme.rcm.domain.coverage;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "payerId" })
@Generated("jsonschema2pojo")
public class Payer {

	@JsonProperty("name")
	private String name;
	@JsonProperty("payerId")
	private String payerId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("payerId")
	public String getPayerId() {
		return payerId;
	}

	@JsonProperty("payerId")
	public void setPayerId(String payerId) {
		this.payerId = payerId;
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
