package com.sunknowledge.changehealthcare.pojo.professionalclaims;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "otherPayerBillingProviderIdentifier", "entityTypeQualifier" })
@Generated("jsonschema2pojo")
public class OtherPayerBillingProvider {

	@JsonProperty("otherPayerBillingProviderIdentifier")
	private List<OtherPayerBillingProviderIdentifier> otherPayerBillingProviderIdentifier = null;
	@JsonProperty("entityTypeQualifier")
	private String entityTypeQualifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherPayerBillingProviderIdentifier")
	public List<OtherPayerBillingProviderIdentifier> getOtherPayerBillingProviderIdentifier() {
		return otherPayerBillingProviderIdentifier;
	}

	@JsonProperty("otherPayerBillingProviderIdentifier")
	public void setOtherPayerBillingProviderIdentifier(
			List<OtherPayerBillingProviderIdentifier> otherPayerBillingProviderIdentifier) {
		this.otherPayerBillingProviderIdentifier = otherPayerBillingProviderIdentifier;
	}

	@JsonProperty("entityTypeQualifier")
	public String getEntityTypeQualifier() {
		return entityTypeQualifier;
	}

	@JsonProperty("entityTypeQualifier")
	public void setEntityTypeQualifier(String entityTypeQualifier) {
		this.entityTypeQualifier = entityTypeQualifier;
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