package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.ItemProcedureCodeMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemProcedureCodeMapAuditLogServiceExtended extends ItemProcedureCodeMapAuditLogService {
    List<AuditLogReportDTO> getItemProcedureCodeMapAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO);
}
