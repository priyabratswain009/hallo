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
@JsonPropertyOrder({ "traceTypeCode", "traceType", "referenceIdentification", "originatingCompanyIdentifier" })
@Generated("jsonschema2pojo")
public class SubscriberTraceNumber {

	@JsonProperty("traceTypeCode")
	private String traceTypeCode;
	@JsonProperty("traceType")
	private String traceType;
	@JsonProperty("referenceIdentification")
	private String referenceIdentification;
	@JsonProperty("originatingCompanyIdentifier")
	private String originatingCompanyIdentifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("traceTypeCode")
	public String getTraceTypeCode() {
		return traceTypeCode;
	}

	@JsonProperty("traceTypeCode")
	public void setTraceTypeCode(String traceTypeCode) {
		this.traceTypeCode = traceTypeCode;
	}

	@JsonProperty("traceType")
	public String getTraceType() {
		return traceType;
	}

	@JsonProperty("traceType")
	public void setTraceType(String traceType) {
		this.traceType = traceType;
	}

	@JsonProperty("referenceIdentification")
	public String getReferenceIdentification() {
		return referenceIdentification;
	}

	@JsonProperty("referenceIdentification")
	public void setReferenceIdentification(String referenceIdentification) {
		this.referenceIdentification = referenceIdentification;
	}

	@JsonProperty("originatingCompanyIdentifier")
	public String getOriginatingCompanyIdentifier() {
		return originatingCompanyIdentifier;
	}

	@JsonProperty("originatingCompanyIdentifier")
	public void setOriginatingCompanyIdentifier(String originatingCompanyIdentifier) {
		this.originatingCompanyIdentifier = originatingCompanyIdentifier;
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