package com.sunknowledge.dme.rcm.domain.soelligibility;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "statusCode", "status", "planDetails", "serviceTypeCodes" })
@Generated("jsonschema2pojo")
public class Planstatus {

	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("status")
	private String status;
	@JsonProperty("planDetails")
	private String planDetails;
	@JsonProperty("serviceTypeCodes")
	private List<String> serviceTypeCodes;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("planDetails")
	public String getPlanDetails() {
		return planDetails;
	}

	@JsonProperty("planDetails")
	public void setPlanDetails(String planDetails) {
		this.planDetails = planDetails;
	}

	@JsonProperty("serviceTypeCodes")
	public List<String> getServiceTypeCodes() {
		return serviceTypeCodes;
	}

	@JsonProperty("serviceTypeCodes")
	public void setServiceTypeCodes(List<String> serviceTypeCodes) {
		this.serviceTypeCodes = serviceTypeCodes;
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
