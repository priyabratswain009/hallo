package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.Cmn}.
 */
public interface CmnService {
    /**
     * Save a cmn.
     *
     * @param cmnDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<CmnDTO> save(CmnDTO cmnDTO);

    /**
     * Updates a cmn.
     *
     * @param cmnDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<CmnDTO> update(CmnDTO cmnDTO);

    /**
     * Partially updates a cmn.
     *
     * @param cmnDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<CmnDTO> partialUpdate(CmnDTO cmnDTO);

    /**
     * Get all the cmns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<CmnDTO> findAll(Pageable pageable);

    /**
     * Returns the number of cmns available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" cmn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<CmnDTO> findOne(Long id);

    /**
     * Delete the "id" cmn.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
