package com.sunknowledge.dme.rcm.domain.elligibility;

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
@JsonPropertyOrder({ "memberId", "firstName", "lastName", "idCard", "ssn", "policyNumber", "patientAccountNumber",
		"healthInsuranceClaimNumber", "agencyClaimNumber", "address", "providerCode",
		"referenceIdentificationQualifier", "providerIdentifier", "dateOfBirth", "gender", "healthCareCodeInformation",
		"idCardIssueDate", "planIssueDate" })
@Generated("jsonschema2pojo")
public class SubscriberIP {

	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("idCard")
	private String idCard;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("policyNumber")
	private String policyNumber;
	@JsonProperty("patientAccountNumber")
	private String patientAccountNumber;
	@JsonProperty("healthInsuranceClaimNumber")
	private String healthInsuranceClaimNumber;
	@JsonProperty("agencyClaimNumber")
	private String agencyClaimNumber;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("providerCode")
	private String providerCode;
	@JsonProperty("referenceIdentificationQualifier")
	private String referenceIdentificationQualifier;
	@JsonProperty("providerIdentifier")
	private String providerIdentifier;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("healthCareCodeInformation")
	private List<HealthCareCodeInformation> healthCareCodeInformation = null;
	@JsonProperty("idCardIssueDate")
	private String idCardIssueDate;
	@JsonProperty("planIssueDate")
	private String planIssueDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("idCard")
	public String getIdCard() {
		return idCard;
	}

	@JsonProperty("idCard")
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("policyNumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("policyNumber")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("patientAccountNumber")
	public String getPatientAccountNumber() {
		return patientAccountNumber;
	}

	@JsonProperty("patientAccountNumber")
	public void setPatientAccountNumber(String patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}

	@JsonProperty("healthInsuranceClaimNumber")
	public String getHealthInsuranceClaimNumber() {
		return healthInsuranceClaimNumber;
	}

	@JsonProperty("healthInsuranceClaimNumber")
	public void setHealthInsuranceClaimNumber(String healthInsuranceClaimNumber) {
		this.healthInsuranceClaimNumber = healthInsuranceClaimNumber;
	}

	@JsonProperty("agencyClaimNumber")
	public String getAgencyClaimNumber() {
		return agencyClaimNumber;
	}

	@JsonProperty("agencyClaimNumber")
	public void setAgencyClaimNumber(String agencyClaimNumber) {
		this.agencyClaimNumber = agencyClaimNumber;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("providerCode")
	public String getProviderCode() {
		return providerCode;
	}

	@JsonProperty("providerCode")
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	@JsonProperty("referenceIdentificationQualifier")
	public String getReferenceIdentificationQualifier() {
		return referenceIdentificationQualifier;
	}

	@JsonProperty("referenceIdentificationQualifier")
	public void setReferenceIdentificationQualifier(String referenceIdentificationQualifier) {
		this.referenceIdentificationQualifier = referenceIdentificationQualifier;
	}

	@JsonProperty("providerIdentifier")
	public String getProviderIdentifier() {
		return providerIdentifier;
	}

	@JsonProperty("providerIdentifier")
	public void setProviderIdentifier(String providerIdentifier) {
		this.providerIdentifier = providerIdentifier;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("healthCareCodeInformation")
	public List<HealthCareCodeInformation> getHealthCareCodeInformation() {
		return healthCareCodeInformation;
	}

	@JsonProperty("healthCareCodeInformation")
	public void setHealthCareCodeInformation(List<HealthCareCodeInformation> healthCareCodeInformation) {
		this.healthCareCodeInformation = healthCareCodeInformation;
	}

	@JsonProperty("idCardIssueDate")
	public String getIdCardIssueDate() {
		return idCardIssueDate;
	}

	@JsonProperty("idCardIssueDate")
	public void setIdCardIssueDate(String idCardIssueDate) {
		this.idCardIssueDate = idCardIssueDate;
	}

	@JsonProperty("planIssueDate")
	public String getPlanIssueDate() {
		return planIssueDate;
	}

	@JsonProperty("planIssueDate")
	public void setPlanIssueDate(String planIssueDate) {
		this.planIssueDate = planIssueDate;
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