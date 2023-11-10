package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.PurchaseOrderAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PurchaseOrderIdAuditLogParameterDTO;

import java.util.List;

public interface PurchaseOrderAuditLogServiceExtended extends PurchaseOrderAuditLogService {
    List<AuditLogReportDTO> getPurchaseOrderAuditLog(PurchaseOrderIdAuditLogParameterDTO auditLogParameterDTO);
}
