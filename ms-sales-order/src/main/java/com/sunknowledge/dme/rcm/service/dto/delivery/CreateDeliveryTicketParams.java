package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliveryTicketParams {
    private Long salesOrderId;
    private String deliveryType;
    private String setupMethod;
    private Long userId;
    private String userName;
}
