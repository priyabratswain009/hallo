package com.sunknowledge.changehealthcare.pojo.claimreports.claimsremittance835;

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
    "informationClaimStatuses"
})
@Generated("jsonschema2pojo")
public class ClaimStatus {

    @JsonProperty("referencedTransactionTraceNumber")
    private String referencedTransactionTraceNumber;
    @JsonProperty("informationClaimStatuses")
    private List<InformationClaimStatus> informationClaimStatuses = null;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
