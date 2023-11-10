package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionServicelinesDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines}.
 */
public interface SecondaryClaimSubmisionServicelinesService {
    /**
     * Save a secondaryClaimSubmisionServicelines.
     *
     * @param secondaryClaimSubmisionServicelinesDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionServicelinesDTO> save(SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO);

    /**
     * Updates a secondaryClaimSubmisionServicelines.
     *
     * @param secondaryClaimSubmisionServicelinesDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionServicelinesDTO> update(SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO);

    /**
     * Partially updates a secondaryClaimSubmisionServicelines.
     *
     * @param secondaryClaimSubmisionServicelinesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionServicelinesDTO> partialUpdate(
        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO
    );

    /**
     * Get all the secondaryClaimSubmisionServicelines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SecondaryClaimSubmisionServicelinesDTO> findAll(Pageable pageable);

    /**
     * Returns the number of secondaryClaimSubmisionServicelines available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" secondaryClaimSubmisionServicelines.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SecondaryClaimSubmisionServicelinesDTO> findOne(Long id);

    /**
     * Delete the "id" secondaryClaimSubmisionServicelines.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
