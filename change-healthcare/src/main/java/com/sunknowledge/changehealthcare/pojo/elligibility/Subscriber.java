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
@JsonPropertyOrder({ "memberId", "firstName", "lastName", "suffix", "groupNumber", "idCard", "ssn", "caseNumber",
		"medicaidRecipientIdentificationNumber", "planNumber", "policyNumber", "memberIdentificationNumber",
		"contractNumber", "medicalRecordIdentificationNumber", "patientAccountNumber", "healthInsuranceClaimNumber",
		"identificationCardSerialNumber", "insurancePolicyNumber", "planNetworkIdentificationNumber",
		"agencyClaimNumber", "address", "providerCode", "referenceIdentificationQualifier", "providerIdentifier",
		"dateOfBirth", "gender", "birthSequenceNumber", "healthCareCodeInformation", "beginningCardIssueDate",
		"endCardIssueDate", "idCardIssueDate", "beginningPlanIssueDate", "endPlanIssueDate", "planIssueDate",
		"informationStatusCode", "employmentStatusCode", "governmentServiceAffiliationCode", "description",
		"militaryServiceRankCode", "dateTimeFormatQualifier", "dateTimePeriod", "startDateTimePeriod",
		"endDateTimePeriod", "spendDownAmount", "spendDownTotalBilledAmount", "coverageLevelCode", "middleName" })
@Generated("jsonschema2pojo")
public class Subscriber {

	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("groupNumber")
	private String groupNumber;
	@JsonProperty("idCard")
	private String idCard;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("caseNumber")
	private String caseNumber;
	@JsonProperty("medicaidRecipientIdentificationNumber")
	private String medicaidRecipientIdentificationNumber;
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
	@JsonProperty("identificationCardSerialNumber")
	private String identificationCardSerialNumber;
	@JsonProperty("insurancePolicyNumber")
	private String insurancePolicyNumber;
	@JsonProperty("planNetworkIdentificationNumber")
	private String planNetworkIdentificationNumber;
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
	@JsonProperty("birthSequenceNumber")
	private String birthSequenceNumber;
	@JsonProperty("healthCareCodeInformation")
	private List<HealthCareCodeInformation> healthCareCodeInformation = null;
	@JsonProperty("beginningCardIssueDate")
	private String beginningCardIssueDate;
	@JsonProperty("endCardIssueDate")
	private String endCardIssueDate;
	@JsonProperty("idCardIssueDate")
	private String idCardIssueDate;
	@JsonProperty("beginningPlanIssueDate")
	private String beginningPlanIssueDate;
	@JsonProperty("endPlanIssueDate")
	private String endPlanIssueDate;
	@JsonProperty("planIssueDate")
	private String planIssueDate;
	@JsonProperty("informationStatusCode")
	private String informationStatusCode;
	@JsonProperty("employmentStatusCode")
	private String employmentStatusCode;
	@JsonProperty("governmentServiceAffiliationCode")
	private String governmentServiceAffiliationCode;
	@JsonProperty("description")
	private String description;
	@JsonProperty("militaryServiceRankCode")
	private String militaryServiceRankCode;
	@JsonProperty("dateTimeFormatQualifier")
	private String dateTimeFormatQualifier;
	@JsonProperty("dateTimePeriod")
	private String dateTimePeriod;
	@JsonProperty("startDateTimePeriod")
	private String startDateTimePeriod;
	@JsonProperty("endDateTimePeriod")
	private String endDateTimePeriod;
	@JsonProperty("spendDownAmount")
	private String spendDownAmount;
	@JsonProperty("spendDownTotalBilledAmount")
	private String spendDownTotalBilledAmount;
	@JsonProperty("coverageLevelCode")
	private String coverageLevelCode;
	@JsonProperty("middleName")
	private String middleName;
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

	@JsonProperty("suffix")
	public String getSuffix() {
		return suffix;
	}

	@JsonProperty("suffix")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@JsonProperty("caseNumber")
	public String getCaseNumber() {
		return caseNumber;
	}

	@JsonProperty("caseNumber")
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@JsonProperty("medicaidRecipientIdentificationNumber")
	public String getMedicaidRecipientIdentificationNumber() {
		return medicaidRecipientIdentificationNumber;
	}

