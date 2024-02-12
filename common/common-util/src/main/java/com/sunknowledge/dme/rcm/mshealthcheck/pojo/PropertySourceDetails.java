package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "propertySources"
})
public class PropertySourceDetails {
    @JsonProperty("propertySources")
    private List<String> propertySources;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("propertySources")
    public List<String> getPropertySources() {
        return propertySources;
    }

    @JsonProperty("propertySources")
    public void setPropertySources(List<String> propertySources) {
        this.propertySources = propertySources;
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
