package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.PriceDetailsAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.PriceMasterAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PriceTableIdAuditLogParameterDTO;
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
public class PriceMasterAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PriceMasterAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("priceMasterAuditLogServiceExtendedImpl")
    PriceMasterAuditLogServiceExtended priceMasterAuditLogServiceExtended;

    /**
     * Get PriceMaster Audit Trail .......
     */
    @GetMapping("getPriceMasterAuditLog")
    public List<AuditLogReportDTO> getPriceMasterAuditLog(
        @ModelAttribute PriceTableIdAuditLogParameterDTO auditLogParameterDTO
    ) {
        return priceMasterAuditLogServiceExtended.getPriceMasterAuditLog(auditLogParameterDTO);
    }
}
