package com.sunknowledge.dme.rcm.service.dto.audittrail.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery {
    private Long soID;
    private List<Long> soItemID;
    private String soNo;
    private Long userID;
    private LocalDate fromDate;
    private LocalDate toDate;
}
