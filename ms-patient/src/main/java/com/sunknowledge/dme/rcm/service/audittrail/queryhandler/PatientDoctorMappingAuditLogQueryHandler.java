package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface PatientDoctorMappingAuditLogQueryHandler {
    Flux<PatientDoctorMapAuditLogDTO> getPatientDoctorMappingAuditTrailInfoByQueryHandler(PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
