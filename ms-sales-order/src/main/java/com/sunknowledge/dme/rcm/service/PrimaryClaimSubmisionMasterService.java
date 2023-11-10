package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster}.
 */
public interface PrimaryClaimSubmisionMasterService {
    /**
     * Save a primaryClaimSubmisionMaster.
     *
     * @param primaryClaimSubmisionMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimSubmisionMasterDTO> save(PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO);

    /**
     * Updates a primaryClaimSubmisionMaster.
     *
     * @param primaryClaimSubmisionMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimSubmisionMasterDTO> update(PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO);

    /**
     * Partially updates a primaryClaimSubmisionMaster.
     *
     * @param primaryClaimSubmisionMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimSubmisionMasterDTO> partialUpdate(PrimaryClaimSubmisionMasterDTO primaryClaimSubmisionMasterDTO);

    /**
     * Get all the primaryClaimSubmisionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PrimaryClaimSubmisionMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of primaryClaimSubmisionMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" primaryClaimSubmisionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PrimaryClaimSubmisionMasterDTO> findOne(Long id);

    /**
     * Delete the "id" primaryClaimSubmisionMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
