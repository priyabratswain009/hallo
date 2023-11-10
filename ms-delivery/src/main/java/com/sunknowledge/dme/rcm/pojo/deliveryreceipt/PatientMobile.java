
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
    "patientMobileYes",
    "patientMobileNo",
    "patientMobileNa"
})
@Generated("jsonschema2pojo")
public class PatientMobile {

    @JsonProperty("patientMobileYes")
    private Boolean patientMobileYes;
    @JsonProperty("patientMobileNo")
    private Boolean patientMobileNo;
    @JsonProperty("patientMobileNa")
    private Boolean patientMobileNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientMobileYes")
    public Boolean getPatientMobileYes() {
        return patientMobileYes;
    }

    @JsonProperty("patientMobileYes")
    public void setPatientMobileYes(Boolean patientMobileYes) {
        this.patientMobileYes = patientMobileYes;
    }

    @JsonProperty("patientMobileNo")
    public Boolean getPatientMobileNo() {
        return patientMobileNo;
    }

    @JsonProperty("patientMobileNo")
    public void setPatientMobileNo(Boolean patientMobileNo) {
        this.patientMobileNo = patientMobileNo;
    }

    @JsonProperty("patientMobileNa")
    public Boolean getPatientMobileNa() {
        return patientMobileNa;
    }

    @JsonProperty("patientMobileNa")
    public void setPatientMobileNa(Boolean patientMobileNa) {
        this.patientMobileNa = patientMobileNa;
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
