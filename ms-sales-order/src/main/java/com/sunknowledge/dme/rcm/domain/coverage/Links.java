package com.sunknowledge.dme.rcm.domain.coverage;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "self" })
@Generated("jsonschema2pojo")
public class Links {

	@JsonProperty("self")
	private Self self;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("self")
	public Self getSelf() {
		return self;
	}

	@JsonProperty("self")
	public void setSelf(Self self) {
		this.self = self;
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
