package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails;
import com.sunknowledge.dme.rcm.repository.ReceiptMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.ReceiptMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.ReceiptMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ReceiptMasterDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ReceiptMasterDetails}.
 */
@Service
@Transactional
public class ReceiptMasterDetailsServiceImpl implements ReceiptMasterDetailsService {

    private final Logger log = LoggerFactory.getLogger(ReceiptMasterDetailsServiceImpl.class);

    private final ReceiptMasterDetailsRepository receiptMasterDetailsRepository;

    private final ReceiptMasterDetailsMapper receiptMasterDetailsMapper;

    public ReceiptMasterDetailsServiceImpl(
        ReceiptMasterDetailsRepository receiptMasterDetailsRepository,
        ReceiptMasterDetailsMapper receiptMasterDetailsMapper
    ) {
        this.receiptMasterDetailsRepository = receiptMasterDetailsRepository;
        this.receiptMasterDetailsMapper = receiptMasterDetailsMapper;
    }

    @Override
    public ReceiptMasterDetailsDTO save(ReceiptMasterDetailsDTO receiptMasterDetailsDTO) {
        log.debug("Request to save ReceiptMasterDetails : {}", receiptMasterDetailsDTO);
        ReceiptMasterDetails receiptMasterDetails = receiptMasterDetailsMapper.toEntity(receiptMasterDetailsDTO);
        receiptMasterDetails = receiptMasterDetailsRepository.save(receiptMasterDetails);
        return receiptMasterDetailsMapper.toDto(receiptMasterDetails);
    }

    @Override
    public ReceiptMasterDetailsDTO update(ReceiptMasterDetailsDTO receiptMasterDetailsDTO) {
        log.debug("Request to save ReceiptMasterDetails : {}", receiptMasterDetailsDTO);
        ReceiptMasterDetails receiptMasterDetails = receiptMasterDetailsMapper.toEntity(receiptMasterDetailsDTO);
        receiptMasterDetails = receiptMasterDetailsRepository.save(receiptMasterDetails);
        return receiptMasterDetailsMapper.toDto(receiptMasterDetails);
    }

    @Override
    public Optional<ReceiptMasterDetailsDTO> partialUpdate(ReceiptMasterDetailsDTO receiptMasterDetailsDTO) {
        log.debug("Request to partially update ReceiptMasterDetails : {}", receiptMasterDetailsDTO);

        return receiptMasterDetailsRepository
            .findById(receiptMasterDetailsDTO.getReceiptId())
            .map(existingReceiptMasterDetails -> {
                receiptMasterDetailsMapper.partialUpdate(existingReceiptMasterDetails, receiptMasterDetailsDTO);

                return existingReceiptMasterDetails;
            })
            .map(receiptMasterDetailsRepository::save)
            .map(receiptMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReceiptMasterDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReceiptMasterDetails");
        return receiptMasterDetailsRepository.findAll(pageable).map(receiptMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReceiptMasterDetailsDTO> findOne(Long id) {
        log.debug("Request to get ReceiptMasterDetails : {}", id);
        return receiptMasterDetailsRepository.findById(id).map(receiptMasterDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReceiptMasterDetails : {}", id);
        receiptMasterDetailsRepository.deleteById(id);
    }
}
