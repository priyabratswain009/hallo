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
@JsonPropertyOrder({ "claimPaymentRemarkCode", "reimbursementRate", "hcpcsPayableAmount",
		"endStageRenalDiseasePaymentAmount", "nonPayableProfessionalComponentBilledAmount" })
@Generated("jsonschema2pojo")
public class MedicareOutpatientAdjudication {

	@JsonProperty("claimPaymentRemarkCode")
	private List<String> claimPaymentRemarkCode = null;
	@JsonProperty("reimbursementRate")
	private String reimbursementRate;
	@JsonProperty("hcpcsPayableAmount")
	private String hcpcsPayableAmount;
	@JsonProperty("endStageRenalDiseasePaymentAmount")
	private String endStageRenalDiseasePaymentAmount;
	@JsonProperty("nonPayableProfessionalComponentBilledAmount")
	private String nonPayableProfessionalComponentBilledAmount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("claimPaymentRemarkCode")
	public List<String> getClaimPaymentRemarkCode() {
		return claimPaymentRemarkCode;
	}

	@JsonProperty("claimPaymentRemarkCode")
	public void setClaimPaymentRemarkCode(List<String> claimPaymentRemarkCode) {
		this.claimPaymentRemarkCode = claimPaymentRemarkCode;
	}

	@JsonProperty("reimbursementRate")
	public String getReimbursementRate() {
		return reimbursementRate;
	}

	@JsonProperty("reimbursementRate")
	public void setReimbursementRate(String reimbursementRate) {
		this.reimbursementRate = reimbursementRate;
	}

	@JsonProperty("hcpcsPayableAmount")
	public String getHcpcsPayableAmount() {
		return hcpcsPayableAmount;
	}

	@JsonProperty("hcpcsPayableAmount")
	public void setHcpcsPayableAmount(String hcpcsPayableAmount) {
		this.hcpcsPayableAmount = hcpcsPayableAmount;
	}

	@JsonProperty("endStageRenalDiseasePaymentAmount")
	public String getEndStageRenalDiseasePaymentAmount() {
		return endStageRenalDiseasePaymentAmount;
	}

	@JsonProperty("endStageRenalDiseasePaymentAmount")
	public void setEndStageRenalDiseasePaymentAmount(String endStageRenalDiseasePaymentAmount) {
		this.endStageRenalDiseasePaymentAmount = endStageRenalDiseasePaymentAmount;
	}

	@JsonProperty("nonPayableProfessionalComponentBilledAmount")
	public String getNonPayableProfessionalComponentBilledAmount() {
		return nonPayableProfessionalComponentBilledAmount;
	}

	@JsonProperty("nonPayableProfessionalComponentBilledAmount")
	public void setNonPayableProfessionalComponentBilledAmount(String nonPayableProfessionalComponentBilledAmount) {
		this.nonPayableProfessionalComponentBilledAmount = nonPayableProfessionalComponentBilledAmount;
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