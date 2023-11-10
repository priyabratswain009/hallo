package com.sunknowledge.dme.rcm.pojo.claimreports;

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
import com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277.Meta;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"reports",
	"meta"
})
@Generated("jsonschema2pojo")
public class ClaimReports {
	@JsonProperty("reports")
	private List<String> reports = null;
	@JsonProperty("meta")
	private Meta meta;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("reports")
	public List<String> getReports() {
		return reports;
	}

	@JsonProperty("reports")
	public void setReports(List<String> reports) {
		this.reports = reports;
	}

	@JsonProperty("meta")
	public Meta getMeta() {
		return meta;
	}

	@JsonProperty("meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
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
