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
@JsonPropertyOrder({ "otherSubscriberInformation", "claimFilingCode", "propertyCasualtyClaimNumber", "patientWeight",
		"patientControlNumber", "deathDate", "pregnancyIndicator", "claimChargeAmount", "placeOfServiceCode",
		"claimFrequencyCode", "planParticipationCode", "signatureIndicator", "benefitsAssignmentCertificationIndicator",
		"releaseInformationCode", "autoAccidentStateCode", "patientAmountPaid", "autoAccidentCountryCode",
		"patientSignatureSourceCode", "specialProgramCode", "delayReasonCode", "homeboundIndicator",
		"claimDateInformation", "serviceFacilityLocation", "ambulancePickUpLocation", "ambulanceDropOffLocation",
		"claimPricingRepricingInformation", "conditionInformation", "anesthesiaRelatedSurgicalProcedure",
		"healthCareCodeInformation", "epsdtReferral", "patientConditionInformationVision", "claimNote",
		"ambulanceTransportInformation", "spinalManipulationServiceInformation", "ambulanceCertification",
		"claimSupplementalInformation", "claimContractInformation", "fileInformationList", "relatedCausesCode",
		"serviceLines" })
@Generated("jsonschema2pojo")
public class ClaimInformation {

	@JsonProperty("otherSubscriberInformation")
	private List<OtherSubscriberInformation> otherSubscriberInformation = null;
	@JsonProperty("claimFilingCode")
	private String claimFilingCode;
	@JsonProperty("propertyCasualtyClaimNumber")
	private String propertyCasualtyClaimNumber;
	@JsonProperty("patientWeight")
	private String patientWeight;
	@JsonProperty("patientControlNumber")
	private String patientControlNumber;
	@JsonProperty("deathDate")
	private String deathDate;
	@JsonProperty("pregnancyIndicator")
	private String pregnancyIndicator;
	@JsonProperty("claimChargeAmount")
	private String claimChargeAmount;
	@JsonProperty("placeOfServiceCode")
	private String placeOfServiceCode;
	@JsonProperty("claimFrequencyCode")
	private String claimFrequencyCode;
	@JsonProperty("planParticipationCode")
	private String planParticipationCode;
	@JsonProperty("signatureIndicator")
	private String signatureIndicator;
	@JsonProperty("benefitsAssignmentCertificationIndicator")
	private String benefitsAssignmentCertificationIndicator;
	@JsonProperty("releaseInformationCode")
	private String releaseInformationCode;
	@JsonProperty("autoAccidentStateCode")
	private String autoAccidentStateCode;
	@JsonProperty("patientAmountPaid")
	private String patientAmountPaid;
	@JsonProperty("autoAccidentCountryCode")
	private String autoAccidentCountryCode;
	@JsonProperty("patientSignatureSourceCode")
	private Boolean patientSignatureSourceCode;
	@JsonProperty("specialProgramCode")
	private String specialProgramCode;
	@JsonProperty("delayReasonCode")
	private String delayReasonCode;
	@JsonProperty("homeboundIndicator")
	private Boolean homeboundIndicator;
	@JsonProperty("claimDateInformation")
	private ClaimDateInformation claimDateInformation;
	@JsonProperty("serviceFacilityLocation")
	private ServiceFacilityLocation serviceFacilityLocation;
	@JsonProperty("ambulancePickUpLocation")
	private AmbulancePickUpLocation ambulancePickUpLocation;
	@JsonProperty("ambulanceDropOffLocation")
	private AmbulanceDropOffLocation ambulanceDropOffLocation;
	@JsonProperty("claimPricingRepricingInformation")
	private ClaimPricingRepricingInformation claimPricingRepricingInformation;
	@JsonProperty("conditionInformation")
	private List<ConditionInformation> conditionInformation = null;
	@JsonProperty("anesthesiaRelatedSurgicalProcedure")
	private List<String> anesthesiaRelatedSurgicalProcedure = null;
	@JsonProperty("healthCareCodeInformation")
	private List<HealthCareCodeInformation> healthCareCodeInformation = null;
	@JsonProperty("epsdtReferral")
	private EpsdtReferral epsdtReferral;
	@JsonProperty("patientConditionInformationVision")
	private List<PatientConditionInformationVision> patientConditionInformationVision = null;
	@JsonProperty("claimNote")
	private ClaimNote claimNote;
	@JsonProperty("ambulanceTransportInformation")
	private AmbulanceTransportInformation ambulanceTransportInformation;
	@JsonProperty("spinalManipulationServiceInformation")
	private SpinalManipulationServiceInformation spinalManipulationServiceInformation;
	@JsonProperty("ambulanceCertification")
	private List<AmbulanceCertification> ambulanceCertification = null;
	@JsonProperty("claimSupplementalInformation")
	private ClaimSupplementalInformation claimSupplementalInformation;
	@JsonProperty("claimContractInformation")
	private ClaimContractInformation claimContractInformation;
	@JsonProperty("fileInformationList")
	private List<String> fileInformationList = null;
	@JsonProperty("relatedCausesCode")
	private List<String> relatedCausesCode = null;
	@JsonProperty("serviceLines")
	private List<ServiceLine> serviceLines = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("otherSubscriberInformation")
	public List<OtherSubscriberInformation> getOtherSubscriberInformation() {
		return otherSubscriberInformation;
	}

