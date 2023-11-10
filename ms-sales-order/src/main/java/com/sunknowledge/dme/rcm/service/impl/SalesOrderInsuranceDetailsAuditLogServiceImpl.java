package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderInsuranceDetailsAuditLog}.
 */
@Service
@Transactional
public class SalesOrderInsuranceDetailsAuditLogServiceImpl implements SalesOrderInsuranceDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderInsuranceDetailsAuditLogServiceImpl.class);

    private final SalesOrderInsuranceDetailsAuditLogRepository salesOrderInsuranceDetailsAuditLogRepository;

    private final SalesOrderInsuranceDetailsAuditLogMapper salesOrderInsuranceDetailsAuditLogMapper;

    public SalesOrderInsuranceDetailsAuditLogServiceImpl(
        SalesOrderInsuranceDetailsAuditLogRepository salesOrderInsuranceDetailsAuditLogRepository,
        SalesOrderInsuranceDetailsAuditLogMapper salesOrderInsuranceDetailsAuditLogMapper
    ) {
        this.salesOrderInsuranceDetailsAuditLogRepository = salesOrderInsuranceDetailsAuditLogRepository;
        this.salesOrderInsuranceDetailsAuditLogMapper = salesOrderInsuranceDetailsAuditLogMapper;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> save(SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO) {
        log.debug("Request to save SalesOrderInsuranceDetailsAuditLog : {}", salesOrderInsuranceDetailsAuditLogDTO);
        return salesOrderInsuranceDetailsAuditLogRepository
            .save(salesOrderInsuranceDetailsAuditLogMapper.toEntity(salesOrderInsuranceDetailsAuditLogDTO))
            .map(salesOrderInsuranceDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> update(SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO) {
        log.debug("Request to save SalesOrderInsuranceDetailsAuditLog : {}", salesOrderInsuranceDetailsAuditLogDTO);
        return salesOrderInsuranceDetailsAuditLogRepository
            .save(salesOrderInsuranceDetailsAuditLogMapper.toEntity(salesOrderInsuranceDetailsAuditLogDTO))
            .map(salesOrderInsuranceDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> partialUpdate(
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO
    ) {
        log.debug("Request to partially update SalesOrderInsuranceDetailsAuditLog : {}", salesOrderInsuranceDetailsAuditLogDTO);

        return salesOrderInsuranceDetailsAuditLogRepository
            .findById(salesOrderInsuranceDetailsAuditLogDTO.getId())
            .map(existingSalesOrderInsuranceDetailsAuditLog -> {
                salesOrderInsuranceDetailsAuditLogMapper.partialUpdate(
                    existingSalesOrderInsuranceDetailsAuditLog,
                    salesOrderInsuranceDetailsAuditLogDTO
                );

                return existingSalesOrderInsuranceDetailsAuditLog;
            })
            .flatMap(salesOrderInsuranceDetailsAuditLogRepository::save)
            .map(salesOrderInsuranceDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderInsuranceDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderInsuranceDetailsAuditLogs");
        return salesOrderInsuranceDetailsAuditLogRepository.findAllBy(pageable).map(salesOrderInsuranceDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderInsuranceDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderInsuranceDetailsAuditLog : {}", id);
        return salesOrderInsuranceDetailsAuditLogRepository.findById(id).map(salesOrderInsuranceDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderInsuranceDetailsAuditLog : {}", id);
        return salesOrderInsuranceDetailsAuditLogRepository.deleteById(id);
    }
}
