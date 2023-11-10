package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemInventoryStatusAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface ItemInventoryStatusAuditLogServiceExtended extends ItemInventoryStatusAuditLogService {
    List<AuditLogReportDTO> getItemInventoryStatusAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
