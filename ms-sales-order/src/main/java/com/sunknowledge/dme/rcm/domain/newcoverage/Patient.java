
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
    "firstName",
    "lastName",
    "subscriberRelationship",
    "subscriberRelationshipCode",
    "gender",
    "genderCode",
    "birthDate",
    "address",
    "updateYourRecords"
})
@Generated("jsonschema2pojo")
public class Patient {

    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("subscriberRelationship")
    public String subscriberRelationship;
    @JsonProperty("subscriberRelationshipCode")
    public String subscriberRelationshipCode;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("genderCode")
    public String genderCode;
    @JsonProperty("birthDate")
    public String birthDate;
    @JsonProperty("address")
    public Address address;
    @JsonProperty("updateYourRecords")
    public Boolean updateYourRecords;
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
