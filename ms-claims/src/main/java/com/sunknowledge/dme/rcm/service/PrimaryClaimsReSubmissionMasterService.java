package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimsReSubmissionMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster}.
 */
public interface PrimaryClaimsReSubmissionMasterService {
    /**
     * Save a primaryClaimsReSubmissionMaster.
     *
     * @param primaryClaimsReSubmissionMasterDTO the entity to save.
     * @return the persisted entity.
     */
    PrimaryClaimsReSubmissionMasterDTO save(PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO);

    /**
     * Updates a primaryClaimsReSubmissionMaster.
     *
     * @param primaryClaimsReSubmissionMasterDTO the entity to update.
     * @return the persisted entity.
     */
    PrimaryClaimsReSubmissionMasterDTO update(PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO);

    /**
     * Partially updates a primaryClaimsReSubmissionMaster.
     *
     * @param primaryClaimsReSubmissionMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PrimaryClaimsReSubmissionMasterDTO> partialUpdate(PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO);

    /**
     * Get all the primaryClaimsReSubmissionMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PrimaryClaimsReSubmissionMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" primaryClaimsReSubmissionMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PrimaryClaimsReSubmissionMasterDTO> findOne(Long id);

    /**
     * Delete the "id" primaryClaimsReSubmissionMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
