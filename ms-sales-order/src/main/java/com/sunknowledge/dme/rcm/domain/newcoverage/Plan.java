
package com.sunknowledge.dme.rcm.domain.newcoverage;

import java.util.LinkedHashMap;
import java.util.List;
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
    "status",
    "statusCode",
    "groupNumber",
    "groupName",
    "serviceDate",
    "planStartDate",
    "benefits",
    "payerNotes"
})
@Generated("jsonschema2pojo")
public class Plan {

    @JsonProperty("status")
    public String status;
    @JsonProperty("statusCode")
    public String statusCode;
    @JsonProperty("groupNumber")
    public String groupNumber;
    @JsonProperty("groupName")
    public String groupName;
    @JsonProperty("serviceDate")
    public String serviceDate;
    @JsonProperty("planStartDate")
    public String planStartDate;
    @JsonProperty("benefits")
    public List<Benefit> benefits;
    @JsonProperty("payerNotes")
    public List<PayerNote> payerNotes;
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
