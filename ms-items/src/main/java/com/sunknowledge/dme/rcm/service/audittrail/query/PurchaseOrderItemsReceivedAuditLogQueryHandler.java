package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery;

import java.util.List;

public interface PurchaseOrderItemsReceivedAuditLogQueryHandler {
    List<PurchaseOrderItemsReceivedAuditLogDTO> getPurchaseOrderItemsReceivedAuditTrailInfoByQueryHandler(PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery queryObj);
}
