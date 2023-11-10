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
	"country_code",
	"country_name",
	"address_purpose",
	"address_type",
	"address_1",
	"address_2",
	"city",
	"state",
	"postal_code",
	"telephone_number",
    "fax_number"
})
@Generated("jsonschema2pojo")
public class Address {
	@JsonProperty("country_code")
	private String countryCode;
	@JsonProperty("country_name")
	private String countryName;
	@JsonProperty("address_purpose")
	private String addressPurpose;
	@JsonProperty("address_type")
	private String addressType;
	@JsonProperty("address_1")
	private String address1;
	@JsonProperty("address_2")
	private String address2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("postal_code")
	private String postalCode;
	@JsonProperty("telephone_number")
	private String telephoneNumber;
    @JsonProperty("fax_number")
    private String faxNumber;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("country_code")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("country_code")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("country_name")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("country_name")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("address_purpose")
	public String getAddressPurpose() {
		return addressPurpose;
	}

	@JsonProperty("address_purpose")
	public void setAddressPurpose(String addressPurpose) {
		this.addressPurpose = addressPurpose;
	}

	@JsonProperty("address_type")
	public String getAddressType() {
		return addressType;
	}

	@JsonProperty("address_type")
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@JsonProperty("address_1")
	public String getAddress1() {
		return address1;
	}

	@JsonProperty("address_1")
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@JsonProperty("address_2")
	public String getAddress2() {
		return address2;
	}

	@JsonProperty("address_2")
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postal_code")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty("telephone_number")
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	@JsonProperty("telephone_number")
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

    @JsonProperty("fax_number")
    public String getFaxNumber() {
        return faxNumber;
    }
    @JsonProperty("fax_number")
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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
