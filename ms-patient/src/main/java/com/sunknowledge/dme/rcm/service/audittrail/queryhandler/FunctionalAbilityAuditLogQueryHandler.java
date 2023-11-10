package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface FunctionalAbilityAuditLogQueryHandler {
    Flux<FunctionalAbilityAuditLogDTO> getFunctionalAbilityAuditTrailInfoByQueryHandler(FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery queryObj);
}
