package com.sunknowledge.dme.rcm.service.dto.audittrail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditLogParameterDTO {
    private String patientIdNo;
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
