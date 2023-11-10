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
public class UserActivityReportDTO {
    private String userId;
    private String userName;
    private LocalDate date;
    private Long countOfSoUpdates;
    private String patientIdNumber;
    private String patientIds;
}
