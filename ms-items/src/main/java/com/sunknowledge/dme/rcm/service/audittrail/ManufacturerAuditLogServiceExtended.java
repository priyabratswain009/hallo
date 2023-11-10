package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ManufacturerAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ManufacturerNoAuditLogParameterDTO;

import java.util.List;

public interface ManufacturerAuditLogServiceExtended extends ManufacturerAuditLogService {
    List<AuditLogReportDTO> getManufacturerAuditLog(ManufacturerNoAuditLogParameterDTO auditLogParameterDTO);
}
