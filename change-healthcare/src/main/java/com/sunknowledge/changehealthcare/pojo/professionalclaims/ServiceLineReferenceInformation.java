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
@JsonPropertyOrder({ "priorAuthorization", "referralNumber", "repricedLineItemReferenceNumber",
		"adjustedRepricedLineItemReferenceNumber", "mammographyCertificationNumber",
		"clinicalLaboratoryImprovementAmendmentNumber", "referringCliaNumber", "immunizationBatchNumber" })
@Generated("jsonschema2pojo")
public class ServiceLineReferenceInformation {

	@JsonProperty("priorAuthorization")
	private List<PriorAuthorization> priorAuthorization = null;
	@JsonProperty("referralNumber")
	private List<String> referralNumber = null;
	@JsonProperty("repricedLineItemReferenceNumber")
	private String repricedLineItemReferenceNumber;
	@JsonProperty("adjustedRepricedLineItemReferenceNumber")
	private String adjustedRepricedLineItemReferenceNumber;
	@JsonProperty("mammographyCertificationNumber")
	private String mammographyCertificationNumber;
	@JsonProperty("clinicalLaboratoryImprovementAmendmentNumber")
	private String clinicalLaboratoryImprovementAmendmentNumber;
	@JsonProperty("referringCliaNumber")
	private String referringCliaNumber;
	@JsonProperty("immunizationBatchNumber")
	private String immunizationBatchNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("priorAuthorization")
	public List<PriorAuthorization> getPriorAuthorization() {
		return priorAuthorization;
	}

	@JsonProperty("priorAuthorization")
	public void setPriorAuthorization(List<PriorAuthorization> priorAuthorization) {
		this.priorAuthorization = priorAuthorization;
	}

	@JsonProperty("referralNumber")
	public List<String> getReferralNumber() {
		return referralNumber;
	}

	@JsonProperty("referralNumber")
	public void setReferralNumber(List<String> referralNumber) {
		this.referralNumber = referralNumber;
	}

	@JsonProperty("repricedLineItemReferenceNumber")
	public String getRepricedLineItemReferenceNumber() {
		return repricedLineItemReferenceNumber;
	}

	@JsonProperty("repricedLineItemReferenceNumber")
	public void setRepricedLineItemReferenceNumber(String repricedLineItemReferenceNumber) {
		this.repricedLineItemReferenceNumber = repricedLineItemReferenceNumber;
	}

	@JsonProperty("adjustedRepricedLineItemReferenceNumber")
	public String getAdjustedRepricedLineItemReferenceNumber() {
		return adjustedRepricedLineItemReferenceNumber;
	}

	@JsonProperty("adjustedRepricedLineItemReferenceNumber")
	public void setAdjustedRepricedLineItemReferenceNumber(String adjustedRepricedLineItemReferenceNumber) {
		this.adjustedRepricedLineItemReferenceNumber = adjustedRepricedLineItemReferenceNumber;
	}

	@JsonProperty("mammographyCertificationNumber")
	public String getMammographyCertificationNumber() {
		return mammographyCertificationNumber;
	}

	@JsonProperty("mammographyCertificationNumber")
	public void setMammographyCertificationNumber(String mammographyCertificationNumber) {
		this.mammographyCertificationNumber = mammographyCertificationNumber;
	}

	@JsonProperty("clinicalLaboratoryImprovementAmendmentNumber")
	public String getClinicalLaboratoryImprovementAmendmentNumber() {
		return clinicalLaboratoryImprovementAmendmentNumber;
	}

	@JsonProperty("clinicalLaboratoryImprovementAmendmentNumber")
	public void setClinicalLaboratoryImprovementAmendmentNumber(String clinicalLaboratoryImprovementAmendmentNumber) {
		this.clinicalLaboratoryImprovementAmendmentNumber = clinicalLaboratoryImprovementAmendmentNumber;
	}

	@JsonProperty("referringCliaNumber")
	public String getReferringCliaNumber() {
		return referringCliaNumber;
	}

	@JsonProperty("referringCliaNumber")
	public void setReferringCliaNumber(String referringCliaNumber) {
		this.referringCliaNumber = referringCliaNumber;
	}

	@JsonProperty("immunizationBatchNumber")
	public String getImmunizationBatchNumber() {
		return immunizationBatchNumber;
	}

	@JsonProperty("immunizationBatchNumber")
	public void setImmunizationBatchNumber(String immunizationBatchNumber) {
		this.immunizationBatchNumber = immunizationBatchNumber;
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