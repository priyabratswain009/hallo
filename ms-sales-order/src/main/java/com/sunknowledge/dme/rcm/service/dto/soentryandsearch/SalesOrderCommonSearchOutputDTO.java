package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderCommonSearchOutputDTO {
    String salesOrderNo;
    UUID salesOrderUuid;
    String patientIdNo;
    String patientName;
    String createdByName;
    LocalDate createdDate;
    LocalDate scheduleDeliveryDate;
    String deliveryAddressLine1;
    String deliveryAddressLine2;
    String deliveryCity;
    String deliveryState;
    String deliveryZip;
    String salesOrderStatus;

//    public static SalesOrderCommonSearchOutputDTO mapData(Row row) {
//        SalesOrderCommonSearchOutputDTO dto = new SalesOrderCommonSearchOutputDTO();
//        dto.setSalesOrderNo(String.valueOf(row.get("sales_order_no")));
//        dto.setSalesOrderUuid(String.valueOf(row.get("sales_order_uuid")));
//        dto.setPatientIdNo(String.valueOf(row.get("patient_id_no")));
//        dto.setPatientFirstName(String.valueOf(row.get("patient_first_name")));
//        dto.setPatientMiddleName(String.valueOf(row.get("patient_middle_name")));
//        dto.setPatientLastName(String.valueOf(row.get("patient_last_name")));
//        dto.setCreatedById(Long.valueOf(String.valueOf(row.get("created_by_id"))));
//        dto.setCreatedByName(String.valueOf(row.get("created_by_name")));
//        dto.setCreatedDate(LocalDate.parse(String.valueOf(row.get("created_date"))));
//        dto.setScheduleDeliveryDate(LocalDate.parse(String.valueOf(row.get("schedule_delivery_date"))));
//        dto.setDeliveryAddressLine1(String.valueOf(row.get("delivery_address_line_1")));
//        dto.setDeliveryAddressLine2(String.valueOf(row.get("delivery_address_line_2")));
//        dto.setDeliveryCity(String.valueOf(row.get("delivery_city")));
//        dto.setDeliveryState(String.valueOf(row.get("delivery_state")));
//        dto.setDeliveryZip(String.valueOf(row.get("delivery_zip")));
//        dto.setSalesOrderStatus(String.valueOf(row.get("sales_order_status")));
//        System.out.println("Output DTO=" + dto);
//        return dto;
//    }
}
