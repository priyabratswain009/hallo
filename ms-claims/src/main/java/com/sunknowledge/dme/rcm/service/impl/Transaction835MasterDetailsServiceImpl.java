package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails;
import com.sunknowledge.dme.rcm.repository.Transaction835MasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.Transaction835MasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.Transaction835MasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.Transaction835MasterDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Transaction835MasterDetails}.
 */
@Service
@Transactional
public class Transaction835MasterDetailsServiceImpl implements Transaction835MasterDetailsService {

    private final Logger log = LoggerFactory.getLogger(Transaction835MasterDetailsServiceImpl.class);

    private final Transaction835MasterDetailsRepository transaction835MasterDetailsRepository;

    private final Transaction835MasterDetailsMapper transaction835MasterDetailsMapper;

    public Transaction835MasterDetailsServiceImpl(
        Transaction835MasterDetailsRepository transaction835MasterDetailsRepository,
        Transaction835MasterDetailsMapper transaction835MasterDetailsMapper
    ) {
        this.transaction835MasterDetailsRepository = transaction835MasterDetailsRepository;
        this.transaction835MasterDetailsMapper = transaction835MasterDetailsMapper;
    }

    @Override
    public Transaction835MasterDetailsDTO save(Transaction835MasterDetailsDTO transaction835MasterDetailsDTO) {
        log.debug("Request to save Transaction835MasterDetails : {}", transaction835MasterDetailsDTO);
        Transaction835MasterDetails transaction835MasterDetails = transaction835MasterDetailsMapper.toEntity(
            transaction835MasterDetailsDTO
        );
        transaction835MasterDetails = transaction835MasterDetailsRepository.save(transaction835MasterDetails);
        return transaction835MasterDetailsMapper.toDto(transaction835MasterDetails);
    }

    @Override
    public Transaction835MasterDetailsDTO update(Transaction835MasterDetailsDTO transaction835MasterDetailsDTO) {
        log.debug("Request to save Transaction835MasterDetails : {}", transaction835MasterDetailsDTO);
        Transaction835MasterDetails transaction835MasterDetails = transaction835MasterDetailsMapper.toEntity(
            transaction835MasterDetailsDTO
        );
        transaction835MasterDetails = transaction835MasterDetailsRepository.save(transaction835MasterDetails);
        return transaction835MasterDetailsMapper.toDto(transaction835MasterDetails);
    }

    @Override
    public Optional<Transaction835MasterDetailsDTO> partialUpdate(Transaction835MasterDetailsDTO transaction835MasterDetailsDTO) {
        log.debug("Request to partially update Transaction835MasterDetails : {}", transaction835MasterDetailsDTO);

        return transaction835MasterDetailsRepository
            .findById(transaction835MasterDetailsDTO.getTransactionId())
            .map(existingTransaction835MasterDetails -> {
                transaction835MasterDetailsMapper.partialUpdate(existingTransaction835MasterDetails, transaction835MasterDetailsDTO);

                return existingTransaction835MasterDetails;
            })
            .map(transaction835MasterDetailsRepository::save)
            .map(transaction835MasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Transaction835MasterDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transaction835MasterDetails");
        return transaction835MasterDetailsRepository.findAll(pageable).map(transaction835MasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction835MasterDetailsDTO> findOne(Long id) {
        log.debug("Request to get Transaction835MasterDetails : {}", id);
        return transaction835MasterDetailsRepository.findById(id).map(transaction835MasterDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transaction835MasterDetails : {}", id);
        transaction835MasterDetailsRepository.deleteById(id);
    }
}
