package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GATEWAY",
    "CLAIMS",
    "PATIENT",
    "ITEMS",
    "SETTINGS",
    "JHIPSTER-REGISTRY",
    "DELIVERY",
    "SALESORDER",
    "AVAILITY",
    "UTILITYAPIS"
})
public class MicroserviceApplications {
    @JsonProperty("GATEWAY")
    private Integer gateway;
    @JsonProperty("CLAIMS")
    private Integer claims;
    @JsonProperty("PATIENT")
    private Integer patient;
    @JsonProperty("ITEMS")
    private Integer items;
    @JsonProperty("SETTINGS")
    private Integer settings;
    @JsonProperty("JHIPSTER-REGISTRY")
    private Integer jhipsterRegistry;
    @JsonProperty("DELIVERY")
    private Integer delivery;
    @JsonProperty("SALESORDER")
    private Integer salesorder;
    @JsonProperty("AVAILITY")
    private Integer availity;
    @JsonProperty("UTILITYAPIS")
    private Integer utilityapis;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("GATEWAY")
    public Integer getGateway() {
        return gateway;
    }

    @JsonProperty("GATEWAY")
    public void setGateway(Integer gateway) {
        this.gateway = gateway;
    }

    @JsonProperty("CLAIMS")
    public Integer getClaims() {
        return claims;
    }

    @JsonProperty("CLAIMS")
    public void setClaims(Integer claims) {
        this.claims = claims;
    }

    @JsonProperty("PATIENT")
    public Integer getPatient() {
        return patient;
    }

    @JsonProperty("PATIENT")
    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    @JsonProperty("ITEMS")
    public Integer getItems() {
        return items;
    }

    @JsonProperty("ITEMS")
    public void setItems(Integer items) {
        this.items = items;
    }

    @JsonProperty("SETTINGS")
    public Integer getSettings() {
        return settings;
    }

    @JsonProperty("SETTINGS")
    public void setSettings(Integer settings) {
        this.settings = settings;
    }

    @JsonProperty("JHIPSTER-REGISTRY")
    public Integer getJhipsterRegistry() {
        return jhipsterRegistry;
    }

    @JsonProperty("JHIPSTER-REGISTRY")
    public void setJhipsterRegistry(Integer jhipsterRegistry) {
        this.jhipsterRegistry = jhipsterRegistry;
    }

    @JsonProperty("DELIVERY")
    public Integer getDelivery() {
        return delivery;
    }

    @JsonProperty("DELIVERY")
    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("SALESORDER")
    public Integer getSalesOrder() {
        return salesorder;
    }

    @JsonProperty("SALESORDER")
    public void setSalesOrder(Integer salesorder) {
        this.salesorder = salesorder;
    }

    @JsonProperty("AVAILITY")
    public Integer getAvaility() {
        return availity;
    }

    @JsonProperty("AVAILITY")
    public void setAvaility(Integer availity) {
        this.availity = availity;
    }

    @JsonProperty("UTILITYAPIS")
    public Integer getUtilityapis() {
        return utilityapis;
    }

    @JsonProperty("UTILITYAPIS")
    public void setUtilityapis(Integer utilityapis) {
        this.utilityapis = utilityapis;
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
