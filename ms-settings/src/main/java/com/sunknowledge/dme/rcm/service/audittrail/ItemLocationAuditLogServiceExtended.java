package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemLocationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemLocationAuditLogParameterDTO;

import java.util.List;

public interface ItemLocationAuditLogServiceExtended extends ItemLocationAuditLogService {
    List<AuditLogReportDTO> getItemLocationAuditLog(ItemLocationAuditLogParameterDTO auditLogParameterDTO);
}
