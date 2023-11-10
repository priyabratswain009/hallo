package com.sunknowledge.dme.rcm.web.rest.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
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
public class SalesOrderAuditLogResourceExtended {
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderAuditLogServiceExtended salesOrderAuditLogServiceExtended;

    /**
     * Get SO Details with Created By Id
     */
    @GetMapping("/getSOAuditLog")
    public Flux<AuditLogReportDTO> getSOAuditLog(@ModelAttribute AuditLogParameterDTO auditLogParameterDTO) {
        return salesOrderAuditLogServiceExtended.getSOAuditTrailInfo(auditLogParameterDTO);
    }

    @GetMapping("/getSOOverallAuditLog")
    public Flux<AuditLogReportDTO> getSOOverallAuditLog(@ModelAttribute AuditLogParameterDTO auditLogParameterDTO) {
        return salesOrderAuditLogServiceExtended.getSOOverallAuditTrailInfo(auditLogParameterDTO);
    }

    @GetMapping("/getSOUserActivityReportForAuditLog")
    public Flux<UserActivityReportDTO> getSOUserActivityReportForAuditLog(@ModelAttribute UserActivityParameterDTO userActivityParameterDTO) {
        return salesOrderAuditLogServiceExtended.getSOUserActivityReportForAuditLog(userActivityParameterDTO);
    }
}
