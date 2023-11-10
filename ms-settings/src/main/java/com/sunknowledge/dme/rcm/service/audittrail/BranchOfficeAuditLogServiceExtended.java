package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.BranchOfficeAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;

import java.util.List;

public interface BranchOfficeAuditLogServiceExtended extends BranchOfficeAuditLogService {
    List<AuditLogReportDTO> getBranchOfficeAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO);

    List<AuditLogReportDTO> getOverallBranchAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO);
}
