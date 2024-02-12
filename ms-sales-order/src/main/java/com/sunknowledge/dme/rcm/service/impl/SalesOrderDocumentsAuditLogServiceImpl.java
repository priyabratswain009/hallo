package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderDocumentsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderDocumentsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderDocumentsAuditLog}.
 */
@Service
@Transactional
public class SalesOrderDocumentsAuditLogServiceImpl implements SalesOrderDocumentsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderDocumentsAuditLogServiceImpl.class);

    private final SalesOrderDocumentsAuditLogRepository salesOrderDocumentsAuditLogRepository;

    private final SalesOrderDocumentsAuditLogMapper salesOrderDocumentsAuditLogMapper;

    public SalesOrderDocumentsAuditLogServiceImpl(
        SalesOrderDocumentsAuditLogRepository salesOrderDocumentsAuditLogRepository,
        SalesOrderDocumentsAuditLogMapper salesOrderDocumentsAuditLogMapper
    ) {
        this.salesOrderDocumentsAuditLogRepository = salesOrderDocumentsAuditLogRepository;
        this.salesOrderDocumentsAuditLogMapper = salesOrderDocumentsAuditLogMapper;
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> save(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO) {
        log.debug("Request to save SalesOrderDocumentsAuditLog : {}", salesOrderDocumentsAuditLogDTO);
        return salesOrderDocumentsAuditLogRepository
            .save(salesOrderDocumentsAuditLogMapper.toEntity(salesOrderDocumentsAuditLogDTO))
            .map(salesOrderDocumentsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> update(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO) {
        log.debug("Request to update SalesOrderDocumentsAuditLog : {}", salesOrderDocumentsAuditLogDTO);
        return salesOrderDocumentsAuditLogRepository
            .save(salesOrderDocumentsAuditLogMapper.toEntity(salesOrderDocumentsAuditLogDTO))
            .map(salesOrderDocumentsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> partialUpdate(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO) {
        log.debug("Request to partially update SalesOrderDocumentsAuditLog : {}", salesOrderDocumentsAuditLogDTO);

        return salesOrderDocumentsAuditLogRepository
            .findById(salesOrderDocumentsAuditLogDTO.getId())
            .map(existingSalesOrderDocumentsAuditLog -> {
                salesOrderDocumentsAuditLogMapper.partialUpdate(existingSalesOrderDocumentsAuditLog, salesOrderDocumentsAuditLogDTO);

                return existingSalesOrderDocumentsAuditLog;
            })
            .flatMap(salesOrderDocumentsAuditLogRepository::save)
            .map(salesOrderDocumentsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderDocumentsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderDocumentsAuditLogs");
        return salesOrderDocumentsAuditLogRepository.findAllBy(pageable).map(salesOrderDocumentsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderDocumentsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderDocumentsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderDocumentsAuditLog : {}", id);
        return salesOrderDocumentsAuditLogRepository.findById(id).map(salesOrderDocumentsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderDocumentsAuditLog : {}", id);
        return salesOrderDocumentsAuditLogRepository.deleteById(id);
    }
}
