package com.sunknowledge.changehealthcare.pojo.attachmentSubmission;

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
@JsonPropertyOrder({ "controlNumber", "tradingPartnerServiceId", "tradingPartnerName", "payerAddress", "submitter",
		"receiver", "provider", "subscriber", "claimInformation" })
@Generated("jsonschema2pojo")
public class AttachmentSubmissionInput {

	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("tradingPartnerServiceId")
	private String tradingPartnerServiceId;
	@JsonProperty("tradingPartnerName")
	private String tradingPartnerName;
	@JsonProperty("payerAddress")
	private PayerAddress payerAddress;
	@JsonProperty("submitter")
	private Submitter submitter;
	@JsonProperty("receiver")
	private Receiver receiver;
	@JsonProperty("provider")
	private Provider provider;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("claimInformation")
	private ClaimInformation claimInformation;
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

	@JsonProperty("payerAddress")
	public PayerAddress getPayerAddress() {
		return payerAddress;
	}

	@JsonProperty("payerAddress")
	public void setPayerAddress(PayerAddress payerAddress) {
		this.payerAddress = payerAddress;
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

	@JsonProperty("provider")
	public Provider getProvider() {
		return provider;
	}

	@JsonProperty("provider")
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@JsonProperty("subscriber")
	public Subscriber getSubscriber() {
		return subscriber;
	}

	@JsonProperty("subscriber")
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@JsonProperty("claimInformation")
	public ClaimInformation getClaimInformation() {
		return claimInformation;
	}

	@JsonProperty("claimInformation")
	public void setClaimInformation(ClaimInformation claimInformation) {
		this.claimInformation = claimInformation;
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