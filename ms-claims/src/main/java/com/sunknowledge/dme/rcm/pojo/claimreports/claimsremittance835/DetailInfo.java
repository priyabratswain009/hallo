package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;
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
    "assignedNumber",
    "paymentInfo"
})
@Generated("jsonschema2pojo")
public class DetailInfo {

    @JsonProperty("assignedNumber")
    private String assignedNumber;
    @JsonProperty("paymentInfo")
    private List<PaymentInfo> paymentInfo = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("assignedNumber")
    public String getAssignedNumber() {
        return assignedNumber;
    }

    @JsonProperty("assignedNumber")
    public void setAssignedNumber(String assignedNumber) {
        this.assignedNumber = assignedNumber;
    }

    @JsonProperty("paymentInfo")
    public List<PaymentInfo> getPaymentInfo() {
        return paymentInfo;
    }

    @JsonProperty("paymentInfo")
    public void setPaymentInfo(List<PaymentInfo> paymentInfo) {
        this.paymentInfo = paymentInfo;
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
