package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "claimLevelAdjustments", "medicareOutpatientAdjudication", "otherSubscriberName", "otherPayerName",
		"otherPayerReferringProvider", "otherPayerRenderingProvider", "otherPayerServiceFacilityLocation",
		"otherPayerSupervisingProvider", "otherPayerBillingProvider", "payerPaidAmount",
		"paymentResponsibilityLevelCode", "individualRelationshipCode", "insuranceGroupOrPolicyNumber",
		"otherInsuredGroupName", "insuranceTypeCode", "claimFilingIndicatorCode", "nonCoveredChargeAmount",
		"remainingPatientLiability", "benefitsAssignmentCertificationIndicator", "patientSignatureGeneratedForPatient",
		"releaseOfInformationCode" })
@Generated("jsonschema2pojo")
public class OtherSubscriberInformation {

	@JsonProperty("claimLevelAdjustments")
	private List<ClaimLevelAdjustment> claimLevelAdjustments = null;
	@JsonProperty("medicareOutpatientAdjudication")
	private MedicareOutpatientAdjudication medicareOutpatientAdjudication;
	@JsonProperty("otherSubscriberName")
	private OtherSubscriberName otherSubscriberName;
	@JsonProperty("otherPayerName")
	private OtherPayerName otherPayerName;
	@JsonProperty("otherPayerReferringProvider")
	private List<OtherPayerReferringProvider> otherPayerReferringProvider = null;
	@JsonProperty("otherPayerRenderingProvider")
	private List<OtherPayerRenderingProvider> otherPayerRenderingProvider = null;
	@JsonProperty("otherPayerServiceFacilityLocation")
	private List<OtherPayerServiceFacilityLocation> otherPayerServiceFacilityLocation = null;
	@JsonProperty("otherPayerSupervisingProvider")
	private List<OtherPayerSupervisingProvider> otherPayerSupervisingProvider = null;
	@JsonProperty("otherPayerBillingProvider")
	private List<OtherPayerBillingProvider> otherPayerBillingProvider = null;
	@JsonProperty("payerPaidAmount")
	private String payerPaidAmount;
	@JsonProperty("paymentResponsibilityLevelCode")
	private String paymentResponsibilityLevelCode;
	@JsonProperty("individualRelationshipCode")
	private String individualRelationshipCode;
	@JsonProperty("insuranceGroupOrPolicyNumber")
	private String insuranceGroupOrPolicyNumber;
	@JsonProperty("otherInsuredGroupName")
	private String otherInsuredGroupName;
	@JsonProperty("insuranceTypeCode")
	private String insuranceTypeCode;
	@JsonProperty("claimFilingIndicatorCode")
	private String claimFilingIndicatorCode;
	@JsonProperty("nonCoveredChargeAmount")
	private String nonCoveredChargeAmount;
	@JsonProperty("remainingPatientLiability")
	private String remainingPatientLiability;
	@JsonProperty("benefitsAssignmentCertificationIndicator")
	private String benefitsAssignmentCertificationIndicator;
	@JsonProperty("patientSignatureGeneratedForPatient")
	private Boolean patientSignatureGeneratedForPatient;
	@JsonProperty("releaseOfInformationCode")
	private String releaseOfInformationCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("claimLevelAdjustments")
	public List<ClaimLevelAdjustment> getClaimLevelAdjustments() {
		return claimLevelAdjustments;
	}

	@JsonProperty("claimLevelAdjustments")
	public void setClaimLevelAdjustments(List<ClaimLevelAdjustment> claimLevelAdjustments) {
		this.claimLevelAdjustments = claimLevelAdjustments;
	}

	@JsonProperty("medicareOutpatientAdjudication")
	public MedicareOutpatientAdjudication getMedicareOutpatientAdjudication() {
		return medicareOutpatientAdjudication;
	}

	@JsonProperty("medicareOutpatientAdjudication")
	public void setMedicareOutpatientAdjudication(MedicareOutpatientAdjudication medicareOutpatientAdjudication) {
		this.medicareOutpatientAdjudication = medicareOutpatientAdjudication;
	}

	@JsonProperty("otherSubscriberName")
	public OtherSubscriberName getOtherSubscriberName() {
		return otherSubscriberName;
	}

	@JsonProperty("otherSubscriberName")
	public void setOtherSubscriberName(OtherSubscriberName otherSubscriberName) {
		this.otherSubscriberName = otherSubscriberName;
	}

	@JsonProperty("otherPayerName")
	public OtherPayerName getOtherPayerName() {
		return otherPayerName;
	}

	@JsonProperty("otherPayerName")
	public void setOtherPayerName(OtherPayerName otherPayerName) {
		this.otherPayerName = otherPayerName;
	}

	@JsonProperty("otherPayerReferringProvider")
	public List<OtherPayerReferringProvider> getOtherPayerReferringProvider() {
		return otherPayerReferringProvider;
	}

	@JsonProperty("otherPayerReferringProvider")
	public void setOtherPayerReferringProvider(List<OtherPayerReferringProvider> otherPayerReferringProvider) {
		this.otherPayerReferringProvider = otherPayerReferringProvider;
	}

	@JsonProperty("otherPayerRenderingProvider")
	public List<OtherPayerRenderingProvider> getOtherPayerRenderingProvider() {
		return otherPayerRenderingProvider;
	}

	@JsonProperty("otherPayerRenderingProvider")
	public void setOtherPayerRenderingProvider(List<OtherPayerRenderingProvider> otherPayerRenderingProvider) {
		this.otherPayerRenderingProvider = otherPayerRenderingProvider;
	}

