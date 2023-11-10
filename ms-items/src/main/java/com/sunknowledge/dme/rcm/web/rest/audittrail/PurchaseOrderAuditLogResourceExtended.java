package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.PriceMasterAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PriceTableIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PurchaseOrderIdAuditLogParameterDTO;
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
public class PurchaseOrderAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PurchaseOrderAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("purchaseOrderAuditLogServiceExtendedImpl")
    PurchaseOrderAuditLogServiceExtended purchaseOrderAuditLogServiceExtended;

    /**
     * Get PurchaseOrder Audit Trail .......
     */
    @GetMapping("getPurchaseOrderAuditLog")
    public List<AuditLogReportDTO> getPurchaseOrderAuditLog(
        @ModelAttribute PurchaseOrderIdAuditLogParameterDTO auditLogParameterDTO
    ) {
        return purchaseOrderAuditLogServiceExtended.getPurchaseOrderAuditLog(auditLogParameterDTO);
    }
}
