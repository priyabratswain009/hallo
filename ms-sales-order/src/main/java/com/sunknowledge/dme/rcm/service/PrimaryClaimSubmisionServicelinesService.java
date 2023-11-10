package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionServicelinesDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines}.
 */
public interface PrimaryClaimSubmisionServicelinesService {
    /**
     * Save a primaryClaimSubmisionServicelines.
     *
     * @param primaryClaimSubmisionServicelinesDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimSubmisionServicelinesDTO> save(PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO);

    /**
     * Updates a primaryClaimSubmisionServicelines.
     *
     * @param primaryClaimSubmisionServicelinesDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimSubmisionServicelinesDTO> update(PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO);

    /**
     * Partially updates a primaryClaimSubmisionServicelines.
     *
     * @param primaryClaimSubmisionServicelinesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimSubmisionServicelinesDTO> partialUpdate(PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO);

    /**
     * Get all the primaryClaimSubmisionServicelines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PrimaryClaimSubmisionServicelinesDTO> findAll(Pageable pageable);

    /**
     * Returns the number of primaryClaimSubmisionServicelines available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" primaryClaimSubmisionServicelines.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PrimaryClaimSubmisionServicelinesDTO> findOne(Long id);

    /**
     * Delete the "id" primaryClaimSubmisionServicelines.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
