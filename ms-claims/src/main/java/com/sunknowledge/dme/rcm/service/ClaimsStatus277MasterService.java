package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277MasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master}.
 */
public interface ClaimsStatus277MasterService {
    /**
     * Save a claimsStatus277Master.
     *
     * @param claimsStatus277MasterDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimsStatus277MasterDTO save(ClaimsStatus277MasterDTO claimsStatus277MasterDTO);

    /**
     * Updates a claimsStatus277Master.
     *
     * @param claimsStatus277MasterDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimsStatus277MasterDTO update(ClaimsStatus277MasterDTO claimsStatus277MasterDTO);

    /**
     * Partially updates a claimsStatus277Master.
     *
     * @param claimsStatus277MasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimsStatus277MasterDTO> partialUpdate(ClaimsStatus277MasterDTO claimsStatus277MasterDTO);

    /**
     * Get all the claimsStatus277Masters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimsStatus277MasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimsStatus277Master.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimsStatus277MasterDTO> findOne(Long id);

    /**
     * Delete the "id" claimsStatus277Master.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
