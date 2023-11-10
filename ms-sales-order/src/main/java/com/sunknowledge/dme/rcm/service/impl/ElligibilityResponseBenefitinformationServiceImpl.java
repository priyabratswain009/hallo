package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseBenefitinformationRepository;
import com.sunknowledge.dme.rcm.service.ElligibilityResponseBenefitinformationService;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseBenefitinformationDTO;
import com.sunknowledge.dme.rcm.service.mapper.ElligibilityResponseBenefitinformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ElligibilityResponseBenefitinformation}.
 */
@Service
@Transactional
public class ElligibilityResponseBenefitinformationServiceImpl implements ElligibilityResponseBenefitinformationService {

    private final Logger log = LoggerFactory.getLogger(ElligibilityResponseBenefitinformationServiceImpl.class);

    private final ElligibilityResponseBenefitinformationRepository elligibilityResponseBenefitinformationRepository;

    private final ElligibilityResponseBenefitinformationMapper elligibilityResponseBenefitinformationMapper;

    public ElligibilityResponseBenefitinformationServiceImpl(
        ElligibilityResponseBenefitinformationRepository elligibilityResponseBenefitinformationRepository,
        ElligibilityResponseBenefitinformationMapper elligibilityResponseBenefitinformationMapper
    ) {
        this.elligibilityResponseBenefitinformationRepository = elligibilityResponseBenefitinformationRepository;
        this.elligibilityResponseBenefitinformationMapper = elligibilityResponseBenefitinformationMapper;
    }

    @Override
    public Mono<ElligibilityResponseBenefitinformationDTO> save(
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    ) {
        log.debug("Request to save ElligibilityResponseBenefitinformation : {}", elligibilityResponseBenefitinformationDTO);
        return elligibilityResponseBenefitinformationRepository
            .save(elligibilityResponseBenefitinformationMapper.toEntity(elligibilityResponseBenefitinformationDTO))
            .map(elligibilityResponseBenefitinformationMapper::toDto);
    }

    @Override
    public Mono<ElligibilityResponseBenefitinformationDTO> update(
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    ) {
        log.debug("Request to save ElligibilityResponseBenefitinformation : {}", elligibilityResponseBenefitinformationDTO);
        return elligibilityResponseBenefitinformationRepository
            .save(elligibilityResponseBenefitinformationMapper.toEntity(elligibilityResponseBenefitinformationDTO))
            .map(elligibilityResponseBenefitinformationMapper::toDto);
    }

    @Override
    public Mono<ElligibilityResponseBenefitinformationDTO> partialUpdate(
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    ) {
        log.debug("Request to partially update ElligibilityResponseBenefitinformation : {}", elligibilityResponseBenefitinformationDTO);

        return elligibilityResponseBenefitinformationRepository
            .findById(elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId())
            .map(existingElligibilityResponseBenefitinformation -> {
                elligibilityResponseBenefitinformationMapper.partialUpdate(
                    existingElligibilityResponseBenefitinformation,
                    elligibilityResponseBenefitinformationDTO
                );

                return existingElligibilityResponseBenefitinformation;
            })
            .flatMap(elligibilityResponseBenefitinformationRepository::save)
            .map(elligibilityResponseBenefitinformationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ElligibilityResponseBenefitinformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElligibilityResponseBenefitinformations");
        return elligibilityResponseBenefitinformationRepository
            .findAllBy(pageable)
            .map(elligibilityResponseBenefitinformationMapper::toDto);
    }

    public Mono<Long> countAll() {
        return elligibilityResponseBenefitinformationRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ElligibilityResponseBenefitinformationDTO> findOne(Long id) {
        log.debug("Request to get ElligibilityResponseBenefitinformation : {}", id);
        return elligibilityResponseBenefitinformationRepository.findById(id).map(elligibilityResponseBenefitinformationMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ElligibilityResponseBenefitinformation : {}", id);
        return elligibilityResponseBenefitinformationRepository.deleteById(id);
    }
}
