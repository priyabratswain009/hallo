package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.EparRequest}.
 */
public interface EparRequestService {
    /**
     * Save a eparRequest.
     *
     * @param eparRequestDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<EparRequestDTO> save(EparRequestDTO eparRequestDTO);

    /**
     * Updates a eparRequest.
     *
     * @param eparRequestDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<EparRequestDTO> update(EparRequestDTO eparRequestDTO);

    /**
     * Partially updates a eparRequest.
     *
     * @param eparRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<EparRequestDTO> partialUpdate(EparRequestDTO eparRequestDTO);

    /**
     * Get all the eparRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<EparRequestDTO> findAll(Pageable pageable);

    /**
     * Returns the number of eparRequests available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" eparRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<EparRequestDTO> findOne(Long id);

    /**
     * Delete the "id" eparRequest.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
