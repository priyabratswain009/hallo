package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoiceMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceMasterDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public InvoiceMasterDetailsDTO save(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO) {
        log.debug("Request to save InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);
        InvoiceMasterDetails invoiceMasterDetails = invoiceMasterDetailsMapper.toEntity(invoiceMasterDetailsDTO);
        invoiceMasterDetails = invoiceMasterDetailsRepository.save(invoiceMasterDetails);
        return invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);
    }

    @Override
    public InvoiceMasterDetailsDTO update(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO) {
        log.debug("Request to save InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);
        InvoiceMasterDetails invoiceMasterDetails = invoiceMasterDetailsMapper.toEntity(invoiceMasterDetailsDTO);
        invoiceMasterDetails = invoiceMasterDetailsRepository.save(invoiceMasterDetails);
        return invoiceMasterDetailsMapper.toDto(invoiceMasterDetails);
    }

    @Override
    public Optional<InvoiceMasterDetailsDTO> partialUpdate(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO) {
        log.debug("Request to partially update InvoiceMasterDetails : {}", invoiceMasterDetailsDTO);

        return invoiceMasterDetailsRepository
            .findById(invoiceMasterDetailsDTO.getInvoiceId())
            .map(existingInvoiceMasterDetails -> {
                invoiceMasterDetailsMapper.partialUpdate(existingInvoiceMasterDetails, invoiceMasterDetailsDTO);

                return existingInvoiceMasterDetails;
            })
            .map(invoiceMasterDetailsRepository::save)
            .map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceMasterDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceMasterDetails");
        return invoiceMasterDetailsRepository.findAll(pageable).map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceMasterDetailsDTO> findOne(Long id) {
        log.debug("Request to get InvoiceMasterDetails : {}", id);
        return invoiceMasterDetailsRepository.findById(id).map(invoiceMasterDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceMasterDetails : {}", id);
        invoiceMasterDetailsRepository.deleteById(id);
    }
}
