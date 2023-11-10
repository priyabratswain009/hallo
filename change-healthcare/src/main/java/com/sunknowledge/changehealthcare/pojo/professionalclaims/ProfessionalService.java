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
@JsonPropertyOrder({
	"procedureIdentifier",
	"lineItemChargeAmount",
	"procedureCode",
	"measurementUnit",
	"serviceUnitCount",
	"compositeDiagnosisCodePointers"
})
@Generated("jsonschema2pojo")
public class ProfessionalService {
	@JsonProperty("procedureIdentifier")
	private String procedureIdentifier;
	@JsonProperty("lineItemChargeAmount")
	private String lineItemChargeAmount;
	@JsonProperty("procedureCode")
	private String procedureCode;
	@JsonProperty("measurementUnit")
	private String measurementUnit;
	@JsonProperty("serviceUnitCount")
	private String serviceUnitCount;
	@JsonProperty("compositeDiagnosisCodePointers")
	private CompositeDiagnosisCodePointers compositeDiagnosisCodePointers;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("procedureIdentifier")
	public String getProcedureIdentifier() {
		return procedureIdentifier;
	}

	@JsonProperty("procedureIdentifier")
	public void setProcedureIdentifier(String procedureIdentifier) {
		this.procedureIdentifier = procedureIdentifier;
	}

	@JsonProperty("lineItemChargeAmount")
	public String getLineItemChargeAmount() {
		return lineItemChargeAmount;
	}

	@JsonProperty("lineItemChargeAmount")
	public void setLineItemChargeAmount(String lineItemChargeAmount) {
		this.lineItemChargeAmount = lineItemChargeAmount;
	}

	@JsonProperty("procedureCode")
	public String getProcedureCode() {
		return procedureCode;
	}

	@JsonProperty("procedureCode")
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}

	@JsonProperty("measurementUnit")
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	@JsonProperty("measurementUnit")
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	@JsonProperty("serviceUnitCount")
	public String getServiceUnitCount() {
		return serviceUnitCount;
	}

	@JsonProperty("serviceUnitCount")
	public void setServiceUnitCount(String serviceUnitCount) {
		this.serviceUnitCount = serviceUnitCount;
	}

	@JsonProperty("compositeDiagnosisCodePointers")
	public CompositeDiagnosisCodePointers getCompositeDiagnosisCodePointers() {
		return compositeDiagnosisCodePointers;
	}

	@JsonProperty("compositeDiagnosisCodePointers")
	public void setCompositeDiagnosisCodePointers(CompositeDiagnosisCodePointers compositeDiagnosisCodePointers) {
		this.compositeDiagnosisCodePointers = compositeDiagnosisCodePointers;
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
