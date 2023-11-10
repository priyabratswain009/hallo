
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
    "patientNutritionalYes",
    "patientNutritionalNo",
    "patientNutritionalNa"
})
@Generated("jsonschema2pojo")
public class PatientNutritional {

    @JsonProperty("patientNutritionalYes")
    private Boolean patientNutritionalYes;
    @JsonProperty("patientNutritionalNo")
    private Boolean patientNutritionalNo;
    @JsonProperty("patientNutritionalNa")
    private Boolean patientNutritionalNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientNutritionalYes")
    public Boolean getPatientNutritionalYes() {
        return patientNutritionalYes;
    }

    @JsonProperty("patientNutritionalYes")
    public void setPatientNutritionalYes(Boolean patientNutritionalYes) {
        this.patientNutritionalYes = patientNutritionalYes;
    }

    @JsonProperty("patientNutritionalNo")
    public Boolean getPatientNutritionalNo() {
        return patientNutritionalNo;
    }

    @JsonProperty("patientNutritionalNo")
    public void setPatientNutritionalNo(Boolean patientNutritionalNo) {
        this.patientNutritionalNo = patientNutritionalNo;
    }

    @JsonProperty("patientNutritionalNa")
    public Boolean getPatientNutritionalNa() {
        return patientNutritionalNa;
    }

    @JsonProperty("patientNutritionalNa")
    public void setPatientNutritionalNa(Boolean patientNutritionalNa) {
        this.patientNutritionalNa = patientNutritionalNa;
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
