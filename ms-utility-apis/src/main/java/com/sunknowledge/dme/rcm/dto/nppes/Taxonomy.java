package com.sunknowledge.dme.rcm.dto.nppes;

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
@JsonPropertyOrder({
	"code",
	"desc",
	"primary",
	"state",
	"license",
	"taxonomy_group"
})
@Generated("jsonschema2pojo")
public class Taxonomy {
	@JsonProperty("code")
	private String code;
	@JsonProperty("desc")
	private String desc;
	@JsonProperty("primary")
	private Boolean primary;
	@JsonProperty("state")
	private String state;
	@JsonProperty("license")
	private String license;
	@JsonProperty("taxonomy_group")
	private String taxonomyGroup;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("desc")
	public String getDesc() {
		return desc;
	}

	@JsonProperty("desc")
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@JsonProperty("primary")
	public Boolean getPrimary() {
		return primary;
	}

	@JsonProperty("primary")
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("license")
	public String getLicense() {
		return license;
	}

	@JsonProperty("license")
	public void setLicense(String license) {
		this.license = license;
	}

	@JsonProperty("taxonomy_group")
	public String getTaxonomyGroup() {
		return taxonomyGroup;
	}

	@JsonProperty("taxonomy_group")
	public void setTaxonomyGroup(String taxonomyGroup) {
		this.taxonomyGroup = taxonomyGroup;
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
