package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;

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
    "name",
    "npi",
    "address",
    "federalTaxPayersIdentificationNumber"
})
@Generated("jsonschema2pojo")
public class Payee {

    @JsonProperty("name")
    private String name;
    @JsonProperty("npi")
    private String npi;
    @JsonProperty("address")
    private PayeeAddress address;
    @JsonProperty("federalTaxPayersIdentificationNumber")
    private String federalTaxPayersIdentificationNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("npi")
    public String getNpi() {
        return npi;
    }

    @JsonProperty("npi")
    public void setNpi(String npi) {
        this.npi = npi;
    }

    @JsonProperty("address")
    public PayeeAddress getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(PayeeAddress address) {
        this.address = address;
    }

    @JsonProperty("federalTaxPayersIdentificationNumber")
    public String getFederalTaxPayersIdentificationNumber() {
        return federalTaxPayersIdentificationNumber;
    }

    @JsonProperty("federalTaxPayersIdentificationNumber")
    public void setFederalTaxPayersIdentificationNumber(String federalTaxPayersIdentificationNumber) {
        this.federalTaxPayersIdentificationNumber = federalTaxPayersIdentificationNumber;
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
