package com.sunknowledge.dme.rcm.domain.Coverage;

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
@JsonPropertyOrder({ "payerId", "providerLastName", "providerFirstName", "providerType", "providerNpi", "providerCity",
		"providerState", "providerZipCode", "submitterId", "asOfDate", "serviceType", "memberId", "patientLastName",
		"patientFirstName", "patientBirthDate", "patientGender", "patientState", "subscriberRelationship" })
@Generated("jsonschema2pojo")
public class CoverageInput {

	@JsonProperty("payerId")
	private String payerId;
	@JsonProperty("providerLastName")
	private String providerLastName;
	@JsonProperty("providerFirstName")
	private String providerFirstName;
	@JsonProperty("providerType")
	private String providerType;
	@JsonProperty("providerNpi")
	private String providerNpi;
	@JsonProperty("providerCity")
	private String providerCity;
	@JsonProperty("providerState")
	private String providerState;
	@JsonProperty("providerZipCode")
	private String providerZipCode;
	@JsonProperty("submitterId")
	private String submitterId;
	@JsonProperty("asOfDate")
	private String asOfDate;
	@JsonProperty("serviceType")
	private String serviceType;
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("patientLastName")
	private String patientLastName;
	@JsonProperty("patientFirstName")
	private String patientFirstName;
	@JsonProperty("patientBirthDate")
	private String patientBirthDate;
	@JsonProperty("patientGender")
	private String patientGender;
	@JsonProperty("patientState")
	private String patientState;
	@JsonProperty("subscriberRelationship")
	private String subscriberRelationship;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("payerId")
	public String getPayerId() {
		return payerId;
	}

	@JsonProperty("payerId")
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	@JsonProperty("providerLastName")
	public String getProviderLastName() {
		return providerLastName;
	}

	@JsonProperty("providerLastName")
	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}

	@JsonProperty("providerFirstName")
	public String getProviderFirstName() {
		return providerFirstName;
	}

	@JsonProperty("providerFirstName")
	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}

	@JsonProperty("providerType")
	public String getProviderType() {
		return providerType;
	}

	@JsonProperty("providerType")
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	@JsonProperty("providerNpi")
	public String getProviderNpi() {
		return providerNpi;
	}

	@JsonProperty("providerNpi")
	public void setProviderNpi(String providerNpi) {
		this.providerNpi = providerNpi;
	}

	@JsonProperty("providerCity")
	public String getProviderCity() {
		return providerCity;
	}

	@JsonProperty("providerCity")
	public void setProviderCity(String providerCity) {
		this.providerCity = providerCity;
	}

	@JsonProperty("providerState")
	public String getProviderState() {
		return providerState;
	}

	@JsonProperty("providerState")
	public void setProviderState(String providerState) {
		this.providerState = providerState;
	}

	@JsonProperty("providerZipCode")
	public String getProviderZipCode() {
		return providerZipCode;
	}

	@JsonProperty("providerZipCode")
	public void setProviderZipCode(String providerZipCode) {
		this.providerZipCode = providerZipCode;
	}

	@JsonProperty("submitterId")
	public String getSubmitterId() {
		return submitterId;
	}

	@JsonProperty("submitterId")
	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}

	@JsonProperty("asOfDate")
	public String getAsOfDate() {
		return asOfDate;
	}

	@JsonProperty("asOfDate")
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}

	@JsonProperty("serviceType")
	public String getServiceType() {
		return serviceType;
	}

	@JsonProperty("serviceType")
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("patientLastName")
	public String getPatientLastName() {
		return patientLastName;
	}

	@JsonProperty("patientLastName")
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	@JsonProperty("patientFirstName")
	public String getPatientFirstName() {
		return patientFirstName;
	}

	@JsonProperty("patientFirstName")
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	@JsonProperty("patientBirthDate")
	public String getPatientBirthDate() {
		return patientBirthDate;
	}

	@JsonProperty("patientBirthDate")
	public void setPatientBirthDate(String patientBirthDate) {
		this.patientBirthDate = patientBirthDate;
	}

	@JsonProperty("patientGender")
	public String getPatientGender() {
		return patientGender;
	}

	@JsonProperty("patientGender")
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	@JsonProperty("patientState")
	public String getPatientState() {
		return patientState;
	}

	@JsonProperty("patientState")
	public void setPatientState(String patientState) {
		this.patientState = patientState;
	}

	@JsonProperty("subscriberRelationship")
	public String getSubscriberRelationship() {
		return subscriberRelationship;
	}

	@JsonProperty("subscriberRelationship")
	public void setSubscriberRelationship(String subscriberRelationship) {
		this.subscriberRelationship = subscriberRelationship;
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