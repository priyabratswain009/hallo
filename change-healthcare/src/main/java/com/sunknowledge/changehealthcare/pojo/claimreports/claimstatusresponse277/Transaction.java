package com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277;

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
@JsonPropertyOrder({
    "controlNumber",
    "paymentAndRemitReassociationDetails",
    "productionDate",
    "receiverIdentifier",
    "financialInformation",
    "payer",
    "payee",
    "detailInfo"
})
@Generated("jsonschema2pojo")
public class Transaction {

    @JsonProperty("controlNumber")
    private String controlNumber;
    @JsonProperty("paymentAndRemitReassociationDetails")
    private PaymentAndRemitReassociationDetails paymentAndRemitReassociationDetails;
    @JsonProperty("productionDate")
    private String productionDate;
    @JsonProperty("receiverIdentifier")
    private String receiverIdentifier;
    @JsonProperty("financialInformation")
    private FinancialInformation financialInformation;
    @JsonProperty("payer")
    private Payer payer;
    @JsonProperty("payee")
    private Payee payee;
    @JsonProperty("detailInfo")
    private List<DetailInfo> detailInfo = null;
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

    @JsonProperty("paymentAndRemitReassociationDetails")
    public PaymentAndRemitReassociationDetails getPaymentAndRemitReassociationDetails() {
        return paymentAndRemitReassociationDetails;
    }

    @JsonProperty("paymentAndRemitReassociationDetails")
    public void setPaymentAndRemitReassociationDetails(PaymentAndRemitReassociationDetails paymentAndRemitReassociationDetails) {
        this.paymentAndRemitReassociationDetails = paymentAndRemitReassociationDetails;
    }

    @JsonProperty("productionDate")
    public String getProductionDate() {
        return productionDate;
    }

    @JsonProperty("productionDate")
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    @JsonProperty("receiverIdentifier")
    public String getReceiverIdentifier() {
        return receiverIdentifier;
    }

    @JsonProperty("receiverIdentifier")
    public void setReceiverIdentifier(String receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }

    @JsonProperty("financialInformation")
    public FinancialInformation getFinancialInformation() {
        return financialInformation;
    }

    @JsonProperty("financialInformation")
    public void setFinancialInformation(FinancialInformation financialInformation) {
        this.financialInformation = financialInformation;
    }

    @JsonProperty("payer")
    public Payer getPayer() {
        return payer;
    }

    @JsonProperty("payer")
    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    @JsonProperty("payee")
    public Payee getPayee() {
        return payee;
    }

    @JsonProperty("payee")
    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    @JsonProperty("detailInfo")
    public List<DetailInfo> getDetailInfo() {
        return detailInfo;
    }

    @JsonProperty("detailInfo")
    public void setDetailInfo(List<DetailInfo> detailInfo) {
        this.detailInfo = detailInfo;
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
