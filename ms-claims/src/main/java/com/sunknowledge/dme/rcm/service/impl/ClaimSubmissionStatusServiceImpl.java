package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;
import com.sunknowledge.dme.rcm.service.ClaimSubmissionStatusService;
import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimSubmissionStatusMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimSubmissionStatus}.
 */
@Service
@Transactional
public class ClaimSubmissionStatusServiceImpl implements ClaimSubmissionStatusService {

    private final Logger log = LoggerFactory.getLogger(ClaimSubmissionStatusServiceImpl.class);

    private final ClaimSubmissionStatusRepository claimSubmissionStatusRepository;

    private final ClaimSubmissionStatusMapper claimSubmissionStatusMapper;

    public ClaimSubmissionStatusServiceImpl(
        ClaimSubmissionStatusRepository claimSubmissionStatusRepository,
        ClaimSubmissionStatusMapper claimSubmissionStatusMapper
    ) {
        this.claimSubmissionStatusRepository = claimSubmissionStatusRepository;
        this.claimSubmissionStatusMapper = claimSubmissionStatusMapper;
    }

    @Override
    public ClaimSubmissionStatusDTO save(ClaimSubmissionStatusDTO claimSubmissionStatusDTO) {
        log.debug("Request to save ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);
        ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusMapper.toEntity(claimSubmissionStatusDTO);
        claimSubmissionStatus = claimSubmissionStatusRepository.save(claimSubmissionStatus);
        return claimSubmissionStatusMapper.toDto(claimSubmissionStatus);
    }

    @Override
    public ClaimSubmissionStatusDTO update(ClaimSubmissionStatusDTO claimSubmissionStatusDTO) {
        log.debug("Request to save ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);
        ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusMapper.toEntity(claimSubmissionStatusDTO);
        claimSubmissionStatus = claimSubmissionStatusRepository.save(claimSubmissionStatus);
        return claimSubmissionStatusMapper.toDto(claimSubmissionStatus);
    }

    @Override
    public Optional<ClaimSubmissionStatusDTO> partialUpdate(ClaimSubmissionStatusDTO claimSubmissionStatusDTO) {
        log.debug("Request to partially update ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);

        return claimSubmissionStatusRepository
            .findById(claimSubmissionStatusDTO.getClaimStatusId())
            .map(existingClaimSubmissionStatus -> {
                claimSubmissionStatusMapper.partialUpdate(existingClaimSubmissionStatus, claimSubmissionStatusDTO);

                return existingClaimSubmissionStatus;
            })
            .map(claimSubmissionStatusRepository::save)
            .map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimSubmissionStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimSubmissionStatuses");
        return claimSubmissionStatusRepository.findAll(pageable).map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimSubmissionStatusDTO> findOne(Long id) {
        log.debug("Request to get ClaimSubmissionStatus : {}", id);
        return claimSubmissionStatusRepository.findById(id).map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimSubmissionStatus : {}", id);
        claimSubmissionStatusRepository.deleteById(id);
    }
}
