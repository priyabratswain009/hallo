package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.FunctionalAbilityAuditLogParameterDTO;
import reactor.core.publisher.Flux;

public interface FunctionalAbilityAuditTrailServiceExtended {
    Flux<AuditLogReportDTO> getFunctionalAbilityAuditLog(FunctionalAbilityAuditLogParameterDTO auditLogParameterDTO);
}
