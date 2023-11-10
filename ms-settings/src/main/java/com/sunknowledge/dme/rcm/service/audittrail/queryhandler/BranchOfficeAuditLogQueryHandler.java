package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery;

import java.util.List;

public interface BranchOfficeAuditLogQueryHandler {
    List<BranchOfficeAuditLogDTO> getBranchOfficeAuditTrailInfoByQueryHandler(BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery queryObj);
}
