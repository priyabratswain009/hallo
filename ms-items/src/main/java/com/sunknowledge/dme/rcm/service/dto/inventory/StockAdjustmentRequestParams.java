package com.sunknowledge.dme.rcm.service.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockAdjustmentRequestParams {
    private String adjustmentType;
    private Long itemId;
    private Long locationId;
    private Long branchId;
    private String branchName;
    private Integer itemQty;
    private String whetherSerialised;
    private String serialNos;
    private String lotNos;
    private String mfgDates;
    private Double avgPrice;
    private Long uid;
    private String uname;
}
