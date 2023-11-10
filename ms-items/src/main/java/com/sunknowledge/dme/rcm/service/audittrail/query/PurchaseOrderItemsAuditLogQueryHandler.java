package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery;

import java.util.List;

public interface PurchaseOrderItemsAuditLogQueryHandler {
    List<PurchaseOrderItemsAuditLogDTO> getPurchaseOrderItemsAuditTrailInfoByQueryHandler(PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery queryObj);
}
