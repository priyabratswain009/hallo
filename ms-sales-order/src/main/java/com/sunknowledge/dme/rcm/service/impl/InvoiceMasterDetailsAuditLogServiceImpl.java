package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InvoiceMasterDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceMasterDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InvoiceMasterDetailsAuditLog}.
 */
@Service
@Transactional
public class InvoiceMasterDetailsAuditLogServiceImpl implements InvoiceMasterDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(InvoiceMasterDetailsAuditLogServiceImpl.class);

    private final InvoiceMasterDetailsAuditLogRepository invoiceMasterDetailsAuditLogRepository;

    private final InvoiceMasterDetailsAuditLogMapper invoiceMasterDetailsAuditLogMapper;

    public InvoiceMasterDetailsAuditLogServiceImpl(
        InvoiceMasterDetailsAuditLogRepository invoiceMasterDetailsAuditLogRepository,
        InvoiceMasterDetailsAuditLogMapper invoiceMasterDetailsAuditLogMapper
    ) {
        this.invoiceMasterDetailsAuditLogRepository = invoiceMasterDetailsAuditLogRepository;
        this.invoiceMasterDetailsAuditLogMapper = invoiceMasterDetailsAuditLogMapper;
    }

    @Override
    public Mono<InvoiceMasterDetailsAuditLogDTO> save(InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO) {
        log.debug("Request to save InvoiceMasterDetailsAuditLog : {}", invoiceMasterDetailsAuditLogDTO);
        return invoiceMasterDetailsAuditLogRepository
            .save(invoiceMasterDetailsAuditLogMapper.toEntity(invoiceMasterDetailsAuditLogDTO))
            .map(invoiceMasterDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<InvoiceMasterDetailsAuditLogDTO> update(InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO) {
        log.debug("Request to update InvoiceMasterDetailsAuditLog : {}", invoiceMasterDetailsAuditLogDTO);
        return invoiceMasterDetailsAuditLogRepository
            .save(invoiceMasterDetailsAuditLogMapper.toEntity(invoiceMasterDetailsAuditLogDTO))
            .map(invoiceMasterDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<InvoiceMasterDetailsAuditLogDTO> partialUpdate(InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO) {
        log.debug("Request to partially update InvoiceMasterDetailsAuditLog : {}", invoiceMasterDetailsAuditLogDTO);

        return invoiceMasterDetailsAuditLogRepository
            .findById(invoiceMasterDetailsAuditLogDTO.getInvceId())
            .map(existingInvoiceMasterDetailsAuditLog -> {
                invoiceMasterDetailsAuditLogMapper.partialUpdate(existingInvoiceMasterDetailsAuditLog, invoiceMasterDetailsAuditLogDTO);

                return existingInvoiceMasterDetailsAuditLog;
            })
            .flatMap(invoiceMasterDetailsAuditLogRepository::save)
            .map(invoiceMasterDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoiceMasterDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceMasterDetailsAuditLogs");
        return invoiceMasterDetailsAuditLogRepository.findAllBy(pageable).map(invoiceMasterDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return invoiceMasterDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoiceMasterDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get InvoiceMasterDetailsAuditLog : {}", id);
        return invoiceMasterDetailsAuditLogRepository.findById(id).map(invoiceMasterDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InvoiceMasterDetailsAuditLog : {}", id);
        return invoiceMasterDetailsAuditLogRepository.deleteById(id);
    }
}
