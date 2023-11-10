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
    "provider",
    "claimStatusDetails"
})
@Generated("jsonschema2pojo")
public class ClaimStatusTransaction {

    @JsonProperty("provider")
    private Provider provider;
    @JsonProperty("claimStatusDetails")
    private List<ClaimStatusDetail> claimStatusDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("provider")
    public Provider getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @JsonProperty("claimStatusDetails")
    public List<ClaimStatusDetail> getClaimStatusDetails() {
        return claimStatusDetails;
    }

    @JsonProperty("claimStatusDetails")
    public void setClaimStatusDetails(List<ClaimStatusDetail> claimStatusDetails) {
        this.claimStatusDetails = claimStatusDetails;
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
