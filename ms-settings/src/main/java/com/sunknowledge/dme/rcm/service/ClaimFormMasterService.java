package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimFormMaster}.
 */
public interface ClaimFormMasterService {
    /**
     * Save a claimFormMaster.
     *
     * @param claimFormMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimFormMasterDTO save(ClaimFormMasterDTO claimFormMasterDTO);

    /**
     * Updates a claimFormMaster.
     *
     * @param claimFormMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimFormMasterDTO update(ClaimFormMasterDTO claimFormMasterDTO);

    /**
     * Partially updates a claimFormMaster.
     *
     * @param claimFormMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimFormMasterDTO> partialUpdate(ClaimFormMasterDTO claimFormMasterDTO);

    /**
     * Get all the claimFormMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimFormMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimFormMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimFormMasterDTO> findOne(Long id);

    /**
     * Delete the "id" claimFormMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
