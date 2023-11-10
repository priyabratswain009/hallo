package com.sunknowledge.changehealthcare.pojo.claimreports.claimsremittance835;

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
    "statusInformationEffectiveDate",
    "informationStatuses"
})
@Generated("jsonschema2pojo")
public class InformationClaimStatus {

    @JsonProperty("statusInformationEffectiveDate")
    private String statusInformationEffectiveDate;
    @JsonProperty("informationStatuses")
    private List<InformationStatus> informationStatuses = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("statusInformationEffectiveDate")
    public String getStatusInformationEffectiveDate() {
        return statusInformationEffectiveDate;
    }

    @JsonProperty("statusInformationEffectiveDate")
    public void setStatusInformationEffectiveDate(String statusInformationEffectiveDate) {
        this.statusInformationEffectiveDate = statusInformationEffectiveDate;
    }

    @JsonProperty("informationStatuses")
    public List<InformationStatus> getInformationStatuses() {
        return informationStatuses;
    }

    @JsonProperty("informationStatuses")
    public void setInformationStatuses(List<InformationStatus> informationStatuses) {
        this.informationStatuses = informationStatuses;
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
