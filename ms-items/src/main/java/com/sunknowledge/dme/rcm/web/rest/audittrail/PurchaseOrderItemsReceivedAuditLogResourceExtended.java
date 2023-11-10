package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderItemsAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderItemsReceivedAuditLogServiceExtended;
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
public class PurchaseOrderItemsReceivedAuditLogResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsReceivedAuditLogResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("purchaseOrderItemsReceivedAuditLogServiceExtendedImpl")
    PurchaseOrderItemsReceivedAuditLogServiceExtended purchaseOrderItemsReceivedAuditLogServiceExtended;

    /**
     * Get PurchaseOrderItemsReceived Audit Trail .......
     */
    @GetMapping("getPurchaseOrderItemsReceivedAuditLog")
    public List<AuditLogReportDTO> getPurchaseOrderItemsReceivedAuditLog(
        @ModelAttribute ItemNoAuditLogParameterDTO auditLogParameterDTO
    ) {
        return purchaseOrderItemsReceivedAuditLogServiceExtended.getPurchaseOrderItemsReceivedAuditLog(auditLogParameterDTO);
    }
}
