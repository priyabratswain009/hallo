package com.sunknowledge.dme.rcm.domain.ServiceReview;

import java.util.LinkedHashMap;
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
@JsonPropertyOrder({ "attachments", "diagnoses", "procedures", "refAuthNumber", "sequence", "updateType" })
@Generated("jsonschema2pojo")
public class SupplementalInformation {

	@JsonProperty("attachments")
	private List<Attachment> attachments;
	@JsonProperty("diagnoses")
	private List<Diagnosis> diagnoses;
	@JsonProperty("procedures")
	private List<Procedure> procedures;
	@JsonProperty("refAuthNumber")
	private String refAuthNumber;
	@JsonProperty("sequence")
	private String sequence;
	@JsonProperty("updateType")
	private String updateType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("attachments")
	public List<Attachment> getAttachments() {
		return attachments;
	}

	@JsonProperty("attachments")
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@JsonProperty("diagnoses")
	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	@JsonProperty("diagnoses")
	public void setDiagnoses(List<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	@JsonProperty("procedures")
	public List<Procedure> getProcedures() {
		return procedures;
	}

	@JsonProperty("procedures")
	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	@JsonProperty("refAuthNumber")
	public String getRefAuthNumber() {
		return refAuthNumber;
	}

	@JsonProperty("refAuthNumber")
	public void setRefAuthNumber(String refAuthNumber) {
		this.refAuthNumber = refAuthNumber;
	}

	@JsonProperty("sequence")
	public String getSequence() {
		return sequence;
	}

	@JsonProperty("sequence")
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@JsonProperty("updateType")
	public String getUpdateType() {
		return updateType;
	}

	@JsonProperty("updateType")
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
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