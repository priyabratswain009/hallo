package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.FunctionalAbility;
import com.sunknowledge.dme.rcm.repository.FunctionalAbilityRepository;
import com.sunknowledge.dme.rcm.service.FunctionalAbilityService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link FunctionalAbility}.
 */
@Service
@Transactional
public class FunctionalAbilityServiceImpl implements FunctionalAbilityService {

    private final Logger log = LoggerFactory.getLogger(FunctionalAbilityServiceImpl.class);

    private final FunctionalAbilityRepository functionalAbilityRepository;

    private final FunctionalAbilityMapper functionalAbilityMapper;

    public FunctionalAbilityServiceImpl(
        FunctionalAbilityRepository functionalAbilityRepository,
        FunctionalAbilityMapper functionalAbilityMapper
    ) {
        this.functionalAbilityRepository = functionalAbilityRepository;
        this.functionalAbilityMapper = functionalAbilityMapper;
    }

    @Override
    public Mono<FunctionalAbilityDTO> save(FunctionalAbilityDTO functionalAbilityDTO) {
        log.debug("Request to save FunctionalAbility : {}", functionalAbilityDTO);
        return functionalAbilityRepository.save(functionalAbilityMapper.toEntity(functionalAbilityDTO)).map(functionalAbilityMapper::toDto);
    }

    @Override
    public Mono<FunctionalAbilityDTO> update(FunctionalAbilityDTO functionalAbilityDTO) {
        log.debug("Request to update FunctionalAbility : {}", functionalAbilityDTO);
        return functionalAbilityRepository.save(functionalAbilityMapper.toEntity(functionalAbilityDTO)).map(functionalAbilityMapper::toDto);
    }

    @Override
    public Mono<FunctionalAbilityDTO> partialUpdate(FunctionalAbilityDTO functionalAbilityDTO) {
        log.debug("Request to partially update FunctionalAbility : {}", functionalAbilityDTO);

        return functionalAbilityRepository
            .findById(functionalAbilityDTO.getFunctionalAbilityId())
            .map(existingFunctionalAbility -> {
                functionalAbilityMapper.partialUpdate(existingFunctionalAbility, functionalAbilityDTO);

                return existingFunctionalAbility;
            })
            .flatMap(functionalAbilityRepository::save)
            .map(functionalAbilityMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FunctionalAbilityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FunctionalAbilities");
        return functionalAbilityRepository.findAllBy(pageable).map(functionalAbilityMapper::toDto);
    }

    public Mono<Long> countAll() {
        return functionalAbilityRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FunctionalAbilityDTO> findOne(Long id) {
        log.debug("Request to get FunctionalAbility : {}", id);
        return functionalAbilityRepository.findById(id).map(functionalAbilityMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FunctionalAbility : {}", id);
        return functionalAbilityRepository.deleteById(id);
    }
}
