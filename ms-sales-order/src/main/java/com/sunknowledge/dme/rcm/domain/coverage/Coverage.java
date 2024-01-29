package com.sunknowledge.dme.rcm.domain.coverage;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "links", "id", "customerId", "controlNumber", "status", "statusCode", "createdDate", "updatedDate",
		"expirationDate", "asOfDate", "requestedServiceType", "validationMessages", "subscriber", "patient", "payer",
		"requestingProvider", "plans" })
@Generated("jsonschema2pojo")
public class Coverage {

	@JsonProperty("links")
	private Links links;
	@JsonProperty("id")
	private String id;
	@JsonProperty("customerId")
	private String customerId;
	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("createdDate")
	private String createdDate;
	@JsonProperty("updatedDate")
	private String updatedDate;
	@JsonProperty("expirationDate")
	private String expirationDate;
	@JsonProperty("asOfDate")
	private String asOfDate;
	@JsonProperty("requestedServiceType")
	private List<RequestedServiceType> requestedServiceType;
	@JsonProperty("validationMessages")
	private List<Object> validationMessages;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("patient")
	private Patient patient;
	@JsonProperty("payer")
	private Payer payer;
	@JsonProperty("requestingProvider")
	private RequestingProvider requestingProvider;
	@JsonProperty("plans")
	private List<Plan> plans;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("links")
	public Links getLinks() {
		return links;
	}

	@JsonProperty("links")
	public void setLinks(Links links) {
		this.links = links;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("customerId")
	public String getCustomerId() {
		return customerId;
	}

	@JsonProperty("customerId")
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("controlNumber")
	public String getControlNumber() {
		return controlNumber;
	}

	@JsonProperty("controlNumber")
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("createdDate")
	public String getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("createdDate")
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@JsonProperty("updatedDate")
	public String getUpdatedDate() {
		return updatedDate;
	}

	@JsonProperty("updatedDate")
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonProperty("expirationDate")
	public String getExpirationDate() {
		return expirationDate;
	}

	@JsonProperty("expirationDate")
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonProperty("asOfDate")
	public String getAsOfDate() {
		return asOfDate;
	}

	@JsonProperty("asOfDate")
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}

	@JsonProperty("requestedServiceType")
	public List<RequestedServiceType> getRequestedServiceType() {
		return requestedServiceType;
	}

	@JsonProperty("requestedServiceType")
	public void setRequestedServiceType(List<RequestedServiceType> requestedServiceType) {
		this.requestedServiceType = requestedServiceType;
	}

	@JsonProperty("validationMessages")
	public List<Object> getValidationMessages() {
		return validationMessages;
	}

	@JsonProperty("validationMessages")
	public void setValidationMessages(List<Object> validationMessages) {
		this.validationMessages = validationMessages;
	}

	@JsonProperty("subscriber")
	public Subscriber getSubscriber() {
		return subscriber;
	}

	@JsonProperty("subscriber")
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@JsonProperty("patient")
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty("patient")
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@JsonProperty("payer")
	public Payer getPayer() {
		return payer;
	}

	@JsonProperty("payer")
	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	@JsonProperty("requestingProvider")
	public RequestingProvider getRequestingProvider() {
		return requestingProvider;
	}

	@JsonProperty("requestingProvider")
	public void setRequestingProvider(RequestingProvider requestingProvider) {
		this.requestingProvider = requestingProvider;
	}

	@JsonProperty("plans")
	public List<Plan> getPlans() {
		return plans;
	}

	@JsonProperty("plans")
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
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
