package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.FacilityMaster;
import com.sunknowledge.dme.rcm.repository.FacilityMasterRepository;
import com.sunknowledge.dme.rcm.service.FacilityMasterService;
import com.sunknowledge.dme.rcm.service.dto.FacilityMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FacilityMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FacilityMaster}.
 */
@Service
@Transactional
public class FacilityMasterServiceImpl implements FacilityMasterService {

    private final Logger log = LoggerFactory.getLogger(FacilityMasterServiceImpl.class);

    private final FacilityMasterRepository facilityMasterRepository;

    private final FacilityMasterMapper facilityMasterMapper;

    public FacilityMasterServiceImpl(FacilityMasterRepository facilityMasterRepository, FacilityMasterMapper facilityMasterMapper) {
        this.facilityMasterRepository = facilityMasterRepository;
        this.facilityMasterMapper = facilityMasterMapper;
    }

    @Override
    public FacilityMasterDTO save(FacilityMasterDTO facilityMasterDTO) {
        log.debug("Request to save FacilityMaster : {}", facilityMasterDTO);
        FacilityMaster facilityMaster = facilityMasterMapper.toEntity(facilityMasterDTO);
        facilityMaster = facilityMasterRepository.save(facilityMaster);
        return facilityMasterMapper.toDto(facilityMaster);
    }

    @Override
    public FacilityMasterDTO update(FacilityMasterDTO facilityMasterDTO) {
        log.debug("Request to save FacilityMaster : {}", facilityMasterDTO);
        FacilityMaster facilityMaster = facilityMasterMapper.toEntity(facilityMasterDTO);
        facilityMaster = facilityMasterRepository.save(facilityMaster);
        return facilityMasterMapper.toDto(facilityMaster);
    }

    @Override
    public Optional<FacilityMasterDTO> partialUpdate(FacilityMasterDTO facilityMasterDTO) {
        log.debug("Request to partially update FacilityMaster : {}", facilityMasterDTO);

        return facilityMasterRepository
            .findById(facilityMasterDTO.getFacilityId())
            .map(existingFacilityMaster -> {
                facilityMasterMapper.partialUpdate(existingFacilityMaster, facilityMasterDTO);

                return existingFacilityMaster;
            })
            .map(facilityMasterRepository::save)
            .map(facilityMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FacilityMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FacilityMasters");
        return facilityMasterRepository.findAll(pageable).map(facilityMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FacilityMasterDTO> findOne(Long id) {
        log.debug("Request to get FacilityMaster : {}", id);
        return facilityMasterRepository.findById(id).map(facilityMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FacilityMaster : {}", id);
        facilityMasterRepository.deleteById(id);
    }
}
