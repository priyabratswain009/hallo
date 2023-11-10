package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus}.
 */
public interface ClaimSubmissionStatusService {
    /**
     * Save a claimSubmissionStatus.
     *
     * @param claimSubmissionStatusDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ClaimSubmissionStatusDTO> save(ClaimSubmissionStatusDTO claimSubmissionStatusDTO);

    /**
     * Updates a claimSubmissionStatus.
     *
     * @param claimSubmissionStatusDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ClaimSubmissionStatusDTO> update(ClaimSubmissionStatusDTO claimSubmissionStatusDTO);

    /**
     * Partially updates a claimSubmissionStatus.
     *
     * @param claimSubmissionStatusDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ClaimSubmissionStatusDTO> partialUpdate(ClaimSubmissionStatusDTO claimSubmissionStatusDTO);

    /**
     * Get all the claimSubmissionStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ClaimSubmissionStatusDTO> findAll(Pageable pageable);

    /**
     * Returns the number of claimSubmissionStatuses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" claimSubmissionStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ClaimSubmissionStatusDTO> findOne(Long id);

    /**
     * Delete the "id" claimSubmissionStatus.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
