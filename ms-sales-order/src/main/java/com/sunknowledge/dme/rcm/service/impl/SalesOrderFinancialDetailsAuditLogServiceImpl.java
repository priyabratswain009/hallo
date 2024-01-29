package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderFinancialDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderFinancialDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderFinancialDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderFinancialDetailsAuditLog}.
 */
@Service
@Transactional
public class SalesOrderFinancialDetailsAuditLogServiceImpl implements SalesOrderFinancialDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderFinancialDetailsAuditLogServiceImpl.class);

    private final SalesOrderFinancialDetailsAuditLogRepository salesOrderFinancialDetailsAuditLogRepository;

    private final SalesOrderFinancialDetailsAuditLogMapper salesOrderFinancialDetailsAuditLogMapper;

    public SalesOrderFinancialDetailsAuditLogServiceImpl(
        SalesOrderFinancialDetailsAuditLogRepository salesOrderFinancialDetailsAuditLogRepository,
        SalesOrderFinancialDetailsAuditLogMapper salesOrderFinancialDetailsAuditLogMapper
    ) {
        this.salesOrderFinancialDetailsAuditLogRepository = salesOrderFinancialDetailsAuditLogRepository;
        this.salesOrderFinancialDetailsAuditLogMapper = salesOrderFinancialDetailsAuditLogMapper;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> save(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO) {
        log.debug("Request to save SalesOrderFinancialDetailsAuditLog : {}", salesOrderFinancialDetailsAuditLogDTO);
        return salesOrderFinancialDetailsAuditLogRepository
            .save(salesOrderFinancialDetailsAuditLogMapper.toEntity(salesOrderFinancialDetailsAuditLogDTO))
            .map(salesOrderFinancialDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> update(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO) {
        log.debug("Request to update SalesOrderFinancialDetailsAuditLog : {}", salesOrderFinancialDetailsAuditLogDTO);
        return salesOrderFinancialDetailsAuditLogRepository
            .save(salesOrderFinancialDetailsAuditLogMapper.toEntity(salesOrderFinancialDetailsAuditLogDTO))
            .map(salesOrderFinancialDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> partialUpdate(
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO
    ) {
        log.debug("Request to partially update SalesOrderFinancialDetailsAuditLog : {}", salesOrderFinancialDetailsAuditLogDTO);

        return salesOrderFinancialDetailsAuditLogRepository
            .findById(salesOrderFinancialDetailsAuditLogDTO.getId())
            .map(existingSalesOrderFinancialDetailsAuditLog -> {
                salesOrderFinancialDetailsAuditLogMapper.partialUpdate(
                    existingSalesOrderFinancialDetailsAuditLog,
                    salesOrderFinancialDetailsAuditLogDTO
                );

                return existingSalesOrderFinancialDetailsAuditLog;
            })
            .flatMap(salesOrderFinancialDetailsAuditLogRepository::save)
            .map(salesOrderFinancialDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderFinancialDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderFinancialDetailsAuditLogs");
        return salesOrderFinancialDetailsAuditLogRepository.findAllBy(pageable).map(salesOrderFinancialDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderFinancialDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderFinancialDetailsAuditLog : {}", id);
        return salesOrderFinancialDetailsAuditLogRepository.findById(id).map(salesOrderFinancialDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderFinancialDetailsAuditLog : {}", id);
        return salesOrderFinancialDetailsAuditLogRepository.deleteById(id);
    }
}
