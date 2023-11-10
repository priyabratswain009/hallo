package com.sunknowledge.dme.rcm.dto.icd;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"@context",
	"@id",
	"parent",
	"child",
	"browserUrl",
	"code",
	"title",
	"inclusion",
	"codingHint",
	"classKind"
})
@Generated("jsonschema2pojo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOutcome {
	@JsonProperty("@context")
	private String context;
	@JsonProperty("@id")
	private String id;
	@JsonProperty("parent")
	private List<String> parent = null;
	@JsonProperty("child")
	private List<String> child = null;
	@JsonProperty("browserUrl")
	private String browserUrl;
	@JsonProperty("code")
	private String code;
	@JsonProperty("title")
	private Title title;
	@JsonProperty("inclusion")
	private List<Inclusion> inclusion = null;
	@JsonProperty("exclusion")
	private List<Exclusion> exclusion = null;
	@JsonProperty("codingHint")
	private List<CodingHint> codingHint = null;
	@JsonProperty("classKind")
	private String classKind;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("@context")
	public String getContext() {
		return context;
	}

	@JsonProperty("@context")
	public void setContext(String context) {
		this.context = context;
	}

	@JsonProperty("@id")
	public String getId() {
		return id;
	}

	@JsonProperty("@id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("parent")
	public List<String> getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(List<String> parent) {
		this.parent = parent;
	}

	@JsonProperty("child")
	public List<String> getChild() {
		return child;
	}

	@JsonProperty("child")
	public void setChild(List<String> child) {
		this.child = child;
	}

	@JsonProperty("browserUrl")
	public String getBrowserUrl() {
		return browserUrl;
	}

	@JsonProperty("browserUrl")
	public void setBrowserUrl(String browserUrl) {
		this.browserUrl = browserUrl;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("title")
	public Title getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(Title title) {
		this.title = title;
	}

	@JsonProperty("inclusion")
	public List<Inclusion> getInclusion() {
		return inclusion;
	}

	@JsonProperty("inclusion")
	public void setInclusion(List<Inclusion> inclusion) {
		this.inclusion = inclusion;
	}

	@JsonProperty("codingHint")
	public List<CodingHint> getCodingHint() {
		return codingHint;
	}

	@JsonProperty("codingHint")
	public void setCodingHint(List<CodingHint> codingHint) {
		this.codingHint = codingHint;
	}

	@JsonProperty("classKind")
	public String getClassKind() {
		return classKind;
	}

	@JsonProperty("classKind")
	public void setClassKind(String classKind) {
		this.classKind = classKind;
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
