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
    "productOrServiceIDQualifier",
    "productOrServiceIDQualifierValue",
    "adjudicatedProcedureCode",
    "adjudicatedProcedureModifierCodes",
    "lineItemChargeAmount",
    "lineItemProviderPaymentAmount",
    "unitsOfServicePaidCount",
    "submittedProductOrServiceIDQualifier",
    "submittedProductOrServiceIDQualifierValue",
    "submittedAdjudicatedProcedureCode",
    "submittedAdjudicatedProcedureModifierCodes"
})
@Generated("jsonschema2pojo")
public class ServicePaymentInformation {

    @JsonProperty("productOrServiceIDQualifier")
    private String productOrServiceIDQualifier;
    @JsonProperty("productOrServiceIDQualifierValue")
    private String productOrServiceIDQualifierValue;
    @JsonProperty("adjudicatedProcedureCode")
    private String adjudicatedProcedureCode;
    @JsonProperty("adjudicatedProcedureModifierCodes")
    private List<String> adjudicatedProcedureModifierCodes = null;
    @JsonProperty("lineItemChargeAmount")
    private String lineItemChargeAmount;
    @JsonProperty("lineItemProviderPaymentAmount")
    private String lineItemProviderPaymentAmount;
    @JsonProperty("unitsOfServicePaidCount")
    private String unitsOfServicePaidCount;
    @JsonProperty("submittedProductOrServiceIDQualifier")
    private String submittedProductOrServiceIDQualifier;
    @JsonProperty("submittedProductOrServiceIDQualifierValue")
    private String submittedProductOrServiceIDQualifierValue;
    @JsonProperty("submittedAdjudicatedProcedureCode")
    private String submittedAdjudicatedProcedureCode;
    @JsonProperty("submittedAdjudicatedProcedureModifierCodes")
    private List<String> submittedAdjudicatedProcedureModifierCodes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("productOrServiceIDQualifier")
    public String getProductOrServiceIDQualifier() {
        return productOrServiceIDQualifier;
    }

    @JsonProperty("productOrServiceIDQualifier")
    public void setProductOrServiceIDQualifier(String productOrServiceIDQualifier) {
        this.productOrServiceIDQualifier = productOrServiceIDQualifier;
    }

    @JsonProperty("productOrServiceIDQualifierValue")
    public String getProductOrServiceIDQualifierValue() {
        return productOrServiceIDQualifierValue;
    }

    @JsonProperty("productOrServiceIDQualifierValue")
    public void setProductOrServiceIDQualifierValue(String productOrServiceIDQualifierValue) {
        this.productOrServiceIDQualifierValue = productOrServiceIDQualifierValue;
    }

    @JsonProperty("adjudicatedProcedureCode")
    public String getAdjudicatedProcedureCode() {
        return adjudicatedProcedureCode;
    }

    @JsonProperty("adjudicatedProcedureCode")
    public void setAdjudicatedProcedureCode(String adjudicatedProcedureCode) {
        this.adjudicatedProcedureCode = adjudicatedProcedureCode;
    }

    @JsonProperty("adjudicatedProcedureModifierCodes")
    public List<String> getAdjudicatedProcedureModifierCodes() {
        return adjudicatedProcedureModifierCodes;
    }

    @JsonProperty("adjudicatedProcedureModifierCodes")
    public void setAdjudicatedProcedureModifierCodes(List<String> adjudicatedProcedureModifierCodes) {
        this.adjudicatedProcedureModifierCodes = adjudicatedProcedureModifierCodes;
    }

    @JsonProperty("lineItemChargeAmount")
    public String getLineItemChargeAmount() {
        return lineItemChargeAmount;
    }

    @JsonProperty("lineItemChargeAmount")
    public void setLineItemChargeAmount(String lineItemChargeAmount) {
        this.lineItemChargeAmount = lineItemChargeAmount;
    }

    @JsonProperty("lineItemProviderPaymentAmount")
    public String getLineItemProviderPaymentAmount() {
        return lineItemProviderPaymentAmount;
    }

    @JsonProperty("lineItemProviderPaymentAmount")
    public void setLineItemProviderPaymentAmount(String lineItemProviderPaymentAmount) {
        this.lineItemProviderPaymentAmount = lineItemProviderPaymentAmount;
    }

    @JsonProperty("unitsOfServicePaidCount")
    public String getUnitsOfServicePaidCount() {
        return unitsOfServicePaidCount;
    }

    @JsonProperty("unitsOfServicePaidCount")
    public void setUnitsOfServicePaidCount(String unitsOfServicePaidCount) {
        this.unitsOfServicePaidCount = unitsOfServicePaidCount;
    }

    @JsonProperty("submittedProductOrServiceIDQualifier")
    public String getSubmittedProductOrServiceIDQualifier() {
        return submittedProductOrServiceIDQualifier;
    }

    @JsonProperty("submittedProductOrServiceIDQualifier")
    public void setSubmittedProductOrServiceIDQualifier(String submittedProductOrServiceIDQualifier) {
        this.submittedProductOrServiceIDQualifier = submittedProductOrServiceIDQualifier;
    }

    @JsonProperty("submittedProductOrServiceIDQualifierValue")
    public String getSubmittedProductOrServiceIDQualifierValue() {
        return submittedProductOrServiceIDQualifierValue;
    }

    @JsonProperty("submittedProductOrServiceIDQualifierValue")
    public void setSubmittedProductOrServiceIDQualifierValue(String submittedProductOrServiceIDQualifierValue) {
        this.submittedProductOrServiceIDQualifierValue = submittedProductOrServiceIDQualifierValue;
    }

    @JsonProperty("submittedAdjudicatedProcedureCode")
    public String getSubmittedAdjudicatedProcedureCode() {
        return submittedAdjudicatedProcedureCode;
    }

    @JsonProperty("submittedAdjudicatedProcedureCode")
    public void setSubmittedAdjudicatedProcedureCode(String submittedAdjudicatedProcedureCode) {
        this.submittedAdjudicatedProcedureCode = submittedAdjudicatedProcedureCode;
    }

    @JsonProperty("submittedAdjudicatedProcedureModifierCodes")
    public List<String> getSubmittedAdjudicatedProcedureModifierCodes() {
        return submittedAdjudicatedProcedureModifierCodes;
    }

    @JsonProperty("submittedAdjudicatedProcedureModifierCodes")
    public void setSubmittedAdjudicatedProcedureModifierCodes(List<String> submittedAdjudicatedProcedureModifierCodes) {
        this.submittedAdjudicatedProcedureModifierCodes = submittedAdjudicatedProcedureModifierCodes;
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
