
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
    "dementiaYes",
    "dementiaNo",
    "dementiaNa"
})
@Generated("jsonschema2pojo")
public class Dementia {

    @JsonProperty("dementiaYes")
    private Boolean dementiaYes;
    @JsonProperty("dementiaNo")
    private Boolean dementiaNo;
    @JsonProperty("dementiaNa")
    private Boolean dementiaNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("dementiaYes")
    public Boolean getDementiaYes() {
        return dementiaYes;
    }

    @JsonProperty("dementiaYes")
    public void setDementiaYes(Boolean dementiaYes) {
        this.dementiaYes = dementiaYes;
    }

    @JsonProperty("dementiaNo")
    public Boolean getDementiaNo() {
        return dementiaNo;
    }

    @JsonProperty("dementiaNo")
    public void setDementiaNo(Boolean dementiaNo) {
        this.dementiaNo = dementiaNo;
    }

    @JsonProperty("dementiaNa")
    public Boolean getDementiaNa() {
        return dementiaNa;
    }

    @JsonProperty("dementiaNa")
    public void setDementiaNa(Boolean dementiaNa) {
        this.dementiaNa = dementiaNa;
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
