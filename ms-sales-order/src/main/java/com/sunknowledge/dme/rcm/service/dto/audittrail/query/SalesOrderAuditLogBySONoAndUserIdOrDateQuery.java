package com.sunknowledge.dme.rcm.service.dto.audittrail.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderAuditLogBySONoAndUserIdOrDateQuery {
    private Long soID;
    private String soNo;
    private Long userID;
    private LocalDate fromDate;
    private LocalDate toDate;
}
