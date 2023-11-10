package com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277;
import java.util.HashMap;
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
    "allowedActual"
})
@Generated("jsonschema2pojo")
public class ServiceSupplementalAmounts {

    @JsonProperty("allowedActual")
    private String allowedActual;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("allowedActual")
    public String getAllowedActual() {
        return allowedActual;
    }

    @JsonProperty("allowedActual")
    public void setAllowedActual(String allowedActual) {
        this.allowedActual = allowedActual;
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
