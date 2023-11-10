package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemGroupAuditLogQueryHandler {
    List<ItemGroupAuditLogDTO> getItemGroupAuditTrailInfoByQueryHandler(ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery queryObj);
}
