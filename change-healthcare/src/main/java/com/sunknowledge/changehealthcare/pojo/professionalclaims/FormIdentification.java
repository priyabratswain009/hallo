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
@JsonPropertyOrder({ "supportingDocumentation", "formTypeCode", "formIdentifier" })
@Generated("jsonschema2pojo")
public class FormIdentification {

	@JsonProperty("supportingDocumentation")
	private List<SupportingDocumentation> supportingDocumentation = null;
	@JsonProperty("formTypeCode")
	private String formTypeCode;
	@JsonProperty("formIdentifier")
	private String formIdentifier;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("supportingDocumentation")
	public List<SupportingDocumentation> getSupportingDocumentation() {
		return supportingDocumentation;
	}

	@JsonProperty("supportingDocumentation")
	public void setSupportingDocumentation(List<SupportingDocumentation> supportingDocumentation) {
		this.supportingDocumentation = supportingDocumentation;
	}

	@JsonProperty("formTypeCode")
	public String getFormTypeCode() {
		return formTypeCode;
	}

	@JsonProperty("formTypeCode")
	public void setFormTypeCode(String formTypeCode) {
		this.formTypeCode = formTypeCode;
	}

	@JsonProperty("formIdentifier")
	public String getFormIdentifier() {
		return formIdentifier;
	}

	@JsonProperty("formIdentifier")
	public void setFormIdentifier(String formIdentifier) {
		this.formIdentifier = formIdentifier;
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