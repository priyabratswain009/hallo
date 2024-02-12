package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "database",
    "validationQuery"
})
public class DbDetails {
    @JsonProperty("database")
    private String database;
    @JsonProperty("validationQuery")
    private String validationQuery;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("database")
    public String getDatabase() {
        return database;
    }

    @JsonProperty("database")
    public void setDatabase(String database) {
        this.database = database;
    }

    @JsonProperty("validationQuery")
    public String getValidationQuery() {
        return validationQuery;
    }

    @JsonProperty("validationQuery")
    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
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
