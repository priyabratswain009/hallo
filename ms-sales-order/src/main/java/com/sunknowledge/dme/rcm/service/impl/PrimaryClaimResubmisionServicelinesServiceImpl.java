package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimResubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimResubmisionServicelinesService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionServicelinesDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimResubmisionServicelinesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PrimaryClaimResubmisionServicelines}.
 */
@Service
@Transactional
public class PrimaryClaimResubmisionServicelinesServiceImpl implements PrimaryClaimResubmisionServicelinesService {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimResubmisionServicelinesServiceImpl.class);

    private final PrimaryClaimResubmisionServicelinesRepository primaryClaimResubmisionServicelinesRepository;

    private final PrimaryClaimResubmisionServicelinesMapper primaryClaimResubmisionServicelinesMapper;

    public PrimaryClaimResubmisionServicelinesServiceImpl(
        PrimaryClaimResubmisionServicelinesRepository primaryClaimResubmisionServicelinesRepository,
        PrimaryClaimResubmisionServicelinesMapper primaryClaimResubmisionServicelinesMapper
    ) {
        this.primaryClaimResubmisionServicelinesRepository = primaryClaimResubmisionServicelinesRepository;
        this.primaryClaimResubmisionServicelinesMapper = primaryClaimResubmisionServicelinesMapper;
    }

    @Override
    public Mono<PrimaryClaimResubmisionServicelinesDTO> save(
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    ) {
        log.debug("Request to save PrimaryClaimResubmisionServicelines : {}", primaryClaimResubmisionServicelinesDTO);
        return primaryClaimResubmisionServicelinesRepository
            .save(primaryClaimResubmisionServicelinesMapper.toEntity(primaryClaimResubmisionServicelinesDTO))
            .map(primaryClaimResubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimResubmisionServicelinesDTO> update(
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    ) {
        log.debug("Request to save PrimaryClaimResubmisionServicelines : {}", primaryClaimResubmisionServicelinesDTO);
        return primaryClaimResubmisionServicelinesRepository
            .save(primaryClaimResubmisionServicelinesMapper.toEntity(primaryClaimResubmisionServicelinesDTO))
            .map(primaryClaimResubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimResubmisionServicelinesDTO> partialUpdate(
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    ) {
        log.debug("Request to partially update PrimaryClaimResubmisionServicelines : {}", primaryClaimResubmisionServicelinesDTO);

        return primaryClaimResubmisionServicelinesRepository
            .findById(primaryClaimResubmisionServicelinesDTO.getChangeHealthPrimaryResubmisionServicelinesId())
            .map(existingPrimaryClaimResubmisionServicelines -> {
                primaryClaimResubmisionServicelinesMapper.partialUpdate(
                    existingPrimaryClaimResubmisionServicelines,
                    primaryClaimResubmisionServicelinesDTO
                );

                return existingPrimaryClaimResubmisionServicelines;
            })
            .flatMap(primaryClaimResubmisionServicelinesRepository::save)
            .map(primaryClaimResubmisionServicelinesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PrimaryClaimResubmisionServicelinesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrimaryClaimResubmisionServicelines");
        return primaryClaimResubmisionServicelinesRepository.findAllBy(pageable).map(primaryClaimResubmisionServicelinesMapper::toDto);
    }

    public Mono<Long> countAll() {
        return primaryClaimResubmisionServicelinesRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PrimaryClaimResubmisionServicelinesDTO> findOne(Long id) {
        log.debug("Request to get PrimaryClaimResubmisionServicelines : {}", id);
        return primaryClaimResubmisionServicelinesRepository.findById(id).map(primaryClaimResubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PrimaryClaimResubmisionServicelines : {}", id);
        return primaryClaimResubmisionServicelinesRepository.deleteById(id);
    }
}