	@JsonProperty("otherSubscriberInformation")
	public void setOtherSubscriberInformation(List<OtherSubscriberInformation> otherSubscriberInformation) {
		this.otherSubscriberInformation = otherSubscriberInformation;
	}

	@JsonProperty("claimFilingCode")
	public String getClaimFilingCode() {
		return claimFilingCode;
	}

	@JsonProperty("claimFilingCode")
	public void setClaimFilingCode(String claimFilingCode) {
		this.claimFilingCode = claimFilingCode;
	}

	@JsonProperty("propertyCasualtyClaimNumber")
	public String getPropertyCasualtyClaimNumber() {
		return propertyCasualtyClaimNumber;
	}

	@JsonProperty("propertyCasualtyClaimNumber")
	public void setPropertyCasualtyClaimNumber(String propertyCasualtyClaimNumber) {
		this.propertyCasualtyClaimNumber = propertyCasualtyClaimNumber;
	}

	@JsonProperty("patientWeight")
	public String getPatientWeight() {
		return patientWeight;
	}

	@JsonProperty("patientWeight")
	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	@JsonProperty("patientControlNumber")
	public String getPatientControlNumber() {
		return patientControlNumber;
	}

	@JsonProperty("patientControlNumber")
	public void setPatientControlNumber(String patientControlNumber) {
		this.patientControlNumber = patientControlNumber;
	}

	@JsonProperty("deathDate")
	public String getDeathDate() {
		return deathDate;
	}

	@JsonProperty("deathDate")
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	@JsonProperty("pregnancyIndicator")
	public String getPregnancyIndicator() {
		return pregnancyIndicator;
	}

