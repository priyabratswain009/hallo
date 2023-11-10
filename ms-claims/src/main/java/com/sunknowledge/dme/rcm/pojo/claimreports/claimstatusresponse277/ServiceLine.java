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
    "service",
    "serviceClaimStatuses",
    "beginServiceLineDate",
    "endServiceLineDate"
})
@Generated("jsonschema2pojo")
public class ServiceLine {

    @JsonProperty("service")
    private Service service;
    @JsonProperty("serviceClaimStatuses")
    private List<ServiceClaimStatus> serviceClaimStatuses = null;
    @JsonProperty("beginServiceLineDate")
    private String beginServiceLineDate;
    @JsonProperty("endServiceLineDate")
    private String endServiceLineDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("service")
    public Service getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(Service service) {
        this.service = service;
    }

    @JsonProperty("serviceClaimStatuses")
    public List<ServiceClaimStatus> getServiceClaimStatuses() {
        return serviceClaimStatuses;
    }

    @JsonProperty("serviceClaimStatuses")
    public void setServiceClaimStatuses(List<ServiceClaimStatus> serviceClaimStatuses) {
        this.serviceClaimStatuses = serviceClaimStatuses;
    }

    @JsonProperty("beginServiceLineDate")
    public String getBeginServiceLineDate() {
        return beginServiceLineDate;
    }

    @JsonProperty("beginServiceLineDate")
    public void setBeginServiceLineDate(String beginServiceLineDate) {
        this.beginServiceLineDate = beginServiceLineDate;
    }

    @JsonProperty("endServiceLineDate")
    public String getEndServiceLineDate() {
        return endServiceLineDate;
    }

    @JsonProperty("endServiceLineDate")
    public void setEndServiceLineDate(String endServiceLineDate) {
        this.endServiceLineDate = endServiceLineDate;
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
