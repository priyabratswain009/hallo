package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.WorkersCompensationAuditTrailServiceExtended;
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
public class WorkersCompensationAuditTrailResourceExtended {
    private final Logger log = LoggerFactory.getLogger(WorkersCompensationAuditTrailResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("workersCompensationAuditTrailServiceExtendedImpl")
    WorkersCompensationAuditTrailServiceExtended workersCompensationAuditTrailServiceExtended;

    /**
     * Get Patient Audit Trail .......
     */
    @GetMapping("getWorkersCompensationAuditLog")
    public Flux<AuditLogReportDTO> getWorkersCompensationAuditLog(
        @ModelAttribute AuditLogParameterDTO auditLogParameterDTO
    ) {
        return workersCompensationAuditTrailServiceExtended.getPatientWorkersCompensationAuditLog(auditLogParameterDTO);
    }
}
