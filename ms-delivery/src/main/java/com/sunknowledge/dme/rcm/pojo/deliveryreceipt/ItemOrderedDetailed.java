
package com.sunknowledge.dme.rcm.pojo.deliveryreceipt;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderQty",
    "deleteQty",
    "type",
    "itemName",
    "chargedAmount",
    "allowedAmount",
    "tax",
    "coPay"
})
@Generated("jsonschema2pojo")
public class ItemOrderedDetailed {

    @JsonProperty("orderQty")
    private String orderQty;
    @JsonProperty("deleteQty")
    private String deleteQty;
    @JsonProperty("type")
    private String type;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("chargedAmount")
    private Double chargedAmount;
    @JsonProperty("allowedAmount")
    private Double allowedAmount;
    @JsonProperty("tax")
    private Double tax;
    @JsonProperty("coPay")
    private Double coPay;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orderQty")
    public String getOrderQty() {
        return orderQty;
    }

    @JsonProperty("orderQty")
    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }

    @JsonProperty("deleteQty")
    public String getDeleteQty() {
        return deleteQty;
    }

    @JsonProperty("deleteQty")
    public void setDeleteQty(String deleteQty) {
        this.deleteQty = deleteQty;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("itemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("chargedAmount")
    public Double getChargedAmount() {
        return chargedAmount;
    }

    @JsonProperty("chargedAmount")
    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    @JsonProperty("allowedAmount")
    public Double getAllowedAmount() {
        return allowedAmount;
    }

    @JsonProperty("allowedAmount")
    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    @JsonProperty("tax")
    public Double getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(Double tax) {
        this.tax = tax;
    }

    @JsonProperty("coPay")
    public Double getCoPay() {
        return coPay;
    }

    @JsonProperty("coPay")
    public void setCoPay(Double coPay) {
        this.coPay = coPay;
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
