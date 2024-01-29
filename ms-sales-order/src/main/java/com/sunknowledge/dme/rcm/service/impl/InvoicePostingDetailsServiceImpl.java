package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoicePostingDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InvoicePostingDetails}.
 */
@Service
@Transactional
public class InvoicePostingDetailsServiceImpl implements InvoicePostingDetailsService {

    private final Logger log = LoggerFactory.getLogger(InvoicePostingDetailsServiceImpl.class);

    private final InvoicePostingDetailsRepository invoicePostingDetailsRepository;

    private final InvoicePostingDetailsMapper invoicePostingDetailsMapper;

    public InvoicePostingDetailsServiceImpl(
        InvoicePostingDetailsRepository invoicePostingDetailsRepository,
        InvoicePostingDetailsMapper invoicePostingDetailsMapper
    ) {
        this.invoicePostingDetailsRepository = invoicePostingDetailsRepository;
        this.invoicePostingDetailsMapper = invoicePostingDetailsMapper;
    }

    @Override
    public Mono<InvoicePostingDetailsDTO> save(InvoicePostingDetailsDTO invoicePostingDetailsDTO) {
        log.debug("Request to save InvoicePostingDetails : {}", invoicePostingDetailsDTO);
        return invoicePostingDetailsRepository
            .save(invoicePostingDetailsMapper.toEntity(invoicePostingDetailsDTO))
            .map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    public Mono<InvoicePostingDetailsDTO> update(InvoicePostingDetailsDTO invoicePostingDetailsDTO) {
        log.debug("Request to update InvoicePostingDetails : {}", invoicePostingDetailsDTO);
        return invoicePostingDetailsRepository
            .save(invoicePostingDetailsMapper.toEntity(invoicePostingDetailsDTO))
            .map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    public Mono<InvoicePostingDetailsDTO> partialUpdate(InvoicePostingDetailsDTO invoicePostingDetailsDTO) {
        log.debug("Request to partially update InvoicePostingDetails : {}", invoicePostingDetailsDTO);

        return invoicePostingDetailsRepository
            .findById(invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
            .map(existingInvoicePostingDetails -> {
                invoicePostingDetailsMapper.partialUpdate(existingInvoicePostingDetails, invoicePostingDetailsDTO);

                return existingInvoicePostingDetails;
            })
            .flatMap(invoicePostingDetailsRepository::save)
            .map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoicePostingDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoicePostingDetails");
        return invoicePostingDetailsRepository.findAllBy(pageable).map(invoicePostingDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return invoicePostingDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoicePostingDetailsDTO> findOne(Long id) {
        log.debug("Request to get InvoicePostingDetails : {}", id);
        return invoicePostingDetailsRepository.findById(id).map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InvoicePostingDetails : {}", id);
        return invoicePostingDetailsRepository.deleteById(id);
    }
}
