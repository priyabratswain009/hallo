package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.StockAdjustmentAuditLog;
import com.sunknowledge.dme.rcm.repository.StockAdjustmentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.StockAdjustmentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StockAdjustmentAuditLog}.
 */
@Service
@Transactional
public class StockAdjustmentAuditLogServiceImpl implements StockAdjustmentAuditLogService {

    private final Logger log = LoggerFactory.getLogger(StockAdjustmentAuditLogServiceImpl.class);

    private final StockAdjustmentAuditLogRepository stockAdjustmentAuditLogRepository;

    private final StockAdjustmentAuditLogMapper stockAdjustmentAuditLogMapper;

    public StockAdjustmentAuditLogServiceImpl(
        StockAdjustmentAuditLogRepository stockAdjustmentAuditLogRepository,
        StockAdjustmentAuditLogMapper stockAdjustmentAuditLogMapper
    ) {
        this.stockAdjustmentAuditLogRepository = stockAdjustmentAuditLogRepository;
        this.stockAdjustmentAuditLogMapper = stockAdjustmentAuditLogMapper;
    }

    @Override
    public StockAdjustmentAuditLogDTO save(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO) {
        log.debug("Request to save StockAdjustmentAuditLog : {}", stockAdjustmentAuditLogDTO);
        StockAdjustmentAuditLog stockAdjustmentAuditLog = stockAdjustmentAuditLogMapper.toEntity(stockAdjustmentAuditLogDTO);
        stockAdjustmentAuditLog = stockAdjustmentAuditLogRepository.save(stockAdjustmentAuditLog);
        return stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);
    }

    @Override
    public StockAdjustmentAuditLogDTO update(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO) {
        log.debug("Request to save StockAdjustmentAuditLog : {}", stockAdjustmentAuditLogDTO);
        StockAdjustmentAuditLog stockAdjustmentAuditLog = stockAdjustmentAuditLogMapper.toEntity(stockAdjustmentAuditLogDTO);
        stockAdjustmentAuditLog = stockAdjustmentAuditLogRepository.save(stockAdjustmentAuditLog);
        return stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);
    }

    @Override
    public Optional<StockAdjustmentAuditLogDTO> partialUpdate(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO) {
        log.debug("Request to partially update StockAdjustmentAuditLog : {}", stockAdjustmentAuditLogDTO);

        return stockAdjustmentAuditLogRepository
            .findById(stockAdjustmentAuditLogDTO.getId())
            .map(existingStockAdjustmentAuditLog -> {
                stockAdjustmentAuditLogMapper.partialUpdate(existingStockAdjustmentAuditLog, stockAdjustmentAuditLogDTO);

                return existingStockAdjustmentAuditLog;
            })
            .map(stockAdjustmentAuditLogRepository::save)
            .map(stockAdjustmentAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockAdjustmentAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockAdjustmentAuditLogs");
        return stockAdjustmentAuditLogRepository.findAll(pageable).map(stockAdjustmentAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockAdjustmentAuditLogDTO> findOne(Long id) {
        log.debug("Request to get StockAdjustmentAuditLog : {}", id);
        return stockAdjustmentAuditLogRepository.findById(id).map(stockAdjustmentAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockAdjustmentAuditLog : {}", id);
        stockAdjustmentAuditLogRepository.deleteById(id);
    }
}
