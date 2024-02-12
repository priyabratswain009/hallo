package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InvoiceLineItemDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceLineItemDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InvoiceLineItemDetailsAuditLog}.
 */
@Service
@Transactional
public class InvoiceLineItemDetailsAuditLogServiceImpl implements InvoiceLineItemDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineItemDetailsAuditLogServiceImpl.class);

    private final InvoiceLineItemDetailsAuditLogRepository invoiceLineItemDetailsAuditLogRepository;

    private final InvoiceLineItemDetailsAuditLogMapper invoiceLineItemDetailsAuditLogMapper;

    public InvoiceLineItemDetailsAuditLogServiceImpl(
        InvoiceLineItemDetailsAuditLogRepository invoiceLineItemDetailsAuditLogRepository,
        InvoiceLineItemDetailsAuditLogMapper invoiceLineItemDetailsAuditLogMapper
    ) {
        this.invoiceLineItemDetailsAuditLogRepository = invoiceLineItemDetailsAuditLogRepository;
        this.invoiceLineItemDetailsAuditLogMapper = invoiceLineItemDetailsAuditLogMapper;
    }

    @Override
    public Mono<InvoiceLineItemDetailsAuditLogDTO> save(InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO) {
        log.debug("Request to save InvoiceLineItemDetailsAuditLog : {}", invoiceLineItemDetailsAuditLogDTO);
        return invoiceLineItemDetailsAuditLogRepository
            .save(invoiceLineItemDetailsAuditLogMapper.toEntity(invoiceLineItemDetailsAuditLogDTO))
            .map(invoiceLineItemDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<InvoiceLineItemDetailsAuditLogDTO> update(InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO) {
        log.debug("Request to update InvoiceLineItemDetailsAuditLog : {}", invoiceLineItemDetailsAuditLogDTO);
        return invoiceLineItemDetailsAuditLogRepository
            .save(invoiceLineItemDetailsAuditLogMapper.toEntity(invoiceLineItemDetailsAuditLogDTO))
            .map(invoiceLineItemDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<InvoiceLineItemDetailsAuditLogDTO> partialUpdate(InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO) {
        log.debug("Request to partially update InvoiceLineItemDetailsAuditLog : {}", invoiceLineItemDetailsAuditLogDTO);

        return invoiceLineItemDetailsAuditLogRepository
            .findById(invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId())
            .map(existingInvoiceLineItemDetailsAuditLog -> {
                invoiceLineItemDetailsAuditLogMapper.partialUpdate(
                    existingInvoiceLineItemDetailsAuditLog,
                    invoiceLineItemDetailsAuditLogDTO
                );

                return existingInvoiceLineItemDetailsAuditLog;
            })
            .flatMap(invoiceLineItemDetailsAuditLogRepository::save)
            .map(invoiceLineItemDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoiceLineItemDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceLineItemDetailsAuditLogs");
        return invoiceLineItemDetailsAuditLogRepository.findAllBy(pageable).map(invoiceLineItemDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return invoiceLineItemDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoiceLineItemDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get InvoiceLineItemDetailsAuditLog : {}", id);
        return invoiceLineItemDetailsAuditLogRepository.findById(id).map(invoiceLineItemDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InvoiceLineItemDetailsAuditLog : {}", id);
        return invoiceLineItemDetailsAuditLogRepository.deleteById(id);
    }
}
