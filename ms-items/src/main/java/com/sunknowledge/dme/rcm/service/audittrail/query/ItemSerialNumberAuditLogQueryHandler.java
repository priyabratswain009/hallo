package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemSerialNumberAuditLogQueryHandler {
    List<ItemSerialNumberAuditLogDTO> getItemSerialNumberAuditTrailInfoByQueryHandler(ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery queryObj);
}
