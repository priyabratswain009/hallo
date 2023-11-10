package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.BranchGroupAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchGroupAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;

import java.util.List;

public interface BranchGroupAuditLogServiceExtended extends BranchGroupAuditLogService {
    List<AuditLogReportDTO> getBranchGroupAuditLog(BranchGroupAuditLogParameterDTO auditLogParameterDTO);
}
