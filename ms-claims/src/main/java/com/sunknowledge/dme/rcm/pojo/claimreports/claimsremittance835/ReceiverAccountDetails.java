package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;

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
    "receiverDfiIdNumberQualifier",
    "receiverDfiIdentificationNumber",
    "receiverAccountNumberQualifier",
    "receiverAccountNumber"
})
@Generated("jsonschema2pojo")
public class ReceiverAccountDetails {

    @JsonProperty("receiverDfiIdNumberQualifier")
    private String receiverDfiIdNumberQualifier;
    @JsonProperty("receiverDfiIdentificationNumber")
    private String receiverDfiIdentificationNumber;
    @JsonProperty("receiverAccountNumberQualifier")
    private String receiverAccountNumberQualifier;
    @JsonProperty("receiverAccountNumber")
    private String receiverAccountNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("receiverDfiIdNumberQualifier")
    public String getReceiverDfiIdNumberQualifier() {
        return receiverDfiIdNumberQualifier;
    }

    @JsonProperty("receiverDfiIdNumberQualifier")
    public void setReceiverDfiIdNumberQualifier(String receiverDfiIdNumberQualifier) {
        this.receiverDfiIdNumberQualifier = receiverDfiIdNumberQualifier;
    }

    @JsonProperty("receiverDfiIdentificationNumber")
    public String getReceiverDfiIdentificationNumber() {
        return receiverDfiIdentificationNumber;
    }

    @JsonProperty("receiverDfiIdentificationNumber")
    public void setReceiverDfiIdentificationNumber(String receiverDfiIdentificationNumber) {
        this.receiverDfiIdentificationNumber = receiverDfiIdentificationNumber;
    }

    @JsonProperty("receiverAccountNumberQualifier")
    public String getReceiverAccountNumberQualifier() {
        return receiverAccountNumberQualifier;
    }

    @JsonProperty("receiverAccountNumberQualifier")
    public void setReceiverAccountNumberQualifier(String receiverAccountNumberQualifier) {
        this.receiverAccountNumberQualifier = receiverAccountNumberQualifier;
    }

    @JsonProperty("receiverAccountNumber")
    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    @JsonProperty("receiverAccountNumber")
    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
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
