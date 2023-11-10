package com.sunknowledge.dme.rcm.service.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import reactor.core.publisher.Flux;

public interface PatientClinicalInformationAuditTrailServiceExtended {
    Flux<AuditLogReportDTO> getPatientClinicalInformationAuditLog(AuditLogParameterDTO auditLogParameterDTO);
}