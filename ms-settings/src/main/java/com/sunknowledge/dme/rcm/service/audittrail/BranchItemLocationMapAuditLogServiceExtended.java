package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.BranchItemLocationMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;

import java.util.List;

public interface BranchItemLocationMapAuditLogServiceExtended extends BranchItemLocationMapAuditLogService {
    List<AuditLogReportDTO> getBranchItemLocationMapAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO);
}
