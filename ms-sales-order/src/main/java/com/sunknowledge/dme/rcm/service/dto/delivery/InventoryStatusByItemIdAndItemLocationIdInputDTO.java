package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryStatusByItemIdAndItemLocationIdInputDTO {
    private Long itemId;

    private Long itemLocationId;

    private Long ItemQty;

    private String operationType;

    private String saleType;

    private String isDropshipFlag;
    private String soNo;
}
