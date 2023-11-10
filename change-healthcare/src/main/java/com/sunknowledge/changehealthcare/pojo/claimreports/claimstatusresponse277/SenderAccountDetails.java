package com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277;

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
    "senderDfiIdNumberQualifier",
    "senderDFIIdentifier",
    "senderAccountNumberQualifier",
    "senderAccountNumber"
})
@Generated("jsonschema2pojo")
public class SenderAccountDetails {

    @JsonProperty("senderDfiIdNumberQualifier")
    private String senderDfiIdNumberQualifier;
    @JsonProperty("senderDFIIdentifier")
    private String senderDFIIdentifier;
    @JsonProperty("senderAccountNumberQualifier")
    private String senderAccountNumberQualifier;
    @JsonProperty("senderAccountNumber")
    private String senderAccountNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("senderDfiIdNumberQualifier")
    public String getSenderDfiIdNumberQualifier() {
        return senderDfiIdNumberQualifier;
    }

    @JsonProperty("senderDfiIdNumberQualifier")
    public void setSenderDfiIdNumberQualifier(String senderDfiIdNumberQualifier) {
        this.senderDfiIdNumberQualifier = senderDfiIdNumberQualifier;
    }

    @JsonProperty("senderDFIIdentifier")
    public String getSenderDFIIdentifier() {
        return senderDFIIdentifier;
    }

    @JsonProperty("senderDFIIdentifier")
    public void setSenderDFIIdentifier(String senderDFIIdentifier) {
        this.senderDFIIdentifier = senderDFIIdentifier;
    }

    @JsonProperty("senderAccountNumberQualifier")
    public String getSenderAccountNumberQualifier() {
        return senderAccountNumberQualifier;
    }

    @JsonProperty("senderAccountNumberQualifier")
    public void setSenderAccountNumberQualifier(String senderAccountNumberQualifier) {
        this.senderAccountNumberQualifier = senderAccountNumberQualifier;
    }

    @JsonProperty("senderAccountNumber")
    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    @JsonProperty("senderAccountNumber")
    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
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
