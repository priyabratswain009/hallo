package com.sunknowledge.dme.rcm.service.dto.delivery;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deliveryTicketId",
    "patientName",
    "documentData",
    "deliveryDocumentResponse"
})
public class HCPCSDocumentData {
    @JsonProperty("deliveryTicketId")
    private Long deliveryTicketId;
    @JsonProperty("patientName")
    private String patientName;
    @JsonProperty("deliveryDocumentResponse")
    public List<DeliveryDocumentResponse> getDeliveryDocumentResponse() {
        return deliveryDocumentResponse;
    }
    @JsonProperty("deliveryDocumentResponse")
    public void setDeliveryDocumentResponse(List<DeliveryDocumentResponse> deliveryDocumentResponse) {
        this.deliveryDocumentResponse = deliveryDocumentResponse;
    }

    @JsonProperty("documentData")
    private List<DocumentData> documentData;
    @JsonProperty("deliveryDocumentResponse")
    private List<DeliveryDocumentResponse> deliveryDocumentResponse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("deliveryTicketId")
    public Long getDeliveryTicketId() {
        return deliveryTicketId;
    }

    @JsonProperty("deliveryTicketId")
    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    @JsonProperty("patientName")
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @JsonProperty("documentData")
    public List<DocumentData> getDocumentData() {
        return documentData;
    }

    @JsonProperty("documentData")
    public void setDocumentData(List<DocumentData> documentData) {
        this.documentData = documentData;
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
