package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "applications"
})
public class ApplicationDetails {
    @JsonProperty("applications")
    private MicroserviceApplications applications;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("applications")
    public MicroserviceApplications getApplications() {
        return applications;
    }

    @JsonProperty("applications")
    public void setApplications(MicroserviceApplications applications) {
        this.applications = applications;
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
