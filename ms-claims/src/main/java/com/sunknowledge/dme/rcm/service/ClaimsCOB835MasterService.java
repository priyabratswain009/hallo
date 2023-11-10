package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835MasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master}.
 */
public interface ClaimsCOB835MasterService {
    /**
     * Save a claimsCOB835Master.
     *
     * @param claimsCOB835MasterDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimsCOB835MasterDTO save(ClaimsCOB835MasterDTO claimsCOB835MasterDTO);

    /**
     * Updates a claimsCOB835Master.
     *
     * @param claimsCOB835MasterDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimsCOB835MasterDTO update(ClaimsCOB835MasterDTO claimsCOB835MasterDTO);

    /**
     * Partially updates a claimsCOB835Master.
     *
     * @param claimsCOB835MasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimsCOB835MasterDTO> partialUpdate(ClaimsCOB835MasterDTO claimsCOB835MasterDTO);

    /**
     * Get all the claimsCOB835Masters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimsCOB835MasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimsCOB835Master.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimsCOB835MasterDTO> findOne(Long id);

    /**
     * Delete the "id" claimsCOB835Master.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
