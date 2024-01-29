package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap;
import com.sunknowledge.dme.rcm.repository.CoverageCriteriaFileMapRepository;
import com.sunknowledge.dme.rcm.service.CoverageCriteriaFileMapService;
import com.sunknowledge.dme.rcm.service.dto.CoverageCriteriaFileMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.CoverageCriteriaFileMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CoverageCriteriaFileMap}.
 */
@Service
@Transactional
public class CoverageCriteriaFileMapServiceImpl implements CoverageCriteriaFileMapService {

    private final Logger log = LoggerFactory.getLogger(CoverageCriteriaFileMapServiceImpl.class);

    private final CoverageCriteriaFileMapRepository coverageCriteriaFileMapRepository;

    private final CoverageCriteriaFileMapMapper coverageCriteriaFileMapMapper;

    public CoverageCriteriaFileMapServiceImpl(
        CoverageCriteriaFileMapRepository coverageCriteriaFileMapRepository,
        CoverageCriteriaFileMapMapper coverageCriteriaFileMapMapper
    ) {
        this.coverageCriteriaFileMapRepository = coverageCriteriaFileMapRepository;
        this.coverageCriteriaFileMapMapper = coverageCriteriaFileMapMapper;
    }

    @Override
    public Mono<CoverageCriteriaFileMapDTO> save(CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO) {
        log.debug("Request to save CoverageCriteriaFileMap : {}", coverageCriteriaFileMapDTO);
        return coverageCriteriaFileMapRepository
            .save(coverageCriteriaFileMapMapper.toEntity(coverageCriteriaFileMapDTO))
            .map(coverageCriteriaFileMapMapper::toDto);
    }

    @Override
    public Mono<CoverageCriteriaFileMapDTO> update(CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO) {
        log.debug("Request to update CoverageCriteriaFileMap : {}", coverageCriteriaFileMapDTO);
        return coverageCriteriaFileMapRepository
            .save(coverageCriteriaFileMapMapper.toEntity(coverageCriteriaFileMapDTO))
            .map(coverageCriteriaFileMapMapper::toDto);
    }

    @Override
    public Mono<CoverageCriteriaFileMapDTO> partialUpdate(CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO) {
        log.debug("Request to partially update CoverageCriteriaFileMap : {}", coverageCriteriaFileMapDTO);

        return coverageCriteriaFileMapRepository
            .findById(coverageCriteriaFileMapDTO.getCoverageCriteriaFileMapId())
            .map(existingCoverageCriteriaFileMap -> {
                coverageCriteriaFileMapMapper.partialUpdate(existingCoverageCriteriaFileMap, coverageCriteriaFileMapDTO);

                return existingCoverageCriteriaFileMap;
            })
            .flatMap(coverageCriteriaFileMapRepository::save)
            .map(coverageCriteriaFileMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CoverageCriteriaFileMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoverageCriteriaFileMaps");
        return coverageCriteriaFileMapRepository.findAllBy(pageable).map(coverageCriteriaFileMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return coverageCriteriaFileMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<CoverageCriteriaFileMapDTO> findOne(Long id) {
        log.debug("Request to get CoverageCriteriaFileMap : {}", id);
        return coverageCriteriaFileMapRepository.findById(id).map(coverageCriteriaFileMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CoverageCriteriaFileMap : {}", id);
        return coverageCriteriaFileMapRepository.deleteById(id);
    }
}
