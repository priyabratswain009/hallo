package com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277;

import java.util.HashMap;
import java.util.List;
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
    "claimStatus",
    "serviceLines"
})
@Generated("jsonschema2pojo")
public class Claim {

    @JsonProperty("claimStatus")
    private ClaimStatus claimStatus;
    @JsonProperty("serviceLines")
    private List<ServiceLine> serviceLines = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("claimStatus")
    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    @JsonProperty("claimStatus")
    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    @JsonProperty("serviceLines")
    public List<ServiceLine> getServiceLines() {
        return serviceLines;
    }

    @JsonProperty("serviceLines")
    public void setServiceLines(List<ServiceLine> serviceLines) {
        this.serviceLines = serviceLines;
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
