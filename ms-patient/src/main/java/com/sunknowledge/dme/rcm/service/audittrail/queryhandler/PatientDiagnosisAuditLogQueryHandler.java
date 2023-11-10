package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface PatientDiagnosisAuditLogQueryHandler {
    Flux<PatientDiagnosisAuditLogDTO> getPatientDiagnosisAuditTrailInfoByQueryHandler(PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
