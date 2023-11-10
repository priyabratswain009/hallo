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
@JsonPropertyOrder({ "attachmentReportTypeCode", "attachmentTransmissionCode", "attachmentControlNumber" })
@Generated("jsonschema2pojo")
public class ServiceLineSupplementalInformation {

	@JsonProperty("attachmentReportTypeCode")
	private String attachmentReportTypeCode;
	@JsonProperty("attachmentTransmissionCode")
	private String attachmentTransmissionCode;
	@JsonProperty("attachmentControlNumber")
	private String attachmentControlNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("attachmentReportTypeCode")
	public String getAttachmentReportTypeCode() {
		return attachmentReportTypeCode;
	}

	@JsonProperty("attachmentReportTypeCode")
	public void setAttachmentReportTypeCode(String attachmentReportTypeCode) {
		this.attachmentReportTypeCode = attachmentReportTypeCode;
	}

	@JsonProperty("attachmentTransmissionCode")
	public String getAttachmentTransmissionCode() {
		return attachmentTransmissionCode;
	}

	@JsonProperty("attachmentTransmissionCode")
	public void setAttachmentTransmissionCode(String attachmentTransmissionCode) {
		this.attachmentTransmissionCode = attachmentTransmissionCode;
	}

	@JsonProperty("attachmentControlNumber")
	public String getAttachmentControlNumber() {
		return attachmentControlNumber;
	}

	@JsonProperty("attachmentControlNumber")
	public void setAttachmentControlNumber(String attachmentControlNumber) {
		this.attachmentControlNumber = attachmentControlNumber;
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
