package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface PurchaseOrderItemsAuditLogServiceExtended extends PurchaseOrderItemsAuditLogService {
    List<AuditLogReportDTO> getPurchaseOrderItemsAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
