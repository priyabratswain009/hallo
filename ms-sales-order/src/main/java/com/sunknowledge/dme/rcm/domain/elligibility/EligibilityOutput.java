package com.sunknowledge.dme.rcm.domain.elligibility;

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
@JsonPropertyOrder({ "meta", "controlNumber", "reassociationKey", "tradingPartnerServiceId", "provider", "subscriber",
		"subscriberTraceNumbers", "payer", "planInformation", "planDateInformation", "planStatus",
		"benefitsInformation", "x12" })
@Generated("jsonschema2pojo")
public class EligibilityOutput {

	@JsonProperty("meta")
	private Meta meta;
	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("reassociationKey")
	private String reassociationKey;
	@JsonProperty("tradingPartnerServiceId")
	private String tradingPartnerServiceId;
	@JsonProperty("provider")
	private ProviderOP provider;
	@JsonProperty("subscriber")
	private SubscriberOP subscriber;
	@JsonProperty("subscriberTraceNumbers")
	private List<SubscriberTraceNumber> subscriberTraceNumbers = null;
	@JsonProperty("payer")
	private Payer payer;
	@JsonProperty("planInformation")
	private PlanInformation planInformation;
	@JsonProperty("planDateInformation")
	private PlanDateInformation planDateInformation;
	@JsonProperty("planStatus")
	private List<Planstatus> planStatus = null;
	@JsonProperty("benefitsInformation")
	private List<BenefitsInformation> benefitsInformation = null;
	@JsonProperty("x12")
	private String x12;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("meta")
	public Meta getMeta() {
		return meta;
	}

	@JsonProperty("meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	@JsonProperty("controlNumber")
	public String getControlNumber() {
		return controlNumber;
	}

	@JsonProperty("controlNumber")
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	@JsonProperty("reassociationKey")
	public String getReassociationKey() {
		return reassociationKey;
	}

	@JsonProperty("reassociationKey")
	public void setReassociationKey(String reassociationKey) {
		this.reassociationKey = reassociationKey;
	}

	public ProviderOP getProvider() {
		return provider;
	}

	public void setProvider(ProviderOP provider) {
		this.provider = provider;
	}

	public SubscriberOP getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(SubscriberOP subscriber) {
		this.subscriber = subscriber;
	}

	@JsonProperty("tradingPartnerServiceId")
	public String getTradingPartnerServiceId() {
		return tradingPartnerServiceId;
	}

	@JsonProperty("tradingPartnerServiceId")
	public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
		this.tradingPartnerServiceId = tradingPartnerServiceId;
	}

	@JsonProperty("subscriberTraceNumbers")
	public List<SubscriberTraceNumber> getSubscriberTraceNumbers() {
		return subscriberTraceNumbers;
	}

	@JsonProperty("subscriberTraceNumbers")
	public void setSubscriberTraceNumbers(List<SubscriberTraceNumber> subscriberTraceNumbers) {
		this.subscriberTraceNumbers = subscriberTraceNumbers;
	}

	@JsonProperty("payer")
	public Payer getPayer() {
		return payer;
	}

	@JsonProperty("payer")
	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	@JsonProperty("planInformation")
	public PlanInformation getPlanInformation() {
		return planInformation;
	}

	@JsonProperty("planInformation")
	public void setPlanInformation(PlanInformation planInformation) {
		this.planInformation = planInformation;
	}

	@JsonProperty("planDateInformation")
	public PlanDateInformation getPlanDateInformation() {
		return planDateInformation;
	}

	@JsonProperty("planDateInformation")
	public void setPlanDateInformation(PlanDateInformation planDateInformation) {
		this.planDateInformation = planDateInformation;
	}

	@JsonProperty("planStatus")
	public List<Planstatus> getPlanStatus() {
		return planStatus;
	}

	@JsonProperty("planStatus")
	public void setPlanStatus(List<Planstatus> planStatus) {
		this.planStatus = planStatus;
	}

	@JsonProperty("benefitsInformation")
	public List<BenefitsInformation> getBenefitsInformation() {
		return benefitsInformation;
	}

	@JsonProperty("benefitsInformation")
	public void setBenefitsInformation(List<BenefitsInformation> benefitsInformation) {
		this.benefitsInformation = benefitsInformation;
	}

	@JsonProperty("x12")
	public String getX12() {
		return x12;
	}

	@JsonProperty("x12")
	public void setX12(String x12) {
		this.x12 = x12;
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