package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap;
import com.sunknowledge.dme.rcm.repository.ChecklistDocumentReferenceMapRepository;
import com.sunknowledge.dme.rcm.service.ChecklistDocumentReferenceMapService;
import com.sunknowledge.dme.rcm.service.dto.ChecklistDocumentReferenceMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ChecklistDocumentReferenceMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ChecklistDocumentReferenceMap}.
 */
@Service
@Transactional
public class ChecklistDocumentReferenceMapServiceImpl implements ChecklistDocumentReferenceMapService {

    private final Logger log = LoggerFactory.getLogger(ChecklistDocumentReferenceMapServiceImpl.class);

    private final ChecklistDocumentReferenceMapRepository checklistDocumentReferenceMapRepository;

    private final ChecklistDocumentReferenceMapMapper checklistDocumentReferenceMapMapper;

    public ChecklistDocumentReferenceMapServiceImpl(
        ChecklistDocumentReferenceMapRepository checklistDocumentReferenceMapRepository,
        ChecklistDocumentReferenceMapMapper checklistDocumentReferenceMapMapper
    ) {
        this.checklistDocumentReferenceMapRepository = checklistDocumentReferenceMapRepository;
        this.checklistDocumentReferenceMapMapper = checklistDocumentReferenceMapMapper;
    }

    @Override
    public Mono<ChecklistDocumentReferenceMapDTO> save(ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO) {
        log.debug("Request to save ChecklistDocumentReferenceMap : {}", checklistDocumentReferenceMapDTO);
        return checklistDocumentReferenceMapRepository
            .save(checklistDocumentReferenceMapMapper.toEntity(checklistDocumentReferenceMapDTO))
            .map(checklistDocumentReferenceMapMapper::toDto);
    }

    @Override
    public Mono<ChecklistDocumentReferenceMapDTO> update(ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO) {
        log.debug("Request to save ChecklistDocumentReferenceMap : {}", checklistDocumentReferenceMapDTO);
        return checklistDocumentReferenceMapRepository
            .save(checklistDocumentReferenceMapMapper.toEntity(checklistDocumentReferenceMapDTO))
            .map(checklistDocumentReferenceMapMapper::toDto);
    }

    @Override
    public Mono<ChecklistDocumentReferenceMapDTO> partialUpdate(ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO) {
        log.debug("Request to partially update ChecklistDocumentReferenceMap : {}", checklistDocumentReferenceMapDTO);

        return checklistDocumentReferenceMapRepository
            .findById(checklistDocumentReferenceMapDTO.getChecklistDocumentReferenceId())
            .map(existingChecklistDocumentReferenceMap -> {
                checklistDocumentReferenceMapMapper.partialUpdate(existingChecklistDocumentReferenceMap, checklistDocumentReferenceMapDTO);

                return existingChecklistDocumentReferenceMap;
            })
            .flatMap(checklistDocumentReferenceMapRepository::save)
            .map(checklistDocumentReferenceMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ChecklistDocumentReferenceMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChecklistDocumentReferenceMaps");
        return checklistDocumentReferenceMapRepository.findAllBy(pageable).map(checklistDocumentReferenceMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return checklistDocumentReferenceMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ChecklistDocumentReferenceMapDTO> findOne(Long id) {
        log.debug("Request to get ChecklistDocumentReferenceMap : {}", id);
        return checklistDocumentReferenceMapRepository.findById(id).map(checklistDocumentReferenceMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ChecklistDocumentReferenceMap : {}", id);
        return checklistDocumentReferenceMapRepository.deleteById(id);
    }
}
