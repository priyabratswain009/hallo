
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
    "name1",
    "phone1",
    "relationship1",
    "name2",
    "phone2",
    "relationship2"
})
@Generated("jsonschema2pojo")
public class AlternateContacts {

    @JsonProperty("name1")
    private String name1;
    @JsonProperty("phone1")
    private String phone1;
    @JsonProperty("relationship1")
    private String relationship1;
    @JsonProperty("name2")
    private String name2;
    @JsonProperty("phone2")
    private String phone2;
    @JsonProperty("relationship2")
    private String relationship2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name1")
    public String getName1() {
        return name1;
    }

    @JsonProperty("name1")
    public void setName1(String name1) {
        this.name1 = name1;
    }

    @JsonProperty("phone1")
    public String getPhone1() {
        return phone1;
    }

    @JsonProperty("phone1")
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @JsonProperty("relationship1")
    public String getRelationship1() {
        return relationship1;
    }

    @JsonProperty("relationship1")
    public void setRelationship1(String relationship1) {
        this.relationship1 = relationship1;
    }

    @JsonProperty("name2")
    public String getName2() {
        return name2;
    }

    @JsonProperty("name2")
    public void setName2(String name2) {
        this.name2 = name2;
    }

    @JsonProperty("phone2")
    public String getPhone2() {
        return phone2;
    }

    @JsonProperty("phone2")
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @JsonProperty("relationship2")
    public String getRelationship2() {
        return relationship2;
    }

    @JsonProperty("relationship2")
    public void setRelationship2(String relationship2) {
        this.relationship2 = relationship2;
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
