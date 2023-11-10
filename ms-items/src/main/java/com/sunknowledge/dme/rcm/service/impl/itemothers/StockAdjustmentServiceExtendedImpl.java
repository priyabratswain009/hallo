package com.sunknowledge.dme.rcm.service.impl.itemothers;

import com.sunknowledge.dme.rcm.repository.itemothers.StockAdjustmentRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.itemothers.StockAdjustmentServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StockAdjustmentServiceExtendedImpl implements StockAdjustmentServiceExtended {

    @Autowired
    StockAdjustmentRepositoryExtended stockAdjustmentRepositoryExtended;
    @Autowired
    StockAdjustmentMapper stockAdjustmentMapper;

    @Override
    public StockAdjustmentDTO save(StockAdjustmentDTO stockAdjustmentDTO) {
        return null;
    }

    @Override
    public StockAdjustmentDTO update(StockAdjustmentDTO stockAdjustmentDTO) {
        return null;
    }

    @Override
    public Optional<StockAdjustmentDTO> partialUpdate(StockAdjustmentDTO stockAdjustmentDTO) {
        return Optional.empty();
    }

    @Override
    public Page<StockAdjustmentDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StockAdjustmentDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<StockAdjustmentDTO> getStockAdjustmentByItemId(Long itemId) {
        return stockAdjustmentMapper.toDto(stockAdjustmentRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active"));
    }
}
