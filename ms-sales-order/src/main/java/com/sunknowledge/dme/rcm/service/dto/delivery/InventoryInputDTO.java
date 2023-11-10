package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryInputDTO {
    private List<InventoryStatusByItemIdAndItemLocationIdInputDTO> inventoryStatusByItemIdAndItemLocationIdInputDTO;
}
