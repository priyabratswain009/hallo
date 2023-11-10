package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimResubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimResubmisionMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimResubmisionMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PrimaryClaimResubmisionMaster}.
 */
@Service
@Transactional
public class PrimaryClaimResubmisionMasterServiceImpl implements PrimaryClaimResubmisionMasterService {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimResubmisionMasterServiceImpl.class);

    private final PrimaryClaimResubmisionMasterRepository primaryClaimResubmisionMasterRepository;

    private final PrimaryClaimResubmisionMasterMapper primaryClaimResubmisionMasterMapper;

    public PrimaryClaimResubmisionMasterServiceImpl(
        PrimaryClaimResubmisionMasterRepository primaryClaimResubmisionMasterRepository,
        PrimaryClaimResubmisionMasterMapper primaryClaimResubmisionMasterMapper
    ) {
        this.primaryClaimResubmisionMasterRepository = primaryClaimResubmisionMasterRepository;
        this.primaryClaimResubmisionMasterMapper = primaryClaimResubmisionMasterMapper;
    }

    @Override
    public Mono<PrimaryClaimResubmisionMasterDTO> save(PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO) {
        log.debug("Request to save PrimaryClaimResubmisionMaster : {}", primaryClaimResubmisionMasterDTO);
        return primaryClaimResubmisionMasterRepository
            .save(primaryClaimResubmisionMasterMapper.toEntity(primaryClaimResubmisionMasterDTO))
            .map(primaryClaimResubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimResubmisionMasterDTO> update(PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO) {
        log.debug("Request to save PrimaryClaimResubmisionMaster : {}", primaryClaimResubmisionMasterDTO);
        return primaryClaimResubmisionMasterRepository
            .save(primaryClaimResubmisionMasterMapper.toEntity(primaryClaimResubmisionMasterDTO))
            .map(primaryClaimResubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimResubmisionMasterDTO> partialUpdate(PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO) {
        log.debug("Request to partially update PrimaryClaimResubmisionMaster : {}", primaryClaimResubmisionMasterDTO);

        return primaryClaimResubmisionMasterRepository
            .findById(primaryClaimResubmisionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
            .map(existingPrimaryClaimResubmisionMaster -> {
                primaryClaimResubmisionMasterMapper.partialUpdate(existingPrimaryClaimResubmisionMaster, primaryClaimResubmisionMasterDTO);

                return existingPrimaryClaimResubmisionMaster;
            })
            .flatMap(primaryClaimResubmisionMasterRepository::save)
            .map(primaryClaimResubmisionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PrimaryClaimResubmisionMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrimaryClaimResubmisionMasters");
        return primaryClaimResubmisionMasterRepository.findAllBy(pageable).map(primaryClaimResubmisionMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return primaryClaimResubmisionMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PrimaryClaimResubmisionMasterDTO> findOne(Long id) {
        log.debug("Request to get PrimaryClaimResubmisionMaster : {}", id);
        return primaryClaimResubmisionMasterRepository.findById(id).map(primaryClaimResubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PrimaryClaimResubmisionMaster : {}", id);
        return primaryClaimResubmisionMasterRepository.deleteById(id);
    }
}
