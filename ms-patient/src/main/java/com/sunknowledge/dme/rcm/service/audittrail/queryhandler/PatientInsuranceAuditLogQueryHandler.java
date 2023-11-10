package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface PatientInsuranceAuditLogQueryHandler {
    Flux<PatientInsuranceAuditLogDTO> getPatientInsuranceAuditTrailInfoByQueryHandler(PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
