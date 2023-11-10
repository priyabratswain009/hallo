package com.sunknowledge.changehealthcare.pojo.claimStatus;

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
@JsonPropertyOrder({ "controlNumber", "tradingPartnerServiceId", "providers", "subscriber", "dependent", "encounter",
		"serviceLineInformation" })
@Generated("jsonschema2pojo")
public class ClaimStatusRequestInput {

	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("tradingPartnerServiceId")
	private String tradingPartnerServiceId;
	@JsonProperty("providers")
	private List<Provider> providers = null;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("dependent")
	private Dependent dependent;
	@JsonProperty("encounter")
	private Encounter encounter;
	@JsonProperty("serviceLineInformation")
	private ServiceLineInformation serviceLineInformation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("controlNumber")
	public String getControlNumber() {
		return controlNumber;
	}

	@JsonProperty("controlNumber")
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	@JsonProperty("tradingPartnerServiceId")
	public String getTradingPartnerServiceId() {
		return tradingPartnerServiceId;
	}

	@JsonProperty("tradingPartnerServiceId")
	public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
		this.tradingPartnerServiceId = tradingPartnerServiceId;
	}

	@JsonProperty("providers")
	public List<Provider> getProviders() {
		return providers;
	}

	@JsonProperty("providers")
	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	@JsonProperty("subscriber")
	public Subscriber getSubscriber() {
		return subscriber;
	}

	@JsonProperty("subscriber")
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@JsonProperty("dependent")
	public Dependent getDependent() {
		return dependent;
	}

	@JsonProperty("dependent")
	public void setDependent(Dependent dependent) {
		this.dependent = dependent;
	}

	@JsonProperty("encounter")
	public Encounter getEncounter() {
		return encounter;
	}

	@JsonProperty("encounter")
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	@JsonProperty("serviceLineInformation")
	public ServiceLineInformation getServiceLineInformation() {
		return serviceLineInformation;
	}

	@JsonProperty("serviceLineInformation")
	public void setServiceLineInformation(ServiceLineInformation serviceLineInformation) {
		this.serviceLineInformation = serviceLineInformation;
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