package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.repository.StopReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.StopReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.StopReasonMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StopReasonMaster}.
 */
@Service
@Transactional
public class StopReasonMasterServiceImpl implements StopReasonMasterService {

    private final Logger log = LoggerFactory.getLogger(StopReasonMasterServiceImpl.class);

    private final StopReasonMasterRepository stopReasonMasterRepository;

    private final StopReasonMasterMapper stopReasonMasterMapper;

    public StopReasonMasterServiceImpl(
        StopReasonMasterRepository stopReasonMasterRepository,
        StopReasonMasterMapper stopReasonMasterMapper
    ) {
        this.stopReasonMasterRepository = stopReasonMasterRepository;
        this.stopReasonMasterMapper = stopReasonMasterMapper;
    }

    @Override
    public StopReasonMasterDTO save(StopReasonMasterDTO stopReasonMasterDTO) {
        log.debug("Request to save StopReasonMaster : {}", stopReasonMasterDTO);
        StopReasonMaster stopReasonMaster = stopReasonMasterMapper.toEntity(stopReasonMasterDTO);
        stopReasonMaster = stopReasonMasterRepository.save(stopReasonMaster);
        return stopReasonMasterMapper.toDto(stopReasonMaster);
    }

    @Override
    public StopReasonMasterDTO update(StopReasonMasterDTO stopReasonMasterDTO) {
        log.debug("Request to save StopReasonMaster : {}", stopReasonMasterDTO);
        StopReasonMaster stopReasonMaster = stopReasonMasterMapper.toEntity(stopReasonMasterDTO);
        stopReasonMaster = stopReasonMasterRepository.save(stopReasonMaster);
        return stopReasonMasterMapper.toDto(stopReasonMaster);
    }

    @Override
    public Optional<StopReasonMasterDTO> partialUpdate(StopReasonMasterDTO stopReasonMasterDTO) {
        log.debug("Request to partially update StopReasonMaster : {}", stopReasonMasterDTO);

        return stopReasonMasterRepository
            .findById(stopReasonMasterDTO.getStopReasonId())
            .map(existingStopReasonMaster -> {
                stopReasonMasterMapper.partialUpdate(existingStopReasonMaster, stopReasonMasterDTO);

                return existingStopReasonMaster;
            })
            .map(stopReasonMasterRepository::save)
            .map(stopReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StopReasonMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StopReasonMasters");
        return stopReasonMasterRepository.findAll(pageable).map(stopReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StopReasonMasterDTO> findOne(Long id) {
        log.debug("Request to get StopReasonMaster : {}", id);
        return stopReasonMasterRepository.findById(id).map(stopReasonMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StopReasonMaster : {}", id);
        stopReasonMasterRepository.deleteById(id);
    }
}
