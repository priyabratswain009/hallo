package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimSubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimSubmisionMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimSubmisionMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PrimaryClaimSubmisionMaster}.
 */
@Service
@Transactional
public class PrimaryClaimSubmisionMasterServiceImpl implements PrimaryClaimSubmisionMasterService {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimSubmisionMasterServiceImpl.class);

    private final PrimaryClaimSubmisionMasterRepository primaryClaimSubmisionMasterRepository;

    private final PrimaryClaimSubmisionMasterMapper primaryClaimSubmisionMasterMapper;

    public PrimaryClaimSubmisionMasterServiceImpl(
        PrimaryClaimSubmisionMasterRepository primaryClaimSubmisionMasterRepository,
        PrimaryClaimSubmisionMasterMapper primaryClaimSubmisionMasterMapper
    ) {
        this.primaryClaimSubmisionMasterRepository = primaryClaimSubmisionMasterRepository;
        this.primaryClaimSubmisionMasterMapper = primaryClaimSubmisionMasterMapper;
    }

    @Override
    public Mono<PrimaryClaimSubmisionMasterDTO> save(PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO) {
        log.debug("Request to save PrimaryClaimSubmisionMaster : {}", primaryClaimSubmisionMasterDTO);
        return primaryClaimSubmisionMasterRepository
            .save(primaryClaimSubmisionMasterMapper.toEntity(primaryClaimSubmisionMasterDTO))
            .map(primaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimSubmisionMasterDTO> update(PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO) {
        log.debug("Request to save PrimaryClaimSubmisionMaster : {}", primaryClaimSubmisionMasterDTO);
        return primaryClaimSubmisionMasterRepository
            .save(primaryClaimSubmisionMasterMapper.toEntity(primaryClaimSubmisionMasterDTO))
            .map(primaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimSubmisionMasterDTO> partialUpdate(PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO) {
        log.debug("Request to partially update PrimaryClaimSubmisionMaster : {}", primaryClaimSubmisionMasterDTO);

        return primaryClaimSubmisionMasterRepository
            .findById(primaryClaimSubmisionMasterDTO.getChangeHealthPrimarySubmisionMasterId())
            .map(existingPrimaryClaimSubmisionMaster -> {
                primaryClaimSubmisionMasterMapper.partialUpdate(existingPrimaryClaimSubmisionMaster, primaryClaimSubmisionMasterDTO);

                return existingPrimaryClaimSubmisionMaster;
            })
            .flatMap(primaryClaimSubmisionMasterRepository::save)
            .map(primaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PrimaryClaimSubmisionMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrimaryClaimSubmisionMasters");
        return primaryClaimSubmisionMasterRepository.findAllBy(pageable).map(primaryClaimSubmisionMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return primaryClaimSubmisionMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PrimaryClaimSubmisionMasterDTO> findOne(Long id) {
        log.debug("Request to get PrimaryClaimSubmisionMaster : {}", id);
        return primaryClaimSubmisionMasterRepository.findById(id).map(primaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PrimaryClaimSubmisionMaster : {}", id);
        return primaryClaimSubmisionMasterRepository.deleteById(id);
    }
}
