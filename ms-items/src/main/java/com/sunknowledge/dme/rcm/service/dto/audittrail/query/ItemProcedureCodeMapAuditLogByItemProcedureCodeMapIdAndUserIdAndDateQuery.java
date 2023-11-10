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
public class ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery {
    private List<Long> itemProcedureCodeMapIdList;
    private Long itemId;
    private String itemNo;
    private Long userID;
    private LocalDate fromDate;
    private LocalDate toDate;
}