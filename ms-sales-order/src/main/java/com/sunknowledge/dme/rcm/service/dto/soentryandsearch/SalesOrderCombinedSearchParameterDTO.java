package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderCombinedSearchParameterDTO {
    //    @NotNull(message = "SO_UUID must be provided")
//    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
//        message = "Provide only valid SO_UUID")
    //String branchID;
    String branchName;
    String salesOrderUUID;
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
