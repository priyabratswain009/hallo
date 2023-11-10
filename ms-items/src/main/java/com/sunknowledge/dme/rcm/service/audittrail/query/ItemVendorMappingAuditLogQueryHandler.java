package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery;

import java.util.List;

public interface ItemVendorMappingAuditLogQueryHandler {
    List<ItemVendorMappingAuditLogDTO> getItemVendorMappingAuditTrailInfoByQueryHandler(ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery queryObj);
}
