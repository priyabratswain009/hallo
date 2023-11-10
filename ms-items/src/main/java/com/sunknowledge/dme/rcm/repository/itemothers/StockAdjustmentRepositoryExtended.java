package com.sunknowledge.dme.rcm.repository.itemothers;

import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.repository.StockAdjustmentRepository;

import java.util.List;

public interface StockAdjustmentRepositoryExtended extends StockAdjustmentRepository {
    List<StockAdjustment> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    List<StockAdjustment> findByItemId(Long itemId);
}
