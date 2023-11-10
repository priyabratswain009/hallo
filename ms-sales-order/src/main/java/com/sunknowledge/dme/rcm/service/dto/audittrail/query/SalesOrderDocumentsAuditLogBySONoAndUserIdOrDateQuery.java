package com.sunknowledge.dme.rcm.service.dto.audittrail.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery {
    private Long soID;
    private List<Long> soDocumentsID;
    private String soNo;
    private Long userID;
    private LocalDate fromDate;
    private LocalDate toDate;
}
