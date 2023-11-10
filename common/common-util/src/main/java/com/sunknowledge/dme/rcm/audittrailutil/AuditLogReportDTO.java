package com.sunknowledge.dme.rcm.audittrailutil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogReportDTO {
    private Long id;
    private String no;
    private String section;
    private String activity;
    private Long userId;
    private String userName;
    private LocalDateTime dateTime;
}
