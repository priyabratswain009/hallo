package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery;

import java.util.List;

public interface InsuranceMasterAuditLogQueryHandler {
    List<InsuranceMasterAuditLogDTO> getInsuranceMasterAuditTrailInfoByQueryHandler(InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery queryObj);
}
