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
@JsonPropertyOrder({ "paymentResponsibilityLevelCode", "individualRelationshipCode", "insuranceGroupOrPolicyNumber",
		"otherInsuredGroupName", "claimFilingIndicatorCode", "groupNumber", "subscriberGroupName", "insuranceTypeCode",
		"pregnancyIndicator", "memberId", "ssn", "firstName", "lastName", "middleName", "dateOfBirth", "gender",
		"policyNumber", "idCard", "payerPaidAmount", "nonCoveredChargeAmount", "remainingPatientLiability",
		"benefitsAssignmentCertificationIndicator", "patientSignatureGeneratedForPatient", "releaseOfInformationCode",
		"reimbursementRate", "hcpcsPayableAmount", "claimPaymentRemarkCode", "endStageRenalDiseasePaymentAmount",
		"nonPayableProfessionalComponentBilledAmount", "otherSubscriberName", "claimLevelAdjustments",
		"adjustmentdetails", "contactInformation", "address" })
@Generated("jsonschema2pojo")
public class Subscriber {

	@JsonProperty("paymentResponsibilityLevelCode")
	private String paymentResponsibilityLevelCode;
	@JsonProperty("individualRelationshipCode")
	private String individualRelationshipCode;
	@JsonProperty("insuranceGroupOrPolicyNumber")
	private String insuranceGroupOrPolicyNumber;
	@JsonProperty("otherInsuredGroupName")
	private String otherInsuredGroupName;
	@JsonProperty("claimFilingIndicatorCode")
	private String claimFilingIndicatorCode;
	@JsonProperty("groupNumber")
	private String groupNumber;
	@JsonProperty("subscriberGroupName")
	private String subscriberGroupName;
	@JsonProperty("insuranceTypeCode")
	private String insuranceTypeCode;
	@JsonProperty("pregnancyIndicator")
	private String pregnancyIndicator;
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("ssn")
	private String ssn;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("policyNumber")
	private String policyNumber;
	@JsonProperty("idCard")
	private String idCard;
	@JsonProperty("payerPaidAmount")
	private String payerPaidAmount;
	@JsonProperty("nonCoveredChargeAmount")
	private String nonCoveredChargeAmount;
	@JsonProperty("remainingPatientLiability")
	private String remainingPatientLiability;
	@JsonProperty("benefitsAssignmentCertificationIndicator")
	private String benefitsAssignmentCertificationIndicator;
	@JsonProperty("patientSignatureGeneratedForPatient")
	private String patientSignatureGeneratedForPatient;
	@JsonProperty("releaseOfInformationCode")
	private String releaseOfInformationCode;
	@JsonProperty("reimbursementRate")
	private String reimbursementRate;
	@JsonProperty("hcpcsPayableAmount")
	private String hcpcsPayableAmount;
	@JsonProperty("claimPaymentRemarkCode")
	private String claimPaymentRemarkCode;
	@JsonProperty("endStageRenalDiseasePaymentAmount")
	private String endStageRenalDiseasePaymentAmount;
	@JsonProperty("nonPayableProfessionalComponentBilledAmount")
	private String nonPayableProfessionalComponentBilledAmount;
	@JsonProperty("otherSubscriberName")
	private OtherSubscriberName otherSubscriberName;
	@JsonProperty("claimLevelAdjustments")
	private List<ClaimLevelAdjustment> claimLevelAdjustments = null;
	@JsonProperty("adjustmentdetails")
	private List<Adjustmentdetail> adjustmentdetails = null;
	@JsonProperty("contactInformation")
	private ContactInformation contactInformation;
	@JsonProperty("address")
	private Address address;
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

	@JsonProperty("claimFilingIndicatorCode")
	public String getClaimFilingIndicatorCode() {
		return claimFilingIndicatorCode;
	}