	@JsonProperty("medicaidRecipientIdentificationNumber")
	public void setMedicaidRecipientIdentificationNumber(String medicaidRecipientIdentificationNumber) {
		this.medicaidRecipientIdentificationNumber = medicaidRecipientIdentificationNumber;
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

	@JsonProperty("identificationCardSerialNumber")
	public String getIdentificationCardSerialNumber() {
		return identificationCardSerialNumber;
	}

	@JsonProperty("identificationCardSerialNumber")
	public void setIdentificationCardSerialNumber(String identificationCardSerialNumber) {
		this.identificationCardSerialNumber = identificationCardSerialNumber;
	}

	@JsonProperty("insurancePolicyNumber")
	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	@JsonProperty("insurancePolicyNumber")
	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	@JsonProperty("planNetworkIdentificationNumber")
	public String getPlanNetworkIdentificationNumber() {
		return planNetworkIdentificationNumber;
	}

	@JsonProperty("planNetworkIdentificationNumber")
	public void setPlanNetworkIdentificationNumber(String planNetworkIdentificationNumber) {
		this.planNetworkIdentificationNumber = planNetworkIdentificationNumber;
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

	@JsonProperty("birthSequenceNumber")
	public String getBirthSequenceNumber() {
		return birthSequenceNumber;
	}

	@JsonProperty("birthSequenceNumber")
	public void setBirthSequenceNumber(String birthSequenceNumber) {
		this.birthSequenceNumber = birthSequenceNumber;
	}

	@JsonProperty("healthCareCodeInformation")
	public List<HealthCareCodeInformation> getHealthCareCodeInformation() {
		return healthCareCodeInformation;
	}

	@JsonProperty("healthCareCodeInformation")
	public void setHealthCareCodeInformation(List<HealthCareCodeInformation> healthCareCodeInformation) {
		this.healthCareCodeInformation = healthCareCodeInformation;
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

	@JsonProperty("idCardIssueDate")
	public String getIdCardIssueDate() {
		return idCardIssueDate;
	}

	@JsonProperty("idCardIssueDate")
	public void setIdCardIssueDate(String idCardIssueDate) {
		this.idCardIssueDate = idCardIssueDate;
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

	@JsonProperty("planIssueDate")
	public String getPlanIssueDate() {
		return planIssueDate;
	}

	@JsonProperty("planIssueDate")
	public void setPlanIssueDate(String planIssueDate) {
		this.planIssueDate = planIssueDate;
	}

	@JsonProperty("informationStatusCode")
	public String getInformationStatusCode() {
		return informationStatusCode;
	}

	@JsonProperty("informationStatusCode")
	public void setInformationStatusCode(String informationStatusCode) {
		this.informationStatusCode = informationStatusCode;
	}

	@JsonProperty("employmentStatusCode")
	public String getEmploymentStatusCode() {
		return employmentStatusCode;
	}

	@JsonProperty("employmentStatusCode")
	public void setEmploymentStatusCode(String employmentStatusCode) {
		this.employmentStatusCode = employmentStatusCode;
	}

	@JsonProperty("governmentServiceAffiliationCode")
	public String getGovernmentServiceAffiliationCode() {
		return governmentServiceAffiliationCode;
	}

	@JsonProperty("governmentServiceAffiliationCode")
	public void setGovernmentServiceAffiliationCode(String governmentServiceAffiliationCode) {
		this.governmentServiceAffiliationCode = governmentServiceAffiliationCode;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("militaryServiceRankCode")
	public String getMilitaryServiceRankCode() {
		return militaryServiceRankCode;
	}

	@JsonProperty("militaryServiceRankCode")
	public void setMilitaryServiceRankCode(String militaryServiceRankCode) {
		this.militaryServiceRankCode = militaryServiceRankCode;
	}

	@JsonProperty("dateTimeFormatQualifier")
	public String getDateTimeFormatQualifier() {
		return dateTimeFormatQualifier;
	}

	@JsonProperty("dateTimeFormatQualifier")
	public void setDateTimeFormatQualifier(String dateTimeFormatQualifier) {
		this.dateTimeFormatQualifier = dateTimeFormatQualifier;
	}

	@JsonProperty("dateTimePeriod")
	public String getDateTimePeriod() {
		return dateTimePeriod;
	}

	@JsonProperty("dateTimePeriod")
	public void setDateTimePeriod(String dateTimePeriod) {
		this.dateTimePeriod = dateTimePeriod;
	}

	@JsonProperty("startDateTimePeriod")
	public String getStartDateTimePeriod() {
		return startDateTimePeriod;
	}

	@JsonProperty("startDateTimePeriod")
	public void setStartDateTimePeriod(String startDateTimePeriod) {
		this.startDateTimePeriod = startDateTimePeriod;
	}

	@JsonProperty("endDateTimePeriod")
	public String getEndDateTimePeriod() {
		return endDateTimePeriod;
	}

	@JsonProperty("endDateTimePeriod")
	public void setEndDateTimePeriod(String endDateTimePeriod) {
		this.endDateTimePeriod = endDateTimePeriod;
	}

	@JsonProperty("spendDownAmount")
	public String getSpendDownAmount() {
		return spendDownAmount;
	}

	@JsonProperty("spendDownAmount")
	public void setSpendDownAmount(String spendDownAmount) {
		this.spendDownAmount = spendDownAmount;
	}

	@JsonProperty("spendDownTotalBilledAmount")
	public String getSpendDownTotalBilledAmount() {
		return spendDownTotalBilledAmount;
	}

	@JsonProperty("spendDownTotalBilledAmount")
	public void setSpendDownTotalBilledAmount(String spendDownTotalBilledAmount) {
		this.spendDownTotalBilledAmount = spendDownTotalBilledAmount;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("coverageLevelCode")
	public String getCoverageLevelCode() {
		return coverageLevelCode;
	}

	@JsonProperty("coverageLevelCode")
	public void setCoverageLevelCode(String coverageLevelCode) {
		this.coverageLevelCode = coverageLevelCode;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

}