package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery;

import java.util.List;

public interface BranchItemLocationMapAuditLogQueryHandler {
    List<BranchItemLocationMapAuditLogDTO> getBranchItemLocationMapAuditTrailInfoByQueryHandler(BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery queryObj);
}
