package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceLineItemDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceLineItemDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InvoiceLineItemDetails}.
 */
@Service
@Transactional
public class InvoiceLineItemDetailsServiceImpl implements InvoiceLineItemDetailsService {

    private final Logger log = LoggerFactory.getLogger(InvoiceLineItemDetailsServiceImpl.class);

    private final InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository;

    private final InvoiceLineItemDetailsMapper invoiceLineItemDetailsMapper;

    public InvoiceLineItemDetailsServiceImpl(
        InvoiceLineItemDetailsRepository invoiceLineItemDetailsRepository,
        InvoiceLineItemDetailsMapper invoiceLineItemDetailsMapper
    ) {
        this.invoiceLineItemDetailsRepository = invoiceLineItemDetailsRepository;
        this.invoiceLineItemDetailsMapper = invoiceLineItemDetailsMapper;
    }

    @Override
    public Mono<InvoiceLineItemDetailsDTO> save(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO) {
        log.debug("Request to save InvoiceLineItemDetails : {}", invoiceLineItemDetailsDTO);
        return invoiceLineItemDetailsRepository
            .save(invoiceLineItemDetailsMapper.toEntity(invoiceLineItemDetailsDTO))
            .map(invoiceLineItemDetailsMapper::toDto);
    }

    @Override
    public Mono<InvoiceLineItemDetailsDTO> update(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO) {
        log.debug("Request to save InvoiceLineItemDetails : {}", invoiceLineItemDetailsDTO);
        return invoiceLineItemDetailsRepository
            .save(invoiceLineItemDetailsMapper.toEntity(invoiceLineItemDetailsDTO))
            .map(invoiceLineItemDetailsMapper::toDto);
    }

    @Override
    public Mono<InvoiceLineItemDetailsDTO> partialUpdate(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO) {
        log.debug("Request to partially update InvoiceLineItemDetails : {}", invoiceLineItemDetailsDTO);

        return invoiceLineItemDetailsRepository
            .findById(invoiceLineItemDetailsDTO.getInvoiceLineItemDetailsId())
            .map(existingInvoiceLineItemDetails -> {
                invoiceLineItemDetailsMapper.partialUpdate(existingInvoiceLineItemDetails, invoiceLineItemDetailsDTO);

                return existingInvoiceLineItemDetails;
            })
            .flatMap(invoiceLineItemDetailsRepository::save)
            .map(invoiceLineItemDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoiceLineItemDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceLineItemDetails");
        return invoiceLineItemDetailsRepository.findAllBy(pageable).map(invoiceLineItemDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return invoiceLineItemDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoiceLineItemDetailsDTO> findOne(Long id) {
        log.debug("Request to get InvoiceLineItemDetails : {}", id);
        return invoiceLineItemDetailsRepository.findById(id).map(invoiceLineItemDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InvoiceLineItemDetails : {}", id);
        return invoiceLineItemDetailsRepository.deleteById(id);
    }
}
