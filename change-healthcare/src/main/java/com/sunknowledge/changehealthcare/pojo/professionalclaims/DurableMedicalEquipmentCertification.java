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
@JsonPropertyOrder({ "certificationTypeCode", "durableMedicalEquipmentDurationInMonths" })
@Generated("jsonschema2pojo")
public class DurableMedicalEquipmentCertification {

	@JsonProperty("certificationTypeCode")
	private String certificationTypeCode;
	@JsonProperty("durableMedicalEquipmentDurationInMonths")
	private String durableMedicalEquipmentDurationInMonths;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("certificationTypeCode")
	public String getCertificationTypeCode() {
		return certificationTypeCode;
	}

	@JsonProperty("certificationTypeCode")
	public void setCertificationTypeCode(String certificationTypeCode) {
		this.certificationTypeCode = certificationTypeCode;
	}

	@JsonProperty("durableMedicalEquipmentDurationInMonths")
	public String getDurableMedicalEquipmentDurationInMonths() {
		return durableMedicalEquipmentDurationInMonths;
	}

	@JsonProperty("durableMedicalEquipmentDurationInMonths")
	public void setDurableMedicalEquipmentDurationInMonths(String durableMedicalEquipmentDurationInMonths) {
		this.durableMedicalEquipmentDurationInMonths = durableMedicalEquipmentDurationInMonths;
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