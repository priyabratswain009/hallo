package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery;

import java.util.List;

public interface PriceDetailsAuditLogQueryHandler {
    List<PriceDetailsAuditLogDTO> getPriceDetailsAuditTrailInfoByQueryHandler(PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery queryObj);
}
