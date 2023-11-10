package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;

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
    "claimReceivedDate",
    "claimPaymentInfo",
    "patientName",
    "renderingProvider",
    "crossoverCarrier",
    "outpatientAdjudication",
    "serviceLines"
})
@Generated("jsonschema2pojo")
public class PaymentInfo {

    @JsonProperty("claimReceivedDate")
    private String claimReceivedDate;
    @JsonProperty("claimPaymentInfo")
    private ClaimPaymentInfo claimPaymentInfo;
    @JsonProperty("patientName")
    private PatientName patientName;
    @JsonProperty("renderingProvider")
    private RenderingProvider renderingProvider;
    @JsonProperty("crossoverCarrier")
    private CrossoverCarrier crossoverCarrier;
    @JsonProperty("outpatientAdjudication")
    private OutpatientAdjudication outpatientAdjudication;
    @JsonProperty("serviceLines")
    private List<ServiceLine> serviceLines = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("claimReceivedDate")
    public String getClaimReceivedDate() {
        return claimReceivedDate;
    }

    @JsonProperty("claimReceivedDate")
    public void setClaimReceivedDate(String claimReceivedDate) {
        this.claimReceivedDate = claimReceivedDate;
    }

    @JsonProperty("claimPaymentInfo")
    public ClaimPaymentInfo getClaimPaymentInfo() {
        return claimPaymentInfo;
    }

    @JsonProperty("claimPaymentInfo")
    public void setClaimPaymentInfo(ClaimPaymentInfo claimPaymentInfo) {
        this.claimPaymentInfo = claimPaymentInfo;
    }

    @JsonProperty("patientName")
    public PatientName getPatientName() {
        return patientName;
    }

    @JsonProperty("patientName")
    public void setPatientName(PatientName patientName) {
        this.patientName = patientName;
    }

    @JsonProperty("renderingProvider")
    public RenderingProvider getRenderingProvider() {
        return renderingProvider;
    }

    @JsonProperty("renderingProvider")
    public void setRenderingProvider(RenderingProvider renderingProvider) {
        this.renderingProvider = renderingProvider;
    }

    @JsonProperty("crossoverCarrier")
    public CrossoverCarrier getCrossoverCarrier() {
        return crossoverCarrier;
    }

    @JsonProperty("crossoverCarrier")
    public void setCrossoverCarrier(CrossoverCarrier crossoverCarrier) {
        this.crossoverCarrier = crossoverCarrier;
    }

    @JsonProperty("outpatientAdjudication")
    public OutpatientAdjudication getOutpatientAdjudication() {
        return outpatientAdjudication;
    }

    @JsonProperty("outpatientAdjudication")
    public void setOutpatientAdjudication(OutpatientAdjudication outpatientAdjudication) {
        this.outpatientAdjudication = outpatientAdjudication;
    }

    @JsonProperty("serviceLines")
    public List<ServiceLine> getServiceLines() {
        return serviceLines;
    }

    @JsonProperty("serviceLines")
    public void setServiceLines(List<ServiceLine> serviceLines) {
        this.serviceLines = serviceLines;
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
