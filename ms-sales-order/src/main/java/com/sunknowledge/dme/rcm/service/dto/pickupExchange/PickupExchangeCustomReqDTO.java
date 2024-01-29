package com.sunknowledge.dme.rcm.service.dto.pickupExchange;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickupExchangeCustomReqDTO{

    private String pickupExchangeType;

    private String replacementItemSerialNo;

    private String replacementItemAssetNo;

    private Long soId;

    private Long itemid;

    private String soNo;

    private LocalDate pickupExchangeScheduleDateTime;

    private String pickupExchangeSupportingDocument1;

    private String pickupExchangeSupportingDocument2;

    private String pickupExchangeReason;

}
