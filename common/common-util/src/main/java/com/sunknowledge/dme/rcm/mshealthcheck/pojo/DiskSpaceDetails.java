package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "free",
    "threshold",
    "exists"
})
public class DiskSpaceDetails {
    @JsonProperty("total")
    private Long total;
    @JsonProperty("free")
    private Long free;
    @JsonProperty("threshold")
    private Integer threshold;
    @JsonProperty("exists")
    private Boolean exists;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("total")
    public Long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Long total) {
        this.total = total;
    }

    @JsonProperty("free")
    public Long getFree() {
        return free;
    }

    @JsonProperty("free")
    public void setFree(Long free) {
        this.free = free;
    }

    @JsonProperty("threshold")
    public Integer getThreshold() {
        return threshold;
    }

    @JsonProperty("threshold")
    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    @JsonProperty("exists")
    public Boolean getExists() {
        return exists;
    }

    @JsonProperty("exists")
    public void setExists(Boolean exists) {
        this.exists = exists;
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
