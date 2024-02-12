package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimSubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimSubmisionServicelinesService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionServicelinesDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimSubmisionServicelinesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PrimaryClaimSubmisionServicelines}.
 */
@Service
@Transactional
public class PrimaryClaimSubmisionServicelinesServiceImpl implements PrimaryClaimSubmisionServicelinesService {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimSubmisionServicelinesServiceImpl.class);

    private final PrimaryClaimSubmisionServicelinesRepository primaryClaimSubmisionServicelinesRepository;

    private final PrimaryClaimSubmisionServicelinesMapper primaryClaimSubmisionServicelinesMapper;

    public PrimaryClaimSubmisionServicelinesServiceImpl(
        PrimaryClaimSubmisionServicelinesRepository primaryClaimSubmisionServicelinesRepository,
        PrimaryClaimSubmisionServicelinesMapper primaryClaimSubmisionServicelinesMapper
    ) {
        this.primaryClaimSubmisionServicelinesRepository = primaryClaimSubmisionServicelinesRepository;
        this.primaryClaimSubmisionServicelinesMapper = primaryClaimSubmisionServicelinesMapper;
    }

    @Override
    public Mono<PrimaryClaimSubmisionServicelinesDTO> save(PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO) {
        log.debug("Request to save PrimaryClaimSubmisionServicelines : {}", primaryClaimSubmisionServicelinesDTO);
        return primaryClaimSubmisionServicelinesRepository
            .save(primaryClaimSubmisionServicelinesMapper.toEntity(primaryClaimSubmisionServicelinesDTO))
            .map(primaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimSubmisionServicelinesDTO> update(PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO) {
        log.debug("Request to update PrimaryClaimSubmisionServicelines : {}", primaryClaimSubmisionServicelinesDTO);
        return primaryClaimSubmisionServicelinesRepository
            .save(primaryClaimSubmisionServicelinesMapper.toEntity(primaryClaimSubmisionServicelinesDTO))
            .map(primaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<PrimaryClaimSubmisionServicelinesDTO> partialUpdate(
        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO
    ) {
        log.debug("Request to partially update PrimaryClaimSubmisionServicelines : {}", primaryClaimSubmisionServicelinesDTO);

        return primaryClaimSubmisionServicelinesRepository
            .findById(primaryClaimSubmisionServicelinesDTO.getChangeHealthPrimarySubmisionServicelinesId())
            .map(existingPrimaryClaimSubmisionServicelines -> {
                primaryClaimSubmisionServicelinesMapper.partialUpdate(
                    existingPrimaryClaimSubmisionServicelines,
                    primaryClaimSubmisionServicelinesDTO
                );

                return existingPrimaryClaimSubmisionServicelines;
            })
            .flatMap(primaryClaimSubmisionServicelinesRepository::save)
            .map(primaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PrimaryClaimSubmisionServicelinesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrimaryClaimSubmisionServicelines");
        return primaryClaimSubmisionServicelinesRepository.findAllBy(pageable).map(primaryClaimSubmisionServicelinesMapper::toDto);
    }

    public Mono<Long> countAll() {
        return primaryClaimSubmisionServicelinesRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PrimaryClaimSubmisionServicelinesDTO> findOne(Long id) {
        log.debug("Request to get PrimaryClaimSubmisionServicelines : {}", id);
        return primaryClaimSubmisionServicelinesRepository.findById(id).map(primaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PrimaryClaimSubmisionServicelines : {}", id);
        return primaryClaimSubmisionServicelinesRepository.deleteById(id);
    }
}
