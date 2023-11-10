package com.sunknowledge.changehealthcare.pojo.attachmentSubmission;

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
@JsonPropertyOrder({ "serviceDate", "payerClaimControlNumber", "serviceLineDateInformation", "professionalService",
		"attachmentDetails" })
@Generated("jsonschema2pojo")
public class ServiceLine {

	@JsonProperty("serviceDate")
	private String serviceDate;
	@JsonProperty("payerClaimControlNumber")
	private String payerClaimControlNumber;
	@JsonProperty("serviceLineDateInformation")
	private ServiceLineDateInformation serviceLineDateInformation;
	@JsonProperty("professionalService")
	private ProfessionalService professionalService;
	@JsonProperty("attachmentDetails")
	private AttachmentDetails attachmentDetails;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("serviceDate")
	public String getServiceDate() {
		return serviceDate;
	}

	@JsonProperty("serviceDate")
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	@JsonProperty("payerClaimControlNumber")
	public String getPayerClaimControlNumber() {
		return payerClaimControlNumber;
	}

	@JsonProperty("payerClaimControlNumber")
	public void setPayerClaimControlNumber(String payerClaimControlNumber) {
		this.payerClaimControlNumber = payerClaimControlNumber;
	}

	@JsonProperty("serviceLineDateInformation")
	public ServiceLineDateInformation getServiceLineDateInformation() {
		return serviceLineDateInformation;
	}

	@JsonProperty("serviceLineDateInformation")
	public void setServiceLineDateInformation(ServiceLineDateInformation serviceLineDateInformation) {
		this.serviceLineDateInformation = serviceLineDateInformation;
	}

	@JsonProperty("professionalService")
	public ProfessionalService getProfessionalService() {
		return professionalService;
	}

	@JsonProperty("professionalService")
	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}

	@JsonProperty("attachmentDetails")
	public AttachmentDetails getAttachmentDetails() {
		return attachmentDetails;
	}

	@JsonProperty("attachmentDetails")
	public void setAttachmentDetails(AttachmentDetails attachmentDetails) {
		this.attachmentDetails = attachmentDetails;
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