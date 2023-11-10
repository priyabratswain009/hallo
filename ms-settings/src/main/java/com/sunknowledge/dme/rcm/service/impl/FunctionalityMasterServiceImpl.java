package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.repository.FunctionalityMasterRepository;
import com.sunknowledge.dme.rcm.service.FunctionalityMasterService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FunctionalityMaster}.
 */
@Service
@Transactional
public class FunctionalityMasterServiceImpl implements FunctionalityMasterService {

    private final Logger log = LoggerFactory.getLogger(FunctionalityMasterServiceImpl.class);

    private final FunctionalityMasterRepository functionalityMasterRepository;

    private final FunctionalityMasterMapper functionalityMasterMapper;

    public FunctionalityMasterServiceImpl(
        FunctionalityMasterRepository functionalityMasterRepository,
        FunctionalityMasterMapper functionalityMasterMapper
    ) {
        this.functionalityMasterRepository = functionalityMasterRepository;
        this.functionalityMasterMapper = functionalityMasterMapper;
    }

    @Override
    public FunctionalityMasterDTO save(FunctionalityMasterDTO functionalityMasterDTO) {
        log.debug("Request to save FunctionalityMaster : {}", functionalityMasterDTO);
        FunctionalityMaster functionalityMaster = functionalityMasterMapper.toEntity(functionalityMasterDTO);
        functionalityMaster = functionalityMasterRepository.save(functionalityMaster);
        return functionalityMasterMapper.toDto(functionalityMaster);
    }

    @Override
    public FunctionalityMasterDTO update(FunctionalityMasterDTO functionalityMasterDTO) {
        log.debug("Request to save FunctionalityMaster : {}", functionalityMasterDTO);
        FunctionalityMaster functionalityMaster = functionalityMasterMapper.toEntity(functionalityMasterDTO);
        functionalityMaster = functionalityMasterRepository.save(functionalityMaster);
        return functionalityMasterMapper.toDto(functionalityMaster);
    }

    @Override
    public Optional<FunctionalityMasterDTO> partialUpdate(FunctionalityMasterDTO functionalityMasterDTO) {
        log.debug("Request to partially update FunctionalityMaster : {}", functionalityMasterDTO);

        return functionalityMasterRepository
            .findById(functionalityMasterDTO.getFunctionalityId())
            .map(existingFunctionalityMaster -> {
                functionalityMasterMapper.partialUpdate(existingFunctionalityMaster, functionalityMasterDTO);

                return existingFunctionalityMaster;
            })
            .map(functionalityMasterRepository::save)
            .map(functionalityMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FunctionalityMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FunctionalityMasters");
        return functionalityMasterRepository.findAll(pageable).map(functionalityMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FunctionalityMasterDTO> findOne(Long id) {
        log.debug("Request to get FunctionalityMaster : {}", id);
        return functionalityMasterRepository.findById(id).map(functionalityMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FunctionalityMaster : {}", id);
        functionalityMasterRepository.deleteById(id);
    }
}
