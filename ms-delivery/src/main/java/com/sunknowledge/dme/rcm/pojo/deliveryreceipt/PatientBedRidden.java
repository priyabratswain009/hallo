
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
    "patientBedRiddenYes",
    "patientBedRiddenNo",
    "patientBedRiddenNa"
})
@Generated("jsonschema2pojo")
public class PatientBedRidden {

    @JsonProperty("patientBedRiddenYes")
    private Boolean patientBedRiddenYes;
    @JsonProperty("patientBedRiddenNo")
    private Boolean patientBedRiddenNo;
    @JsonProperty("patientBedRiddenNa")
    private Boolean patientBedRiddenNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientBedRiddenYes")
    public Boolean getPatientBedRiddenYes() {
        return patientBedRiddenYes;
    }

    @JsonProperty("patientBedRiddenYes")
    public void setPatientBedRiddenYes(Boolean patientBedRiddenYes) {
        this.patientBedRiddenYes = patientBedRiddenYes;
    }

    @JsonProperty("patientBedRiddenNo")
    public Boolean getPatientBedRiddenNo() {
        return patientBedRiddenNo;
    }

    @JsonProperty("patientBedRiddenNo")
    public void setPatientBedRiddenNo(Boolean patientBedRiddenNo) {
        this.patientBedRiddenNo = patientBedRiddenNo;
    }

    @JsonProperty("patientBedRiddenNa")
    public Boolean getPatientBedRiddenNa() {
        return patientBedRiddenNa;
    }

    @JsonProperty("patientBedRiddenNa")
    public void setPatientBedRiddenNa(Boolean patientBedRiddenNa) {
        this.patientBedRiddenNa = patientBedRiddenNa;
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
