package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsRepository;
import com.sunknowledge.dme.rcm.service.InvoicePostingDetailsService;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public InvoicePostingDetailsDTO save(InvoicePostingDetailsDTO invoicePostingDetailsDTO) {
        log.debug("Request to save InvoicePostingDetails : {}", invoicePostingDetailsDTO);
        InvoicePostingDetails invoicePostingDetails = invoicePostingDetailsMapper.toEntity(invoicePostingDetailsDTO);
        invoicePostingDetails = invoicePostingDetailsRepository.save(invoicePostingDetails);
        return invoicePostingDetailsMapper.toDto(invoicePostingDetails);
    }

    @Override
    public InvoicePostingDetailsDTO update(InvoicePostingDetailsDTO invoicePostingDetailsDTO) {
        log.debug("Request to save InvoicePostingDetails : {}", invoicePostingDetailsDTO);
        InvoicePostingDetails invoicePostingDetails = invoicePostingDetailsMapper.toEntity(invoicePostingDetailsDTO);
        invoicePostingDetails = invoicePostingDetailsRepository.save(invoicePostingDetails);
        return invoicePostingDetailsMapper.toDto(invoicePostingDetails);
    }

    @Override
    public Optional<InvoicePostingDetailsDTO> partialUpdate(InvoicePostingDetailsDTO invoicePostingDetailsDTO) {
        log.debug("Request to partially update InvoicePostingDetails : {}", invoicePostingDetailsDTO);

        return invoicePostingDetailsRepository
            .findById(invoicePostingDetailsDTO.getInvoiceLineItemPostingId())
            .map(existingInvoicePostingDetails -> {
                invoicePostingDetailsMapper.partialUpdate(existingInvoicePostingDetails, invoicePostingDetailsDTO);

                return existingInvoicePostingDetails;
            })
            .map(invoicePostingDetailsRepository::save)
            .map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InvoicePostingDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvoicePostingDetails");
        return invoicePostingDetailsRepository.findAll(pageable).map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoicePostingDetailsDTO> findOne(Long id) {
        log.debug("Request to get InvoicePostingDetails : {}", id);
        return invoicePostingDetailsRepository.findById(id).map(invoicePostingDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoicePostingDetails : {}", id);
        invoicePostingDetailsRepository.deleteById(id);
    }
}
