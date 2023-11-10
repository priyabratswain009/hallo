package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface ItemMasterAuditLogServiceExtended extends ItemMasterAuditLogService {
    List<AuditLogReportDTO> getItemMasterAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);

    List<AuditLogReportDTO> getOverallItemAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
