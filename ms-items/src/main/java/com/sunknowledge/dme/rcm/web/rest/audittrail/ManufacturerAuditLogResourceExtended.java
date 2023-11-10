package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.ManufacturerAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ManufacturerNoAuditLogParameterDTO;
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
public class ManufacturerAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ManufacturerAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("manufacturerAuditLogServiceExtendedImpl")
    ManufacturerAuditLogServiceExtended manufacturerAuditLogServiceExtended;

    /**
     * Get Manufacturer Audit Trail .......
     */
    @GetMapping("getManufacturerAuditLog")
    public List<AuditLogReportDTO> getManufacturerAuditLog(
        @ModelAttribute ManufacturerNoAuditLogParameterDTO auditLogParameterDTO
    ) {
        return manufacturerAuditLogServiceExtended.getManufacturerAuditLog(auditLogParameterDTO);
    }
}
