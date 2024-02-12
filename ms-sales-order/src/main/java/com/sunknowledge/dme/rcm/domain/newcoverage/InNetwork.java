
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
    "insuranceType",
    "insuranceTypeCode",
    "amount",
    "units",
    "amountTimePeriod",
    "amountTimePeriodCode",
    "level",
    "levelCode",
    "payerNotes"
})
@Generated("jsonschema2pojo")
public class InNetwork {

    @JsonProperty("status")
    public String status;
    @JsonProperty("statusCode")
    public String statusCode;
    @JsonProperty("insuranceType")
    public String insuranceType;
    @JsonProperty("insuranceTypeCode")
    public String insuranceTypeCode;
    @JsonProperty("amount")
    public String amount;
    @JsonProperty("units")
    public String units;
    @JsonProperty("amountTimePeriod")
    public String amountTimePeriod;
    @JsonProperty("amountTimePeriodCode")
    public String amountTimePeriodCode;
    @JsonProperty("level")
    public String level;
    @JsonProperty("levelCode")
    public String levelCode;
    @JsonProperty("payerNotes")
    public List<String> payerNotes;
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
