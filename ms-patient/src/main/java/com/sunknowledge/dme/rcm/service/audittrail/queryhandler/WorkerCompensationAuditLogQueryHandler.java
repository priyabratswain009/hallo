package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface WorkerCompensationAuditLogQueryHandler {
    Flux<WorkersCompensationAuditLogDTO> getWorkerCompensationAuditTrailInfoByQueryHandler(WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