	@JsonProperty("pregnancyIndicator")
	public void setPregnancyIndicator(String pregnancyIndicator) {
		this.pregnancyIndicator = pregnancyIndicator;
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

	@JsonProperty("planParticipationCode")
	public String getPlanParticipationCode() {
		return planParticipationCode;
	}

	@JsonProperty("planParticipationCode")
	public void setPlanParticipationCode(String planParticipationCode) {
		this.planParticipationCode = planParticipationCode;
	}

	@JsonProperty("signatureIndicator")
	public String getSignatureIndicator() {
		return signatureIndicator;
	}

	@JsonProperty("signatureIndicator")
	public void setSignatureIndicator(String signatureIndicator) {
		this.signatureIndicator = signatureIndicator;
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

	@JsonProperty("autoAccidentStateCode")
	public String getAutoAccidentStateCode() {
		return autoAccidentStateCode;
	}

	@JsonProperty("autoAccidentStateCode")
	public void setAutoAccidentStateCode(String autoAccidentStateCode) {
		this.autoAccidentStateCode = autoAccidentStateCode;
	}

	@JsonProperty("patientAmountPaid")
	public String getPatientAmountPaid() {
		return patientAmountPaid;
	}

	@JsonProperty("patientAmountPaid")
	public void setPatientAmountPaid(String patientAmountPaid) {
		this.patientAmountPaid = patientAmountPaid;
	}

	@JsonProperty("autoAccidentCountryCode")
	public String getAutoAccidentCountryCode() {
		return autoAccidentCountryCode;
	}

	@JsonProperty("autoAccidentCountryCode")
	public void setAutoAccidentCountryCode(String autoAccidentCountryCode) {
		this.autoAccidentCountryCode = autoAccidentCountryCode;
	}

	@JsonProperty("patientSignatureSourceCode")
	public Boolean getPatientSignatureSourceCode() {
		return patientSignatureSourceCode;
	}

	@JsonProperty("patientSignatureSourceCode")
	public void setPatientSignatureSourceCode(Boolean patientSignatureSourceCode) {
		this.patientSignatureSourceCode = patientSignatureSourceCode;
	}

	@JsonProperty("specialProgramCode")
	public String getSpecialProgramCode() {
		return specialProgramCode;
	}

	@JsonProperty("specialProgramCode")
	public void setSpecialProgramCode(String specialProgramCode) {
		this.specialProgramCode = specialProgramCode;
	}

	@JsonProperty("delayReasonCode")
	public String getDelayReasonCode() {
		return delayReasonCode;
	}

	@JsonProperty("delayReasonCode")
	public void setDelayReasonCode(String delayReasonCode) {
		this.delayReasonCode = delayReasonCode;
	}

	@JsonProperty("homeboundIndicator")
	public Boolean getHomeboundIndicator() {
		return homeboundIndicator;
	}

	@JsonProperty("homeboundIndicator")
	public void setHomeboundIndicator(Boolean homeboundIndicator) {
		this.homeboundIndicator = homeboundIndicator;
	}

	@JsonProperty("claimDateInformation")
	public ClaimDateInformation getClaimDateInformation() {
		return claimDateInformation;
	}

	@JsonProperty("claimDateInformation")
	public void setClaimDateInformation(ClaimDateInformation claimDateInformation) {
		this.claimDateInformation = claimDateInformation;
	}

	@JsonProperty("serviceFacilityLocation")
	public ServiceFacilityLocation getServiceFacilityLocation() {
		return serviceFacilityLocation;
	}

	@JsonProperty("serviceFacilityLocation")
	public void setServiceFacilityLocation(ServiceFacilityLocation serviceFacilityLocation) {
		this.serviceFacilityLocation = serviceFacilityLocation;
	}

	@JsonProperty("ambulancePickUpLocation")
	public AmbulancePickUpLocation getAmbulancePickUpLocation() {
		return ambulancePickUpLocation;
	}

	@JsonProperty("ambulancePickUpLocation")
	public void setAmbulancePickUpLocation(AmbulancePickUpLocation ambulancePickUpLocation) {
		this.ambulancePickUpLocation = ambulancePickUpLocation;
	}

	@JsonProperty("ambulanceDropOffLocation")
	public AmbulanceDropOffLocation getAmbulanceDropOffLocation() {
		return ambulanceDropOffLocation;
	}

	@JsonProperty("ambulanceDropOffLocation")
	public void setAmbulanceDropOffLocation(AmbulanceDropOffLocation ambulanceDropOffLocation) {
		this.ambulanceDropOffLocation = ambulanceDropOffLocation;
	}

	@JsonProperty("claimPricingRepricingInformation")
	public ClaimPricingRepricingInformation getClaimPricingRepricingInformation() {
		return claimPricingRepricingInformation;
	}

	@JsonProperty("claimPricingRepricingInformation")
	public void setClaimPricingRepricingInformation(ClaimPricingRepricingInformation claimPricingRepricingInformation) {
		this.claimPricingRepricingInformation = claimPricingRepricingInformation;
	}

	@JsonProperty("conditionInformation")
	public List<ConditionInformation> getConditionInformation() {
		return conditionInformation;
	}

	@JsonProperty("conditionInformation")
	public void setConditionInformation(List<ConditionInformation> conditionInformation) {
		this.conditionInformation = conditionInformation;
	}

	@JsonProperty("anesthesiaRelatedSurgicalProcedure")
	public List<String> getAnesthesiaRelatedSurgicalProcedure() {
		return anesthesiaRelatedSurgicalProcedure;
	}

	@JsonProperty("anesthesiaRelatedSurgicalProcedure")
	public void setAnesthesiaRelatedSurgicalProcedure(List<String> anesthesiaRelatedSurgicalProcedure) {
		this.anesthesiaRelatedSurgicalProcedure = anesthesiaRelatedSurgicalProcedure;
	}

	@JsonProperty("healthCareCodeInformation")
	public List<HealthCareCodeInformation> getHealthCareCodeInformation() {
		return healthCareCodeInformation;
	}

	@JsonProperty("healthCareCodeInformation")
	public void setHealthCareCodeInformation(List<HealthCareCodeInformation> healthCareCodeInformation) {
		this.healthCareCodeInformation = healthCareCodeInformation;
	}

	@JsonProperty("epsdtReferral")
	public EpsdtReferral getEpsdtReferral() {
		return epsdtReferral;
	}

	@JsonProperty("epsdtReferral")
	public void setEpsdtReferral(EpsdtReferral epsdtReferral) {
		this.epsdtReferral = epsdtReferral;
	}

	@JsonProperty("patientConditionInformationVision")
	public List<PatientConditionInformationVision> getPatientConditionInformationVision() {
		return patientConditionInformationVision;
	}

	@JsonProperty("patientConditionInformationVision")
	public void setPatientConditionInformationVision(
			List<PatientConditionInformationVision> patientConditionInformationVision) {
		this.patientConditionInformationVision = patientConditionInformationVision;
	}

	@JsonProperty("claimNote")
	public ClaimNote getClaimNote() {
		return claimNote;
	}

	@JsonProperty("claimNote")
	public void setClaimNote(ClaimNote claimNote) {
		this.claimNote = claimNote;
	}

	@JsonProperty("ambulanceTransportInformation")
	public AmbulanceTransportInformation getAmbulanceTransportInformation() {
		return ambulanceTransportInformation;
	}

	@JsonProperty("ambulanceTransportInformation")
	public void setAmbulanceTransportInformation(AmbulanceTransportInformation ambulanceTransportInformation) {
		this.ambulanceTransportInformation = ambulanceTransportInformation;
	}

	@JsonProperty("spinalManipulationServiceInformation")
	public SpinalManipulationServiceInformation getSpinalManipulationServiceInformation() {
		return spinalManipulationServiceInformation;
	}

	@JsonProperty("spinalManipulationServiceInformation")
	public void setSpinalManipulationServiceInformation(
			SpinalManipulationServiceInformation spinalManipulationServiceInformation) {
		this.spinalManipulationServiceInformation = spinalManipulationServiceInformation;
	}

	@JsonProperty("ambulanceCertification")
	public List<AmbulanceCertification> getAmbulanceCertification() {
		return ambulanceCertification;
	}

	@JsonProperty("ambulanceCertification")
	public void setAmbulanceCertification(List<AmbulanceCertification> ambulanceCertification) {
		this.ambulanceCertification = ambulanceCertification;
	}

	@JsonProperty("claimSupplementalInformation")
	public ClaimSupplementalInformation getClaimSupplementalInformation() {
		return claimSupplementalInformation;
	}

	@JsonProperty("claimSupplementalInformation")
	public void setClaimSupplementalInformation(ClaimSupplementalInformation claimSupplementalInformation) {
		this.claimSupplementalInformation = claimSupplementalInformation;
	}

	@JsonProperty("claimContractInformation")
	public ClaimContractInformation getClaimContractInformation() {
		return claimContractInformation;
	}

	@JsonProperty("claimContractInformation")
	public void setClaimContractInformation(ClaimContractInformation claimContractInformation) {
		this.claimContractInformation = claimContractInformation;
	}

	@JsonProperty("fileInformationList")
	public List<String> getFileInformationList() {
		return fileInformationList;
	}

	@JsonProperty("fileInformationList")
	public void setFileInformationList(List<String> fileInformationList) {
		this.fileInformationList = fileInformationList;
	}

	@JsonProperty("relatedCausesCode")
	public List<String> getRelatedCausesCode() {
		return relatedCausesCode;
	}

	@JsonProperty("relatedCausesCode")
	public void setRelatedCausesCode(List<String> relatedCausesCode) {
		this.relatedCausesCode = relatedCausesCode;
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