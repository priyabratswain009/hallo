package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.PriceDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface PriceDetailsAuditLogServiceExtended extends PriceDetailsAuditLogService {
    List<AuditLogReportDTO> getPriceDetailsAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
