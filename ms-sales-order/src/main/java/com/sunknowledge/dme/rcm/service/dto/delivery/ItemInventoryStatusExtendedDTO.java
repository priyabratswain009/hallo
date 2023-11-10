package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInventoryStatusExtendedDTO {
    private Long itemInventoryStatusId;
    private Long itemId;
    private Long itemLocationId;
    private Long onhandQty;
    private Long onrentQty;
    private Long comittedQty;
    private Long inorderQty;
    private Long itemQty;
}
