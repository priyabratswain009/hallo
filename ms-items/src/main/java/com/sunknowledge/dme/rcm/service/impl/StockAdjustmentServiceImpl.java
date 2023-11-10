package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.repository.StockAdjustmentRepository;
import com.sunknowledge.dme.rcm.service.StockAdjustmentService;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StockAdjustment}.
 */
@Service
@Transactional
public class StockAdjustmentServiceImpl implements StockAdjustmentService {

    private final Logger log = LoggerFactory.getLogger(StockAdjustmentServiceImpl.class);

    private final StockAdjustmentRepository stockAdjustmentRepository;

    private final StockAdjustmentMapper stockAdjustmentMapper;

    public StockAdjustmentServiceImpl(StockAdjustmentRepository stockAdjustmentRepository, StockAdjustmentMapper stockAdjustmentMapper) {
        this.stockAdjustmentRepository = stockAdjustmentRepository;
        this.stockAdjustmentMapper = stockAdjustmentMapper;
    }

    @Override
    public StockAdjustmentDTO save(StockAdjustmentDTO stockAdjustmentDTO) {
        log.debug("Request to save StockAdjustment : {}", stockAdjustmentDTO);
        StockAdjustment stockAdjustment = stockAdjustmentMapper.toEntity(stockAdjustmentDTO);
        stockAdjustment = stockAdjustmentRepository.save(stockAdjustment);
        return stockAdjustmentMapper.toDto(stockAdjustment);
    }

    @Override
    public StockAdjustmentDTO update(StockAdjustmentDTO stockAdjustmentDTO) {
        log.debug("Request to update StockAdjustment : {}", stockAdjustmentDTO);
        StockAdjustment stockAdjustment = stockAdjustmentMapper.toEntity(stockAdjustmentDTO);
        stockAdjustment = stockAdjustmentRepository.save(stockAdjustment);
        return stockAdjustmentMapper.toDto(stockAdjustment);
    }

    @Override
    public Optional<StockAdjustmentDTO> partialUpdate(StockAdjustmentDTO stockAdjustmentDTO) {
        log.debug("Request to partially update StockAdjustment : {}", stockAdjustmentDTO);

        return stockAdjustmentRepository
            .findById(stockAdjustmentDTO.getStockAdjustmentId())
            .map(existingStockAdjustment -> {
                stockAdjustmentMapper.partialUpdate(existingStockAdjustment, stockAdjustmentDTO);

                return existingStockAdjustment;
            })
            .map(stockAdjustmentRepository::save)
            .map(stockAdjustmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockAdjustmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockAdjustments");
        return stockAdjustmentRepository.findAll(pageable).map(stockAdjustmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockAdjustmentDTO> findOne(Long id) {
        log.debug("Request to get StockAdjustment : {}", id);
        return stockAdjustmentRepository.findById(id).map(stockAdjustmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockAdjustment : {}", id);
        stockAdjustmentRepository.deleteById(id);
    }
}
