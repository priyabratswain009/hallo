package com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277;

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
    "name",
    "payerIdentificationNumber",
    "address",
    "businessContactInformation",
    "technicalContactInformation"
})
@Generated("jsonschema2pojo")
public class Payer {

    @JsonProperty("name")
    private String name;
    @JsonProperty("payerIdentificationNumber")
    private String payerIdentificationNumber;
    @JsonProperty("address")
    private PayerAddress address;
    @JsonProperty("businessContactInformation")
    private BusinessContactInformation businessContactInformation;
    @JsonProperty("technicalContactInformation")
    private List<TechnicalContactInformation> technicalContactInformation = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("payerIdentificationNumber")
    public String getPayerIdentificationNumber() {
        return payerIdentificationNumber;
    }

    @JsonProperty("payerIdentificationNumber")
    public void setPayerIdentificationNumber(String payerIdentificationNumber) {
        this.payerIdentificationNumber = payerIdentificationNumber;
    }

    @JsonProperty("address")
    public PayerAddress getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(PayerAddress address) {
        this.address = address;
    }

    @JsonProperty("businessContactInformation")
    public BusinessContactInformation getBusinessContactInformation() {
        return businessContactInformation;
    }

    @JsonProperty("businessContactInformation")
    public void setBusinessContactInformation(BusinessContactInformation businessContactInformation) {
        this.businessContactInformation = businessContactInformation;
    }

    @JsonProperty("technicalContactInformation")
    public List<TechnicalContactInformation> getTechnicalContactInformation() {
        return technicalContactInformation;
    }

    @JsonProperty("technicalContactInformation")
    public void setTechnicalContactInformation(List<TechnicalContactInformation> technicalContactInformation) {
        this.technicalContactInformation = technicalContactInformation;
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
