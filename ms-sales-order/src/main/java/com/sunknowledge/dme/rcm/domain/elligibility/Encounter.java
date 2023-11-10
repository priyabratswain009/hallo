package com.sunknowledge.dme.rcm.domain.elligibility;

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
@JsonPropertyOrder({ "serviceTypeCodes", "dateOfService", "productOrServiceIDQualifier", "procedureCode" })
@Generated("jsonschema2pojo")
public class Encounter {

	@JsonProperty("serviceTypeCodes")
	private List<String> serviceTypeCodes = null;
	@JsonProperty("dateOfService")
	private String dateOfService;
	@JsonProperty("productOrServiceIDQualifier")
	private String productOrServiceIDQualifier;
	@JsonProperty("procedureCode")
	private String procedureCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("serviceTypeCodes")
	public List<String> getServiceTypeCodes() {
		return serviceTypeCodes;
	}

	@JsonProperty("serviceTypeCodes")
	public void setServiceTypeCodes(List<String> serviceTypeCodes) {
		this.serviceTypeCodes = serviceTypeCodes;
	}

	@JsonProperty("dateOfService")
	public String getDateOfService() {
		return dateOfService;
	}

	@JsonProperty("dateOfService")
	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}

	@JsonProperty("productOrServiceIDQualifier")
	public String getProductOrServiceIDQualifier() {
		return productOrServiceIDQualifier;
	}

	@JsonProperty("productOrServiceIDQualifier")
	public void setProductOrServiceIDQualifier(String productOrServiceIDQualifier) {
		this.productOrServiceIDQualifier = productOrServiceIDQualifier;
	}

	@JsonProperty("procedureCode")
	public String getProcedureCode() {
		return procedureCode;
	}

	@JsonProperty("procedureCode")
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
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