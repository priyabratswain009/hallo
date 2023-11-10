package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.PriceMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PriceTableIdAuditLogParameterDTO;

import java.util.List;

public interface PriceMasterAuditLogServiceExtended extends PriceMasterAuditLogService {
    List<AuditLogReportDTO> getPriceMasterAuditLog(PriceTableIdAuditLogParameterDTO auditLogParameterDTO);
}
