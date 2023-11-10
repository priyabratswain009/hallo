package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemSerialNumberAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface ItemSerialNumberAuditLogServiceExtended extends ItemSerialNumberAuditLogService {
    List<AuditLogReportDTO> getItemSerialNumberAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
