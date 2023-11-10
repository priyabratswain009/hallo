package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.ItemSerialNumberAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.ItemTypeAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemTypeIdAuditLogParameterDTO;
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
public class ItemTypeAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemTypeAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("itemTypeAuditLogServiceExtendedImpl")
    ItemTypeAuditLogServiceExtended itemTypeAuditLogServiceExtended;

    /**
     * Get ItemType Audit Trail .......
     */
    @GetMapping("getItemTypeAuditLog")
    public List<AuditLogReportDTO> getItemTypeAuditLog(
        @ModelAttribute ItemTypeIdAuditLogParameterDTO auditLogParameterDTO
    ) {
        return itemTypeAuditLogServiceExtended.getItemTypeAuditLog(auditLogParameterDTO);
    }
}
