package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.UserActivityReportByFromDateAndToDateQuery;
import reactor.core.publisher.Flux;

public interface PatientAuditLogQueryHandler {
    Flux<PatientMasterAuditLogDTO> getPatientAuditTrailInfoByQueryHandler(PatientAuditLogByPatientNoAndUserIdAndDateQuery queryObj);

    Flux<UserActivityReportDTO> getPatientUserActivityReportByQueryHandler(UserActivityReportByFromDateAndToDateQuery query);
}
