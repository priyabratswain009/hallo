package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsReceivedAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;

import java.util.List;

public interface PurchaseOrderItemsReceivedAuditLogServiceExtended extends PurchaseOrderItemsReceivedAuditLogService {
    List<AuditLogReportDTO> getPurchaseOrderItemsReceivedAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
