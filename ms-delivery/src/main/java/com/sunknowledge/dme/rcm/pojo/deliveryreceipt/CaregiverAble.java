
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
    "caregiverAbleYes",
    "caregiverAbleNo",
    "caregiverAbleNa"
})
@Generated("jsonschema2pojo")
public class CaregiverAble {

    @JsonProperty("caregiverAbleYes")
    private Boolean caregiverAbleYes;
    @JsonProperty("caregiverAbleNo")
    private Boolean caregiverAbleNo;
    @JsonProperty("caregiverAbleNa")
    private Boolean caregiverAbleNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("caregiverAbleYes")
    public Boolean getCaregiverAbleYes() {
        return caregiverAbleYes;
    }

    @JsonProperty("caregiverAbleYes")
    public void setCaregiverAbleYes(Boolean caregiverAbleYes) {
        this.caregiverAbleYes = caregiverAbleYes;
    }

    @JsonProperty("caregiverAbleNo")
    public Boolean getCaregiverAbleNo() {
        return caregiverAbleNo;
    }

    @JsonProperty("caregiverAbleNo")
    public void setCaregiverAbleNo(Boolean caregiverAbleNo) {
        this.caregiverAbleNo = caregiverAbleNo;
    }

    @JsonProperty("caregiverAbleNa")
    public Boolean getCaregiverAbleNa() {
        return caregiverAbleNa;
    }

    @JsonProperty("caregiverAbleNa")
    public void setCaregiverAbleNa(Boolean caregiverAbleNa) {
        this.caregiverAbleNa = caregiverAbleNa;
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
