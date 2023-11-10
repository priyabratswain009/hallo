package com.sunknowledge.dme.rcm.service.audittrail.query;

import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery;

import java.util.List;

public interface ManufacturerAuditLogQueryHandler {
    List<ManufacturerAuditLogDTO> getManufacturerAuditTrailInfoByQueryHandler(ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery queryObj);
}
