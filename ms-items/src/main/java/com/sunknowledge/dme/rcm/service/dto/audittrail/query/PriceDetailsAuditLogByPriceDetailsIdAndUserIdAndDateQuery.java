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
public class PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery {
    private Long itemId;
    private String itemNo;
    private List<Long> priceDetailsIdList;
    private Long userID;
    private LocalDate fromDate;
    private LocalDate toDate;
}
