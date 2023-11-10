package com.sunknowledge.dme.rcm.domain.Coverage;

import java.util.LinkedHashMap;
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
@JsonPropertyOrder({ "totalCount", "count", "offset", "limit", "links", "coverages" })
@Generated("jsonschema2pojo")
public class CoverageOutput {

	@JsonProperty("totalCount")
	private Integer totalCount;
	@JsonProperty("count")
	private Integer count;
	@JsonProperty("offset")
	private Integer offset;
	@JsonProperty("limit")
	private Integer limit;
	@JsonProperty("links")
	private Links links;
	@JsonProperty("coverages")
	private List<Coverage> coverages;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("totalCount")
	public Integer getTotalCount() {
		return totalCount;
	}

	@JsonProperty("totalCount")
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@JsonProperty("count")
	public Integer getCount() {
		return count;
	}

	@JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
	}

	@JsonProperty("offset")
	public Integer getOffset() {
		return offset;
	}

	@JsonProperty("offset")
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@JsonProperty("limit")
	public Integer getLimit() {
		return limit;
	}

	@JsonProperty("limit")
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@JsonProperty("links")
	public Links getLinks() {
		return links;
	}

	@JsonProperty("links")
	public void setLinks(Links links) {
		this.links = links;
	}

	@JsonProperty("coverages")
	public List<Coverage> getCoverages() {
		return coverages;
	}

	@JsonProperty("coverages")
	public void setCoverages(List<Coverage> coverages) {
		this.coverages = coverages;
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