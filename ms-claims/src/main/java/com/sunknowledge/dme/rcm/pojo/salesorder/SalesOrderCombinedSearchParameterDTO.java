package com.sunknowledge.dme.rcm.pojo.salesorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderCombinedSearchParameterDTO {
    private String branchID;
    private String branchName;
    private String salesOrderUUID;
    private String patientIDNo;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String salesOrderNo;
    private String scheduleDeliveryFromDate;
    private String scheduleDeliveryToDate;
    private String deliveryActualDateStart;
    private String deliveryActualDateEnd;
    private String createdByID;
    private String createdByName;
    private String createdDateFromDate;
    private String createdDateToDate;
    private String itemNo;
    private String itemName;
    private String hCPCSCode;
    private String primaryPayerId;
    private String primaryPayerName;
    private String dropshipStatus;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String salesOrderStatus;
}
