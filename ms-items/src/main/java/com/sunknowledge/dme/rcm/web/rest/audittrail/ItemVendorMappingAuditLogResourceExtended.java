package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.ItemTypeAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.ItemVendorMappingAuditLogServiceExtended;
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
public class ItemVendorMappingAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("itemVendorMappingAuditLogServiceExtendedImpl")
    ItemVendorMappingAuditLogServiceExtended itemVendorMappingAuditLogServiceExtended;

    /**
     * Get ItemVendorMapping Audit Trail .......
     */
    @GetMapping("getItemVendorMappingAuditLog")
    public List<AuditLogReportDTO> getItemVendorMappingAuditLog(
        @ModelAttribute ItemNoAuditLogParameterDTO auditLogParameterDTO
    ) {
        return itemVendorMappingAuditLogServiceExtended.getItemVendorMappingAuditLog(auditLogParameterDTO);
    }
}
