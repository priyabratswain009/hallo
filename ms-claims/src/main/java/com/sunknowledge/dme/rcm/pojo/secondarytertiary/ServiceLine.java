package com.sunknowledge.dme.rcm.pojo.secondarytertiary;

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
import com.sunknowledge.dme.rcm.pojo.common.OrderingProvider;
import com.sunknowledge.dme.rcm.pojo.common.ProfessionalService;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "assignedNumber", "professionalService", "orderingProvider", "serviceDate",
		"providerControlNumber", "additionalNotes", "lineAdjudicationInformation" })
@Generated("jsonschema2pojo")
public class ServiceLine {

	@JsonProperty("assignedNumber")
	private String assignedNumber;
	@JsonProperty("professionalService")
	private ProfessionalService professionalService;
	@JsonProperty("orderingProvider")
	private OrderingProvider orderingProvider;
	@JsonProperty("serviceDate")
	private String serviceDate;
	@JsonProperty("providerControlNumber")
	private String providerControlNumber;
	@JsonProperty("additionalNotes")
	private String additionalNotes;
	@JsonProperty("lineAdjudicationInformation")
	private List<LineAdjudicationInformation> lineAdjudicationInformation = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("assignedNumber")
	public String getAssignedNumber() {
		return assignedNumber;
	}

	@JsonProperty("assignedNumber")
	public void setAssignedNumber(String assignedNumber) {
		this.assignedNumber = assignedNumber;
	}

	@JsonProperty("professionalService")
	public ProfessionalService getProfessionalService() {
		return professionalService;
	}

	@JsonProperty("professionalService")
	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}

	@JsonProperty("orderingProvider")
	public OrderingProvider getOrderingProvider() {
		return orderingProvider;
	}

	@JsonProperty("orderingProvider")
	public void setOrderingProvider(OrderingProvider orderingProvider) {
		this.orderingProvider = orderingProvider;
	}

	@JsonProperty("serviceDate")
	public String getServiceDate() {
		return serviceDate;
	}

	@JsonProperty("serviceDate")
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	@JsonProperty("providerControlNumber")
	public String getProviderControlNumber() {
		return providerControlNumber;
	}

	@JsonProperty("providerControlNumber")
	public void setProviderControlNumber(String providerControlNumber) {
		this.providerControlNumber = providerControlNumber;
	}

	@JsonProperty("additionalNotes")
	public String getAdditionalNotes() {
		return additionalNotes;
	}

	@JsonProperty("additionalNotes")
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	@JsonProperty("lineAdjudicationInformation")
	public List<LineAdjudicationInformation> getLineAdjudicationInformation() {
		return lineAdjudicationInformation;
	}

	@JsonProperty("lineAdjudicationInformation")
	public void setLineAdjudicationInformation(List<LineAdjudicationInformation> lineAdjudicationInformation) {
		this.lineAdjudicationInformation = lineAdjudicationInformation;
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