
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
    "streetAddress1",
    "streetAddress2",
    "cityStateZip",
    "phoneNo"
})
@Generated("jsonschema2pojo")
public class BillToAddress {

    @JsonProperty("streetAddress1")
    private String streetAddress1;
    @JsonProperty("streetAddress2")
    private String streetAddress2;
    @JsonProperty("cityStateZip")
    private String cityStateZip;
    @JsonProperty("phoneNo")
    private String phoneNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("streetAddress1")
    public String getStreetAddress1() {
        return streetAddress1;
    }

    @JsonProperty("streetAddress1")
    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    @JsonProperty("streetAddress2")
    public String getStreetAddress2() {
        return streetAddress2;
    }

    @JsonProperty("streetAddress2")
    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    @JsonProperty("cityStateZip")
    public String getCityStateZip() {
        return cityStateZip;
    }

    @JsonProperty("cityStateZip")
    public void setCityStateZip(String cityStateZip) {
        this.cityStateZip = cityStateZip;
    }

    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
