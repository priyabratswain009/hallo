package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionServicelinesRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimSubmisionServicelinesService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionServicelinesDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimSubmisionServicelinesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SecondaryClaimSubmisionServicelines}.
 */
@Service
@Transactional
public class SecondaryClaimSubmisionServicelinesServiceImpl implements SecondaryClaimSubmisionServicelinesService {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimSubmisionServicelinesServiceImpl.class);

    private final SecondaryClaimSubmisionServicelinesRepository secondaryClaimSubmisionServicelinesRepository;

    private final SecondaryClaimSubmisionServicelinesMapper secondaryClaimSubmisionServicelinesMapper;

    public SecondaryClaimSubmisionServicelinesServiceImpl(
        SecondaryClaimSubmisionServicelinesRepository secondaryClaimSubmisionServicelinesRepository,
        SecondaryClaimSubmisionServicelinesMapper secondaryClaimSubmisionServicelinesMapper
    ) {
        this.secondaryClaimSubmisionServicelinesRepository = secondaryClaimSubmisionServicelinesRepository;
        this.secondaryClaimSubmisionServicelinesMapper = secondaryClaimSubmisionServicelinesMapper;
    }

    @Override
    public Mono<SecondaryClaimSubmisionServicelinesDTO> save(
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    ) {
        log.debug("Request to save SecondaryClaimSubmisionServicelines : {}", secondaryClaimSubmisionServicelinesDTO);
        return secondaryClaimSubmisionServicelinesRepository
            .save(secondaryClaimSubmisionServicelinesMapper.toEntity(secondaryClaimSubmisionServicelinesDTO))
            .map(secondaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<SecondaryClaimSubmisionServicelinesDTO> update(
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    ) {
        log.debug("Request to save SecondaryClaimSubmisionServicelines : {}", secondaryClaimSubmisionServicelinesDTO);
        return secondaryClaimSubmisionServicelinesRepository
            .save(secondaryClaimSubmisionServicelinesMapper.toEntity(secondaryClaimSubmisionServicelinesDTO))
            .map(secondaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<SecondaryClaimSubmisionServicelinesDTO> partialUpdate(
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    ) {
        log.debug("Request to partially update SecondaryClaimSubmisionServicelines : {}", secondaryClaimSubmisionServicelinesDTO);

        return secondaryClaimSubmisionServicelinesRepository
            .findById(secondaryClaimSubmisionServicelinesDTO.getChangeHealthSecondarySubmisionServicelinesId())
            .map(existingSecondaryClaimSubmisionServicelines -> {
                secondaryClaimSubmisionServicelinesMapper.partialUpdate(
                    existingSecondaryClaimSubmisionServicelines,
                    secondaryClaimSubmisionServicelinesDTO
                );

                return existingSecondaryClaimSubmisionServicelines;
            })
            .flatMap(secondaryClaimSubmisionServicelinesRepository::save)
            .map(secondaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SecondaryClaimSubmisionServicelinesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SecondaryClaimSubmisionServicelines");
        return secondaryClaimSubmisionServicelinesRepository.findAllBy(pageable).map(secondaryClaimSubmisionServicelinesMapper::toDto);
    }

    public Mono<Long> countAll() {
        return secondaryClaimSubmisionServicelinesRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SecondaryClaimSubmisionServicelinesDTO> findOne(Long id) {
        log.debug("Request to get SecondaryClaimSubmisionServicelines : {}", id);
        return secondaryClaimSubmisionServicelinesRepository.findById(id).map(secondaryClaimSubmisionServicelinesMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SecondaryClaimSubmisionServicelines : {}", id);
        return secondaryClaimSubmisionServicelinesRepository.deleteById(id);
    }
}
