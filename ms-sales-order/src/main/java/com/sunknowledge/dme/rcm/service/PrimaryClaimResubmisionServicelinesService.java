package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionServicelinesDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines}.
 */
public interface PrimaryClaimResubmisionServicelinesService {
    /**
     * Save a primaryClaimResubmisionServicelines.
     *
     * @param primaryClaimResubmisionServicelinesDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimResubmisionServicelinesDTO> save(PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO);

    /**
     * Updates a primaryClaimResubmisionServicelines.
     *
     * @param primaryClaimResubmisionServicelinesDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimResubmisionServicelinesDTO> update(PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO);

    /**
     * Partially updates a primaryClaimResubmisionServicelines.
     *
     * @param primaryClaimResubmisionServicelinesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimResubmisionServicelinesDTO> partialUpdate(
        PrimaryClaimResubmisionServicelinesDTO primaryClaimResubmisionServicelinesDTO
    );

    /**
     * Get all the primaryClaimResubmisionServicelines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PrimaryClaimResubmisionServicelinesDTO> findAll(Pageable pageable);

    /**
     * Returns the number of primaryClaimResubmisionServicelines available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" primaryClaimResubmisionServicelines.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PrimaryClaimResubmisionServicelinesDTO> findOne(Long id);

    /**
     * Delete the "id" primaryClaimResubmisionServicelines.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
