package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DepositMasterDetails;
import com.sunknowledge.dme.rcm.repository.DepositMasterDetailsRepository;
import com.sunknowledge.dme.rcm.service.DepositMasterDetailsService;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DepositMasterDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DepositMasterDetails}.
 */
@Service
@Transactional
public class DepositMasterDetailsServiceImpl implements DepositMasterDetailsService {

    private final Logger log = LoggerFactory.getLogger(DepositMasterDetailsServiceImpl.class);

    private final DepositMasterDetailsRepository depositMasterDetailsRepository;

    private final DepositMasterDetailsMapper depositMasterDetailsMapper;

    public DepositMasterDetailsServiceImpl(
        DepositMasterDetailsRepository depositMasterDetailsRepository,
        DepositMasterDetailsMapper depositMasterDetailsMapper
    ) {
        this.depositMasterDetailsRepository = depositMasterDetailsRepository;
        this.depositMasterDetailsMapper = depositMasterDetailsMapper;
    }

    @Override
    public DepositMasterDetailsDTO save(DepositMasterDetailsDTO depositMasterDetailsDTO) {
        log.debug("Request to save DepositMasterDetails : {}", depositMasterDetailsDTO);
        DepositMasterDetails depositMasterDetails = depositMasterDetailsMapper.toEntity(depositMasterDetailsDTO);
        depositMasterDetails = depositMasterDetailsRepository.save(depositMasterDetails);
        return depositMasterDetailsMapper.toDto(depositMasterDetails);
    }

    @Override
    public DepositMasterDetailsDTO update(DepositMasterDetailsDTO depositMasterDetailsDTO) {
        log.debug("Request to save DepositMasterDetails : {}", depositMasterDetailsDTO);
        DepositMasterDetails depositMasterDetails = depositMasterDetailsMapper.toEntity(depositMasterDetailsDTO);
        depositMasterDetails = depositMasterDetailsRepository.save(depositMasterDetails);
        return depositMasterDetailsMapper.toDto(depositMasterDetails);
    }

    @Override
    public Optional<DepositMasterDetailsDTO> partialUpdate(DepositMasterDetailsDTO depositMasterDetailsDTO) {
        log.debug("Request to partially update DepositMasterDetails : {}", depositMasterDetailsDTO);

        return depositMasterDetailsRepository
            .findById(depositMasterDetailsDTO.getDepositId())
            .map(existingDepositMasterDetails -> {
                depositMasterDetailsMapper.partialUpdate(existingDepositMasterDetails, depositMasterDetailsDTO);

                return existingDepositMasterDetails;
            })
            .map(depositMasterDetailsRepository::save)
            .map(depositMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DepositMasterDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DepositMasterDetails");
        return depositMasterDetailsRepository.findAll(pageable).map(depositMasterDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepositMasterDetailsDTO> findOne(Long id) {
        log.debug("Request to get DepositMasterDetails : {}", id);
        return depositMasterDetailsRepository.findById(id).map(depositMasterDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DepositMasterDetails : {}", id);
        depositMasterDetailsRepository.deleteById(id);
    }
}
