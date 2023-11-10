package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.repository.HoldReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.HoldReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.HoldReasonMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HoldReasonMaster}.
 */
@Service
@Transactional
public class HoldReasonMasterServiceImpl implements HoldReasonMasterService {

    private final Logger log = LoggerFactory.getLogger(HoldReasonMasterServiceImpl.class);

    private final HoldReasonMasterRepository holdReasonMasterRepository;

    private final HoldReasonMasterMapper holdReasonMasterMapper;

    public HoldReasonMasterServiceImpl(
        HoldReasonMasterRepository holdReasonMasterRepository,
        HoldReasonMasterMapper holdReasonMasterMapper
    ) {
        this.holdReasonMasterRepository = holdReasonMasterRepository;
        this.holdReasonMasterMapper = holdReasonMasterMapper;
    }

    @Override
    public HoldReasonMasterDTO save(HoldReasonMasterDTO holdReasonMasterDTO) {
        log.debug("Request to save HoldReasonMaster : {}", holdReasonMasterDTO);
        HoldReasonMaster holdReasonMaster = holdReasonMasterMapper.toEntity(holdReasonMasterDTO);
        holdReasonMaster = holdReasonMasterRepository.save(holdReasonMaster);
        return holdReasonMasterMapper.toDto(holdReasonMaster);
    }

    @Override
    public HoldReasonMasterDTO update(HoldReasonMasterDTO holdReasonMasterDTO) {
        log.debug("Request to save HoldReasonMaster : {}", holdReasonMasterDTO);
        HoldReasonMaster holdReasonMaster = holdReasonMasterMapper.toEntity(holdReasonMasterDTO);
        holdReasonMaster = holdReasonMasterRepository.save(holdReasonMaster);
        return holdReasonMasterMapper.toDto(holdReasonMaster);
    }

    @Override
    public Optional<HoldReasonMasterDTO> partialUpdate(HoldReasonMasterDTO holdReasonMasterDTO) {
        log.debug("Request to partially update HoldReasonMaster : {}", holdReasonMasterDTO);

        return holdReasonMasterRepository
            .findById(holdReasonMasterDTO.getHoldReasonId())
            .map(existingHoldReasonMaster -> {
                holdReasonMasterMapper.partialUpdate(existingHoldReasonMaster, holdReasonMasterDTO);

                return existingHoldReasonMaster;
            })
            .map(holdReasonMasterRepository::save)
            .map(holdReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HoldReasonMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HoldReasonMasters");
        return holdReasonMasterRepository.findAll(pageable).map(holdReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HoldReasonMasterDTO> findOne(Long id) {
        log.debug("Request to get HoldReasonMaster : {}", id);
        return holdReasonMasterRepository.findById(id).map(holdReasonMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HoldReasonMaster : {}", id);
        holdReasonMasterRepository.deleteById(id);
    }
}
