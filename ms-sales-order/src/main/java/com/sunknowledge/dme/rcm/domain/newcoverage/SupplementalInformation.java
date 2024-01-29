
package com.sunknowledge.dme.rcm.domain.newcoverage;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "professionalPatientCostEstimator",
    "institutionalPatientCostEstimator",
    "patientCareSummary",
    "assessmentCarePlan",
    "outOfArea"
})
@Generated("jsonschema2pojo")
public class SupplementalInformation {

    @JsonProperty("professionalPatientCostEstimator")
    public Boolean professionalPatientCostEstimator;
    @JsonProperty("institutionalPatientCostEstimator")
    public Boolean institutionalPatientCostEstimator;
    @JsonProperty("patientCareSummary")
    public Boolean patientCareSummary;
    @JsonProperty("assessmentCarePlan")
    public Boolean assessmentCarePlan;
    @JsonProperty("outOfArea")
    public Boolean outOfArea;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
