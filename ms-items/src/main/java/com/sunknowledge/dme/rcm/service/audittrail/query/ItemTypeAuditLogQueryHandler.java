package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemTypeAuditLogQueryHandler {
    List<ItemTypeAuditLogDTO> getItemTypeAuditTrailInfoByQueryHandler(ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery queryObj);
}
