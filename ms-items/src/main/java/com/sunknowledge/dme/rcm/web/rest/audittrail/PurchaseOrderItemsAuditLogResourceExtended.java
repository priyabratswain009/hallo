package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderItemsAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
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
public class PurchaseOrderItemsAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("purchaseOrderItemsAuditLogServiceExtendedImpl")
    PurchaseOrderItemsAuditLogServiceExtended purchaseOrderItemsAuditLogServiceExtended;

    /**
     * Get PurchaseOrderItems Audit Trail .......
     */
    @GetMapping("getPurchaseOrderItemsAuditLog")
    public List<AuditLogReportDTO> getPurchaseOrderItemsAuditLog(
        @ModelAttribute ItemNoAuditLogParameterDTO auditLogParameterDTO
    ) {
        return purchaseOrderItemsAuditLogServiceExtended.getPurchaseOrderItemsAuditLog(auditLogParameterDTO);
    }
}
