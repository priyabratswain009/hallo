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
    "traceTypeCode",
    "checkOrEFTTraceNumber",
    "originatingCompanyIdentifier"
})
@Generated("jsonschema2pojo")
public class PaymentAndRemitReassociationDetails {

    @JsonProperty("traceTypeCode")
    private String traceTypeCode;
    @JsonProperty("checkOrEFTTraceNumber")
    private String checkOrEFTTraceNumber;
    @JsonProperty("originatingCompanyIdentifier")
    private String originatingCompanyIdentifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("traceTypeCode")
    public String getTraceTypeCode() {
        return traceTypeCode;
    }

    @JsonProperty("traceTypeCode")
    public void setTraceTypeCode(String traceTypeCode) {
        this.traceTypeCode = traceTypeCode;
    }

    @JsonProperty("checkOrEFTTraceNumber")
    public String getCheckOrEFTTraceNumber() {
        return checkOrEFTTraceNumber;
    }

    @JsonProperty("checkOrEFTTraceNumber")
    public void setCheckOrEFTTraceNumber(String checkOrEFTTraceNumber) {
        this.checkOrEFTTraceNumber = checkOrEFTTraceNumber;
    }

    @JsonProperty("originatingCompanyIdentifier")
    public String getOriginatingCompanyIdentifier() {
        return originatingCompanyIdentifier;
    }

    @JsonProperty("originatingCompanyIdentifier")
    public void setOriginatingCompanyIdentifier(String originatingCompanyIdentifier) {
        this.originatingCompanyIdentifier = originatingCompanyIdentifier;
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
