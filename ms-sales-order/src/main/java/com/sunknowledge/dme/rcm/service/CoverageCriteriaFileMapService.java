package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.CoverageCriteriaFileMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap}.
 */
public interface CoverageCriteriaFileMapService {
    /**
     * Save a coverageCriteriaFileMap.
     *
     * @param coverageCriteriaFileMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<CoverageCriteriaFileMapDTO> save(CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO);

    /**
     * Updates a coverageCriteriaFileMap.
     *
     * @param coverageCriteriaFileMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<CoverageCriteriaFileMapDTO> update(CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO);

    /**
     * Partially updates a coverageCriteriaFileMap.
     *
     * @param coverageCriteriaFileMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<CoverageCriteriaFileMapDTO> partialUpdate(CoverageCriteriaFileMapDTO coverageCriteriaFileMapDTO);

    /**
     * Get all the coverageCriteriaFileMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<CoverageCriteriaFileMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of coverageCriteriaFileMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" coverageCriteriaFileMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<CoverageCriteriaFileMapDTO> findOne(Long id);

    /**
     * Delete the "id" coverageCriteriaFileMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
