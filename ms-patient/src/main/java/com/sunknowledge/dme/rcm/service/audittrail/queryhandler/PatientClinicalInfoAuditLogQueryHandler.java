package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientClinicalInfoAuditLogByPatientNoAndUserIdAndDateQuery;
import reactor.core.publisher.Flux;

public interface PatientClinicalInfoAuditLogQueryHandler {
    Flux<PatientClinicalInformationAuditLogDTO> getPatientClinicalInfoAuditTrailInfoByQueryHandler(PatientClinicalInfoAuditLogByPatientNoAndUserIdAndDateQuery queryObj);
}
