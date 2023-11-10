package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery;

import java.util.List;

public interface ItemInventoryStatusAuditLogQueryHandler {
    List<ItemInventoryStatusAuditLogDTO> getItemInventoryStatusAuditTrailInfoByQueryHandler(ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery queryObj);
}
