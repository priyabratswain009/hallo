package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimFormMaster;
import com.sunknowledge.dme.rcm.repository.ClaimFormMasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimFormMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimFormMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimFormMaster}.
 */
@Service
@Transactional
public class ClaimFormMasterServiceImpl implements ClaimFormMasterService {

    private final Logger log = LoggerFactory.getLogger(ClaimFormMasterServiceImpl.class);

    private final ClaimFormMasterRepository claimFormMasterRepository;

    private final ClaimFormMasterMapper claimFormMasterMapper;

    public ClaimFormMasterServiceImpl(ClaimFormMasterRepository claimFormMasterRepository, ClaimFormMasterMapper claimFormMasterMapper) {
        this.claimFormMasterRepository = claimFormMasterRepository;
        this.claimFormMasterMapper = claimFormMasterMapper;
    }

    @Override
    public ClaimFormMasterDTO save(ClaimFormMasterDTO claimFormMasterDTO) {
        log.debug("Request to save ClaimFormMaster : {}", claimFormMasterDTO);
        ClaimFormMaster claimFormMaster = claimFormMasterMapper.toEntity(claimFormMasterDTO);
        claimFormMaster = claimFormMasterRepository.save(claimFormMaster);
        return claimFormMasterMapper.toDto(claimFormMaster);
    }

    @Override
    public ClaimFormMasterDTO update(ClaimFormMasterDTO claimFormMasterDTO) {
        log.debug("Request to save ClaimFormMaster : {}", claimFormMasterDTO);
        ClaimFormMaster claimFormMaster = claimFormMasterMapper.toEntity(claimFormMasterDTO);
        claimFormMaster = claimFormMasterRepository.save(claimFormMaster);
        return claimFormMasterMapper.toDto(claimFormMaster);
    }

    @Override
    public Optional<ClaimFormMasterDTO> partialUpdate(ClaimFormMasterDTO claimFormMasterDTO) {
        log.debug("Request to partially update ClaimFormMaster : {}", claimFormMasterDTO);

        return claimFormMasterRepository
            .findById(claimFormMasterDTO.getClaimFormId())
            .map(existingClaimFormMaster -> {
                claimFormMasterMapper.partialUpdate(existingClaimFormMaster, claimFormMasterDTO);

                return existingClaimFormMaster;
            })
            .map(claimFormMasterRepository::save)
            .map(claimFormMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimFormMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimFormMasters");
        return claimFormMasterRepository.findAll(pageable).map(claimFormMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimFormMasterDTO> findOne(Long id) {
        log.debug("Request to get ClaimFormMaster : {}", id);
        return claimFormMasterRepository.findById(id).map(claimFormMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimFormMaster : {}", id);
        claimFormMasterRepository.deleteById(id);
    }
}
