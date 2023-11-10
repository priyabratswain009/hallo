
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
    "patientHearingYes",
    "patientHearingNo",
    "patientHearingNa"
})
@Generated("jsonschema2pojo")
public class PatientHearing {

    @JsonProperty("patientHearingYes")
    private Boolean patientHearingYes;
    @JsonProperty("patientHearingNo")
    private Boolean patientHearingNo;
    @JsonProperty("patientHearingNa")
    private Boolean patientHearingNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("patientHearingYes")
    public Boolean getPatientHearingYes() {
        return patientHearingYes;
    }

    @JsonProperty("patientHearingYes")
    public void setPatientHearingYes(Boolean patientHearingYes) {
        this.patientHearingYes = patientHearingYes;
    }

    @JsonProperty("patientHearingNo")
    public Boolean getPatientHearingNo() {
        return patientHearingNo;
    }

    @JsonProperty("patientHearingNo")
    public void setPatientHearingNo(Boolean patientHearingNo) {
        this.patientHearingNo = patientHearingNo;
    }

    @JsonProperty("patientHearingNa")
    public Boolean getPatientHearingNa() {
        return patientHearingNa;
    }

    @JsonProperty("patientHearingNa")
    public void setPatientHearingNa(Boolean patientHearingNa) {
        this.patientHearingNa = patientHearingNa;
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
