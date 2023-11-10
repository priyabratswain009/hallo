package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.BranchInsuranceMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;

import java.util.List;

public interface BranchInsuranceMapAuditLogServiceExtended extends BranchInsuranceMapAuditLogService {
    List<AuditLogReportDTO> getBranchInsuranceMapAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO);
}
