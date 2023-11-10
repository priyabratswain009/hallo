package com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "submitterId", "payerId", "claimStartDate", "claimEndDate", "transactionReceivedStartDate",
		"transactionReceivedEndDate", "controlNumber", "patientFirstName", "patientLastName", "memberId", "providerId",
		"providerFirstName", "providerLastName", "payerName" })
@Generated("jsonschema2pojo")
public class AttachmentSubmissionStatusInput {

	@JsonProperty("submitterId")
	private String submitterId;
	@JsonProperty("payerId")
	private String payerId;
	@JsonProperty("claimStartDate")
	private String claimStartDate;
	@JsonProperty("claimEndDate")
	private String claimEndDate;
	@JsonProperty("transactionReceivedStartDate")
	private String transactionReceivedStartDate;
	@JsonProperty("transactionReceivedEndDate")
	private String transactionReceivedEndDate;
	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("patientFirstName")
	private String patientFirstName;
	@JsonProperty("patientLastName")
	private String patientLastName;
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("providerId")
	private String providerId;
	@JsonProperty("providerFirstName")
	private String providerFirstName;
	@JsonProperty("providerLastName")
	private String providerLastName;
	@JsonProperty("payerName")
	private String payerName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("submitterId")
	public String getSubmitterId() {
		return submitterId;
	}

	@JsonProperty("submitterId")
	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}

	@JsonProperty("payerId")
	public String getPayerId() {
		return payerId;
	}

	@JsonProperty("payerId")
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	@JsonProperty("claimStartDate")
	public String getClaimStartDate() {
		return claimStartDate;
	}

	@JsonProperty("claimStartDate")
	public void setClaimStartDate(String claimStartDate) {
		this.claimStartDate = claimStartDate;
	}

	@JsonProperty("claimEndDate")
	public String getClaimEndDate() {
		return claimEndDate;
	}

	@JsonProperty("claimEndDate")
	public void setClaimEndDate(String claimEndDate) {
		this.claimEndDate = claimEndDate;
	}

	@JsonProperty("transactionReceivedStartDate")
	public String getTransactionReceivedStartDate() {
		return transactionReceivedStartDate;
	}

	@JsonProperty("transactionReceivedStartDate")
	public void setTransactionReceivedStartDate(String transactionReceivedStartDate) {
		this.transactionReceivedStartDate = transactionReceivedStartDate;
	}

	@JsonProperty("transactionReceivedEndDate")
	public String getTransactionReceivedEndDate() {
		return transactionReceivedEndDate;
	}

	@JsonProperty("transactionReceivedEndDate")
	public void setTransactionReceivedEndDate(String transactionReceivedEndDate) {
		this.transactionReceivedEndDate = transactionReceivedEndDate;
	}

	@JsonProperty("controlNumber")
	public String getControlNumber() {
		return controlNumber;
	}

	@JsonProperty("controlNumber")
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	@JsonProperty("patientFirstName")
	public String getPatientFirstName() {
		return patientFirstName;
	}

	@JsonProperty("patientFirstName")
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	@JsonProperty("patientLastName")
	public String getPatientLastName() {
		return patientLastName;
	}

	@JsonProperty("patientLastName")
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("providerId")
	public String getProviderId() {
		return providerId;
	}

	@JsonProperty("providerId")
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@JsonProperty("providerFirstName")
	public String getProviderFirstName() {
		return providerFirstName;
	}

	@JsonProperty("providerFirstName")
	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}

	@JsonProperty("providerLastName")
	public String getProviderLastName() {
		return providerLastName;
	}

	@JsonProperty("providerLastName")
	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}

	@JsonProperty("payerName")
	public String getPayerName() {
		return payerName;
	}

	@JsonProperty("payerName")
	public void setPayerName(String payerName) {
		this.payerName = payerName;
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