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
    "serviceProvider",
    "patientClaimStatusDetails"
})
@Generated("jsonschema2pojo")
public class ClaimStatusDetail {

    @JsonProperty("serviceProvider")
    private ServiceProvider serviceProvider;
    @JsonProperty("patientClaimStatusDetails")
    private List<PatientClaimStatusDetail> patientClaimStatusDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("serviceProvider")
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    @JsonProperty("serviceProvider")
    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @JsonProperty("patientClaimStatusDetails")
    public List<PatientClaimStatusDetail> getPatientClaimStatusDetails() {
        return patientClaimStatusDetails;
    }

    @JsonProperty("patientClaimStatusDetails")
    public void setPatientClaimStatusDetails(List<PatientClaimStatusDetail> patientClaimStatusDetails) {
        this.patientClaimStatusDetails = patientClaimStatusDetails;
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