	@JsonProperty("otherPayerServiceFacilityLocation")
	public List<OtherPayerServiceFacilityLocation> getOtherPayerServiceFacilityLocation() {
		return otherPayerServiceFacilityLocation;
	}

	@JsonProperty("otherPayerServiceFacilityLocation")
	public void setOtherPayerServiceFacilityLocation(
			List<OtherPayerServiceFacilityLocation> otherPayerServiceFacilityLocation) {
		this.otherPayerServiceFacilityLocation = otherPayerServiceFacilityLocation;
	}

	@JsonProperty("otherPayerSupervisingProvider")
	public List<OtherPayerSupervisingProvider> getOtherPayerSupervisingProvider() {
		return otherPayerSupervisingProvider;
	}

	@JsonProperty("otherPayerSupervisingProvider")
	public void setOtherPayerSupervisingProvider(List<OtherPayerSupervisingProvider> otherPayerSupervisingProvider) {
		this.otherPayerSupervisingProvider = otherPayerSupervisingProvider;
	}

	@JsonProperty("otherPayerBillingProvider")
	public List<OtherPayerBillingProvider> getOtherPayerBillingProvider() {
		return otherPayerBillingProvider;
	}

	@JsonProperty("otherPayerBillingProvider")
	public void setOtherPayerBillingProvider(List<OtherPayerBillingProvider> otherPayerBillingProvider) {
		this.otherPayerBillingProvider = otherPayerBillingProvider;
	}

	@JsonProperty("payerPaidAmount")
	public String getPayerPaidAmount() {
		return payerPaidAmount;
	}

	@JsonProperty("payerPaidAmount")
	public void setPayerPaidAmount(String payerPaidAmount) {
		this.payerPaidAmount = payerPaidAmount;
	}

	@JsonProperty("paymentResponsibilityLevelCode")
	public String getPaymentResponsibilityLevelCode() {
		return paymentResponsibilityLevelCode;
	}

	@JsonProperty("paymentResponsibilityLevelCode")
	public void setPaymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
		this.paymentResponsibilityLevelCode = paymentResponsibilityLevelCode;
	}

	@JsonProperty("individualRelationshipCode")
	public String getIndividualRelationshipCode() {
		return individualRelationshipCode;
	}

	@JsonProperty("individualRelationshipCode")
	public void setIndividualRelationshipCode(String individualRelationshipCode) {
		this.individualRelationshipCode = individualRelationshipCode;
	}

	@JsonProperty("insuranceGroupOrPolicyNumber")
	public String getInsuranceGroupOrPolicyNumber() {
		return insuranceGroupOrPolicyNumber;
	}

	@JsonProperty("insuranceGroupOrPolicyNumber")
	public void setInsuranceGroupOrPolicyNumber(String insuranceGroupOrPolicyNumber) {
		this.insuranceGroupOrPolicyNumber = insuranceGroupOrPolicyNumber;
	}

	@JsonProperty("otherInsuredGroupName")
	public String getOtherInsuredGroupName() {
		return otherInsuredGroupName;
	}

	@JsonProperty("otherInsuredGroupName")
	public void setOtherInsuredGroupName(String otherInsuredGroupName) {
		this.otherInsuredGroupName = otherInsuredGroupName;
	}

	@JsonProperty("insuranceTypeCode")
	public String getInsuranceTypeCode() {
		return insuranceTypeCode;
	}

	@JsonProperty("insuranceTypeCode")
	public void setInsuranceTypeCode(String insuranceTypeCode) {
		this.insuranceTypeCode = insuranceTypeCode;
	}

	@JsonProperty("claimFilingIndicatorCode")
	public String getClaimFilingIndicatorCode() {
		return claimFilingIndicatorCode;
	}

	@JsonProperty("claimFilingIndicatorCode")
	public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
		this.claimFilingIndicatorCode = claimFilingIndicatorCode;
	}

	@JsonProperty("nonCoveredChargeAmount")
	public String getNonCoveredChargeAmount() {
		return nonCoveredChargeAmount;
	}

	@JsonProperty("nonCoveredChargeAmount")
	public void setNonCoveredChargeAmount(String nonCoveredChargeAmount) {
		this.nonCoveredChargeAmount = nonCoveredChargeAmount;
	}

	@JsonProperty("remainingPatientLiability")
	public String getRemainingPatientLiability() {
		return remainingPatientLiability;
	}

	@JsonProperty("remainingPatientLiability")
	public void setRemainingPatientLiability(String remainingPatientLiability) {
		this.remainingPatientLiability = remainingPatientLiability;
	}

	@JsonProperty("benefitsAssignmentCertificationIndicator")
	public String getBenefitsAssignmentCertificationIndicator() {
		return benefitsAssignmentCertificationIndicator;
	}

	@JsonProperty("benefitsAssignmentCertificationIndicator")
	public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
		this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
	}

	@JsonProperty("patientSignatureGeneratedForPatient")
	public Boolean getPatientSignatureGeneratedForPatient() {
		return patientSignatureGeneratedForPatient;
	}

	@JsonProperty("patientSignatureGeneratedForPatient")
	public void setPatientSignatureGeneratedForPatient(Boolean patientSignatureGeneratedForPatient) {
		this.patientSignatureGeneratedForPatient = patientSignatureGeneratedForPatient;
	}

	@JsonProperty("releaseOfInformationCode")
	public String getReleaseOfInformationCode() {
		return releaseOfInformationCode;
	}

	@JsonProperty("releaseOfInformationCode")
	public void setReleaseOfInformationCode(String releaseOfInformationCode) {
		this.releaseOfInformationCode = releaseOfInformationCode;
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