package com.sunknowledge.changehealthcare.pojo.elligibility;

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
@JsonPropertyOrder({ "individualRelationshipCode", "birthSequenceNumber", "issueNumber", "eligibilityCategory",
		"firstName", "lastName", "middleName", "suffix", "dateOfBirth", "gender", "ssn", "groupNumber", "idCard",
		"providerCode", "referenceIdentificationQualifier", "providerIdentifier", "idCardIssueDate",
		"beginningCardIssueDate", "endCardIssueDate", "memberId", "planIssueDate", "beginningPlanIssueDate",
		"endPlanIssueDate", "planNumber", "policyNumber", "memberIdentificationNumber", "contractNumber",
		"medicalRecordIdentificationNumber", "patientAccountNumber", "healthInsuranceClaimNumber",
		"healthCareCodeInformation", "address", "additionalIdentification" })
@Generated("jsonschema2pojo")
public class Dependent {

	@JsonProperty("individualRelationshipCode")
	private String individualRelationshipCode;
	@JsonProperty("birthSequenceNumber")
	private String birthSequenceNumber;
	@JsonProperty("issueNumber")
	private String issueNumber;
	@JsonProperty("eligibilityCategory")
	private String eligibilityCategory;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("groupNumber")
	private String groupNumber;
	@JsonProperty("idCard")
	private String idCard;
	@JsonProperty("providerCode")
	private String providerCode;
	@JsonProperty("referenceIdentificationQualifier")
	private String referenceIdentificationQualifier;
	@JsonProperty("providerIdentifier")
	private String providerIdentifier;
	@JsonProperty("idCardIssueDate")
	private String idCardIssueDate;
	@JsonProperty("beginningCardIssueDate")
	private String beginningCardIssueDate;
	@JsonProperty("endCardIssueDate")
	private String endCardIssueDate;
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("planIssueDate")
	private String planIssueDate;
	@JsonProperty("beginningPlanIssueDate")
	private String beginningPlanIssueDate;
	@JsonProperty("endPlanIssueDate")
	private String endPlanIssueDate;
	@JsonProperty("planNumber")
	private String planNumber;
	@JsonProperty("policyNumber")
	private String policyNumber;
	@JsonProperty("memberIdentificationNumber")
	private String memberIdentificationNumber;
	@JsonProperty("contractNumber")
	private String contractNumber;
	@JsonProperty("medicalRecordIdentificationNumber")
	private String medicalRecordIdentificationNumber;
	@JsonProperty("patientAccountNumber")
	private String patientAccountNumber;
	@JsonProperty("healthInsuranceClaimNumber")
	private String healthInsuranceClaimNumber;
	@JsonProperty("healthCareCodeInformation")
	private List<HealthCareCodeInformation__1> healthCareCodeInformation = null;
	@JsonProperty("address")
	private Address__3 address;
	@JsonProperty("additionalIdentification")
	private AdditionalIdentification additionalIdentification;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("individualRelationshipCode")
	public String getIndividualRelationshipCode() {
		return individualRelationshipCode;
	}

	@JsonProperty("individualRelationshipCode")
	public void setIndividualRelationshipCode(String individualRelationshipCode) {
		this.individualRelationshipCode = individualRelationshipCode;
	}

	@JsonProperty("birthSequenceNumber")
	public String getBirthSequenceNumber() {
		return birthSequenceNumber;
	}

	@JsonProperty("birthSequenceNumber")
	public void setBirthSequenceNumber(String birthSequenceNumber) {
		this.birthSequenceNumber = birthSequenceNumber;
	}

	@JsonProperty("issueNumber")
	public String getIssueNumber() {
		return issueNumber;
	}

	@JsonProperty("issueNumber")
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	@JsonProperty("eligibilityCategory")
	public String getEligibilityCategory() {
		return eligibilityCategory;
	}

