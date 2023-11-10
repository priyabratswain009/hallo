package com.sunknowledge.dme.rcm.dto.nppes;

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
@JsonPropertyOrder({
	"enumeration_type",
	"number",
	"last_updated_epoch",
	"created_epoch",
	"basic",
	"other_names",
	"addresses",
	"taxonomies",
	"identifiers",
	"endpoints",
	"practiceLocations"
})
@Generated("jsonschema2pojo")
public class Result {
	@JsonProperty("enumeration_type")
	private String enumerationType;
	@JsonProperty("number")
	private String number;
	@JsonProperty("last_updated_epoch")
	private String lastUpdatedEpoch;
	@JsonProperty("created_epoch")
	private String createdEpoch;
	@JsonProperty("basic")
	private Basic basic;
	@JsonProperty("other_names")
	private List<OtherName> otherNames = null;
	@JsonProperty("addresses")
	private List<Address> addresses = null;
	@JsonProperty("taxonomies")
	private List<Taxonomy> taxonomies = null;
	@JsonProperty("identifiers")
	private List<Identifier> identifiers = null;
	@JsonProperty("endpoints")
	private List<Endpoint> endpoints = null;
	@JsonProperty("practiceLocations")
	private List<PracticeLocation> practiceLocations = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("enumeration_type")
	public String getEnumerationType() {
		return enumerationType;
	}

	@JsonProperty("enumeration_type")
	public void setEnumerationType(String enumerationType) {
		this.enumerationType = enumerationType;
	}

	@JsonProperty("number")
	public String getNumber() {
		return number;
	}

	@JsonProperty("number")
	public void setNumber(String number) {
		this.number = number;
	}

	@JsonProperty("last_updated_epoch")
	public String getLastUpdatedEpoch() {
		return lastUpdatedEpoch;
	}

	@JsonProperty("last_updated_epoch")
	public void setLastUpdatedEpoch(String lastUpdatedEpoch) {
		this.lastUpdatedEpoch = lastUpdatedEpoch;
	}

	@JsonProperty("created_epoch")
	public String getCreatedEpoch() {
		return createdEpoch;
	}

	@JsonProperty("created_epoch")
	public void setCreatedEpoch(String createdEpoch) {
		this.createdEpoch = createdEpoch;
	}

	@JsonProperty("basic")
	public Basic getBasic() {
		return basic;
	}

	@JsonProperty("basic")
	public void setBasic(Basic basic) {
		this.basic = basic;
	}

	@JsonProperty("other_names")
	public List<OtherName> getOtherNames() {
		return otherNames;
	}

	@JsonProperty("other_names")
	public void setOtherNames(List<OtherName> otherNames) {
		this.otherNames = otherNames;
	}

	@JsonProperty("addresses")
	public List<Address> getAddresses() {
		return addresses;
	}

	@JsonProperty("addresses")
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@JsonProperty("taxonomies")
	public List<Taxonomy> getTaxonomies() {
		return taxonomies;
	}

	@JsonProperty("taxonomies")
	public void setTaxonomies(List<Taxonomy> taxonomies) {
		this.taxonomies = taxonomies;
	}

	@JsonProperty("identifiers")
	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	@JsonProperty("identifiers")
	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	@JsonProperty("endpoints")
	public List<Endpoint> getEndpoints() {
		return endpoints;
	}

	@JsonProperty("endpoints")
	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	@JsonProperty("practiceLocations")
	public List<PracticeLocation> getPracticeLocations() {
	return practiceLocations;
	}

	@JsonProperty("practiceLocations")
	public void setPracticeLocations(List<PracticeLocation> practiceLocations) {
	this.practiceLocations = practiceLocations;
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
