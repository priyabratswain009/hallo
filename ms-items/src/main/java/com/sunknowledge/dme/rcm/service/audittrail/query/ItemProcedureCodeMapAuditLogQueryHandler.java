package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemProcedureCodeMapAuditLogQueryHandler {
    List<ItemProcedureCodeMapAuditLogDTO> getItemProcedureCodeMapAuditTrailInfoByQueryHandler(ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery queryObj);
}
