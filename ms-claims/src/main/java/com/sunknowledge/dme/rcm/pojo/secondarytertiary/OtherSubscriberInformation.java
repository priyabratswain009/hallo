package com.sunknowledge.dme.rcm.pojo.secondarytertiary;

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
@JsonPropertyOrder({ "paymentResponsibilityLevelCode", "individualRelationshipCode", "claimFilingIndicatorCode",
		"payerPaidAmount", "benefitsAssignmentCertificationIndicator", "releaseOfInformationCode",
		"otherSubscriberName", "otherPayerName" })
@Generated("jsonschema2pojo")
public class OtherSubscriberInformation {

	@JsonProperty("paymentResponsibilityLevelCode")
	private String paymentResponsibilityLevelCode;
	@JsonProperty("individualRelationshipCode")
	private String individualRelationshipCode;
	@JsonProperty("claimFilingIndicatorCode")
	private String claimFilingIndicatorCode;
	@JsonProperty("payerPaidAmount")
	private String payerPaidAmount;
	@JsonProperty("benefitsAssignmentCertificationIndicator")
	private String benefitsAssignmentCertificationIndicator;
	@JsonProperty("releaseOfInformationCode")
	private String releaseOfInformationCode;
	@JsonProperty("otherSubscriberName")
	private OtherSubscriberName otherSubscriberName;
	@JsonProperty("otherPayerName")
	private OtherPayerName otherPayerName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("claimFilingIndicatorCode")
	public String getClaimFilingIndicatorCode() {
		return claimFilingIndicatorCode;
	}

	@JsonProperty("claimFilingIndicatorCode")
	public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
		this.claimFilingIndicatorCode = claimFilingIndicatorCode;
	}

	@JsonProperty("payerPaidAmount")
	public String getPayerPaidAmount() {
		return payerPaidAmount;
	}

	@JsonProperty("payerPaidAmount")
	public void setPayerPaidAmount(String payerPaidAmount) {
		this.payerPaidAmount = payerPaidAmount;
	}

	@JsonProperty("benefitsAssignmentCertificationIndicator")
	public String getBenefitsAssignmentCertificationIndicator() {
		return benefitsAssignmentCertificationIndicator;
	}

	@JsonProperty("benefitsAssignmentCertificationIndicator")
	public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
		this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
	}

	@JsonProperty("releaseOfInformationCode")
	public String getReleaseOfInformationCode() {
		return releaseOfInformationCode;
	}

	@JsonProperty("releaseOfInformationCode")
	public void setReleaseOfInformationCode(String releaseOfInformationCode) {
		this.releaseOfInformationCode = releaseOfInformationCode;
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}