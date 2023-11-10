package com.sunknowledge.dme.rcm.service.dto.audittrail.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientAuditLogByPatientNoAndUserIdAndDateQuery {
    private String patientIdNo;
    private Long patientId;
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
