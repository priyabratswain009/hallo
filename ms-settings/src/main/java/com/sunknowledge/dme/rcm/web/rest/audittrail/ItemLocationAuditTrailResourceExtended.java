package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.BranchOfficeAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.ItemLocationAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemLocationAuditLogParameterDTO;
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
public class ItemLocationAuditTrailResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemLocationAuditTrailResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("itemLocationAuditLogServiceExtendedImpl")
    ItemLocationAuditLogServiceExtended itemLocationAuditLogServiceExtended;

    /**
     * Get ItemLocation Audit Trail .......
     */
    @GetMapping("getItemLocationAuditLog")
    public List<AuditLogReportDTO> getItemLocationAuditLog(
        @ModelAttribute ItemLocationAuditLogParameterDTO auditLogParameterDTO
    ) {
        return itemLocationAuditLogServiceExtended.getItemLocationAuditLog(auditLogParameterDTO);
    }
}
