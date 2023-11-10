package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;
import com.sunknowledge.dme.rcm.service.SoItemTransactionDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoItemTransactionDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SoItemTransactionDetails}.
 */
@Service
@Transactional
public class SoItemTransactionDetailsServiceImpl implements SoItemTransactionDetailsService {

    private final Logger log = LoggerFactory.getLogger(SoItemTransactionDetailsServiceImpl.class);

    private final SoItemTransactionDetailsRepository soItemTransactionDetailsRepository;

    private final SoItemTransactionDetailsMapper soItemTransactionDetailsMapper;

    public SoItemTransactionDetailsServiceImpl(
        SoItemTransactionDetailsRepository soItemTransactionDetailsRepository,
        SoItemTransactionDetailsMapper soItemTransactionDetailsMapper
    ) {
        this.soItemTransactionDetailsRepository = soItemTransactionDetailsRepository;
        this.soItemTransactionDetailsMapper = soItemTransactionDetailsMapper;
    }

    @Override
    public SoItemTransactionDetailsDTO save(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO) {
        log.debug("Request to save SoItemTransactionDetails : {}", soItemTransactionDetailsDTO);
        SoItemTransactionDetails soItemTransactionDetails = soItemTransactionDetailsMapper.toEntity(soItemTransactionDetailsDTO);
        soItemTransactionDetails = soItemTransactionDetailsRepository.save(soItemTransactionDetails);
        return soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);
    }

    @Override
    public SoItemTransactionDetailsDTO update(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO) {
        log.debug("Request to update SoItemTransactionDetails : {}", soItemTransactionDetailsDTO);
        SoItemTransactionDetails soItemTransactionDetails = soItemTransactionDetailsMapper.toEntity(soItemTransactionDetailsDTO);
        soItemTransactionDetails = soItemTransactionDetailsRepository.save(soItemTransactionDetails);
        return soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);
    }

    @Override
    public Optional<SoItemTransactionDetailsDTO> partialUpdate(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO) {
        log.debug("Request to partially update SoItemTransactionDetails : {}", soItemTransactionDetailsDTO);

        return soItemTransactionDetailsRepository
            .findById(soItemTransactionDetailsDTO.getSoItemTransactionDetailsId())
            .map(existingSoItemTransactionDetails -> {
                soItemTransactionDetailsMapper.partialUpdate(existingSoItemTransactionDetails, soItemTransactionDetailsDTO);

                return existingSoItemTransactionDetails;
            })
            .map(soItemTransactionDetailsRepository::save)
            .map(soItemTransactionDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SoItemTransactionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoItemTransactionDetails");
        return soItemTransactionDetailsRepository.findAll(pageable).map(soItemTransactionDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SoItemTransactionDetailsDTO> findOne(Long id) {
        log.debug("Request to get SoItemTransactionDetails : {}", id);
        return soItemTransactionDetailsRepository.findById(id).map(soItemTransactionDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SoItemTransactionDetails : {}", id);
        soItemTransactionDetailsRepository.deleteById(id);
    }
}
