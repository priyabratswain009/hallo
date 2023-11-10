
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
    "caregiverUnderstandsYes",
    "caregiverUnderstandsNa",
    "caregiverUnderstandsNo"
})
@Generated("jsonschema2pojo")
public class CaregiverUnderstands {

    @JsonProperty("caregiverUnderstandsYes")
    private Boolean caregiverUnderstandsYes;
    @JsonProperty("caregiverUnderstandsNa")
    private Boolean caregiverUnderstandsNa;
    @JsonProperty("caregiverUnderstandsNo")
    private Boolean caregiverUnderstandsNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("caregiverUnderstandsYes")
    public Boolean getCaregiverUnderstandsYes() {
        return caregiverUnderstandsYes;
    }

    @JsonProperty("caregiverUnderstandsYes")
    public void setCaregiverUnderstandsYes(Boolean caregiverUnderstandsYes) {
        this.caregiverUnderstandsYes = caregiverUnderstandsYes;
    }

    @JsonProperty("caregiverUnderstandsNa")
    public Boolean getCaregiverUnderstandsNa() {
        return caregiverUnderstandsNa;
    }

    @JsonProperty("caregiverUnderstandsNa")
    public void setCaregiverUnderstandsNa(Boolean caregiverUnderstandsNa) {
        this.caregiverUnderstandsNa = caregiverUnderstandsNa;
    }

    @JsonProperty("caregiverUnderstandsNo")
    public Boolean getCaregiverUnderstandsNo() {
        return caregiverUnderstandsNo;
    }

    @JsonProperty("caregiverUnderstandsNo")
    public void setCaregiverUnderstandsNo(Boolean caregiverUnderstandsNo) {
        this.caregiverUnderstandsNo = caregiverUnderstandsNo;
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
