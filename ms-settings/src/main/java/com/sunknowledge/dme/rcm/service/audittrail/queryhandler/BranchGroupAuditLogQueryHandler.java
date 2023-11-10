package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery;

import java.util.List;

public interface BranchGroupAuditLogQueryHandler {
    List<BranchGroupAuditLogDTO> getBranchGroupAuditTrailInfoByQueryHandler(BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery queryObj);
}
