package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    ClaimSubmissionStatusDTO save(ClaimSubmissionStatusDTO claimSubmissionStatusDTO);

    /**
     * Updates a claimSubmissionStatus.
     *
     * @param claimSubmissionStatusDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimSubmissionStatusDTO update(ClaimSubmissionStatusDTO claimSubmissionStatusDTO);

    /**
     * Partially updates a claimSubmissionStatus.
     *
     * @param claimSubmissionStatusDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimSubmissionStatusDTO> partialUpdate(ClaimSubmissionStatusDTO claimSubmissionStatusDTO);

    /**
     * Get all the claimSubmissionStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimSubmissionStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimSubmissionStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimSubmissionStatusDTO> findOne(Long id);

    /**
     * Delete the "id" claimSubmissionStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
