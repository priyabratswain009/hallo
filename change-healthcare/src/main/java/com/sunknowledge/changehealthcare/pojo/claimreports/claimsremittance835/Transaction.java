package com.sunknowledge.changehealthcare.pojo.claimreports.claimsremittance835;

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
    "referenceIdentification",
    "transactionSetCreationDate",
    "transactionSetCreationTime",
    "payers"
})
@Generated("jsonschema2pojo")
public class Transaction {

    @JsonProperty("controlNumber")
    private String controlNumber;
    @JsonProperty("referenceIdentification")
    private String referenceIdentification;
    @JsonProperty("transactionSetCreationDate")
    private String transactionSetCreationDate;
    @JsonProperty("transactionSetCreationTime")
    private String transactionSetCreationTime;
    @JsonProperty("payers")
    private List<Payer> payers = null;
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

    @JsonProperty("referenceIdentification")
    public String getReferenceIdentification() {
        return referenceIdentification;
    }

    @JsonProperty("referenceIdentification")
    public void setReferenceIdentification(String referenceIdentification) {
        this.referenceIdentification = referenceIdentification;
    }

    @JsonProperty("transactionSetCreationDate")
    public String getTransactionSetCreationDate() {
        return transactionSetCreationDate;
    }

    @JsonProperty("transactionSetCreationDate")
    public void setTransactionSetCreationDate(String transactionSetCreationDate) {
        this.transactionSetCreationDate = transactionSetCreationDate;
    }

    @JsonProperty("transactionSetCreationTime")
    public String getTransactionSetCreationTime() {
        return transactionSetCreationTime;
    }

    @JsonProperty("transactionSetCreationTime")
    public void setTransactionSetCreationTime(String transactionSetCreationTime) {
        this.transactionSetCreationTime = transactionSetCreationTime;
    }

    @JsonProperty("payers")
    public List<Payer> getPayers() {
        return payers;
    }

    @JsonProperty("payers")
    public void setPayers(List<Payer> payers) {
        this.payers = payers;
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
