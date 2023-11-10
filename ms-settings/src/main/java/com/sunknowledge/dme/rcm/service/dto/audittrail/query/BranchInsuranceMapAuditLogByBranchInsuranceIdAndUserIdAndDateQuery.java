package com.sunknowledge.dme.rcm.service.dto.audittrail.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery {
    private List<Long> branchInsuranceMapIdList;
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
