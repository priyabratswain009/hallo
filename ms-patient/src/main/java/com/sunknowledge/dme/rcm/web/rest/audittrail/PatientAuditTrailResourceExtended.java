package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.audittrail.PatientAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * REST controller for PatientAuditTrailResourceExtended.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientAuditTrailResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientAuditTrailResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientAuditTrailServiceExtendedImpl")
    PatientAuditTrailServiceExtended patientAuditTrailServiceExtended;

    /**
     * Get Patient Audit Trail .......
     */
    @GetMapping("getPatientAuditLog")
    public Flux<AuditLogReportDTO> getPatientAuditLog(
        @ModelAttribute AuditLogParameterDTO auditLogParameterDTO
    ) {
        return patientAuditTrailServiceExtended.getPatientAuditLog(auditLogParameterDTO);
    }

    @GetMapping("getPatientOverallAuditLog")
    public Flux<AuditLogReportDTO> getPatientOverallAuditLog(
        @ModelAttribute AuditLogParameterDTO auditLogParameterDTO
    ) {
        return patientAuditTrailServiceExtended.getPatientOverallAuditLog(auditLogParameterDTO);
    }

    @GetMapping("getPatientUserActivityReport")
    public Flux<UserActivityReportDTO> getPatientUserActivityReport(
        @ModelAttribute UserActivityParameterDTO userActivityParameterDTO
    ) {
        return patientAuditTrailServiceExtended.getPatientUserActivityReport(userActivityParameterDTO);
    }

}
