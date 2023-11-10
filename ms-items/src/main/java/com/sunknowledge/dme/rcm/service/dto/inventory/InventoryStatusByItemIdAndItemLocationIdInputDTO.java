package com.sunknowledge.dme.rcm.service.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryStatusByItemIdAndItemLocationIdInputDTO {
    //@NotNull(message="Item Id must be provided.")
    //@Min(value=1, message="Item Id must be greater than and equals to 1.")
    private int itemId;

    //@NotNull(message="Item Location Id must be provided.")
    //@Min(value=1, message="Item Location Id must be greater than and equals to 1.")
    private int itemLocationId;

    private int ItemQty;

    private String soNo;
    private String operationType;
    private String saleType;
    private String IsDropshipFlag;
}
