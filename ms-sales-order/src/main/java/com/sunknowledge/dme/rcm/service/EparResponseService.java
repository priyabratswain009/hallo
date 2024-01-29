package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.EparResponse}.
 */
public interface EparResponseService {
    /**
     * Save a eparResponse.
     *
     * @param eparResponseDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<EparResponseDTO> save(EparResponseDTO eparResponseDTO);

    /**
     * Updates a eparResponse.
     *
     * @param eparResponseDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<EparResponseDTO> update(EparResponseDTO eparResponseDTO);

    /**
     * Partially updates a eparResponse.
     *
     * @param eparResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<EparResponseDTO> partialUpdate(EparResponseDTO eparResponseDTO);

    /**
     * Get all the eparResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<EparResponseDTO> findAll(Pageable pageable);

    /**
     * Returns the number of eparResponses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" eparResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<EparResponseDTO> findOne(Long id);

    /**
     * Delete the "id" eparResponse.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
