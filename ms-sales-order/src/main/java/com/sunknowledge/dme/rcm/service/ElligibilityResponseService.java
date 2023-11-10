package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponse}.
 */
public interface ElligibilityResponseService {
    /**
     * Save a elligibilityResponse.
     *
     * @param elligibilityResponseDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseDTO> save(ElligibilityResponseDTO elligibilityResponseDTO);

    /**
     * Updates a elligibilityResponse.
     *
     * @param elligibilityResponseDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseDTO> update(ElligibilityResponseDTO elligibilityResponseDTO);

    /**
     * Partially updates a elligibilityResponse.
     *
     * @param elligibilityResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseDTO> partialUpdate(ElligibilityResponseDTO elligibilityResponseDTO);

    /**
     * Get all the elligibilityResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ElligibilityResponseDTO> findAll(Pageable pageable);

    /**
     * Returns the number of elligibilityResponses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" elligibilityResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ElligibilityResponseDTO> findOne(Long id);

    /**
     * Delete the "id" elligibilityResponse.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
