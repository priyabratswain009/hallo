package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface PurchaseOrderAuditLogQueryHandler {
    List<PurchaseOrderAuditLogDTO> getPurchaseOrderAuditTrailInfoByQueryHandler(PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery queryObj);
}
