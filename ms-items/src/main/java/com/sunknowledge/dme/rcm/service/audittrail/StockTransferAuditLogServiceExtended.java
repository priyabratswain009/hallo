package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.StockTransferAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface StockTransferAuditLogServiceExtended extends StockTransferAuditLogService {
    List<AuditLogReportDTO> getStockTransferAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
