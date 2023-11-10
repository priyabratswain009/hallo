package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimProgramMaster}.
 */
public interface ClaimProgramMasterService {
    /**
     * Save a claimProgramMaster.
     *
     * @param claimProgramMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimProgramMasterDTO save(ClaimProgramMasterDTO claimProgramMasterDTO);

    /**
     * Updates a claimProgramMaster.
     *
     * @param claimProgramMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimProgramMasterDTO update(ClaimProgramMasterDTO claimProgramMasterDTO);

    /**
     * Partially updates a claimProgramMaster.
     *
     * @param claimProgramMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimProgramMasterDTO> partialUpdate(ClaimProgramMasterDTO claimProgramMasterDTO);

    /**
     * Get all the claimProgramMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimProgramMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimProgramMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimProgramMasterDTO> findOne(Long id);

    /**
     * Delete the "id" claimProgramMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
