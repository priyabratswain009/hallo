package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery;

import java.util.List;

public interface PriceMasterAuditLogQueryHandler {
    List<PriceMasterAuditLogDTO> getPriceMasterAuditTrailInfoByQueryHandler(PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery queryObj);
}
