package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InvoicePostingDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InvoicePostingDetailsAuditLog}.
 */
@Service
@Transactional
public class InvoicePostingDetailsAuditLogServiceImpl implements InvoicePostingDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(InvoicePostingDetailsAuditLogServiceImpl.class);

    private final InvoicePostingDetailsAuditLogRepository invoicePostingDetailsAuditLogRepository;

    private final InvoicePostingDetailsAuditLogMapper invoicePostingDetailsAuditLogMapper;

    public InvoicePostingDetailsAuditLogServiceImpl(
        InvoicePostingDetailsAuditLogRepository invoicePostingDetailsAuditLogRepository,
        InvoicePostingDetailsAuditLogMapper invoicePostingDetailsAuditLogMapper
    ) {
        this.invoicePostingDetailsAuditLogRepository = invoicePostingDetailsAuditLogRepository;
        this.invoicePostingDetailsAuditLogMapper = invoicePostingDetailsAuditLogMapper;
    }

    @Override
    public Mono<InvoicePostingDetailsAuditLogDTO> save(InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO) {
        log.debug("Request to save InvoicePostingDetailsAuditLog : {}", invoicePostingDetailsAuditLogDTO);
        return invoicePostingDetailsAuditLogRepository
            .save(invoicePostingDetailsAuditLogMapper.toEntity(invoicePostingDetailsAuditLogDTO))
            .map(invoicePostingDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<InvoicePostingDetailsAuditLogDTO> update(InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO) {
        log.debug("Request to save InvoicePostingDetailsAuditLog : {}", invoicePostingDetailsAuditLogDTO);
        return invoicePostingDetailsAuditLogRepository
            .save(invoicePostingDetailsAuditLogMapper.toEntity(invoicePostingDetailsAuditLogDTO))
            .map(invoicePostingDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<InvoicePostingDetailsAuditLogDTO> partialUpdate(InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO) {
        log.debug("Request to partially update InvoicePostingDetailsAuditLog : {}", invoicePostingDetailsAuditLogDTO);

        return invoicePostingDetailsAuditLogRepository
            .findById(invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId())
            .map(existingInvoicePostingDetailsAuditLog -> {
                invoicePostingDetailsAuditLogMapper.partialUpdate(existingInvoicePostingDetailsAuditLog, invoicePostingDetailsAuditLogDTO);

                return existingInvoicePostingDetailsAuditLog;
            })
            .flatMap(invoicePostingDetailsAuditLogRepository::save)
            .map(invoicePostingDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoicePostingDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoicePostingDetailsAuditLogs");
        return invoicePostingDetailsAuditLogRepository.findAllBy(pageable).map(invoicePostingDetailsAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return invoicePostingDetailsAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoicePostingDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get InvoicePostingDetailsAuditLog : {}", id);
        return invoicePostingDetailsAuditLogRepository.findById(id).map(invoicePostingDetailsAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InvoicePostingDetailsAuditLog : {}", id);
        return invoicePostingDetailsAuditLogRepository.deleteById(id);
    }
}
