package com.sunknowledge.dme.rcm.service.dto.salesorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {
    private String deliveryAddressLine_1;
    private String deliveryAddressLine_2;
    private String deliveryCityName;
    private String deliveryStateName;
    private String deliveryZipCode;
    private String deliveryPhoneNo_1;
    private String deliveryPhoneNo_2;
    private String poRequestDocumentName;
}
