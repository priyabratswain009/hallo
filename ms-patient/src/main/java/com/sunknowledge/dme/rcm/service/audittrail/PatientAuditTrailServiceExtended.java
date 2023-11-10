package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface PatientAuditTrailServiceExtended {
    Flux<AuditLogReportDTO> getPatientAuditLog(AuditLogParameterDTO auditLogParameterDTO);

    Flux<AuditLogReportDTO> getPatientOverallAuditLog(AuditLogParameterDTO auditLogParameterDTO);

    Flux<UserActivityReportDTO> getPatientUserActivityReport(UserActivityParameterDTO userActivityParameterDTO);
}
