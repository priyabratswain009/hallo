package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.repository.WipStatusMasterRepository;
import com.sunknowledge.dme.rcm.service.WipStatusMasterService;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipStatusMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WipStatusMaster}.
 */
@Service
@Transactional
public class WipStatusMasterServiceImpl implements WipStatusMasterService {

    private final Logger log = LoggerFactory.getLogger(WipStatusMasterServiceImpl.class);

    private final WipStatusMasterRepository wipStatusMasterRepository;

    private final WipStatusMasterMapper wipStatusMasterMapper;

    public WipStatusMasterServiceImpl(WipStatusMasterRepository wipStatusMasterRepository, WipStatusMasterMapper wipStatusMasterMapper) {
        this.wipStatusMasterRepository = wipStatusMasterRepository;
        this.wipStatusMasterMapper = wipStatusMasterMapper;
    }

    @Override
    public WipStatusMasterDTO save(WipStatusMasterDTO wipStatusMasterDTO) {
        log.debug("Request to save WipStatusMaster : {}", wipStatusMasterDTO);
        WipStatusMaster wipStatusMaster = wipStatusMasterMapper.toEntity(wipStatusMasterDTO);
        wipStatusMaster = wipStatusMasterRepository.save(wipStatusMaster);
        return wipStatusMasterMapper.toDto(wipStatusMaster);
    }

    @Override
    public WipStatusMasterDTO update(WipStatusMasterDTO wipStatusMasterDTO) {
        log.debug("Request to save WipStatusMaster : {}", wipStatusMasterDTO);
        WipStatusMaster wipStatusMaster = wipStatusMasterMapper.toEntity(wipStatusMasterDTO);
        wipStatusMaster = wipStatusMasterRepository.save(wipStatusMaster);
        return wipStatusMasterMapper.toDto(wipStatusMaster);
    }

    @Override
    public Optional<WipStatusMasterDTO> partialUpdate(WipStatusMasterDTO wipStatusMasterDTO) {
        log.debug("Request to partially update WipStatusMaster : {}", wipStatusMasterDTO);

        return wipStatusMasterRepository
            .findById(wipStatusMasterDTO.getWipStatusId())
            .map(existingWipStatusMaster -> {
                wipStatusMasterMapper.partialUpdate(existingWipStatusMaster, wipStatusMasterDTO);

                return existingWipStatusMaster;
            })
            .map(wipStatusMasterRepository::save)
            .map(wipStatusMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WipStatusMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WipStatusMasters");
        return wipStatusMasterRepository.findAll(pageable).map(wipStatusMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WipStatusMasterDTO> findOne(Long id) {
        log.debug("Request to get WipStatusMaster : {}", id);
        return wipStatusMasterRepository.findById(id).map(wipStatusMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WipStatusMaster : {}", id);
        wipStatusMasterRepository.deleteById(id);
    }
}
