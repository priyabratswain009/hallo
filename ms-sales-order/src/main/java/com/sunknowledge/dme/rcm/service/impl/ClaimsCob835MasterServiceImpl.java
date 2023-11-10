package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Master;
import com.sunknowledge.dme.rcm.repository.ClaimsCob835MasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCob835MasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835MasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCob835MasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ClaimsCob835Master}.
 */
@Service
@Transactional
public class ClaimsCob835MasterServiceImpl implements ClaimsCob835MasterService {

    private final Logger log = LoggerFactory.getLogger(ClaimsCob835MasterServiceImpl.class);

    private final ClaimsCob835MasterRepository claimsCob835MasterRepository;

    private final ClaimsCob835MasterMapper claimsCob835MasterMapper;

    public ClaimsCob835MasterServiceImpl(
        ClaimsCob835MasterRepository claimsCob835MasterRepository,
        ClaimsCob835MasterMapper claimsCob835MasterMapper
    ) {
        this.claimsCob835MasterRepository = claimsCob835MasterRepository;
        this.claimsCob835MasterMapper = claimsCob835MasterMapper;
    }

    @Override
    public Mono<ClaimsCob835MasterDTO> save(ClaimsCob835MasterDTO claimsCob835MasterDTO) {
        log.debug("Request to save ClaimsCob835Master : {}", claimsCob835MasterDTO);
        return claimsCob835MasterRepository
            .save(claimsCob835MasterMapper.toEntity(claimsCob835MasterDTO))
            .map(claimsCob835MasterMapper::toDto);
    }

    @Override
    public Mono<ClaimsCob835MasterDTO> update(ClaimsCob835MasterDTO claimsCob835MasterDTO) {
        log.debug("Request to save ClaimsCob835Master : {}", claimsCob835MasterDTO);
        return claimsCob835MasterRepository
            .save(claimsCob835MasterMapper.toEntity(claimsCob835MasterDTO))
            .map(claimsCob835MasterMapper::toDto);
    }

    @Override
    public Mono<ClaimsCob835MasterDTO> partialUpdate(ClaimsCob835MasterDTO claimsCob835MasterDTO) {
        log.debug("Request to partially update ClaimsCob835Master : {}", claimsCob835MasterDTO);

        return claimsCob835MasterRepository
            .findById(claimsCob835MasterDTO.getClaimCob835MasterId())
            .map(existingClaimsCob835Master -> {
                claimsCob835MasterMapper.partialUpdate(existingClaimsCob835Master, claimsCob835MasterDTO);

                return existingClaimsCob835Master;
            })
            .flatMap(claimsCob835MasterRepository::save)
            .map(claimsCob835MasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ClaimsCob835MasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsCob835Masters");
        return claimsCob835MasterRepository.findAllBy(pageable).map(claimsCob835MasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return claimsCob835MasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ClaimsCob835MasterDTO> findOne(Long id) {
        log.debug("Request to get ClaimsCob835Master : {}", id);
        return claimsCob835MasterRepository.findById(id).map(claimsCob835MasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ClaimsCob835Master : {}", id);
        return claimsCob835MasterRepository.deleteById(id);
    }
}
