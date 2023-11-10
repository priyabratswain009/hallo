package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.repository.WipQueueDetailsRepository;
import com.sunknowledge.dme.rcm.service.WipQueueDetailsService;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipQueueDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WipQueueDetails}.
 */
@Service
@Transactional
public class WipQueueDetailsServiceImpl implements WipQueueDetailsService {

    private final Logger log = LoggerFactory.getLogger(WipQueueDetailsServiceImpl.class);

    private final WipQueueDetailsRepository wipQueueDetailsRepository;

    private final WipQueueDetailsMapper wipQueueDetailsMapper;

    public WipQueueDetailsServiceImpl(WipQueueDetailsRepository wipQueueDetailsRepository, WipQueueDetailsMapper wipQueueDetailsMapper) {
        this.wipQueueDetailsRepository = wipQueueDetailsRepository;
        this.wipQueueDetailsMapper = wipQueueDetailsMapper;
    }

    @Override
    public WipQueueDetailsDTO save(WipQueueDetailsDTO wipQueueDetailsDTO) {
        log.debug("Request to save WipQueueDetails : {}", wipQueueDetailsDTO);
        WipQueueDetails wipQueueDetails = wipQueueDetailsMapper.toEntity(wipQueueDetailsDTO);
        wipQueueDetails = wipQueueDetailsRepository.save(wipQueueDetails);
        return wipQueueDetailsMapper.toDto(wipQueueDetails);
    }

    @Override
    public WipQueueDetailsDTO update(WipQueueDetailsDTO wipQueueDetailsDTO) {
        log.debug("Request to save WipQueueDetails : {}", wipQueueDetailsDTO);
        WipQueueDetails wipQueueDetails = wipQueueDetailsMapper.toEntity(wipQueueDetailsDTO);
        wipQueueDetails = wipQueueDetailsRepository.save(wipQueueDetails);
        return wipQueueDetailsMapper.toDto(wipQueueDetails);
    }

    @Override
    public Optional<WipQueueDetailsDTO> partialUpdate(WipQueueDetailsDTO wipQueueDetailsDTO) {
        log.debug("Request to partially update WipQueueDetails : {}", wipQueueDetailsDTO);

        return wipQueueDetailsRepository
            .findById(wipQueueDetailsDTO.getWipQueueDetailsId())
            .map(existingWipQueueDetails -> {
                wipQueueDetailsMapper.partialUpdate(existingWipQueueDetails, wipQueueDetailsDTO);

                return existingWipQueueDetails;
            })
            .map(wipQueueDetailsRepository::save)
            .map(wipQueueDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WipQueueDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WipQueueDetails");
        return wipQueueDetailsRepository.findAll(pageable).map(wipQueueDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WipQueueDetailsDTO> findOne(Long id) {
        log.debug("Request to get WipQueueDetails : {}", id);
        return wipQueueDetailsRepository.findById(id).map(wipQueueDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WipQueueDetails : {}", id);
        wipQueueDetailsRepository.deleteById(id);
    }
}
