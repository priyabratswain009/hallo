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
@JsonPropertyOrder({ "beginningDateOfService", "endDateOfService", "trackingNumber", "submittedAmount",
		"tradingPartnerClaimNumber", "locationIdentifier", "billingType", "patientAccountNumber",
		"pharmacyPrescriptionNumber", "clearingHouseClaimNumber" })
@Generated("jsonschema2pojo")
public class Encounter {

	@JsonProperty("beginningDateOfService")
	private String beginningDateOfService;
	@JsonProperty("endDateOfService")
	private String endDateOfService;
	@JsonProperty("trackingNumber")
	private String trackingNumber;
	@JsonProperty("submittedAmount")
	private String submittedAmount;
	@JsonProperty("tradingPartnerClaimNumber")
	private String tradingPartnerClaimNumber;
	@JsonProperty("locationIdentifier")
	private String locationIdentifier;
	@JsonProperty("billingType")
	private String billingType;
	@JsonProperty("patientAccountNumber")
	private String patientAccountNumber;
	@JsonProperty("pharmacyPrescriptionNumber")
	private String pharmacyPrescriptionNumber;
	@JsonProperty("clearingHouseClaimNumber")
	private String clearingHouseClaimNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("beginningDateOfService")
	public String getBeginningDateOfService() {
		return beginningDateOfService;
	}

	@JsonProperty("beginningDateOfService")
	public void setBeginningDateOfService(String beginningDateOfService) {
		this.beginningDateOfService = beginningDateOfService;
	}

	@JsonProperty("endDateOfService")
	public String getEndDateOfService() {
		return endDateOfService;
	}

	@JsonProperty("endDateOfService")
	public void setEndDateOfService(String endDateOfService) {
		this.endDateOfService = endDateOfService;
	}

	@JsonProperty("trackingNumber")
	public String getTrackingNumber() {
		return trackingNumber;
	}

	@JsonProperty("trackingNumber")
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	@JsonProperty("submittedAmount")
	public String getSubmittedAmount() {
		return submittedAmount;
	}

	@JsonProperty("submittedAmount")
	public void setSubmittedAmount(String submittedAmount) {
		this.submittedAmount = submittedAmount;
	}

	@JsonProperty("tradingPartnerClaimNumber")
	public String getTradingPartnerClaimNumber() {
		return tradingPartnerClaimNumber;
	}

	@JsonProperty("tradingPartnerClaimNumber")
	public void setTradingPartnerClaimNumber(String tradingPartnerClaimNumber) {
		this.tradingPartnerClaimNumber = tradingPartnerClaimNumber;
	}

	@JsonProperty("locationIdentifier")
	public String getLocationIdentifier() {
		return locationIdentifier;
	}

	@JsonProperty("locationIdentifier")
	public void setLocationIdentifier(String locationIdentifier) {
		this.locationIdentifier = locationIdentifier;
	}

	@JsonProperty("billingType")
	public String getBillingType() {
		return billingType;
	}

	@JsonProperty("billingType")
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@JsonProperty("patientAccountNumber")
	public String getPatientAccountNumber() {
		return patientAccountNumber;
	}

	@JsonProperty("patientAccountNumber")
	public void setPatientAccountNumber(String patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}

	@JsonProperty("pharmacyPrescriptionNumber")
	public String getPharmacyPrescriptionNumber() {
		return pharmacyPrescriptionNumber;
	}

	@JsonProperty("pharmacyPrescriptionNumber")
	public void setPharmacyPrescriptionNumber(String pharmacyPrescriptionNumber) {
		this.pharmacyPrescriptionNumber = pharmacyPrescriptionNumber;
	}

	@JsonProperty("clearingHouseClaimNumber")
	public String getClearingHouseClaimNumber() {
		return clearingHouseClaimNumber;
	}

	@JsonProperty("clearingHouseClaimNumber")
	public void setClearingHouseClaimNumber(String clearingHouseClaimNumber) {
		this.clearingHouseClaimNumber = clearingHouseClaimNumber;
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