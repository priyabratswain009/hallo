
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
    "patientSpeechYes",
    "patientSpeechNo",
    "patientSpeechNa"
})
@Generated("jsonschema2pojo")
public class PatientSpeech {

    @JsonProperty("patientSpeechYes")
    private Boolean patientSpeechYes;
    @JsonProperty("patientSpeechNo")
    private Boolean patientSpeechNo;
    @JsonProperty("patientSpeechNa")
    private Boolean patientSpeechNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientSpeechYes")
    public Boolean getPatientSpeechYes() {
        return patientSpeechYes;
    }

    @JsonProperty("patientSpeechYes")
    public void setPatientSpeechYes(Boolean patientSpeechYes) {
        this.patientSpeechYes = patientSpeechYes;
    }

    @JsonProperty("patientSpeechNo")
    public Boolean getPatientSpeechNo() {
        return patientSpeechNo;
    }

    @JsonProperty("patientSpeechNo")
    public void setPatientSpeechNo(Boolean patientSpeechNo) {
        this.patientSpeechNo = patientSpeechNo;
    }

    @JsonProperty("patientSpeechNa")
    public Boolean getPatientSpeechNa() {
        return patientSpeechNa;
    }

    @JsonProperty("patientSpeechNa")
    public void setPatientSpeechNa(Boolean patientSpeechNa) {
        this.patientSpeechNa = patientSpeechNa;
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
