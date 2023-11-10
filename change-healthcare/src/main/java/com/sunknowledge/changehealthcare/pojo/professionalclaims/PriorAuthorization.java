package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "priorAuthorizationOrReferralNumber", "otherPayerPrimaryIdentifier" })
@Generated("jsonschema2pojo")
public class PriorAuthorization {

	@JsonProperty("priorAuthorizationOrReferralNumber")
	private String priorAuthorizationOrReferralNumber;
	@JsonProperty("otherPayerPrimaryIdentifier")
	private String otherPayerPrimaryIdentifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("priorAuthorizationOrReferralNumber")
	public String getPriorAuthorizationOrReferralNumber() {
		return priorAuthorizationOrReferralNumber;
	}

	@JsonProperty("priorAuthorizationOrReferralNumber")
	public void setPriorAuthorizationOrReferralNumber(String priorAuthorizationOrReferralNumber) {
		this.priorAuthorizationOrReferralNumber = priorAuthorizationOrReferralNumber;
	}

	@JsonProperty("otherPayerPrimaryIdentifier")
	public String getOtherPayerPrimaryIdentifier() {
		return otherPayerPrimaryIdentifier;
	}

	@JsonProperty("otherPayerPrimaryIdentifier")
	public void setOtherPayerPrimaryIdentifier(String otherPayerPrimaryIdentifier) {
		this.otherPayerPrimaryIdentifier = otherPayerPrimaryIdentifier;
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