package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemMasterAuditLogByItemNoAndUserIdAndDateQuery;

import java.util.List;

public interface ItemMasterAuditLogQueryHandler {
    List<ItemMasterAuditLogDTO> getItemMasterAuditTrailInfoByQueryHandler(ItemMasterAuditLogByItemNoAndUserIdAndDateQuery queryObj);
}
