package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsSubmissionMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster}.
 */
public interface ClaimsSubmissionMasterService {
    /**
     * Save a claimsSubmissionMaster.
     *
     * @param claimsSubmissionMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimsSubmissionMasterDTO save(ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO);

    /**
     * Updates a claimsSubmissionMaster.
     *
     * @param claimsSubmissionMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimsSubmissionMasterDTO update(ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO);

    /**
     * Partially updates a claimsSubmissionMaster.
     *
     * @param claimsSubmissionMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimsSubmissionMasterDTO> partialUpdate(ClaimsSubmissionMasterDTO claimsSubmissionMasterDTO);

    /**
     * Get all the claimsSubmissionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimsSubmissionMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimsSubmissionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimsSubmissionMasterDTO> findOne(Long id);

    /**
     * Delete the "id" claimsSubmissionMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
