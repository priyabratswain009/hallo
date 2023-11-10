package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderInsuranceAuditLogBySONoAndUserIdOrDateQuery;

import java.util.List;

public interface SalesOrderInsuranceAuditLogQueryHandler {

    List<SalesOrderInsuranceDetailsAuditLogDTO> getSOInsuranceAuditTrailInfoByQueryHandler(SalesOrderInsuranceAuditLogBySONoAndUserIdOrDateQuery parameterObj);
}
