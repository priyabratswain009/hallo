package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemGroupAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemGroupIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface ItemGroupAuditLogServiceExtended extends ItemGroupAuditLogService {
    List<AuditLogReportDTO> getItemGroupAuditLog(ItemGroupIdAuditLogParameterDTO auditLogParameterDTO);
}
