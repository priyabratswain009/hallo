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
    "subscriber",
    "dependent",
    "claims"
})
@Generated("jsonschema2pojo")
public class PatientClaimStatusDetail {

    @JsonProperty("subscriber")
    private Subscriber subscriber;
    @JsonProperty("dependent")
    private Dependent dependent;
    @JsonProperty("claims")
    private List<Claim> claims = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("subscriber")
    public Subscriber getSubscriber() {
        return subscriber;
    }

    @JsonProperty("subscriber")
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @JsonProperty("dependent")
    public Dependent getDependent() {
        return dependent;
    }

    @JsonProperty("dependent")
    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }

    @JsonProperty("claims")
    public List<Claim> getClaims() {
        return claims;
    }

    @JsonProperty("claims")
    public void setClaims(List<Claim> claims) {
        this.claims = claims;
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