	@JsonProperty("claimFilingIndicatorCode")
	public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
		this.claimFilingIndicatorCode = claimFilingIndicatorCode;
	}

	@JsonProperty("groupNumber")
	public String getGroupNumber() {
		return groupNumber;
	}

	@JsonProperty("groupNumber")
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	@JsonProperty("subscriberGroupName")
	public String getSubscriberGroupName() {
		return subscriberGroupName;
	}

	@JsonProperty("subscriberGroupName")
	public void setSubscriberGroupName(String subscriberGroupName) {
		this.subscriberGroupName = subscriberGroupName;
	}

	@JsonProperty("insuranceTypeCode")
	public String getInsuranceTypeCode() {
		return insuranceTypeCode;
	}

	@JsonProperty("insuranceTypeCode")
	public void setInsuranceTypeCode(String insuranceTypeCode) {
		this.insuranceTypeCode = insuranceTypeCode;
	}

	@JsonProperty("pregnancyIndicator")
	public String getPregnancyIndicator() {
		return pregnancyIndicator;
	}

	@JsonProperty("pregnancyIndicator")
	public void setPregnancyIndicator(String pregnancyIndicator) {
		this.pregnancyIndicator = pregnancyIndicator;
	}

	@JsonProperty("memberId")
	public String getMemberId() {
		return memberId;
	}

	@JsonProperty("memberId")
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@JsonProperty("ssn")
	public String getSsn() {
		return ssn;
	}

	@JsonProperty("ssn")
	public void setSsn(String ssn) {
		this.ssn = ssn;
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

	@JsonProperty("policyNumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("policyNumber")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("idCard")
	public String getIdCard() {
		return idCard;
	}

	@JsonProperty("idCard")
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@JsonProperty("payerPaidAmount")
	public String getPayerPaidAmount() {
		return payerPaidAmount;
	}

	@JsonProperty("payerPaidAmount")
	public void setPayerPaidAmount(String payerPaidAmount) {
		this.payerPaidAmount = payerPaidAmount;
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
	public String getPatientSignatureGeneratedForPatient() {
		return patientSignatureGeneratedForPatient;
	}

	@JsonProperty("patientSignatureGeneratedForPatient")
	public void setPatientSignatureGeneratedForPatient(String patientSignatureGeneratedForPatient) {
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

	@JsonProperty("reimbursementRate")
	public String getReimbursementRate() {
		return reimbursementRate;
	}

	@JsonProperty("reimbursementRate")
	public void setReimbursementRate(String reimbursementRate) {
		this.reimbursementRate = reimbursementRate;
	}

	@JsonProperty("hcpcsPayableAmount")
	public String getHcpcsPayableAmount() {
		return hcpcsPayableAmount;
	}

	@JsonProperty("hcpcsPayableAmount")
	public void setHcpcsPayableAmount(String hcpcsPayableAmount) {
		this.hcpcsPayableAmount = hcpcsPayableAmount;
	}

	@JsonProperty("claimPaymentRemarkCode")
	public String getClaimPaymentRemarkCode() {
		return claimPaymentRemarkCode;
	}

	@JsonProperty("claimPaymentRemarkCode")
	public void setClaimPaymentRemarkCode(String claimPaymentRemarkCode) {
		this.claimPaymentRemarkCode = claimPaymentRemarkCode;
	}

	@JsonProperty("endStageRenalDiseasePaymentAmount")
	public String getEndStageRenalDiseasePaymentAmount() {
		return endStageRenalDiseasePaymentAmount;
	}

	@JsonProperty("endStageRenalDiseasePaymentAmount")
	public void setEndStageRenalDiseasePaymentAmount(String endStageRenalDiseasePaymentAmount) {
		this.endStageRenalDiseasePaymentAmount = endStageRenalDiseasePaymentAmount;
	}

	@JsonProperty("nonPayableProfessionalComponentBilledAmount")
	public String getNonPayableProfessionalComponentBilledAmount() {
		return nonPayableProfessionalComponentBilledAmount;
	}

	@JsonProperty("nonPayableProfessionalComponentBilledAmount")
	public void setNonPayableProfessionalComponentBilledAmount(String nonPayableProfessionalComponentBilledAmount) {
		this.nonPayableProfessionalComponentBilledAmount = nonPayableProfessionalComponentBilledAmount;
	}

	@JsonProperty("otherSubscriberName")
	public OtherSubscriberName getOtherSubscriberName() {
		return otherSubscriberName;
	}

	@JsonProperty("otherSubscriberName")
	public void setOtherSubscriberName(OtherSubscriberName otherSubscriberName) {
		this.otherSubscriberName = otherSubscriberName;
	}

	@JsonProperty("claimLevelAdjustments")
	public List<ClaimLevelAdjustment> getClaimLevelAdjustments() {
		return claimLevelAdjustments;
	}

	@JsonProperty("claimLevelAdjustments")
	public void setClaimLevelAdjustments(List<ClaimLevelAdjustment> claimLevelAdjustments) {
		this.claimLevelAdjustments = claimLevelAdjustments;
	}

	@JsonProperty("adjustmentdetails")
	public List<Adjustmentdetail> getAdjustmentdetails() {
		return adjustmentdetails;
	}

	@JsonProperty("adjustmentdetails")
	public void setAdjustmentdetails(List<Adjustmentdetail> adjustmentdetails) {
		this.adjustmentdetails = adjustmentdetails;
	}

	@JsonProperty("contactInformation")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	@JsonProperty("contactInformation")
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
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