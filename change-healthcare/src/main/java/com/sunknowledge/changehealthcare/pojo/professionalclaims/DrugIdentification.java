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
@JsonPropertyOrder({ "serviceIdQualifier", "nationalDrugCode", "nationalDrugUnitCount", "measurementUnitCode",
		"linkSequenceNumber", "pharmacyPrescriptionNumber" })
@Generated("jsonschema2pojo")
public class DrugIdentification {

	@JsonProperty("serviceIdQualifier")
	private String serviceIdQualifier;
	@JsonProperty("nationalDrugCode")
	private String nationalDrugCode;
	@JsonProperty("nationalDrugUnitCount")
	private String nationalDrugUnitCount;
	@JsonProperty("measurementUnitCode")
	private String measurementUnitCode;
	@JsonProperty("linkSequenceNumber")
	private String linkSequenceNumber;
	@JsonProperty("pharmacyPrescriptionNumber")
	private String pharmacyPrescriptionNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("serviceIdQualifier")
	public String getServiceIdQualifier() {
		return serviceIdQualifier;
	}

	@JsonProperty("serviceIdQualifier")
	public void setServiceIdQualifier(String serviceIdQualifier) {
		this.serviceIdQualifier = serviceIdQualifier;
	}

	@JsonProperty("nationalDrugCode")
	public String getNationalDrugCode() {
		return nationalDrugCode;
	}

	@JsonProperty("nationalDrugCode")
	public void setNationalDrugCode(String nationalDrugCode) {
		this.nationalDrugCode = nationalDrugCode;
	}

	@JsonProperty("nationalDrugUnitCount")
	public String getNationalDrugUnitCount() {
		return nationalDrugUnitCount;
	}

	@JsonProperty("nationalDrugUnitCount")
	public void setNationalDrugUnitCount(String nationalDrugUnitCount) {
		this.nationalDrugUnitCount = nationalDrugUnitCount;
	}

	@JsonProperty("measurementUnitCode")
	public String getMeasurementUnitCode() {
		return measurementUnitCode;
	}

	@JsonProperty("measurementUnitCode")
	public void setMeasurementUnitCode(String measurementUnitCode) {
		this.measurementUnitCode = measurementUnitCode;
	}

	@JsonProperty("linkSequenceNumber")
	public String getLinkSequenceNumber() {
		return linkSequenceNumber;
	}

	@JsonProperty("linkSequenceNumber")
	public void setLinkSequenceNumber(String linkSequenceNumber) {
		this.linkSequenceNumber = linkSequenceNumber;
	}

	@JsonProperty("pharmacyPrescriptionNumber")
	public String getPharmacyPrescriptionNumber() {
		return pharmacyPrescriptionNumber;
	}

	@JsonProperty("pharmacyPrescriptionNumber")
	public void setPharmacyPrescriptionNumber(String pharmacyPrescriptionNumber) {
		this.pharmacyPrescriptionNumber = pharmacyPrescriptionNumber;
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