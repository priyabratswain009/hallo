package com.sunknowledge.dme.rcm.pojo.salesorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderCommonSearchOutputDTO {
    private String salesOrderNo;
    private UUID salesOrderUuid;
    private String patientIdNo;
    private String patientName;
    private String createdByName;
    private LocalDate createdDate;
    private LocalDate scheduleDeliveryDate;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String salesOrderStatus;
}
