package com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277;

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
    "transactionHandlingCode",
    "totalActualProviderPaymentAmount",
    "creditOrDebitFlagCode",
    "paymentMethodCode",
    "paymentFormatCode",
    "payerIdentifier",
    "checkIssueOrEFTEffectiveDate",
    "senderAccountDetails",
    "receiverAccountDetails"
})
@Generated("jsonschema2pojo")
public class FinancialInformation {

    @JsonProperty("transactionHandlingCode")
    private String transactionHandlingCode;
    @JsonProperty("totalActualProviderPaymentAmount")
    private String totalActualProviderPaymentAmount;
    @JsonProperty("creditOrDebitFlagCode")
    private String creditOrDebitFlagCode;
    @JsonProperty("paymentMethodCode")
    private String paymentMethodCode;
    @JsonProperty("paymentFormatCode")
    private String paymentFormatCode;
    @JsonProperty("payerIdentifier")
    private String payerIdentifier;
    @JsonProperty("checkIssueOrEFTEffectiveDate")
    private String checkIssueOrEFTEffectiveDate;
    @JsonProperty("senderAccountDetails")
    private SenderAccountDetails senderAccountDetails;
    @JsonProperty("receiverAccountDetails")
    private ReceiverAccountDetails receiverAccountDetails;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("transactionHandlingCode")
    public String getTransactionHandlingCode() {
        return transactionHandlingCode;
    }

    @JsonProperty("transactionHandlingCode")
    public void setTransactionHandlingCode(String transactionHandlingCode) {
        this.transactionHandlingCode = transactionHandlingCode;
    }

    @JsonProperty("totalActualProviderPaymentAmount")
    public String getTotalActualProviderPaymentAmount() {
        return totalActualProviderPaymentAmount;
    }

    @JsonProperty("totalActualProviderPaymentAmount")
    public void setTotalActualProviderPaymentAmount(String totalActualProviderPaymentAmount) {
        this.totalActualProviderPaymentAmount = totalActualProviderPaymentAmount;
    }

    @JsonProperty("creditOrDebitFlagCode")
    public String getCreditOrDebitFlagCode() {
        return creditOrDebitFlagCode;
    }

    @JsonProperty("creditOrDebitFlagCode")
    public void setCreditOrDebitFlagCode(String creditOrDebitFlagCode) {
        this.creditOrDebitFlagCode = creditOrDebitFlagCode;
    }

    @JsonProperty("paymentMethodCode")
    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    @JsonProperty("paymentMethodCode")
    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    @JsonProperty("paymentFormatCode")
    public String getPaymentFormatCode() {
        return paymentFormatCode;
    }

    @JsonProperty("paymentFormatCode")
    public void setPaymentFormatCode(String paymentFormatCode) {
        this.paymentFormatCode = paymentFormatCode;
    }

    @JsonProperty("payerIdentifier")
    public String getPayerIdentifier() {
        return payerIdentifier;
    }

    @JsonProperty("payerIdentifier")
    public void setPayerIdentifier(String payerIdentifier) {
        this.payerIdentifier = payerIdentifier;
    }

    @JsonProperty("checkIssueOrEFTEffectiveDate")
    public String getCheckIssueOrEFTEffectiveDate() {
        return checkIssueOrEFTEffectiveDate;
    }

    @JsonProperty("checkIssueOrEFTEffectiveDate")
    public void setCheckIssueOrEFTEffectiveDate(String checkIssueOrEFTEffectiveDate) {
        this.checkIssueOrEFTEffectiveDate = checkIssueOrEFTEffectiveDate;
    }

    @JsonProperty("senderAccountDetails")
    public SenderAccountDetails getSenderAccountDetails() {
        return senderAccountDetails;
    }

    @JsonProperty("senderAccountDetails")
    public void setSenderAccountDetails(SenderAccountDetails senderAccountDetails) {
        this.senderAccountDetails = senderAccountDetails;
    }

    @JsonProperty("receiverAccountDetails")
    public ReceiverAccountDetails getReceiverAccountDetails() {
        return receiverAccountDetails;
    }

    @JsonProperty("receiverAccountDetails")
    public void setReceiverAccountDetails(ReceiverAccountDetails receiverAccountDetails) {
        this.receiverAccountDetails = receiverAccountDetails;
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
