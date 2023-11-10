package com.sunknowledge.dme.rcm.service.inventory;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusInputRequest;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusResponse;
import com.sunknowledge.dme.rcm.service.dto.inventory.StockAdjustmentRequestParams;

public interface InventoryService {
    ServiceOutcome<StockAdjustmentDTO> inventoryStockAdjustment(StockAdjustmentRequestParams stockAdjustmentRequestParams);
    StockAdjustment inventoryStockAdjustment1(StockAdjustmentRequestParams stockAdjustmentRequestParams);
    ServiceOutcome<ItemInventoryStatusResponse> updateItemInventoryStatusByItemAndLocation(ItemInventoryStatusInputRequest itemInventoryStatusInputRequest);
}
