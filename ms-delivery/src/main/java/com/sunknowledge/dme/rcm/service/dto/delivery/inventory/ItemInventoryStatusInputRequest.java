package com.sunknowledge.dme.rcm.service.dto.delivery.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInventoryStatusInputRequest {
    private List<ItemInventoryStatusInputParams> itemInventoryStatusInputParamsList;
}
