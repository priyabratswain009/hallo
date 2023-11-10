package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderCombinedSearchInternalDTO {
    String branchName;
    String salesOrderUUID;
    String branchID;
    String patientIDNo;
    String patientFirstName;
    String patientMiddleName;
    String patientLastName;
    String salesOrderNo;
    String scheduleDeliveryFromDate;
    String scheduleDeliveryToDate;
    String deliveryActualDateStart;
    String deliveryActualDateEnd;
    String createdByID;
    String createdByName;
    String createdDateFromDate;
    String createdDateToDate;
    String itemNo;
    String itemName;
    String hCPCSCode;
    String primaryPayerId;
    String primaryPayerName;
    String dropshipStatus;
    String deliveryAddressLine1;
    String deliveryAddressLine2;
    String deliveryCity;
    String deliveryState;
    String deliveryZip;
    String salesOrderStatus;
}
