package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.PatientClinicalInformationAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.PatientDiagnosisAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
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

@RestController
@RequestMapping("/api")
public class PatientClinicalInformationAuditTrailResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientClinicalInformationAuditTrailResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientClinicalInformationAuditTrailServiceExtendedImpl")
    PatientClinicalInformationAuditTrailServiceExtended patientClinicalInformationAuditTrailServiceExtended;

    /**
     * Get Patient Audit Trail .......
     */
    @GetMapping("getPatientClinicalInformationAuditLog")
    public Flux<AuditLogReportDTO> getPatientClinicalInformationAuditLog(
        @ModelAttribute AuditLogParameterDTO auditLogParameterDTO
    ) {
        return patientClinicalInformationAuditTrailServiceExtended.getPatientClinicalInformationAuditLog(auditLogParameterDTO);
    }
}
