package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderItemDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderItemDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderItemDetailsAuditLog}.
 */
@Service
@Transactional
public class SalesOrderItemDetailsAuditLogServiceImpl implements SalesOrderItemDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderItemDetailsAuditLogServiceImpl.class);

    private final SalesOrderItemDetailsAuditLogRepository salesOrderItemDetailsAuditLogRepository;

    private final SalesOrderItemDetailsAuditLogMapper salesOrderItemDetailsAuditLogMapper;

    public SalesOrderItemDetailsAuditLogServiceImpl(
        SalesOrderItemDetailsAuditLogRepository salesOrderItemDetailsAuditLogRepository,
        SalesOrderItemDetailsAuditLogMapper salesOrderItemDetailsAuditLogMapper
    ) {
        this.salesOrderItemDetailsAuditLogRepository = salesOrderItemDetailsAuditLogRepository;
        this.salesOrderItemDetailsAuditLogMapper = salesOrderItemDetailsAuditLogMapper;
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> save(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO) {
        log.debug("Request to save SalesOrderItemDetailsAuditLog : {}", salesOrderItemDetailsAuditLogDTO);
        return salesOrderItemDetailsAuditLogRepository
            .save(salesOrderItemDetailsAuditLogMapper.toEntity(salesOrderItemDetailsAuditLogDTO))
            .map(salesOrderItemDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> update(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO) {
        log.debug("Request to update SalesOrderItemDetailsAuditLog : {}", salesOrderItemDetailsAuditLogDTO);
        return salesOrderItemDetailsAuditLogRepository
            .save(salesOrderItemDetailsAuditLogMapper.toEntity(salesOrderItemDetailsAuditLogDTO))
            .map(salesOrderItemDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> partialUpdate(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO) {
        log.debug("Request to partially update SalesOrderItemDetailsAuditLog : {}", salesOrderItemDetailsAuditLogDTO);

        return salesOrderItemDetailsAuditLogRepository
            .findById(salesOrderItemDetailsAuditLogDTO.getId())
            .map(existingSalesOrderItemDetailsAuditLog -> {
                salesOrderItemDetailsAuditLogMapper.partialUpdate(existingSalesOrderItemDetailsAuditLog, salesOrderItemDetailsAuditLogDTO);

                return existingSalesOrderItemDetailsAuditLog;
            })
            .flatMap(salesOrderItemDetailsAuditLogRepository::save)
            .map(salesOrderItemDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderItemDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderItemDetailsAuditLogs");
        return salesOrderItemDetailsAuditLogRepository.findAllBy(pageable).map(salesOrderItemDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderItemDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderItemDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderItemDetailsAuditLog : {}", id);
        return salesOrderItemDetailsAuditLogRepository.findById(id).map(salesOrderItemDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderItemDetailsAuditLog : {}", id);
        return salesOrderItemDetailsAuditLogRepository.deleteById(id);
    }
}
