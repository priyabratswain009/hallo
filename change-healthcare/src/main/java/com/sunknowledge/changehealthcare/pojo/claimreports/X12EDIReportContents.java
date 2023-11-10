package com.sunknowledge.changehealthcare.pojo.claimreports;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277.Meta;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"report_content",
	"meta"
})
@Generated("jsonschema2pojo")
public class X12EDIReportContents {
	@JsonProperty("report_content")
	private String reportContent;
	@JsonProperty("meta")
	private Meta meta;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("report_content")
	public String getReportContent() {
		return reportContent;
	}

	@JsonProperty("report_content")
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
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
