package com.sunknowledge.dme.rcm.pojo.primary;

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
import com.sunknowledge.dme.rcm.pojo.common.HealthCareCodeInformation;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "claimFilingCode", "patientControlNumber", "claimChargeAmount", "placeOfServiceCode",
		"claimFrequencyCode", "signatureIndicator", "planParticipationCode", "benefitsAssignmentCertificationIndicator",
		"releaseInformationCode", "healthCareCodeInformation", "serviceLines" })
@Generated("jsonschema2pojo")
public class ClaimInformation {

	@JsonProperty("claimFilingCode")
	private String claimFilingCode;
	@JsonProperty("patientControlNumber")
	private String patientControlNumber;
	@JsonProperty("claimChargeAmount")
	private String claimChargeAmount;
	@JsonProperty("placeOfServiceCode")
	private String placeOfServiceCode;
	@JsonProperty("claimFrequencyCode")
	private String claimFrequencyCode;
	@JsonProperty("signatureIndicator")
	private String signatureIndicator;
	@JsonProperty("planParticipationCode")
	private String planParticipationCode;
	@JsonProperty("benefitsAssignmentCertificationIndicator")
	private String benefitsAssignmentCertificationIndicator;
	@JsonProperty("releaseInformationCode")
	private String releaseInformationCode;
	@JsonProperty("healthCareCodeInformation")
	private List<HealthCareCodeInformation> healthCareCodeInformation = null;
	@JsonProperty("serviceLines")
	private List<ServiceLine> serviceLines = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("claimFilingCode")
	public String getClaimFilingCode() {
		return claimFilingCode;
	}

	@JsonProperty("claimFilingCode")
	public void setClaimFilingCode(String claimFilingCode) {
		this.claimFilingCode = claimFilingCode;
	}

	@JsonProperty("patientControlNumber")
	public String getPatientControlNumber() {
		return patientControlNumber;
	}

	@JsonProperty("patientControlNumber")
	public void setPatientControlNumber(String patientControlNumber) {
		this.patientControlNumber = patientControlNumber;
	}

	@JsonProperty("claimChargeAmount")
	public String getClaimChargeAmount() {
		return claimChargeAmount;
	}

	@JsonProperty("claimChargeAmount")
	public void setClaimChargeAmount(String claimChargeAmount) {
		this.claimChargeAmount = claimChargeAmount;
	}

	@JsonProperty("placeOfServiceCode")
	public String getPlaceOfServiceCode() {
		return placeOfServiceCode;
	}

	@JsonProperty("placeOfServiceCode")
	public void setPlaceOfServiceCode(String placeOfServiceCode) {
		this.placeOfServiceCode = placeOfServiceCode;
	}

	@JsonProperty("claimFrequencyCode")
	public String getClaimFrequencyCode() {
		return claimFrequencyCode;
	}

	@JsonProperty("claimFrequencyCode")
	public void setClaimFrequencyCode(String claimFrequencyCode) {
		this.claimFrequencyCode = claimFrequencyCode;
	}

	@JsonProperty("signatureIndicator")
	public String getSignatureIndicator() {
		return signatureIndicator;
	}

	@JsonProperty("signatureIndicator")
	public void setSignatureIndicator(String signatureIndicator) {
		this.signatureIndicator = signatureIndicator;
	}

	@JsonProperty("planParticipationCode")
	public String getPlanParticipationCode() {
		return planParticipationCode;
	}

	@JsonProperty("planParticipationCode")
	public void setPlanParticipationCode(String planParticipationCode) {
		this.planParticipationCode = planParticipationCode;
	}

	@JsonProperty("benefitsAssignmentCertificationIndicator")
	public String getBenefitsAssignmentCertificationIndicator() {
		return benefitsAssignmentCertificationIndicator;
	}

	@JsonProperty("benefitsAssignmentCertificationIndicator")
	public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
		this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
	}

	@JsonProperty("releaseInformationCode")
	public String getReleaseInformationCode() {
		return releaseInformationCode;
	}

	@JsonProperty("releaseInformationCode")
	public void setReleaseInformationCode(String releaseInformationCode) {
		this.releaseInformationCode = releaseInformationCode;
	}

	@JsonProperty("healthCareCodeInformation")
	public List<HealthCareCodeInformation> getHealthCareCodeInformation() {
		return healthCareCodeInformation;
	}

	@JsonProperty("healthCareCodeInformation")
	public void setHealthCareCodeInformation(List<HealthCareCodeInformation> healthCareCodeInformation) {
		this.healthCareCodeInformation = healthCareCodeInformation;
	}

	@JsonProperty("serviceLines")
	public List<ServiceLine> getServiceLines() {
		return serviceLines;
	}

	@JsonProperty("serviceLines")
	public void setServiceLines(List<ServiceLine> serviceLines) {
		this.serviceLines = serviceLines;
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