package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.repository.ParSoMapRepository;
import com.sunknowledge.dme.rcm.service.ParSoMapService;
import com.sunknowledge.dme.rcm.service.dto.ParSoMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParSoMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ParSoMap}.
 */
@Service
@Transactional
public class ParSoMapServiceImpl implements ParSoMapService {

    private final Logger log = LoggerFactory.getLogger(ParSoMapServiceImpl.class);

    private final ParSoMapRepository parSoMapRepository;

    private final ParSoMapMapper parSoMapMapper;

    public ParSoMapServiceImpl(ParSoMapRepository parSoMapRepository, ParSoMapMapper parSoMapMapper) {
        this.parSoMapRepository = parSoMapRepository;
        this.parSoMapMapper = parSoMapMapper;
    }

    @Override
    public Mono<ParSoMapDTO> save(ParSoMapDTO parSoMapDTO) {
        log.debug("Request to save ParSoMap : {}", parSoMapDTO);
        return parSoMapRepository.save(parSoMapMapper.toEntity(parSoMapDTO)).map(parSoMapMapper::toDto);
    }

    @Override
    public Mono<ParSoMapDTO> update(ParSoMapDTO parSoMapDTO) {
        log.debug("Request to update ParSoMap : {}", parSoMapDTO);
        return parSoMapRepository.save(parSoMapMapper.toEntity(parSoMapDTO)).map(parSoMapMapper::toDto);
    }

    @Override
    public Mono<ParSoMapDTO> partialUpdate(ParSoMapDTO parSoMapDTO) {
        log.debug("Request to partially update ParSoMap : {}", parSoMapDTO);

        return parSoMapRepository
            .findById(parSoMapDTO.getParSoId())
            .map(existingParSoMap -> {
                parSoMapMapper.partialUpdate(existingParSoMap, parSoMapDTO);

                return existingParSoMap;
            })
            .flatMap(parSoMapRepository::save)
            .map(parSoMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ParSoMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParSoMaps");
        return parSoMapRepository.findAllBy(pageable).map(parSoMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return parSoMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ParSoMapDTO> findOne(Long id) {
        log.debug("Request to get ParSoMap : {}", id);
        return parSoMapRepository.findById(id).map(parSoMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ParSoMap : {}", id);
        return parSoMapRepository.deleteById(id);
    }
}
