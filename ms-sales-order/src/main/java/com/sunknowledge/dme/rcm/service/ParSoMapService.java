package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ParSoMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ParSoMap}.
 */
public interface ParSoMapService {
    /**
     * Save a parSoMap.
     *
     * @param parSoMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ParSoMapDTO> save(ParSoMapDTO parSoMapDTO);

    /**
     * Updates a parSoMap.
     *
     * @param parSoMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ParSoMapDTO> update(ParSoMapDTO parSoMapDTO);

    /**
     * Partially updates a parSoMap.
     *
     * @param parSoMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ParSoMapDTO> partialUpdate(ParSoMapDTO parSoMapDTO);

    /**
     * Get all the parSoMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ParSoMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of parSoMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" parSoMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ParSoMapDTO> findOne(Long id);

    /**
     * Delete the "id" parSoMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
