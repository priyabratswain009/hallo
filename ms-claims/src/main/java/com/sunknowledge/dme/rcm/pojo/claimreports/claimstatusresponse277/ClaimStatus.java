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
    "referencedTransactionTraceNumber",
    "informationClaimStatuses",
    "tradingPartnerClaimNumber",
    "patientAccountNumber",
    "clearinghouseTraceNumber",
    "claimServiceBeginDate",
    "claimServiceEndDate"
})
@Generated("jsonschema2pojo")
public class ClaimStatus {

    @JsonProperty("referencedTransactionTraceNumber")
    private String referencedTransactionTraceNumber;
    @JsonProperty("informationClaimStatuses")
    private List<InformationClaimStatus> informationClaimStatuses = null;
    @JsonProperty("tradingPartnerClaimNumber")
    private String tradingPartnerClaimNumber;
    @JsonProperty("patientAccountNumber")
    private String patientAccountNumber;
    @JsonProperty("clearinghouseTraceNumber")
    private String clearinghouseTraceNumber;
    @JsonProperty("claimServiceBeginDate")
    private String claimServiceBeginDate;
    @JsonProperty("claimServiceEndDate")
    private String claimServiceEndDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("referencedTransactionTraceNumber")
    public String getReferencedTransactionTraceNumber() {
        return referencedTransactionTraceNumber;
    }

    @JsonProperty("referencedTransactionTraceNumber")
    public void setReferencedTransactionTraceNumber(String referencedTransactionTraceNumber) {
        this.referencedTransactionTraceNumber = referencedTransactionTraceNumber;
    }

    @JsonProperty("informationClaimStatuses")
    public List<InformationClaimStatus> getInformationClaimStatuses() {
        return informationClaimStatuses;
    }

    @JsonProperty("informationClaimStatuses")
    public void setInformationClaimStatuses(List<InformationClaimStatus> informationClaimStatuses) {
        this.informationClaimStatuses = informationClaimStatuses;
    }

    @JsonProperty("tradingPartnerClaimNumber")
    public String getTradingPartnerClaimNumber() {
        return tradingPartnerClaimNumber;
    }

    @JsonProperty("tradingPartnerClaimNumber")
    public void setTradingPartnerClaimNumber(String tradingPartnerClaimNumber) {
        this.tradingPartnerClaimNumber = tradingPartnerClaimNumber;
    }

    @JsonProperty("patientAccountNumber")
    public String getPatientAccountNumber() {
        return patientAccountNumber;
    }

    @JsonProperty("patientAccountNumber")
    public void setPatientAccountNumber(String patientAccountNumber) {
        this.patientAccountNumber = patientAccountNumber;
    }

    @JsonProperty("clearinghouseTraceNumber")
    public String getClearinghouseTraceNumber() {
        return clearinghouseTraceNumber;
    }

    @JsonProperty("clearinghouseTraceNumber")
    public void setClearinghouseTraceNumber(String clearinghouseTraceNumber) {
        this.clearinghouseTraceNumber = clearinghouseTraceNumber;
    }

    @JsonProperty("claimServiceBeginDate")
    public String getClaimServiceBeginDate() {
        return claimServiceBeginDate;
    }

    @JsonProperty("claimServiceBeginDate")
    public void setClaimServiceBeginDate(String claimServiceBeginDate) {
        this.claimServiceBeginDate = claimServiceBeginDate;
    }

    @JsonProperty("claimServiceEndDate")
    public String getClaimServiceEndDate() {
        return claimServiceEndDate;
    }

    @JsonProperty("claimServiceEndDate")
    public void setClaimServiceEndDate(String claimServiceEndDate) {
        this.claimServiceEndDate = claimServiceEndDate;
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
