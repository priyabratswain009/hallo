package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery;

import java.util.List;

public interface SalesOrderDocumentsAuditLogQueryHandler {
    List<SalesOrderDocumentsAuditLogDTO> getSODocumentsAuditTrailInfoByQueryHandler(SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery parameterObj);
}
