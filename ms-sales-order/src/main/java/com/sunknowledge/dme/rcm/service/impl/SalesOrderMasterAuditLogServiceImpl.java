package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderMasterAuditLog}.
 */
@Service
@Transactional
public class SalesOrderMasterAuditLogServiceImpl implements SalesOrderMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterAuditLogServiceImpl.class);

    private final SalesOrderMasterAuditLogRepository salesOrderMasterAuditLogRepository;

    private final SalesOrderMasterAuditLogMapper salesOrderMasterAuditLogMapper;

    public SalesOrderMasterAuditLogServiceImpl(
        SalesOrderMasterAuditLogRepository salesOrderMasterAuditLogRepository,
        SalesOrderMasterAuditLogMapper salesOrderMasterAuditLogMapper
    ) {
        this.salesOrderMasterAuditLogRepository = salesOrderMasterAuditLogRepository;
        this.salesOrderMasterAuditLogMapper = salesOrderMasterAuditLogMapper;
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> save(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO) {
        log.debug("Request to save SalesOrderMasterAuditLog : {}", salesOrderMasterAuditLogDTO);
        return salesOrderMasterAuditLogRepository
            .save(salesOrderMasterAuditLogMapper.toEntity(salesOrderMasterAuditLogDTO))
            .map(salesOrderMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> update(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO) {
        log.debug("Request to save SalesOrderMasterAuditLog : {}", salesOrderMasterAuditLogDTO);
        return salesOrderMasterAuditLogRepository
            .save(salesOrderMasterAuditLogMapper.toEntity(salesOrderMasterAuditLogDTO))
            .map(salesOrderMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> partialUpdate(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO) {
        log.debug("Request to partially update SalesOrderMasterAuditLog : {}", salesOrderMasterAuditLogDTO);

        return salesOrderMasterAuditLogRepository
            .findById(salesOrderMasterAuditLogDTO.getId())
            .map(existingSalesOrderMasterAuditLog -> {
                salesOrderMasterAuditLogMapper.partialUpdate(existingSalesOrderMasterAuditLog, salesOrderMasterAuditLogDTO);

                return existingSalesOrderMasterAuditLog;
            })
            .flatMap(salesOrderMasterAuditLogRepository::save)
            .map(salesOrderMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderMasterAuditLogs");
        return salesOrderMasterAuditLogRepository.findAllBy(pageable).map(salesOrderMasterAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderMasterAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderMasterAuditLog : {}", id);
        return salesOrderMasterAuditLogRepository.findById(id).map(salesOrderMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderMasterAuditLog : {}", id);
        return salesOrderMasterAuditLogRepository.deleteById(id);
    }
}
