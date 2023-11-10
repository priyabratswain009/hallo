package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master;
import com.sunknowledge.dme.rcm.repository.ClaimsCOB835MasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCOB835MasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835MasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCOB835MasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimsCOB835Master}.
 */
@Service
@Transactional
public class ClaimsCOB835MasterServiceImpl implements ClaimsCOB835MasterService {

    private final Logger log = LoggerFactory.getLogger(ClaimsCOB835MasterServiceImpl.class);

    private final ClaimsCOB835MasterRepository claimsCOB835MasterRepository;

    private final ClaimsCOB835MasterMapper claimsCOB835MasterMapper;

    public ClaimsCOB835MasterServiceImpl(
        ClaimsCOB835MasterRepository claimsCOB835MasterRepository,
        ClaimsCOB835MasterMapper claimsCOB835MasterMapper
    ) {
        this.claimsCOB835MasterRepository = claimsCOB835MasterRepository;
        this.claimsCOB835MasterMapper = claimsCOB835MasterMapper;
    }

    @Override
    public ClaimsCOB835MasterDTO save(ClaimsCOB835MasterDTO claimsCOB835MasterDTO) {
        log.debug("Request to save ClaimsCOB835Master : {}", claimsCOB835MasterDTO);
        ClaimsCOB835Master claimsCOB835Master = claimsCOB835MasterMapper.toEntity(claimsCOB835MasterDTO);
        claimsCOB835Master = claimsCOB835MasterRepository.save(claimsCOB835Master);
        return claimsCOB835MasterMapper.toDto(claimsCOB835Master);
    }

    @Override
    public ClaimsCOB835MasterDTO update(ClaimsCOB835MasterDTO claimsCOB835MasterDTO) {
        log.debug("Request to save ClaimsCOB835Master : {}", claimsCOB835MasterDTO);
        ClaimsCOB835Master claimsCOB835Master = claimsCOB835MasterMapper.toEntity(claimsCOB835MasterDTO);
        claimsCOB835Master = claimsCOB835MasterRepository.save(claimsCOB835Master);
        return claimsCOB835MasterMapper.toDto(claimsCOB835Master);
    }

    @Override
    public Optional<ClaimsCOB835MasterDTO> partialUpdate(ClaimsCOB835MasterDTO claimsCOB835MasterDTO) {
        log.debug("Request to partially update ClaimsCOB835Master : {}", claimsCOB835MasterDTO);

        return claimsCOB835MasterRepository
            .findById(claimsCOB835MasterDTO.getClaimCob835MasterId())
            .map(existingClaimsCOB835Master -> {
                claimsCOB835MasterMapper.partialUpdate(existingClaimsCOB835Master, claimsCOB835MasterDTO);

                return existingClaimsCOB835Master;
            })
            .map(claimsCOB835MasterRepository::save)
            .map(claimsCOB835MasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimsCOB835MasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsCOB835Masters");
        return claimsCOB835MasterRepository.findAll(pageable).map(claimsCOB835MasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimsCOB835MasterDTO> findOne(Long id) {
        log.debug("Request to get ClaimsCOB835Master : {}", id);
        return claimsCOB835MasterRepository.findById(id).map(claimsCOB835MasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimsCOB835Master : {}", id);
        claimsCOB835MasterRepository.deleteById(id);
    }
}
