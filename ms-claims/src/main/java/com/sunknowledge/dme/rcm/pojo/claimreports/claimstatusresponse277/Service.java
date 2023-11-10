package com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277;

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
    "serviceIdQualifierCode",
    "serviceIdQualifierCodeValue",
    "procedureCode",
    "procedureModifiers",
    "chargeAmount",
    "amountPaid",
    "submittedUnits"
})
@Generated("jsonschema2pojo")
public class Service {

    @JsonProperty("serviceIdQualifierCode")
    private String serviceIdQualifierCode;
    @JsonProperty("serviceIdQualifierCodeValue")
    private String serviceIdQualifierCodeValue;
    @JsonProperty("procedureCode")
    private String procedureCode;
    @JsonProperty("procedureModifiers")
    private List<String> procedureModifiers = null;
    @JsonProperty("chargeAmount")
    private String chargeAmount;
    @JsonProperty("amountPaid")
    private String amountPaid;
    @JsonProperty("submittedUnits")
    private String submittedUnits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("serviceIdQualifierCode")
    public String getServiceIdQualifierCode() {
        return serviceIdQualifierCode;
    }

    @JsonProperty("serviceIdQualifierCode")
    public void setServiceIdQualifierCode(String serviceIdQualifierCode) {
        this.serviceIdQualifierCode = serviceIdQualifierCode;
    }

    @JsonProperty("serviceIdQualifierCodeValue")
    public String getServiceIdQualifierCodeValue() {
        return serviceIdQualifierCodeValue;
    }

    @JsonProperty("serviceIdQualifierCodeValue")
    public void setServiceIdQualifierCodeValue(String serviceIdQualifierCodeValue) {
        this.serviceIdQualifierCodeValue = serviceIdQualifierCodeValue;
    }

    @JsonProperty("procedureCode")
    public String getProcedureCode() {
        return procedureCode;
    }

    @JsonProperty("procedureCode")
    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    @JsonProperty("procedureModifiers")
    public List<String> getProcedureModifiers() {
        return procedureModifiers;
    }

    @JsonProperty("procedureModifiers")
    public void setProcedureModifiers(List<String> procedureModifiers) {
        this.procedureModifiers = procedureModifiers;
    }

    @JsonProperty("chargeAmount")
    public String getChargeAmount() {
        return chargeAmount;
    }

    @JsonProperty("chargeAmount")
    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @JsonProperty("amountPaid")
    public String getAmountPaid() {
        return amountPaid;
    }

    @JsonProperty("amountPaid")
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    @JsonProperty("submittedUnits")
    public String getSubmittedUnits() {
        return submittedUnits;
    }

    @JsonProperty("submittedUnits")
    public void setSubmittedUnits(String submittedUnits) {
        this.submittedUnits = submittedUnits;
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
