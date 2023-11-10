package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "pricingMethodologyCode", "repricedAllowedAmount", "repricedSavingAmount",
		"repricingOrganizationIdentifier", "repricingPerDiemOrFlatRateAmoung",
		"repricedApprovedAmbulatoryPatientGroupCode", "repricedApprovedAmbulatoryPatientGroupAmount",
		"rejectReasonCode", "policyComplianceCode", "exceptionCode" })
@Generated("jsonschema2pojo")
public class ClaimPricingRepricingInformation {

	@JsonProperty("pricingMethodologyCode")
	private String pricingMethodologyCode;
	@JsonProperty("repricedAllowedAmount")
	private String repricedAllowedAmount;
	@JsonProperty("repricedSavingAmount")
	private String repricedSavingAmount;
	@JsonProperty("repricingOrganizationIdentifier")
	private String repricingOrganizationIdentifier;
	@JsonProperty("repricingPerDiemOrFlatRateAmoung")
	private String repricingPerDiemOrFlatRateAmoung;
	@JsonProperty("repricedApprovedAmbulatoryPatientGroupCode")
	private String repricedApprovedAmbulatoryPatientGroupCode;
	@JsonProperty("repricedApprovedAmbulatoryPatientGroupAmount")
	private String repricedApprovedAmbulatoryPatientGroupAmount;
	@JsonProperty("rejectReasonCode")
	private String rejectReasonCode;
	@JsonProperty("policyComplianceCode")
	private String policyComplianceCode;
	@JsonProperty("exceptionCode")
	private String exceptionCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("pricingMethodologyCode")
	public String getPricingMethodologyCode() {
		return pricingMethodologyCode;
	}

	@JsonProperty("pricingMethodologyCode")
	public void setPricingMethodologyCode(String pricingMethodologyCode) {
		this.pricingMethodologyCode = pricingMethodologyCode;
	}

	@JsonProperty("repricedAllowedAmount")
	public String getRepricedAllowedAmount() {
		return repricedAllowedAmount;
	}

	@JsonProperty("repricedAllowedAmount")
	public void setRepricedAllowedAmount(String repricedAllowedAmount) {
		this.repricedAllowedAmount = repricedAllowedAmount;
	}

	@JsonProperty("repricedSavingAmount")
	public String getRepricedSavingAmount() {
		return repricedSavingAmount;
	}

	@JsonProperty("repricedSavingAmount")
	public void setRepricedSavingAmount(String repricedSavingAmount) {
		this.repricedSavingAmount = repricedSavingAmount;
	}

	@JsonProperty("repricingOrganizationIdentifier")
	public String getRepricingOrganizationIdentifier() {
		return repricingOrganizationIdentifier;
	}

	@JsonProperty("repricingOrganizationIdentifier")
	public void setRepricingOrganizationIdentifier(String repricingOrganizationIdentifier) {
		this.repricingOrganizationIdentifier = repricingOrganizationIdentifier;
	}

	@JsonProperty("repricingPerDiemOrFlatRateAmoung")
	public String getRepricingPerDiemOrFlatRateAmoung() {
		return repricingPerDiemOrFlatRateAmoung;
	}

	@JsonProperty("repricingPerDiemOrFlatRateAmoung")
	public void setRepricingPerDiemOrFlatRateAmoung(String repricingPerDiemOrFlatRateAmoung) {
		this.repricingPerDiemOrFlatRateAmoung = repricingPerDiemOrFlatRateAmoung;
	}

	@JsonProperty("repricedApprovedAmbulatoryPatientGroupCode")
	public String getRepricedApprovedAmbulatoryPatientGroupCode() {
		return repricedApprovedAmbulatoryPatientGroupCode;
	}

	@JsonProperty("repricedApprovedAmbulatoryPatientGroupCode")
	public void setRepricedApprovedAmbulatoryPatientGroupCode(String repricedApprovedAmbulatoryPatientGroupCode) {
		this.repricedApprovedAmbulatoryPatientGroupCode = repricedApprovedAmbulatoryPatientGroupCode;
	}

	@JsonProperty("repricedApprovedAmbulatoryPatientGroupAmount")
	public String getRepricedApprovedAmbulatoryPatientGroupAmount() {
		return repricedApprovedAmbulatoryPatientGroupAmount;
	}

	@JsonProperty("repricedApprovedAmbulatoryPatientGroupAmount")
	public void setRepricedApprovedAmbulatoryPatientGroupAmount(String repricedApprovedAmbulatoryPatientGroupAmount) {
		this.repricedApprovedAmbulatoryPatientGroupAmount = repricedApprovedAmbulatoryPatientGroupAmount;
	}

	@JsonProperty("rejectReasonCode")
	public String getRejectReasonCode() {
		return rejectReasonCode;
	}

	@JsonProperty("rejectReasonCode")
	public void setRejectReasonCode(String rejectReasonCode) {
		this.rejectReasonCode = rejectReasonCode;
	}

	@JsonProperty("policyComplianceCode")
	public String getPolicyComplianceCode() {
		return policyComplianceCode;
	}

	@JsonProperty("policyComplianceCode")
	public void setPolicyComplianceCode(String policyComplianceCode) {
		this.policyComplianceCode = policyComplianceCode;
	}

	@JsonProperty("exceptionCode")
	public String getExceptionCode() {
		return exceptionCode;
	}

	@JsonProperty("exceptionCode")
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
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