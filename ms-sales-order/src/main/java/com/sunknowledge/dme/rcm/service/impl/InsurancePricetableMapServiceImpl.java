package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.repository.InsurancePricetableMapRepository;
import com.sunknowledge.dme.rcm.service.InsurancePricetableMapService;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsurancePricetableMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link InsurancePricetableMap}.
 */
@Service
@Transactional
public class InsurancePricetableMapServiceImpl implements InsurancePricetableMapService {

    private final Logger log = LoggerFactory.getLogger(InsurancePricetableMapServiceImpl.class);

    private final InsurancePricetableMapRepository insurancePricetableMapRepository;

    private final InsurancePricetableMapMapper insurancePricetableMapMapper;

    public InsurancePricetableMapServiceImpl(
        InsurancePricetableMapRepository insurancePricetableMapRepository,
        InsurancePricetableMapMapper insurancePricetableMapMapper
    ) {
        this.insurancePricetableMapRepository = insurancePricetableMapRepository;
        this.insurancePricetableMapMapper = insurancePricetableMapMapper;
    }

    @Override
    public Mono<InsurancePricetableMapDTO> save(InsurancePricetableMapDTO insurancePricetableMapDTO) {
        log.debug("Request to save InsurancePricetableMap : {}", insurancePricetableMapDTO);
        return insurancePricetableMapRepository
            .save(insurancePricetableMapMapper.toEntity(insurancePricetableMapDTO))
            .map(insurancePricetableMapMapper::toDto);
    }

    @Override
    public Mono<InsurancePricetableMapDTO> update(InsurancePricetableMapDTO insurancePricetableMapDTO) {
        log.debug("Request to save InsurancePricetableMap : {}", insurancePricetableMapDTO);
        return insurancePricetableMapRepository
            .save(insurancePricetableMapMapper.toEntity(insurancePricetableMapDTO))
            .map(insurancePricetableMapMapper::toDto);
    }

    @Override
    public Mono<InsurancePricetableMapDTO> partialUpdate(InsurancePricetableMapDTO insurancePricetableMapDTO) {
        log.debug("Request to partially update InsurancePricetableMap : {}", insurancePricetableMapDTO);

        return insurancePricetableMapRepository
            .findById(insurancePricetableMapDTO.getInsurancePricetableMapId())
            .map(existingInsurancePricetableMap -> {
                insurancePricetableMapMapper.partialUpdate(existingInsurancePricetableMap, insurancePricetableMapDTO);

                return existingInsurancePricetableMap;
            })
            .flatMap(insurancePricetableMapRepository::save)
            .map(insurancePricetableMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InsurancePricetableMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsurancePricetableMaps");
        return insurancePricetableMapRepository.findAllBy(pageable).map(insurancePricetableMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return insurancePricetableMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InsurancePricetableMapDTO> findOne(Long id) {
        log.debug("Request to get InsurancePricetableMap : {}", id);
        return insurancePricetableMapRepository.findById(id).map(insurancePricetableMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InsurancePricetableMap : {}", id);
        return insurancePricetableMapRepository.deleteById(id);
    }
}
