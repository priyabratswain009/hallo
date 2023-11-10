package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835MasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCob835Master}.
 */
public interface ClaimsCob835MasterService {
    /**
     * Save a claimsCob835Master.
     *
     * @param claimsCob835MasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ClaimsCob835MasterDTO> save(ClaimsCob835MasterDTO claimsCob835MasterDTO);

    /**
     * Updates a claimsCob835Master.
     *
     * @param claimsCob835MasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ClaimsCob835MasterDTO> update(ClaimsCob835MasterDTO claimsCob835MasterDTO);

    /**
     * Partially updates a claimsCob835Master.
     *
     * @param claimsCob835MasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ClaimsCob835MasterDTO> partialUpdate(ClaimsCob835MasterDTO claimsCob835MasterDTO);

    /**
     * Get all the claimsCob835Masters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ClaimsCob835MasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of claimsCob835Masters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" claimsCob835Master.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ClaimsCob835MasterDTO> findOne(Long id);

    /**
     * Delete the "id" claimsCob835Master.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
