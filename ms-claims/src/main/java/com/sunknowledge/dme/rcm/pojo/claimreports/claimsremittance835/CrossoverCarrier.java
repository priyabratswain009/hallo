package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;

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
    "organizationName",
    "payorId",
    "entityIdentifierCode",
    "entityTypeQualifier"
})
@Generated("jsonschema2pojo")
public class CrossoverCarrier {

    @JsonProperty("organizationName")
    private String organizationName;
    @JsonProperty("payorId")
    private String payorId;
    @JsonProperty("entityIdentifierCode")
    private String entityIdentifierCode;
    @JsonProperty("entityTypeQualifier")
    private String entityTypeQualifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("organizationName")
    public String getOrganizationName() {
        return organizationName;
    }

    @JsonProperty("organizationName")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @JsonProperty("payorId")
    public String getPayorId() {
        return payorId;
    }

    @JsonProperty("payorId")
    public void setPayorId(String payorId) {
        this.payorId = payorId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("entityIdentifierCode")
	public String getEntityIdentifierCode() {
		return entityIdentifierCode;
	}

    @JsonProperty("entityIdentifierCode")
	public void setEntityIdentifierCode(String entityIdentifierCode) {
		this.entityIdentifierCode = entityIdentifierCode;
	}

    @JsonProperty("entityTypeQualifier")
	public String getEntityTypeQualifier() {
		return entityTypeQualifier;
	}

    @JsonProperty("entityTypeQualifier")
	public void setEntityTypeQualifier(String entityTypeQualifier) {
		this.entityTypeQualifier = entityTypeQualifier;
	}

}
