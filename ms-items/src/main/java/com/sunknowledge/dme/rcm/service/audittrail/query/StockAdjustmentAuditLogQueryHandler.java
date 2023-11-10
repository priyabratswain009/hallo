package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery;

import java.util.List;

public interface StockAdjustmentAuditLogQueryHandler {
    List<StockAdjustmentAuditLogDTO> getStockAdjustmentAuditTrailInfoByQueryHandler(StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery queryObj);
}
