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
@JsonPropertyOrder({ "controlNumber", "tradingPartnerServiceId", "payer", "providers", "subscriber", "claims",
		"reassociationKey", "status", "meta" })
@Generated("jsonschema2pojo")
public class ClaimStatusRequestOutput {

	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("tradingPartnerServiceId")
	private String tradingPartnerServiceId;
	@JsonProperty("payer")
	private Payer payer;
	@JsonProperty("providers")
	private List<Provider> providers = null;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("claims")
	private List<Claim> claims = null;
	@JsonProperty("reassociationKey")
	private String reassociationKey;
	@JsonProperty("status")
	private String status;
	@JsonProperty("meta")
	private Meta meta;
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

	@JsonProperty("payer")
	public Payer getPayer() {
		return payer;
	}

	@JsonProperty("payer")
	public void setPayer(Payer payer) {
		this.payer = payer;
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

	@JsonProperty("claims")
	public List<Claim> getClaims() {
		return claims;
	}

	@JsonProperty("claims")
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	@JsonProperty("reassociationKey")
	public String getReassociationKey() {
		return reassociationKey;
	}

	@JsonProperty("reassociationKey")
	public void setReassociationKey(String reassociationKey) {
		this.reassociationKey = reassociationKey;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("meta")
	public Meta getMeta() {
		return meta;
	}

	@JsonProperty("meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
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