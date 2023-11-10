package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.UserMaster}.
 */
public interface UserMasterService {
    /**
     * Save a userMaster.
     *
     * @param userMasterDTO the entity to save.
     * @return the persisted entity.
     */
    UserMasterDTO save(UserMasterDTO userMasterDTO);

    /**
     * Updates a userMaster.
     *
     * @param userMasterDTO the entity to update.
     * @return the persisted entity.
     */
    UserMasterDTO update(UserMasterDTO userMasterDTO);

    /**
     * Partially updates a userMaster.
     *
     * @param userMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserMasterDTO> partialUpdate(UserMasterDTO userMasterDTO);

    /**
     * Get all the userMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserMasterDTO> findOne(Long id);

    /**
     * Delete the "id" userMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
