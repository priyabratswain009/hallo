
package com.sunknowledge.dme.rcm.pojo.deliveryreceipt;

import java.util.LinkedHashMap;
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
    "caregiverWillingYes",
    "caregiverWillingNo",
    "caregiverWillingNa"
})
@Generated("jsonschema2pojo")
public class CaregiverWilling {

    @JsonProperty("caregiverWillingYes")
    private Boolean caregiverWillingYes;
    @JsonProperty("caregiverWillingNo")
    private Boolean caregiverWillingNo;
    @JsonProperty("caregiverWillingNa")
    private Boolean caregiverWillingNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("caregiverWillingYes")
    public Boolean getCaregiverWillingYes() {
        return caregiverWillingYes;
    }

    @JsonProperty("caregiverWillingYes")
    public void setCaregiverWillingYes(Boolean caregiverWillingYes) {
        this.caregiverWillingYes = caregiverWillingYes;
    }

    @JsonProperty("caregiverWillingNo")
    public Boolean getCaregiverWillingNo() {
        return caregiverWillingNo;
    }

    @JsonProperty("caregiverWillingNo")
    public void setCaregiverWillingNo(Boolean caregiverWillingNo) {
        this.caregiverWillingNo = caregiverWillingNo;
    }

    @JsonProperty("caregiverWillingNa")
    public Boolean getCaregiverWillingNa() {
        return caregiverWillingNa;
    }

    @JsonProperty("caregiverWillingNa")
    public void setCaregiverWillingNa(Boolean caregiverWillingNa) {
        this.caregiverWillingNa = caregiverWillingNa;
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
