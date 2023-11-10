package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ParDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ParDetails}.
 */
public interface ParDetailsService {
    /**
     * Save a parDetails.
     *
     * @param parDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ParDetailsDTO> save(ParDetailsDTO parDetailsDTO);

    /**
     * Updates a parDetails.
     *
     * @param parDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ParDetailsDTO> update(ParDetailsDTO parDetailsDTO);

    /**
     * Partially updates a parDetails.
     *
     * @param parDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ParDetailsDTO> partialUpdate(ParDetailsDTO parDetailsDTO);

    /**
     * Get all the parDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ParDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of parDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" parDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ParDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" parDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
