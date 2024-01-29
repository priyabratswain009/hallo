package com.sunknowledge.dme.rcm.domain.coverage;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "npi", "taxId" })
@Generated("jsonschema2pojo")
public class RequestingProvider {

	@JsonProperty("npi")
	private String npi;
	@JsonProperty("taxId")
	private String taxId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
	}

	@JsonProperty("taxId")
	public String getTaxId() {
		return taxId;
	}

	@JsonProperty("taxId")
	public void setTaxId(String taxId) {
		this.taxId = taxId;
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
