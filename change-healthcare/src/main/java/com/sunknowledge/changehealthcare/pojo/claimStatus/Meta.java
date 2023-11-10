package com.sunknowledge.changehealthcare.pojo.claimStatus;

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
@JsonPropertyOrder({ "senderId", "traceId", "applicationMode" })
@Generated("jsonschema2pojo")
public class Meta {

	@JsonProperty("senderId")
	private String senderId;
	@JsonProperty("traceId")
	private String traceId;
	@JsonProperty("applicationMode")
	private String applicationMode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("senderId")
	public String getSenderId() {
		return senderId;
	}

	@JsonProperty("senderId")
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	@JsonProperty("traceId")
	public String getTraceId() {
		return traceId;
	}

	@JsonProperty("traceId")
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	@JsonProperty("applicationMode")
	public String getApplicationMode() {
		return applicationMode;
	}

	@JsonProperty("applicationMode")
	public void setApplicationMode(String applicationMode) {
		this.applicationMode = applicationMode;
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