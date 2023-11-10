package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import reactor.core.publisher.Flux;

public interface SalesOrderInsuranceAuditLogServiceExtended extends SalesOrderInsuranceDetailsAuditLogService {
    Flux<AuditLogReportDTO> getSOInsuranceAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO);
}
