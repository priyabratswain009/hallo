package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.ClaimsSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsSubmissionMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsSubmissionMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimsSubmissionMaster}.
 */
@Service
@Transactional
public class ClaimsSubmissionMasterServiceImpl implements ClaimsSubmissionMasterService {

    private final Logger log = LoggerFactory.getLogger(ClaimsSubmissionMasterServiceImpl.class);

    private final ClaimsSubmissionMasterRepository claimsSubmissionMasterRepository;

    private final ClaimsSubmissionMasterMapper claimsSubmissionMasterMapper;

    public ClaimsSubmissionMasterServiceImpl(
        ClaimsSubmissionMasterRepository claimsSubmissionMasterRepository,
        ClaimsSubmissionMasterMapper claimsSubmissionMasterMapper
    ) {
        this.claimsSubmissionMasterRepository = claimsSubmissionMasterRepository;
        this.claimsSubmissionMasterMapper = claimsSubmissionMasterMapper;
    }

    @Override
    public ClaimsSubmissionMasterDTO save(ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO) {
        log.debug("Request to save ClaimsSubmissionMaster : {}", claimsSubmissionMasterDTO);
        ClaimsSubmissionMaster claimsSubmissionMaster = claimsSubmissionMasterMapper.toEntity(claimsSubmissionMasterDTO);
        claimsSubmissionMaster = claimsSubmissionMasterRepository.save(claimsSubmissionMaster);
        return claimsSubmissionMasterMapper.toDto(claimsSubmissionMaster);
    }

    @Override
    public ClaimsSubmissionMasterDTO update(ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO) {
        log.debug("Request to save ClaimsSubmissionMaster : {}", claimsSubmissionMasterDTO);
        ClaimsSubmissionMaster claimsSubmissionMaster = claimsSubmissionMasterMapper.toEntity(claimsSubmissionMasterDTO);
        claimsSubmissionMaster = claimsSubmissionMasterRepository.save(claimsSubmissionMaster);
        return claimsSubmissionMasterMapper.toDto(claimsSubmissionMaster);
    }

    @Override
    public Optional<ClaimsSubmissionMasterDTO> partialUpdate(ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO) {
        log.debug("Request to partially update ClaimsSubmissionMaster : {}", claimsSubmissionMasterDTO);

        return claimsSubmissionMasterRepository
            .findById(claimsSubmissionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
            .map(existingClaimsSubmissionMaster -> {
                claimsSubmissionMasterMapper.partialUpdate(existingClaimsSubmissionMaster, claimsSubmissionMasterDTO);

                return existingClaimsSubmissionMaster;
            })
            .map(claimsSubmissionMasterRepository::save)
            .map(claimsSubmissionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimsSubmissionMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsSubmissionMasters");
        return claimsSubmissionMasterRepository.findAll(pageable).map(claimsSubmissionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimsSubmissionMasterDTO> findOne(Long id) {
        log.debug("Request to get ClaimsSubmissionMaster : {}", id);
        return claimsSubmissionMasterRepository.findById(id).map(claimsSubmissionMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimsSubmissionMaster : {}", id);
        claimsSubmissionMasterRepository.deleteById(id);
    }
}
