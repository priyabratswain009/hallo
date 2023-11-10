
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
    "patientAmbulatoryYes",
    "patientAmbulatoryNo",
    "patientAmbulatoryNa"
})
@Generated("jsonschema2pojo")
public class PatientAmbulatory {

    @JsonProperty("patientAmbulatoryYes")
    private Boolean patientAmbulatoryYes;
    @JsonProperty("patientAmbulatoryNo")
    private Boolean patientAmbulatoryNo;
    @JsonProperty("patientAmbulatoryNa")
    private Boolean patientAmbulatoryNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientAmbulatoryYes")
    public Boolean getPatientAmbulatoryYes() {
        return patientAmbulatoryYes;
    }

    @JsonProperty("patientAmbulatoryYes")
    public void setPatientAmbulatoryYes(Boolean patientAmbulatoryYes) {
        this.patientAmbulatoryYes = patientAmbulatoryYes;
    }

    @JsonProperty("patientAmbulatoryNo")
    public Boolean getPatientAmbulatoryNo() {
        return patientAmbulatoryNo;
    }

    @JsonProperty("patientAmbulatoryNo")
    public void setPatientAmbulatoryNo(Boolean patientAmbulatoryNo) {
        this.patientAmbulatoryNo = patientAmbulatoryNo;
    }

    @JsonProperty("patientAmbulatoryNa")
    public Boolean getPatientAmbulatoryNa() {
        return patientAmbulatoryNa;
    }

    @JsonProperty("patientAmbulatoryNa")
    public void setPatientAmbulatoryNa(Boolean patientAmbulatoryNa) {
        this.patientAmbulatoryNa = patientAmbulatoryNa;
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
