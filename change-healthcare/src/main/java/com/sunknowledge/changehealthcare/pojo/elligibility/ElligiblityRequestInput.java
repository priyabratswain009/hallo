package com.sunknowledge.changehealthcare.pojo.elligibility;

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
@JsonPropertyOrder({ "tradingPartnerServiceId", "controlNumber", "submitterTransactionIdentifier", "tradingPartnerName",
		"portalUsername", "portalPassword", "subscriberTraceNumber", "subscriber", "provider",
		"informationReceiverName", "dependents", "encounter" })
@Generated("jsonschema2pojo")
public class ElligiblityRequestInput {

	@JsonProperty("tradingPartnerServiceId")
	private String tradingPartnerServiceId;
	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("submitterTransactionIdentifier")
	private String submitterTransactionIdentifier;
	@JsonProperty("tradingPartnerName")
	private String tradingPartnerName;
	@JsonProperty("portalUsername")
	private String portalUsername;
	@JsonProperty("portalPassword")
	private String portalPassword;
	@JsonProperty("subscriberTraceNumber")
	private SubscriberTraceNumber subscriberTraceNumber;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("provider")
	private Provider provider;
	@JsonProperty("informationReceiverName")
	private InformationReceiverName informationReceiverName;
	@JsonProperty("dependents")
	private List<Dependent> dependents = null;
	@JsonProperty("encounter")
	private Encounter encounter;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("tradingPartnerServiceId")
	public String getTradingPartnerServiceId() {
		return tradingPartnerServiceId;
	}

	@JsonProperty("tradingPartnerServiceId")
	public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
		this.tradingPartnerServiceId = tradingPartnerServiceId;
	}

	@JsonProperty("controlNumber")
	public String getControlNumber() {
		return controlNumber;
	}

	@JsonProperty("controlNumber")
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	@JsonProperty("submitterTransactionIdentifier")
	public String getSubmitterTransactionIdentifier() {
		return submitterTransactionIdentifier;
	}

	@JsonProperty("submitterTransactionIdentifier")
	public void setSubmitterTransactionIdentifier(String submitterTransactionIdentifier) {
		this.submitterTransactionIdentifier = submitterTransactionIdentifier;
	}

	@JsonProperty("tradingPartnerName")
	public String getTradingPartnerName() {
		return tradingPartnerName;
	}

	@JsonProperty("tradingPartnerName")
	public void setTradingPartnerName(String tradingPartnerName) {
		this.tradingPartnerName = tradingPartnerName;
	}

	@JsonProperty("portalUsername")
	public String getPortalUsername() {
		return portalUsername;
	}

	@JsonProperty("portalUsername")
	public void setPortalUsername(String portalUsername) {
		this.portalUsername = portalUsername;
	}

	@JsonProperty("portalPassword")
	public String getPortalPassword() {
		return portalPassword;
	}

	@JsonProperty("portalPassword")
	public void setPortalPassword(String portalPassword) {
		this.portalPassword = portalPassword;
	}

	@JsonProperty("subscriberTraceNumber")
	public SubscriberTraceNumber getSubscriberTraceNumber() {
		return subscriberTraceNumber;
	}

	@JsonProperty("subscriberTraceNumber")
	public void setSubscriberTraceNumber(SubscriberTraceNumber subscriberTraceNumber) {
		this.subscriberTraceNumber = subscriberTraceNumber;
	}

	@JsonProperty("subscriber")
	public Subscriber getSubscriber() {
		return subscriber;
	}

	@JsonProperty("subscriber")
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@JsonProperty("provider")
	public Provider getProvider() {
		return provider;
	}

	@JsonProperty("provider")
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@JsonProperty("informationReceiverName")
	public InformationReceiverName getInformationReceiverName() {
		return informationReceiverName;
	}

	@JsonProperty("informationReceiverName")
	public void setInformationReceiverName(InformationReceiverName informationReceiverName) {
		this.informationReceiverName = informationReceiverName;
	}

	@JsonProperty("dependents")
	public List<Dependent> getDependents() {
		return dependents;
	}

	@JsonProperty("dependents")
	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	@JsonProperty("encounter")
	public Encounter getEncounter() {
		return encounter;
	}

	@JsonProperty("encounter")
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
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