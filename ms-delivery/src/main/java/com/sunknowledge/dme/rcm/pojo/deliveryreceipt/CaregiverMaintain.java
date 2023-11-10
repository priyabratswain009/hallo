
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
    "caregiverMaintainYes",
    "caregiverMaintainNo",
    "caregiverMaintainNa"
})
@Generated("jsonschema2pojo")
public class CaregiverMaintain {

    @JsonProperty("caregiverMaintainYes")
    private Boolean caregiverMaintainYes;
    @JsonProperty("caregiverMaintainNo")
    private Boolean caregiverMaintainNo;
    @JsonProperty("caregiverMaintainNa")
    private Boolean caregiverMaintainNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("caregiverMaintainYes")
    public Boolean getCaregiverMaintainYes() {
        return caregiverMaintainYes;
    }

    @JsonProperty("caregiverMaintainYes")
    public void setCaregiverMaintainYes(Boolean caregiverMaintainYes) {
        this.caregiverMaintainYes = caregiverMaintainYes;
    }

    @JsonProperty("caregiverMaintainNo")
    public Boolean getCaregiverMaintainNo() {
        return caregiverMaintainNo;
    }

    @JsonProperty("caregiverMaintainNo")
    public void setCaregiverMaintainNo(Boolean caregiverMaintainNo) {
        this.caregiverMaintainNo = caregiverMaintainNo;
    }

    @JsonProperty("caregiverMaintainNa")
    public Boolean getCaregiverMaintainNa() {
        return caregiverMaintainNa;
    }

    @JsonProperty("caregiverMaintainNa")
    public void setCaregiverMaintainNa(Boolean caregiverMaintainNa) {
        this.caregiverMaintainNa = caregiverMaintainNa;
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
