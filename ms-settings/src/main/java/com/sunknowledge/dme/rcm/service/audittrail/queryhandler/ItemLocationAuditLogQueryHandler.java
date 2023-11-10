package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemLocationAuditLogQueryHandler {
    List<ItemLocationAuditLogDTO> getItemLocationAuditTrailInfoByQueryHandler(ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery queryObj);
}
