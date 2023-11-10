package com.sunknowledge.dme.rcm.service.dto.audittrail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesOrderOverallAuditLogDTO {
    @NotNull(message = "must not be null")
    private Long id;

    @NotNull(message = "must not be null")
    private Long refId;

    private String oldRowData;

    private String newRowData;

    @NotNull(message = "must not be null")
    private String dmlType;

    @NotNull(message = "must not be null")
    private String section;

    @NotNull(message = "must not be null")
    private LocalDate dmlTimestamp;

    @NotNull(message = "must not be null")
    private String dmlCreatedBy;
}
