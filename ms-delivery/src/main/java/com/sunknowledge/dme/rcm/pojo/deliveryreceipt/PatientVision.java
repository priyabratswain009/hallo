
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
    "patientVisionYes",
    "patientVisionNo",
    "patientVisionNa"
})
@Generated("jsonschema2pojo")
public class PatientVision {

    @JsonProperty("patientVisionYes")
    private Boolean patientVisionYes;
    @JsonProperty("patientVisionNo")
    private Boolean patientVisionNo;
    @JsonProperty("patientVisionNa")
    private Boolean patientVisionNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientVisionYes")
    public Boolean getPatientVisionYes() {
        return patientVisionYes;
    }

    @JsonProperty("patientVisionYes")
    public void setPatientVisionYes(Boolean patientVisionYes) {
        this.patientVisionYes = patientVisionYes;
    }

    @JsonProperty("patientVisionNo")
    public Boolean getPatientVisionNo() {
        return patientVisionNo;
    }

    @JsonProperty("patientVisionNo")
    public void setPatientVisionNo(Boolean patientVisionNo) {
        this.patientVisionNo = patientVisionNo;
    }

    @JsonProperty("patientVisionNa")
    public Boolean getPatientVisionNa() {
        return patientVisionNa;
    }

    @JsonProperty("patientVisionNa")
    public void setPatientVisionNa(Boolean patientVisionNa) {
        this.patientVisionNa = patientVisionNa;
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
