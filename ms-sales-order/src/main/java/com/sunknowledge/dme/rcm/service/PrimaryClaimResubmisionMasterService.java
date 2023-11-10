package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionMaster}.
 */
public interface PrimaryClaimResubmisionMasterService {
    /**
     * Save a primaryClaimResubmisionMaster.
     *
     * @param primaryClaimResubmisionMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimResubmisionMasterDTO> save(PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO);

    /**
     * Updates a primaryClaimResubmisionMaster.
     *
     * @param primaryClaimResubmisionMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimResubmisionMasterDTO> update(PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO);

    /**
     * Partially updates a primaryClaimResubmisionMaster.
     *
     * @param primaryClaimResubmisionMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PrimaryClaimResubmisionMasterDTO> partialUpdate(PrimaryClaimResubmisionMasterDTO primaryClaimResubmisionMasterDTO);

    /**
     * Get all the primaryClaimResubmisionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PrimaryClaimResubmisionMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of primaryClaimResubmisionMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" primaryClaimResubmisionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PrimaryClaimResubmisionMasterDTO> findOne(Long id);

    /**
     * Delete the "id" primaryClaimResubmisionMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
