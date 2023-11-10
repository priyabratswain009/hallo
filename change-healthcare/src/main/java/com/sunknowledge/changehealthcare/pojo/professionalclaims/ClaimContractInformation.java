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
@JsonPropertyOrder({ "contractTypeCode", "contractAmount", "contractPercentage", "contractCode",
		"termsDiscountPercentage", "contractVersionIdentifier" })
@Generated("jsonschema2pojo")
public class ClaimContractInformation {

	@JsonProperty("contractTypeCode")
	private String contractTypeCode;
	@JsonProperty("contractAmount")
	private String contractAmount;
	@JsonProperty("contractPercentage")
	private String contractPercentage;
	@JsonProperty("contractCode")
	private String contractCode;
	@JsonProperty("termsDiscountPercentage")
	private String termsDiscountPercentage;
	@JsonProperty("contractVersionIdentifier")
	private String contractVersionIdentifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("contractTypeCode")
	public String getContractTypeCode() {
		return contractTypeCode;
	}

	@JsonProperty("contractTypeCode")
	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}

	@JsonProperty("contractAmount")
	public String getContractAmount() {
		return contractAmount;
	}

	@JsonProperty("contractAmount")
	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	@JsonProperty("contractPercentage")
	public String getContractPercentage() {
		return contractPercentage;
	}

	@JsonProperty("contractPercentage")
	public void setContractPercentage(String contractPercentage) {
		this.contractPercentage = contractPercentage;
	}

	@JsonProperty("contractCode")
	public String getContractCode() {
		return contractCode;
	}

	@JsonProperty("contractCode")
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	@JsonProperty("termsDiscountPercentage")
	public String getTermsDiscountPercentage() {
		return termsDiscountPercentage;
	}

	@JsonProperty("termsDiscountPercentage")
	public void setTermsDiscountPercentage(String termsDiscountPercentage) {
		this.termsDiscountPercentage = termsDiscountPercentage;
	}

	@JsonProperty("contractVersionIdentifier")
	public String getContractVersionIdentifier() {
		return contractVersionIdentifier;
	}

	@JsonProperty("contractVersionIdentifier")
	public void setContractVersionIdentifier(String contractVersionIdentifier) {
		this.contractVersionIdentifier = contractVersionIdentifier;
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