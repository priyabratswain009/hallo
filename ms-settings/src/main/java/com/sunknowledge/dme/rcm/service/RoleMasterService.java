package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.RoleMaster}.
 */
public interface RoleMasterService {
    /**
     * Save a roleMaster.
     *
     * @param roleMasterDTO the entity to save.
     * @return the persisted entity.
     */
    RoleMasterDTO save(RoleMasterDTO roleMasterDTO);

    /**
     * Updates a roleMaster.
     *
     * @param roleMasterDTO the entity to update.
     * @return the persisted entity.
     */
    RoleMasterDTO update(RoleMasterDTO roleMasterDTO);

    /**
     * Partially updates a roleMaster.
     *
     * @param roleMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RoleMasterDTO> partialUpdate(RoleMasterDTO roleMasterDTO);

    /**
     * Get all the roleMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoleMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" roleMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoleMasterDTO> findOne(Long id);

    /**
     * Delete the "id" roleMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
