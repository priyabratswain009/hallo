package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "discoveryClient",
    "eureka"
})
public class DiscoveryComponents {
    @JsonProperty("discoveryClient")
    private DiscoveryClient discoveryClient;
    @JsonProperty("eureka")
    private Eureka eureka;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("discoveryClient")
    public DiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }

    @JsonProperty("discoveryClient")
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @JsonProperty("eureka")
    public Eureka getEureka() {
        return eureka;
    }

    @JsonProperty("eureka")
    public void setEureka(Eureka eureka) {
        this.eureka = eureka;
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