	@JsonProperty("eligibilityCategory")
	public void setEligibilityCategory(String eligibilityCategory) {
		this.eligibilityCategory = eligibilityCategory;
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

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("suffix")
	public String getSuffix() {
		return suffix;
	}

	@JsonProperty("suffix")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("groupNumber")
	public String getGroupNumber() {
		return groupNumber;
	}

	@JsonProperty("groupNumber")
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	@JsonProperty("idCard")
	public String getIdCard() {
		return idCard;
	}

	@JsonProperty("idCard")
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	@JsonProperty("idCardIssueDate")
	public String getIdCardIssueDate() {
		return idCardIssueDate;
	}

	@JsonProperty("idCardIssueDate")
	public void setIdCardIssueDate(String idCardIssueDate) {
		this.idCardIssueDate = idCardIssueDate;
	}

	@JsonProperty("beginningCardIssueDate")
	public String getBeginningCardIssueDate() {
		return beginningCardIssueDate;
	}

	@JsonProperty("beginningCardIssueDate")
	public void setBeginningCardIssueDate(String beginningCardIssueDate) {
		this.beginningCardIssueDate = beginningCardIssueDate;
	}

	@JsonProperty("endCardIssueDate")
	public String getEndCardIssueDate() {
		return endCardIssueDate;
	}

	@JsonProperty("endCardIssueDate")
	public void setEndCardIssueDate(String endCardIssueDate) {
		this.endCardIssueDate = endCardIssueDate;
	}

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("planIssueDate")
	public String getPlanIssueDate() {
		return planIssueDate;
	}

	@JsonProperty("planIssueDate")
	public void setPlanIssueDate(String planIssueDate) {
		this.planIssueDate = planIssueDate;
	}

	@JsonProperty("beginningPlanIssueDate")
	public String getBeginningPlanIssueDate() {
		return beginningPlanIssueDate;
	}

	@JsonProperty("beginningPlanIssueDate")
	public void setBeginningPlanIssueDate(String beginningPlanIssueDate) {
		this.beginningPlanIssueDate = beginningPlanIssueDate;
	}

	@JsonProperty("endPlanIssueDate")
	public String getEndPlanIssueDate() {
		return endPlanIssueDate;
	}

	@JsonProperty("endPlanIssueDate")
	public void setEndPlanIssueDate(String endPlanIssueDate) {
		this.endPlanIssueDate = endPlanIssueDate;
	}

	@JsonProperty("planNumber")
	public String getPlanNumber() {
		return planNumber;
	}

	@JsonProperty("planNumber")
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	@JsonProperty("policyNumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("policyNumber")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("memberIdentificationNumber")
	public String getMemberIdentificationNumber() {
		return memberIdentificationNumber;
	}

	@JsonProperty("memberIdentificationNumber")
	public void setMemberIdentificationNumber(String memberIdentificationNumber) {
		this.memberIdentificationNumber = memberIdentificationNumber;
	}

	@JsonProperty("contractNumber")
	public String getContractNumber() {
		return contractNumber;
	}

	@JsonProperty("contractNumber")
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@JsonProperty("medicalRecordIdentificationNumber")
	public String getMedicalRecordIdentificationNumber() {
		return medicalRecordIdentificationNumber;
	}

	@JsonProperty("medicalRecordIdentificationNumber")
	public void setMedicalRecordIdentificationNumber(String medicalRecordIdentificationNumber) {
		this.medicalRecordIdentificationNumber = medicalRecordIdentificationNumber;
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

	@JsonProperty("healthCareCodeInformation")
	public List<HealthCareCodeInformation__1> getHealthCareCodeInformation() {
		return healthCareCodeInformation;
	}

	@JsonProperty("healthCareCodeInformation")
	public void setHealthCareCodeInformation(List<HealthCareCodeInformation__1> healthCareCodeInformation) {
		this.healthCareCodeInformation = healthCareCodeInformation;
	}

	@JsonProperty("address")
	public Address__3 getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address__3 address) {
		this.address = address;
	}

	@JsonProperty("additionalIdentification")
	public AdditionalIdentification getAdditionalIdentification() {
		return additionalIdentification;
	}

	@JsonProperty("additionalIdentification")
	public void setAdditionalIdentification(AdditionalIdentification additionalIdentification) {
		this.additionalIdentification = additionalIdentification;
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