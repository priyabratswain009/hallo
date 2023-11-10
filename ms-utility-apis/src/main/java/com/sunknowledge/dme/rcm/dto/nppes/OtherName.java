package com.sunknowledge.dme.rcm.dto.nppes;

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
	"code",
	"type",
	"last_name",
	"first_name",
	"middle_name",
	"credential",
    "prefix"
})
@Generated("jsonschema2pojo")
public class OtherName {
	@JsonProperty("code")
	private String code;
	@JsonProperty("type")
	private String type;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("middle_name")
	private String middleName;
	@JsonProperty("credential")
	private String credential;
    @JsonProperty("prefix")
    private String prefix;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("middle_name")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middle_name")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("credential")
	public String getCredential() {
		return credential;
	}

	@JsonProperty("credential")
	public void setCredential(String credential) {
		this.credential = credential;
	}

    @JsonProperty("prefix")
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
