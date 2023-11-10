package com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277;

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
@JsonPropertyOrder({
    "statusInformationEffectiveDate",
    "totalClaimChargeAmount",
    "claimPaymentAmount",
    "adjudicatedFinalizedDate",
    "remittanceDate",
    "remittanceTraceNumber",
    "informationStatuses"
})
@Generated("jsonschema2pojo")
public class InformationClaimStatus {

    @JsonProperty("statusInformationEffectiveDate")
    private String statusInformationEffectiveDate;
    @JsonProperty("totalClaimChargeAmount")
    private String totalClaimChargeAmount;
    @JsonProperty("claimPaymentAmount")
    private String claimPaymentAmount;
    @JsonProperty("adjudicatedFinalizedDate")
    private String adjudicatedFinalizedDate;
    @JsonProperty("remittanceDate")
    private String remittanceDate;
    @JsonProperty("remittanceTraceNumber")
    private String remittanceTraceNumber;
    @JsonProperty("informationStatuses")
    private List<InformationStatus> informationStatuses = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("statusInformationEffectiveDate")
    public String getStatusInformationEffectiveDate() {
        return statusInformationEffectiveDate;
    }

    @JsonProperty("statusInformationEffectiveDate")
    public void setStatusInformationEffectiveDate(String statusInformationEffectiveDate) {
        this.statusInformationEffectiveDate = statusInformationEffectiveDate;
    }

    @JsonProperty("totalClaimChargeAmount")
    public String getTotalClaimChargeAmount() {
        return totalClaimChargeAmount;
    }

    @JsonProperty("totalClaimChargeAmount")
    public void setTotalClaimChargeAmount(String totalClaimChargeAmount) {
        this.totalClaimChargeAmount = totalClaimChargeAmount;
    }

    @JsonProperty("claimPaymentAmount")
    public String getClaimPaymentAmount() {
        return claimPaymentAmount;
    }

    @JsonProperty("claimPaymentAmount")
    public void setClaimPaymentAmount(String claimPaymentAmount) {
        this.claimPaymentAmount = claimPaymentAmount;
    }

    @JsonProperty("adjudicatedFinalizedDate")
    public String getAdjudicatedFinalizedDate() {
        return adjudicatedFinalizedDate;
    }

    @JsonProperty("adjudicatedFinalizedDate")
    public void setAdjudicatedFinalizedDate(String adjudicatedFinalizedDate) {
        this.adjudicatedFinalizedDate = adjudicatedFinalizedDate;
    }

    @JsonProperty("remittanceDate")
    public String getRemittanceDate() {
        return remittanceDate;
    }

    @JsonProperty("remittanceDate")
    public void setRemittanceDate(String remittanceDate) {
        this.remittanceDate = remittanceDate;
    }

    @JsonProperty("remittanceTraceNumber")
    public String getRemittanceTraceNumber() {
        return remittanceTraceNumber;
    }

    @JsonProperty("remittanceTraceNumber")
    public void setRemittanceTraceNumber(String remittanceTraceNumber) {
        this.remittanceTraceNumber = remittanceTraceNumber;
    }

    @JsonProperty("informationStatuses")
    public List<InformationStatus> getInformationStatuses() {
        return informationStatuses;
    }

    @JsonProperty("informationStatuses")
    public void setInformationStatuses(List<InformationStatus> informationStatuses) {
        this.informationStatuses = informationStatuses;
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
