package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderCommonSearchParameterDTO {
    //    @NotNull(message = "Branch_UUID must be provided")
//    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
//        message = "Provide only valid Branch_UUID")
//    String branchUUID;
    String patientIDNo;
    String patientFirstName;
    String patientMiddleName;
    String patientLastName;
    String salesOrderNo;
    LocalDate scheduleDeliveryFromDate;
    LocalDate scheduleDeliveryToDate;
    Long createdByID;
    String createdByName;
    LocalDate createdDateFromDate;
    LocalDate createdDateToDate;
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
