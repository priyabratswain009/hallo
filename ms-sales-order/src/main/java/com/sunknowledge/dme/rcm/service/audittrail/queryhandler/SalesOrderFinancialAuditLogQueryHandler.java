package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderFinancialAuditLogBySONoAndUserIdOrDateQuery;

import java.util.List;

public interface SalesOrderFinancialAuditLogQueryHandler {
    List<SalesOrderFinancialDetailsAuditLogDTO> getSOFinancialAuditTrailInfoByQueryHandler(SalesOrderFinancialAuditLogBySONoAndUserIdOrDateQuery parameterObj);
}
