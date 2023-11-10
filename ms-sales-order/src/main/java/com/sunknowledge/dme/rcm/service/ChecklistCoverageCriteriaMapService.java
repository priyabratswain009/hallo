package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ChecklistCoverageCriteriaMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap}.
 */
public interface ChecklistCoverageCriteriaMapService {
    /**
     * Save a checklistCoverageCriteriaMap.
     *
     * @param checklistCoverageCriteriaMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ChecklistCoverageCriteriaMapDTO> save(ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO);

    /**
     * Updates a checklistCoverageCriteriaMap.
     *
     * @param checklistCoverageCriteriaMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ChecklistCoverageCriteriaMapDTO> update(ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO);

    /**
     * Partially updates a checklistCoverageCriteriaMap.
     *
     * @param checklistCoverageCriteriaMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ChecklistCoverageCriteriaMapDTO> partialUpdate(ChecklistCoverageCriteriaMapDTO checklistCoverageCriteriaMapDTO);

    /**
     * Get all the checklistCoverageCriteriaMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ChecklistCoverageCriteriaMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of checklistCoverageCriteriaMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" checklistCoverageCriteriaMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ChecklistCoverageCriteriaMapDTO> findOne(Long id);

    /**
     * Delete the "id" checklistCoverageCriteriaMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
