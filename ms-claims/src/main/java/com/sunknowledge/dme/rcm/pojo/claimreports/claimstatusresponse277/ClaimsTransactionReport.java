package com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277;

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
@JsonPropertyOrder({
    "claimStatusResponseTransaction",
    "claimsRemittanceTransaction"
})
@Generated("jsonschema2pojo")
public class ClaimsTransactionReport {

    @JsonProperty("claimStatusResponseTransaction")
    private ClaimStatusResponseTransaction claimStatusResponseTransaction;
    @JsonProperty("claimsRemittanceTransaction")
    private Object claimsRemittanceTransaction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("claimStatusResponseTransaction")
    public ClaimStatusResponseTransaction getClaimStatusResponseTransaction() {
        return claimStatusResponseTransaction;
    }

    @JsonProperty("claimStatusResponseTransaction")
    public void setClaimStatusResponseTransaction(ClaimStatusResponseTransaction claimStatusResponseTransaction) {
        this.claimStatusResponseTransaction = claimStatusResponseTransaction;
    }

    @JsonProperty("claimsRemittanceTransaction")
    public Object getClaimsRemittanceTransaction() {
        return claimsRemittanceTransaction;
    }

    @JsonProperty("claimsRemittanceTransaction")
    public void setClaimsRemittanceTransaction(Object claimsRemittanceTransaction) {
        this.claimsRemittanceTransaction = claimsRemittanceTransaction;
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
