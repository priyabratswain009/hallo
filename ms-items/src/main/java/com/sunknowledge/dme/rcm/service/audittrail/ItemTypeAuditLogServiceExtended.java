package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemTypeAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemTypeIdAuditLogParameterDTO;

import java.util.List;

public interface ItemTypeAuditLogServiceExtended extends ItemTypeAuditLogService {
    List<AuditLogReportDTO> getItemTypeAuditLog(ItemTypeIdAuditLogParameterDTO auditLogParameterDTO);
}
