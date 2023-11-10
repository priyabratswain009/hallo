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
    "serviceDate",
    "servicePaymentInformation",
    "serviceAdjustments",
    "serviceIdentification",
    "serviceSupplementalAmounts"
})
@Generated("jsonschema2pojo")
public class ServiceLine {

    @JsonProperty("serviceDate")
    private String serviceDate;
    @JsonProperty("servicePaymentInformation")
    private ServicePaymentInformation servicePaymentInformation;
    @JsonProperty("serviceAdjustments")
    private List<ServiceAdjustment> serviceAdjustments = null;
    @JsonProperty("serviceIdentification")
    private ServiceIdentification serviceIdentification;
    @JsonProperty("serviceSupplementalAmounts")
    private ServiceSupplementalAmounts serviceSupplementalAmounts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("serviceDate")
    public String getServiceDate() {
        return serviceDate;
    }

    @JsonProperty("serviceDate")
    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    @JsonProperty("servicePaymentInformation")
    public ServicePaymentInformation getServicePaymentInformation() {
        return servicePaymentInformation;
    }

    @JsonProperty("servicePaymentInformation")
    public void setServicePaymentInformation(ServicePaymentInformation servicePaymentInformation) {
        this.servicePaymentInformation = servicePaymentInformation;
    }

    @JsonProperty("serviceAdjustments")
    public List<ServiceAdjustment> getServiceAdjustments() {
        return serviceAdjustments;
    }

    @JsonProperty("serviceAdjustments")
    public void setServiceAdjustments(List<ServiceAdjustment> serviceAdjustments) {
        this.serviceAdjustments = serviceAdjustments;
    }

    @JsonProperty("serviceIdentification")
    public ServiceIdentification getServiceIdentification() {
        return serviceIdentification;
    }

    @JsonProperty("serviceIdentification")
    public void setServiceIdentification(ServiceIdentification serviceIdentification) {
        this.serviceIdentification = serviceIdentification;
    }

    @JsonProperty("serviceSupplementalAmounts")
    public ServiceSupplementalAmounts getServiceSupplementalAmounts() {
        return serviceSupplementalAmounts;
    }

    @JsonProperty("serviceSupplementalAmounts")
    public void setServiceSupplementalAmounts(ServiceSupplementalAmounts serviceSupplementalAmounts) {
        this.serviceSupplementalAmounts = serviceSupplementalAmounts;
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
