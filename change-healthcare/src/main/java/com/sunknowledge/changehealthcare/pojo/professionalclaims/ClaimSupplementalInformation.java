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
@JsonPropertyOrder({ "cliaNumber", "reportInformation", "priorAuthorizationNumber", "claimNumber", "referralNumber",
		"claimControlNumber", "repricedClaimNumber", "adjustedRepricedClaimNumber",
		"investigationalDeviceExemptionNumber", "mammographyCertificationNumber", "medicalRecordNumber",
		"demoProjectIdentifier", "carePlanOversightNumber", "medicareCrossoverReferenceId",
		"serviceAuthorizationExceptionCode" })
@Generated("jsonschema2pojo")
public class ClaimSupplementalInformation {

	@JsonProperty("cliaNumber")
	private String cliaNumber;
	@JsonProperty("reportInformation")
	private ReportInformation reportInformation;
	@JsonProperty("priorAuthorizationNumber")
	private String priorAuthorizationNumber;
	@JsonProperty("claimNumber")
	private String claimNumber;
	@JsonProperty("referralNumber")
	private String referralNumber;
	@JsonProperty("claimControlNumber")
	private String claimControlNumber;
	@JsonProperty("repricedClaimNumber")
	private String repricedClaimNumber;
	@JsonProperty("adjustedRepricedClaimNumber")
	private String adjustedRepricedClaimNumber;
	@JsonProperty("investigationalDeviceExemptionNumber")
	private String investigationalDeviceExemptionNumber;
	@JsonProperty("mammographyCertificationNumber")
	private String mammographyCertificationNumber;
	@JsonProperty("medicalRecordNumber")
	private String medicalRecordNumber;
	@JsonProperty("demoProjectIdentifier")
	private String demoProjectIdentifier;
	@JsonProperty("carePlanOversightNumber")
	private String carePlanOversightNumber;
	@JsonProperty("medicareCrossoverReferenceId")
	private String medicareCrossoverReferenceId;
	@JsonProperty("serviceAuthorizationExceptionCode")
	private String serviceAuthorizationExceptionCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("cliaNumber")
	public String getCliaNumber() {
		return cliaNumber;
	}

	@JsonProperty("cliaNumber")
	public void setCliaNumber(String cliaNumber) {
		this.cliaNumber = cliaNumber;
	}

	@JsonProperty("reportInformation")
	public ReportInformation getReportInformation() {
		return reportInformation;
	}

	@JsonProperty("reportInformation")
	public void setReportInformation(ReportInformation reportInformation) {
		this.reportInformation = reportInformation;
	}

	@JsonProperty("priorAuthorizationNumber")
	public String getPriorAuthorizationNumber() {
		return priorAuthorizationNumber;
	}

	@JsonProperty("priorAuthorizationNumber")
	public void setPriorAuthorizationNumber(String priorAuthorizationNumber) {
		this.priorAuthorizationNumber = priorAuthorizationNumber;
	}

	@JsonProperty("claimNumber")
	public String getClaimNumber() {
		return claimNumber;
	}

	@JsonProperty("claimNumber")
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	@JsonProperty("referralNumber")
	public String getReferralNumber() {
		return referralNumber;
	}

	@JsonProperty("referralNumber")
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}

	@JsonProperty("claimControlNumber")
	public String getClaimControlNumber() {
		return claimControlNumber;
	}

	@JsonProperty("claimControlNumber")
	public void setClaimControlNumber(String claimControlNumber) {
		this.claimControlNumber = claimControlNumber;
	}

	@JsonProperty("repricedClaimNumber")
	public String getRepricedClaimNumber() {
		return repricedClaimNumber;
	}

	@JsonProperty("repricedClaimNumber")
	public void setRepricedClaimNumber(String repricedClaimNumber) {
		this.repricedClaimNumber = repricedClaimNumber;
	}

	@JsonProperty("adjustedRepricedClaimNumber")
	public String getAdjustedRepricedClaimNumber() {
		return adjustedRepricedClaimNumber;
	}

	@JsonProperty("adjustedRepricedClaimNumber")
	public void setAdjustedRepricedClaimNumber(String adjustedRepricedClaimNumber) {
		this.adjustedRepricedClaimNumber = adjustedRepricedClaimNumber;
	}

	@JsonProperty("investigationalDeviceExemptionNumber")
	public String getInvestigationalDeviceExemptionNumber() {
		return investigationalDeviceExemptionNumber;
	}

	@JsonProperty("investigationalDeviceExemptionNumber")
	public void setInvestigationalDeviceExemptionNumber(String investigationalDeviceExemptionNumber) {
		this.investigationalDeviceExemptionNumber = investigationalDeviceExemptionNumber;
	}

	@JsonProperty("mammographyCertificationNumber")
	public String getMammographyCertificationNumber() {
		return mammographyCertificationNumber;
	}

	@JsonProperty("mammographyCertificationNumber")
	public void setMammographyCertificationNumber(String mammographyCertificationNumber) {
		this.mammographyCertificationNumber = mammographyCertificationNumber;
	}

	@JsonProperty("medicalRecordNumber")
	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	@JsonProperty("medicalRecordNumber")
	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	@JsonProperty("demoProjectIdentifier")
	public String getDemoProjectIdentifier() {
		return demoProjectIdentifier;
	}

	@JsonProperty("demoProjectIdentifier")
	public void setDemoProjectIdentifier(String demoProjectIdentifier) {
		this.demoProjectIdentifier = demoProjectIdentifier;
	}

	@JsonProperty("carePlanOversightNumber")
	public String getCarePlanOversightNumber() {
		return carePlanOversightNumber;
	}

	@JsonProperty("carePlanOversightNumber")
	public void setCarePlanOversightNumber(String carePlanOversightNumber) {
		this.carePlanOversightNumber = carePlanOversightNumber;
	}

	@JsonProperty("medicareCrossoverReferenceId")
	public String getMedicareCrossoverReferenceId() {
		return medicareCrossoverReferenceId;
	}

	@JsonProperty("medicareCrossoverReferenceId")
	public void setMedicareCrossoverReferenceId(String medicareCrossoverReferenceId) {
		this.medicareCrossoverReferenceId = medicareCrossoverReferenceId;
	}

	@JsonProperty("serviceAuthorizationExceptionCode")
	public String getServiceAuthorizationExceptionCode() {
		return serviceAuthorizationExceptionCode;
	}

	@JsonProperty("serviceAuthorizationExceptionCode")
	public void setServiceAuthorizationExceptionCode(String serviceAuthorizationExceptionCode) {
		this.serviceAuthorizationExceptionCode = serviceAuthorizationExceptionCode;
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