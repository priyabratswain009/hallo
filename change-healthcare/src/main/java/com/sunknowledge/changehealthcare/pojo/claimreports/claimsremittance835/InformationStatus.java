package com.sunknowledge.changehealthcare.pojo.claimreports.claimsremittance835;

import java.util.HashMap;
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
    "healthCareClaimStatusCategoryCode",
    "healthCareClaimStatusCategoryCodeValue",
    "statusCode",
    "statusCodeValue",
    "entityIdentifierCode",
    "entityIdentifierCodeValue"
})
@Generated("jsonschema2pojo")
public class InformationStatus {

    @JsonProperty("healthCareClaimStatusCategoryCode")
    private String healthCareClaimStatusCategoryCode;
    @JsonProperty("healthCareClaimStatusCategoryCodeValue")
    private String healthCareClaimStatusCategoryCodeValue;
    @JsonProperty("statusCode")
    private String statusCode;
    @JsonProperty("statusCodeValue")
    private String statusCodeValue;
    @JsonProperty("entityIdentifierCode")
    private String entityIdentifierCode;
    @JsonProperty("entityIdentifierCodeValue")
    private String entityIdentifierCodeValue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("healthCareClaimStatusCategoryCode")
    public String getHealthCareClaimStatusCategoryCode() {
        return healthCareClaimStatusCategoryCode;
    }

    @JsonProperty("healthCareClaimStatusCategoryCode")
    public void setHealthCareClaimStatusCategoryCode(String healthCareClaimStatusCategoryCode) {
        this.healthCareClaimStatusCategoryCode = healthCareClaimStatusCategoryCode;
    }

    @JsonProperty("healthCareClaimStatusCategoryCodeValue")
    public String getHealthCareClaimStatusCategoryCodeValue() {
        return healthCareClaimStatusCategoryCodeValue;
    }

    @JsonProperty("healthCareClaimStatusCategoryCodeValue")
    public void setHealthCareClaimStatusCategoryCodeValue(String healthCareClaimStatusCategoryCodeValue) {
        this.healthCareClaimStatusCategoryCodeValue = healthCareClaimStatusCategoryCodeValue;
    }

    @JsonProperty("statusCode")
    public String getStatusCode() {
        return statusCode;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("statusCodeValue")
    public String getStatusCodeValue() {
        return statusCodeValue;
    }

    @JsonProperty("statusCodeValue")
    public void setStatusCodeValue(String statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    @JsonProperty("entityIdentifierCode")
    public String getEntityIdentifierCode() {
        return entityIdentifierCode;
    }

    @JsonProperty("entityIdentifierCode")
    public void setEntityIdentifierCode(String entityIdentifierCode) {
        this.entityIdentifierCode = entityIdentifierCode;
    }

    @JsonProperty("entityIdentifierCodeValue")
    public String getEntityIdentifierCodeValue() {
        return entityIdentifierCodeValue;
    }

    @JsonProperty("entityIdentifierCodeValue")
    public void setEntityIdentifierCodeValue(String entityIdentifierCodeValue) {
        this.entityIdentifierCodeValue = entityIdentifierCodeValue;
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
