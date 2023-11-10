package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.ItemGroupAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.ItemInventoryStatusAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemGroupIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
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
public class ItemGroupAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemGroupAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("itemGroupAuditLogServiceExtendedImpl")
    ItemGroupAuditLogServiceExtended itemGroupAuditLogServiceExtended;

    /**
     * Get ItemGroup Audit Trail .......
     */
    @GetMapping("getItemGroupAuditLog")
    public List<AuditLogReportDTO> getItemGroupAuditLog(
        @ModelAttribute ItemGroupIdAuditLogParameterDTO auditLogParameterDTO
    ) {
        return itemGroupAuditLogServiceExtended.getItemGroupAuditLog(auditLogParameterDTO);
    }
}
