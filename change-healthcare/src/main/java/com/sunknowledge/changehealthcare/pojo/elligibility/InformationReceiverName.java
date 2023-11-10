package com.sunknowledge.changehealthcare.pojo.elligibility;

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
@JsonPropertyOrder({ "address", "devicePinNumber", "contactNumber", "stateLicenceNumber", "medicareProviderNumber",
		"medicaidProviderNumber", "facilityIdNumber", "submitterIdNumber", "nationalProviderIdentifier",
		"providerPlanNetworkIdNumber", "facilityNetworkIdNumber", "priorIdentifierNumber", "socialSecurityNumber",
		"federalTaxpayerIdentificationNumber", "informationReceiverAdditionalIdentifierState" })
@Generated("jsonschema2pojo")
public class InformationReceiverName {

	@JsonProperty("address")
	private Address__2 address;
	@JsonProperty("devicePinNumber")
	private String devicePinNumber;
	@JsonProperty("contactNumber")
	private String contactNumber;
	@JsonProperty("stateLicenceNumber")
	private String stateLicenceNumber;
	@JsonProperty("medicareProviderNumber")
	private String medicareProviderNumber;
	@JsonProperty("medicaidProviderNumber")
	private String medicaidProviderNumber;
	@JsonProperty("facilityIdNumber")
	private String facilityIdNumber;
	@JsonProperty("submitterIdNumber")
	private String submitterIdNumber;
	@JsonProperty("nationalProviderIdentifier")
	private String nationalProviderIdentifier;
	@JsonProperty("providerPlanNetworkIdNumber")
	private String providerPlanNetworkIdNumber;
	@JsonProperty("facilityNetworkIdNumber")
	private String facilityNetworkIdNumber;
	@JsonProperty("priorIdentifierNumber")
	private String priorIdentifierNumber;
	@JsonProperty("socialSecurityNumber")
	private String socialSecurityNumber;
	@JsonProperty("federalTaxpayerIdentificationNumber")
	private String federalTaxpayerIdentificationNumber;
	@JsonProperty("informationReceiverAdditionalIdentifierState")
	private String informationReceiverAdditionalIdentifierState;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address")
	public Address__2 getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address__2 address) {
		this.address = address;
	}

	@JsonProperty("devicePinNumber")
	public String getDevicePinNumber() {
		return devicePinNumber;
	}

	@JsonProperty("devicePinNumber")
	public void setDevicePinNumber(String devicePinNumber) {
		this.devicePinNumber = devicePinNumber;
	}

	@JsonProperty("contactNumber")
	public String getContactNumber() {
		return contactNumber;
	}

	@JsonProperty("contactNumber")
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@JsonProperty("stateLicenceNumber")
	public String getStateLicenceNumber() {
		return stateLicenceNumber;
	}

	@JsonProperty("stateLicenceNumber")
	public void setStateLicenceNumber(String stateLicenceNumber) {
		this.stateLicenceNumber = stateLicenceNumber;
	}

	@JsonProperty("medicareProviderNumber")
	public String getMedicareProviderNumber() {
		return medicareProviderNumber;
	}

	@JsonProperty("medicareProviderNumber")
	public void setMedicareProviderNumber(String medicareProviderNumber) {
		this.medicareProviderNumber = medicareProviderNumber;
	}

	@JsonProperty("medicaidProviderNumber")
	public String getMedicaidProviderNumber() {
		return medicaidProviderNumber;
	}

	@JsonProperty("medicaidProviderNumber")
	public void setMedicaidProviderNumber(String medicaidProviderNumber) {
		this.medicaidProviderNumber = medicaidProviderNumber;
	}

	@JsonProperty("facilityIdNumber")
	public String getFacilityIdNumber() {
		return facilityIdNumber;
	}

	@JsonProperty("facilityIdNumber")
	public void setFacilityIdNumber(String facilityIdNumber) {
		this.facilityIdNumber = facilityIdNumber;
	}

	@JsonProperty("submitterIdNumber")
	public String getSubmitterIdNumber() {
		return submitterIdNumber;
	}

	@JsonProperty("submitterIdNumber")
	public void setSubmitterIdNumber(String submitterIdNumber) {
		this.submitterIdNumber = submitterIdNumber;
	}

	@JsonProperty("nationalProviderIdentifier")
	public String getNationalProviderIdentifier() {
		return nationalProviderIdentifier;
	}

	@JsonProperty("nationalProviderIdentifier")
	public void setNationalProviderIdentifier(String nationalProviderIdentifier) {
		this.nationalProviderIdentifier = nationalProviderIdentifier;
	}

	@JsonProperty("providerPlanNetworkIdNumber")
	public String getProviderPlanNetworkIdNumber() {
		return providerPlanNetworkIdNumber;
	}

	@JsonProperty("providerPlanNetworkIdNumber")
	public void setProviderPlanNetworkIdNumber(String providerPlanNetworkIdNumber) {
		this.providerPlanNetworkIdNumber = providerPlanNetworkIdNumber;
	}

	@JsonProperty("facilityNetworkIdNumber")
	public String getFacilityNetworkIdNumber() {
		return facilityNetworkIdNumber;
	}

	@JsonProperty("facilityNetworkIdNumber")
	public void setFacilityNetworkIdNumber(String facilityNetworkIdNumber) {
		this.facilityNetworkIdNumber = facilityNetworkIdNumber;
	}

	@JsonProperty("priorIdentifierNumber")
	public String getPriorIdentifierNumber() {
		return priorIdentifierNumber;
	}

	@JsonProperty("priorIdentifierNumber")
	public void setPriorIdentifierNumber(String priorIdentifierNumber) {
		this.priorIdentifierNumber = priorIdentifierNumber;
	}

	@JsonProperty("socialSecurityNumber")
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	@JsonProperty("socialSecurityNumber")
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	@JsonProperty("federalTaxpayerIdentificationNumber")
	public String getFederalTaxpayerIdentificationNumber() {
		return federalTaxpayerIdentificationNumber;
	}

	@JsonProperty("federalTaxpayerIdentificationNumber")
	public void setFederalTaxpayerIdentificationNumber(String federalTaxpayerIdentificationNumber) {
		this.federalTaxpayerIdentificationNumber = federalTaxpayerIdentificationNumber;
	}

	@JsonProperty("informationReceiverAdditionalIdentifierState")
	public String getInformationReceiverAdditionalIdentifierState() {
		return informationReceiverAdditionalIdentifierState;
	}

	@JsonProperty("informationReceiverAdditionalIdentifierState")
	public void setInformationReceiverAdditionalIdentifierState(String informationReceiverAdditionalIdentifierState) {
		this.informationReceiverAdditionalIdentifierState = informationReceiverAdditionalIdentifierState;
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