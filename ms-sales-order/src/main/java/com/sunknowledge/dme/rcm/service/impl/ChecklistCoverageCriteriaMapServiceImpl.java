package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap;
import com.sunknowledge.dme.rcm.repository.ChecklistCoverageCriteriaMapRepository;
import com.sunknowledge.dme.rcm.service.ChecklistCoverageCriteriaMapService;
import com.sunknowledge.dme.rcm.service.dto.ChecklistCoverageCriteriaMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ChecklistCoverageCriteriaMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ChecklistCoverageCriteriaMap}.
 */
@Service
@Transactional
public class ChecklistCoverageCriteriaMapServiceImpl implements ChecklistCoverageCriteriaMapService {

    private final Logger log = LoggerFactory.getLogger(ChecklistCoverageCriteriaMapServiceImpl.class);

    private final ChecklistCoverageCriteriaMapRepository checklistCoverageCriteriaMapRepository;

    private final ChecklistCoverageCriteriaMapMapper checklistCoverageCriteriaMapMapper;

    public ChecklistCoverageCriteriaMapServiceImpl(
        ChecklistCoverageCriteriaMapRepository checklistCoverageCriteriaMapRepository,
        ChecklistCoverageCriteriaMapMapper checklistCoverageCriteriaMapMapper
    ) {
        this.checklistCoverageCriteriaMapRepository = checklistCoverageCriteriaMapRepository;
        this.checklistCoverageCriteriaMapMapper = checklistCoverageCriteriaMapMapper;
    }

    @Override
    public Mono<ChecklistCoverageCriteriaMapDTO> save(ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO) {
        log.debug("Request to save ChecklistCoverageCriteriaMap : {}", checklistCoverageCriteriaMapDTO);
        return checklistCoverageCriteriaMapRepository
            .save(checklistCoverageCriteriaMapMapper.toEntity(checklistCoverageCriteriaMapDTO))
            .map(checklistCoverageCriteriaMapMapper::toDto);
    }

    @Override
    public Mono<ChecklistCoverageCriteriaMapDTO> update(ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO) {
        log.debug("Request to update ChecklistCoverageCriteriaMap : {}", checklistCoverageCriteriaMapDTO);
        return checklistCoverageCriteriaMapRepository
            .save(checklistCoverageCriteriaMapMapper.toEntity(checklistCoverageCriteriaMapDTO))
            .map(checklistCoverageCriteriaMapMapper::toDto);
    }

    @Override
    public Mono<ChecklistCoverageCriteriaMapDTO> partialUpdate(ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO) {
        log.debug("Request to partially update ChecklistCoverageCriteriaMap : {}", checklistCoverageCriteriaMapDTO);

        return checklistCoverageCriteriaMapRepository
            .findById(checklistCoverageCriteriaMapDTO.getChecklistCoverageCriteriaId())
            .map(existingChecklistCoverageCriteriaMap -> {
                checklistCoverageCriteriaMapMapper.partialUpdate(existingChecklistCoverageCriteriaMap, checklistCoverageCriteriaMapDTO);

                return existingChecklistCoverageCriteriaMap;
            })
            .flatMap(checklistCoverageCriteriaMapRepository::save)
            .map(checklistCoverageCriteriaMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ChecklistCoverageCriteriaMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChecklistCoverageCriteriaMaps");
        return checklistCoverageCriteriaMapRepository.findAllBy(pageable).map(checklistCoverageCriteriaMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return checklistCoverageCriteriaMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ChecklistCoverageCriteriaMapDTO> findOne(Long id) {
        log.debug("Request to get ChecklistCoverageCriteriaMap : {}", id);
        return checklistCoverageCriteriaMapRepository.findById(id).map(checklistCoverageCriteriaMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ChecklistCoverageCriteriaMap : {}", id);
        return checklistCoverageCriteriaMapRepository.deleteById(id);
    }
}
