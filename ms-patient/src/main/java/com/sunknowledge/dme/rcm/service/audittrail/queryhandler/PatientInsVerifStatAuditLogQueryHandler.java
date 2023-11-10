package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface PatientInsVerifStatAuditLogQueryHandler {
    Flux<PatientInsVerifStatAuditLogDTO> getPatientInsVerifStatAuditTrailInfoByQueryHandler(PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
