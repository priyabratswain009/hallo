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
    "organizationName",
    "payerIdentification",
    "claimStatusTransactions"
})
@Generated("jsonschema2pojo")
public class Payer {

    @JsonProperty("organizationName")
    private String organizationName;
    @JsonProperty("payerIdentification")
    private String payerIdentification;
    @JsonProperty("claimStatusTransactions")
    private List<ClaimStatusTransaction> claimStatusTransactions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("organizationName")
    public String getOrganizationName() {
        return organizationName;
    }

    @JsonProperty("organizationName")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @JsonProperty("payerIdentification")
    public String getPayerIdentification() {
        return payerIdentification;
    }

    @JsonProperty("payerIdentification")
    public void setPayerIdentification(String payerIdentification) {
        this.payerIdentification = payerIdentification;
    }

    @JsonProperty("claimStatusTransactions")
    public List<ClaimStatusTransaction> getClaimStatusTransactions() {
        return claimStatusTransactions;
    }

    @JsonProperty("claimStatusTransactions")
    public void setClaimStatusTransactions(List<ClaimStatusTransaction> claimStatusTransactions) {
        this.claimStatusTransactions = claimStatusTransactions;
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
