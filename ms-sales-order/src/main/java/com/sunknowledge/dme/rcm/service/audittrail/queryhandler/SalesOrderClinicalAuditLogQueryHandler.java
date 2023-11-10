package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderClinicalAuditLogBySONoAndUserIdOrDateQuery;

import java.util.List;

public interface SalesOrderClinicalAuditLogQueryHandler {
    List<SalesOrderClinicalDetailsAuditLogDTO> getSOClinicalAuditTrailInfoByQueryHandler(SalesOrderClinicalAuditLogBySONoAndUserIdOrDateQuery parameterObj);
}
