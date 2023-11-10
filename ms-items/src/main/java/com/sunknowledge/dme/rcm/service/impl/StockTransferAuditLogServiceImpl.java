package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.StockTransferAuditLog;
import com.sunknowledge.dme.rcm.repository.StockTransferAuditLogRepository;
import com.sunknowledge.dme.rcm.service.StockTransferAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockTransferAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StockTransferAuditLog}.
 */
@Service
@Transactional
public class StockTransferAuditLogServiceImpl implements StockTransferAuditLogService {

    private final Logger log = LoggerFactory.getLogger(StockTransferAuditLogServiceImpl.class);

    private final StockTransferAuditLogRepository stockTransferAuditLogRepository;

    private final StockTransferAuditLogMapper stockTransferAuditLogMapper;

    public StockTransferAuditLogServiceImpl(
        StockTransferAuditLogRepository stockTransferAuditLogRepository,
        StockTransferAuditLogMapper stockTransferAuditLogMapper
    ) {
        this.stockTransferAuditLogRepository = stockTransferAuditLogRepository;
        this.stockTransferAuditLogMapper = stockTransferAuditLogMapper;
    }

    @Override
    public StockTransferAuditLogDTO save(StockTransferAuditLogDTO stockTransferAuditLogDTO) {
        log.debug("Request to save StockTransferAuditLog : {}", stockTransferAuditLogDTO);
        StockTransferAuditLog stockTransferAuditLog = stockTransferAuditLogMapper.toEntity(stockTransferAuditLogDTO);
        stockTransferAuditLog = stockTransferAuditLogRepository.save(stockTransferAuditLog);
        return stockTransferAuditLogMapper.toDto(stockTransferAuditLog);
    }

    @Override
    public StockTransferAuditLogDTO update(StockTransferAuditLogDTO stockTransferAuditLogDTO) {
        log.debug("Request to update StockTransferAuditLog : {}", stockTransferAuditLogDTO);
        StockTransferAuditLog stockTransferAuditLog = stockTransferAuditLogMapper.toEntity(stockTransferAuditLogDTO);
        stockTransferAuditLog = stockTransferAuditLogRepository.save(stockTransferAuditLog);
        return stockTransferAuditLogMapper.toDto(stockTransferAuditLog);
    }

    @Override
    public Optional<StockTransferAuditLogDTO> partialUpdate(StockTransferAuditLogDTO stockTransferAuditLogDTO) {
        log.debug("Request to partially update StockTransferAuditLog : {}", stockTransferAuditLogDTO);

        return stockTransferAuditLogRepository
            .findById(stockTransferAuditLogDTO.getId())
            .map(existingStockTransferAuditLog -> {
                stockTransferAuditLogMapper.partialUpdate(existingStockTransferAuditLog, stockTransferAuditLogDTO);

                return existingStockTransferAuditLog;
            })
            .map(stockTransferAuditLogRepository::save)
            .map(stockTransferAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockTransferAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockTransferAuditLogs");
        return stockTransferAuditLogRepository.findAll(pageable).map(stockTransferAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockTransferAuditLogDTO> findOne(Long id) {
        log.debug("Request to get StockTransferAuditLog : {}", id);
        return stockTransferAuditLogRepository.findById(id).map(stockTransferAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockTransferAuditLog : {}", id);
        stockTransferAuditLogRepository.deleteById(id);
    }
}
