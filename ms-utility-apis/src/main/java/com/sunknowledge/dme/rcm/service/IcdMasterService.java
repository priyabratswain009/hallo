package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.IcdMaster}.
 */
public interface IcdMasterService {
    /**
     * Save a icdMaster.
     *
     * @param icdMasterDTO the entity to save.
     * @return the persisted entity.
     */
    IcdMasterDTO save(IcdMasterDTO icdMasterDTO);

    /**
     * Updates a icdMaster.
     *
     * @param icdMasterDTO the entity to update.
     * @return the persisted entity.
     */
    IcdMasterDTO update(IcdMasterDTO icdMasterDTO);

    /**
     * Partially updates a icdMaster.
     *
     * @param icdMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IcdMasterDTO> partialUpdate(IcdMasterDTO icdMasterDTO);

    /**
     * Get all the icdMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IcdMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" icdMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IcdMasterDTO> findOne(Long id);

    /**
     * Delete the "id" icdMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
