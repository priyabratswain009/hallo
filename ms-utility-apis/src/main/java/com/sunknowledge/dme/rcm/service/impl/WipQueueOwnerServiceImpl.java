package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.repository.WipQueueOwnerRepository;
import com.sunknowledge.dme.rcm.service.WipQueueOwnerService;
import com.sunknowledge.dme.rcm.service.dto.WipQueueOwnerDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipQueueOwnerMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WipQueueOwner}.
 */
@Service
@Transactional
public class WipQueueOwnerServiceImpl implements WipQueueOwnerService {

    private final Logger log = LoggerFactory.getLogger(WipQueueOwnerServiceImpl.class);

    private final WipQueueOwnerRepository wipQueueOwnerRepository;

    private final WipQueueOwnerMapper wipQueueOwnerMapper;

    public WipQueueOwnerServiceImpl(WipQueueOwnerRepository wipQueueOwnerRepository, WipQueueOwnerMapper wipQueueOwnerMapper) {
        this.wipQueueOwnerRepository = wipQueueOwnerRepository;
        this.wipQueueOwnerMapper = wipQueueOwnerMapper;
    }

    @Override
    public WipQueueOwnerDTO save(WipQueueOwnerDTO wipQueueOwnerDTO) {
        log.debug("Request to save WipQueueOwner : {}", wipQueueOwnerDTO);
        WipQueueOwner wipQueueOwner = wipQueueOwnerMapper.toEntity(wipQueueOwnerDTO);
        wipQueueOwner = wipQueueOwnerRepository.save(wipQueueOwner);
        return wipQueueOwnerMapper.toDto(wipQueueOwner);
    }

    @Override
    public WipQueueOwnerDTO update(WipQueueOwnerDTO wipQueueOwnerDTO) {
        log.debug("Request to save WipQueueOwner : {}", wipQueueOwnerDTO);
        WipQueueOwner wipQueueOwner = wipQueueOwnerMapper.toEntity(wipQueueOwnerDTO);
        wipQueueOwner = wipQueueOwnerRepository.save(wipQueueOwner);
        return wipQueueOwnerMapper.toDto(wipQueueOwner);
    }

    @Override
    public Optional<WipQueueOwnerDTO> partialUpdate(WipQueueOwnerDTO wipQueueOwnerDTO) {
        log.debug("Request to partially update WipQueueOwner : {}", wipQueueOwnerDTO);

        return wipQueueOwnerRepository
            .findById(wipQueueOwnerDTO.getWipQueueOwnerId())
            .map(existingWipQueueOwner -> {
                wipQueueOwnerMapper.partialUpdate(existingWipQueueOwner, wipQueueOwnerDTO);

                return existingWipQueueOwner;
            })
            .map(wipQueueOwnerRepository::save)
            .map(wipQueueOwnerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WipQueueOwnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WipQueueOwners");
        return wipQueueOwnerRepository.findAll(pageable).map(wipQueueOwnerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WipQueueOwnerDTO> findOne(Long id) {
        log.debug("Request to get WipQueueOwner : {}", id);
        return wipQueueOwnerRepository.findById(id).map(wipQueueOwnerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WipQueueOwner : {}", id);
        wipQueueOwnerRepository.deleteById(id);
    }
}
