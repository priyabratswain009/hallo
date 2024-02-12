package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceMasterDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InvoiceMasterDetails}.
 */
@Service
@Transactional
public class InvoiceMasterDetailsServiceImpl implements InvoiceMasterDetailsService {

    private final Logger log = LoggerFactory.getLogger(InvoiceMasterDetailsServiceImpl.class);

    private final InvoiceMasterDetailsRepository invoiceMasterDetailsRepository;

    private final InvoiceMasterDetailsMapper invoiceMasterDetailsMapper;

    public InvoiceMasterDetailsServiceImpl(
        InvoiceMasterDetailsRepository invoiceMasterDetailsRepository,
        InvoiceMasterDetailsMapper invoiceMasterDetailsMapper
    ) {
        this.invoiceMasterDetailsRepository = invoiceMasterDetailsRepository;
        this.invoiceMasterDetailsMapper = invoiceMasterDetailsMapper;
    }

    @Override
    public Mono<InvoiceMasterDetailsDTO> save(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO) {
        log.debug("Request to save InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);
        return invoiceMasterDetailsRepository
            .save(invoiceMasterDetailsMapper.toEntity(invoiceMasterDetailsDTO))
            .map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    public Mono<InvoiceMasterDetailsDTO> update(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO) {
        log.debug("Request to update InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);
        return invoiceMasterDetailsRepository
            .save(invoiceMasterDetailsMapper.toEntity(invoiceMasterDetailsDTO))
            .map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    public Mono<InvoiceMasterDetailsDTO> partialUpdate(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO) {
        log.debug("Request to partially update InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);

        return invoiceMasterDetailsRepository
            .findById(invoiceMasterDetailsDTO.getInvoiceId())
            .map(existingInvoiceMasterDetails -> {
                invoiceMasterDetailsMapper.partialUpdate(existingInvoiceMasterDetails, invoiceMasterDetailsDTO);

                return existingInvoiceMasterDetails;
            })
            .flatMap(invoiceMasterDetailsRepository::save)
            .map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoiceMasterDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceMasterDetails");
        return invoiceMasterDetailsRepository.findAllBy(pageable).map(invoiceMasterDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return invoiceMasterDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoiceMasterDetailsDTO> findOne(Long id) {
        log.debug("Request to get InvoiceMasterDetails : {}", id);
        return invoiceMasterDetailsRepository.findById(id).map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InvoiceMasterDetails : {}", id);
        return invoiceMasterDetailsRepository.deleteById(id);
    }
}
