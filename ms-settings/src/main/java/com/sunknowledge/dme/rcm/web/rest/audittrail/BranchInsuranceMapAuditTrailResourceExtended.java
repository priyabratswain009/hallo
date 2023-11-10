package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.BranchInsuranceMapAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;
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
public class BranchInsuranceMapAuditTrailResourceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchInsuranceMapAuditTrailResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("branchInsuranceMapAuditLogServiceExtendedImpl")
    BranchInsuranceMapAuditLogServiceExtended branchInsuranceMapAuditLogServiceExtended;

    /**
     * Get BranchInsuranceMap Audit Trail .......
     */
    @GetMapping("getBranchInsuranceMapAuditLog")
    public List<AuditLogReportDTO> getBranchInsuranceMapAuditLog(
        @ModelAttribute BranchNoAuditLogParameterDTO auditLogParameterDTO
    ) {
        return branchInsuranceMapAuditLogServiceExtended.getBranchInsuranceMapAuditLog(auditLogParameterDTO);
    }
}
