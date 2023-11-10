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
@JsonPropertyOrder({ "controlNumber", "tradingPartnerServiceId", "tradingPartnerName", "usageIndicator", "payerAddress",
		"payToAddress", "payToPlan", "submitter", "receiver", "providers", "subscriber", "dependents",
		"claimInformation", "otherPayerName", "billing", "referring", "rendering", "ordering", "supervising" })
@Generated("jsonschema2pojo")
public class ProfessionalClaimSubmission {

	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("tradingPartnerServiceId")
	private String tradingPartnerServiceId;
	@JsonProperty("tradingPartnerName")
	private String tradingPartnerName;
	@JsonProperty("usageIndicator")
	private String usageIndicator;
	@JsonProperty("payerAddress")
	private PayerAddress payerAddress;
	@JsonProperty("payToAddress")
	private PayToAddress payToAddress;
	@JsonProperty("payToPlan")
	private PayToPlan payToPlan;
	@JsonProperty("submitter")
	private Submitter submitter;
	@JsonProperty("receiver")
	private Receiver receiver;
	@JsonProperty("providers")
	private List<Provider> providers = null;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("dependents")
	private Dependents dependents;
	@JsonProperty("claimInformation")
	private ClaimInformation claimInformation;
	@JsonProperty("otherPayerName")
	private OtherPayerName otherPayerName;
	@JsonProperty("billing")
	private Billing billing;
	@JsonProperty("referring")
	private Referring referring;
	@JsonProperty("rendering")
	private Rendering rendering;
	@JsonProperty("ordering")
	private Ordering ordering;
	@JsonProperty("supervising")
	private Supervising supervising;
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

	@JsonProperty("tradingPartnerName")
	public String getTradingPartnerName() {
		return tradingPartnerName;
	}

	@JsonProperty("tradingPartnerName")
	public void setTradingPartnerName(String tradingPartnerName) {
		this.tradingPartnerName = tradingPartnerName;
	}

	@JsonProperty("usageIndicator")
	public String getUsageIndicator() {
		return usageIndicator;
	}

	@JsonProperty("usageIndicator")
	public void setUsageIndicator(String usageIndicator) {
		this.usageIndicator = usageIndicator;
	}

	@JsonProperty("payerAddress")
	public PayerAddress getPayerAddress() {
		return payerAddress;
	}

	@JsonProperty("payerAddress")
	public void setPayerAddress(PayerAddress payerAddress) {
		this.payerAddress = payerAddress;
	}

	@JsonProperty("payToAddress")
	public PayToAddress getPayToAddress() {
		return payToAddress;
	}

	@JsonProperty("payToAddress")
	public void setPayToAddress(PayToAddress payToAddress) {
		this.payToAddress = payToAddress;
	}

	@JsonProperty("payToPlan")
	public PayToPlan getPayToPlan() {
		return payToPlan;
	}

	@JsonProperty("payToPlan")
	public void setPayToPlan(PayToPlan payToPlan) {
		this.payToPlan = payToPlan;
	}

	@JsonProperty("submitter")
	public Submitter getSubmitter() {
		return submitter;
	}

	@JsonProperty("submitter")
	public void setSubmitter(Submitter submitter) {
		this.submitter = submitter;
	}

	@JsonProperty("receiver")
	public Receiver getReceiver() {
		return receiver;
	}

	@JsonProperty("receiver")
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
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

	@JsonProperty("dependents")
	public Dependents getDependents() {
		return dependents;
	}

	@JsonProperty("dependents")
	public void setDependents(Dependents dependents) {
		this.dependents = dependents;
	}

	@JsonProperty("claimInformation")
	public ClaimInformation getClaimInformation() {
		return claimInformation;
	}

	@JsonProperty("claimInformation")
	public void setClaimInformation(ClaimInformation claimInformation) {
		this.claimInformation = claimInformation;
	}

	@JsonProperty("otherPayerName")
	public OtherPayerName getOtherPayerName() {
		return otherPayerName;
	}

	@JsonProperty("otherPayerName")
	public void setOtherPayerName(OtherPayerName otherPayerName) {
		this.otherPayerName = otherPayerName;
	}

	@JsonProperty("billing")
	public Billing getBilling() {
		return billing;
	}

	@JsonProperty("billing")
	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	@JsonProperty("referring")
	public Referring getReferring() {
		return referring;
	}

	@JsonProperty("referring")
	public void setReferring(Referring referring) {
		this.referring = referring;
	}

	@JsonProperty("rendering")
	public Rendering getRendering() {
		return rendering;
	}

	@JsonProperty("rendering")
	public void setRendering(Rendering rendering) {
		this.rendering = rendering;
	}

	@JsonProperty("ordering")
	public Ordering getOrdering() {
		return ordering;
	}

	@JsonProperty("ordering")
	public void setOrdering(Ordering ordering) {
		this.ordering = ordering;
	}

	@JsonProperty("supervising")
	public Supervising getSupervising() {
		return supervising;
	}

	@JsonProperty("supervising")
	public void setSupervising(Supervising supervising) {
		this.supervising = supervising;
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
