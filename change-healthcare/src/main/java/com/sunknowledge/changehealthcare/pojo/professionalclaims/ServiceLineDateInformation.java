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
@JsonPropertyOrder({ "prescriptionDate", "certificationRevisionOrRecertificationDate", "beginTherapyDate",
		"lastCertificationDate", "treatmentOrTherapyDate", "hemoglobinTestDate", "serumCreatineTestDate", "shippedDate",
		"lastXRayDate", "initialTreatmentDate" })
@Generated("jsonschema2pojo")
public class ServiceLineDateInformation {

	@JsonProperty("prescriptionDate")
	private String prescriptionDate;
	@JsonProperty("certificationRevisionOrRecertificationDate")
	private String certificationRevisionOrRecertificationDate;
	@JsonProperty("beginTherapyDate")
	private String beginTherapyDate;
	@JsonProperty("lastCertificationDate")
	private String lastCertificationDate;
	@JsonProperty("treatmentOrTherapyDate")
	private String treatmentOrTherapyDate;
	@JsonProperty("hemoglobinTestDate")
	private String hemoglobinTestDate;
	@JsonProperty("serumCreatineTestDate")
	private String serumCreatineTestDate;
	@JsonProperty("shippedDate")
	private String shippedDate;
	@JsonProperty("lastXRayDate")
	private String lastXRayDate;
	@JsonProperty("initialTreatmentDate")
	private String initialTreatmentDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("prescriptionDate")
	public String getPrescriptionDate() {
		return prescriptionDate;
	}

	@JsonProperty("prescriptionDate")
	public void setPrescriptionDate(String prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	@JsonProperty("certificationRevisionOrRecertificationDate")
	public String getCertificationRevisionOrRecertificationDate() {
		return certificationRevisionOrRecertificationDate;
	}

	@JsonProperty("certificationRevisionOrRecertificationDate")
	public void setCertificationRevisionOrRecertificationDate(String certificationRevisionOrRecertificationDate) {
		this.certificationRevisionOrRecertificationDate = certificationRevisionOrRecertificationDate;
	}

	@JsonProperty("beginTherapyDate")
	public String getBeginTherapyDate() {
		return beginTherapyDate;
	}

	@JsonProperty("beginTherapyDate")
	public void setBeginTherapyDate(String beginTherapyDate) {
		this.beginTherapyDate = beginTherapyDate;
	}

	@JsonProperty("lastCertificationDate")
	public String getLastCertificationDate() {
		return lastCertificationDate;
	}

	@JsonProperty("lastCertificationDate")
	public void setLastCertificationDate(String lastCertificationDate) {
		this.lastCertificationDate = lastCertificationDate;
	}

	@JsonProperty("treatmentOrTherapyDate")
	public String getTreatmentOrTherapyDate() {
		return treatmentOrTherapyDate;
	}

	@JsonProperty("treatmentOrTherapyDate")
	public void setTreatmentOrTherapyDate(String treatmentOrTherapyDate) {
		this.treatmentOrTherapyDate = treatmentOrTherapyDate;
	}

	@JsonProperty("hemoglobinTestDate")
	public String getHemoglobinTestDate() {
		return hemoglobinTestDate;
	}

	@JsonProperty("hemoglobinTestDate")
	public void setHemoglobinTestDate(String hemoglobinTestDate) {
		this.hemoglobinTestDate = hemoglobinTestDate;
	}

	@JsonProperty("serumCreatineTestDate")
	public String getSerumCreatineTestDate() {
		return serumCreatineTestDate;
	}

	@JsonProperty("serumCreatineTestDate")
	public void setSerumCreatineTestDate(String serumCreatineTestDate) {
		this.serumCreatineTestDate = serumCreatineTestDate;
	}

	@JsonProperty("shippedDate")
	public String getShippedDate() {
		return shippedDate;
	}

	@JsonProperty("shippedDate")
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}

	@JsonProperty("lastXRayDate")
	public String getLastXRayDate() {
		return lastXRayDate;
	}

	@JsonProperty("lastXRayDate")
	public void setLastXRayDate(String lastXRayDate) {
		this.lastXRayDate = lastXRayDate;
	}

	@JsonProperty("initialTreatmentDate")
	public String getInitialTreatmentDate() {
		return initialTreatmentDate;
	}

	@JsonProperty("initialTreatmentDate")
	public void setInitialTreatmentDate(String initialTreatmentDate) {
		this.initialTreatmentDate = initialTreatmentDate;
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