package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderItemAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderItemAuditLogResourceExtended {
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderItemAuditLogServiceExtended salesOrderItemAuditLogServiceExtended;

    @GetMapping("/getSOItemAuditLog")
    public Flux<AuditLogReportDTO> getSOItemAuditLog(@ModelAttribute AuditLogParameterDTO auditLogParameterDTO){
        return salesOrderItemAuditLogServiceExtended.getSOItemAuditTrailInfo(auditLogParameterDTO);
    }
}
