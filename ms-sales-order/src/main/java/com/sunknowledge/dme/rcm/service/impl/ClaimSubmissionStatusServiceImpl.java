package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;
import com.sunknowledge.dme.rcm.service.ClaimSubmissionStatusService;
import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimSubmissionStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ClaimSubmissionStatusDTO> save(ClaimSubmissionStatusDTO claimSubmissionStatusDTO) {
        log.debug("Request to save ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);
        return claimSubmissionStatusRepository
            .save(claimSubmissionStatusMapper.toEntity(claimSubmissionStatusDTO))
            .map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    public Mono<ClaimSubmissionStatusDTO> update(ClaimSubmissionStatusDTO claimSubmissionStatusDTO) {
        log.debug("Request to save ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);
        return claimSubmissionStatusRepository
            .save(claimSubmissionStatusMapper.toEntity(claimSubmissionStatusDTO))
            .map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    public Mono<ClaimSubmissionStatusDTO> partialUpdate(ClaimSubmissionStatusDTO claimSubmissionStatusDTO) {
        log.debug("Request to partially update ClaimSubmissionStatus : {}", claimSubmissionStatusDTO);

        return claimSubmissionStatusRepository
            .findById(claimSubmissionStatusDTO.getClaimStatusId())
            .map(existingClaimSubmissionStatus -> {
                claimSubmissionStatusMapper.partialUpdate(existingClaimSubmissionStatus, claimSubmissionStatusDTO);

                return existingClaimSubmissionStatus;
            })
            .flatMap(claimSubmissionStatusRepository::save)
            .map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ClaimSubmissionStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimSubmissionStatuses");
        return claimSubmissionStatusRepository.findAllBy(pageable).map(claimSubmissionStatusMapper::toDto);
    }

    public Mono<Long> countAll() {
        return claimSubmissionStatusRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ClaimSubmissionStatusDTO> findOne(Long id) {
        log.debug("Request to get ClaimSubmissionStatus : {}", id);
        return claimSubmissionStatusRepository.findById(id).map(claimSubmissionStatusMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ClaimSubmissionStatus : {}", id);
        return claimSubmissionStatusRepository.deleteById(id);
    }
}
