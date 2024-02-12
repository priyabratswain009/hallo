package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderClassificationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClassificationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClassificationAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SalesOrderClassificationAuditLog}.
 */
@Service
@Transactional
public class SalesOrderClassificationAuditLogServiceImpl implements SalesOrderClassificationAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClassificationAuditLogServiceImpl.class);

    private final SalesOrderClassificationAuditLogRepository salesOrderClassificationAuditLogRepository;

    private final SalesOrderClassificationAuditLogMapper salesOrderClassificationAuditLogMapper;

    public SalesOrderClassificationAuditLogServiceImpl(
        SalesOrderClassificationAuditLogRepository salesOrderClassificationAuditLogRepository,
        SalesOrderClassificationAuditLogMapper salesOrderClassificationAuditLogMapper
    ) {
        this.salesOrderClassificationAuditLogRepository = salesOrderClassificationAuditLogRepository;
        this.salesOrderClassificationAuditLogMapper = salesOrderClassificationAuditLogMapper;
    }

    @Override
    public SalesOrderClassificationAuditLogDTO save(SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO) {
        log.debug("Request to save SalesOrderClassificationAuditLog : {}", salesOrderClassificationAuditLogDTO);
        SalesOrderClassificationAuditLog salesOrderClassificationAuditLog = salesOrderClassificationAuditLogMapper.toEntity(
            salesOrderClassificationAuditLogDTO
        );
        salesOrderClassificationAuditLog = salesOrderClassificationAuditLogRepository.save(salesOrderClassificationAuditLog);
        return salesOrderClassificationAuditLogMapper.toDto(salesOrderClassificationAuditLog);
    }

    @Override
    public SalesOrderClassificationAuditLogDTO update(SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO) {
        log.debug("Request to update SalesOrderClassificationAuditLog : {}", salesOrderClassificationAuditLogDTO);
        SalesOrderClassificationAuditLog salesOrderClassificationAuditLog = salesOrderClassificationAuditLogMapper.toEntity(
            salesOrderClassificationAuditLogDTO
        );
        salesOrderClassificationAuditLog = salesOrderClassificationAuditLogRepository.save(salesOrderClassificationAuditLog);
        return salesOrderClassificationAuditLogMapper.toDto(salesOrderClassificationAuditLog);
    }

    @Override
    public Optional<SalesOrderClassificationAuditLogDTO> partialUpdate(
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO
    ) {
        log.debug("Request to partially update SalesOrderClassificationAuditLog : {}", salesOrderClassificationAuditLogDTO);

        return salesOrderClassificationAuditLogRepository
            .findById(salesOrderClassificationAuditLogDTO.getId())
            .map(existingSalesOrderClassificationAuditLog -> {
                salesOrderClassificationAuditLogMapper.partialUpdate(
                    existingSalesOrderClassificationAuditLog,
                    salesOrderClassificationAuditLogDTO
                );

                return existingSalesOrderClassificationAuditLog;
            })
            .map(salesOrderClassificationAuditLogRepository::save)
            .map(salesOrderClassificationAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesOrderClassificationAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderClassificationAuditLogs");
        return salesOrderClassificationAuditLogRepository.findAll(pageable).map(salesOrderClassificationAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesOrderClassificationAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderClassificationAuditLog : {}", id);
        return salesOrderClassificationAuditLogRepository.findById(id).map(salesOrderClassificationAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesOrderClassificationAuditLog : {}", id);
        salesOrderClassificationAuditLogRepository.deleteById(id);
    }
}
