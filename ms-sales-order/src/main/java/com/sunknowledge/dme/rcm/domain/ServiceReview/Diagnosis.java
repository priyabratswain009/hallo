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
@JsonPropertyOrder({ "qualifier", "qualifierCode", "value", "code", "date" })
@Generated("jsonschema2pojo")
public class Diagnosis {

	@JsonProperty("qualifier")
	private String qualifier;
	@JsonProperty("qualifierCode")
	private String qualifierCode;
	@JsonProperty("value")
	private String value;
	@JsonProperty("code")
	private String code;
	@JsonProperty("date")
	private String date;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("qualifier")
	public String getQualifier() {
		return qualifier;
	}

	@JsonProperty("qualifier")
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	@JsonProperty("qualifierCode")
	public String getQualifierCode() {
		return qualifierCode;
	}

	@JsonProperty("qualifierCode")
	public void setQualifierCode(String qualifierCode) {
		this.qualifierCode = qualifierCode;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
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