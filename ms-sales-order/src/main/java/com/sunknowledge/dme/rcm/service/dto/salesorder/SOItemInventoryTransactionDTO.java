package com.sunknowledge.dme.rcm.service.dto.salesorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOItemInventoryTransactionDTO {
    private String salesOrderNo;
    private String saleType;
    private Long itemId;
    private String itemNo;
    private String itemName;
    private String itemUom;
    private Long itemQty;
    private String wheatherSerialized;
    private String serialNo;
    private String wheatherAssetTagged;
    private LocalDate originalDos;
    private Long branchId;
    private Long locationId;
    private String locationName;
    private Long createdById;
    private String createdByName;
    private LocalDate createdDate;
    private Long updatedById;
    private LocalDate updatedDate;
    private String updatedByName;
    private String isDropship;
    private String poNo;
}
