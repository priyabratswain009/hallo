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
    "claimPaymentRemarkCode1",
    "claimPaymentRemarkCode2",
    "claimPaymentRemarkCode3"
})
@Generated("jsonschema2pojo")
public class OutpatientAdjudication {

    @JsonProperty("claimPaymentRemarkCode1")
    private String claimPaymentRemarkCode1;
    @JsonProperty("claimPaymentRemarkCode2")
    private String claimPaymentRemarkCode2;
    @JsonProperty("claimPaymentRemarkCode3")
    private String claimPaymentRemarkCode3;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("claimPaymentRemarkCode1")
    public String getClaimPaymentRemarkCode1() {
        return claimPaymentRemarkCode1;
    }

    @JsonProperty("claimPaymentRemarkCode1")
    public void setClaimPaymentRemarkCode1(String claimPaymentRemarkCode1) {
        this.claimPaymentRemarkCode1 = claimPaymentRemarkCode1;
    }

    @JsonProperty("claimPaymentRemarkCode2")
    public String getClaimPaymentRemarkCode2() {
        return claimPaymentRemarkCode2;
    }

    @JsonProperty("claimPaymentRemarkCode2")
    public void setClaimPaymentRemarkCode2(String claimPaymentRemarkCode2) {
        this.claimPaymentRemarkCode2 = claimPaymentRemarkCode2;
    }

    @JsonProperty("claimPaymentRemarkCode3")
    public String getClaimPaymentRemarkCode3() {
        return claimPaymentRemarkCode3;
    }

    @JsonProperty("claimPaymentRemarkCode3")
    public void setClaimPaymentRemarkCode3(String claimPaymentRemarkCode3) {
        this.claimPaymentRemarkCode3 = claimPaymentRemarkCode3;
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
