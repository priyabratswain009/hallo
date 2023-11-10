package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderItemCmnSubroutineDTO {
    private Long patientId;
    private String itemNo;
    private String dos;
    private String orderingProviderNpi;
    private Long salesOrderId;
    private Long soItemDetailsId;
    private String salesOrderNo;
}
