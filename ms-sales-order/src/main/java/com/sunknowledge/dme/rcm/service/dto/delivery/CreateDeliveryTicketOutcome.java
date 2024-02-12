package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliveryTicketOutcome {
    private Long deliveryticketid;
    private String deliveryticketno;
    private Long salesorderid;
    private String salesorderno;
    private Long inventorylocationid;
    private String deliverytypee;
    private UUID deliveryticketuuid;
}
