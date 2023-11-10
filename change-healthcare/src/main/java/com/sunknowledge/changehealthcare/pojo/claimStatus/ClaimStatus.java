package com.sunknowledge.changehealthcare.pojo.claimStatus;

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
@JsonPropertyOrder({ "statusCategoryCode", "statusCategoryCodeValue", "statusCode", "statusCodeValue", "entityCode",
		"entity", "effectiveDate", "submittedAmount", "amountPaid", "paidDate", "checkIssueDate", "checkNumber",
		"trackingNumber", "claimServiceDate", "tradingPartnerClaimNumber" })
@Generated("jsonschema2pojo")
public class ClaimStatus {

	@JsonProperty("statusCategoryCode")
	private String statusCategoryCode;
	@JsonProperty("statusCategoryCodeValue")
	private String statusCategoryCodeValue;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("statusCodeValue")
	private String statusCodeValue;
	@JsonProperty("entityCode")
	private String entityCode;
	@JsonProperty("entity")
	private String entity;
	@JsonProperty("effectiveDate")
	private String effectiveDate;
	@JsonProperty("submittedAmount")
	private String submittedAmount;
	@JsonProperty("amountPaid")
	private String amountPaid;
	@JsonProperty("paidDate")
	private String paidDate;
	@JsonProperty("checkIssueDate")
	private String checkIssueDate;
	@JsonProperty("checkNumber")
	private String checkNumber;
	@JsonProperty("trackingNumber")
	private String trackingNumber;
	@JsonProperty("claimServiceDate")
	private String claimServiceDate;
	@JsonProperty("tradingPartnerClaimNumber")
	private String tradingPartnerClaimNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("statusCategoryCode")
	public String getStatusCategoryCode() {
		return statusCategoryCode;
	}

	@JsonProperty("statusCategoryCode")
	public void setStatusCategoryCode(String statusCategoryCode) {
		this.statusCategoryCode = statusCategoryCode;
	}

	@JsonProperty("statusCategoryCodeValue")
	public String getStatusCategoryCodeValue() {
		return statusCategoryCodeValue;
	}

	@JsonProperty("statusCategoryCodeValue")
	public void setStatusCategoryCodeValue(String statusCategoryCodeValue) {
		this.statusCategoryCodeValue = statusCategoryCodeValue;
	}

	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("statusCodeValue")
	public String getStatusCodeValue() {
		return statusCodeValue;
	}

	@JsonProperty("statusCodeValue")
	public void setStatusCodeValue(String statusCodeValue) {
		this.statusCodeValue = statusCodeValue;
	}

	@JsonProperty("entityCode")
	public String getEntityCode() {
		return entityCode;
	}

	@JsonProperty("entityCode")
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	@JsonProperty("entity")
	public String getEntity() {
		return entity;
	}

	@JsonProperty("entity")
	public void setEntity(String entity) {
		this.entity = entity;
	}

	@JsonProperty("effectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@JsonProperty("effectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("submittedAmount")
	public String getSubmittedAmount() {
		return submittedAmount;
	}

	@JsonProperty("submittedAmount")
	public void setSubmittedAmount(String submittedAmount) {
		this.submittedAmount = submittedAmount;
	}

	@JsonProperty("amountPaid")
	public String getAmountPaid() {
		return amountPaid;
	}

	@JsonProperty("amountPaid")
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	@JsonProperty("paidDate")
	public String getPaidDate() {
		return paidDate;
	}

	@JsonProperty("paidDate")
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	@JsonProperty("checkIssueDate")
	public String getCheckIssueDate() {
		return checkIssueDate;
	}

	@JsonProperty("checkIssueDate")
	public void setCheckIssueDate(String checkIssueDate) {
		this.checkIssueDate = checkIssueDate;
	}

	@JsonProperty("checkNumber")
	public String getCheckNumber() {
		return checkNumber;
	}

	@JsonProperty("checkNumber")
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	@JsonProperty("trackingNumber")
	public String getTrackingNumber() {
		return trackingNumber;
	}

	@JsonProperty("trackingNumber")
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	@JsonProperty("claimServiceDate")
	public String getClaimServiceDate() {
		return claimServiceDate;
	}

	@JsonProperty("claimServiceDate")
	public void setClaimServiceDate(String claimServiceDate) {
		this.claimServiceDate = claimServiceDate;
	}

	@JsonProperty("tradingPartnerClaimNumber")
	public String getTradingPartnerClaimNumber() {
		return tradingPartnerClaimNumber;
	}

	@JsonProperty("tradingPartnerClaimNumber")
	public void setTradingPartnerClaimNumber(String tradingPartnerClaimNumber) {
		this.tradingPartnerClaimNumber = tradingPartnerClaimNumber;
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