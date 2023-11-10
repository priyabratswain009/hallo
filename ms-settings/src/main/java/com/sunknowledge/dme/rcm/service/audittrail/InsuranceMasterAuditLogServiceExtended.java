package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.InsuranceMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.InsuranceMasterAuditLogParameterDTO;

import java.util.List;

public interface InsuranceMasterAuditLogServiceExtended extends InsuranceMasterAuditLogService {
    List<AuditLogReportDTO> getInsuranceMasterAuditLog(InsuranceMasterAuditLogParameterDTO insuranceMasterAuditLogParameterDTO);
}
