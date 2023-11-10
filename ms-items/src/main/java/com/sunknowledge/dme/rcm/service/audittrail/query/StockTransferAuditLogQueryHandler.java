package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.StockTransferAuditLogByItemNoAndUserIdAndDateQuery;

import java.util.List;

public interface StockTransferAuditLogQueryHandler {
    List<StockTransferAuditLogDTO> getStockTransferAuditTrailInfoByQueryHandler(StockTransferAuditLogByItemNoAndUserIdAndDateQuery queryObj);
}
