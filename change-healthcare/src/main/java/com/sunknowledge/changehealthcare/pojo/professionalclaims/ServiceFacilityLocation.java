package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "address", "secondaryIdentifier", "organizationName", "npi", "phoneName", "phoneNumber",
		"phoneExtension", "IdentificationQualifierCode" })
@Generated("jsonschema2pojo")
public class ServiceFacilityLocation {

	@JsonProperty("address")
	private Address address;
	@JsonProperty("secondaryIdentifier")
	private List<SecondaryIdentifier> secondaryIdentifier = null;
	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("npi")
	private String npi;
	@JsonProperty("phoneName")
	private String phoneName;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("phoneExtension")
	private String phoneExtension;
	@JsonProperty("IdentificationQualifierCode")
	private String identificationQualifierCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("secondaryIdentifier")
	public List<SecondaryIdentifier> getSecondaryIdentifier() {
		return secondaryIdentifier;
	}

	@JsonProperty("secondaryIdentifier")
	public void setSecondaryIdentifier(List<SecondaryIdentifier> secondaryIdentifier) {
		this.secondaryIdentifier = secondaryIdentifier;
	}

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("npi")
	public String getNpi() {
		return npi;
	}

	@JsonProperty("npi")
	public void setNpi(String npi) {
		this.npi = npi;
	}

	@JsonProperty("phoneName")
	public String getPhoneName() {
		return phoneName;
	}

	@JsonProperty("phoneName")
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("phoneExtension")
	public String getPhoneExtension() {
		return phoneExtension;
	}

	@JsonProperty("phoneExtension")
	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	@JsonProperty("IdentificationQualifierCode")
	public String getIdentificationQualifierCode() {
		return identificationQualifierCode;
	}

	@JsonProperty("IdentificationQualifierCode")
	public void setIdentificationQualifierCode(String identificationQualifierCode) {
		this.identificationQualifierCode = identificationQualifierCode;
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