package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery;
import java.util.List;

public interface SalesOrderItemAuditLogQueryHandler {

    List<SalesOrderItemDetailsAuditLogDTO> getSOItemAuditTrailInfoByQueryHandler(SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery parameterObj);
}
