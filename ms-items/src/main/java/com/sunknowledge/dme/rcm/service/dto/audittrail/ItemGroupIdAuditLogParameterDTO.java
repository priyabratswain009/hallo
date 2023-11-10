package com.sunknowledge.dme.rcm.service.dto.audittrail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemGroupIdAuditLogParameterDTO {
    private Long itemGroupId;
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
