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
@JsonPropertyOrder({ "otherPayerRenderingProviderSecondaryIdentifier", "entityTypeQualifier" })
@Generated("jsonschema2pojo")
public class OtherPayerRenderingProvider {

	@JsonProperty("otherPayerRenderingProviderSecondaryIdentifier")
	private List<OtherPayerRenderingProviderSecondaryIdentifier> otherPayerRenderingProviderSecondaryIdentifier = null;
	@JsonProperty("entityTypeQualifier")
	private String entityTypeQualifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherPayerRenderingProviderSecondaryIdentifier")
	public List<OtherPayerRenderingProviderSecondaryIdentifier> getOtherPayerRenderingProviderSecondaryIdentifier() {
		return otherPayerRenderingProviderSecondaryIdentifier;
	}

	@JsonProperty("otherPayerRenderingProviderSecondaryIdentifier")
	public void setOtherPayerRenderingProviderSecondaryIdentifier(
			List<OtherPayerRenderingProviderSecondaryIdentifier> otherPayerRenderingProviderSecondaryIdentifier) {
		this.otherPayerRenderingProviderSecondaryIdentifier = otherPayerRenderingProviderSecondaryIdentifier;
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