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
@JsonPropertyOrder({ "organizationName", "firstName", "lastName", "npi", "tin", "spn", "taxId", "etin",
		"providerType" })
@Generated("jsonschema2pojo")
public class Provider {

	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("tin")
	private String tin;
	@JsonProperty("spn")
	private String spn;
	@JsonProperty("taxId")
	private String taxId;
	@JsonProperty("etin")
	private String etin;
	@JsonProperty("providerType")
	private String providerType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
	}

	@JsonProperty("tin")
	public String getTin() {
		return tin;
	}

	@JsonProperty("tin")
	public void setTin(String tin) {
		this.tin = tin;
	}

	@JsonProperty("spn")
	public String getSpn() {
		return spn;
	}

	@JsonProperty("spn")
	public void setSpn(String spn) {
		this.spn = spn;
	}

	@JsonProperty("taxId")
	public String getTaxId() {
		return taxId;
	}

	@JsonProperty("taxId")
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@JsonProperty("etin")
	public String getEtin() {
		return etin;
	}

	@JsonProperty("etin")
	public void setEtin(String etin) {
		this.etin = etin;
	}

	@JsonProperty("providerType")
	public String getProviderType() {
		return providerType;
	}

	@JsonProperty("providerType")
	public void setProviderType(String providerType) {
		this.providerType = providerType;
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