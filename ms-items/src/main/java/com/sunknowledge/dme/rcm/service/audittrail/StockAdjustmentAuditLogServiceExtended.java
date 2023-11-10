package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.StockAdjustmentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface StockAdjustmentAuditLogServiceExtended extends StockAdjustmentAuditLogService {
    List<AuditLogReportDTO> getStockAdjustmentAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
