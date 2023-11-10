package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery;

import java.util.List;

public interface BranchInsuranceMapAuditLogQueryHandler {
    List<BranchInsuranceMapAuditLogDTO> getBranchInsuranceMapAuditTrailInfoByQueryHandler(BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery queryObj);
}
