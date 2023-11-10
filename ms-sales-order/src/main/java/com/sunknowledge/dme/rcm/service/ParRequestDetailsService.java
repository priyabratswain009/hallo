package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ParRequestDetails}.
 */
public interface ParRequestDetailsService {
    /**
     * Save a parRequestDetails.
     *
     * @param parRequestDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ParRequestDetailsDTO> save(ParRequestDetailsDTO parRequestDetailsDTO);

    /**
     * Updates a parRequestDetails.
     *
     * @param parRequestDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ParRequestDetailsDTO> update(ParRequestDetailsDTO parRequestDetailsDTO);

    /**
     * Partially updates a parRequestDetails.
     *
     * @param parRequestDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ParRequestDetailsDTO> partialUpdate(ParRequestDetailsDTO parRequestDetailsDTO);

    /**
     * Get all the parRequestDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ParRequestDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of parRequestDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" parRequestDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ParRequestDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" parRequestDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
