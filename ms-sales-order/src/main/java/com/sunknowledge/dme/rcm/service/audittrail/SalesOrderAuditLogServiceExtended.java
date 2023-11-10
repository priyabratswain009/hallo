package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import reactor.core.publisher.Flux;

public interface SalesOrderAuditLogServiceExtended extends SalesOrderMasterAuditLogService {

    Flux<AuditLogReportDTO> getSOAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO);

    Flux<AuditLogReportDTO> getSOOverallAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO);

    Flux<UserActivityReportDTO> getSOUserActivityReportForAuditLog(UserActivityParameterDTO userActivityParameterDTO);
}
