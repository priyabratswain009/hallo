package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMaster;
import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimProgramMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimProgramMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimProgramMaster}.
 */
@Service
@Transactional
public class ClaimProgramMasterServiceImpl implements ClaimProgramMasterService {

    private final Logger log = LoggerFactory.getLogger(ClaimProgramMasterServiceImpl.class);

    private final ClaimProgramMasterRepository claimProgramMasterRepository;

    private final ClaimProgramMasterMapper claimProgramMasterMapper;

    public ClaimProgramMasterServiceImpl(
        ClaimProgramMasterRepository claimProgramMasterRepository,
        ClaimProgramMasterMapper claimProgramMasterMapper
    ) {
        this.claimProgramMasterRepository = claimProgramMasterRepository;
        this.claimProgramMasterMapper = claimProgramMasterMapper;
    }

    @Override
    public ClaimProgramMasterDTO save(ClaimProgramMasterDTO claimProgramMasterDTO) {
        log.debug("Request to save ClaimProgramMaster : {}", claimProgramMasterDTO);
        ClaimProgramMaster claimProgramMaster = claimProgramMasterMapper.toEntity(claimProgramMasterDTO);
        claimProgramMaster = claimProgramMasterRepository.save(claimProgramMaster);
        return claimProgramMasterMapper.toDto(claimProgramMaster);
    }

    @Override
    public ClaimProgramMasterDTO update(ClaimProgramMasterDTO claimProgramMasterDTO) {
        log.debug("Request to save ClaimProgramMaster : {}", claimProgramMasterDTO);
        ClaimProgramMaster claimProgramMaster = claimProgramMasterMapper.toEntity(claimProgramMasterDTO);
        claimProgramMaster = claimProgramMasterRepository.save(claimProgramMaster);
        return claimProgramMasterMapper.toDto(claimProgramMaster);
    }

    @Override
    public Optional<ClaimProgramMasterDTO> partialUpdate(ClaimProgramMasterDTO claimProgramMasterDTO) {
        log.debug("Request to partially update ClaimProgramMaster : {}", claimProgramMasterDTO);

        return claimProgramMasterRepository
            .findById(claimProgramMasterDTO.getClaimProgramMasterId())
            .map(existingClaimProgramMaster -> {
                claimProgramMasterMapper.partialUpdate(existingClaimProgramMaster, claimProgramMasterDTO);

                return existingClaimProgramMaster;
            })
            .map(claimProgramMasterRepository::save)
            .map(claimProgramMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimProgramMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimProgramMasters");
        return claimProgramMasterRepository.findAll(pageable).map(claimProgramMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimProgramMasterDTO> findOne(Long id) {
        log.debug("Request to get ClaimProgramMaster : {}", id);
        return claimProgramMasterRepository.findById(id).map(claimProgramMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimProgramMaster : {}", id);
        claimProgramMasterRepository.deleteById(id);
    }
}
