package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "name", "phoneNumber", "faxNumber", "email", "phoneExtension" })
@Generated("jsonschema2pojo")
public class ContactInformation {

	@JsonProperty("name")
	private String name;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("faxNumber")
	private String faxNumber;
	@JsonProperty("email")
	private String email;
	@JsonProperty("phoneExtension")
	private String phoneExtension;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("faxNumber")
	public String getFaxNumber() {
		return faxNumber;
	}

	@JsonProperty("faxNumber")
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("phoneExtension")
	public String getPhoneExtension() {
		return phoneExtension;
	}

	@JsonProperty("phoneExtension")
	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
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