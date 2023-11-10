package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierTypeDeliveryInput {
    private Long deliveryTicketId;
    private String deliverType;
    private String carrierName;
    private String shippingDate;
    private String trackingNo;
    private String referenceNo;
    private String packageWeight;
}
