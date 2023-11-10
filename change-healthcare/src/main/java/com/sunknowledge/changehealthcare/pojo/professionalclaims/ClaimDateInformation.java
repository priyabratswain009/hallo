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
@JsonPropertyOrder({ "symptomDate", "initialTreatmentDate", "lastSeenDate", "acuteManifestationDate", "accidentDate",
		"lastMenstrualPeriodDate", "lastXRayDate", "hearingAndVisionPrescriptionDate", "disabilityBeginDate",
		"disabilityEndDate", "lastWorkedDate", "authorizedReturnToWorkDate", "admissionDate", "dischargeDate",
		"assumedAndRelinquishedCareBeginDate", "assumedAndRelinquishedCareEndDate", "repricerReceivedDate",
		"firstContactDate" })
@Generated("jsonschema2pojo")
public class ClaimDateInformation {

	@JsonProperty("symptomDate")
	private String symptomDate;
	@JsonProperty("initialTreatmentDate")
	private String initialTreatmentDate;
	@JsonProperty("lastSeenDate")
	private String lastSeenDate;
	@JsonProperty("acuteManifestationDate")
	private String acuteManifestationDate;
	@JsonProperty("accidentDate")
	private String accidentDate;
	@JsonProperty("lastMenstrualPeriodDate")
	private String lastMenstrualPeriodDate;
	@JsonProperty("lastXRayDate")
	private String lastXRayDate;
	@JsonProperty("hearingAndVisionPrescriptionDate")
	private String hearingAndVisionPrescriptionDate;
	@JsonProperty("disabilityBeginDate")
	private String disabilityBeginDate;
	@JsonProperty("disabilityEndDate")
	private String disabilityEndDate;
	@JsonProperty("lastWorkedDate")
	private String lastWorkedDate;
	@JsonProperty("authorizedReturnToWorkDate")
	private String authorizedReturnToWorkDate;
	@JsonProperty("admissionDate")
	private String admissionDate;
	@JsonProperty("dischargeDate")
	private String dischargeDate;
	@JsonProperty("assumedAndRelinquishedCareBeginDate")
	private String assumedAndRelinquishedCareBeginDate;
	@JsonProperty("assumedAndRelinquishedCareEndDate")
	private String assumedAndRelinquishedCareEndDate;
	@JsonProperty("repricerReceivedDate")
	private String repricerReceivedDate;
	@JsonProperty("firstContactDate")
	private String firstContactDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("symptomDate")
	public String getSymptomDate() {
		return symptomDate;
	}

	@JsonProperty("symptomDate")
	public void setSymptomDate(String symptomDate) {
		this.symptomDate = symptomDate;
	}

	@JsonProperty("initialTreatmentDate")
	public String getInitialTreatmentDate() {
		return initialTreatmentDate;
	}

	@JsonProperty("initialTreatmentDate")
	public void setInitialTreatmentDate(String initialTreatmentDate) {
		this.initialTreatmentDate = initialTreatmentDate;
	}

	@JsonProperty("lastSeenDate")
	public String getLastSeenDate() {
		return lastSeenDate;
	}

	@JsonProperty("lastSeenDate")
	public void setLastSeenDate(String lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}

	@JsonProperty("acuteManifestationDate")
	public String getAcuteManifestationDate() {
		return acuteManifestationDate;
	}

	@JsonProperty("acuteManifestationDate")
	public void setAcuteManifestationDate(String acuteManifestationDate) {
		this.acuteManifestationDate = acuteManifestationDate;
	}

	@JsonProperty("accidentDate")
	public String getAccidentDate() {
		return accidentDate;
	}

