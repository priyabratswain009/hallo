package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster}.
 */
public interface SecondaryClaimSubmisionMasterService {
    /**
     * Save a secondaryClaimSubmisionMaster.
     *
     * @param secondaryClaimSubmisionMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionMasterDTO> save(SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO);

    /**
     * Updates a secondaryClaimSubmisionMaster.
     *
     * @param secondaryClaimSubmisionMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionMasterDTO> update(SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO);

    /**
     * Partially updates a secondaryClaimSubmisionMaster.
     *
     * @param secondaryClaimSubmisionMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionMasterDTO> partialUpdate(SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO);

    /**
     * Get all the secondaryClaimSubmisionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SecondaryClaimSubmisionMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of secondaryClaimSubmisionMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" secondaryClaimSubmisionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SecondaryClaimSubmisionMasterDTO> findOne(Long id);

    /**
     * Delete the "id" secondaryClaimSubmisionMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
