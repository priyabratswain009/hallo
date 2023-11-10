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
@JsonPropertyOrder({ "otherPayerOrganizationName", "otherPayerIdentifierTypeCode", "otherPayerIdentifier",
		"otherPayerAdjudicationOrPaymentDate", "otherPayerSecondaryIdentifier", "qualifier", "identifier",
		"otherPayerClaimAdjustmentIndicator", "otherPayerClaimControlNumber", "otherPayerAddress" })
@Generated("jsonschema2pojo")
public class OtherPayerName {

	@JsonProperty("otherPayerOrganizationName")
	private String otherPayerOrganizationName;
	@JsonProperty("otherPayerIdentifierTypeCode")
	private String otherPayerIdentifierTypeCode;
	@JsonProperty("otherPayerIdentifier")
	private String otherPayerIdentifier;
	@JsonProperty("otherPayerAdjudicationOrPaymentDate")
	private String otherPayerAdjudicationOrPaymentDate;
	@JsonProperty("otherPayerSecondaryIdentifier")
	private List<OtherPayerSecondaryIdentifier> otherPayerSecondaryIdentifier = null;
	@JsonProperty("qualifier")
	private String qualifier;
	@JsonProperty("identifier")
	private String identifier;
	@JsonProperty("otherPayerClaimAdjustmentIndicator")
	private String otherPayerClaimAdjustmentIndicator;
	@JsonProperty("otherPayerClaimControlNumber")
	private String otherPayerClaimControlNumber;
	@JsonProperty("otherPayerAddress")
	private OtherPayerAddress otherPayerAddress;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherPayerOrganizationName")
	public String getOtherPayerOrganizationName() {
		return otherPayerOrganizationName;
	}

	@JsonProperty("otherPayerOrganizationName")
	public void setOtherPayerOrganizationName(String otherPayerOrganizationName) {
		this.otherPayerOrganizationName = otherPayerOrganizationName;
	}

	@JsonProperty("otherPayerIdentifierTypeCode")
	public String getOtherPayerIdentifierTypeCode() {
		return otherPayerIdentifierTypeCode;
	}

	@JsonProperty("otherPayerIdentifierTypeCode")
	public void setOtherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
		this.otherPayerIdentifierTypeCode = otherPayerIdentifierTypeCode;
	}

	@JsonProperty("otherPayerIdentifier")
	public String getOtherPayerIdentifier() {
		return otherPayerIdentifier;
	}

	@JsonProperty("otherPayerIdentifier")
	public void setOtherPayerIdentifier(String otherPayerIdentifier) {
		this.otherPayerIdentifier = otherPayerIdentifier;
	}

	@JsonProperty("otherPayerAdjudicationOrPaymentDate")
	public String getOtherPayerAdjudicationOrPaymentDate() {
		return otherPayerAdjudicationOrPaymentDate;
	}

	@JsonProperty("otherPayerAdjudicationOrPaymentDate")
	public void setOtherPayerAdjudicationOrPaymentDate(String otherPayerAdjudicationOrPaymentDate) {
		this.otherPayerAdjudicationOrPaymentDate = otherPayerAdjudicationOrPaymentDate;
	}

	@JsonProperty("qualifier")
	public String getQualifier() {
		return qualifier;
	}

	@JsonProperty("qualifier")
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	@JsonProperty("identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@JsonProperty("otherPayerClaimAdjustmentIndicator")
	public String getOtherPayerClaimAdjustmentIndicator() {
		return otherPayerClaimAdjustmentIndicator;
	}

	@JsonProperty("otherPayerClaimAdjustmentIndicator")
	public void setOtherPayerClaimAdjustmentIndicator(String otherPayerClaimAdjustmentIndicator) {
		this.otherPayerClaimAdjustmentIndicator = otherPayerClaimAdjustmentIndicator;
	}

	@JsonProperty("otherPayerClaimControlNumber")
	public String getOtherPayerClaimControlNumber() {
		return otherPayerClaimControlNumber;
	}

	@JsonProperty("otherPayerClaimControlNumber")
	public void setOtherPayerClaimControlNumber(String otherPayerClaimControlNumber) {
		this.otherPayerClaimControlNumber = otherPayerClaimControlNumber;
	}

	@JsonProperty("otherPayerAddress")
	public OtherPayerAddress getOtherPayerAddress() {
		return otherPayerAddress;
	}

	@JsonProperty("otherPayerAddress")
	public void setOtherPayerAddress(OtherPayerAddress otherPayerAddress) {
		this.otherPayerAddress = otherPayerAddress;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public List<OtherPayerSecondaryIdentifier> getOtherPayerSecondaryIdentifier() {
		return otherPayerSecondaryIdentifier;
	}

	public void setOtherPayerSecondaryIdentifier(List<OtherPayerSecondaryIdentifier> otherPayerSecondaryIdentifier) {
		this.otherPayerSecondaryIdentifier = otherPayerSecondaryIdentifier;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}