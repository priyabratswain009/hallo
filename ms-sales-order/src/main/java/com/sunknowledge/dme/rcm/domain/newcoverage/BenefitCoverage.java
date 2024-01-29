
package com.sunknowledge.dme.rcm.domain.newcoverage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "links",
    "id",
    "customerId",
    "controlNumber",
    "status",
    "statusCode",
    "createdDate",
    "updatedDate",
    "expirationDate",
    "asOfDate",
    "requestedServiceType",
    "validationMessages",
    "subscriber",
    "patient",
    "payer",
    "requestingProvider",
    "plans",
    "supplementalInformation"
})
@Generated("jsonschema2pojo")
public class BenefitCoverage {

    @JsonProperty("links")
    public Links links;
    @JsonProperty("id")
    public String id;
    @JsonProperty("customerId")
    public String customerId;
    @JsonProperty("controlNumber")
    public String controlNumber;
    @JsonProperty("status")
    public String status;
    @JsonProperty("statusCode")
    public String statusCode;
    @JsonProperty("createdDate")
    public String createdDate;
    @JsonProperty("updatedDate")
    public String updatedDate;
    @JsonProperty("expirationDate")
    public String expirationDate;
    @JsonProperty("asOfDate")
    public String asOfDate;
    @JsonProperty("requestedServiceType")
    public List<RequestedServiceType> requestedServiceType;
    @JsonProperty("validationMessages")
    public List<Object> validationMessages;
    @JsonProperty("subscriber")
    public Subscriber subscriber;
    @JsonProperty("patient")
    public Patient patient;
    @JsonProperty("payer")
    public Payer payer;
    @JsonProperty("requestingProvider")
    public RequestingProvider requestingProvider;
    @JsonProperty("plans")
    public List<Plan> plans;
    @JsonProperty("supplementalInformation")
    public SupplementalInformation supplementalInformation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