	@JsonProperty("accidentDate")
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}

	@JsonProperty("lastMenstrualPeriodDate")
	public String getLastMenstrualPeriodDate() {
		return lastMenstrualPeriodDate;
	}

	@JsonProperty("lastMenstrualPeriodDate")
	public void setLastMenstrualPeriodDate(String lastMenstrualPeriodDate) {
		this.lastMenstrualPeriodDate = lastMenstrualPeriodDate;
	}

	@JsonProperty("lastXRayDate")
	public String getLastXRayDate() {
		return lastXRayDate;
	}

	@JsonProperty("lastXRayDate")
	public void setLastXRayDate(String lastXRayDate) {
		this.lastXRayDate = lastXRayDate;
	}

	@JsonProperty("hearingAndVisionPrescriptionDate")
	public String getHearingAndVisionPrescriptionDate() {
		return hearingAndVisionPrescriptionDate;
	}

	@JsonProperty("hearingAndVisionPrescriptionDate")
	public void setHearingAndVisionPrescriptionDate(String hearingAndVisionPrescriptionDate) {
		this.hearingAndVisionPrescriptionDate = hearingAndVisionPrescriptionDate;
	}

	@JsonProperty("disabilityBeginDate")
	public String getDisabilityBeginDate() {
		return disabilityBeginDate;
	}

	@JsonProperty("disabilityBeginDate")
	public void setDisabilityBeginDate(String disabilityBeginDate) {
		this.disabilityBeginDate = disabilityBeginDate;
	}

	@JsonProperty("disabilityEndDate")
	public String getDisabilityEndDate() {
		return disabilityEndDate;
	}

	@JsonProperty("disabilityEndDate")
	public void setDisabilityEndDate(String disabilityEndDate) {
		this.disabilityEndDate = disabilityEndDate;
	}

	@JsonProperty("lastWorkedDate")
	public String getLastWorkedDate() {
		return lastWorkedDate;
	}

	@JsonProperty("lastWorkedDate")
	public void setLastWorkedDate(String lastWorkedDate) {
		this.lastWorkedDate = lastWorkedDate;
	}

	@JsonProperty("authorizedReturnToWorkDate")
	public String getAuthorizedReturnToWorkDate() {
		return authorizedReturnToWorkDate;
	}

	@JsonProperty("authorizedReturnToWorkDate")
	public void setAuthorizedReturnToWorkDate(String authorizedReturnToWorkDate) {
		this.authorizedReturnToWorkDate = authorizedReturnToWorkDate;
	}

	@JsonProperty("admissionDate")
	public String getAdmissionDate() {
		return admissionDate;
	}

	@JsonProperty("admissionDate")
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	@JsonProperty("dischargeDate")
	public String getDischargeDate() {
		return dischargeDate;
	}

	@JsonProperty("dischargeDate")
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@JsonProperty("assumedAndRelinquishedCareBeginDate")
	public String getAssumedAndRelinquishedCareBeginDate() {
		return assumedAndRelinquishedCareBeginDate;
	}

	@JsonProperty("assumedAndRelinquishedCareBeginDate")
	public void setAssumedAndRelinquishedCareBeginDate(String assumedAndRelinquishedCareBeginDate) {
		this.assumedAndRelinquishedCareBeginDate = assumedAndRelinquishedCareBeginDate;
	}

	@JsonProperty("assumedAndRelinquishedCareEndDate")
	public String getAssumedAndRelinquishedCareEndDate() {
		return assumedAndRelinquishedCareEndDate;
	}

	@JsonProperty("assumedAndRelinquishedCareEndDate")
	public void setAssumedAndRelinquishedCareEndDate(String assumedAndRelinquishedCareEndDate) {
		this.assumedAndRelinquishedCareEndDate = assumedAndRelinquishedCareEndDate;
	}

	@JsonProperty("repricerReceivedDate")
	public String getRepricerReceivedDate() {
		return repricerReceivedDate;
	}

	@JsonProperty("repricerReceivedDate")
	public void setRepricerReceivedDate(String repricerReceivedDate) {
		this.repricerReceivedDate = repricerReceivedDate;
	}

	@JsonProperty("firstContactDate")
	public String getFirstContactDate() {
		return firstContactDate;
	}

	@JsonProperty("firstContactDate")
	public void setFirstContactDate(String firstContactDate) {
		this.firstContactDate = firstContactDate;
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