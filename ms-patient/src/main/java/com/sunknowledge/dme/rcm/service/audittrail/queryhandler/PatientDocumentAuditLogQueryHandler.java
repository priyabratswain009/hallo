package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDocumentAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface PatientDocumentAuditLogQueryHandler {
    Flux<PatientDocumentAuditLogDTO> getPatientDocumentAuditTrailInfoByQueryHandler(PatientDocumentAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
