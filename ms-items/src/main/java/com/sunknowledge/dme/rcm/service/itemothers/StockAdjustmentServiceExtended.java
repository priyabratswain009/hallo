package com.sunknowledge.dme.rcm.service.itemothers;

import com.sunknowledge.dme.rcm.service.StockAdjustmentService;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;

import java.util.List;

public interface StockAdjustmentServiceExtended extends StockAdjustmentService {
    List<StockAdjustmentDTO> getStockAdjustmentByItemId(Long itemId);
}
