package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemVendorMappingAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemTypeIdAuditLogParameterDTO;

import java.util.List;

public interface ItemVendorMappingAuditLogServiceExtended extends ItemVendorMappingAuditLogService {
    List<AuditLogReportDTO> getItemVendorMappingAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
