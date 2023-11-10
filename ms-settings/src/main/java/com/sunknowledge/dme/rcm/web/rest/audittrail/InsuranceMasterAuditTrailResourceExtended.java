package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.InsuranceMasterAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.audittrail.InsuranceMasterAuditLogServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceMasterAuditTrailResourceExtended {
    private final Logger log = LoggerFactory.getLogger(InsuranceMasterAuditTrailResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("insuranceMasterAuditLogServiceExtendedImpl")
    InsuranceMasterAuditLogServiceExtended insuranceMasterAuditLogServiceExtended;

    /**
     * Get InsuranceMaster Audit Trail .......
     */
    @GetMapping("getInsuranceMasterAuditLog")
    public List<AuditLogReportDTO> getInsuranceMasterAuditLog(
        @ModelAttribute InsuranceMasterAuditLogParameterDTO insuranceMasterAuditLogParameterDTO
    ) {
        return insuranceMasterAuditLogServiceExtended.getInsuranceMasterAuditLog(insuranceMasterAuditLogParameterDTO);
    }
}
