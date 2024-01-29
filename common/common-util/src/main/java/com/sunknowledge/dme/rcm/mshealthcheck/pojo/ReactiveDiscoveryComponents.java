package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonPropertyOrder({
    "SimpleReactiveDiscoveryClient",
    "SpringCloudEurekaReactiveDiscoveryClient"
})
public class ReactiveDiscoveryComponents {
    @JsonProperty("SimpleReactiveDiscoveryClient")
    private SimpleReactiveDiscoveryClient simpleReactiveDiscoveryClient;
    @JsonProperty("SpringCloudEurekaReactiveDiscoveryClient")
    private SpringCloudEurekaReactiveDiscoveryClient springCloudEurekaReactiveDiscoveryClient;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("SimpleReactiveDiscoveryClient")
    public SimpleReactiveDiscoveryClient getSimpleReactiveDiscoveryClient() {
        return simpleReactiveDiscoveryClient;
    }

    @JsonProperty("SimpleReactiveDiscoveryClient")
    public void setSimpleReactiveDiscoveryClient(SimpleReactiveDiscoveryClient simpleReactiveDiscoveryClient) {
        this.simpleReactiveDiscoveryClient = simpleReactiveDiscoveryClient;
    }

    @JsonProperty("SpringCloudEurekaReactiveDiscoveryClient")
    public SpringCloudEurekaReactiveDiscoveryClient getSpringCloudEurekaReactiveDiscoveryClient() {
        return springCloudEurekaReactiveDiscoveryClient;
    }

    @JsonProperty("SpringCloudEurekaReactiveDiscoveryClient")
    public void setSpringCloudEurekaReactiveDiscoveryClient(SpringCloudEurekaReactiveDiscoveryClient springCloudEurekaReactiveDiscoveryClient) {
        this.springCloudEurekaReactiveDiscoveryClient = springCloudEurekaReactiveDiscoveryClient;
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
