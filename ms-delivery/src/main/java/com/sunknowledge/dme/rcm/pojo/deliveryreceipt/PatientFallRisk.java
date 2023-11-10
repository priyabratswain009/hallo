
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
    "patientFallRiskYes",
    "patientFallRiskNo",
    "patientFallRiskNa"
})
@Generated("jsonschema2pojo")
public class PatientFallRisk {

    @JsonProperty("patientFallRiskYes")
    private Boolean patientFallRiskYes;
    @JsonProperty("patientFallRiskNo")
    private Boolean patientFallRiskNo;
    @JsonProperty("patientFallRiskNa")
    private Boolean patientFallRiskNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientFallRiskYes")
    public Boolean getPatientFallRiskYes() {
        return patientFallRiskYes;
    }

    @JsonProperty("patientFallRiskYes")
    public void setPatientFallRiskYes(Boolean patientFallRiskYes) {
        this.patientFallRiskYes = patientFallRiskYes;
    }

    @JsonProperty("patientFallRiskNo")
    public Boolean getPatientFallRiskNo() {
        return patientFallRiskNo;
    }

    @JsonProperty("patientFallRiskNo")
    public void setPatientFallRiskNo(Boolean patientFallRiskNo) {
        this.patientFallRiskNo = patientFallRiskNo;
    }

    @JsonProperty("patientFallRiskNa")
    public Boolean getPatientFallRiskNa() {
        return patientFallRiskNa;
    }

    @JsonProperty("patientFallRiskNa")
    public void setPatientFallRiskNa(Boolean patientFallRiskNa) {
        this.patientFallRiskNa = patientFallRiskNa;
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
