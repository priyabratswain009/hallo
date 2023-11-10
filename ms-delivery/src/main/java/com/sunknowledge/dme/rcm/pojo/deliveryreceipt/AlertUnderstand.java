
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
    "alertUnderstandYes",
    "alertUnderstandNo",
    "alertUnderstandNa"
})
@Generated("jsonschema2pojo")
public class AlertUnderstand {

    @JsonProperty("alertUnderstandYes")
    private Boolean alertUnderstandYes;
    @JsonProperty("alertUnderstandNo")
    private Boolean alertUnderstandNo;
    @JsonProperty("alertUnderstandNa")
    private Boolean alertUnderstandNa;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("alertUnderstandYes")
    public Boolean getAlertUnderstandYes() {
        return alertUnderstandYes;
    }

    @JsonProperty("alertUnderstandYes")
    public void setAlertUnderstandYes(Boolean alertUnderstandYes) {
        this.alertUnderstandYes = alertUnderstandYes;
    }

    @JsonProperty("alertUnderstandNo")
    public Boolean getAlertUnderstandNo() {
        return alertUnderstandNo;
    }

    @JsonProperty("alertUnderstandNo")
    public void setAlertUnderstandNo(Boolean alertUnderstandNo) {
        this.alertUnderstandNo = alertUnderstandNo;
    }

    @JsonProperty("alertUnderstandNa")
    public Boolean getAlertUnderstandNa() {
        return alertUnderstandNa;
    }

    @JsonProperty("alertUnderstandNa")
    public void setAlertUnderstandNa(Boolean alertUnderstandNa) {
        this.alertUnderstandNa = alertUnderstandNa;
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
