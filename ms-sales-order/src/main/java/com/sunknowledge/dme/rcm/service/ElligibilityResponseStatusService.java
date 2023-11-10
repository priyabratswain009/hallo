package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseStatusDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus}.
 */
public interface ElligibilityResponseStatusService {
    /**
     * Save a elligibilityResponseStatus.
     *
     * @param elligibilityResponseStatusDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseStatusDTO> save(ElligibilityResponseStatusDTO elligibilityResponseStatusDTO);

    /**
     * Updates a elligibilityResponseStatus.
     *
     * @param elligibilityResponseStatusDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseStatusDTO> update(ElligibilityResponseStatusDTO elligibilityResponseStatusDTO);

    /**
     * Partially updates a elligibilityResponseStatus.
     *
     * @param elligibilityResponseStatusDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseStatusDTO> partialUpdate(ElligibilityResponseStatusDTO elligibilityResponseStatusDTO);

    /**
     * Get all the elligibilityResponseStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ElligibilityResponseStatusDTO> findAll(Pageable pageable);

    /**
     * Returns the number of elligibilityResponseStatuses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" elligibilityResponseStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ElligibilityResponseStatusDTO> findOne(Long id);

    /**
     * Delete the "id" elligibilityResponseStatus.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
