
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
    "confusedYes",
    "confusedNo",
    "confusedNa"
})
@Generated("jsonschema2pojo")
public class Confused {

    @JsonProperty("confusedYes")
    private Boolean confusedYes;
    @JsonProperty("confusedNo")
    private Boolean confusedNo;
    @JsonProperty("confusedNa")
    private Boolean confusedNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("confusedYes")
    public Boolean getConfusedYes() {
        return confusedYes;
    }

    @JsonProperty("confusedYes")
    public void setConfusedYes(Boolean confusedYes) {
        this.confusedYes = confusedYes;
    }

    @JsonProperty("confusedNo")
    public Boolean getConfusedNo() {
        return confusedNo;
    }

    @JsonProperty("confusedNo")
    public void setConfusedNo(Boolean confusedNo) {
        this.confusedNo = confusedNo;
    }

    @JsonProperty("confusedNa")
    public Boolean getConfusedNa() {
        return confusedNa;
    }

    @JsonProperty("confusedNa")
    public void setConfusedNa(Boolean confusedNa) {
        this.confusedNa = confusedNa;
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
