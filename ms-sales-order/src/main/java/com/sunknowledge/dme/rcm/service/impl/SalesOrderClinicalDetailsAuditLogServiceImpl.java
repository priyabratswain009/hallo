package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClinicalDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClinicalDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderClinicalDetailsAuditLog}.
 */
@Service
@Transactional
public class SalesOrderClinicalDetailsAuditLogServiceImpl implements SalesOrderClinicalDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClinicalDetailsAuditLogServiceImpl.class);

    private final SalesOrderClinicalDetailsAuditLogRepository salesOrderClinicalDetailsAuditLogRepository;

    private final SalesOrderClinicalDetailsAuditLogMapper salesOrderClinicalDetailsAuditLogMapper;

    public SalesOrderClinicalDetailsAuditLogServiceImpl(
        SalesOrderClinicalDetailsAuditLogRepository salesOrderClinicalDetailsAuditLogRepository,
        SalesOrderClinicalDetailsAuditLogMapper salesOrderClinicalDetailsAuditLogMapper
    ) {
        this.salesOrderClinicalDetailsAuditLogRepository = salesOrderClinicalDetailsAuditLogRepository;
        this.salesOrderClinicalDetailsAuditLogMapper = salesOrderClinicalDetailsAuditLogMapper;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> save(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO) {
        log.debug("Request to save SalesOrderClinicalDetailsAuditLog : {}", salesOrderClinicalDetailsAuditLogDTO);
        return salesOrderClinicalDetailsAuditLogRepository
            .save(salesOrderClinicalDetailsAuditLogMapper.toEntity(salesOrderClinicalDetailsAuditLogDTO))
            .map(salesOrderClinicalDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> update(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO) {
        log.debug("Request to save SalesOrderClinicalDetailsAuditLog : {}", salesOrderClinicalDetailsAuditLogDTO);
        return salesOrderClinicalDetailsAuditLogRepository
            .save(salesOrderClinicalDetailsAuditLogMapper.toEntity(salesOrderClinicalDetailsAuditLogDTO))
            .map(salesOrderClinicalDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> partialUpdate(
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO
    ) {
        log.debug("Request to partially update SalesOrderClinicalDetailsAuditLog : {}", salesOrderClinicalDetailsAuditLogDTO);

        return salesOrderClinicalDetailsAuditLogRepository
            .findById(salesOrderClinicalDetailsAuditLogDTO.getId())
            .map(existingSalesOrderClinicalDetailsAuditLog -> {
                salesOrderClinicalDetailsAuditLogMapper.partialUpdate(
                    existingSalesOrderClinicalDetailsAuditLog,
                    salesOrderClinicalDetailsAuditLogDTO
                );

                return existingSalesOrderClinicalDetailsAuditLog;
            })
            .flatMap(salesOrderClinicalDetailsAuditLogRepository::save)
            .map(salesOrderClinicalDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderClinicalDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderClinicalDetailsAuditLogs");
        return salesOrderClinicalDetailsAuditLogRepository.findAllBy(pageable).map(salesOrderClinicalDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderClinicalDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderClinicalDetailsAuditLog : {}", id);
        return salesOrderClinicalDetailsAuditLogRepository.findById(id).map(salesOrderClinicalDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderClinicalDetailsAuditLog : {}", id);
        return salesOrderClinicalDetailsAuditLogRepository.deleteById(id);
    }
}
