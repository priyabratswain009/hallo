package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimsSubmissionMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster}.
 */
public interface SecondaryClaimsSubmissionMasterService {
    /**
     * Save a secondaryClaimsSubmissionMaster.
     *
     * @param secondaryClaimsSubmissionMasterDTO the entity to save.
     * @return the persisted entity.
     */
    SecondaryClaimsSubmissionMasterDTO save(SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO);

    /**
     * Updates a secondaryClaimsSubmissionMaster.
     *
     * @param secondaryClaimsSubmissionMasterDTO the entity to update.
     * @return the persisted entity.
     */
    SecondaryClaimsSubmissionMasterDTO update(SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO);

    /**
     * Partially updates a secondaryClaimsSubmissionMaster.
     *
     * @param secondaryClaimsSubmissionMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SecondaryClaimsSubmissionMasterDTO> partialUpdate(SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO);

    /**
     * Get all the secondaryClaimsSubmissionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SecondaryClaimsSubmissionMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" secondaryClaimsSubmissionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SecondaryClaimsSubmissionMasterDTO> findOne(Long id);

    /**
     * Delete the "id" secondaryClaimsSubmissionMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
