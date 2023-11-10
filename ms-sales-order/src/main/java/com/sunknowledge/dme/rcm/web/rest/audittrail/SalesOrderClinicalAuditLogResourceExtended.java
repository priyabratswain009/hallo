package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderClinicalAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderFinancialAuditLogServiceExtended;
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
public class SalesOrderClinicalAuditLogResourceExtended {
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderClinicalAuditLogServiceExtended salesOrderClinicalAuditLogServiceExtended;

    @GetMapping("/getSOClinicalAuditLog")
    public Flux<AuditLogReportDTO> getSOClinicalAuditLog(@ModelAttribute AuditLogParameterDTO auditLogParameterDTO){
        return salesOrderClinicalAuditLogServiceExtended.getSOClinicalAuditTrailInfo(auditLogParameterDTO);
    }
}
