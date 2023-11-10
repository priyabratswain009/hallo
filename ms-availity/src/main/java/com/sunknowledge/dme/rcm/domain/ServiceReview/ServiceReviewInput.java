package com.sunknowledge.dme.rcm.domain.ServiceReview;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "payer", "requestingProvider", "subscriber", "patient", "requestTypeCode", "fromDate", "toDate",
		"certificationIssueDate", "certificationNumber", "referenceNumber", "statusCode", "sessionId" })
@Generated("jsonschema2pojo")
public class ServiceReviewInput {

	@JsonProperty("payer")
	private Payer payer;
	@JsonProperty("requestingProvider")
	private RequestingProvider requestingProvider;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("patient")
	private Patient patient;
	@JsonProperty("requestTypeCode")
	private String requestTypeCode;
	@JsonProperty("fromDate")
	private String fromDate;
	@JsonProperty("toDate")
	private String toDate;
	@JsonProperty("certificationIssueDate")
	private String certificationIssueDate;
	@JsonProperty("certificationNumber")
	private String certificationNumber;
	@JsonProperty("referenceNumber")
	private String referenceNumber;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("sessionId")
	private String sessionId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

	@JsonProperty("requestTypeCode")
	public String getRequestTypeCode() {
		return requestTypeCode;
	}

	@JsonProperty("requestTypeCode")
	public void setRequestTypeCode(String requestTypeCode) {
		this.requestTypeCode = requestTypeCode;
	}

	@JsonProperty("fromDate")
	public String getFromDate() {
		return fromDate;
	}

	@JsonProperty("fromDate")
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	@JsonProperty("toDate")
	public String getToDate() {
		return toDate;
	}

	@JsonProperty("toDate")
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@JsonProperty("certificationIssueDate")
	public String getCertificationIssueDate() {
		return certificationIssueDate;
	}

	@JsonProperty("certificationIssueDate")
	public void setCertificationIssueDate(String certificationIssueDate) {
		this.certificationIssueDate = certificationIssueDate;
	}

	@JsonProperty("certificationNumber")
	public String getCertificationNumber() {
		return certificationNumber;
	}

	@JsonProperty("certificationNumber")
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	@JsonProperty("referenceNumber")
	public String getReferenceNumber() {
		return referenceNumber;
	}

	@JsonProperty("referenceNumber")
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("sessionId")
	public String getSessionId() {
		return sessionId;
	}

	@JsonProperty("sessionId")
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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