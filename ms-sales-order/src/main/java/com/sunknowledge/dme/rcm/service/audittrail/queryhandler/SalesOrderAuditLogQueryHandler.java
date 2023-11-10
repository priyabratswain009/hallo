package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.SalesOrderOverallAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderAuditLogBySONoAndUserIdOrDateQuery;

import java.util.List;

public interface SalesOrderAuditLogQueryHandler {

    List<SalesOrderMasterAuditLogDTO> getSOAuditTrailInfoByQueryHandler(SalesOrderAuditLogBySONoAndUserIdOrDateQuery parameterObj);

    List<SalesOrderOverallAuditLogDTO> getSOOverallAuditTrailInfoByQueryHandler(SalesOrderAuditLogBySONoAndUserIdOrDateQuery queryObj);
}
