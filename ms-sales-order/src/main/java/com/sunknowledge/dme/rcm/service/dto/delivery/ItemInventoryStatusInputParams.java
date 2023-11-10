package com.sunknowledge.dme.rcm.service.dto.delivery;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deliveryTicketId",
    "deliveryTicketNo",
    "soId",
    "soNumber",
    "itemLocationId",
    "deliveryType",
    "serviceType",
    "deliveryItemData"
})
public class ItemInventoryStatusInputParams {
    @JsonProperty("deliveryTicketId")
    private Long deliveryTicketId;
    @JsonProperty("deliveryTicketNo")
    private String deliveryTicketNo;
    @JsonProperty("soId")
    private Long soId;
    @JsonProperty("soNumber")
    private String soNumber;
    @JsonProperty("itemLocationId")
    private Long itemLocationId;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("serviceType")
    private String serviceType;
    @JsonProperty("deliveryTicketUuid")
    private String deliveryTicketUuid;
    @JsonProperty("deliveryItemData")
    private List<DeliveryItemData> deliveryItemData;
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

    @JsonProperty("deliveryTicketNo")
    public String getDeliveryTicketNo() {
        return deliveryTicketNo;
    }

    @JsonProperty("deliveryTicketNo")
    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    @JsonProperty("soId")
    public Long getSoId() {
        return soId;
    }

    @JsonProperty("soId")
    public void setSoId(Long soId) {
        this.soId = soId;
    }

    @JsonProperty("soNumber")
    public String getSoNumber() {
        return soNumber;
    }

    @JsonProperty("soNumber")
    public void setSoNumber(String soNumber) {
        this.soNumber = soNumber;
    }

    @JsonProperty("itemLocationId")
    public Long getItemLocationId() {
        return itemLocationId;
    }

    @JsonProperty("itemLocationId")
    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("serviceType")
    public String getServiceType() {
        return serviceType;
    }

    @JsonProperty("serviceType")
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @JsonProperty("deliveryTicketUuid")
    public String getDeliveryTicketUuid() {
        return deliveryTicketUuid;
    }

    @JsonProperty("deliveryTicketUuid")
    public void setDeliveryTicketUuid(String deliveryTicketUuid) {
        this.deliveryTicketUuid = deliveryTicketUuid;
    }

    @JsonProperty("deliveryItemData")
    public List<DeliveryItemData> getDeliveryItemData() {
        return deliveryItemData;
    }

    @JsonProperty("deliveryItemData")
    public void setDeliveryItemData(List<DeliveryItemData> deliveryItemData) {
        this.deliveryItemData = deliveryItemData;
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
