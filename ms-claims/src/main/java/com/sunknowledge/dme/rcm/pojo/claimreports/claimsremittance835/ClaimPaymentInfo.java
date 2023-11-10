package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;

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
    "patientControlNumber",
    "claimStatusCode",
    "totalClaimChargeAmount",
    "claimPaymentAmount",
    "patientResponsibilityAmount",
    "claimFilingIndicatorCode",
    "payerClaimControlNumber",
    "facilityTypeCode",
    "claimFrequencyCode"
})
@Generated("jsonschema2pojo")
public class ClaimPaymentInfo {

    @JsonProperty("patientControlNumber")
    private String patientControlNumber;
    @JsonProperty("claimStatusCode")
    private String claimStatusCode;
    @JsonProperty("totalClaimChargeAmount")
    private String totalClaimChargeAmount;
    @JsonProperty("claimPaymentAmount")
    private String claimPaymentAmount;
    @JsonProperty("patientResponsibilityAmount")
    private String patientResponsibilityAmount;
    @JsonProperty("claimFilingIndicatorCode")
    private String claimFilingIndicatorCode;
    @JsonProperty("payerClaimControlNumber")
    private String payerClaimControlNumber;
    @JsonProperty("facilityTypeCode")
    private String facilityTypeCode;
    @JsonProperty("claimFrequencyCode")
    private String claimFrequencyCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("patientControlNumber")
    public String getPatientControlNumber() {
        return patientControlNumber;
    }

    @JsonProperty("patientControlNumber")
    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    @JsonProperty("claimStatusCode")
    public String getClaimStatusCode() {
        return claimStatusCode;
    }

    @JsonProperty("claimStatusCode")
    public void setClaimStatusCode(String claimStatusCode) {
        this.claimStatusCode = claimStatusCode;
    }

    @JsonProperty("totalClaimChargeAmount")
    public String getTotalClaimChargeAmount() {
        return totalClaimChargeAmount;
    }

    @JsonProperty("totalClaimChargeAmount")
    public void setTotalClaimChargeAmount(String totalClaimChargeAmount) {
        this.totalClaimChargeAmount = totalClaimChargeAmount;
    }

    @JsonProperty("claimPaymentAmount")
    public String getClaimPaymentAmount() {
        return claimPaymentAmount;
    }

    @JsonProperty("claimPaymentAmount")
    public void setClaimPaymentAmount(String claimPaymentAmount) {
        this.claimPaymentAmount = claimPaymentAmount;
    }

    @JsonProperty("patientResponsibilityAmount")
    public String getPatientResponsibilityAmount() {
        return patientResponsibilityAmount;
    }

    @JsonProperty("patientResponsibilityAmount")
    public void setPatientResponsibilityAmount(String patientResponsibilityAmount) {
        this.patientResponsibilityAmount = patientResponsibilityAmount;
    }

    @JsonProperty("claimFilingIndicatorCode")
    public String getClaimFilingIndicatorCode() {
        return claimFilingIndicatorCode;
    }

    @JsonProperty("claimFilingIndicatorCode")
    public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
        this.claimFilingIndicatorCode = claimFilingIndicatorCode;
    }

    @JsonProperty("payerClaimControlNumber")
    public String getPayerClaimControlNumber() {
        return payerClaimControlNumber;
    }

    @JsonProperty("payerClaimControlNumber")
    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    @JsonProperty("facilityTypeCode")
    public String getFacilityTypeCode() {
        return facilityTypeCode;
    }

    @JsonProperty("facilityTypeCode")
    public void setFacilityTypeCode(String facilityTypeCode) {
        this.facilityTypeCode = facilityTypeCode;
    }

    @JsonProperty("claimFrequencyCode")
    public String getClaimFrequencyCode() {
        return claimFrequencyCode;
    }

    @JsonProperty("claimFrequencyCode")
    public void setClaimFrequencyCode(String claimFrequencyCode) {
        this.claimFrequencyCode = claimFrequencyCode;
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
